package elements;

import primitives.Color;

public class AmbientLight {
    protected Color RGB;
    protected double Ka;

    public AmbientLight(Color RGB, double ka) {
        this.RGB = new Color(RGB) ;
        Ka = ka;
    }
    public Color GetIntensity(){
        return new Color(this.RGB.scale(Ka));
    }
    public AmbientLight(){

    }
    public AmbientLight(AmbientLight fillLight) {
        RGB = new Color(fillLight.RGB);
        Ka = fillLight.Ka;
    }
}
