package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.List;
import java.util.stream.Collectors;

public class Knight extends Piece{

    public Knight(Team team, Coordinates coordinates){
        super(PieceType.KNIGHT, team, coordinates);
    }

    public Knight(Team team, char file, int rank){
        super(PieceType.KNIGHT, team, file, rank);
    }

    @Override
    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        return List
                .of(
                        coordinates.left().left().top(),
                        coordinates.left().left().bottom(),
                        coordinates.top().top().left(),
                        coordinates.top().top().right(),
                        coordinates.right().right().top(),
                        coordinates.right().right().bottom(),
                        coordinates.bottom().bottom().left(),
                        coordinates.bottom().bottom().right())
                .stream()
                .filter(Coordinates::isValid)
                .collect(Collectors.toList());
    }
}
