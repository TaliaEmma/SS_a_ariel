package api;

import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgorithms_Class implements DirectedWeightedGraphAlgorithms
{
    private DirectedWeightedGraph original;
    private DirectedWeightedGraph copied;

    @Override
    public void init(DirectedWeightedGraph g)
    {
        original = g; //original points to g
        copied = new DirectedWeightedGraph_Class(g); //deep copy of g
    }

    @Override
    public DirectedWeightedGraph getGraph() { return original; }

    @Override
    public DirectedWeightedGraph copy() { return copied; }


    public void calculatePathData(int key)
    {
        /* boolean[] visited;  //מיקום בו היינו כבר בגרף
        int[] dist; //המרחק בין הקודקודים
        int[] pred; //קודקוד האב של הקודקוד
        nodes node = new nodes();
        for (String i : nodes.keySet()) {
            pred[i] = null;
            visited[i] = false;
            dist[i] = 0;
        }
        dist[0] = 0;
        visited[0] = true;
        while(nodes.hasNext){
            int min = Collections.min(nodes.keySet());
            for(nodes.values){
                if (!visited[u])
                    if (dist[u] > dist[v] + G[v][u].w) pred[u] = v;
                dist[u] = min(dist[u], dist[v] + G[v][u].w);
            }
            visited[v] = true
        }
        return 0;
        */
    }

    @Override
    public boolean isConnected() {
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) { return 0; }


    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() { return null; }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {

        try
        {
            this.original = new DirectedWeightedGraph_Class(file);
        }
        catch (IOException | ParseException e)
        {
            e.printStackTrace();
        }
        return true;

    }
}
