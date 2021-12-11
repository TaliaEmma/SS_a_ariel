package GUI;

import api.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.List;

public class Frame extends JFrame implements ActionListener
{

    JButton shortPathWeight, shortPathList, tsp, addNode, removeNode, addEdge, removeEdge;
    JTextField field;
    JLabel label;
    private DrawGraph graph;




    public Frame(DrawGraph graph)
    {
        this.graph = graph;
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(300, 200);
        setLocationRelativeTo(graph);
        setLayout(null);
        setResizable(true);

    }

    public void addNode()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        addNode = new JButton("add node");
        addNode.setBounds(100, 90, 110, 30);
        label = new JLabel("Enter id and then location (x,y)");
        label.setBounds(10, 25, 300, 30);
        add(addNode);
        add(field);
        add(label);
        addNode.addActionListener(this);
    }

    public void addEdge()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        addEdge = new JButton("add edge");
        addEdge.setBounds(100, 90, 110, 30);
        label = new JLabel("Enter id of src then dest and then weight");
        label.setBounds(10, 25, 300, 30);
        add(addEdge);
        add(field);
        add(label);
        addEdge.addActionListener(this);
    }

    public void removeNode()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        removeNode = new JButton("remove node");
        removeNode.setBounds(100, 90, 110, 30);
        label = new JLabel("Enter id");
        label.setBounds(10, 25, 300, 30);
        add(removeNode);
        add(field);
        add(label);
        removeNode.addActionListener(this);
    }


    public void removeEdge()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        removeEdge = new JButton("Add Edge");
        removeEdge.setBounds(100, 90, 110, 30);
        label = new JLabel("Enter id of src then dest:");
        label.setBounds(10, 25, 300, 30);
        add(removeEdge);
        add(field);
        add(label);
        removeEdge.addActionListener(this);
    }

    public void tsp()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        tsp = new JButton("tsp");
        tsp.setBounds(100, 90, 110, 30);
        label = new JLabel("enter nodes id:");
        label.setBounds(10, 25, 300, 30);
        add(tsp);
        add(field);
        add(label);
        tsp.addActionListener(this);
    }

    public void shortPathList()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        shortPathList = new JButton("shortest path list");
        shortPathList.setBounds(100, 90, 110, 30);
        label = new JLabel("enter id of src and then dest:");
        label.setBounds(10, 25, 300, 30);
        add(shortPathList);
        add(field);
        add(label);
        shortPathList.addActionListener(this);
    }

    public void shortPathWeight()
    {
        field = new JTextField();
        field.setBounds(100, 50, 100, 20);
        shortPathWeight = new JButton("shortest path weight");
        shortPathWeight.setBounds(100, 90, 110, 30);
        label = new JLabel("enter id of src and then dest:");
        label.setBounds(10, 25, 300, 30);
        add(shortPathWeight);
        add(field);
        add(label);
        shortPathWeight.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == addNode)
        {
            if(field.getText().contains(",") && field.getText().split(",").length == 3)
            {
                int id = Integer.parseInt(field.getText().split(",")[0]);
                double x = Double.parseDouble(field.getText().split(",")[1]);
                double y = Double.parseDouble(field.getText().split(",")[2]);
                graph.getAlgo().getGraph().addNode(new Node(id, x,y,0));
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                graph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graph.dispatchEvent(new WindowEvent(graph, WindowEvent.WINDOW_CLOSING));
                new DrawGraph(graph.getAlgo());
            }
            else
            {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                label.setText("Invalid Input. Enter the following: id,x,y");
                setVisible(true);
            }
        }

        if(e.getSource() == addEdge)
        {
            if(field.getText().contains(",") && field.getText().split(",").length == 3)
            {
                int src = Integer.parseInt(field.getText().split(",")[0]);
                int dest = Integer.parseInt(field.getText().split(",")[1]);
                double weight = Double.parseDouble(field.getText().split(",")[2]);
                graph.getAlgo().getGraph().connect(src, dest, weight);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                graph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graph.dispatchEvent(new WindowEvent(graph, WindowEvent.WINDOW_CLOSING));
                new DrawGraph(graph.getAlgo());
            }
            else
            {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                label.setText("Invalid Input. Enter the following: src,dest,weight");
                setVisible(true);
            }
        }

        if(e.getSource() == removeEdge)
        {
            if(field.getText().contains(",") && field.getText().split(",").length == 2)
            {
                int src = Integer.parseInt(field.getText().split(",")[0]);
                int dest = Integer.parseInt(field.getText().split(",")[1]);
                graph.getAlgo().getGraph().removeEdge(src, dest);
                setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                graph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                graph.dispatchEvent(new WindowEvent(graph, WindowEvent.WINDOW_CLOSING));
                new DrawGraph(graph.getAlgo());
            }
            else
            {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                label.setText("Invalid Input. Enter the following: src,dest");
                setVisible(true);
            }
        }

        if(e.getSource() == removeNode)
        {
            int id = Integer.parseInt(field.getText());
            graph.getAlgo().getGraph().removeNode(id);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
            graph.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            graph.dispatchEvent(new WindowEvent(graph, WindowEvent.WINDOW_CLOSING));
            new DrawGraph(graph.getAlgo());
        }

        if(e.getSource() == tsp)
        {
            if(field.getText().contains(","))
            {
                String path = "";
                List<NodeData> cities = new LinkedList<>();
                for (int i = 0; i < field.getText().split(",").length; i++)
                {
                    int id = Integer.parseInt(field.getText().split(",")[i]);
                    cities.add(graph.getAlgo().getGraph().getNode(id));
                }
                List<NodeData> list = graph.getAlgo().tsp(cities);
                if(list != null)
                {
                    for (NodeData node : list)
                        path = path + node.getKey() + " , ";
                    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    label.setText("list is: " + path +"");
                    setVisible(true);
                }
                else
                {
                    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    label.setText("there is no path");
                    setVisible(true);
                }
            }
            else
            {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                label.setText("Invalid Input. Enter keys correctly: id1,id2,id3,...");
                setVisible(true);
            }
        }

        if(e.getSource() == shortPathWeight)
        {
            if(field.getText().contains(",") && field.getText().split(",").length == 2)
            {
                int src = Integer.parseInt(field.getText().split(",")[0]);
                int dest = Integer.parseInt(field.getText().split(",")[1]);
                double w = graph.getAlgo().shortestPathDist(src, dest);
                if(w != Double.MAX_VALUE)
                {
                    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    label.setText("weight is: " + w +"");
                    setVisible(true);
                }
                else
                {
                    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    label.setText("there is no path");
                    setVisible(true);
                }
            }
            else
            {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                label.setText("Invalid Input. Enter: src,dest");
                setVisible(true);
            }
        }

        if(e.getSource() == shortPathList)
        {
            if(field.getText().contains(",") && field.getText().split(",").length == 2)
            {
                String path = "";
                int src = Integer.parseInt(field.getText().split(",")[0]);
                int dist = Integer.parseInt(field.getText().split(",")[1]);
                List<NodeData> list = graph.getAlgo().shortestPath(src, dist);
                if(list != null)
                {
                    for (NodeData node : list)
                        path = path + node.getKey() + " , ";
                    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    label.setText("list is: " + path +"");
                    setVisible(true);
                }
                else
                {
                    setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                    label.setText("there is no path");
                    setVisible(true);
                }
            }
            else
            {
                setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
                label.setText("Invalid Input. Enter: src,dest");
                setVisible(true);
            }
        }



    }
}