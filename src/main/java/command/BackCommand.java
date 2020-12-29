package command;

import system.MenuController;

public class BackCommand implements Command{

    @Override
    public void execute(){
        MenuController menuController = MenuController.getInstance();
        var lastMenu = menuController.popLatestMenu();
        if(lastMenu != null) menuController.changeMenu(lastMenu);
    }
}
