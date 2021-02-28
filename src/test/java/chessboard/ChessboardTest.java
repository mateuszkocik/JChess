package chessboard;

import game.Chessboard;
import game.Coordinates;
import game.Team;
import game.pieces.Pawn;
import game.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static game.Team.*;

public class ChessboardTest{

    private Chessboard chessboard;
    private Coordinates pieceCords;
    private Piece piece;


    @BeforeEach
    void init(){
        chessboard = new Chessboard();
        pieceCords = new Coordinates('a', 2);
        piece = new Pawn(WHITE, pieceCords);
    }

    @Test
    void chessboardHasEmptySquaresAtBeginning(){
        assertEquals(chessboard.get(pieceCords), Optional.empty());
    }

    @Test
    void teamPiecesAreEmptyAtBeginning(){
        teamPiecesSizeEquals(WHITE, 0);
        teamPiecesSizeEquals(BLACK, 0);
    }

    @Test
    void getSetTest(){
        chessboard.set(piece);
        assertEquals(piece, chessboard.get(pieceCords).get());
        assertTrue(chessboard.getTeamPieces(piece.getTeam()).contains(piece));
        teamPiecesSizeEquals(WHITE, 1);
    }

    @Test
    void removePieceWhichIsNotOnChessboard(){
        chessboard.remove(piece);
        teamPiecesSizeEquals(WHITE, 0);
    }

    @Test
    void removePiece(){
        chessboard.set(piece);
        teamPiecesSizeEquals(WHITE, 1);
        chessboard.remove(piece);
        teamPiecesSizeEquals(WHITE, 0);
        assertEquals(chessboard.get(pieceCords), Optional.empty());
    }

    @Test
    void removePieceByGivenCords(){
        chessboard.set(piece);
        teamPiecesSizeEquals(WHITE, 1);
        chessboard.remove(piece.getCoordinates());
        teamPiecesSizeEquals(WHITE, 0);
        assertEquals(chessboard.get(pieceCords), Optional.empty());
    }

    @Test
    void isEnemy(){
        chessboard.set(piece);
        assertTrue(chessboard.isEnemy(pieceCords, BLACK));
        assertFalse(chessboard.isEnemy(pieceCords, WHITE));
        assertFalse(chessboard.isEnemy(new Coordinates('i', 9), BLACK));
    }

    @Test
    void isEmpty(){
        assertTrue(chessboard.isEmpty(pieceCords));
        chessboard.set(piece);
        assertFalse(chessboard.isEmpty(pieceCords));
        assertFalse(chessboard.isEmpty(new Coordinates('i', 9)));
        assertTrue(chessboard.isEmpty(new Coordinates('h', 8)));
    }

    @Test
    void squareIsAttackedByEnemyTest(){
        assertFalse(chessboard.squareIsAttackedByEnemy(pieceCords.topLeft(), BLACK));
        chessboard.set(piece);
        assertFalse(chessboard.squareIsAttackedByEnemy(pieceCords.topLeft(), BLACK));
        assertTrue(chessboard.squareIsAttackedByEnemy(pieceCords.topRight(), BLACK));
        assertFalse(chessboard.squareIsAttackedByEnemy(pieceCords.top(), BLACK));
        assertFalse(chessboard.squareIsAttackedByEnemy(new Coordinates('h', 9), BLACK));
    }

    private void teamPiecesSizeEquals(Team team, int size){
        assertEquals(chessboard.getTeamPieces(team).size(), size);
    }

}
