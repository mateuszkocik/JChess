package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.List;
import java.util.stream.Collectors;

public class King extends MovedDependentPiece{

    public King(Team team, Coordinates coordinates){
        super(PieceType.KING, team, coordinates);
    }

    public King(Team team, char file, int rank){
        super(PieceType.KING, team, file, rank);
    }

    @Override
    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        return List.of(
                coordinates.top(),
                coordinates.left(),
                coordinates.bottom(),
                coordinates.right(),
                coordinates.topLeft(),
                coordinates.topRight(),
                coordinates.bottomLeft(),
                coordinates.bottomRight())
                .stream()
                .filter(Coordinates::isValid)
                .collect(Collectors.toList());
    }

    @Override
    public List<Coordinates> getAvailableMoves(Chessboard chessboard){
        var availableMoves =
                getAttackMoves(chessboard)
                        .stream()
                        .filter(c -> chessboard.isEmptyOrEnemy(c, team))
                        .collect(Collectors.toList());
        if(longCastlingAvailable(chessboard))
            availableMoves.add(coordinates.left().left());
        if(shortCastlingAvailable(chessboard))
            availableMoves.add(coordinates.right().right());
        return availableMoves;
    }

    private boolean longCastlingAvailable(Chessboard chessboard){
        var oneLeft = coordinates.left();
        var twoLeft = oneLeft.left();
        var threeLeft = twoLeft.left();
        var pieceOnRookPosition = chessboard.get(threeLeft.left());
        if(!wasMoved && pieceOnRookPosition.isPresent() && pieceOnRookPosition.get() instanceof Rook){
            var rook = (Rook) pieceOnRookPosition.get();
            return !rook.wasMoved &&
                    rook.team == team &&
                    chessboard.isEmpty(oneLeft) &&
                    chessboard.isEmpty(twoLeft) &&
                    chessboard.isEmpty(threeLeft) &&
                    !isChecked(chessboard) &&
                    !chessboard.squareIsAttackedByEnemy(oneLeft, team) &&
                    !chessboard.squareIsAttackedByEnemy(twoLeft, team);
        }
        return false;

    }

    private boolean shortCastlingAvailable(Chessboard chessboard){
        var oneRight = coordinates.right();
        var twoRight = oneRight.right();
        var pieceOnRookPosition = chessboard.get(twoRight.right());
        if(!wasMoved && pieceOnRookPosition.isPresent() && pieceOnRookPosition.get() instanceof Rook){
            var rook = (Rook) pieceOnRookPosition.get();
            return !rook.wasMoved &&
                    rook.team == team &&
                    chessboard.isEmpty(oneRight) &&
                    chessboard.isEmpty(twoRight) &&
                    !isChecked(chessboard) &&
                    !chessboard.squareIsAttackedByEnemy(oneRight, team) &&
                    !chessboard.squareIsAttackedByEnemy(twoRight, team);
        }
        return false;
    }

    public boolean isChecked(Chessboard chessboard){
        return chessboard.squareIsAttackedByEnemy(coordinates, team);
    }
}
