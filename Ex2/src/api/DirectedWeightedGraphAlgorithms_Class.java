package api;

import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgorithms_Class implements DirectedWeightedGraphAlgorithms
{
    private DirectedWeightedGraph_Class graph;
    private DirectedWeightedGraph originalGraph;
    private HashMap<String, LinkedList<NodeData>> completePath;
    double[][] matrix;
    HashMap<Integer, double[]> bestPath;


    @Override
    public void init(DirectedWeightedGraph g)
    {
        originalGraph = g;
        graph = new DirectedWeightedGraph_Class(g); //copy of given interface
        matrix = new double[graph.nodeSize()][graph.nodeSize()];
        completePath = new HashMap<String, LinkedList<NodeData>>();
        bestPath = new HashMap<Integer, double[]>();

        Iterator<EdgeData> edgesIterator = graph.edgeIter();
        while(edgesIterator.hasNext())
        {
            EdgeData edge = edgesIterator.next();
            matrix[edge.getSrc()][edge.getDest()] = edge.getWeight();
        }
    }


    int minDistance(double[] dist, Boolean[] sptSet)
    {
        double min = Integer.MAX_VALUE;
        int min_index = -1;
        for (int v = 0; v < graph.nodeSize(); v++)
            if (!sptSet[v] && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
        return min_index;
    }

    private double[] dijkstra(double[][] matrix, int src)
    {
        int V = graph.nodeSize();
        double[] dist = new double[V];
        Boolean[] sptSet = new Boolean[V];
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++)
        {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v])
                    dist[v] = dist[u] + matrix[u][v];
        }
        return dist;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return graph;
    }

    @Override
    public DirectedWeightedGraph copy() {
        return new DirectedWeightedGraph_Class(graph);
    }


    @Override
    public boolean isConnected() //o(V*(V+E))
    {
        Iterator<NodeData> iter1 = graph.nodeIter();
        while(iter1.hasNext())  //o(V)
        {
            NodeData node1 = iter1.next();
            DFS(node1.getKey()); // o(V+E)
            Iterator<NodeData> iter2 = graph.nodeIter();
            while (iter2.hasNext()) //o(V)
            {
                NodeData node2 = iter2.next();
                if (node2.getTag() == 0)
                    return false;
            }
        }
        return true;
    }

    public void DFS(int v)
    {
        graph.getNode(v).setTag(1);
        Iterator<EdgeData> iter = graph.edgeIter(v);
        while(iter.hasNext())
        {
            EdgeData edge = iter.next();
            if (graph.getNode(edge.getDest()).getTag() == 0)
                DFS(edge.getDest());
        }
    }

    @Override
    public double shortestPathDist(int src, int dest)
    {//worst case is o(V^2)
        double answer;
        if(!bestPath.containsKey(src))
            bestPath.put(src, dijkstra(matrix, src));
        answer = bestPath.get(src)[dest];
        if(answer == Integer.MAX_VALUE)
            return -1;
        return answer;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) { //NEED TO COMPLETE
        return null;
    }

    @Override
    public NodeData center()
    {
        if (!isConnected())
            return null;

        int id, i=0 , j=0;;
        double maxDist = 0 , minDist = 0;
        HashMap<Integer , Double> comperator = new HashMap<>();
        Iterator<NodeData> iter1 = graph.nodeIter();
        while (iter1.hasNext())
        {
            NodeData node = iter1.next();
            id = node.getKey();
            maxDist = 0;
            Iterator<NodeData> iter2 = graph.nodeIter();
            while (iter2.hasNext())
            {
                NodeData nodeDest = iter2.next();
                if(maxDist < shortestPathDist(node.getKey() , nodeDest.getKey()))
                    maxDist = shortestPathDist(node.getKey() , nodeDest.getKey());
            }
            comperator.put(id , maxDist);
        }
        minDist = comperator.get(0);
        for(int h=0; h<comperator.size(); h++)
        {
            if(comperator.get(i) <= minDist)
            {
                minDist = comperator.get(i);
                j=i;
            }
            i++;
        }
        return graph.getNode(j);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities)
    {
        return null;
    }

    @Override
    public boolean save(String file) { //NEED TO COMPLETE
        return false;
    }

    @Override
    public boolean load(String file) {

        try
        {
            DirectedWeightedGraph_Class g = new DirectedWeightedGraph_Class(file);
            init(g);
        }
        catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
        return true;

    }
}
