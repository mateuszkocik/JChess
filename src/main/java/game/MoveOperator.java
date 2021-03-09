package game;

public class MoveOperator{

    private MoveInvoker moveInvoker;
    private MoveValidator moveValidator;

    public MoveOperator(Chessboard chessboard){
        this.moveInvoker = new MoveInvoker(chessboard);
        this.moveValidator = new MoveValidator(chessboard);
    }

    public void move(Move move, Team teamTurn) throws IllegalMoveException{
        if(!moveValidator.validMove(move, teamTurn)) throw new IllegalMoveException("Illegal move");
        moveInvoker.move(move);
    }

    public MoveValidator getMoveValidator(){
        return moveValidator;
    }
}
