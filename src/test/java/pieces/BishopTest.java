package pieces;

import game.pieces.Bishop;
import org.junit.jupiter.api.Test;

import static game.Team.*;

public class BishopTest extends PieceTest{

    @Test
    void inCornerBehindAlly(){
        var whiteA1 = new Bishop(WHITE, 'a', 1);
        setPiece(new Bishop(WHITE, 'b', 2));
        emptyAvailableMoves(whiteA1);
        pieceAttackMovesEqualsStringCoords(whiteA1, "b2");
    }

    @Test
    void enemiesOnEachDiagonal(){
        var blackD4 = new Bishop(BLACK, 'd', 4);
        setPiece(new Bishop(WHITE, 'e', 5));
        setPiece(new Bishop(WHITE, 'b', 6));
        setPiece(new Bishop(WHITE, 'c', 3));
        setPiece(new Bishop(WHITE, 'g', 1));
        pieceAvailableMovesEqualsStringCoords(blackD4, "e5,c5,b6,c3,e3,f2,g1");
    }

    @Test
    void blockedBishopOnTheMiddle(){
        var blackD4 = new Bishop(WHITE, 'd', 4);
        setPiece(new Bishop(WHITE, 'e', 5));
        setPiece(new Bishop(WHITE, 'c', 5));
        setPiece(new Bishop(WHITE, 'c', 3));
        setPiece(new Bishop(WHITE, 'e', 3));
        emptyAvailableMoves(blackD4);
    }

    @Test
    void noEnemies(){
        var whiteD4 = new Bishop(WHITE, 'd', 4);
        String expected = "e5,f6,g7,h8,c5,b6,a7,e3,f2,g1,c3,b2,a1";
        pieceAvailableMovesEqualsStringCoords(whiteD4, expected);
    }

    @Test
    void enemyBehindAlly(){
        var whiteA8 = new Bishop(WHITE, 'a', 8);
        setPiece(new Bishop(WHITE, 'c', 6));
        setPiece(new Bishop(BLACK, 'd', 5));
        pieceAvailableMovesEqualsStringCoords(whiteA8, "b7");
    }

}
