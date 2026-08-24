package bankingSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerDAO {

    public void insertCustomer(Customer customer) {

        String sql = "INSERT INTO customers (customer_id, name, age) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customer.getCustomerId());
            statement.setString(2, customer.getName());
            statement.setInt(3, customer.getAge());

            statement.executeUpdate();

            System.out.println("Customer inserted successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to insert customer.");
            e.printStackTrace();
        }
    }
    public Customer fetchCustomer(String customerId) {

        String sql = "SELECT customer_id, name, age FROM customers WHERE customer_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, customerId);

            var resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String id = resultSet.getString("customer_id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                return new Customer(id, name, age);
            }

            System.out.println("Customer not found.");

        } catch (SQLException e) {
            System.out.println("Failed to fetch customer.");
            e.printStackTrace();
        }

        return null;
    }
}