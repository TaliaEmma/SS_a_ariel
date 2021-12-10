import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame implements ActionListener {

    JButton Open;
    JButton Exit;
    JButton Save;
    JButton Algo;

    Frame() {
        JFrame frame = new JFrame();
        this.setSize(420, 420);
        this.setTitle("Gui Panel");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(Color.white);
        this.setLayout(new FlowLayout());

        Open = new JButton("Open");
        Exit = new JButton("Exit");
        Save = new JButton("Save");
        Algo = new JButton("Algo");

        // Open.setBounds(100,100,250,100);
        // Exit.setBounds(200,100,250,100);

        Open.addActionListener(this);
        Exit.addActionListener(this);
        Save.addActionListener(this);
        Algo.addActionListener((this));

        this.add(Open);
        this.add(Save);
        this.add(Algo);
        this.add(Exit);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==Open){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            //DirectedWeightedGraph_Class D = new DirectedWeightedGraph_Class(fileChooser);
        }
        if(e.getSource()==Save){



        }

        if(e.getSource()==Algo){



        }


        if(e.getSource()==Exit){
            System.exit(0);
        }


    }
}