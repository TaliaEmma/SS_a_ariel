package GUI;


import api.EdgeData;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Iterator;

public class DrawEdges extends JComponent
{
    private Iterator<EdgeData> iterator;
    private DrawGraph graph;
    private int HEIGHT;
    private int WIDTH;

    public DrawEdges(DrawGraph graph)
    {
        iterator = graph.getAlgo().getGraph().edgeIter();
        this.graph = graph;
        WIDTH = graph.getWidth();
        HEIGHT = graph.getHeight();
    }

    private void updateSizes()
    {
        WIDTH = graph.getWidth();
        HEIGHT = graph.getHeight();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        updateSizes();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.BLACK);

        iterator = graph.getAlgo().getGraph().edgeIter();
        while (iterator.hasNext())
        {
            EdgeData edge = iterator.next();
            double x1 = graph.getAlgo().getGraph().getNode(edge.getSrc()).getLocation().x() - DrawNodes.Xmax;
            double x2 = graph.getAlgo().getGraph().getNode(edge.getDest()).getLocation().x() - DrawNodes.Xmin;
            double y1 = graph.getAlgo().getGraph().getNode(edge.getSrc()).getLocation().y() - DrawNodes.Ymin;
            double y2 = graph.getAlgo().getGraph().getNode(edge.getDest()).getLocation().y() - DrawNodes.Ymin;
            x1 = (int) ((x1 / (DrawNodes.Xmax - DrawNodes.Xmin)) * WIDTH * 0.8) + (int) (0.08 * WIDTH) + 7;
            x2 = (int) ((x2 / (DrawNodes.Xmax - DrawNodes.Xmin)) * WIDTH * 0.8) + (int) (0.08 * WIDTH) + 7;
            y1 = (int) ((y1 / (DrawNodes.Ymax - DrawNodes.Ymin)) * HEIGHT * 0.8) + 15;
            y2 = (int) ((y2 / (DrawNodes.Ymax - DrawNodes.Ymin)) * HEIGHT * 0.8) + 15;
            g2d.draw(new Line2D.Double(x1, y1, x2, y2));
        }
    }
}
