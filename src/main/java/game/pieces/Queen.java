package game.pieces;

import game.Chessboard;
import game.Coordinates;
import game.Team;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(Team team, Coordinates coordinates){
        super(PieceType.QUEEN, team, coordinates);
    }

    public Queen(Team team, char file, int rank){
        super(PieceType.QUEEN, team, file, rank);
    }

    @Override
    public List<Coordinates> getAttackMoves(Chessboard chessboard){
        var bishopMoves = new Bishop(team, coordinates).getAttackMoves(chessboard);
        var rookMoves = new Rook(team, coordinates).getAttackMoves(chessboard);

        var attackMoves = new ArrayList<>(bishopMoves);
        attackMoves.addAll(rookMoves);

        return attackMoves;
    }
}
