package GuessingGameSwing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

public class GuessingGameSwing extends JFrame {
    private int rand;
    private int trial = 1;

    public GuessingGameSwing() {
        Random random = new Random();
        rand = random.nextInt(100);

        JLabel label = new JLabel("Hey, welcome to the guessing game. You have 3 Trials:");
        JLabel trialLabel = new JLabel("Trial 1");
        JTextField inputField = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        JLabel resultLabel = new JLabel("");

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(inputField.getText());
                if (guess == rand) {
                    showResult("Game Won", "You got it Correct: " + rand);
                } else if (trial < 3) {
                    trial++;
                    trialLabel.setText("Trial " + trial);
                    inputField.setText("");
                    resultLabel.setText("Wrong, Try again");
                } else {
                    showResult("Game Lost", "The number was: " + rand);
                }
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(8, 20));
        panel.add(label);
        panel.add(trialLabel);
        panel.add(inputField);
        panel.add(guessButton);
        panel.add(resultLabel);
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));

        add(panel);
        setTitle("Guessing Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void showResult(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public static void main(String[] args) {
        new GuessingGameSwing();
    }
}
