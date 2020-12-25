package validator;

public interface CommandValidator{

    boolean validate(String command);

    String getError();
}
