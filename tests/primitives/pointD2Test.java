package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pointD2Test {

    @Test
    void toStringTest() {
        pointD2 p= new pointD2(1.0,2.0);
        assertEquals("{1.0,2.0}",p.toString());
    }
}