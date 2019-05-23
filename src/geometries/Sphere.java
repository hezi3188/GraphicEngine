package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry implements IGeometry {
    protected pointD3 center;

    public Sphere(double radius, pointD3 center,Color c) {
        super(radius,c);
        this.center = new pointD3(center);
    }

    public Sphere(RadialGeometry obj, pointD3 center) {
        super(obj);
        this.center = new pointD3(center);
    }

    public pointD3 getCenter() {
        return new pointD3(center);
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public vector getNormal(pointD3 a) {
        return new vector(a.substract(center)).normalize();
    }

    @Override
    public List<pointD3> findIntersections(ray R) {
        vector u = center.substract(R.getStart());
        double Tm = ((vector)R).dotProduct(u);
        double d2 = (u.length()*u.length()) - (Tm*Tm);
        double d = Math.sqrt(d2);
        if(d>this.get_radius()) return null;
        double Th2 = (this.get_radius()*this.get_radius()) - d2;
        double Th = Math.sqrt(Th2);
        double T1 = Tm+Th;
        double T2 = Tm-Th;
        pointD3 P1 = R.getStart().add(((vector)R).multScalar(T1));
        pointD3 P2 = R.getStart().add(((vector)R).multScalar(T2));
        List<pointD3> out = new ArrayList<>();
        if(T1>0)out.add(P1);
        if(T2>0)out.add(P2);
        return out;
    }
}