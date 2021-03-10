import game.*;
import game.pieces.Bishop;
import game.pieces.King;
import game.pieces.Pawn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static game.Team.*;
import static game.Coordinates.makeCoord;
import static game.Move.moveOf;
import static game.GameStatus.*;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest{

    private Game game;

    @BeforeEach
    void setUp(){
        game = new Game();
    }

    @Test
    void standardMoves(){
        GameStatus s1 = game.movePiece(moveOf('b', 2, 'b', 4));
        assertEquals(ONGOING, s1);
        assertEquals(BLACK, game.getTeamTurn());
        GameStatus s2 = game.movePiece(moveOf('b', 8, 'c', 6));
        assertEquals(ONGOING, s2);
        assertEquals(WHITE, game.getTeamTurn());
        GameStatus s3 = game.movePiece(moveOf('c', 1, 'b', 2));
        assertEquals(ONGOING, s3);
        assertEquals(BLACK, game.getTeamTurn());
    }

    @Test
    void foolsMate(){
        game.movePiece(moveOf('f', 2, 'f', 3));
        game.movePiece(moveOf('e', 7, 'e', 6));
        game.movePiece(moveOf('g', 2, 'g', 4));
        GameStatus gs = game.movePiece(moveOf('d', 8, 'h', 4));
        assertEquals(BLACK_WON, gs);
    }

    @Test
    void fastestStalemate(){
        Move[] moves = {
                moveOf('e', 2, 'e', 3),
                moveOf('a', 7, 'a', 5),
                moveOf('d', 1, 'h', 5),
                moveOf('a', 8, 'a', 6),
                moveOf('h', 5, 'a', 5),
                moveOf('h', 7, 'h', 5),
                moveOf('h', 2, 'h', 4),
                moveOf('a', 6, 'h', 6),
                moveOf('a', 5, 'c', 7),
                moveOf('f', 7, 'f', 6),
                moveOf('c', 7, 'd', 7),
                moveOf('e', 8, 'f', 7),
                moveOf('d', 7, 'b', 7),
                moveOf('d', 8, 'd', 3),
                moveOf('b', 7, 'b', 8),
                moveOf('d', 3, 'h', 7),
                moveOf('b', 8, 'c', 8),
                moveOf('f', 7, 'g', 6),
                moveOf('c', 8, 'e', 6)};

        Arrays.stream(moves).forEach(game::movePiece);
        assertEquals(DRAW, game.getGameStatus());
    }

    @Test
    void kingAndBishopVsKing(){
        Chessboard ch = new Chessboard();
        ch.set(new King(WHITE, makeCoord('f', 5)));
        ch.set(new Bishop(WHITE, 'e', 4));
        ch.set(new King(BLACK, 'e', 2));
        ch.set(new Pawn(BLACK, 'e', 5));
        Game g = new Game(ch);
        g.movePiece(moveOf('f', 5, 'e', 5));
        assertEquals(DRAW, g.getGameStatus());
    }

    @Test
    void sixMoveCheckmate(){
        Move[] moves = {
                moveOf('e', 2, 'e', 4),
                moveOf('e', 7, 'e', 5),
                moveOf('g', 1, 'f', 3),
                moveOf('f', 7, 'f', 6),
                moveOf('f', 1, 'c', 4),
                moveOf('c', 7, 'c', 6),
                moveOf('f', 3, 'e', 5),
                moveOf('f', 6, 'e', 5),
                moveOf('d', 1, 'h', 5),
                moveOf('e', 8, 'e', 7),
                moveOf('h', 5, 'e', 5)
        };
        Arrays.stream(moves).forEach(game::movePiece);
        assertEquals(WHITE_WON, game.getGameStatus());
    }
}
