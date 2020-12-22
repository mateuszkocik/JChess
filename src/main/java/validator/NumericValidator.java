package validator;

public class NumericValidator implements CommandValidator{

    private int from;
    private int to;

    public NumericValidator(int from, int to){
        this.from = from;
        this.to = to;
    }

    @Override
    public boolean validate(String command){
        int value = 0;
        try{
            value = Integer.parseInt(command);
        }catch(NumberFormatException e){
            return false;
        }
        return to <= value && value <= to;
    }

    @Override
    public void displayError(){
        System.out.println("Wrong command, choose number from " + from + " to " + to + "!");
    }
}
