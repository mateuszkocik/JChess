package command;

import colors.Color;
import colors.ColorFormatter;
import colors.ColoredElement;
import system.MenuController;

public class ChangeColorCommand implements Command{

    private final ColoredElement element;
    private final Color color;
    private final ColorFormatter colorFormatter;

    public ChangeColorCommand(ColoredElement property, Color color){
        this.element = property;
        this.color = color;
        this.colorFormatter = MenuController.getInstance().getColorFormatter();
    }

    @Override
    public void execute(){
        colorFormatter.setElementColor(element, color);
        colorFormatter.displayDefaultColors();
    }
}
