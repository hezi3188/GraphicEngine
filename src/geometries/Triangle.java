package geometries;
import primitives.*;

import java.util.List;

public class Triangle extends Geometry implements FlatGeometry {
    protected Point3D a;
    protected Point3D b;
    protected Point3D c;

    public Triangle(Point3D a, Point3D b, Point3D c, Color color,Material m) {
        super(color,m);
         this.a = new Point3D(a);
        this.b = new Point3D(b);
        this.c = new Point3D(c);
    }

    public Triangle(Triangle T) {
        super(T);
        this.a = T.getA();
        this.b = T.getB();
        this.c = T.getC();
    }

    public Point3D getA() {
        return new Point3D(a);
    }

    public Point3D getB() {
        return new Point3D(b);
    }

    public Point3D getC() {
        return new Point3D(c);
    }
    public List<Point3D> findIntersections(ray R) {
        Plane plane = new Plane(a,b,c,this.getEmmission(),null);
        List<Point3D> cut = plane.findIntersections(R);
        if(cut == null) return null;
        Point3D P = cut.get(0);
        vector v1 = a.substract(R.getStart());
        vector v2 = b.substract(R.getStart());
        vector v3 = c.substract(R.getStart());
        vector N1 = v1.crossProduct(v2).normalize();
        vector N2 = v2.crossProduct(v3).normalize();
        vector N3 = v3.crossProduct(v1).normalize();
        double num1 = P.substract(R.getStart()).dotProduct(N1);
        double num2 = P.substract(R.getStart()).dotProduct(N2);
        double num3 = P.substract(R.getStart()).dotProduct(N3);
        if(num1>0 && num2>0 && num3 >0)
            return cut;
        if(num1<0 && num2<0 && num3 <0)
            return cut;
        return null;
    }
    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }

    @Override
    public vector getNormal(Point3D a) {
        return new vector(c.substract(this.a)).crossProduct(new vector(b.substract(this.a))).normalize();
    }
}