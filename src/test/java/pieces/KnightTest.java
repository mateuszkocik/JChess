package pieces;

import game.pieces.Knight;
import org.junit.jupiter.api.Test;

import static game.Team.*;

public class KnightTest extends PieceTest{

    @Test
    void inTheCorner(){
        var whiteA1 = new Knight(WHITE, 'a', 1);
        pieceAvailableMovesEqualsStringCoords(whiteA1, "b3,c2");
        var blackH8 = new Knight(BLACK, 'h', 8);
        pieceAvailableMovesEqualsStringCoords(blackH8, "g6,f7");
        pieceAttackMovesEqualsStringCoords(blackH8, "g6,f7");
    }

    @Test
    void onTheEdge(){
        var whiteD8 = new Knight(WHITE, 'd', 8);
        String expected = "f7,e6,c6,b7";
        pieceAvailableMovesEqualsStringCoords(whiteD8, expected);
        pieceAttackMovesEqualsStringCoords(whiteD8, expected);
    }

    @Test
    void inTheMiddle(){
        var blackD5 = new Knight(BLACK, 'd', 5);
        String expected = "e7,f6,f4,e3,c3,b4,b6,c7";
        pieceAvailableMovesEqualsStringCoords(blackD5, expected);
        pieceAttackMovesEqualsStringCoords(blackD5, expected);
    }

    @Test
    void enemiesAndAlliesOnFields(){
        var blackG2 = new Knight(BLACK, 'g', 2);
        setPiece(new Knight(WHITE, 'h', 4));
        setPiece(new Knight(BLACK, 'f', 4));
        setPiece(new Knight(WHITE, 'e', 3));
        setPiece(new Knight(BLACK, 'e', 1));
        pieceAvailableMovesEqualsStringCoords(blackG2, "h4,e3");
        pieceAttackMovesEqualsStringCoords(blackG2, "h4,f4,e3,e1");
    }

}
