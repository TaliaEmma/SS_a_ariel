package api;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;


public class DirectedWeightedGraph_Class implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes; //the key is the node id.
    private HashMap<String, EdgeData> edges; // for example, if src=2 and dest=4 then the key is the string: "2,4"
    private int MC; //count changes in the graph.
    private HashMap<Integer, HashMap<Integer, EdgeData>> outEdges;
    private HashMap<Integer, HashMap<Integer, EdgeData>> inEdges;


    public DirectedWeightedGraph_Class(DirectedWeightedGraph graph)
    {
        nodes = new HashMap<Integer, NodeData>();
        edges = new HashMap<String, EdgeData>();
        outEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        inEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        MC = graph.getMC();

        Iterator<NodeData> iterator1 = graph.nodeIter();
        while(iterator1.hasNext())
        {
            NodeData node = iterator1.next();
            addNode(node);
        }

        Iterator<EdgeData> iterator2 = graph.edgeIter();
        while(iterator2.hasNext())
        {
            EdgeData edge = iterator2.next();
            connect(edge.getSrc(), edge.getDest(), edge.getWeight());
        }
    }


    public DirectedWeightedGraph_Class(String fileName) throws IOException, JSONException
    {
        nodes = new HashMap<Integer, NodeData>();
        edges = new HashMap<String, EdgeData>();
        outEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        inEdges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        MC = 0;

        JSONObject j = new JSONObject(new String(Files.readAllBytes(Paths.get(fileName))));
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
            addNode(node);
        }
        for(int i = 0; i < jEdges.length(); i++)
        {
            int src = jEdges.getJSONObject(i).getInt("src");
            double w = jEdges.getJSONObject(i).getDouble("w");
            int dest = jEdges.getJSONObject(i).getInt("dest");
            connect(src, dest, w);
        }
    }

    @Override
    public NodeData getNode(int key) { return nodes.get(key); }

    @Override
    public EdgeData getEdge(int src, int dest) {
        String str = src + "," + dest;
        return edges.get(str);
    }

    @Override
    public void addNode(NodeData n)
    {
        MC++;
        nodes.put(n.getKey() , new Node(n.getKey(), n.getLocation().x(), n.getLocation().y(), n.getLocation().z()));
    }

    @Override
    public void connect(int src, int dest, double w)
    {
        MC++;
        inEdges.put(dest, new HashMap<Integer, EdgeData>());
        outEdges.put(src, new HashMap<Integer, EdgeData>());
        Edge e = new Edge(src, dest, w);
        String str = src + "," + dest;
        edges.put(str, e);
        inEdges.get(dest).put(src, e);
        outEdges.get(src).put(dest, e);
    }

    @Override
    public Iterator<NodeData> nodeIter()
    {
        return nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter()
    {
        return edges.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id)
    {
        return outEdges.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key)
    {
        MC++;
        Iterator<EdgeData> edgesIter = edgeIter();
        while (edgesIter.hasNext())
        {
            EdgeData e = edgesIter.next();
            if (e.getSrc() == key || e.getDest() == key)
                edgesIter.remove();
        }
        return nodes.remove(key); //o(1)
    }

    @Override
    public EdgeData removeEdge(int src, int dest)
    {
        MC++;
        outEdges.get(src).remove(dest);
        inEdges.get(dest).remove(src);
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
