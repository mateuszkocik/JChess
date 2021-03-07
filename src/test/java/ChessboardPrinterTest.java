import game.*;
import game.pieces.King;
import org.junit.jupiter.api.Test;

import static game.ChessboardPrinter.print;

public class ChessboardPrinterTest{

    @Test
    void standardChessboard(){
        print(ChessboardBuilder.buildStandardChessboard(), false);
    }

    @Test
    void standardChessboardReversed(){
        print(ChessboardBuilder.buildStandardChessboard(), true);
    }

    @Test
    void chessboardWithOnlyKings(){
        Chessboard chessboard = new Chessboard();
        chessboard.set(new King(Team.WHITE, new Coordinates('d', 5)));
        chessboard.set(new King(Team.BLACK, new Coordinates('e', 8)));
        print(chessboard,false);
    }
}
