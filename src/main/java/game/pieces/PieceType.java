package game.pieces;

public enum PieceType{
    KING("King", 100),
    QUEEN("Queen", 9),
    ROOK("Rook", 5),
    BISHOP("Bishop", 3),
    KNIGHT("Knight", 3),
    PAWN("Pawn", 1);

    String name;
    int weight;

    PieceType(String name, int weight){
        this.name = name;
        this.weight = weight;
    }

    public int getWeight(){
        return weight;
    }

    public String getName(){
        return name;
    }
}
