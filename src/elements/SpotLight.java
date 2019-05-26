package elements;

import primitives.Color;
import primitives.pointD3;
import primitives.vector;

public class SpotLight extends PointLight {
    vector _direction;

    public SpotLight(Color _color, pointD3 _position, double _kc, double _kl, double _kq, vector _direction) {
        super(_color, _position, _kc, _kl, _kq);
        this._direction = _direction.normalize();
    }
    public Color getIntensity(pointD3 point,vector vec){
        double d=getD(point);
        return super.getIntensity(point).scale(Math.max(0,_direction.dotProduct(vec.normalize())));
    }
}
