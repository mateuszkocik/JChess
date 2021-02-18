package system;

import colors.ColoredElement;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ElementPainterTest{

    @Test
    public void colorElementDoesntReturnNull(){
        String text = "xyz";
        for(ColoredElement element : ColoredElement.values()){
            assertNotNull(ElementPainter.colorElement(text,element));
        }
    }
}
