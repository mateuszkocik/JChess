package game;

import game.pieces.*;

import java.util.Optional;

import static game.BoardParameters.*;

public class MoveAnalyser{

    private final Chessboard chessboard;

    public MoveAnalyser(Chessboard chessboard){
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


    private boolean afterMoveKingChecked(Move move, Team team){
        var optPieceFrom = chessboard.get(move.getFrom());
        boolean result = false;
        if(optPieceFrom.isPresent()){
            Piece pieceFrom = optPieceFrom.get();
            changePiecePosition(pieceFrom, move.getTo());
            result = kingIsChecked(team);
            changePiecePosition(pieceFrom, move.getFrom());
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

    public void move(Move move, Team teamTurn) throws IllegalMoveException{
        if(!validMove(move, teamTurn)) throw new IllegalMoveException("Illegal move");
        if(specialMove(move)) return;
        Piece piece = chessboard.get(move.getFrom()).get();
        changePiecePosition(piece, move.getTo());
    }

    private boolean specialMove(Move move){
        Piece piece = chessboard.get(move.getFrom()).get();
        Coordinates to = move.getTo();
        if(isPawnPromotion(piece, to)){
            promotePawn(piece);
            return true;
        }
        if(isCastling(piece, to)){
            castling(piece, to);
            return true;
        }
        return false;
    }

    private void castling(Piece king, Coordinates to){
        int fileDifference = king.getCoordinates().getFile() - to.getFile();
        if(fileDifference < 0)
            shortCastling(king);
        else
            longCastling(king);
    }

    private void longCastling(Piece king){
        Coordinates rookCoordinates = king.getCoordinates().left().left().left().left();
        Piece rook = chessboard.get(rookCoordinates).get();
        changePiecePosition(king, king.getCoordinates().left().left());
        changePiecePosition(rook, rook.getCoordinates().right().right().right());
    }


    private void shortCastling(Piece king){
        Coordinates rookCoordinates = king.getCoordinates().right().right().right();
        Piece rook = chessboard.get(rookCoordinates).get();
        changePiecePosition(king, king.getCoordinates().right().right());
        changePiecePosition(rook, rook.getCoordinates().left().left());
    }

    private boolean isCastling(Piece piece, Coordinates to){
        return piece instanceof King && Math.abs(piece.getCoordinates().getFile() - to.getFile()) == 2;
    }

    private void promotePawn(Piece piece){
        Coordinates cords = piece.getCoordinates();
        Coordinates queenCoordinates = piece.getTeam() == Team.WHITE ? cords.top() : cords.bottom();
        chessboard.remove(piece);
        chessboard.set(new Queen(piece.getTeam(), queenCoordinates));
    }

    private boolean isPawnPromotion(Piece piece, Coordinates to){
        return piece instanceof Pawn && (to.getRank() == FROM_RANK || to.getRank() == TO_RANK);
    }

    private void changePiecePosition(Piece piece, Coordinates to){
        chessboard.remove(piece);
        piece.move(to);
        chessboard.set(piece);
    }
}
