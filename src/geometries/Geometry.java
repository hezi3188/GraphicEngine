package geometries;

import primitives.Color;

public abstract class Geometry implements IGeometry{
    Color EmmissionLight;
    Geometry(Color c){
        EmmissionLight = new Color(c);
    }
    Geometry(Geometry g){
        EmmissionLight = g.getEmmission();
    }
    public primitives.Color getEmmission(){
        return new Color(this.EmmissionLight);
    }
}
