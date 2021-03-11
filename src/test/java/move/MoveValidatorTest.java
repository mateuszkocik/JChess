package move;

import game.chessboard.Chessboard;
import game.move.MoveValidator;
import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static game.chessboard.Coordinates.makeCoord;
import static org.junit.jupiter.api.Assertions.*;
import static game.move.Move.moveOf;
import static game.Team.BLACK;
import static game.Team.WHITE;

public class MoveValidatorTest{

    private Chessboard chessboard;
    private MoveValidator validator;

    @BeforeEach
    void setUp(){
        chessboard = new Chessboard();
        validator = new MoveValidator(chessboard);
    }

    @Test
    void validMoveReturnTruePawn(){
        chessboard.set(new Pawn(WHITE, makeCoord('a', 2)));
        chessboard.set(new Pawn(BLACK, makeCoord('b', 3)));
        assertTrue(validator.validMove(moveOf('a', 2, 'a', 3), WHITE));
        assertTrue(validator.validMove(moveOf('a', 2, 'a', 4), WHITE));
        assertTrue(validator.validMove(moveOf('a', 2, 'b', 3), WHITE));
    }

    @Test
    void validMoveFalseKingUncovered(){
        chessboard.set(new King(BLACK, makeCoord('e', 4)));
        chessboard.set(new Pawn(BLACK, makeCoord('d', 5)));
        chessboard.set(new Bishop(WHITE, makeCoord('b', 7)));
        assertFalse(validator.validMove(moveOf('d', 5, 'd', 6), BLACK));
    }
}
