package menu;

import command.ChangeMenuCommand;
import command.Command;
import command.QuitCommand;
import validator.CommandValidator;
import validator.NumericValidator;

public class WelcomeMenu implements Menu{

    @Override
    public void showContent(){
        System.out.println(
                "Welcome in the JChess game. What would you like to do?\n\n" +
                        "1. Play JChess game\n" +
                        "2. Color settings\n" +
                        "3. Help\n" +
                        "4. Quit\n");
    }

    @Override
    public Command getCommand(String command){
        switch(command){
            case "1" : return new ChangeMenuCommand(new GameMenu());
            case "2" : return new ChangeMenuCommand(new ColorSettingsMenu());
            case "3" : return new ChangeMenuCommand(new HelpMenu());
            case "4" : return new QuitCommand(false);
        }
        return null;
    }

    @Override
    public CommandValidator getCommandValidator(){
        return new NumericValidator(1, 4);
    }
}
