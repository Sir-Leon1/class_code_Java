package CustomerManagementGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CustomerManagementGUI extends JFrame {
    private ArrayList<Customer> customers = new ArrayList<>();
    private JTextField idField, nameField, emailField;

    private static final String JDBC_URL = "jdbc:ucanaccess://Contacts_App.accdb";


/*
 * Below CustomerManagementGUI is a construct for the application Graphical User Interface.
 */
    public CustomerManagementGUI() {
        setTitle("Customer Management App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        idField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);

        JButton addButton = new JButton("Add Customer");
        JButton deleteButton = new JButton("Delete Customer");
        JButton updateButton = new JButton("Update Customer");
        JButton reportButton = new JButton("Generate Report");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();

                Customer newCustomer = new Customer(id, name, email);
                addCustomerToDatabase(newCustomer);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerId = idField.getText();
                deleteCustomerFromDatabase(customerId);
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String email = emailField.getText();

                Customer updatedCustomer = new Customer(id, name, email);
                updateCustomerInDatabase(updatedCustomer);
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReportFromDatabase();
            }
        });

        panel.add(new JLabel("Customer ID:"));
        panel.add(idField);
        panel.add(new JLabel("Customer Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Customer Email:"));
        panel.add(emailField);
        panel.add(addButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(reportButton);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        add(panel);
        setVisible(true);

        initializeDatabase();
    }

    /*initializedatabase initializes the database */
    private void initializeDatabase() {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            createTableIfNotExists(connection);
            loadCustomersFromDatabase(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initialising database: " + e.getMessage());
        }
    }
    
    private static void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE Customers (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
            System.out.println("Table 'Customers' created successfully (if it didn't exist already).");
        } catch (SQLException e) {
            // Handle the exception (print the stack trace for now)
            e.printStackTrace();
        }
    }

/*
    private void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Customers (id VARCHAR(255) PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        }
    }
*/

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
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
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

    private void deleteCustomerFromDatabase(String customerId) {
        String deleteSQL = "DELETE FROM Customers WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, customerId);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer deleted successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deletiong customer" + e.getMessage());
        }
    }

    private void updateCustomerInDatabase(Customer updateCustomer) {
        String updateSQL = "UPDATE Customers SET name = ?, email = ? WHERE id = ?";
        try (Connection connection =  DriverManager.getConnection(JDBC_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, updateCustomer.getName());
            preparedStatement.setString(2, updateCustomer.getEmail());
            preparedStatement.setString(3, updateCustomer.getId());
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Customer updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Customer not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating customer: " + e.getMessage());
        }
    }

    private void generateReportFromDatabase() {
        StringBuilder report = new StringBuilder("Customer Report:\n");
        for (Customer customer : customers) {
            report.append(customer).append("\n");
        }
        JOptionPane.showMessageDialog(this, report.toString());
    }


/*

//The below methods (add, delete, update customer, generate reprt Work on the implementation withouta database)

    private void addCustomer() {
        String id = idField.getText();
        String name = nameField.getText();
        String email = emailField.getText();

        Customer newCustomer = new Customer(id, name, email);
        customers.add(newCustomer);

        JOptionPane.showMessageDialog(this, "Customer added successfully!");
    }

    private void deleteCustomer() {
        String idToDelete = idField.getText();

        customers.removeIf(customer -> customer.getId().equals(idToDelete));

        JOptionPane.showMessageDialog(this, "Customer deleted successfully!");
    }

    private void updateCustomer() {
        String idToUpdate = idField.getText();

        for (Customer customer : customers) {
            if (customer.getId().equals(idToUpdate)) {
                String newName = JOptionPane.showInputDialog("Enter new name:");
                String newEmail = JOptionPane.showInputDialog("Enter new email:");

                customer = new Customer(customer.getId(), newName, newEmail);
                JOptionPane.showMessageDialog(this, "Customer updated successfully!");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "Customer not found!");
    }

    private void generateReport() {
        StringBuilder report = new StringBuilder("Customer Report:\n");
        for (Customer customer : customers) {
            report.append(customer).append("\n");
        }
        JOptionPane.showMessageDialog(this, report.toString());
    }

    */


    public static void main(String[] args) {
        new CustomerManagementGUI();
    }
}

/*
 * class customer implements the array for the applications first
 * primitive version.
 */
class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", email=" + email + "]";
    }
}

