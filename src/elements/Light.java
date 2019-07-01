package elements;

import geometries.Geometry;
import primitives.Color;
import primitives.Point3D;
import primitives.vector;

import java.util.Objects;

/**
 * A abstract class to defind Light
 */
public abstract class Light {
    Color _color;

    /**
     * defult constractor
     */
    public Light() {
    }

    /**
     * clac power of light
     * @param point point to clac color
     * @param g geometry the owner of color in point.
     * @return the color
     */
    public Color getIntensity(Point3D point, Geometry g){
        return null;
    }

    /**
     * constractor
     * @param _color set color of the light
     */
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

    /**
     * calc the vector from the light to point in scene. help for clac shadow and power light.
     * @param point the point the check color
     * @return vector
     */
    public vector getL(Point3D point){
        return null;
    }
}
