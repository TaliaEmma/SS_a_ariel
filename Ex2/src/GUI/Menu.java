package GUI;
import api.NodeData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

public class Menu implements ActionListener
{

    JMenuBar menu;
    JMenu Graph, Algorithm;
    JMenuItem save, load, addNode, removeNode, addEdge, removeEdge, tsp, center, isConnected, shortestPathWeight, shortestPathList;
    private final DrawGraph graph;

    public Menu(DrawGraph g) {
        graph = g;
        menu = new JMenuBar();

        Graph = new JMenu("graph");
        addEdge = new JMenuItem("add edge");
        addNode = new JMenuItem("add node");
        removeEdge = new JMenuItem("remove edge");
        removeNode = new JMenuItem("remove node");
        Graph.add(addEdge);
        Graph.add(addNode);
        Graph.add(removeEdge);
        Graph.add(removeNode);

        Algorithm = new JMenu("algorithm");
        save = new JMenuItem("save");
        load = new JMenuItem("load");
        tsp = new JMenuItem("tsp");
        center = new JMenuItem("center");
        isConnected = new JMenuItem("is connected");
        shortestPathWeight = new JMenuItem("shortest path weight");
        shortestPathList = new JMenuItem("shortest path list");
        Algorithm.add(save);
        Algorithm.add(load);
        Algorithm.add(tsp);
        Algorithm.add(center);
        Algorithm.add(isConnected);
        Algorithm.add(shortestPathList);
        Algorithm.add(shortestPathWeight);

        addEdge.addActionListener(this);
        addNode.addActionListener(this);
        removeEdge.addActionListener(this);
        removeNode.addActionListener(this);
        save.addActionListener(this);
        load.addActionListener(this);
        tsp.addActionListener(this);
        center.addActionListener(this);
        isConnected.addActionListener(this);
        shortestPathWeight.addActionListener(this);
        shortestPathList.addActionListener(this);

        menu.add(Graph);
        menu.add(Algorithm);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        JMenuItem src = (JMenuItem) e.getSource();
        Frame temp = new Frame(graph);

        if (src == save)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save file");
            int userSelection = fileChooser.showSaveDialog(graph);
            if (userSelection == JFileChooser.APPROVE_OPTION)
            {
                File fileToSave = fileChooser.getSelectedFile();
                graph.getAlgo().save(fileToSave.getAbsolutePath());
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }
        }
        if (src == load)
        {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save file");
            int userSelection = fileChooser.showOpenDialog(graph);
            if (userSelection == JFileChooser.APPROVE_OPTION)
            {
                File fileToLoad = fileChooser.getSelectedFile();
                graph.getAlgo().load(fileToLoad.getAbsolutePath());
                System.out.println("Load file: " + fileToLoad.getAbsolutePath());
                graph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graph.dispatchEvent(new WindowEvent(graph, WindowEvent.WINDOW_CLOSING));
                new DrawGraph(graph.getAlgo());
            }
        }
        if (src == isConnected)
        {
            temp.setTitle("is Connected");
            JLabel label = new JLabel(graph.getAlgo().isConnected() ? "TRUE" : "FALSE");
            label.setBounds(100, 50, 100, 50);
            temp.add(label);
            temp.setVisible(true);
        }
        if (src == addEdge)
        {

            temp.setTitle("add Edge");
            temp.addEdge();
            temp.setVisible(true);
        }
        if (src == removeEdge)
        {
            temp.setTitle("remove Edge");
            temp.removeEdge();
            temp.setVisible(true);
        }
        if (src == addNode)
        {
            temp.setTitle("add Node");
            temp.addNode();
            temp.setVisible(true);
        }
        if (src == removeNode)
        {
            temp.setTitle("remove Node");
            temp.removeNode();
            temp.setVisible(true);

        }
        if (src == shortestPathList)
        {
            temp.setTitle("Shortest Path list");
            temp.shortPathList();
            temp.setVisible(true);
        }
        if (src == shortestPathWeight)
        {
            temp.setTitle("Shortest Path weight");
            temp.shortPathWeight();
            temp.setVisible(true);
        }
        if (src == tsp)
        {
            temp.setTitle("tsp");
            temp.tsp();
            temp.setVisible(true);
        }
        if (src == center)
        {
            temp.setTitle("center");
            NodeData center = graph.getAlgo().center();
            JLabel label;
            if(center!=null)
            {
                label = new JLabel("center is: " + center.getKey());
                label.setBounds(100, 50, 100, 50);
            }
            else
            {
                label = new JLabel("there is no center");
                label.setBounds(100, 50, 100, 50);
            }
            temp.add(label);
            temp.setVisible(true);
        }
    }
}