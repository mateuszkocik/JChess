
import game.*;
import game.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static game.Team.*;
import static org.junit.jupiter.api.Assertions.*;
import static game.Coordinates.makeCoord;

public class MoveAnalyserTest{

    private Chessboard chessboard;
    private MoveAnalyser analyser;

    @BeforeEach
    void setUp(){
        chessboard = new Chessboard();
        analyser = new MoveAnalyser(chessboard);
    }

    @Test
    void validMoveReturnTruePawn(){
        chessboard.set(new Pawn(WHITE, makeCoord('a', 2)));
        chessboard.set(new Pawn(BLACK, makeCoord('b', 3)));
        assertTrue(analyser.validMove(moveOf('a', 2, 'a', 3), WHITE));
        assertTrue(analyser.validMove(moveOf('a', 2, 'a', 4), WHITE));
        assertTrue(analyser.validMove(moveOf('a', 2, 'b', 3), WHITE));
    }

    @Test
    void validMoveFalseKingUncovered(){
        chessboard.set(new King(BLACK, makeCoord('e', 4)));
        chessboard.set(new Pawn(BLACK, makeCoord('d', 5)));
        chessboard.set(new Bishop(WHITE, makeCoord('b', 7)));
        assertFalse(analyser.validMove(moveOf('d', 5, 'd', 6), BLACK));
    }

    @Test
    void successfulBishopMove() throws IllegalMoveException{
        chessboard.set(new Bishop(WHITE, makeCoord('a', 2)));
        analyser.move(moveOf('a', 2, 'b', 3), WHITE);
    }

    @Test
    void exceptionThrownWhenIllegalMove(){
        chessboard.set(new King(BLACK, makeCoord('e', 4)));
        chessboard.set(new Pawn(BLACK, makeCoord('d', 5)));
        chessboard.set(new Bishop(WHITE, makeCoord('b', 7)));
        assertThrows(IllegalMoveException.class, () ->
                analyser.move(moveOf('d', 5, 'd', 6), BLACK));
    }

    @Test
    void teamPiecesUpdatedAfterMove() throws IllegalMoveException{
        Coordinates coordsBefore = makeCoord('a', 3);
        Pawn pawn = new Pawn(WHITE, coordsBefore);
        chessboard.set(pawn);
        var teamPiecesBefore = chessboard.getTeamPieces(WHITE);
        assertEquals(coordsBefore, teamPiecesBefore.get(0).getCoordinates());
        assertEquals(1, teamPiecesBefore.size());
        Coordinates coordsAfter = makeCoord('a', 4);
        analyser.move(new Move(coordsBefore, coordsAfter), WHITE);
        var teamPiecesAfter = chessboard.getTeamPieces(WHITE);
        assertEquals(coordsAfter, teamPiecesAfter.get(0).getCoordinates());
        assertEquals(1, teamPiecesAfter.size());
    }

    @Test
    void whitePawnPromotion() throws IllegalMoveException{
        Coordinates coordsBefore = makeCoord('a', 7);
        Coordinates coordsAfter = makeCoord('a', 8);
        chessboard.set(new Pawn(WHITE, coordsBefore));
        var teamPieces = chessboard.getTeamPieces(WHITE);
        assertEquals(1, teamPieces.size());
        analyser.move(new Move(coordsBefore, coordsAfter), WHITE);
        var teamPiecesAfter = chessboard.getTeamPieces(WHITE);
        assertEquals(1, teamPiecesAfter.size());
        Piece queen = teamPiecesAfter.get(0);
        assertTrue(queen instanceof Queen);
        assertSame(queen.getTeam(), WHITE);
        assertEquals(queen.getCoordinates(), coordsAfter);
    }

    @Test
    void shortCastling() throws IllegalMoveException{
        Coordinates kingCoords = makeCoord('e', 1);
        Coordinates rookCoords = makeCoord('h', 1);
        King king = new King(WHITE, kingCoords);
        Rook rook = new Rook(WHITE, rookCoords);
        chessboard.set(king);
        chessboard.set(rook);
        analyser.move(moveOf('e', 1, 'g', 1), WHITE);
        var teamPieces = chessboard.getTeamPieces(WHITE);
        assertSame(2, teamPieces.size());
        assertTrue(teamPieces.contains(king));
        assertTrue(teamPieces.contains(rook));
        assertEquals(Optional.empty(),chessboard.get(kingCoords));
        assertEquals(Optional.empty(),chessboard.get(rookCoords));
        assertEquals(king, chessboard.get(makeCoord('g', 1)).get());
        assertEquals(rook, chessboard.get(makeCoord('f', 1)).get());
        assertTrue(king.wasMoved());
        assertTrue(rook.wasMoved());
    }

    @Test
    void longCastling() throws IllegalMoveException{
        Coordinates kingCoords = makeCoord('e', 8);
        Coordinates rookCoords = makeCoord('a', 8);
        King king = new King(BLACK, kingCoords);
        Rook rook = new Rook(BLACK, rookCoords);
        chessboard.set(king);
        chessboard.set(rook);
        analyser.move(moveOf('e', 8, 'c', 8), BLACK);
        var teamPieces = chessboard.getTeamPieces(BLACK);
        assertSame(2, teamPieces.size());
        assertTrue(teamPieces.contains(king));
        assertTrue(teamPieces.contains(rook));
        assertEquals(Optional.empty(),chessboard.get(kingCoords));
        assertEquals(Optional.empty(),chessboard.get(rookCoords));
        assertEquals(king, chessboard.get(makeCoord('c', 8)).get());
        assertEquals(rook, chessboard.get(makeCoord('d', 8)).get());
        assertTrue(king.wasMoved());
        assertTrue(rook.wasMoved());
    }

    private Move moveOf(char f1, int r1, char f2, int r2){
        return new Move(makeCoord(f1, r1), makeCoord(f2, r2));
    }
}
