package api;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class DirectedWeightedGraphAlgorithms_Class implements DirectedWeightedGraphAlgorithms
{
    private DirectedWeightedGraph graph;


    public DirectedWeightedGraphAlgorithms_Class(String fileName)
    {
        load(fileName);
    }


    @Override
    public void init(DirectedWeightedGraph g) {graph = g;}

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
    {// worst case is o(V^2)
        HashMap<String, Double> matrix= new HashMap<>();
        HashMap<Integer, Double> dist = new HashMap<>();
        HashMap<Integer, Boolean> sptSet= new HashMap<>();

        Iterator<NodeData> nodesIter = graph.nodeIter();
        while (nodesIter.hasNext())
        {
            NodeData node = nodesIter.next();
            dist.put(node.getKey(), Double.MAX_VALUE);
            sptSet.put(node.getKey(), false);
        }
        Iterator<EdgeData> edgesIterator = graph.edgeIter();
        while(edgesIterator.hasNext())
        {
            EdgeData edge = edgesIterator.next();
            String str = edge.getSrc() + "," + edge.getDest();
            matrix.put(str, edge.getWeight());
        }

        dist.put(src, 0.0);
        for (int count = 0; count < graph.nodeSize() - 1; count++)
        {
            int u = minDistance(dist, sptSet);
            sptSet.put(u, true);
            Iterator<NodeData> iterator = graph.nodeIter();
            while (iterator.hasNext())
            {
                NodeData node = iterator.next();
                int v = node.getKey();
                String str = u + "," + v;
                if (matrix.containsKey(str) && !sptSet.get(v) && matrix.get(str) != 0 && dist.get(u) != Double.MAX_VALUE && dist.get(u) + matrix.get(str) < dist.get(v))
                    dist.put(v, dist.get(u) + matrix.get(str));
            }
        }
        return dist.get(dest);
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest)
    {// final complexity is: o(V^2)
        HashMap<String, Double> edgeW= new HashMap<>(); //save all edges weight
        HashMap<Integer, Integer> preNode = new HashMap<>(); //the value is the node that will come before the key in the returned list.
        HashMap<Integer, Double> dist = new HashMap<>(); //keys are nodes id and value is the distance to them from the src.
        HashMap<Integer, Boolean> sptSet= new HashMap<>(); //keys are nodes id and value is true if we benn to the node.

        Iterator<NodeData> nodesIter = graph.nodeIter();
        while (nodesIter.hasNext())
        {
            NodeData node = nodesIter.next();
            dist.put(node.getKey(), Double.MAX_VALUE);
            sptSet.put(node.getKey(), false);
        }
        Iterator<EdgeData> edgesIterator = graph.edgeIter();
        while(edgesIterator.hasNext())
        {
            EdgeData edge = edgesIterator.next();
            String str = edge.getSrc() + "," + edge.getDest();
            edgeW.put(str, edge.getWeight());
        }

        dist.put(src, 0.0);
        for (int count = 0; count < graph.nodeSize() - 1; count++)
        {
            int u = minDistance(dist, sptSet);
            sptSet.put(u, true);
            Iterator<NodeData> iterator = graph.nodeIter();
            while (iterator.hasNext())
            {
                NodeData node = iterator.next();
                int v = node.getKey();
                String str = u + "," + v;
                if (edgeW.containsKey(str) && !sptSet.get(v) && edgeW.get(str) != 0 && dist.get(u) != Double.MAX_VALUE && dist.get(u) + edgeW.get(str) < dist.get(v))
                {
                    dist.put(v, dist.get(u) + edgeW.get(str));
                    preNode.put(v, u);
                    if (v == dest)
                        return checkPath(preNode, src, dest);
                }
            }
        }
        return null;
    }

    @Override
    public NodeData center()
    {
        HashMap<String, Double> edgeW = new HashMap<>(); //save all the edges weights
        Iterator<EdgeData> edgesIterator = graph.edgeIter();
        while(edgesIterator.hasNext())
        {
            EdgeData edge = edgesIterator.next();
            String str = edge.getSrc() + "," + edge.getDest();
            edgeW.put(str, edge.getWeight());
        }

        int id=0;
        double lowestWeight= Double.MAX_VALUE;
        Iterator<NodeData> nodesIter = graph.nodeIter();
        while (nodesIter.hasNext())
        { //go over all the nodes and find the lowest value.
            NodeData node = nodesIter.next();
            double newWeight = dijkstra(node.getKey(), edgeW);
            if(newWeight == -1)
                return null;
            if (lowestWeight > newWeight)
            {
                id = node.getKey();
                lowestWeight = newWeight;
            }
        }
        return graph.getNode(id);
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities)
    {
        HashMap<Integer, Boolean> visited = new HashMap<>();
        List<NodeData> path = new LinkedList<>();
        double weight = Double.MAX_VALUE;

        for (NodeData node: cities) //find the best path when the src is different in each loop run.
        {
            for (NodeData n: cities)
                visited.put(n.getKey(), false);

            int src = node.getKey(); //change the src every loop run
            visited.put(src, true); //we start in the src so we will mark it
            List<NodeData> newPath = new LinkedList<>();
            newPath.add(graph.getNode(src));
            NodeData newNode = null;
            while (visited.containsValue(false)) //for every src find the best path from it to the rest.
            {
                newNode = findNextNode(src, visited, cities);
                if (newNode == null) //in case we don't have a path return null.
                    return null;
                List<NodeData> tempList = shortestPath(src, newNode.getKey());
                tempList.remove(graph.getNode(src));
                newPath.addAll(tempList);
                visited.put(newNode.getKey(), true);
                src = newNode.getKey();
            }
            double newWeight = listWeight(newPath);
            if(newWeight < weight)
            {
                weight = newWeight;
                path = newPath;
            }
        }

        return path;
    }

    @Override
    public boolean save(String file)
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
    public boolean load(String file)
    {
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

    int minDistance(HashMap<Integer, Double> dist, HashMap<Integer, Boolean> sptSet)
    {
        //implements Dijkstra's shortest pat algorithm for the graph
        double min = Double.MAX_VALUE;
        int min_index = -1;
        Iterator<NodeData> nodesIter = graph.nodeIter();
        while (nodesIter.hasNext())
        {
            NodeData node = nodesIter.next();
            if (!sptSet.get(node.getKey()) && dist.get(node.getKey()) <= min)
            {
                min = dist.get(node.getKey());
                min_index = node.getKey();
            }
        }
        return min_index;
    }

    private List<NodeData> checkPath(HashMap<Integer, Integer> preNode, int src, int dest)
    {
        LinkedList<NodeData> list = new LinkedList<>();
        while (dest != src)
        {
            list.addFirst(graph.getNode(dest));
            dest = preNode.get(dest);
        }
        list.addFirst(graph.getNode(src));
        return list;
    }

    private static void DFS(List<List<Integer>> adjList, int v, boolean[] visited)
    {
        visited[v] = true;
        for (int u: adjList.get(v))
            if (!visited[u])
                DFS(adjList, u, visited);
    }

    private double dijkstra(int src, HashMap<String, Double> edgesW)
    {
        // the function is for the center algorithm. its build like the previous versions but here we
        // will return the highest result instead of result for a given destination value.
        HashMap<Integer, Double> dist = new HashMap<>();
        HashMap<Integer, Boolean> sptSet= new HashMap<>();

        Iterator<NodeData> nodesIter = graph.nodeIter();
        while (nodesIter.hasNext()) //o(V)
        {
            NodeData node = nodesIter.next();
            dist.put(node.getKey(), Double.MAX_VALUE);
            sptSet.put(node.getKey(), false);
        }

        dist.put(src, 0.0);
        for (int i = 0; i < graph.nodeSize() - 1; i++)
        {
            int u = minDistance(dist, sptSet);
            sptSet.put(u, true);
            Iterator<NodeData> iterator = graph.nodeIter();
            while (iterator.hasNext())
            {
                NodeData node = iterator.next();
                int v = node.getKey();
                String str = u + "," + v;
                if (edgesW.containsKey(str) && !sptSet.get(v) && edgesW.get(str) != 0 && dist.get(u) != Double.MAX_VALUE && dist.get(u) + edgesW.get(str) < dist.get(v))
                {
                    dist.put(v, dist.get(u) + edgesW.get(str));
                }
            }
        }
        double max = -1;
        for (Double weight: dist.values())
        {
            if(weight > max)
                max = weight;
            if(weight == Double.MAX_VALUE)
                return -1;
        }
        return max;
    }

    private NodeData findNextNode(int src, HashMap<Integer, Boolean> visited, List<NodeData> list)
    {
        double weight = Double.MAX_VALUE;
        int id = 0 ;

        for (NodeData node: list) //go over all the nodes and check for the one with the shortest path from src.
        {
            if (visited.get(node.getKey()))
                continue;
            double newWeight = shortestPathDist(src, node.getKey());

            if (newWeight == Double.MAX_VALUE) //in case we don't have a path return null.
                return null;

            if(newWeight < weight)
            {
                weight = newWeight;
                id = node.getKey();
            }
        }
        return graph.getNode(id);
    }

    private double listWeight(List<NodeData> list)
    { //accept list of connected nodes and return the total weight.
        double answer = 0;
        boolean firstTime = true;
        NodeData preNode = null;

        for(NodeData node : list)
        {
            if (firstTime)
            {
                firstTime = false;
                preNode = node;
                continue;
            }

            answer += graph.getEdge(preNode.getKey(), node.getKey()).getWeight();
            preNode = node;
        }
        return answer;
    }
}
