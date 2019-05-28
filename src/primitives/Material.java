package primitives;

public class Material {
    double _Kd; //distance arg
    double _Ks; //distance arg
    int _nShininess; //mavhikut

    public Material(double _Kd, double _Ks, int _nShininess) {
        this._Kd = _Kd;
        this._Ks = _Ks;
        this._nShininess = _nShininess;
    }
    public Material(Material M) {
        this._Kd = M._Kd;
        this._Ks = M._Ks;
        this._nShininess = M._nShininess;
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
