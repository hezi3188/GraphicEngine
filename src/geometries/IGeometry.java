package geometries;

import primitives.*;

/**
 * Represent interface for getNormal to each geometry
 */
public interface IGeometry extends Intersectable {
    vector getNormal(Point3D a);
}
