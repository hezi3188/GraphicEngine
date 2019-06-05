package renderer;

import elements.*;
import geometries.Plane;
import geometries.Quad;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;
import scene.Scene;

class RenderTestColor {
    @Test
    public void test3()

    {
        Scene scene = new Scene("");

        scene.insertImage(new Sphere(100, new Point3D(0.0, 0.0, -150), new Color(java.awt.Color.red)));

        //scene.setFillLight(new AmbientLight(new Color(java.awt.Color.white), 0.2));
        //scene.insertLight(new PointLight(new Color(255,255,255),new Point3D(0.0, 0.0, -20),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,0,255),new Point3D(-120.0, -101.0, -20),1,0.001,0.001));
        scene.insertImage(
                new Plane(new Point3D(100,100,-100),new vector(new Point3D(1,1,1)),new Color(java.awt.Color.red))
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

        scene.insertImage(new Sphere(170, new Point3D(0.0, 0.0, -250), new Color(java.awt.Color.RED)));
        scene.insertImage(new Triangle(new Point3D(100,100,-50),new Point3D(50,50,-50),new Point3D(50,75,-50), new Color(java.awt.Color.RED)));

        /*scene.insertImage(new Sphere(50, new Point3D(-70.0, 70.0, -150), new Color(java.awt.Color.RED)));
        scene.insertImage(new Sphere(30, new Point3D(70, 70, -50), new Color(java.awt.Color.RED)));
        scene.insertImage(new Sphere(50, new Point3D(70, -70, -150), new Color(java.awt.Color.RED)));
        scene.insertImage(new Sphere(50, new Point3D(-70, -70, -150), new Color(java.awt.Color.RED)));*/

        scene.setFillLight(new AmbientLight(new Color(30,30,30), 0.01));

        //scene.insertLight(new PointLight(new Color(255,255,255),new Point3D(0.0, 0.0, -20),1,0.001,0.001));
        /*scene.insertLight(
                new SpotLight(
                        new Color(255,255,255),new Point3D(0, 0, -180),0,0.5,0.05,
                        new vector(0,0,-1)
                )
        );*/
        scene.insertLight(new PointLight(new Color(0,255,255),new Point3D(100, 100, 10),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,255,255),new Point3D(103, 103, 10),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,255,255),new Point3D(97, 97, 10),1,0.001,0.001));
        scene.insertLight(new PointLight(new Color(0,255,255),new Point3D(100, 100, 13),1,0.001,0.001));
        scene.insertLight(new SpotLight(new Color(90, 30, 130), new Point3D(100, -100, 0)
                , 0, 0.001, 0.0001 , //right light
                new Point3D(100,100,-50).substract(new Point3D(100, -100, 0))));
        /*scene.insertImage(
                new Plane(new Point3D(0,0,-200),new vector(new Point3D(1,1,0)),new Color(java.awt.Color.red))
        );*/

        /*scene.insertImage(
                new Triangle(new Point3D( -200, -300, -149),
                        new Point3D(  0, 300, -149),
                        new Point3D( 200, 200, -149),new Color(java.awt.Color.blue))  );*/

        scene.setDis(140);

        ImageWriter imageWriter = new ImageWriter("Render test Color2", 500, 500, 500, 500);

        Render render = new Render(scene);

