package system;

import command.Command;
import menu.Menu;

import java.util.Optional;
import java.util.Scanner;

public class CommandScanner{
    private static final Scanner scanner = new Scanner(System.in);

    public static String scanCommand(){
        return scanner.nextLine().toUpperCase();
    }

    public static Optional<Command> getCommand(Menu menu){
        String command = scanCommand();
        if(GlobalCommandParser.commandIsGlobal(command)) return GlobalCommandParser.getGlobalCommand(command);
        var validator = menu.getCommandValidator();
        return validator.validate(command) ? Optional.of(menu.getCommand(command)) : Optional.empty();
    }

}
