package api;

public class Node implements NodeData {
    private int id;
    private double w;
    private int tag;
    private GeoLocation_Class Loc;
    public Node(int id){
        this.id=id;
    }

    @Override
    public int getKey() {
        return id;
    }

    @Override
    public GeoLocation getLocation() {
        return Loc;
    }

    @Override
    public void setLocation(GeoLocation p) {
        double x=p.x();
        double y=p.y();
        double z=p.z();
        Loc = new GeoLocation_Class(x,y,z);
    }

    @Override
    public double getWeight() {
        return w;
    }

    @Override
    public void setWeight(double w) {
        this.w=w;
    }

    @Override
    public String getInfo() {
        String id = "id: " + this.id;
        String w = "Weight: " + this.w;
        String Loc = "Location: (" + this.Loc.x() + ", " + this.Loc.y() + ", " + this.Loc.z() + ")";
        return id + " " + w + " " + Loc;
    }

    @Override
    public void setInfo(String s) {
        //לקבל סטרינג בפורמט מסויים ולחלץ ממנו את הקואורדינטות ולעדכן את כל הנתונים שלנו
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        this.tag=t;
    }
}
