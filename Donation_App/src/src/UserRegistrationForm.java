
package src;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRegistrationForm extends JFrame implements ActionListener {
    private JComboBox<String> userTypeComboBox;
    private JComboBox<String> locationComboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField phoneNumberField;
    private JTextField emailField;

    public UserRegistrationForm() {
        setTitle("User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userTypeLabel = new JLabel("User Type:");
        panel.add(userTypeLabel);
        userTypeComboBox = new JComboBox<>(new String[]{"Admin", "User", "Guest"});
        panel.add(userTypeComboBox);

        JLabel locationLabel = new JLabel("Location:");
        panel.add(locationLabel);
        locationComboBox = new JComboBox<>(new String[]{"Location A", "Location B", "Location C"});
        panel.add(locationComboBox);

        JLabel usernameLabel = new JLabel("Username:");
        panel.add(usernameLabel);
        usernameField = new JTextField();
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JLabel phoneNumberLabel = new JLabel("Phone Number:");
        panel.add(phoneNumberLabel);
        phoneNumberField = new JTextField();
        panel.add(phoneNumberField);

        JLabel emailLabel = new JLabel("Email:");
        panel.add(emailLabel);
        emailField = new JTextField();
        panel.add(emailField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        panel.add(registerButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Register")) {
            String userType = (String) userTypeComboBox.getSelectedItem();
            String location = (String) locationComboBox.getSelectedItem();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String phoneNumber = phoneNumberField.getText();
            String email = emailField.getText();

            // Here, you can implement the registration logic
            // For now, let's just display the entered details
            JOptionPane.showMessageDialog(this,
                    "User Type: " + userType + "\n" +
                            "Location: " + location + "\n" +
                            "Username: " + username + "\n" +
                            "Password: " + password + "\n" +
                            "Phone Number: " + phoneNumber + "\n" +
                            "Email: " + email,
                    "Registration Details",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UserRegistrationForm registrationForm = new UserRegistrationForm();
            registrationForm.setVisible(true);
        });
    }
}
