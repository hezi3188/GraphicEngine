package elements;

import geometries.Geometry;
import primitives.Color;
import primitives.pointD3;
import primitives.vector;

public class PointLight extends Light implements LightSource {
    public pointD3 get_position() {
        return new pointD3(_position);
    }

    pointD3 _position;
    double _kc,_kl,_kq;

    public PointLight(Color _color, pointD3 _position, double _kc, double _kl, double _kq) {
        super(_color);
        this._position = _position;
        this._kc = _kc;
        this._kl = _kl;
        this._kq = _kq;
    }

    /*public Color getIntensity(pointD3 point, Geometry g){
        double d = getD(point);

        double precents = 80; //strong of the light
        double scale = precents/(_kc+_kl*d+_kq*d*d);

        double allColor = _color.getColor().getRed()+_color.getColor().getBlue()+_color.getColor().getGreen();
        double Red = _color.getColor().getRed()/allColor;
        double blue = _color.getColor().getBlue()/allColor;
        double green = _color.getColor().getGreen()/allColor;

        double allColorO = g.getEmmission().getColor().getRed()+g.getEmmission().getColor().getBlue()+g.getEmmission().getColor().getGreen();
        double RedO = g.getEmmission().getColor().getRed()/allColorO;
        double blueO = g.getEmmission().getColor().getBlue()/allColorO;
        double greenO = g.getEmmission().getColor().getGreen()/allColorO;

        Color newColor = g.getEmmission();
        newColor = newColor.setColor(g.getEmmission().getColor().getRed()*(RedO)*scale + _color.getColor().getRed()*(Red)*0.2*scale,
                g.getEmmission().getColor().getGreen()*(greenO)*scale + _color.getColor().getGreen()*(green)*0.2*scale,
                g.getEmmission().getColor().getBlue()*(blueO)*scale + _color.getColor().getBlue()*(blue)*0.2*scale);

        return newColor;
        //return this._color.scale(1/(_kc+_kl*d+_kq*d*d));
    }*/

    protected double getD(pointD3 point) {
        return point.distance(_position);
    }

    @Override
    public Color getIntensity(pointD3 point) {
        double d = getD(point);
        double scale = (_kc+_kl*d+_kq*d*d);
        return this._color.scale(1/scale);
    }
    public vector getL(pointD3 x){
        return new vector(x.substract(this._position));
    }
}
