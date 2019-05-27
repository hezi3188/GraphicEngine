package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.pointD3;
import primitives.vector;
import scene.Scene;
import primitives.Color;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RenderTestColor {
    @Test
    public void test3()

    {
        Scene scene = new Scene("");

        scene.insertImage(new Sphere(100, new pointD3(0.0, 0.0, -150), new Color(java.awt.Color.red)));

        //scene.setFillLight(new AmbientLight(new Color(java.awt.Color.white), 0.2));
        scene.insertLight(new PointLight(new Color(255,255,255),new pointD3(0.0, 0.0, -20),1,0.001,0.001));

        scene.setDis(50);

        ImageWriter imageWriter = new ImageWriter("Render test Color", 500, 500, 500, 500);

        Render render = new Render(scene);

        render.renderImage(imageWriter);
        //render.printGrid(50);
        //imageWriter.writeToimage();

    }
}