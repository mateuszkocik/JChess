package colors;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ColorTest{

    private List<String> colors;

    public ColorTest(){
        this.colors =
                Arrays
                        .stream(Color.values())
                        .map(Color::toString)
                        .collect(Collectors.toList());
    }

    @Test
    public void fontColor(){
        var fontColors =
                Arrays
                        .stream(FontColor.values())
                        .map(FontColor::toString)
                        .collect(Collectors.toList());

        assertTrue(fontColors.containsAll(colors));
    }

    @Test
    public void backgroundColor(){
        var backgroundColors =
                Arrays
                        .stream(BackgroundColor.values())
                        .map(BackgroundColor::toString)
                        .collect(Collectors.toList());

        assertTrue(backgroundColors.containsAll(colors));
    }
}
