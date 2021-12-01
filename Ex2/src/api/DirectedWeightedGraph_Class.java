package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class DirectedWeightedGraph_Class implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<String, EdgeData> edges; // for example, if str=2 and dest=4 then the key is the string: "2,4"
    Iterator<NodeData> nodesIterator;
    Iterator<EdgeData> edgeIterator;
    private int MC;


    public DirectedWeightedGraph_Class(String fileName) throws IOException, ParseException, JSONException
    {
        nodes = new HashMap<Integer, NodeData>();
        edges = new HashMap<String, EdgeData>();
        nodesIterator = nodes.values().iterator();
        edgeIterator = edges.values().iterator();
        MC = 0;

        JSONObject j = (JSONObject) new JSONParser().parse(new FileReader(fileName));
        JSONArray jEdges = j.getJSONArray("Edges");
        JSONArray jNodes = j.getJSONArray("Nodes");
        for(int i = 0; i < jNodes.length(); i++)
        {
            String pos=jNodes.getJSONObject(i).getString("pos");
            int id=jNodes.getJSONObject(i).getInt("id");

            String[] loc_of_node = pos.split(",");
            double x = Double.parseDouble(loc_of_node[0]);
            double y = Double.parseDouble(loc_of_node[1]);
            double z = Double.parseDouble(loc_of_node[2]);

            Node node = new Node(id, x, y, z);
            nodes.put(node.getKey(), node);
        }
        for(int i = 0; i < jEdges.length(); i++)
        {
            int src = jEdges.getJSONObject(i).getInt("src");
            double w = jEdges.getJSONObject(i).getDouble("w");
            int dest = jEdges.getJSONObject(i).getInt("dest");

            Edge edge = new Edge(src, dest, w);
            String str = src + "," + dest;
            nodes.get(src).outEdges().put(dest, edge);
            nodes.get(dest).inEdges().put(src, edge);
            edges.put(str, edge);
        }
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
    public Iterator<NodeData> nodeIter()
    {
        if(MC > 0)
            throw new RuntimeException();
        else
            return nodesIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter()
    {
        if(MC > 0)
            throw new RuntimeException();
        else
            return edgeIterator;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id)
    {
        if(!nodes.get(node_id).outChange())
            throw new RuntimeException();
        else
            return nodes.get(node_id).outEdges().values().iterator();
    }

    @Override
    public NodeData removeNode(int key)
    {
        MC++;
        for(Map.Entry<String, EdgeData> set : edges.entrySet())
            if(set.getValue().getDest() == key || set.getValue().getSrc() == key)
            {
                nodes.get(edges.get(set.getKey()).getSrc()).setOutChange(true);
                nodes.get(edges.get(set.getKey()).getDest()).setOutChange(true);
                edges.remove(set.getKey());
            }
        return nodes.remove(key);
    }

    @Override
    public EdgeData removeEdge(int src, int dest)
    {
        MC++;
        String str = src + "," + dest;
        nodes.get(src).setOutChange(true);
        nodes.get(dest).setOutChange(true);
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
