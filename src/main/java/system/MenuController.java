package system;

import colors.ColorFormatter;
import command.Command;
import menu.Menu;
import menu.WelcomeMenu;
import validator.CommandValidator;

import java.util.Scanner;

public class MenuController{

    private static MenuController instance;
    private final Scanner scanner;
    private Menu currentMenu;
    private CommandValidator currentValidator;
    private final ColorFormatter colorFormatter;

    public MenuController(){
        this.scanner = new Scanner(System.in);
        this.currentMenu = new WelcomeMenu();
        this.currentValidator = currentMenu.getCommandValidator();
        this.colorFormatter = new ColorFormatter();
        colorFormatter.displayDefaultColors();
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
            instance.changeMenu(instance.currentMenu);
        }
        return instance;
    }

    public void changeMenu(Menu menu){
        instance.currentMenu = menu;
        instance.currentValidator = menu.getCommandValidator();
        showCurrentMenu();
        getCommand().execute();
    }

    private void showCurrentMenu(){
        clearScreen();
        instance.currentMenu.showContent();
    }

    private Command getCommand(){
        String terminalCommand = validateTerminalCommand();
        return instance.currentMenu.getCommand(terminalCommand);
    }

    private String validateTerminalCommand(){
        String terminalCommand = getCommandFromTerminal();
        while(!instance.currentValidator.validate(terminalCommand)){
            showCurrentMenu();
            String errorMessage = instance.currentValidator.getError();
            System.out.println(instance.colorFormatter.getColoredError(errorMessage));
            terminalCommand = getCommandFromTerminal();
        }
        return terminalCommand;
    }

    private String getCommandFromTerminal(){
        var command = instance.scanner.nextLine();
        instance.scanner.reset();
        return command;
    }

    private void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
