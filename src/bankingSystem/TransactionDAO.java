package bankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionDAO {

    // Insert a transaction into the database
    public void insertTransaction(
            String accountNumber,
            String transactionType,
            double amount,
            String description) {

        String sql = "INSERT INTO transactions "
                + "(account_number, transaction_type, amount, description) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, accountNumber);
            statement.setString(2, transactionType);
            statement.setDouble(3, amount);
            statement.setString(4, description);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Transaction recorded successfully!");
            } else {
                System.out.println("Transaction was not recorded.");
            }

        } catch (SQLException e) {

            System.out.println("Failed to record transaction.");
            e.printStackTrace();
        }
    }
    public void insertTransfer(
            String fromAccount,
            String toAccount,
            double amount) {

        String sql = "INSERT INTO transactions "
                + "(account_number, transaction_type, amount, description) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            // Record transfer for source account
            statement.setString(1, fromAccount);
            statement.setString(2, "TRANSFER");
            statement.setDouble(3, amount);
            statement.setString(
                    4,
                    "Transfer to " + toAccount
            );

            statement.executeUpdate();

            System.out.println(
                    "Transfer transaction recorded successfully!"
            );

        } catch (SQLException e) {

            System.out.println(
                    "Failed to record transfer."
            );

            e.printStackTrace();
        }
    }
}