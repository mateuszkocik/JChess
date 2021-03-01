package pieces;

import game.pieces.Pawn;
import org.junit.jupiter.api.Test;

import static game.Team.*;

public class PawnTest extends PieceTest{

    @Test
    void firstMoveTest(){
        setPiece(new Pawn(BLACK, 'a', 3));
        var whiteB2 = new Pawn(WHITE, 'b', 2);
        pieceAvailableMovesEqualsStringCoords(whiteB2, "a3,b3,b4");
        var whiteB3 = new Pawn(WHITE, 'b', 3);
        setPiece(whiteB3);
        pieceAvailableMovesEqualsStringCoords(whiteB2, "a3");
    }


    @Test
    void attackMoveOnChessboardEdge(){
        var whiteH2 = new Pawn(WHITE, 'h', 2);
        pieceAttackMovesEqualsStringCoords(whiteH2, "g3");
        var blackA4 = new Pawn(BLACK, 'a', 4);
        pieceAttackMovesEqualsStringCoords(blackA4, "b3");
    }

    @Test
    void sameTeamPieceOnAttack(){
        var whiteB3 = new Pawn(WHITE, 'b', 3);
        setPiece(whiteB3);
        var whiteC2 = new Pawn(WHITE, 'c', 2);
        pieceAttackMovesEqualsStringCoords(whiteC2, "d3,b3");
    }

    @Test
    void movedPawnAvailableMoves(){
        var movedBlackB3 = new Pawn(BLACK, 'b', 3, true);
        pieceAvailableMovesEqualsStringCoords(movedBlackB3, "b2");
        var movedWhiteA5 = new Pawn(WHITE, 'a', 5, true);
        pieceAvailableMovesEqualsStringCoords(movedWhiteA5, "a6");
    }

    @Test
    void emptyAvailableMoves(){
        var blackD5 = new Pawn(BLACK, 'd', 5);
        var whiteD4 = new Pawn(WHITE, 'd', 4);
        setPiece(blackD5);
        setPiece(whiteD4);
        emptyAvailableMoves(blackD5);
        emptyAvailableMoves(whiteD4);
    }

}
