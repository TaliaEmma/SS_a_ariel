package GUI;


import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class Draw extends JPanel
{
    private final DrawGraph graph;
    private double maxX = Double.MIN_VALUE;
    private double minX = Double.MAX_VALUE;
    private double maxY = Double.MIN_VALUE;
    private double minY = Double.MAX_VALUE;
    private double absX = 0;
    private double absY = 0;

    public Draw(DrawGraph graph)
    {
        this.graph = graph;
        updateMinMax();
    }

    public void updateMinMax()
    {
        DirectedWeightedGraphAlgorithms algorithm = graph.getAlgo();
        Iterator<NodeData> NodeIter = algorithm.getGraph().nodeIter();
        while (NodeIter.hasNext())
        {
            NodeData node = NodeIter.next();
            if (node.getLocation().x() < minX)
                minX = node.getLocation().x();
            else if (node.getLocation().x() > maxX)
                maxX = node.getLocation().x();
            if (node.getLocation().y() < minY)
                minY = node.getLocation().y();
            else if (node.getLocation().y() > maxY)
                maxY = node.getLocation().y();
        }
        absX = Math.abs(maxX - minX);
        absY = Math.abs(maxY - minY);
    }

    private void paintEdges(Graphics g)
    {
        DirectedWeightedGraphAlgorithms algorithm = graph.getAlgo();
        double x = getWidth() / absX * 0.8;
        double y = getHeight() / absY * 0.8;
        Iterator<EdgeData> edgeIter = algorithm.getGraph().edgeIter();
        g.setColor(Color.black);
        while (edgeIter.hasNext())
        {
            EdgeData edge = edgeIter.next();

            double srcX = (algorithm.getGraph().getNode(edge.getSrc()).getLocation().x() - minX) * x + 70;
            double  srcY = (algorithm.getGraph().getNode(edge.getSrc()).getLocation().y() - minY) * y + 70;
            double destX = (algorithm.getGraph().getNode(edge.getDest()).getLocation().x() - minX) * x + 70;
            double destY = (algorithm.getGraph().getNode(edge.getDest()).getLocation().y() - minY) * y + 70;
            g.drawLine((int) srcX, (int) srcY, (int) destX, (int) destY);
        }
    }

    private void paintNodes(Graphics g)
    {
        DirectedWeightedGraphAlgorithms algorithm = graph.getAlgo();
        Iterator<NodeData> nodeIter = algorithm.getGraph().nodeIter();
        g.setColor(Color.red);
        while (nodeIter.hasNext())
        {
            NodeData node = nodeIter.next();
            double x = (node.getLocation().x() - minX) * (getWidth() / absX * 0.8) + 70;
            double y = (node.getLocation().y() - minY) * (getHeight() / absY * 0.8) + 70;
            g.fillOval((int) x - 5, (int) y - 5, 11, 11);
            g.setColor(Color.red);
            g.drawString(node.getKey() + "", (int) x - 5, (int) y - 5);
        }
    }

    @Override
    public void paintComponent (Graphics g)
    {
        super.paintComponent(g);
        this.setVisible(false);
        paintNodes(g);
        paintEdges(g);
        this.setVisible(true);

    }
}
