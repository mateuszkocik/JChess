package game;

import game.pieces.King;
import game.pieces.Piece;

import java.util.Optional;

public class MoveValidator{

    private final Chessboard chessboard;

    public MoveValidator(Chessboard chessboard){
        this.chessboard = chessboard;
    }

    public boolean validMove(Move move, Team teamTurn){
        var pieceOnFromCord = chessboard.get(move.getFrom());
        if(pieceOnFromCord.isPresent()){
            Piece pieceToBeMoved = pieceOnFromCord.get();
            var availableMoves = pieceToBeMoved.getAvailableMoves(chessboard);
            return pieceToBeMoved.getTeam() == teamTurn &&
                    availableMoves.contains(move.getTo()) &&
                    !afterMoveKingChecked(move, teamTurn);
        }
        return false;
    }

    public boolean anyMoveValid(Team team){
        var teamPieces = chessboard.getTeamPieces(team);
        for(Piece p : teamPieces){
            var availableMoves = p.getAvailableMoves(chessboard);
            boolean pieceHasValidMove =
                    availableMoves
                            .stream()
                            .anyMatch(c -> validMove(new Move(p.getCoordinates(), c), team));
            if(pieceHasValidMove) return true;
        }
        return false;
    }

    private boolean afterMoveKingChecked(Move move, Team team){
        var optPieceFrom = chessboard.get(move.getFrom());
        boolean result = false;
        if(optPieceFrom.isPresent()){
            Piece pieceFrom = optPieceFrom.get();
            var pieceTo = chessboard.get(move.getTo());
            changePiecePositionWithoutMoving(pieceFrom, move.getTo());
            result = kingIsChecked(team);
            changePiecePositionWithoutMoving(pieceFrom, move.getFrom());
            pieceTo.ifPresent(chessboard::set);
        }
        return result;
    }

    public boolean kingIsChecked(Team team){
        var optKing = getKing(team);
        return optKing.isPresent() && optKing.get().isChecked(chessboard);
    }

    private Optional<King> getKing(Team team){
        var teamPieces = chessboard.getTeamPieces(team);
        for(Piece teamPiece : teamPieces){
            if(teamPiece instanceof King)
                return Optional.of((King) teamPiece);
        }
        return Optional.empty();
    }

    private void changePiecePositionWithoutMoving(Piece piece, Coordinates to){
        chessboard.remove(piece);
        piece.setCoordinates(to);
        chessboard.set(piece);
    }
}
