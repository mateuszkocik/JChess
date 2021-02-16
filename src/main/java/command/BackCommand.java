package command;

import system.MenuController;
import system.MenuHistory;

public class BackCommand implements Command{

    @Override
    public void execute(){
        MenuController menuController = MenuController.getInstance();
        var lastMenu = MenuHistory.getInstance().getPreviousMenu();
        menuController.changeMenu(lastMenu);
    }
}
