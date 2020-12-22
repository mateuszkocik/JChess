package validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumericValidatorTest{

    private CommandValidator validator;

    @BeforeEach
    public void init(){
        validator = new NumericValidator(5,100);
    }

    @Test
    public void lowerValue(){
        assertFalse(validator.validate("4"));
        assertFalse(validator.validate("0"));
        assertFalse(validator.validate("-100"));
    }

    @Test
    public void biggerValue(){
        assertFalse(validator.validate("101"));
        assertFalse(validator.validate("1000"));
    }

    @Test
    public void inRange(){
        assertTrue(validator.validate("5"));
        assertTrue(validator.validate("50"));
        assertTrue(validator.validate("100"));
    }

    @Test
    public void notInteger(){
        assertFalse(validator.validate("abc"));
        assertFalse(validator.validate(" "));
        assertFalse(validator.validate("\n"));
        assertFalse(validator.validate("ABCDEFGH"));
    }

}
