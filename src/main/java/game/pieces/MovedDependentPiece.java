package game.pieces;

import game.Coordinates;
import game.Team;

public abstract class MovedDependentPiece extends Piece{

    protected boolean wasMoved = false;

    public MovedDependentPiece(PieceType type, Team team, Coordinates coordinates){
        super(type, team, coordinates);
    }

    public MovedDependentPiece(PieceType type, Team team, char file, int rank){
        super(type, team, file, rank);
    }

    public MovedDependentPiece(PieceType type, Team team, Coordinates coordinates, boolean wasMoved){
        super(type, team, coordinates);
        this.wasMoved = wasMoved;
    }

    public MovedDependentPiece(PieceType type, Team team, char file, int rank, boolean wasMoved){
        super(type, team, file, rank);
        this.wasMoved = wasMoved;
    }

}
