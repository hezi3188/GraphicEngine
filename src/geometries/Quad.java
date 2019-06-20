package geometries;

import primitives.*;

import java.util.List;

public class Quad  extends Geometry  {
    Point3D TopRight;
    Point3D TopLeft;
    Point3D BottumRight;
    Point3D BottumLeft;
    Triangle plane;
    Triangle plane2;
    public Quad(Point3D topRight, Point3D topLeft, Point3D bottumRight, Point3D bottumLeft, Color c) {
        super(c);
        TopRight = topRight;
        TopLeft = topLeft;
        BottumRight = bottumRight;
        BottumLeft = bottumLeft;
        Calc();
    }
    public Quad(Point3D topRight, Point3D topLeft, Point3D bottumRight, Point3D bottumLeft, Color c,Material M) {
        super(c,M);
        TopRight = topRight;
        TopLeft = topLeft;
        BottumRight = bottumRight;
        BottumLeft = bottumLeft;
        Calc();
    }
    public Quad(Point3D topRight,double width,double height, Color c) {
        super(c);
        TopRight = topRight;
        TopLeft = TopRight.add(new vector(width,0,0));
        BottumRight = TopRight.add(new vector(0,-height,0));
        BottumLeft = TopRight.add(new vector(width,-height,0));
        Calc();
    }
    private void Calc(){
        plane = new Triangle(TopRight,TopLeft,BottumRight, this.getEmmission(),new Material(0,0,0,0,0));
        plane2 = new Triangle(TopLeft, BottumRight,BottumLeft, this.getEmmission(),new Material(0,0,0,0,0));
    }
    @Override
    public vector getNormal(Point3D a) {
        Plane x1 = new Plane(plane.getA(),plane.getB(),plane.getC(),new Color(java.awt.Color.white),new Material(0,0,0,0,0));
        if(x1.IfPointOnP(a))
            return x1.getNormal(null);
        Plane x2 = new Plane(plane2.getB(),plane2.getA(),plane2.getC(),new Color(java.awt.Color.white),new Material(0,0,0,0,0));
        if(x2.IfPointOnP(a)) return x2.getNormal(null);
        return null;
    }

    @Override
    public List<Point3D> findIntersections(ray R) {

        List<Point3D> intersections = plane.findIntersections(R);
        List<Point3D> intersections2 = plane2.findIntersections(R);
        if(intersections == null) return intersections2;
        if(intersections2 == null) return intersections;
        intersections.addAll(intersections2);
        return intersections;

    }
}
