package validator;

import command.BackCommand;
import command.Command;
import command.QuitCommand;

import java.util.Map;

public class GlobalCommandValidator implements CommandValidator{

    private final Map<String, Command> GLOBAL_COMMANDS = Map.of(
            "QUIT", new QuitCommand(true),
            "BACK", new BackCommand());

    @Override
    public boolean validate(String command){
        return GLOBAL_COMMANDS.containsKey(command);
    }

    @Override
    public String getError(){
        return "Invalid command";
    }

    public Command getGlobalCommand(String key){
        return GLOBAL_COMMANDS.get(key);
    }
}
