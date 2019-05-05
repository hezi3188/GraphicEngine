package geometries;
import primitives.*;

public abstract class RadialGeometry{
    double _radius;

    public RadialGeometry(double radius) {
        this._radius = radius;
    }
    public RadialGeometry(RadialGeometry obj) {
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