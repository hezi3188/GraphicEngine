package elements;
/**
 * The power of color that the object return even when the light trun off.
 */

import primitives.Color;

public class AmbientLight {
    protected Color RGB;
    protected double Ka;

    /**
     * contractor
     * @param RGB Color of the AmbientLight
     * @param ka between 0-1 . power of AmbientLight
     */
    public AmbientLight(Color RGB, double ka) {
        this.RGB = new Color(RGB) ;
        Ka = ka;
    }

    /**
     *  help func to calc the Color of AmbientLight.
     * @return the Color of AmbientLight after the power calc
     */
    public Color GetIntensity(){
        return new Color(this.RGB.scale(Ka));
    }

    /**
     * defult constractor
     */
    public AmbientLight(){
        this.RGB = new Color(0,0,0) ;
        Ka = 0.5;
    }

    /**
     * copy constractor
     * @param fillLight the old AmbientLight
     */
    public AmbientLight(AmbientLight fillLight) {
        RGB = new Color(fillLight.RGB);
        Ka = fillLight.Ka;
    }
}
