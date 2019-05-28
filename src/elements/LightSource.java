package elements;

import primitives.Color;
import primitives.pointD3;
import primitives.vector;

public interface LightSource {
    Color getIntensity(pointD3 point);
    vector getL(pointD3 x);
}
