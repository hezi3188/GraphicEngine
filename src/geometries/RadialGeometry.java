package geometries;
import primitives.*;

public abstract class RadialGeometry extends Geometry{
    double _radius;

    public RadialGeometry(double radius,Color c,Material m) {
        super(c,m);
        this._radius = radius;
    }
    public RadialGeometry(RadialGeometry obj) {
        super(obj);
        this._radius = obj._radius;
    }

    public double get_radius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "RadialGeometry{" +
                "_radius=" + _radius +
                '}';
    }
}