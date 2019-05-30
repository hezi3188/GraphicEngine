package elements;


import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Point3D;

class PointLightTest {

    @Test
    void getIntensity() {
        Point3D a;
        Color lastColor;


        /*Point3D p = new Point3D(1,2,3);
        SpotLight x = new SpotLight(new Color(150,100,100),p,0.2,0.2,0.05,new vector(1,1,1));

        Point3D lastPoint = new Point3D(0,0,0);
        lastColor = x.getIntensity(lastPoint,null,new vector(-1,-1,-1));
        double dis = lastPoint.distance(p);

        for (double i=0;i<5;i+=0.01){
            a = new Point3D(i*10,i*10,i*10);
            System.out.println(x.getIntensity(a,null,new vector(i,i,i)).getColor().getRed()+"<"+lastColor.getColor().getRed());
            lastColor =   x.getIntensity(a,null,new vector(i,i,i));
            lastPoint = a;
            dis = lastPoint.distance(p);

        }*/

    }
}