package command;

import menu.Menu;
import system.MenuController;

public class ChangeMenuCommand implements Command{

    private Menu menu;

    public ChangeMenuCommand(Menu menu){
        this.menu = menu;
    }

    @Override
    public void execute(){
        MenuController mc = MenuController.getInstance();
        mc.changeMenu(menu);
    }
}
