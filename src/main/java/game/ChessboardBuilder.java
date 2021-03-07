package game;

import game.pieces.*;

import static game.BoardParameters.*;
import static game.Team.*;
import static game.Coordinates.makeCoord;

public class ChessboardBuilder{

    public static Chessboard buildStandardChessboard(){
        Chessboard ch = new Chessboard();
        fillFirstRank(ch, WHITE);
        fillFirstRank(ch,BLACK);
        fillRankWithPawns(ch, 2, WHITE);
        fillRankWithPawns(ch, 7, BLACK);

        return ch;
    }

    private static void fillFirstRank(Chessboard ch, Team team){
        int rank = team == WHITE ? 1 : 8;
        ch.set(new Rook(team, makeCoord('a', rank)));
        ch.set(new Knight(team, makeCoord('b', rank)));
        ch.set(new Bishop(team, makeCoord('c', rank)));
        ch.set(new Queen(team, makeCoord('d', rank)));
        ch.set(new King(team, makeCoord('e', rank)));
        ch.set(new Bishop(team, makeCoord('f', rank)));
        ch.set(new Knight(team, makeCoord('g', rank)));
        ch.set(new Rook(team, makeCoord('h', rank)));
    }

    private static void fillRankWithPawns(Chessboard ch, int rank, Team team){
        for(char i = FROM_FILE; i <= TO_FILE; i++){
            ch.set(new Pawn(team, makeCoord(i, rank)));
        }
    }
}
