package validator;

public class GlobalCommandValidator implements CommandValidator{

    @Override
    public boolean validate(String command){
        return command.equals("quit") || command.equals("back");
    }

    @Override
    public String getError(){
        return null;
    }
}
