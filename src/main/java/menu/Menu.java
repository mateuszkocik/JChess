package menu;

import command.Command;
import validator.CommandValidator;

public interface Menu{

    void showContent();

    Command getCommand(String command);

    CommandValidator getCommandValidator();
}
