package validator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NumericValidatorTest extends ValidatorTest{

    public NumericValidatorTest(){
        super(new NumericValidator(5,100));
    }

    @Test
    public void lowerValue(){
        assertFalse(validate("4"));
        assertFalse(validate("0"));
        assertFalse(validate("-100"));
    }

    @Test
    public void biggerValue(){
        assertFalse(validate("101"));
        assertFalse(validate("1000"));
    }

    @Test
    public void inRange(){
        assertTrue(validate("5"));
        assertTrue(validate("50"));
        assertTrue(validate("100"));
    }

    @Test
    public void notInteger(){
        assertFalse(validate("abc"));
        assertFalse(validate(" "));
        assertFalse(validate("\n"));
        assertFalse(validate("ABCDEFGH"));
    }

}
