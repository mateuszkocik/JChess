package menu;

import command.Command;
import validator.CommandValidator;
import validator.GlobalCommandValidator;

public class HelpMenu implements Menu{

    @Override
    public void showContent(){
        System.out.println(
                "Welcome in the JChess help section!\n" +
                        "\n1.Game\n" +
                            "Playing JChess game is very simple. After start of the game all you have to do is to specify " +
                            "moves in commands e.g. A2 A4 which means move piece from A2 to A4. When you input " +
                            "invalid move error will be shown. Team turn is specified on the top of menu. " +
                            "When the game is over in the same place will be result. After each move chessboard is reversed.\n" +
                        "\n2.Color settings\n" +
                            "Color settings are saved under root of filesystem in file .JChess-color-settings." +
                            "To edit settings you can edit that file or do it using Color Settings menu.\n" +
                        "\n3.Commands\n" +
                            "All commands are case-independent.\n" +
                            "There are 2 commands which you can use wherever you are\n" +
                            "Quit - to quit JChess\n" +
                            "Back - to back to the previous menu\n");
    }

    @Override
    public Command getCommand(String command){
        return null;
    }

    @Override
    public CommandValidator getCommandValidator(){
        return new GlobalCommandValidator();
    }
}
