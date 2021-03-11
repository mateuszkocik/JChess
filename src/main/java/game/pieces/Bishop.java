package game.pieces;

import game.chessboard.Chessboard;
import game.chessboard.Coordinates;
import game.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Bishop extends Piece{

    public Bishop(Team team, Coordinates coordinates){
        super(PieceType.BISHOP, team, coordinates);
    }

    public Bishop(Team team, char file, int rank){
        super(PieceType.BISHOP, team, file, rank);
    }

    @Override
    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        var attackMoves = new ArrayList<Coordinates>();

        Coordinates topLeft = coordinates;
        do{
            topLeft = topLeft.topLeft();
            attackMoves.add(topLeft);
        }while(chessboard.isEmpty(topLeft));

        Coordinates bottomRight = coordinates;
        do{
            bottomRight = bottomRight.bottomRight();
            attackMoves.add(bottomRight);
        }while(chessboard.isEmpty(bottomRight));

        Coordinates topRight = coordinates;
        do{
            topRight = topRight.topRight();
            attackMoves.add(topRight);
        }while(chessboard.isEmpty(topRight));

        Coordinates bottomLeft = coordinates;
        do{
            bottomLeft = bottomLeft.bottomLeft();
            attackMoves.add(bottomLeft);
        }while(chessboard.isEmpty(bottomLeft));

        return attackMoves
                .stream()
                .filter(Coordinates::isValid)
                .collect(Collectors.toList());
    }
}
