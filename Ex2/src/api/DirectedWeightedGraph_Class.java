package api;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class DirectedWeightedGraph_Class implements DirectedWeightedGraph
{
    private HashMap<Integer, NodeData> nodes;
    private HashMap<String, EdgeData> edges; // for example, if str=2 and dest=4 then the key is the string: "2,4"
    private int MC;


    public  DirectedWeightedGraph_Class()
    {
        nodes = new HashMap<Integer, NodeData>();
        edges = new HashMap<String, EdgeData>();
        MC = 0;
    }



    @Override
    public NodeData getNode(int key)
    {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest)
    {
        String str = src + "," + dest;
        return edges.get(str);
    }

    @Override
    public void addNode(NodeData n)
    {
        MC++;
        nodes.put(n.getKey() , n);
    }

    @Override
    public void connect(int src, int dest, double w)
    {
        MC++;
        Edge e = new Edge(src, dest, w);
        String str = src + "," + dest;
        edges.put(str, e);
    }

    @Override
    public Iterator<NodeData> nodeIter() { //NEED TO COMPLETE
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() { //NEED TO COMPLETE
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) { //NEED TO COMPLETE
        return null;
    }

    @Override
    public NodeData removeNode(int key)
    {
        MC++;
        for(Map.Entry<String, EdgeData> set : edges.entrySet())
            if(set.getValue().getDest() == key || set.getValue().getSrc() == key)
                edges.remove(set.getKey());
        return nodes.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest)
    {
        MC++;
        String str = src + "," + dest;
        return edges.remove(str);
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public int getMC() {
        return MC;
    }
}
