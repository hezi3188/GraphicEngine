package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry implements FlatGeometry {
    protected Point3D a;
    protected vector cross; //Normal
    protected vector niceVector;

    public Plane(Point3D a, vector cross, Color color) {
        super(color);
        this.a = new Point3D(a);
        this.cross = new vector(cross).normalize();
    }

    public Plane(Point3D a, Point3D b, Point3D c, Color color) {
        super(color);
        this.a = new Point3D(a);
        this.cross = new vector(c.substract(a).normalize()).crossProduct(new vector(b.substract(a).normalize())).normalize();
    }

    public Point3D getA() {
        return new Point3D(a);
    }

    public vector getCross() {
        return new vector(cross);
    }

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

    @Override
    public vector getNormal(Point3D a) {
        return new vector(cross);
    }//For what we need that?
    public Boolean IfPointOnP(Point3D p){
        Coordinate X = getCross().getPoint().getX().multiply(p.getX());
        Coordinate Y = getCross().getPoint().getY().multiply(p.getY());
        Coordinate Z = getCross().getPoint().getZ().multiply(p.getZ());
        getZofPlane();
        Coordinate SumOFNumbers = X.add(Y.add(Z.add(getZofPlane())));
         return SumOFNumbers.get() == 0;
    }
    @Override
    public List<Point3D> findIntersections(ray R) {
        Coordinate X = getCross().getPoint().getX().multiply(R.getStart().getX());
        Coordinate Y = getCross().getPoint().getY().multiply(R.getStart().getY());
        Coordinate Z = getCross().getPoint().getZ().multiply(R.getStart().getZ());
        getZofPlane();
        Coordinate SumOFNumbers = X.add(Y.add(Z.add(getZofPlane())));

        Coordinate X1 = getCross().getPoint().getX().multiply(R.getPoint().getX());
        Coordinate Y1 = getCross().getPoint().getY().multiply(R.getPoint().getY());
        Coordinate Z1 = getCross().getPoint().getZ().multiply(R.getPoint().getZ());
        Coordinate SumOFAlfa = X1.add(Y1.add(Z1)).multiply(new Coordinate(-1));
        //if (SumOFAlfa.isZero()) return null;
        double Alfa = SumOFNumbers.get()/SumOFAlfa.get();
        if(Alfa<0) return null;
        vector NewVec = new vector(R.getPoint()).multScalar(Alfa);
        Point3D Cut = R.getStart().add(NewVec);
        List<Point3D> out = new ArrayList<>();
        out.add(Cut);
        return out;
    }
}