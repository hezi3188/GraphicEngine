package geometries;

import org.junit.jupiter.api.Test;
import primitives.pointD3;
import primitives.ray;
import primitives.vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void findIntersections() {
        pointD3 p1 = new pointD3(0,1,0);
        pointD3 p2 = new pointD3(1,1,1);
        pointD3 p3 = new pointD3(0,1,1);

        Plane P = new Plane(p1,p2,p3);

        ray R = new ray(new pointD3(0,0,0),new vector(1,1,1));
        List<pointD3> C =P.findIntersections(R);
        if(C==null)
            assert false;
        assertEquals(new pointD3(1,1,1),C.get(0));

        R = new ray(new pointD3(0,0,0),new vector(-1,-1,-1));
        assertEquals(null,P.findIntersections(R));
    }
}