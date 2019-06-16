package renderer;

import elements.AmbientLight;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Material;
import primitives.Point3D;
import scene.Scene;
import primitives.Color;

class RenderTest2 {

    @Test

    public void basicRendering(){

        Scene scene = new Scene("Render test");

        scene.insertImage(new Sphere( 50, new Point3D(0.0, 0.0, -150),new Color(java.awt.Color.red),new Material(0,0,0,0,0)));

        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149),new Color(java.awt.Color.blue),new Material(0,0,0,0,0));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149),new Color(java.awt.Color.green),new Material(0,0,0,0,0));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149),new Color(java.awt.Color.orange),new Material(0,0,0,0,0));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149),new Color(java.awt.Color.pink),new Material(0,0,0,0,0));

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