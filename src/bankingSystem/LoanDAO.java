package bankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LoanDAO {

    public boolean insertLoan(
            String accountNumber,
            double amount,
            String status) {

        String sql =
                "INSERT INTO loans "
                + "(account_number, amount, status) "
                + "VALUES (?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setDouble(2, amount);
            statement.setString(3, status);

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                    "Loan record inserted successfully."
                );

                return true;
            }

        } catch (SQLException e) {

            System.out.println(
                "Failed to insert loan record."
            );

            e.printStackTrace();
        }

        return false;
    }
    public void fetchLoan(String accountNumber) {

        String sql =
                "SELECT loan_id, account_number, "
                + "amount, status, loan_date "
                + "FROM loans "
                + "WHERE account_number = ? "
                + "ORDER BY loan_id DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);

            java.sql.ResultSet resultSet =
                    statement.executeQuery();

            boolean found = false;

            while (resultSet.next()) {

                found = true;

                System.out.println(
                    "\n-----------------------------"
                );

                System.out.println(
                    "Loan ID: "
                    + resultSet.getInt("loan_id")
                );

                System.out.println(
                    "Account Number: "
                    + resultSet.getString("account_number")
                );

                System.out.println(
                    "Loan Amount: ₹"
                    + resultSet.getDouble("amount")
                );

                System.out.println(
                    "Status: "
                    + resultSet.getString("status")
                );

                System.out.println(
                    "Loan Date: "
                    + resultSet.getTimestamp("loan_date")
                );

                System.out.println(
                    "-----------------------------"
                );
            }

            if (!found) {

                System.out.println(
                    "No loan records found for account "
                    + accountNumber
                );
            }

        } catch (SQLException e) {

            System.out.println(
                "Failed to fetch loan details."
            );

            e.printStackTrace();
        }
    }
    public boolean updateLoanStatus(
            int loanId,
            String status) {

        String sql =
                "UPDATE loans "
                + "SET status = ? "
                + "WHERE loan_id = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, status);
            statement.setInt(2, loanId);

            int rows =
                    statement.executeUpdate();

            if (rows > 0) {

                System.out.println(
                    "Loan status updated successfully."
                );

                return true;
            }

            System.out.println(
                "Loan ID not found."
            );

        } catch (SQLException e) {

            System.out.println(
                "Failed to update loan status."
            );

            e.printStackTrace();
        }

        return false;
    }
}