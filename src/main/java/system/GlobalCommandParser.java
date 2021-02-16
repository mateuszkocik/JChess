package system;

import command.BackCommand;
import command.Command;
import command.QuitCommand;

import java.util.Optional;

public class GlobalCommandParser{

    public static Optional<Command> getGlobalCommand(String command){
        switch(command){
            case "QUIT":
                return Optional.of(new QuitCommand());
            case "BACK":
                return Optional.of(new BackCommand());
            default:
                return Optional.empty();
        }
    }

    public static boolean commandIsGlobal(String command){
        return command.equals("QUIT") || command.equals("BACK");
    }

}
