package src;
//import src.*;

import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.event.*;

public class Login implements ActionListener{

    private static JLabel label;
    private static JTextField usertext;
    private static JLabel password;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;
    private static JButton registerBtn;
    public static JPanel loginPanel = new JPanel();
    public static JFrame frame;

    public Login() {

        frame = new JFrame();
        
        
        /**
         * Create the frame of the 
         * interface and add the panel to it
         */
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(loginPanel);
        //sets the GUI at the center of the screen
        frame.setLocationRelativeTo(null);
        loginPanel.setLayout(null);

        //label for the Username
        label = new JLabel("User");
        label.setBounds(10, 20, 80, 25);
        loginPanel.add(label);
        //Text area for the username text
        usertext = new JTextField();
        usertext.setBounds(100, 20, 165, 25);
        loginPanel.add(usertext);

        //A label for the password
        password = new JLabel("Password");
        password.setBounds(10, 50, 80, 25);;
        loginPanel.add(password);
        //text area for the password
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        loginPanel.add(passwordText);

        //Add login button to the panel
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        registerBtn = new JButton("Register");
        registerBtn.setBounds(100, 80, 100, 25);
        

        //Add an action listener to the buttons
        button.addActionListener(this);
        registerBtn.addActionListener(this);
        loginPanel.add(button);
        loginPanel.add(registerBtn);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        loginPanel.add(success);

        frame.add(loginPanel);
        //Initialises the database connection
        //initializeDatabase();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            login.frame.setVisible(true);
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String user, password;

        if(e.getSource() == button){
            user = usertext.getText();
            password = passwordText.getText();

            if(user.equals("Leon") & password.equals("1234")){
                success.setText("Login Successful");
            }
        }

        else if (e.getSource() == registerBtn){
            user = usertext.getText();
            password = passwordText.getText();
            
            frame.dispose();
            SwingUtilities.invokeLater(() -> {
                UserRegForm registration = new UserRegForm();
                registration.regFrame.setVisible(true);
            });

        }

    }

}