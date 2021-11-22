package api;

public class GeoLocation_Class implements GeoLocation{
    private double x,y,z;
    public GeoLocation_Class(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }
    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(GeoLocation g) {
        return 0;
    }
}
