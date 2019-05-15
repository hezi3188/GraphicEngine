package scene;

import elements.AmbientLight;
import elements.Camera;
import geometries.Intersectable;
import geometries.Triangle;
import primitives.Color;

import java.util.ArrayList;
import java.util.List;

public class Scene {

    private String name;

    private Color backColor;
    private AmbientLight fillLight;
    private Camera cam;
    private double DisToScreen;
    private List<Intersectable> image;

    public Scene(Scene s) {
        this.name = s.getName();
        this.backColor = s.getBackColor();
        this.fillLight = s.getFillLight();
        this.cam = s.getCam();
        this.DisToScreen = s.getDisToScreen();
        this.image = new ArrayList<Intersectable>();
        this.image = s.getImage();
    }

    public String getName() {
        return name;
    }

    public Color getBackColor() {
        return backColor;
    }

    public AmbientLight getFillLight() {
        if(fillLight == null)
            return new AmbientLight();
        return new AmbientLight(fillLight);
    }

    public Camera getCam() {
        return new Camera(cam);
    }

    public double getDisToScreen() {
        return DisToScreen;
    }

    public List<Intersectable> getImage() {
        return new ArrayList<Intersectable>(this.image);
    }

    public Scene(String name) {
        this.name = name;
        this.image = new ArrayList<Intersectable>();
    }

    public void insertImage(Intersectable image) {
        this.image.add(image); // ToDo: deep copy to image
    }

    public void setBackColor(Color backColor) {
        this.backColor = new Color(backColor);
    }

    public void setFillLight(AmbientLight fillLight) {
        this.fillLight = new AmbientLight(fillLight);
    }

    public void setCamAndDis(Camera cam,double disToScreen) {
        this.cam = new Camera(cam);
        this.DisToScreen = disToScreen;
    }

}
