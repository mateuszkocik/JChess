package validator;

import colors.Color;
import colors.ColoredElement;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ColorChangeValidator implements CommandValidator{

    @Override
    public boolean validate(String command){
        var parts = command.split(" ");
        return parts.length == 2 && stringIsElement(parts[0]) && stringIsColor(parts[1]);
    }

    private boolean stringIsElement(String s){
        return Arrays
                .stream(ColoredElement.values())
                .map(ColoredElement::toString)
                .collect(Collectors.toList())
                .contains(s);
    }

    private boolean stringIsColor(String s){
        return Arrays
                .stream(Color.values())
                .map(Color::toString)
                .collect(Collectors.toList())
                .contains(s);
    }

    @Override
    public String getError(){
        return "Command must have format: [text/background/error] [color] e.g. text YELLOW";
    }
}
