package CustomerManagementGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerManagementGUI extends JFrame {
    private ArrayList<Customer> customers = new ArrayList<>();
    private JTextField idField, nameField, emailField;

    // Database connection details
    private static final String JDBC_URL = "jdbc:ucanaccess://C:/Path/To/Your/Database.accdb";
    private static final String DB_USER = ""; // Your database username (if any)
    private static final String DB_PASSWORD = ""; // Your database password (if any)

    public CustomerManagementGUI() {
        // Your existing GUI code

        // Initialize the database connection
        initializeDatabase();

        // Rest of your code
    }

    private void initializeDatabase() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            createTableIfNotExists(connection);
            loadCustomersFromDatabase(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initializing database: " + e.getMessage());
        }
    }

    private void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Customers (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        }
    }

    private void loadCustomersFromDatabase(Connection connection) throws SQLException {
        String selectSQL = "SELECT * FROM Customers";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                customers.add(new Customer(id, name, email));
            }
        }
    }

    private void addCustomerToDatabase(Customer newCustomer) {
        String insertSQL = "INSERT INTO Customers (id, name, email) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, newCustomer.getId());
            preparedStatement.setString(2, newCustomer.getName());
            preparedStatement.setString(3, newCustomer.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer to database: " + e.getMessage());
        }
    }

    // ... (your existing code)

    private void deleteCustomerFromDatabase(String customerId) {
        String deleteSQL = "DELETE FROM Customers WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, customerId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting customer from database: " + e.getMessage());
        }
    }

    private void updateCustomerInDatabase(Customer updatedCustomer) {
        String updateSQL = "UPDATE Customers SET name = ?, email = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, updatedCustomer.getName());
            preparedStatement.setString(2, updatedCustomer.getEmail());
            preparedStatement.setString(3, updatedCustomer.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating customer in database: " + e.getMessage());
        }
    }

    private void generateReportFromDatabase() {
        StringBuilder report = new StringBuilder("Customer Report:\n");
        for (Customer customer : customers) {
            report.append(customer).append("\n");
        }
        JOptionPane.showMessageDialog(this, report.toString());
    }

    // ... (rest of your code)


        // Similar modifications for delete, update, and report methods

        // Rest of your code
    }

    addButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get customer details from the input fields (idField, nameField, emailField)
        String id = idField.getText();
        String name = nameField.getText();
        String email = emailField.getText();

        // Create a new Customer object
        Customer newCustomer = new Customer(id, name, email);

        // Call the method to add the customer to the database
        addCustomerToDatabase(newCustomer);

        // Optionally, you can clear the input fields or perform other actions after adding the customer
        idField.setText("");
        nameField.setText("");
        emailField.setText("");
    }
});

