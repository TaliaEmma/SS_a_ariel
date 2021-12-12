package GUI;


import api.DirectedWeightedGraphAlgorithms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DrawGraph extends JFrame
{

    private final DirectedWeightedGraphAlgorithms algorithm;
    Draw d;


    public DrawGraph(DirectedWeightedGraphAlgorithms algorithm)
    {
        this.setTitle("GUI");
        this.setLayout(new BorderLayout());
        this.setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2) , (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.algorithm = algorithm;
        this.d = new Draw(this);
        Menu menu = new Menu(this);

        this.add(d, BorderLayout.CENTER);
        setVisible(true);
        setJMenuBar(menu.menu);
        setVisible(true);
        setResizable(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                d.repaint();
            }});
    }

    public DirectedWeightedGraphAlgorithms getAlgo()
    {
        return algorithm;
    }
}