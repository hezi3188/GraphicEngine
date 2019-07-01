package elements;
/**
 * PointLight light on scene
 */

import primitives.Color;
import primitives.Point3D;
import primitives.vector;

public class PointLight extends Light implements LightSource {
    public Point3D get_position() {
        return new Point3D(_position);
    }

    Point3D _position;
    double _kc,_kl,_kq;

    /**
     * constractor of Point light
     * @param _color color of light
     * @param _position position of light
     * @param _kc the base of color weakening
     * @param _kl the color weakening after linar distance
     * @param _kq the color weakening after exponential distance
     */
    public PointLight(Color _color, Point3D _position, double _kc, double _kl, double _kq) {
        super(_color);
        this._position = _position;
        this._kc = _kc;
        this._kl = _kl;
        this._kq = _kq;
    }

    /**
     * clac the distance betwteen the point to light
     * @param point the point in scene
     * @return the distance
     */
    protected double getD(Point3D point) {
        return point.distance(_position);
    }
    /**
     * clac power of light
     * @param point point to clac color
     * @return the color
     */
    @Override
    public Color getIntensity(Point3D point) {
        double d = getD(point);
        double scale = (_kc+_kl*d+_kq*d*d);
        return this._color.scale(1/scale);
    }
    /**
     * imploy calc the vector from the light to point in scene. help for clac shadow and power light.
     * @param x the point the check color
     * @return vector
     */
    public vector getL(Point3D x){
        return new vector(x.substract(this._position));
    }

    public void set_position(Point3D _position) {
        this._position = new Point3D(_position);
    }

    public double get_kc() {
        return _kc;
    }

    public void set_kc(double _kc) {
        this._kc = _kc;
    }

    public double get_kl() {
        return _kl;
    }

    public void set_kl(double _kl) {
        this._kl = _kl;
    }

    public double get_kq() {
        return _kq;
    }

    public void set_kq(double _kq) {
        this._kq = _kq;
    }

    @Override
    public String toString() {
        return "PointLight{" +
                "_position=" + _position +
                ", _kc=" + _kc +
                ", _kl=" + _kl +
                ", _kq=" + _kq +
                ", _color=" + _color +
                '}';
    }
}
