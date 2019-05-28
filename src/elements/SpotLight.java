package elements;

import geometries.Geometry;
import primitives.Color;
import primitives.pointD3;
import primitives.vector;

public class SpotLight extends PointLight {
    vector _direction;

    public SpotLight(Color _color, pointD3 _position, double _kc, double _kl, double _kq, vector _direction) {
        super(_color, _position, _kc, _kl, _kq);
        this._direction = _direction.normalize();
    }
    public Color getIntensity(pointD3 point){
        vector v = new vector(super.getL(point).normalize());
        double scal = _direction.dotProduct(v);
        Color out = super.getIntensity(point).scale(Math.max(0,scal));
        return out;
    }
    /*public Color getIntensity(pointD3 point, Geometry g,vector vec){
        double d=getD(point);
        double scal = _direction.dotProduct(vec.normalize());
        return super.getIntensity(point, g).scale(
                (Math.max(1,1/-scal))); //why minus???
    }*/
}
