package elements;

import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.vector;

public class DirectionalLight extends Light implements LightSource {

    vector _direction;

    public DirectionalLight(Color _color) {
        super(_color);
    }

    public DirectionalLight(){
        _direction=new vector(0,0,1);
    }

    @Override
    public Color getIntensity(Point3D point) {
        return _color;
    }

}
