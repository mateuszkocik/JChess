package game;

import colors.Color;
import game.pieces.Piece;
import system.ColorProperties;
import system.ElementPainter;

import java.util.Collections;

import static game.BoardParameters.*;
import static colors.ColoredElement.*;
import static game.Team.*;

public class ChessboardPrinter{

    private static final ColorProperties colorProperties = ColorProperties.getInstance();

    public static void print(Chessboard ch, boolean reversed){
        for(int i = FROM_RANK; i <= TO_RANK; i++){
            int rank = reversed ? FROM_RANK + i - 1 : TO_RANK - i + 1;
            printChessboardSquareLine(ch, rank);
        }
        printFileLine();
        System.out.println();
    }

    private static void printChessboardSquareLine(Chessboard ch, int rank){
        for(int i = 0; i < SQUARE_HEIGHT; i++){
            System.out.print('\t');
            for(char file = FROM_FILE; file <= TO_FILE; file++){
                Color backgroundColor = getSquareColor(file, rank);
                if(i == SQUARE_HEIGHT / 2){
                    if(file == FROM_FILE)
                        System.out.print(rank);
                    printLineWithName(ch, file, rank, backgroundColor);
                }else{
                    if(file == FROM_FILE)
                        System.out.print(" ");
                    printEmptySquareLine(backgroundColor);
                }
            }
            System.out.println();
        }
    }

    private static void printLineWithName(Chessboard ch, char file, int rank, Color backgroundColor){
        var optPiece = ch.get(new Coordinates(file, rank));
        optPiece.ifPresentOrElse(
                p -> System.out.print(colorPieceName(p, backgroundColor)),
                () -> printEmptySquareLine(backgroundColor));
    }

    private static Color getSquareColor(char file, int rank){
        return (rank + file) % 2 == 1 ?
                colorProperties.getElementColor(CHESSBOARD1) : colorProperties.getElementColor(CHESSBOARD2);
    }

    private static String colorPieceName(Piece piece, Color backgroundColor){
        String nameSquareFormat = getTextBetweenSpaces(piece.getName());
        Team team = piece.getTeam();
        Color fontColor =
                team == WHITE ?
                        colorProperties.getElementColor(WHITE_PIECES) : colorProperties.getElementColor(BLACK_PIECES);

        return ElementPainter.colorTextAndBackground(nameSquareFormat, fontColor, backgroundColor);
    }

    private static String getTextBetweenSpaces(String text){
        int freeSpaces = SQUARE_WIDTH - text.length();
        return spaces(freeSpaces / 2) + text +
                spaces(freeSpaces - freeSpaces / 2);
    }

    private static void printEmptySquareLine(Color background){
        String coloredSpaces = ElementPainter.colorBackground(spaces(SQUARE_WIDTH), background);
        System.out.print(coloredSpaces);
    }

    private static void printFileLine(){
        System.out.print('\t');
        for(char i = FROM_FILE; i <= TO_FILE; i++){
            System.out.print(getTextBetweenSpaces(String.valueOf(i)));
        }
    }

    private static String spaces(int size){
        return String.join("", Collections.nCopies(size, " "));
    }
}
