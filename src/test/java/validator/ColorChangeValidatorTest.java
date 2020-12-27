package validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ColorChangeValidatorTest extends ValidatorTest{

    public ColorChangeValidatorTest(){
        super(new ColorChangeValidator());
    }

    @Test
    public void oneParameter(){
        assertFalse(validate("text"));
        assertFalse(validate("RED"));
        assertFalse(validate("command"));
    }

    @Test
    public void firstParameterWrong(){
        assertFalse(validate("wrong PURPLE"));
        assertFalse(validate("texxt YELLOW"));
        assertFalse(validate("bbackgrount WHITE"));
    }

    @Test
    public void secondParameterWrong(){
        assertFalse(validate("text wrong"));
        assertFalse(validate("background BLACKk"));
        assertFalse(validate("error REs"));
    }

    @Test
    public void bothParametersWrong(){
        assertFalse(validate("abc def"));
        assertFalse(validate("texxt ReeD"));
        assertFalse(validate("backgroundd asd"));
    }

    @Test
    public void tooManyParameters(){
        assertFalse(validate("abc def ghi"));
        assertFalse(validate("text YELLOW x"));
        assertFalse(validate("error text RED"));
        assertFalse(validate("background CYAN PURPLE"));
    }

    @Test
    public void correctCommand(){
        assertTrue(validate("error BLUE"));
        assertTrue(validate("background GREEN"));
        assertTrue(validate("text WHITE"));
    }

}
