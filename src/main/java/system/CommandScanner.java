package system;

import command.Command;
import menu.Menu;
import validator.CommandValidator;
import validator.GlobalCommandValidator;

import java.util.Optional;
import java.util.Scanner;

public class CommandScanner{

    private static final Scanner scanner = new Scanner(System.in);
    private static final GlobalCommandValidator globalValidator = new GlobalCommandValidator();

    public static String scanCommand(){
        return scanner.nextLine().toUpperCase();
    }

    public static Optional<Command> getCommand(Menu menu){
        String command = scanCommand();
        if(isGlobal(command)) return Optional.of(getGlobalCommand(command));
        CommandValidator validator = menu.getCommandValidator();
        return validator.validate(command) ? Optional.of(menu.getCommand(command)) : Optional.empty();
    }

    public static Command getGlobalCommand(String command){
        return globalValidator.getGlobalCommand(command);
    }

    public static boolean isGlobal(String command){
        return globalValidator.validate(command);
    }
}
