package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Rook extends MovedDependentPiece{

    public Rook(Team team, Coordinates coordinates){
        super(PieceType.ROOK, team, coordinates);
    }

    public Rook(Team team, char file, int rank){
        super(PieceType.ROOK, team, file, rank);
    }

    public Rook(Team team, Coordinates coordinates, boolean wasMoved){
        super(PieceType.ROOK, team, coordinates, wasMoved);
    }

    public Rook(Team team, char file, int rank, boolean wasMoved){
        super(PieceType.ROOK, team, file, rank, wasMoved);
    }

    @Override
    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        var attackMoves = new ArrayList<Coordinates>();

        Coordinates top = coordinates;
        do{
            top = top.top();
            attackMoves.add(top);
        }while(chessboard.isEmpty(top));

        Coordinates bottom = coordinates;
        do{
            bottom = bottom.bottom();
            attackMoves.add(bottom);
        }while(chessboard.isEmpty(bottom));

        Coordinates left = coordinates;
        do{
            left = left.left();
            attackMoves.add(left);
        }while(chessboard.isEmpty(left));

        Coordinates right = coordinates;
        do{
            right = right.right();
            attackMoves.add(right);
        }while(chessboard.isEmpty(right));

        return attackMoves
                .stream()
                .filter(Coordinates::isValid)
                .collect(Collectors.toList());
    }

}
