package elements;

import primitives.Color;
import primitives.pointD3;

public class PointLight extends Light {
    pointD3 _position;
    double _kc,_kl,_kq;

    public PointLight(Color _color, pointD3 _position, double _kc, double _kl, double _kq) {
        super(_color);
        this._position = _position;
        this._kc = _kc;
        this._kl = _kl;
        this._kq = _kq;
    }
    @Override
    public Color getIntensity(pointD3 point){
        double d = getD(point);
        return this._color.scale(1/(_kc+_kl*d+_kq*d*d));
    }

    protected double getD(pointD3 point) {
        return point.distance(_position);
    }
}
