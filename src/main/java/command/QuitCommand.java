package command;

import system.CommandScanner;
import system.MenuController;

public class QuitCommand implements Command{

    @Override
    public void execute(){
        System.out.println("Are you sure you want to quit? To confirm type \"yes\"!");
        String confirmation = CommandScanner.scanCommand();
        if(confirmation.equals("YES")){
            System.out.println("See you again :)!");
            System.exit(0);
        }
    }
}
