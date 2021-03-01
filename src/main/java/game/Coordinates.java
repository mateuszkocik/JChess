package game;

import java.util.Objects;

import static game.BoardParameters.*;
import static game.BoardParameters.TO_RANK;

public class Coordinates{

    private final char file;
    private final int rank;

    public Coordinates(char file, int rank){
        this.file = file;
        this.rank = rank;
    }

    public char getFile(){
        return file;
    }

    public int getRank(){
        return rank;
    }

    public Coordinates top(){
        return new Coordinates(file, rank + 1);
    }

    public Coordinates bottom(){
        return new Coordinates(file, rank - 1);
    }

    public Coordinates right(){
        return new Coordinates((char) (file + 1), rank);
    }

    public Coordinates left(){
        return new Coordinates((char) (file - 1), rank);
    }

    public Coordinates topRight(){
        return new Coordinates((char) (file + 1), rank + 1);
    }

    public Coordinates topLeft(){
        return new Coordinates((char) (file - 1), rank + 1);
    }

    public Coordinates bottomRight(){
        return new Coordinates((char) (file + 1), rank - 1);
    }

    public Coordinates bottomLeft(){
        return new Coordinates((char) (file - 1), rank - 1);
    }

    public boolean isValid(){
        return file >= FROM_FILE && file <= TO_FILE && rank >= FROM_RANK && rank <= TO_RANK;
    }

    public static Coordinates makeCoord(char file, int rank){
        return new Coordinates(file, rank);
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return file == that.file && rank == that.rank;
    }

    @Override
    public int hashCode(){
        return Objects.hash(file, rank);
    }

    @Override
    public String toString(){
        return "Coordinates{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }

}
