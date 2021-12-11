package GUI;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.util.Iterator;

public class DrawNodes extends JComponent
{
    private DrawGraph graph;
    private int HEIGHT;
    private int WIDTH;

    public static double Xmax = Double.MIN_VALUE;
    public static double Ymax = Double.MIN_VALUE;
    public static double Xmin = Double.MAX_VALUE;
    public static double Ymin = Double.MAX_VALUE;

    public DrawNodes(DrawGraph graph)
    {
        this.graph = graph;
        WIDTH = graph.getWidth();
        HEIGHT = graph.getHeight();
        getCoordBounds();
        setBounds(0, 0, WIDTH, HEIGHT);
    }

    private void getCoordBounds()
    {
        Iterator<NodeData> iterator = graph.getAlgo().getGraph().nodeIter();
        while (iterator.hasNext())
        {
            NodeData node = iterator.next();
            if (Xmax < node.getLocation().x())
                Xmax = node.getLocation().x();
            if (Ymax < node.getLocation().y())
                Ymax = node.getLocation().y();
            if (Xmin > node.getLocation().x())
                Xmin = node.getLocation().x();
            if (Ymin > node.getLocation().y())
                Ymin = node.getLocation().y();
        }
    }

    private void updateSizes()
    {
        WIDTH = graph.getWidth();
        HEIGHT = graph.getHeight();
        setBounds(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        updateSizes();
        Graphics2D g2d = (Graphics2D) g;
        Iterator<NodeData> iterator = graph.getAlgo().getGraph().nodeIter();
        while (iterator.hasNext())
        {
            NodeData node = iterator.next();
            double x = node.getLocation().x() - Xmin;
            double y = node.getLocation().y() - Ymin;
            int x_coord = (int) ((x / (Xmax - Xmin)) * WIDTH * 0.8) + (int) (0.08 * WIDTH);
            int y_coord = (int) ((y / (Ymax - Ymin)) * HEIGHT * 0.8) + 10;
            g2d.setPaint(Color.RED);
            g2d.fillOval(x_coord, y_coord, 20, 20);
            g2d.setPaint(Color.BLUE);
            g2d.drawString("" + node.getKey(), x_coord, y_coord);
        }
    }

}
