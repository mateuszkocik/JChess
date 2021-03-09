import game.Chessboard;
import game.IllegalMoveException;
import game.MoveOperator;
import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.Coordinates.makeCoord;
import static game.Move.moveOf;
import static game.Team.BLACK;
import static game.Team.WHITE;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MoveOperatorTest{

    private MoveOperator moveOperator;
    private Chessboard chessboard;

    @BeforeEach
    void setUp(){
        chessboard = new Chessboard();
        moveOperator = new MoveOperator(chessboard);
    }

    @Test
    void validBishopMove() throws IllegalMoveException{
        chessboard.set(new Bishop(WHITE, makeCoord('a', 2)));
        moveOperator.move(moveOf('a', 2, 'b', 3), WHITE);
    }

    @Test
    void invalidMove(){
        chessboard.set(new King(BLACK, makeCoord('e', 4)));
        chessboard.set(new Pawn(BLACK, makeCoord('d', 5)));
        chessboard.set(new Bishop(WHITE, makeCoord('b', 7)));
        assertThrows(IllegalMoveException.class, () ->
                moveOperator.move(moveOf('d', 5, 'd', 6), BLACK));
    }
}
