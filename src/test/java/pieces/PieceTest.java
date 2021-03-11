package pieces;

import game.chessboard.Chessboard;
import game.chessboard.Coordinates;
import game.pieces.Piece;
import org.junit.jupiter.api.BeforeEach;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static game.chessboard.Coordinates.makeCoord;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class PieceTest{

    protected Chessboard chessboard;

    @BeforeEach
    void setUp(){
        chessboard = new Chessboard();
    }

    protected void emptyAvailableMoves(Piece piece){
        assertEquals(piece.getAvailableMoves(chessboard).size(), 0);
    }

    protected void pieceAttackMovesEqualsStringCoords(Piece piece, String coords){
        var stringCoords = stringToCoordinates(coords);
        var pieceAttackMoves = piece.getAttackMoves(chessboard);
        coordinatesListAreTheSameIgnoringOrder(stringCoords, pieceAttackMoves);
    }

    protected void pieceAvailableMovesEqualsStringCoords(Piece piece, String coords){
        var stringCoords = stringToCoordinates(coords);
        var pieceAvailableMoves = piece.getAvailableMoves(chessboard);
        coordinatesListAreTheSameIgnoringOrder(stringCoords, pieceAvailableMoves);
    }

    private void coordinatesListAreTheSameIgnoringOrder(List<Coordinates> l1, List<Coordinates> l2){
        assertTrue(l1.size() == l2.size() && l2.containsAll(l1) && l1.containsAll(l2));
    }

    private List<Coordinates> stringToCoordinates(String coords){
        var result = coords.split(",");
        return Arrays
                .stream(result)
                .map(s -> makeCoord(s.charAt(0), s.charAt(1) - '0'))
                .collect(Collectors.toList());
    }

    protected void setPiece(Piece piece){
        chessboard.set(piece);
    }

}
