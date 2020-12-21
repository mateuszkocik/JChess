package system;

import menu.Menu;
import menu.WelcomeMenu;

import java.util.Scanner;

public class MenuController{

    private static MenuController instance;
    private final Scanner scanner;
    private Menu currentMenu;

    public MenuController(){
        scanner = new Scanner(System.in);
        currentMenu = new WelcomeMenu();
        changeMenu(currentMenu);
    }

    public static MenuController getInstance(){
        if(instance == null) instance = new MenuController();
        return instance;
    }

    public void changeMenu(Menu menu){
        clearScreen();
        currentMenu = menu;
        menu.showContent();
        menu.processCommand(getCommand());
    }

    public void displayCurrentMenuWithError(){
        clearScreen();
        currentMenu.showContent();
        System.out.println("\033[0;91m" + "Wrong argument!" + "\033[0;91m");
        currentMenu.processCommand(getCommand());
    }

    private String getCommand(){
        var command = scanner.nextLine();
        scanner.reset();
        return command;
    }

    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
