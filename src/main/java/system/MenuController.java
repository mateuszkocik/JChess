package system;

import colors.ColoredElement;
import menu.Menu;
import menu.WelcomeMenu;

public class MenuController{

    private static MenuController instance;
    private final MenuHistory menuHistory;
    private Menu currentMenu;

    public MenuController(){
        this.currentMenu = new WelcomeMenu();
        this.menuHistory = new MenuHistory();
    }

    public void process(){
        showCurrentMenu();
        while(true){
            var command = CommandScanner.getCommand(currentMenu);
            if(command.isPresent()){
                command.get().execute();
                showCurrentMenu();
            }else{
                showCurrentMenuWithError();
            }
        }
    }

    public void changeMenu(Menu menu){
        menuHistory.addMenuToHistory(currentMenu);
        currentMenu = menu;
    }

    public void backToPreviousMenu(){
        currentMenu = menuHistory.getPreviousMenu();
    }

    private void showCurrentMenu(){
        clearScreen();
        currentMenu.showContent();
    }

    private void showCurrentMenuWithError(){
        showCurrentMenu();
        String errorMessage = currentMenu.getCommandValidator().getError();
        System.out.println(ElementPainter.colorElement(errorMessage, ColoredElement.ERROR));
    }

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
        }
        return instance;
    }
}
