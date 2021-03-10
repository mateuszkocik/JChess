package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pawn extends MovedDependentPiece{

    public Pawn(Team team, Coordinates coordinates){
        super(PieceType.PAWN, team, coordinates);
    }

    public Pawn(Team team, char file, int rank){
        super(PieceType.PAWN, team, file, rank);
    }

    public Pawn(Team team, Coordinates coordinates, boolean wasMoved){
        super(PieceType.PAWN, team, coordinates, wasMoved);
    }

    public Pawn(Team team, char file, int rank, boolean wasMoved){
        super(PieceType.PAWN, team, file, rank, wasMoved);
    }

    @Override
    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        var attackMoves = new ArrayList<Coordinates>();
        Coordinates forward = getForwardCoordinates(coordinates);
        attackMoves.add(forward.left());
        attackMoves.add(forward.right());

        return attackMoves
                .stream()
                .filter(Coordinates::isValid)
                .collect(Collectors.toList());
    }

    @Override
    public List<Coordinates> getAvailableMoves(Chessboard chessboard){
        var availableMoves = new ArrayList<Coordinates>();
        Coordinates forward = getForwardCoordinates(coordinates);
        Coordinates doubleForward = getForwardCoordinates(forward);
        if(chessboard.isEmpty(forward))
            availableMoves.add(forward);
        if(!wasMoved && chessboard.isEmpty(forward) && chessboard.isEmpty(doubleForward))
            availableMoves.add(doubleForward);

        availableMoves.addAll(
                getAttackMoves(chessboard)
                        .stream()
                        .filter(c -> chessboard.isEnemy(c, team))
                        .collect(Collectors.toList()));

        return availableMoves;
    }

    private Coordinates getForwardCoordinates(Coordinates coordinates){
        return team == Team.WHITE ? coordinates.top() : coordinates.bottom();
    }
}
