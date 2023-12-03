package CustomerManagementGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomerManagementGUI extends JFrame {
    private ArrayList<Customer> customers = new ArrayList<>();
    private JTextField idField, nameField, emailField;

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
                addCustomer();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteCustomer();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCustomer();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateReport();
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
    }

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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomerManagementGUI());
    }
}

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

