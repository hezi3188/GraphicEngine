package elements;


import primitives.Color;
import primitives.Point3D;
import primitives.vector;

/**
 * SpotLight light on scene
 */
public class SpotLight extends PointLight {
    vector _direction;

    /**
     * constractor of Spot light
     * @param _color color of light
     * @param _position position of light
     * @param _kc the base of color weakening
     * @param _kl the color weakening after linar distance
     * @param _kq the color weakening after exponential distance
     * @param _direction the vector of direction of light
     */
    public SpotLight(Color _color, Point3D _position, double _kc, double _kl, double _kq, vector _direction) {
        super(_color, _position, _kc, _kl, _kq);
        this._direction = _direction.normalize();
    }
    /**
     * clac power of light
     * @param point point to clac color
     * @return the color
     */
    public Color getIntensity(Point3D point){
        vector v = new vector(super.getL(point).normalize());
        double scal = _direction.dotProduct(v);
        Color out = super.getIntensity(point).scale(Math.max(0,scal));
        return out;
    }

    public vector get_direction() {
        return new vector(_direction);
    }

    public void set_direction(vector _direction) {
        this._direction = new vector(_direction);
    }

    @Override
    public String toString() {
        return "SpotLight{" +
                "_direction=" + _direction +
                ", _position=" + _position +
                ", _kc=" + _kc +
                ", _kl=" + _kl +
                ", _kq=" + _kq +
                ", _color=" + _color +
                '}';
    }
}
