package geometries;

import primitives.*;

public interface IGeometry extends Intersectable {
    vector getNormal(Point3D a);
}
