package api;

import java.util.HashMap;

public class Node implements NodeData
{
    private int id;
    private double weight;
    private GeoLocation location;
    private String info;
    private int tag;
    public HashMap<Integer, Edge> outEdges; // all edges going out of the node
    public HashMap<Integer, Edge> inEdges;  // all edges going in to the node


    public Node(int id, double x, double y, double z)
    {
        this.id = id;
        location = new Location(x, y, z);
        weight = 0;
        info = "";
        tag = 0;
        outEdges = new HashMap<>();
        inEdges = new HashMap<>();
    }

    @Override
    public int getKey() {
        return id;
    }

    @Override
    public GeoLocation getLocation() {
        return location;
    }

    @Override
    public void setLocation(GeoLocation p) {
        location = new Location(p.x(), p.y(), p.z());
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double weight) {
        this.weight=weight;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public void setInfo(String s) {
        this.info =info;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }

    @Override
    public HashMap<Integer, Edge> outEdges() {
        return outEdges;
    }

    @Override
    public HashMap<Integer, Edge> inEdges() {
        return inEdges;
    }
}
