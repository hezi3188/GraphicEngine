package geometries;
import primitives.*;

import java.util.List;

public class Triangle extends Geometry {
    protected pointD3 a;
    protected pointD3 b;
    protected pointD3 c;

    public Triangle(pointD3 a, pointD3 b, pointD3 c) {
         this.a = new pointD3(a);
        this.b = new pointD3(b);
        this.c = new pointD3(c);
    }

    public Triangle(Triangle T) {
        this.a = T.getA();
        this.b = T.getB();
        this.c = T.getC();
    }

    public pointD3 getA() {
        return new pointD3(a);
    }

    public pointD3 getB() {
        return new pointD3(b);
    }

    public pointD3 getC() {
        return new pointD3(c);
    }
    public List<pointD3> findIntersections(ray R) {
        Plane plane = new Plane(a,b,c);
        List<pointD3> cut = plane.findIntersections(R);
        if(cut == null) return null;
        pointD3 P = cut.get(0);
        vector v1 = a.substract(R.getStart());
        vector v2 = b.substract(R.getStart());
        vector v3 = c.substract(R.getStart());
        vector N1 = v1.crossProduct(v2).normalize();
        vector N2 = v2.crossProduct(v3).normalize();
        vector N3 = v3.crossProduct(v1).normalize();
        double num1 = P.substract(R.getStart()).dotProduct(N1);
        double num2 = P.substract(R.getStart()).dotProduct(N2);
        double num3 = P.substract(R.getStart()).dotProduct(N3);
        if(num1>0 && num2>0 && num3 >0) return cut;
        if(num1<0 && num2<0 && num3 <0) return cut;
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
    public vector getNormal(pointD3 a) {
        return new vector(c.substract(a)).crossProduct(new vector(b.substract(a)));
    }
}