package geometries;
import primitives.*;

import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry {
    protected pointD3 a;
    protected vector cross;

    public Plane(pointD3 a, vector cross,Color color) {
        super(color);
        this.a = new pointD3(a);
        this.cross = new vector(cross).normalize();
    }

    public Plane(pointD3 a, pointD3 b, pointD3 c, Color color) {
        super(color);
        this.a = new pointD3(a);
        this.cross = new vector(c.substract(a)).crossProduct(new vector(b.substract(a)));
    }

    public pointD3 getA() {
        return new pointD3(a);
    }

    public vector getCross() {
        return new vector(cross);
    }

    public Coordinate getZofPlane(){
        pointD3  N = getNormal(null).getPoint();
        pointD3 P = getA();
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
                ", cross=" + cross +
                '}';
    }

    @Override
    public vector getNormal(pointD3 a) {
        return new vector(cross);
    }//For what we need that?

    @Override
    public List<pointD3> findIntersections(ray R) {
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
        pointD3 Cut = R.getStart().add(NewVec);
        List<pointD3> out = new ArrayList<>();
        out.add(Cut);
        return out;
    }
}