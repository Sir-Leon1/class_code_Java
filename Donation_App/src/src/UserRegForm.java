package src;

import javax.swing.*;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//import java.awt.event.ActionListener;


public class UserRegForm extends JFrame /*implements ActionListener*/ {
    
    private static JLabel label;
    private static JTextField txtArea;
    public static JButton button;
    private JPanel regPanel = new JPanel();

    public UserRegForm() {
        setTitle("New User Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        regPanel.setLayout(new GridLayout(7, 2, 10, 10));
        //regPanel.setBorder(BorderFactory)

        labelAndCombobox("User Type", new String[]{"Admin", "Donor", "Recipient"});
        labelAndCombobox("Location", new String[]{"Location A", "Location B", "Locatin c"});
        labelAndTextfield("Username");
        labelAndTextfield("password");
        labelAndTextfield("Phone number");
        labelAndTextfield("Email:");

        add(regPanel);



    }

    @Override
    public void actionPerformed(ActonEvent e) {
        if (e.getSource() == register){
            String userType = (String) 
        }
    }

    private void labelAndCombobox(String labelTxt, String[] items) {
        label = new JLabel(labelTxt);
        JComboBox<String> comboBox = new JComboBox<>(items);
        regPanel.add(label);
        regPanel.add(comboBox);
    }
    
    private void labelAndTextfield(String labelTxt){
        label = new JLabel(labelTxt);
        txtArea = new JTextField();
        regPanel.add(label);
        regPanel.add(txtArea);
    }

}