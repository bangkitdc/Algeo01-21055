package mtrx.Point;

public class Point {
    private double x;
    private double y;
    
    public Point() {
        this.setAbsis(0);
        this.setAbsis(0);
    }

    public Point(double a, double b) {
        this.setAbsis(a);
        this.setAbsis(b);
    }

    public Point(Point p) {
        copyElmt(p);
    }

    public void copyElmt(Point p) {
        this.setAbsis(p.getAbsis());
        this.setOrdinat(p.getOrdinat());
    }
    
    public void setAbsis(double value) {
        this.x = value;
    }

    public void setOrdinat(double value) {
        this.y = value;
    }

    public double getAbsis() {
        return this.x;
    }

    public double getOrdinat() {
        return this.y;
    }
}
