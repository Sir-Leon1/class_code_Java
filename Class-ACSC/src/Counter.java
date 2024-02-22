import java.awt.*;
import java.awt.event.*;

public class Counter extends Frame implements ActionListener{
    private Label lblCounter;
    private TextField tfCount;
    private Button btnCount;
    private int count = 0;
    

    public Counter (){

        setLayout(new FlowLayout());
        lblCounter = new Label("Counter");
        add(lblCounter);
        tfCount = new TextField("0", 10);
        tfCount.setEditable(false);
        btnCount = new Button("Count");
        btnCount.addActionListener(this);
        setTitle("SimpleCounter");
        setSize(250, 100);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(tfCount);
        add(btnCount);

        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    public static void main(String[]args){
        Counter app = new Counter();
    }

    @Override
    public void actionPerformed(ActionEvent evt){
        ++count;
        tfCount.setText(count+ " ");
    }
}