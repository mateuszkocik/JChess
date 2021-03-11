package game.move;

import game.chessboard.Coordinates;

public class Move{

    private final Coordinates from;
    private final Coordinates to;

    public Move(Coordinates from, Coordinates to){
        this.from = from;
        this.to = to;
    }

    public Coordinates getFrom(){
        return from;
    }

    public Coordinates getTo(){
        return to;
    }

    public static Move moveOf(char f1, int r1, char f2, int r2){
        return new Move(new Coordinates(f1, r1), new Coordinates(f2, r2));
    }
}
