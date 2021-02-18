package system;

import command.BackCommand;
import command.Command;
import command.QuitCommand;
import menu.Menu;

import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

public class CommandScanner{
    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String,Command> GLOBAL_COMMANDS = Map.of(
        "QUIT", new QuitCommand(),
        "BACK", new BackCommand());


    public static String scanCommand(){
        return scanner.nextLine().toUpperCase();
    }

    public static Optional<Command> getCommand(Menu menu){
        String command = scanCommand();
        if(isGlobal(command)) return Optional.of(getGlobalCommand(command));
        var validator = menu.getCommandValidator();
        return validator.validate(command) ? Optional.of(menu.getCommand(command)) : Optional.empty();
    }

    public static Command getGlobalCommand(String key){
        return GLOBAL_COMMANDS.get(key);
    }

    public static boolean isGlobal(String command){
        return GLOBAL_COMMANDS.containsKey(command);
    }


}
