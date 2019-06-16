package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void findIntersections() {
        Point3D p1 = new Point3D(0,1,0);
        Point3D p2 = new Point3D(1,1,1);
        Point3D p3 = new Point3D(0,1,1);

        Plane P = new Plane(p1,p2,p3,new Color(java.awt.Color.CYAN),new Material(0,0,0,0,0));

        ray R = new ray(new Point3D(0,0,0),new vector(1,1,1));
        List<Point3D> C =P.findIntersections(R);
        if(C==null)
            assert false;
        assertEquals(new Point3D(1,1,1),C.get(0));

        R = new ray(new Point3D(0,0,0),new vector(-1,-1,-1));
        assertEquals(null,P.findIntersections(R));
    }
}