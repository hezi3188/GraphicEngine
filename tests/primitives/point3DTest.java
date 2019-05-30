package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class point3DTest {

    @Test
    void distance() {
        Point3D a = new Point3D(1,2,3);
        Point3D b = new Point3D(2,3,4);
        double result =  a.distance(b);
        assertEquals(1.732051,result,0.000001);
    }
}