package elements;

import geometries.Geometry;
import primitives.Color;
import primitives.pointD3;
import primitives.vector;


public abstract class Light {
    Color _color;

    public Color getIntensity(pointD3 point, Geometry g){
        return null;
    }

    public Light(Color _color) {
        this._color = _color;
    }

    public vector getL(pointD3 point){
        return null;
    }
}
