package validator;

import game.Game;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameValidatorTest{

    private GameValidator validator;

    public GameValidatorTest(){
        this.validator = new GameValidator(new Game());
    }

    @Test
    void validMove(){
        assertTrue(validator.validate("A2 A3"));
    }

    @Test
    void invalidFirstCoordinates(){
        assertFalse(validator.validate("A0 B1"));
        assertFalse(validator.validate("1 H8"));
        assertFalse(validator.validate("H9 B1"));
    }

    @Test
    void invalidSecondCoordinates(){
        assertFalse(validator.validate("H2 B"));
        assertFalse(validator.validate("A1 ZZ"));
        assertFalse(validator.validate("C4 B9"));
    }
}
