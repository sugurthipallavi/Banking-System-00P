package bankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDAO {

    public void insertAccount(Account account) {

        String sql = "INSERT INTO accounts "
                + "(account_number, customer_id, balance, account_type, interest_rate, overdraft_limit) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, account.getAccountNumber());
            statement.setString(2, account.getCustomer().getCustomerId());
            statement.setDouble(3, account.getBalance());

            if (account instanceof SavingsAccount) {

                SavingsAccount savingsAccount =
                        (SavingsAccount) account;

                statement.setString(4, "SAVINGS");
                statement.setDouble(5, savingsAccount.getInterestRate());
                statement.setNull(6, java.sql.Types.DECIMAL);

            } else if (account instanceof CurrentAccount) {

                CurrentAccount currentAccount =
                        (CurrentAccount) account;

                statement.setString(4, "CURRENT");
                statement.setNull(5, java.sql.Types.DECIMAL);
                statement.setDouble(6, currentAccount.getOverdraftLimit());
            }

            statement.executeUpdate();

            System.out.println("Account inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to insert account.");
            e.printStackTrace();
        }
    }
    public Account fetchAccount(String accountNumber) {

        String sql = "SELECT account_number, customer_id, balance, "
                + "account_type, interest_rate, overdraft_limit "
                + "FROM accounts WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String accountNo =
                        resultSet.getString("account_number");

                String customerId =
                        resultSet.getString("customer_id");

                double balance =
                        resultSet.getDouble("balance");

                String accountType =
                        resultSet.getString("account_type");

                CustomerDAO customerDAO = new CustomerDAO();

                Customer customer =
                        customerDAO.fetchCustomer(customerId);

                if (customer == null) {
                    System.out.println("Customer associated with account not found.");
                    return null;
                }

                if ("SAVINGS".equalsIgnoreCase(accountType)) {

                    double interestRate =
                            resultSet.getDouble("interest_rate");

                    return new SavingsAccount(
                            accountNo,
                            balance,
                            customer,
                            interestRate
                    );

                } else if ("CURRENT".equalsIgnoreCase(accountType)) {

                    double overdraftLimit =
                            resultSet.getDouble("overdraft_limit");

                    return new CurrentAccount(
                            accountNo,
                            balance,
                            customer,
                            overdraftLimit
                    );
                }
            }

            System.out.println("Account not found.");

        } catch (SQLException e) {

            System.out.println("Failed to fetch account.");
            e.printStackTrace();
        }

        return null;
    }
    public void updateBalance(String accountNumber, double newBalance) {

        String sql = "UPDATE accounts "
                + "SET balance = ? "
                + "WHERE account_number = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, newBalance);
            statement.setString(2, accountNumber);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Account balance updated successfully!");
            } else {
                System.out.println("Account not found.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to update account balance.");
            e.printStackTrace();
        }
    }
    public boolean transferMoney(
            String fromAccount,
            String toAccount,
            double amount) {

        if (amount <= 0) {

            System.out.println(
                "Transfer amount must be greater than zero."
            );

            return false;
        }

        if (fromAccount == null ||
            toAccount == null ||
            fromAccount.equals(toAccount)) {

            System.out.println(
                "Invalid transfer accounts."
            );

            return false;
        }

        String checkBalanceSql =
            "SELECT balance FROM accounts "
            + "WHERE account_number = ? "
            + "FOR UPDATE";

        String updateSql =
            "UPDATE accounts "
            + "SET balance = balance + ? "
            + "WHERE account_number = ?";

        String transactionSql =
            "INSERT INTO transactions "
            + "(account_number, transaction_type, amount, description) "
            + "VALUES (?, ?, ?, ?)";

        Connection connection = null;

        try {

            connection =
                DatabaseConnection.getConnection();

            connection.setAutoCommit(false);

            // =====================================================
            // CHECK SOURCE ACCOUNT BALANCE
            // =====================================================

            double sourceBalance;

            try (PreparedStatement statement =
                    connection.prepareStatement(
                        checkBalanceSql)) {

                statement.setString(1, fromAccount);

                var resultSet =
                    statement.executeQuery();

                if (!resultSet.next()) {

                    throw new SQLException(
                        "Source account not found."
                    );
                }

                sourceBalance =
                    resultSet.getDouble("balance");
            }

            if (sourceBalance < amount) {

                throw new SQLException(
                    "Insufficient balance."
                );
            }

            // =====================================================
            // CHECK TARGET ACCOUNT
            // =====================================================

            try (PreparedStatement statement =
                    connection.prepareStatement(
                        "SELECT account_number "
                        + "FROM accounts "
                        + "WHERE account_number = ? "
                        + "FOR UPDATE")) {

                statement.setString(1, toAccount);

                var resultSet =
                    statement.executeQuery();

                if (!resultSet.next()) {

                    throw new SQLException(
                        "Target account not found."
                    );
                }
            }

            // =====================================================
            // DEDUCT FROM SOURCE ACCOUNT
            // =====================================================

            try (PreparedStatement statement =
                    connection.prepareStatement(
                        updateSql)) {

                statement.setDouble(1, -amount);
                statement.setString(2, fromAccount);

                statement.executeUpdate();
            }

            // =====================================================
            // ADD TO TARGET ACCOUNT
            // =====================================================

            try (PreparedStatement statement =
                    connection.prepareStatement(
                        updateSql)) {

                statement.setDouble(1, amount);
                statement.setString(2, toAccount);

                statement.executeUpdate();
            }

            // =====================================================
            // RECORD TRANSFER
            // =====================================================

            try (PreparedStatement statement =
                    connection.prepareStatement(
                        transactionSql)) {

                statement.setString(1, fromAccount);
                statement.setString(2, "TRANSFER");
                statement.setDouble(3, amount);

                statement.setString(
                    4,
                    "Transfer to " + toAccount
                );

                statement.executeUpdate();
            }

            // =====================================================
            // COMMIT
            // =====================================================

            connection.commit();

            System.out.println(
                "Transfer completed successfully."
            );

            return true;

        } catch (SQLException e) {

            if (connection != null) {

                try {

                    connection.rollback();

                    System.out.println(
                        "Transfer failed. Changes rolled back."
                    );

                } catch (SQLException rollbackException) {

                    rollbackException.printStackTrace();
                }
            }

            System.out.println(
                "Transfer error: " +
                e.getMessage()
            );

            return false;

        } finally {

            if (connection != null) {

                try {

                    connection.setAutoCommit(true);
                    connection.close();

                } catch (SQLException e) {

                    e.printStackTrace();
                }
            }
        }
    }
}