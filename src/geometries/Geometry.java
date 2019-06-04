package geometries;

import primitives.Color;
import primitives.Material;

public abstract class Geometry implements IGeometry{
    Color EmmissionLight;

    public void set_material(Material _material) {
        this._material = new Material(_material);
    }

    Material _material;

    public Geometry(Color emmissionLight, Material _material) {
        EmmissionLight = new Color(emmissionLight);
        this._material = new Material(_material);
    }

    public Material get_material() {
        return new Material( _material);
    }

    Geometry(Color c){
        EmmissionLight = new Color(c);
        _material = new Material(1.5,6,200,0,0);
    }
    Geometry(Geometry g){
        EmmissionLight = g.getEmmission();
    }
    public primitives.Color getEmmission(){
        return new Color(this.EmmissionLight);
    }
}
