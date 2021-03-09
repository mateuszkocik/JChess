
import game.*;
import game.pieces.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static game.Move.moveOf;
import static game.Team.*;
import static org.junit.jupiter.api.Assertions.*;
import static game.Coordinates.makeCoord;

public class MoveInvokerTest{

    private Chessboard chessboard;
    private MoveInvoker invoker;

    @BeforeEach
    void setUp(){
        chessboard = new Chessboard();
        invoker = new MoveInvoker(chessboard);
    }

    @Test
    void teamPiecesUpdatedAfterMove(){
        Coordinates coordsBefore = makeCoord('a', 3);
        Pawn pawn = new Pawn(WHITE, coordsBefore);
        chessboard.set(pawn);
        var teamPiecesBefore = chessboard.getTeamPieces(WHITE);
        assertEquals(coordsBefore, teamPiecesBefore.get(0).getCoordinates());
        assertEquals(1, teamPiecesBefore.size());
        Coordinates coordsAfter = makeCoord('a', 4);
        invoker.move(new Move(coordsBefore, coordsAfter));
        var teamPiecesAfter = chessboard.getTeamPieces(WHITE);
        assertEquals(coordsAfter, teamPiecesAfter.get(0).getCoordinates());
        assertEquals(1, teamPiecesAfter.size());
    }

    @Test
    void whitePawnPromotion(){
        Coordinates coordsBefore = makeCoord('a', 7);
        Coordinates coordsAfter = makeCoord('a', 8);
        chessboard.set(new Pawn(WHITE, coordsBefore));
        var teamPieces = chessboard.getTeamPieces(WHITE);
        assertEquals(1, teamPieces.size());
        invoker.move(new Move(coordsBefore, coordsAfter));
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
        invoker.move(moveOf('e', 1, 'g', 1));
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
    void longCastling(){
        Coordinates kingCoords = makeCoord('e', 8);
        Coordinates rookCoords = makeCoord('a', 8);
        King king = new King(BLACK, kingCoords);
        Rook rook = new Rook(BLACK, rookCoords);
        chessboard.set(king);
        chessboard.set(rook);
        invoker.move(moveOf('e', 8, 'c', 8));
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
}
