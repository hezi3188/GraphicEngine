package geometries;
import primitives.*;

/**
 * Represents Cylinder on the space
 */
public class Cylinder extends Tube{
    public Coordinate len;

    /**
     * Constructor with all the pramaters
     * @param radius
     * @param center
     * @param len The length of Cylinder
     * @param c The color of the Cylinder
     * @param m The material of Cylinder
     */
    public Cylinder(double radius, ray center, Coordinate len,Color c,Material m) {
        super(radius, center , c,m);
        this.len = new Coordinate(len);
    }

    /**
     *
     * @return Return the length of Cylinder
     */
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