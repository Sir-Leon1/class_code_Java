import javax.swing.JFrame;
import javax.swing.JLabel; 
import javax.swing.*;
import java.awt.event.*;

public class Login implements ActionListener {

    private static JLabel label;
    private static JTextField usertext;
    private static JLabel password;
    private static JPasswordField passwordText;
    private static JButton button;
    private static JLabel success;
    private static JButton registerBtn;

    public static void main(String[] args){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        
        /**
         * Create the frame of the 
         * interface and add the panel to it
         */
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        //label for the Username
        label = new JLabel("User");
        label.setBounds(10, 20, 80, 25);
        panel.add(label);
        //Text area for the username text
        usertext = new JTextField();
        usertext.setBounds(100, 20, 165, 25);
        panel.add(usertext);

        //A label for the password
        password = new JLabel("Password");
        password.setBounds(10, 50, 80, 25);;
        panel.add(password);
        //text area for the password
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel.add(passwordText);

        //Add login button to the panel
        button = new JButton("Login");
        button.setBounds(10, 80, 80, 25);
        registerBtn = new JButton("Register");
        registerBtn.setBounds(100, 80, 100, 25);
        

        //Add an action listener to the buttons
        button.addActionListener(new Login());
        registerBtn.addActionListener(new Login());
        panel.add(button);
        panel.add(registerBtn);

        success = new JLabel("");
        success.setBounds(10, 110, 300, 25);
        panel.add(success);

        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        /**
         * Get and save the user name and
         * password
         */
        String user = usertext.getText();
        String password = passwordText.getText();

        //Check if the password and username are same as the registered ones
        if(user.equals("Leon") & password.equals("12345")){
            success.setText("Login successful");
        }
    }
}