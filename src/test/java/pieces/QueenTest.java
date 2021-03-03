package pieces;

import game.pieces.Queen;
import org.junit.jupiter.api.Test;

import static game.Team.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QueenTest extends PieceTest{

    @Test
    void blockedByAllies(){
        var whiteD4 = new Queen(WHITE, 'd', 4);
        setPiece(new Queen(WHITE, 'e', 4));
        setPiece(new Queen(WHITE, 'e', 5));
        setPiece(new Queen(WHITE, 'd', 5));
        setPiece(new Queen(WHITE, 'c', 5));
        setPiece(new Queen(WHITE, 'c', 4));
        setPiece(new Queen(WHITE, 'c', 3));
        setPiece(new Queen(WHITE, 'd', 3));
        setPiece(new Queen(WHITE, 'e', 3));
        emptyAvailableMoves(whiteD4);
        pieceAttackMovesEqualsStringCoords(whiteD4, "e4,e5,d5,c5,c4,c3,d3,e3");
    }

    @Test
    void noEnemySizeTest(){
        var whiteD4 = new Queen(WHITE, 'd', 4);
        assertEquals(27, whiteD4.getAvailableMoves(chessboard).size());
        assertEquals(27, whiteD4.getAttackMoves(chessboard).size());
    }

    @Test
    void mixedTest(){
        var whiteD4 = new Queen(WHITE, 'd', 4);
        setPiece(new Queen(BLACK, 'f', 4));
        setPiece(new Queen(BLACK, 'e', 5));
        setPiece(new Queen(WHITE, 'd', 6));
        setPiece(new Queen(WHITE, 'c', 3));
        setPiece(new Queen(BLACK, 'd', 2));
        setPiece(new Queen(BLACK, 'e', 3));
        String expectedAvailable = "e4,f4,e5,d5,c5,b6,a7,c4,b4,a4,d3,d2,e3";
        String expectedAttack = expectedAvailable + ",d6,c3";
        pieceAvailableMovesEqualsStringCoords(whiteD4, expectedAvailable);
        pieceAttackMovesEqualsStringCoords(whiteD4, expectedAttack);
    }

}
