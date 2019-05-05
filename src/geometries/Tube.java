package geometries;
import primitives.*;

import java.util.List;

public class Tube extends RadialGeometry implements IGeometry {
    protected ray center;

    public Tube(double radius, ray center) {
        super(radius);
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
    public vector getNormal(pointD3 a) {
        return null;
    }

    @Override
    public List<pointD3> findIntersections(ray R) {
        return null;
    }
}