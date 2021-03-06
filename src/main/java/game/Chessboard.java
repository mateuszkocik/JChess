package game;

import game.pieces.Piece;

import java.util.*;

import static game.Team.*;

public class Chessboard extends Board<Piece>{

    private final Map<Team, List<Piece>> pieces;

    public Chessboard(){
        super(Piece.class);
        this.pieces = new HashMap<>();
        pieces.put(WHITE, new ArrayList<>());
        pieces.put(BLACK, new ArrayList<>());
    }

    @Override
    public void set(Coordinates c, Piece value){
        remove(c);
        super.set(c, value);
        pieces.get(value.getTeam()).add(value);
    }

    public void set(Piece value){
        set(value.getCoordinates(), value);
    }

    @Override
    public void remove(Coordinates c){
        super.remove(c);
        get(c).ifPresent(piece -> pieces.get(piece.getTeam()).remove(piece));
    }

    public void remove(Piece value){
        if(pieces.get(value.getTeam()).contains(value)){
            super.remove(value.getCoordinates());
            pieces.get(value.getTeam()).remove(value);
        }
    }

    public boolean isEnemy(Coordinates c, Team team){
        return c.isValid() && get(c).isPresent() && get(c).get().getTeam() != team;
    }

    public boolean isEmpty(Coordinates c){
        return c.isValid() && get(c).isEmpty();
    }

    public boolean isEmptyOrEnemy(Coordinates c, Team team){
        return isEnemy(c, team) || isEmpty(c);
    }

    public boolean squareIsAttackedByEnemy(Coordinates coordinates, Team team){
        var enemyTeamPieces = getTeamPieces(team.getEnemyTeam());
        for(Piece p : enemyTeamPieces){
            var attackMoves = p.getAttackMoves(this);
            if(attackMoves.contains(coordinates))
                return true;
        }
        return false;
    }

    public List<Piece> getTeamPieces(Team team){
        return List.copyOf(pieces.get(team));
    }

}
