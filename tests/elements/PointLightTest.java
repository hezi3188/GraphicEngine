package elements;


import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.pointD3;
import primitives.vector;

import static org.junit.jupiter.api.Assertions.*;

class PointLightTest {

    @Test
    void getIntensity() {
        pointD3 a;
        Color lastColor;


        pointD3 p = new pointD3(1,2,3);
        SpotLight x = new SpotLight(new Color(150,100,100),p,0.2,0.2,0.05,new vector(1,1,1));

        pointD3 lastPoint = new pointD3(0,0,0);
        lastColor = x.getIntensity(lastPoint,new vector(-1,-1,-1));
        double dis = lastPoint.distance(p);

        for (double i=0;i<5;i+=0.01){
            a = new pointD3(i*10,i*10,i*10);
            System.out.println(x.getIntensity(a,new vector(i,i,i)).getColor().getRed()+"<"+lastColor.getColor().getRed());
            lastColor =   x.getIntensity(a,new vector(i,i,i));
            lastPoint = a;
            dis = lastPoint.distance(p);

        }

    }
}