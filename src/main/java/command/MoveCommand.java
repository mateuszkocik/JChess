package command;

import game.Game;
import game.move.Move;

public class MoveCommand implements Command{

    private final Game game;
    private final Move move;

    public MoveCommand(Game game, Move move){
        this.game = game;
        this.move = move;
    }

    @Override
    public void execute(){
        game.movePiece(move);
    }
}
