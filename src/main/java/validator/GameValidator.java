package validator;

import game.*;
import game.chessboard.Coordinates;
import game.move.Move;

public class GameValidator implements CommandValidator{

    private final Game game;

    public GameValidator(Game game){
        this.game = game;
    }

    @Override
    public boolean validate(String command){
        Team teamTurn = game.getTeamTurn();
        return isMove(command) && game.getMoveValidator().validMove(moveFromCommand(command), teamTurn);
    }

    private boolean isCoordinate(String s){
        String coordinatesRegex = "[A-H][1-8]";
        return s.matches(coordinatesRegex);
    }

    private boolean isMove(String command){
        String[] split = command.split(" ");
        return split.length == 2 && isCoordinate(split[0]) && isCoordinate(split[1]);
    }

    private Move moveFromCommand(String command){
        command = command.toLowerCase();
        String[] split = command.split(" ");
        Coordinates c1 = new Coordinates(split[0].charAt(0), Character.getNumericValue(split[0].charAt(1)));
        Coordinates c2 = new Coordinates(split[1].charAt(0), Character.getNumericValue(split[1].charAt(1)));
        return new Move(c1, c2);
    }

    @Override
    public String getError(){
        return game.getGameStatus() == GameStatus.ONGOING ? "Invalid move" : "Game is over";
    }
}
