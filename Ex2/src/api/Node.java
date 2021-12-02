package api;

import java.util.HashMap;

public class Node implements NodeData
{
    private int id;
    private double weight;
    private GeoLocation location;
    private String info;
    private int tag;
    private boolean outChange; //check if the edges going out changed since graph constructed.
    private HashMap<Integer, EdgeData> outEdges;
    private HashMap<Integer, EdgeData> inEdges;  // MAYBE DELETE. CHECK LATER


    public Node(int id, double x, double y, double z)
    {
        outChange = false;
        this.id = id;
        location = new Location(x, y, z);
        weight = 0;
        info = "";
        tag = 0;
        outEdges = new HashMap<>();
        inEdges = new HashMap<>();
    }

    @Override
    public boolean outChange(){ return outChange; }

    @Override
    public void setOutChange(boolean a){ outChange = a; }

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
    public HashMap<Integer, EdgeData> outEdges() {
        return outEdges;
    }

    @Override
    public HashMap<Integer, EdgeData> inEdges() {
        return inEdges;
    }
}
