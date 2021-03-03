package pieces;

import game.pieces.Rook;
import org.junit.jupiter.api.Test;

import static game.Team.*;

public class RookTest extends PieceTest{

    @Test
    void blockedRookOnTheMiddleByEnemies(){
        var blackE4 = new Rook(BLACK, 'e', 4);
        setPiece(new Rook(WHITE, 'f', 4));
        setPiece(new Rook(WHITE, 'e', 5));
        setPiece(new Rook(WHITE, 'd', 4));
        setPiece(new Rook(WHITE, 'e', 3));
        pieceAvailableMovesEqualsStringCoords(blackE4, "f4,e5,d4,e3");
        pieceAttackMovesEqualsStringCoords(blackE4, "f4,e5,d4,e3");
    }

    @Test
    void blockedRookOnTheMiddleByAllies(){
        var blackE4 = new Rook(BLACK, 'e', 4);
        setPiece(new Rook(BLACK, 'f', 4));
        setPiece(new Rook(BLACK, 'e', 5));
        setPiece(new Rook(BLACK, 'd', 4));
        setPiece(new Rook(BLACK, 'e', 3));
        emptyAvailableMoves(blackE4);
        pieceAttackMovesEqualsStringCoords(blackE4, "f4,e5,d4,e3");
    }

    @Test
    void noEnemies(){
        var whiteD5 = new Rook(WHITE, 'd', 5);
        String expected = "d8,d7,d6,d4,d3,d2,d1,a5,b5,c5,e5,f5,g5,h5";
        pieceAvailableMovesEqualsStringCoords(whiteD5, expected);
        pieceAttackMovesEqualsStringCoords(whiteD5, expected);
    }

    @Test
    void enemiesAndAlliesOnWay(){
        var whiteF2 = new Rook(WHITE, 'f', 2);
        setPiece(new Rook(WHITE, 'h', 2));
        setPiece(new Rook(BLACK, 'f', 1));
        setPiece(new Rook(WHITE, 'f', 5));
        setPiece(new Rook(BLACK, 'f', 6));
        setPiece(new Rook(BLACK, 'c', 2));
        setPiece(new Rook(BLACK, 'c', 3));
        String expectedAvailable = "g2,f1,f3,f4,e2,d2,c2";
        String expectedAttack = expectedAvailable + ",h2,f5";
        pieceAvailableMovesEqualsStringCoords(whiteF2, expectedAvailable);
        pieceAttackMovesEqualsStringCoords(whiteF2, expectedAttack);
    }

}
