package game;

import game.chessboard.Chessboard;
import game.chessboard.ChessboardBuilder;
import game.move.IllegalMoveException;
import game.move.Move;
import game.move.MoveOperator;
import game.move.MoveValidator;
import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Knight;
import game.pieces.Piece;

import static game.GameStatus.*;
import static game.Team.*;

public class Game{

    private final Chessboard chessboard;
    private final MoveOperator moveOperator;
    private GameStatus gameStatus;
    private Team teamTurn;

    public Game(){
        this.gameStatus = ONGOING;
        this.teamTurn = WHITE;
        this.chessboard = ChessboardBuilder.buildStandardChessboard();
        this.moveOperator = new MoveOperator(chessboard);
    }

    public Game(Chessboard chessboard){
        this.gameStatus = ONGOING;
        this.teamTurn = WHITE;
        this.chessboard = chessboard;
        this.moveOperator = new MoveOperator(chessboard);
    }

    public GameStatus movePiece(Move move){
        if(gameStatus == ONGOING){
            try{
                moveOperator.move(move, teamTurn);
            }catch(IllegalMoveException e){
                return gameStatus;
            }
            gameStatus = getNextGameStatus();
            teamTurn = teamTurn.getEnemyTeam();
        }
        return gameStatus;
    }

    private GameStatus getNextGameStatus(){
        Team nextTeamMove = teamTurn.getEnemyTeam();
        if(draw(nextTeamMove))
            return DRAW;
        else if(moveOperator.getMoveValidator().anyMoveValid(nextTeamMove))
            return ONGOING;
        else
            return GameStatus.winnerOfTeam(teamTurn);
    }

    private boolean draw(Team nextTeamMove){
        return stalemate(nextTeamMove) || insufficientMaterial();
    }

    private boolean kingAndBishopOrKingAndKnight(Team team){
        var teamPieces = chessboard.getTeamPieces(team);
        if(teamPieces.size() == 2){
            Piece p1 = teamPieces.get(0);
            Piece p2 = teamPieces.get(1);
            boolean kingAndBishop = p1 instanceof King && p2 instanceof Bishop || p1 instanceof Bishop && p2 instanceof King;
            boolean kingAndKnight = p1 instanceof King && p2 instanceof Knight || p1 instanceof Knight && p2 instanceof King;
            return kingAndBishop || kingAndKnight;
        }
        return false;
    }

    private boolean onlyKingInTeam(Team team){
        var teamPieces = chessboard.getTeamPieces(team);
        return teamPieces.size() == 1 && teamPieces.get(0) instanceof King;
    }

    private boolean stalemate(Team team){
        MoveValidator moveValidator = moveOperator.getMoveValidator();
        return !moveValidator.kingIsChecked(team) && !moveValidator.anyMoveValid(team);
    }

    private boolean insufficientMaterial(){
        return (onlyKingInTeam(WHITE) || kingAndBishopOrKingAndKnight(WHITE)) &&
                (onlyKingInTeam(BLACK) || kingAndBishopOrKingAndKnight(BLACK));
    }

    public GameStatus getGameStatus(){
        return gameStatus;
    }

    public Chessboard getChessboard(){
        return chessboard;
    }

    public Team getTeamTurn(){
        return teamTurn;
    }

    public MoveValidator getMoveValidator(){
        return moveOperator.getMoveValidator();
    }
}
