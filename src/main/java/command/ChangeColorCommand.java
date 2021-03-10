package command;

import colors.Color;
import colors.ColoredElement;
import system.ColorProperties;
import system.ElementPainter;

public class ChangeColorCommand implements Command{

    private final ColoredElement element;
    private final Color color;

    public ChangeColorCommand(ColoredElement property, Color color){
        this.element = property;
        this.color = color;
    }

    @Override
    public void execute(){
        ColorProperties.getInstance().setProperty(element, color);
        ElementPainter.printDefaultColorCodes();
    }
}
