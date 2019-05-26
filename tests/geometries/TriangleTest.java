package geometries;

import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.pointD3;
import primitives.ray;
import primitives.vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {
    @Test
    void Koko() {
        Triangle T=new Triangle(new pointD3(0,1,-2),new pointD3(-1,-1,-2),new pointD3(1,-1,-2),new Color(java.awt.Color.BLACK));
        assertEquals(T.getNormal(null).toString(),"");
    }
    @Test
    void findIntersections() {
        pointD3 p1 = new pointD3(0,1,0);
        pointD3 p2 = new pointD3(1,1,1);
        pointD3 p3 = new pointD3(0,1,1);

        Triangle P = new Triangle(p1,p2,p3,new Color(java.awt.Color.CYAN));

        ray R = new ray(new pointD3(0,0,0),new vector(0.1,1,0.5));
        List<pointD3> C =P.findIntersections(R);
        if(C==null)
            assert false;
        assertEquals(new pointD3(0.1,1,0.5),C.get(0));

        R = new ray(new pointD3(0,0,0),new vector(1,1,1));
        assertEquals(null,P.findIntersections(R));
    }
}