package renderer;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.pointD3;
import primitives.vector;
import scene.Scene;
import primitives.Color;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class RenderTest2 {

    @Test

    public void basicRendering(){

        Scene scene = new Scene("Render test");

        scene.insertImage(new Sphere( 50, new pointD3(0.0, 0.0, -150),new Color(java.awt.Color.red)));

        Triangle triangle = new Triangle(new pointD3( 100, 0, -149),
                new pointD3(  0, 100, -149),
                new pointD3( 100, 100, -149),new Color(java.awt.Color.blue));

        Triangle triangle2 = new Triangle(new pointD3( 100, 0, -149),
                new pointD3(  0, -100, -149),
                new pointD3( 100,-100, -149),new Color(java.awt.Color.green));

        Triangle triangle3 = new Triangle(new pointD3(-100, 0, -149),
                new pointD3(  0, 100, -149),
                new pointD3(-100, 100, -149),new Color(java.awt.Color.orange));

        Triangle triangle4 = new Triangle(new pointD3(-100, 0, -149),
                new pointD3(  0,  -100, -149),
                new pointD3(-100, -100, -149),new Color(java.awt.Color.pink));

        scene.insertImage(triangle);
        scene.insertImage(triangle2);
        scene.insertImage(triangle3);
        scene.insertImage(triangle4);
        scene.setFillLight(new AmbientLight(new Color(java.awt.Color.white),0.2));
        scene.setDis(100);

        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

        Render render = new Render(scene);

        render.renderImage(imageWriter);
        //render.printGrid(50);
        //imageWriter.writeToimage();


    }

}