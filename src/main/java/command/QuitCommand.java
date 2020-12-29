package command;

import system.MenuController;

public class QuitCommand implements Command{

    @Override
    public void execute(){
        System.out.println("Are you sure you want to quit? To confirm type \"yes\"!");
        MenuController menuController = MenuController.getInstance();
        String confirmation = menuController.getCommandFromTerminal().toUpperCase();
        if(confirmation.equals("YES")){
            System.out.println("See you again :)!");
            System.exit(0);
        }
    }
}
