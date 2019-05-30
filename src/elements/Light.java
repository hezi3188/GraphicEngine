package elements;

import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.vector;


public abstract class Light {
    Color _color;

    public Color getIntensity(Point3D point, Geometry g){
        return null;
    }

    public Light(Color _color) {
        this._color = _color;
    }

    public vector getL(Point3D point){
        return null;
    }
}
