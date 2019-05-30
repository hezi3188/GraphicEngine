package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.vector;

public interface LightSource {
    Color getIntensity(Point3D point);
    vector getL(Point3D x);

}
