package chessboard;

import game.Board;
import game.Coordinates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest{

    private Board<Integer> board;
    private final Coordinates a1 = new Coordinates('a', 1);

    @BeforeEach
    public void initializeBoard(){
        board = new Board<>(Integer.class);
    }

    @Test
    public void newBoard(){
        assertNotNull(board);
    }

    @Test
    public void getSetTest(){
        assertEquals(board.get(a1), Optional.empty());
        board.set(a1, 1);
        assertEquals((int) board.get(a1).get(), 1);
    }

    @Test
    public void removeTest(){
        board.set(a1, 1);
        assertEquals((int) board.get(a1).get(), 1);
        board.remove(a1);
        assertEquals(board.get(a1), Optional.empty());
    }

}
