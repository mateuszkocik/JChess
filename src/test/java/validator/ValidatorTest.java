package validator;

public abstract class ValidatorTest{

    private CommandValidator validator;

    public ValidatorTest(CommandValidator validator){
        this.validator = validator;
    }

    public boolean validate(String command){
        return validator.validate(command);
    }
}
