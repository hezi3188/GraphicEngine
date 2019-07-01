package geometries;

import primitives.Color;
import primitives.Material;

/**
 * Represents all the types of geometry
 */
public abstract class Geometry implements IGeometry{
    /**
     * The natural color of the geomatry
     */
    Color EmmissionLight;
    /**
     * The material of every geomatry
     */
    Material _material;

    /**
     * Constructor with emmissionLight and
     * material of the geometry
     * @param emmissionLight
     * @param _material
     */
    public Geometry(Color emmissionLight, Material _material) {
        EmmissionLight = new Color(emmissionLight);
        this._material = new Material(_material);
    }

    /**
     * Constructor with deafult naterial
     * and color c
     * @param c
     */
    Geometry(Color c){
        EmmissionLight = new Color(c);
        _material = new Material(1.5,6,200,0,0.9);
    }

    /**
     * Copy constructor
     * @param g Other geometry
     */
    Geometry(Geometry g){
        EmmissionLight = g.getEmmission();
    }
    //**************Getter/Setter**************//
    public Material get_material() {
        return new Material( _material);
    }
    public primitives.Color getEmmission(){
        return new Color(this.EmmissionLight);
    }
    public void set_material(Material _material) {
        this._material = new Material(_material);
    }
}
