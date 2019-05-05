package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pointD3Test {

    @Test
    void distance() {
        pointD3 a = new pointD3(1,2,3);
        pointD3 b = new pointD3(2,3,4);
        double result =  a.distance(b);
        assertEquals(1.732051,result,0.000001);
    }
}