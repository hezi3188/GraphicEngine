package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents plane on the space
 */
public class Plane extends Geometry implements FlatGeometry {
    /**
     * The point of the start normal vector
     */
    protected Point3D a;
    /**
     * The normal vector
     */
    protected vector cross; //Normal

    /**
     * Constructor with point, normal and color
     * @param a start vector
     * @param cross normal
     * @param color The color of plane
     */
    public Plane(Point3D a, vector cross, Color color) {
        super(color);
        this.a = new Point3D(a);
        this.cross = new vector(cross).normalize();
    }

    /**
     *Constructor with point, normal , color and material
     * @param a The start point of the vector
     * @param cross The normal vector
     * @param color The color of the plane
     * @param m The material of the plane
     */
    public Plane(Point3D a, vector cross, Color color,Material m) {
        super(color,m);
        this.a = new Point3D(a);
        this.cross = new vector(cross).normalize();
    }

    /**
     * Constructor that defined with 3 point 3d
     * @param a The first point
     * @param b The second point
     * @param c The third point
     * @param color The color of the plane
     * @param m The material of the plane
     */
    public Plane(Point3D a, Point3D b, Point3D c, Color color,Material m) {
        super(color,m);
        this.a = new Point3D(a);
        this.cross = new vector(c.substract(a).normalize()).crossProduct(new vector(b.substract(a).normalize())).normalize();
    }
//******************Getter/Setter*****************//
    public Point3D getA() {
        return new Point3D(a);
    }
    @Override
    public vector getNormal(Point3D a) {
        return new vector(cross);
    }
    public vector getCross() {
        return new vector(cross);
    }
    // ***************** Administration  ******************** //

    /**
     * Calculate the free prameter of plane
     * @return Return the free prameter of plane
     */
    public Coordinate getZofPlane(){
        Point3D N = getNormal(null).getPoint();
        Point3D P = getA();
        Coordinate XM = N.getX().multiply(P.getX());
        Coordinate YM = N.getY().multiply(P.getY());
        Coordinate ZM = N.getZ().multiply(P.getZ());
        Coordinate ZOfMshvatMishor =XM.add(YM.add(ZM));
        return ZOfMshvatMishor.multiply(new Coordinate(-1));
    }

    @Override
    public String toString() {
        return "Plane{" +
                "a=" + a +
                ", Normal=" + cross +
                '}';
    }

    /**
     * Calculate if point p on the plane
     * @param p The point for checking
     * @return Return true if p on the plane
     */
    public Boolean IfPointOnP(Point3D p){
        Coordinate X = getCross().getPoint().getX().multiply(p.getX());
        Coordinate Y = getCross().getPoint().getY().multiply(p.getY());
        Coordinate Z = getCross().getPoint().getZ().multiply(p.getZ());
        getZofPlane();
        Coordinate SumOFNumbers = X.add(Y.add(Z.add(getZofPlane())));
         return SumOFNumbers.get() < 0.001 && SumOFNumbers.get() > -0.001 ;
    }

    /**
     * Find the instersections of the plane with the ray R
     * @param R Ray r on the space
     * @return Return list of all the instersections
     */
    @Override
    public List<Point3D> findIntersections(ray R) {
        //formola of plane in point for numbers
        Coordinate X = getCross().getPoint().getX().multiply(R.getStart().getX());
        Coordinate Y = getCross().getPoint().getY().multiply(R.getStart().getY());
        Coordinate Z = getCross().getPoint().getZ().multiply(R.getStart().getZ());
        getZofPlane();
        Coordinate SumOFNumbers = X.add(Y.add(Z.add(getZofPlane())));
        //formola of plane in point for alfa
        Coordinate X1 = getCross().getPoint().getX().multiply(R.getPoint().getX());
        Coordinate Y1 = getCross().getPoint().getY().multiply(R.getPoint().getY());
        Coordinate Z1 = getCross().getPoint().getZ().multiply(R.getPoint().getZ());
        Coordinate SumOFAlfa = X1.add(Y1.add(Z1)).multiply(new Coordinate(-1));
        //get alfa
        double Alfa = SumOFNumbers.get()/SumOFAlfa.get();
        if(Alfa<0) return null;
        vector NewVec = new vector(R.getPoint()).multScalar(Alfa);
        Point3D Cut = R.getStart().add(NewVec);
        List<Point3D> out = new ArrayList<>();
        out.add(Cut);
        return out;
    }
}