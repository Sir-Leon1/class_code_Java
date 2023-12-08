package CustomerManagementGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class CustomerManagementGUI extends JFrame {
    private ArrayList<Customer> customers = new ArrayList<>();
    private JTextField phoneField, nameField, emailField;

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

        phoneField = new JTextField(20);
        nameField = new JTextField(20);
        emailField = new JTextField(20);

        JButton addButton = new JButton("Add Customer");
        JButton deleteButton = new JButton("Delete Customer");
        JButton updateButton = new JButton("Update Customer");
        JButton reportButton = new JButton("Generate Report");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String phone = phoneField.getText();
                String name = nameField.getText();
                String email = emailField.getText();

                Customer newCustomer = new Customer(phone, name, email);
                addCustomerToDatabase(newCustomer);

                phoneField.setText("");
                nameField.setText("");
                emailField.setText("");
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerPhone = phoneField.getText();
                deleteCustomerFromDatabase(customerPhone);

                phoneField.setText("");
                nameField.setText("");
                emailField.setText("");
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = phoneField.getText();
                String name = nameField.getText();
                String email = emailField.getText();

                Customer updatedCustomer = new Customer(phone, name, email);
                updateCustomerInDatabase(updatedCustomer);

                phoneField.setText("");
                nameField.setText("");
                emailField.setText("");
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReportFromDatabase();

                phoneField.setText("");
                nameField.setText("");
                emailField.setText("");
            }
        });

        panel.add(new JLabel("Customer Phone:"));
        panel.add(phoneField);
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
            //createTableIfNotExists(connection);
            loadCustomersFromDatabase(connection);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error initialising database: " + e.getMessage());
        }
    }

/*
    private static void createTableIfNotExists(Connection connection) {
        String createTableSQL = "CREATE TABLE Customers (phone VARCHAR(255) PRIMARY KEY, name VARCHAR(255), email VARCHAR(255))";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
            System.out.println("Table 'Customers' created successfully (if it didn't exist already).");
        } catch (SQLException e) {
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
                String phone = resultSet.getString("phone");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                customers.add(new Customer(phone, name, email));
            }
        }
    }

    private void addCustomerToDatabase(Customer newCustomer) {
        String insertSQL = "INSERT INTO Customers (phone, name, email) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, newCustomer.getPhone());
            preparedStatement.setString(2, newCustomer.getName());
            preparedStatement.setString(3, newCustomer.getEmail());
            int rowsAffected = preparedStatement.executeUpdate();
            
            if (rowsAffected > 0) {
            	JOptionPane.showMessageDialog(this, "Contact added Successfully");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer to database: " + e.getMessage());
        }
    }

    private void deleteCustomerFromDatabase(String customerPhone) {
        String deleteSQL = "DELETE FROM Customers WHERE phone = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {
            preparedStatement.setString(1, customerPhone);
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
        String updateSQL = "UPDATE Customers SET name = ?, email = ? WHERE phone = ?";
        try (Connection connection =  DriverManager.getConnection(JDBC_URL);
        PreparedStatement preparedStatement = connection.prepareStatement(updateSQL)) {
            preparedStatement.setString(1, updateCustomer.getName());
            preparedStatement.setString(2, updateCustomer.getEmail());
            preparedStatement.setString(3, updateCustomer.getPhone());
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
	
	    try (Connection connection = DriverManager.getConnection(JDBC_URL);
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customers");
	         ResultSet resultSet = preparedStatement.executeQuery()) {
	
	        while (resultSet.next()) {
	            String phone = resultSet.getString("phone");
	            String name = resultSet.getString("name");
	            String email = resultSet.getString("email");
	            report.append("Customer [phone=").append(phone).append(", name=").append(name).append(", email=").append(email).append("]\n");
	        }
	
	    } catch (SQLException e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error generating report from database: " + e.getMessage());
	    }
	
	    JOptionPane.showMessageDialog(this, report.toString());
	}




/*
    private void generateReportFromDatabase() {
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
    private String phone;
    private String name;
    private String email;

    public Customer(String phone, String name, String email) {
        this.phone = phone;
        this.name = name;
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setId(String phone) {
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return ":   " + name + "    " + phone + "    " + email + "";
    }
}

