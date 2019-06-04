package elements;

import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.vector;

import java.util.Objects;


public abstract class Light {
    Color _color;

    public Light() {
    }


    public Color getIntensity(Point3D point, Geometry g){
        return null;
    }

    public Light(Color _color) {
        this._color = _color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Light light = (Light) o;
        return Objects.equals(_color, light._color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_color);
    }

    public vector getL(Point3D point){
        return null;
    }
}
