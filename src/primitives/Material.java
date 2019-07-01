package primitives;

/**
 * The material of every shape
 */
public class Material {
    double _Kd; //distance arg
    double _Ks; //distance arg
    int _nShininess; //mavhikut
    double _kr;
    double _kt;

    //****************Constructors******************//

    /**
     * Constructor
     * @param _Kd the power of defuse
     * @param _Ks the power of specular
     * @param _nShininess The radius of the specular
     * @param _kr The power of reflaction
     * @param _kt The power of refraction
     */
    public Material(double _Kd, double _Ks, int _nShininess,double _kr,double _kt) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._nShininess = _nShininess;
        this._kr=_kr;
        this._kt=_kt;
    }

    /**
     * Copy constructor
     * @param M Other Material
     */
    public Material(Material M) {
        this._Kd = M._Kd;
        this._Ks = M._Ks;
        this._nShininess = M._nShininess;
        this._kr=M._kr;
        this._kt=M._kt;
    }
//*******************getter/setter****************//
    public double get_kr() {
        return _kr;
    }

    public void set_kr(double _kr) {
        this._kr = _kr;
    }

    public double get_kt() {
        return _kt;
    }

    public void set_kt(double _kt) {
        this._kt = _kt;
    }

    public double get_Ks() {
        return _Ks;
    }

    public void set_Ks(double _Ks) {
        this._Ks = _Ks;
    }

    public int get_nShininess() {
        return _nShininess;
    }

    public void set_nShininess(int _nShininess) {
        this._nShininess = _nShininess;
    }

    public double get_Kd() {
        return _Kd;
    }

    public void set_Kd(double _Kd) {
        this._Kd = _Kd;
    }
}
