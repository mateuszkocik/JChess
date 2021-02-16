package system;

import colors.ColorFormatter;
import command.BackCommand;
import command.Command;
import command.QuitCommand;
import menu.Menu;
import menu.WelcomeMenu;
import validator.CommandValidator;
import validator.GlobalCommandValidator;
import java.util.Scanner;

public class MenuController{

    private static MenuController instance;

    private final Scanner scanner;
    private Menu currentMenu;
    private CommandValidator currentValidator;
    private final ColorFormatter colorFormatter;


    public MenuController(){
        this.scanner = new Scanner(System.in);
        this.colorFormatter = new ColorFormatter();
    }

    public static MenuController getInstance(){
        if(instance == null){
            instance = new MenuController();
            instance.changeMenu(new WelcomeMenu());
            instance.progress();
        }
        return instance;
    }

    private void progress(){
        while(true){
            showCurrentMenu();
            getCommand().execute();
        }
    }

    public void changeMenu(Menu menu){
        currentMenu = menu;
        currentValidator = menu.getCommandValidator();
    }

    private void showCurrentMenu(){
        clearScreen();
        currentMenu.showContent();
    }

    private Command getCommand(){
        String terminalCommand = validateTerminalCommand();
        if(commandIsGlobal(terminalCommand)) return getGlobalCommand(terminalCommand);
        return currentMenu.getCommand(terminalCommand);
    }

    private String validateTerminalCommand(){
        String terminalCommand = getCommandFromTerminal();
        while(!(currentValidator.validate(terminalCommand) || commandIsGlobal(terminalCommand))){
            showCurrentMenu();
            String errorMessage = currentValidator.getError();
            System.out.println(colorFormatter.getColoredError(errorMessage));
            terminalCommand = getCommandFromTerminal();
        }
        return terminalCommand;
    }

    public String getCommandFromTerminal(){
        var command = scanner.nextLine();
        scanner.reset();
        return command;
    }

    private Command getGlobalCommand(String terminalCommand){
        switch(terminalCommand){
            case "quit":
                return new QuitCommand();
            case "back":
                return new BackCommand();
        }
        return null;
    }

    boolean commandIsGlobal(String command){
        return new GlobalCommandValidator().validate(command);
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
