package GUI;


import api.DirectedWeightedGraphAlgorithms;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DrawGraph extends JFrame
{

    private Menu menu;
    private DirectedWeightedGraphAlgorithms algorithm;
    private DrawNodes nodes;
    private DrawEdges edges;
    public static final int HEIGHT = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / 2);
    public static final int WIDTH = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 2);

    public DrawGraph(DirectedWeightedGraphAlgorithms algorithm)
    {
        this.setTitle("GUI");
        this.setLayout(new BorderLayout());
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.algorithm = algorithm;

        this.nodes = new DrawNodes(this);
        this.edges = new DrawEdges(this);
        menu = new Menu(this);

        this.add(nodes, BorderLayout.CENTER);
        setVisible(true);
        this.add(edges, BorderLayout.CENTER);
        setVisible(true);
        setJMenuBar(menu.menu);
        setVisible(true);
        setResizable(true);

        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                nodes.repaint();
                edges.repaint();
            }});
    }


    public DirectedWeightedGraphAlgorithms getAlgo() {return algorithm;}
}