package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.List;
import java.util.stream.Collectors;

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

    public abstract List<Coordinates> getAttackMoves(Chessboard chessboard);

    public List<Coordinates> getAvailableMoves(Chessboard chessboard){
        return getAttackMoves(chessboard)
                .stream()
                .filter(c -> chessboard.isEmptyOrEnemy(c, team))
                .collect(Collectors.toList());
    }

    public void move(Coordinates to){
        coordinates = to;
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

    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
}
