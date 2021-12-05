import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener {

    private JLabel label;
    private JFrame frame;
    private JPanel panel;

    public GUI(){
        frame = new JFrame();

        JButton button = new JButton("put Button name");
        button.addActionListener(this);

        label = new JLabel("put Label Name");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30)); //לשנות בהתאם לקואורדינטות
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("OUR GUI");
        frame.pack();
        frame.setVisible(true);


    }

    public static void main(String[] args){
        new GUI();

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
