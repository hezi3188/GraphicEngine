package geometries;
import primitives.*;

public class Cylinder extends Tube{
    public Coordinate len;

    public Cylinder(double radius, ray center, Coordinate len,Color c) {
        super(radius, center , c);
        this.len = new Coordinate(len);
    }

    public Coordinate getLen() {
        return new Coordinate(len);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "len=" + len +
                ", center=" + center +
                ", _radius=" + _radius +
                '}';
    }

}