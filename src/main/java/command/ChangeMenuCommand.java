package command;

import menu.Menu;
import system.MenuController;
import system.MenuHistory;

public class ChangeMenuCommand implements Command{

    private final Menu menu;

    public ChangeMenuCommand(Menu menu){
        this.menu = menu;
    }

    @Override
    public void execute(){
        MenuController menuController = MenuController.getInstance();
        MenuHistory.getInstance().addMenuToHistory(menuController.getCurrentMenu());
        menuController.changeMenu(menu);
    }
}
