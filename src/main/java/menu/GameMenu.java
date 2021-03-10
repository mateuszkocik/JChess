package menu;

import colors.ColoredElement;
import command.Command;
import command.MoveCommand;
import game.*;
import system.ElementPainter;
import validator.CommandValidator;
import validator.GameValidator;

public class GameMenu implements Menu{

    private final Game game;

    public GameMenu(){
        this.game = new Game();
    }

    @Override
    public void showContent(){
        printHeader();
        boolean reversedChessboard = game.getTeamTurn() != Team.WHITE;
        ChessboardPrinter.print(game.getChessboard(), reversedChessboard);
    }

    private void printHeader(){
        GameStatus status = game.getGameStatus();
        switch(status){
            case ONGOING:
                printTeamTurn();
                break;
            case WHITE_WON:
                printTeamWin(Team.WHITE);
                break;
            case BLACK_WON:
                printTeamWin(Team.BLACK);
                break;
            default:
                printDraw();
                break;
        }
    }

    private void printDraw(){
        System.out.println("DRAW!!!");
    }

    private void printTeamWin(Team team){
        System.out.println(getTeamPiecePrintedText(team.name() + " WON!!!!", team));
    }

    private void printTeamTurn(){
        Team team = game.getTeamTurn();
        System.out.println(getTeamPiecePrintedText(team.name(), team));
    }

    private String getTeamPiecePrintedText(String text, Team team){
        return team == Team.WHITE ?
                ElementPainter.colorElement(text, ColoredElement.WHITE_PIECES) :
                ElementPainter.colorElement(text, ColoredElement.BLACK_PIECES);
    }

    @Override
    public Command getCommand(String command){
        Move move = moveFromCommand(command);
        return new MoveCommand(game, move);
    }

    private Move moveFromCommand(String command){
        command = command.toLowerCase();
        String[] split = command.split(" ");
        Coordinates c1 = new Coordinates(split[0].charAt(0), Character.getNumericValue(split[0].charAt(1)));
        Coordinates c2 = new Coordinates(split[1].charAt(0), Character.getNumericValue(split[1].charAt(1)));
        return new Move(c1, c2);
    }

    @Override
    public CommandValidator getCommandValidator(){
        return new GameValidator(game);
    }
}
