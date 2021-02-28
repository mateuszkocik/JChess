package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.List;

public abstract class Piece{
    private final PieceType type;
    protected final Team team;
    protected Coordinates coordinates;

    public Piece(PieceType type, Team team, Coordinates coordinates){
        this.type = type;
        this.team = team;
        this.coordinates = coordinates;
    }

    public Piece(PieceType type, Team team, char file, int rank){
        this.type = type;
        this.team = team;
        this.coordinates = new Coordinates(file, rank);
    }

    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        return getAvailableMoves(chessboard);
    }

    public abstract List<Coordinates> getAvailableMoves(Chessboard chessboard);

    public Piece move(Coordinates to){
        coordinates = to;
        return this;
    }

    public Coordinates getCoordinates(){
        return coordinates;
    }

    public PieceType getType(){
        return type;
    }

    public String getName(){
        return type.getName();
    }

    public int getWeight(){
        return type.getWeight();
    }

    public Team getTeam(){
        return team;
    }

}
