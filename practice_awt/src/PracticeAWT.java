import java.awt.Button;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.awt.*;

public class PracticeAWT extends Frame implements ActionListener {
    Button btn;

    public PracticeAWT() {
        btn = new Button("Say hello world");
        btn.addActionListener(this);
        add(btn);
        
        setTitle("Hello world");
        setSize(300, 250);
        setLayout(new FlowLayout());

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });


        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello world");
    }

    public static void main(String[] args) {
        new PracticeAWT();
    }
}
