package geometries;
import primitives.*;

/**
 * Represents radial geometry
 */
public abstract class RadialGeometry extends Geometry{
    double _radius;
//**************Constructors*******************//
    /**
     * Constructor with radius, color and material
     * @param radius Radius
     * @param c Color
     * @param m MAterial
     */
    public RadialGeometry(double radius,Color c,Material m) {
        super(c,m);
        this._radius = radius;
    }

    /**
     * Copy constructor
     * @param obj Other RadialGeometry
     */
    public RadialGeometry(RadialGeometry obj) {
        super(obj);
        this._radius = obj._radius;
    }
//***********Getter***********//
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