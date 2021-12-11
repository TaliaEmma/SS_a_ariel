package api;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Check_time
{
    public static void main(String[] args)
    {
        //createGraph();

        DirectedWeightedGraphAlgorithms a = new DirectedWeightedGraphAlgorithms_Class();
        long startTime = System.nanoTime();
        a.load("Ex2/data/G1.json");
        long endTime = System.nanoTime();
        long timeElapsed = endTime - startTime;
        System.out.println("100000 nodes load time in millisecond: " + timeElapsed / 1000000);
        System.out.println();

        System.out.println(a.isConnected());
        a.getGraph().removeNode(16);
        a.getGraph().removeNode(1);
        System.out.println(a.isConnected());

//        startTime = System.nanoTime();
//        System.out.println("the graph isConnected: " + a.isConnected());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println("100000 nodes isConnected time in millisecond: " + timeElapsed / 1000000);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        System.out.println("shortestPathDist from 6 to 300: " + a.shortestPathDist(6,300));
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println("100000 nodes shortestPathDist(weight) time in millisecond: " + timeElapsed / 1000000);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        List<NodeData> list = a.shortestPath(6,300);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.print("shortestPath from 6 to 300: ");
//        printList(list);
//        System.out.println("100000 nodes shortestPathDist(list) time in millisecond: " + timeElapsed / 1000000);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        System.out.println("center is: " + a.center().getKey());
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.println("100000 nodes center time in millisecond: " + timeElapsed / 1000000);
//        System.out.println();
//
//        startTime = System.nanoTime();
//        List<NodeData> list2 = a.tsp(list);
//        endTime = System.nanoTime();
//        timeElapsed = endTime - startTime;
//        System.out.print("check tsp with the list: ");
//        printList(list);
//        System.out.print("the returned list is: ");
//        printList(list2);
//        System.out.println("100000 nodes tsp time in millisecond: " + timeElapsed / 1000000);


    }
    public static void printList(List<NodeData> list)
    {
        for (NodeData n:list)
            System.out.print(n.getKey() + ", ");
        System.out.println();
    }

    public static void createGraph()
    {
        JSONArray edgesArray = new JSONArray();
        JSONArray nodesArray = new JSONArray();
        JSONObject edge;
        String location;
        JSONObject node;
        for(int i=0; i<1000000; i++)
        {
            node = new JSONObject();
            location = Math.random()*1000 + "," + Math.random()*1000 + "," + Math.random()*1000;
            node.put("pos", location);
            node.put("id", i);
            edgesArray.add(node);
        }
        for(int i=0; i<500000; i++)
            for(int j=0; j<10; j++)
            {
                edge = new JSONObject();
                edge.put("src", i);
                edge.put("w", Math.random()*1000);
                edge.put("dest",(int)(500000 + Math.random()*500000));
                nodesArray.add(edge);
            }
        for(int i=500000; i<1000000; i++)
            for(int j=0; j<20; j++)
            {
                edge = new JSONObject();
                edge.put("src", i);
                edge.put("w", Math.random()*1000);
                edge.put("dest",(int)(Math.random()*500000));
                nodesArray.add(edge);
            }
        JSONObject result = new JSONObject();
        result.put("Edges", edgesArray);
        result.put("Nodes", nodesArray);

        try (FileWriter f = new FileWriter("1000000.json"))
        {
            f.write(result.toJSONString());
            f.flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
