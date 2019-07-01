package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents sphere on the space
 */
public class Sphere extends RadialGeometry implements IGeometry {
    /**
     * The center of sphere
     */
    protected Point3D center;
//******************Constructors***************//

    /**
     * Constructor with radius,point,color and material of the sphere
     * @param radius Radius
     * @param center Center
     * @param c Color
     * @param m Material
     */
    public Sphere(double radius, Point3D center, Color c,Material m) {
        super(radius,c,m);
        this.center = new Point3D(center);
    }

    /**
     * Constructor with deafult color and material
     * @param obj
     * @param center
     */
    public Sphere(RadialGeometry obj, Point3D center) {
        super(obj);
        this.center = new Point3D(center);
    }
//****************Getter**************//
    public Point3D getCenter() {
        return new Point3D(center);
    }
    @Override
    public vector getNormal(Point3D a) {
        return new vector(a.substract(center)).normalize();
    }
    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", _radius=" + _radius +
                '}';
    }


    /**
     * Calculate the intersections of the sphere
     * @param R
     * @return return the list of the all intersections
     */
    @Override
    public List<Point3D> findIntersections(ray R) {
        //The calculate from the moodle//
        vector u = center.substract(R.getStart());
        double Tm = ((vector)R).dotProduct(u);
        double d2 = (u.length()*u.length()) - (Tm*Tm);
        double d = Math.sqrt(d2);
        if(d>this.get_radius()) return null;
        double Th2 = (this.get_radius()*this.get_radius()) - d*d;
        double Th = Math.sqrt(Th2);
        double T1 = Tm+Th;
        double T2 = Tm-Th;
        Point3D P1 = R.getStart().add(((vector)R).multScalar(T1));
        Point3D P2 = R.getStart().add(((vector)R).multScalar(T2));
        List<Point3D> out = new ArrayList<>();
        if(T1>0)out.add(P1);
        if(T2>0)out.add(P2);
        return out;
    }
}