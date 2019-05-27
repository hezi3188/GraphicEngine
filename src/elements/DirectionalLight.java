package elements;

import primitives.Color;
import primitives.vector;

public class DirectionalLight extends Light {

    vector _direction;

    public DirectionalLight(Color _color) {
        super(_color);
    }
}
