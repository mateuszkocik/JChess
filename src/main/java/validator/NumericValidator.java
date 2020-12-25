package validator;

public class NumericValidator implements CommandValidator{

    private final int from;
    private final int to;

    public NumericValidator(int from, int to){
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean validate(String command){
        int value;
        try{
            value = Integer.parseInt(command);
        }catch(NumberFormatException e){
            return false;
        }
        return from <= value && value <= to;
    }

    @Override
    public String getError(){
        return "Wrong command, choose number from " + from + " to " + to + "!";
    }

}
