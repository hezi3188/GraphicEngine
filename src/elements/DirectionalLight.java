package elements;

import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.vector;

/**
 * class of DirectionalLight, like a sun for exmple
 */
public class DirectionalLight extends Light implements LightSource {

    vector _direction;

    /**
     * constractor
     * @param _color color of light
     */
    public DirectionalLight(Color _color) {
        super(_color);
    }

    /**
     * defult constractor
     */
    public DirectionalLight(){
        _direction=new vector(0,0,1);
    }

    /**
     * clac the color on point
     * @param point point to color
     * @return the power light
     */
    @Override
    public Color getIntensity(Point3D point) {
        return new Color(_color);
    }

}
