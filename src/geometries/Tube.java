package geometries;
import primitives.*;

import java.util.List;

public class Tube extends RadialGeometry implements IGeometry {
    protected ray center;

    public Tube(double radius, ray center,Color c,Material m) {
        super(radius,c,m);
        this.center = new ray(center);
    }

    public ray getCenter() {
        return new ray(center);
    }

    @Override
    public String toString() {
        return "Tube{" +
                "center=" + center +
                ", _radius=" + _radius +
                '}';
    }

    @Override
    public vector getNormal(Point3D a) {
        return null;
    }

    @Override
    public List<Point3D> findIntersections(ray R) {
        return null;
    }
}