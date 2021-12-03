package api;

import java.util.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DirectedWeightedGraphAlgorithms_Class implements DirectedWeightedGraphAlgorithms{
    private DirectedWeightedGraph G;

    @java.lang.Override
    public void init(DirectedWeightedGraph g) {
        this.G = g;
    }

    @java.lang.Override
    public DirectedWeightedGraph getGraph() {
        return G;
    }

    @java.lang.Override
    public DirectedWeightedGraph copy() {
        return null;
    }

    @java.lang.Override
    public boolean isConnected() {

        return false;
    }

    @java.lang.Override
    public double shortestPathDist(int src, int dest) { //dijkstra
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

    @java.lang.Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @java.lang.Override
    public NodeData center() {
        return null;
    }

    @java.lang.Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @java.lang.Override
    public boolean save(String file) {
        return false;
    }

    @java.lang.Override
    public boolean load(String file) {
        try {
            this.G = new DirectedWeightedGraph_Class(file);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
