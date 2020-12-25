package menu;

import command.Command;
import validator.CommandValidator;
import validator.NumericValidator;

public class WelcomeMenu implements Menu{

    @Override
    public void showContent(){
        System.out.println(
                "Welcome in the Jchess game. What would you like to do?\n" +
                        "1. Play Jchess game\n" +
                        "2. Options\n" +
                        "3. Guide\n" +
                        "4. Quit\n");
    }

    @Override
    public Command getCommand(String command){
        return null;
    }

    @Override
    public CommandValidator getCommandValidator(){
        return new NumericValidator(1, 4);
    }
}
