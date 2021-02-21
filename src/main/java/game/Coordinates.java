package game;

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
        return new Coordinates((char) (file -1), rank -1);
    }


}
