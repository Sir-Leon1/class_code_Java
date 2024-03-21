package src;

import java.awt.event.ActionListener;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;


public class UserRegForm implements ActionListener {
    
    private static JLabel locationLbl, usrTypeLbl, passwordLbl, userNameLbl, phoneLbl, emailLbl;
    private static JTextField userNameField, phoneField, emailField;
    private static JPasswordField passwordField;
    public static JButton button, loginButton;
    private static JComboBox<String> usrTypeComboBox, locationComboBox;
    private static JButton registerButton;
    private JPanel regPanel = new JPanel();
    public static JFrame regFrame;

    public UserRegForm() {
        regFrame = new JFrame();
        regFrame.setTitle("New User Registration");
        regFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        regFrame.setSize(400, 300);
        regFrame.setLocationRelativeTo(null);

        regPanel.setLayout(new GridLayout(7, 2, 10, 10));
        regPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        usrTypeLbl = new JLabel("User Type");
        usrTypeComboBox = new JComboBox<>(new String[]{"Select here", "Admin", "Donor", "Recipient"});
        regPanel.add(usrTypeLbl);
        regPanel.add(usrTypeComboBox);

        locationLbl = new JLabel("Location");
        locationComboBox = new JComboBox<>(new String[]{"Select here", "Location A", "Location B", "Locatin C"});
        regPanel.add(locationLbl);
        regPanel.add(locationComboBox);
        
        //creates a label and 
        userNameLbl = new JLabel("Username");
        userNameField = new JTextField();
        regPanel.add(userNameLbl);
        regPanel.add(userNameField);

        passwordLbl = new JLabel("Set a password");
        passwordField = new JPasswordField();
        regPanel.add(passwordLbl);
        regPanel.add(passwordField);

        phoneLbl = new JLabel("Phone number");
        phoneField = new JTextField();
        regPanel.add(phoneLbl);
        regPanel.add(phoneField);

        emailLbl = new JLabel("Email:");
        emailField = new JTextField();
        regPanel.add(emailLbl);
        regPanel.add(emailField);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        regPanel.add(registerButton);

        loginButton = new JButton("Back to Login");
        loginButton.addActionListener(this);
        regPanel.add(loginButton);
        //implement back to login button

        regFrame.add(regPanel);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == registerButton){
                DBFunctionality.initializeDB();
                String userType = (String)  usrTypeComboBox.getSelectedItem();
                String location = (String) locationComboBox.getSelectedItem();
                String username = userNameField.getText();
                String password = new String(passwordField.getPassword());
                String phoneNumber = phoneField.getText();
                String email = emailField.getText();

                Users newUsers = new Users(email, username, password, location, userType, phoneNumber);
                DBFunctionality.addUserToDB(newUsers);
/**
                //Implement A database to store the information
                //Shows the selected information on a dialog box
                JOptionPane.showMessageDialog(this,
                "User Type: " + userType + "\n" +
                "Location: " + location + "\n" +
                "Password: " + password + "\n" +
                "Phone Number:" + phoneNumber + "\n" +
                "Email: " + email,
                "Registration Details",
                JOptionPane.INFORMATION_MESSAGE);
*/
            }

            else if (e.getSource() == loginButton){
                regFrame.dispose();
                Login.login_GUI();

            }
    }

    


}