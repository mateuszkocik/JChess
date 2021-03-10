package colors;

public enum ColoredElement{
    TEXT("Standard text [T]"),
    ERROR("Error message text [T]"),
    BACKGROUND("Menu background [B]"),
    CHESSBOARD1("First color of chessboard [B]"),
    CHESSBOARD2("Second color of chessboard [B]"),
    WHITE_PIECES("Color of white pieces names on chessboard [T]"),
    BLACK_PIECES("Color of black pieces names on chessboard [T]");

    String description;

    ColoredElement(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
