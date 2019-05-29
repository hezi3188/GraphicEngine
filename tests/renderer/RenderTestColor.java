package renderer;

import elements.AmbientLight;
import elements.Camera;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
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
        //scene.insertLight(new PointLight(new Color(255,255,255),new pointD3(0.0, 0.0, -20),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,0,255),new pointD3(-120.0, -101.0, -20),1,0.001,0.001));
        scene.insertImage(
                new Plane(new pointD3(100,100,-100),new vector(new pointD3(1,1,1)),new Color(java.awt.Color.red))
        );
        scene.setDis(50);

        ImageWriter imageWriter = new ImageWriter("Render test Color", 500, 500, 500, 500);

        Render render = new Render(scene);

        render.renderImage(imageWriter);
        //render.printGrid(50);
        //imageWriter.writeToimage();

    }
    @Test
    public void test4()

    {
        Scene scene = new Scene("");

        scene.insertImage(new Sphere(170, new pointD3(0.0, 0.0, -250), new Color(java.awt.Color.RED)));
        scene.insertImage(new Triangle(new pointD3(100,100,-50),new pointD3(50,50,-50),new pointD3(50,75,-50), new Color(java.awt.Color.RED)));

        /*scene.insertImage(new Sphere(50, new pointD3(-70.0, 70.0, -150), new Color(java.awt.Color.RED)));
        scene.insertImage(new Sphere(30, new pointD3(70, 70, -50), new Color(java.awt.Color.RED)));
        scene.insertImage(new Sphere(50, new pointD3(70, -70, -150), new Color(java.awt.Color.RED)));
        scene.insertImage(new Sphere(50, new pointD3(-70, -70, -150), new Color(java.awt.Color.RED)));*/

        scene.setFillLight(new AmbientLight(new Color(30,30,30), 0.01));
        //scene.insertLight(new PointLight(new Color(255,255,255),new pointD3(0.0, 0.0, -20),1,0.001,0.001));
        /*scene.insertLight(
                new SpotLight(
                        new Color(255,255,255),new pointD3(0, 0, -180),0,0.5,0.05,
                        new vector(0,0,-1)
                )
        );*/
        scene.insertLight(new PointLight(new Color(0,255,255),new pointD3(100, 100, 10),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,255,255),new pointD3(103, 103, 10),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,255,255),new pointD3(97, 97, 10),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,255,255),new pointD3(100, 100, 13),1,0.001,0.001));
        /*scene.insertImage(
                new Plane(new pointD3(0,0,-200),new vector(new pointD3(1,1,0)),new Color(java.awt.Color.red))
        );*/

        /*scene.insertImage(
                new Triangle(new pointD3( -200, -300, -149),
                        new pointD3(  0, 300, -149),
                        new pointD3( 200, 200, -149),new Color(java.awt.Color.blue))  );*/

        scene.setDis(140);

        ImageWriter imageWriter = new ImageWriter("Render test Color2", 500, 500, 500, 500);

        Render render = new Render(scene);

        render.renderImage(imageWriter);
        //render.printGrid(50);
        //imageWriter.writeToimage();

    }
}