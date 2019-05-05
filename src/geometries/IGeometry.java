package geometries;

import primitives.*;

public interface IGeometry extends Intersectable {
    vector getNormal(pointD3 a);
}
