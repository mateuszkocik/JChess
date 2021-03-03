package game;

import java.lang.reflect.Array;
import java.util.Optional;

import static game.BoardParameters.*;

public class Board<T>{
    private final T[][] board;

    @SuppressWarnings("unchecked")
    public Board(Class<T> clazz){
        this.board = (T[][]) Array.newInstance(clazz, SIZE, SIZE);
    }

    public void set(Coordinates coordinates, T value){
        board[getX(coordinates)][getY(coordinates)] = value;
    }

    public Optional<T> get(Coordinates c){
        if (!c.isValid()) return Optional.empty();
        T val = board[getX(c)][getY(c)];
        return val == null ? Optional.empty() : Optional.of(val);
    }

    public void remove(Coordinates c){
        board[getX(c)][getY(c)] = null;
    }

    private int getX(Coordinates c){
        return c.getFile() - FROM_FILE;
    }

    private int getY(Coordinates c){
        return c.getRank() - FROM_RANK;
    }

}
