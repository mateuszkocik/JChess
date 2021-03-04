package pieces;

import game.pieces.*;
import org.junit.jupiter.api.Test;

import static game.Team.*;
import static org.junit.jupiter.api.Assertions.*;

public class KingTest extends PieceTest{

    @Test
    void noEnemies(){
        var whiteF5 = new King(WHITE, 'f', 5);
        pieceAvailableMovesEqualsStringCoords(whiteF5, "g5,g6,f6,e6,e5,e4,f4,g4");
    }

    @Test
    void blockedKingInCorner(){
        var blackA8 = new King(BLACK, 'a', 8);
        setPiece(new King(BLACK, 'b', 8));
        setPiece(new King(WHITE, 'b', 7));
        setPiece(new King(BLACK, 'a', 7));
        pieceAvailableMovesEqualsStringCoords(blackA8, "b7");
        pieceAttackMovesEqualsStringCoords(blackA8, "b8,b7,a7");
    }

    @Test
    void kingInTheMiddleAroundEnemiesAndAllies(){
        var blackF5 = new King(BLACK, 'f', 5);
        setPiece(new King(BLACK, 'g', 5));
        setPiece(new King(WHITE, 'g', 6));
        setPiece(new King(BLACK, 'e', 6));
        setPiece(new King(WHITE, 'e', 5));
        setPiece(new King(BLACK, 'e', 4));
        setPiece(new King(WHITE, 'f', 4));
        pieceAvailableMovesEqualsStringCoords(blackF5, "g6,f6,e5,f4,g4");
        pieceAttackMovesEqualsStringCoords(blackF5, "g5,g6,f6,e6,e5,e4,f4,g4");
    }

    @Test
    void checkedKing(){
        var whiteE3 = new King(WHITE, 'e', 3);
        setPiece(new Bishop(WHITE, 'b', 3));
        assertFalse(whiteE3.isChecked(chessboard));
        setPiece(new Queen(BLACK, 'e', 5));
        assertTrue(whiteE3.isChecked(chessboard));
    }

    @Test
    void uncheckedKing(){
        var blackA6 = new King(BLACK, 'a', 6);
        setPiece(new Pawn(WHITE, 'a', 5));
        setPiece(new Queen(BLACK, 'b', 6));
        setPiece(new Bishop(WHITE, 'a', 7));
        assertFalse(blackA6.isChecked(chessboard));
    }

    @Test
    void bothCastlingAvailableStandardChessboard(){
        var whiteE1 = new King(WHITE, 'e', 1);
        setPiece(new Rook(WHITE, 'h', 1));
        setPiece(new Rook(WHITE, 'a', 1));
        pieceAvailableMovesEqualsStringCoords(whiteE1, "d1,d2,e2,f2,f1,g1,c1");
        var blackE8 = new King(BLACK, 'e', 8);
        setPiece(new Rook(BLACK, 'h', 8));
        setPiece(new Rook(BLACK, 'a', 8));
        pieceAvailableMovesEqualsStringCoords(blackE8, "d8,d7,e7,f7,f8,g8,c8");
    }

    @Test
    void castlingUnavailableRookMoved(){
        var whiteE1 = new King(WHITE, 'e', 1);
        setPiece(new Rook(WHITE, 'h', 1, true));
        setPiece(new Rook(WHITE, 'a', 1, true));
        pieceAvailableMovesEqualsStringCoords(whiteE1, "d1,d2,e2,f2,f1");
    }

    @Test
    void castlingUnavailableNotRookInCorner(){
        var whiteE1 = new King(WHITE, 'e', 1);
        setPiece(new Bishop(WHITE, 'h', 1));
        setPiece(new Queen(WHITE, 'a', 1));
        pieceAvailableMovesEqualsStringCoords(whiteE1, "d1,d2,e2,f2,f1");
    }

    @Test
    void castlingUnavailableSquareAttackedByEnemy(){
        var blackE8 = new King(BLACK, 'e', 8);
        setPiece(new Rook(BLACK, 'h', 8));
        setPiece(new Rook(BLACK, 'a', 8));
        setPiece(new Bishop(WHITE, 'e', 6));
        pieceAvailableMovesEqualsStringCoords(blackE8, "d8,d7,e7,f7,f8");
    }

    @Test
    void castlingUnavailableSameTeamPieceBetweenKingAndRook(){
        var blackE8 = new King(BLACK, 'e', 8);
        setPiece(new Rook(BLACK, 'h', 8));
        setPiece(new Rook(BLACK, 'a', 8));
        setPiece(new Rook(BLACK, 'f', 8));
        setPiece(new Queen(BLACK, 'b', 8));
        pieceAvailableMovesEqualsStringCoords(blackE8, "d8,d7,e7,f7");
    }

    @Test
    void castlingUnavailableKingChecked(){
        var blackE8 = new King(BLACK, 'e', 8);
        setPiece(new Rook(BLACK, 'h', 8));
        setPiece(new Rook(BLACK, 'a', 8));
        setPiece(new Queen(WHITE, 'e', 3));
        pieceAvailableMovesEqualsStringCoords(blackE8, "d8,d7,e7,f7,f8");
    }

}
