package api;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgorithms_Class implements DirectedWeightedGraphAlgorithms
{
    private DirectedWeightedGraph_Class graph;
    private DirectedWeightedGraph originalGraph;
    private double[][] matrix;
    private HashMap<Integer, double[]> bestPath; //saves the best path weight for every node. the key is every node id.


    @Override
    public void init(DirectedWeightedGraph g)
    {
        originalGraph = g;
        graph = new DirectedWeightedGraph_Class(g); //copy of given interface
        matrix = new double[graph.nodeSize()][graph.nodeSize()];
        bestPath = new HashMap<Integer, double[]>();

        Iterator<EdgeData> edgesIterator = graph.edgeIter();
        while(edgesIterator.hasNext())
        {
            EdgeData edge = edgesIterator.next();
            matrix[edge.getSrc()][edge.getDest()] = edge.getWeight();
        }
    }


    @Override
    public DirectedWeightedGraph getGraph()
    {
        return graph;
    }

    @Override
    public DirectedWeightedGraph copy()
    {
        return new DirectedWeightedGraph_Class(graph);
    }


    @Override
    public boolean isConnected() //worst case: o(V*(V+E))
    {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i < graph.nodeSize(); i++)
            adjList.add(new ArrayList<>());

        // add edges to the directed graph
        Iterator<EdgeData> iter = graph.edgeIter();
        while(iter.hasNext())
        {
            EdgeData e = iter.next();
            adjList.get(e.getSrc()).add(e.getDest());
        }

        for(int i = 0; i < graph.nodeSize(); i++) // do for every node
        {
            boolean[] visited = new boolean[graph.nodeSize()];
            DFS(adjList, i, visited);
            for(boolean b: visited)
                if (!b)
                    return false;
        }
        return true;
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
    public List<NodeData> shortestPath(int src, int dest)
    {//worst case is o(V^2) like the previous code.
        LinkedList<NodeData> path = new LinkedList<NodeData>();
        path.add(graph.getNode(src));

        int V = graph.nodeSize();
        int lastKey = src;
        double[] dist = new double[V];
        Boolean[] sptSet = new Boolean[V];
        for (int i = 0; i < V; i++) //o(V)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V - 1; count++)
        {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            path.add(graph.getNode(u));
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && matrix[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + matrix[u][v] < dist[v])
                {
                    dist[v] = dist[u] + matrix[u][v];
                    if (lastKey != u)
                        path.add(graph.getNode(u));
                    if(u == dest)
                        return path;
                    lastKey = u;
                }
        }
        return null;
    }

    @Override
    public NodeData center()
    {
        if (!isConnected())
            return null;

        int id, i=0 , j=0;;
        double maxDist = 0 , minDist = 0;
        HashMap<Integer , Double> comp = new HashMap<>();
        Iterator<NodeData> iter1 = graph.nodeIter();
        while (iter1.hasNext())
        {
            maxDist = 0;
            NodeData node = iter1.next();
            id = node.getKey();
            Iterator<NodeData> iter2 = graph.nodeIter();
            while (iter2.hasNext())
            {
                NodeData nodeDest = iter2.next();
                if(maxDist < shortestPathDist(node.getKey() , nodeDest.getKey()))
                    maxDist = shortestPathDist(node.getKey() , nodeDest.getKey());
            }
            comp.put(id , maxDist);
        }
        minDist = comp.get(0);
        for(int h=0; h<comp.size(); h++)
        {
            if(comp.get(i) <= minDist)
            {
                minDist = comp.get(i);
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
    public boolean save(String file) //need to fix
    {
        JSONArray edgesArray = new JSONArray();
        JSONArray nodesArray = new JSONArray();
        Iterator<EdgeData> iter1 = graph.edgeIter();
        while(iter1.hasNext())
        {
            EdgeData e = iter1.next();
            JSONObject edge = new JSONObject();
            edge.put("src", e.getSrc());
            edge.put("w", e.getWeight());
            edge.put("dest", e.getDest());
            edgesArray.add(edge);
        }
        Iterator<NodeData> iter2 = graph.nodeIter();
        while(iter2.hasNext())
        {
            JSONObject node = new JSONObject();
            NodeData n = iter2.next();
            String location = n.getLocation().x() + "," + n.getLocation().y() + "," + n.getLocation().z();
            node.put("pos", location);
            node.put("id", n.getKey());
            nodesArray.add(node);
        }
        JSONObject result = new JSONObject();
        result.put("Edges", edgesArray);
        result.put("Nodes", nodesArray);

        try (FileWriter f = new FileWriter(file))
        {
            f.write(result.toJSONString());
            f.flush();
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean load(String file) {

        try
        {
            DirectedWeightedGraph_Class g = new DirectedWeightedGraph_Class(file);
            init(g);
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    int minDistance(double[] dist, Boolean[] sptSet) //implements Dijkstra's shortest pat algorithm for the graph
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

    private double[] dijkstra(double[][] matrix, int src) //implements Dijkstra's algorithm with adjacency matrix.
    {
        int V = graph.nodeSize();
        double[] dist = new double[V];
        Boolean[] sptSet = new Boolean[V];
        for (int i = 0; i < V; i++) //o(V)
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

    private static void DFS(List<List<Integer>> adjList, int v, boolean[] visited)
    {
        visited[v] = true;
        for (int u: adjList.get(v))
            if (!visited[u])
                DFS(adjList, u, visited);
    }
}
