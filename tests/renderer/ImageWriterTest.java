package renderer;
import org.junit.jupiter.api.Test;

//import java.awt.*;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {
    @Test
    void Test() {

        ImageWriter imageWriter = new ImageWriter("test0", 0, 0, 500, 500);
        for(int y=0;y<500;y+=50)
            for(int i=0;i<500;i++){
                imageWriter.writePixel(i,y,new primitives.Color(Color.magenta));
                imageWriter.writePixel(y,i,new primitives.Color(Color.magenta));
            }
        imageWriter.writeToimage();
        assertEquals(new primitives.Color(Color.magenta).getColor().getRGB(), -65281);
    }
}