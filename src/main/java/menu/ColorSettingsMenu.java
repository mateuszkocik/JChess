package menu;

import colors.*;
import command.ChangeColorCommand;
import command.Command;
import system.MenuController;
import validator.ColorChangeValidator;
import validator.CommandValidator;

public class ColorSettingsMenu implements Menu{

    private final ColorFormatter colorFormatter;

    public ColorSettingsMenu(){
        this.colorFormatter = MenuController.getInstance().getColorFormatter();
    }

    @Override
    public void showContent(){
        System.out.println(
                "Write command to change color of background, text or error\n" +
                        "Correct format of command: [text/background/error]  [color]\n\n" +
                        "List of available colors :\n");
        printListOfColors();
    }

    private void printListOfColors(){
        String lorem = "Lorem ipsum dolor sit amet...";
        int loremLen = lorem.length();
        String formatString = "%-15s\t%-" + loremLen + "s\t%-" + loremLen + "s\n";
        System.out.printf(formatString + '\n', "color", "text", "background");
        for(Color color : Color.values()){
            String coloredText = colorFormatter.getColoredText(lorem, color);
            String backgroundColoredText = colorFormatter.getTextWithBackground(lorem, color);
            System.out.printf(formatString, color, coloredText, backgroundColoredText);
        }
    }

    @Override
    public Command getCommand(String command){
        var commandParts = command.split(" ");
        ColoredElement coloredElement = ColoredElement.valueOf(commandParts[0].toUpperCase());
        Color color = Color.valueOf(commandParts[1].toUpperCase());
        return new ChangeColorCommand(coloredElement, color);
    }

    @Override
    public CommandValidator getCommandValidator(){
        return new ColorChangeValidator();
    }
}