        render.renderImage(imageWriter);
        //render.printGrid(50);
        //imageWriter.writeToimage();

    }
    @Test
    public void testPlane(){
        for(int i=10;i<50;i+=10) {
            Scene scene = new Scene("");
            //scene.setDis(150);
            scene.setCamAndDis(new Camera(new Point3D(0, 0, 0), new vector(0, 0, -1), new vector(0, 1, 0)), 150);

            //Plane plane = new Plane(new Point3D(-100, i*10, i*10), new vector(1, 0, 0), new Color(133, 133, 133));
            Triangle plane = new Triangle(new Point3D(i*10, i*10, -150-(i*10)),new Point3D(-i*10, -i*10, -150-(i*2)),new Point3D(-i*10, i*10, -150-(i*10)), new Color(133, 133, 133));
            scene.insertImage(plane);
            Triangle plane2 = new Triangle(new Point3D(i*10, i*10, -150-(i*10)),new Point3D(-i*10, -i*10, -150-(i*2)),new Point3D(i*10, -i*10, -150-(i*10)), new Color(133, 133, 133));
            scene.insertImage(plane2);


            ImageWriter imageWriter = new ImageWriter("testPart3_02"+i, 500, 500, 500, 500);
            Render render = new Render(scene);
            render.renderImage(imageWriter);
        }

    }
    @Test
    public void qube(){
        Scene scene = new Scene("");
        scene.setCamAndDis(new Camera(new Point3D(0, 0, 0), new vector(0, 0, -1), new vector(0, 1, 0)), 150);
        scene.setFillLight(new AmbientLight(new Color(120,120,120),0.5));
        Point3D TopRight = new Point3D(-200,200,-150);
        double width = 300;
        double height = 300;
        double dip =300;

        Quad a = new Quad(TopRight,300,300,new Color(255,0,0));
        scene.insertImage(a);
        PointLight Li = new PointLight(new Color(0,120,120),new Point3D(-200,200,-130),1,0.001,0.00001);
        scene.insertLight(Li);
        ImageWriter imageWriter = new ImageWriter("testPart3_qu", 500, 500, 500, 500);
        Render render = new Render(scene);
        render.renderImage(imageWriter);
    }
    @Test
    public void test5(){
        Scene scene = new Scene("");
        //scene.setDis(150);
        scene.setCamAndDis(new Camera(new Point3D(0,0,0),new vector(0,0,-1),new vector(1,0,0)),150);
        //Material material = new Material(19, 0.4, 0.2);
        Point3D pSphere = new Point3D(-50, -100, -150);
        Sphere sphere = new Sphere(50, pSphere,new Color(10, 100, 20));
        //sphere.setEmmission();
        //sphere.setMaterial(material);
        scene.insertImage(sphere);

        Point3D pSphere1 = new Point3D(-30, 0, -250);
        Sphere sphere1 = new Sphere(70,pSphere1,new Color(110, 20, 10));
        //sphere1.setEmmission(new Color(110, 20, 10));
        //sphere1.setMaterial(material);
        scene.insertImage(sphere1);

        Point3D pSphere2 = new Point3D(-10, 150, -350);
        Sphere sphere2 = new Sphere(90,pSphere2,new Color(20, 20, 100) );
        /*sphere2.setEmmission(new Color(20, 20, 100));
        sphere2.setMaterial(material);*/
        scene.insertImage(sphere2);


        Plane plane = new Plane(new Point3D(-100, 0 , 0),new vector(1,0,0), new Color(133, 133, 133));
        plane.set_material(new Material(20,0.5,20,0,0));
        //plane.setMaterial(15, 0.1, 0.4, 0.2,1);
        //plane.setEmmission(new Color(133, 133, 133));
        scene.insertImage(plane);

        scene.insertLight(new SpotLight(new Color(130, 100, 130), new Point3D(150, 150, -50), //right light
                0, 0.001, 0.0001, pSphere.substract(new Point3D(300, 0, -250))));
        scene.insertLight(new SpotLight(new Color(110, 130, 30), new Point3D(150, 150, -50), //right light
                0, 0.001, 0.0001,pSphere1.substract(new Point3D(300, 0, -250))));
        scene.insertLight(new SpotLight(new Color(90, 30, 130), new Point3D(150, 150, -50)
                , 0, 0.001, 0.0001 , //right light
                pSphere2.substract(new Point3D(300, 0, -250))));


        ImageWriter imageWriter = new ImageWriter("testPart3_02", 500, 500, 500, 500);
        Render render = new Render(scene);
        render.renderImage(imageWriter);
        //render.writeToImage();
    }
    @Test
    public void spotLightTest2(){

        Scene scene = new Scene("");
        scene.setCamAndDis(new Camera(new Point3D(0,0,0),new vector(0,0,-1),new vector(1,0,0)),200);

        Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000),new Color(0, 0, 100));
        //sphere.setEmmission(new Color(0, 0, 100));
        //Material m=new Material();
        //sphere.setShininess(20);
        //sphere.setMaterial(m);
        scene.insertImage(sphere);

        Triangle triangle = new Triangle(new Point3D(-125, -225, -260),
                new Point3D(-225, -125, -260),
                new Point3D(-225, -225, -270),new Color (0, 0, 100));
        //triangle.setEmmission(new Color (0, 0, 100));

        //Material m1=new Material();
        //triangle.setShininess(4);
        //triangle.setMaterial(m);
        scene.insertImage(triangle);

        scene.insertLight(new SpotLight(new Color(255, 100, 100), new Point3D(-200, -200, -150)
                , 0.1, 0.00001, 0.000005,new vector(2, 2, -3)));

        ImageWriter imageWriter = new ImageWriter("Spot test 2", 500, 500, 500, 500);

        Render render = new Render(scene);

        render.renderImage(imageWriter);
        //render.writeToImage();
    }
    @Test
    /**
     * Testing creation of lighted images
     */
    public void bigimageTest() {
        //Render renderer = new Render();
        //renderer.setImageName("The great image");
        Scene getScene = new Scene("");
        getScene.setCamAndDis(new Camera(new Point3D(-5000,0,0), new vector(1,0,0), new vector(0,1,0)),5300); //NOTE: in this test, vTo is NOT the usual 0,0,-1. It is 1,0,0!!

        getScene.insertLight(new PointLight(new Color(200,200,200), new Point3D(-30,50,60), 1, 0.00005, 0.00003));
        getScene.insertLight(new PointLight(new Color(00,00,200), new Point3D(new Coordinate(80), new Coordinate(80), new Coordinate(120)), 1, 0.00005, 0.00003));
        getScene.insertLight(new SpotLight(new Color(200,00,000), new Point3D(new Coordinate(30), new Coordinate(00), new Coordinate(-60)), 1, 0.00005, 0.00003, new vector(new Point3D(new Coordinate(1), new Coordinate(0), new Coordinate(2)))));

        //getScene.insertLight(new SpotLight(new Color(10, 100, 10), new vector(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(-0.5)))));

        Sphere s;


        // Remove this loop and all it's contents if you wish the rendering to take less than 30 minutes!!
        /*for (double x = -50; x<=50; x+=5)
            for (double y = -(50-Math.abs(x)); y<=50-Math.abs(x); y+=5) {
                double z = Math.sqrt(2500 - x*x - y*y);
                s = new Sphere(5, new Point3D(new Coordinate(x + 60), new Coordinate(y), new Coordinate(z)),new Color((int)Math.abs(x+y+z)%25,(int)Math.abs(x+y+z+10)%25,(int)Math.abs(x+y+z+20)%25));
                s.setMaterial(new Material(1,0.1,0,0.6,99));
                renderer.getScene().addGeometry(s);
                if (z != 0 ) {
                    s = new Sphere(5, new Point3D(new Coordinate(x + 60), new Coordinate(y), new Coordinate(-z)),new Color((int)Math.abs(x+y+z)%25,(int)Math.abs(x+y+z+10)%25,(int)Math.abs(x+y+z+20)%25));
                    s.setMaterial(new Material(1,0.1,0,0.6,99));
                    renderer.getScene().addGeometry(s);
                }

            }*/



        s = new Sphere(70, new Point3D(new Coordinate(80), new Coordinate(0), new Coordinate(120)),new Color(0,0,0));
        //s.setMaterial(new Material(0.05,1,1,0,15));
        getScene.insertImage(s);

        s = new Sphere(30, new Point3D(new Coordinate(60), new Coordinate(0), new Coordinate(0)),new Color(75,0,25));
        //s.setMaterial(new Material(0.2,1,1,0,15));
        getScene.insertImage(s);

        s = new Sphere(800, new Point3D(new Coordinate(60), new Coordinate(-900), new Coordinate(0)),new Color(0,0,0));
        //s.setMaterial(new Material(0.1,1,1,0,15));
        getScene.insertImage(s);

        s = new Sphere(800, new Point3D(new Coordinate(60), new Coordinate(900), new Coordinate(0)),new Color(0,0,0));
        //s.setMaterial(new Material(0.1,1,1,0,15));
        getScene.insertImage(s);


        getScene.insertImage(new Plane(new Point3D(new Coordinate(250), new Coordinate(-200), new Coordinate(-150)),new Point3D(new Coordinate(250), new Coordinate(200), new Coordinate(-150)),new Point3D(new Coordinate(250), new Coordinate(-200), new Coordinate(200)),new Color(15,15,15)));

        getScene.insertImage(new Triangle(new Point3D(new Coordinate(-5000), new Coordinate(-200), new Coordinate(-70)),new Point3D(new Coordinate(150), new Coordinate(200), new Coordinate(-70)),new Point3D(new Coordinate(150), new Coordinate(-200), new Coordinate(-70)),new Color(7,7,7)));
        getScene.insertImage(new Triangle(new Point3D(new Coordinate(-5000), new Coordinate(200), new Coordinate(-70)),new Point3D(new Coordinate(150), new Coordinate(200), new Coordinate(-70)),new Point3D(new Coordinate(-5000), new Coordinate(-200), new Coordinate(-70)),new Color(7,7,7)));

        getScene.setFillLight(new AmbientLight(new Color(0,0,0), 0));
        Render renderer = new Render(getScene);
        ImageWriter imageWriter = new ImageWriter("GrateImage", 500, 500, 500, 500);

        renderer.renderImage(imageWriter);
    }
}