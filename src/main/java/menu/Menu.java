package menu;

import system.MenuController;

public interface Menu{
    void showContent();
    void processCommand(String command);

    default void displayError(){
        MenuController.getInstance().displayCurrentMenuWithError();
    }

    default void changeMenu(Menu menu){
        var menuController = MenuController.getInstance();
        menuController.changeMenu(menu);
    }

}
