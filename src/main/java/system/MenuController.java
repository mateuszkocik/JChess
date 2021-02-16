package system;

import colors.ColorFormatter;
import menu.Menu;
import menu.WelcomeMenu;

public class MenuController{

    private static MenuController instance;

    private Menu currentMenu;
    private final ColorFormatter colorFormatter;


    public MenuController(){
        this.colorFormatter = new ColorFormatter();
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
            instance.currentMenu = new WelcomeMenu();
            instance.process();
        }
        return instance;
    }

    private void process(){
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
        currentMenu = menu;
    }

    private void showCurrentMenu(){
        clearScreen();
        currentMenu.showContent();
    }

    private void showCurrentMenuWithError(){
        clearScreen();
        currentMenu.showContent();
        String errorMessage = currentMenu.getCommandValidator().getError();
        System.out.println(colorFormatter.getColoredError(errorMessage));
    }

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public ColorFormatter getColorFormatter(){
        return colorFormatter;
    }

    public Menu getCurrentMenu(){
        return currentMenu;
    }
}
