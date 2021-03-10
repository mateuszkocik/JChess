package command;

import system.CommandScanner;

public class QuitCommand implements Command{

    private final boolean confirmation;

    public QuitCommand(boolean confirmation){
        this.confirmation = confirmation;
    }

    @Override
    public void execute(){
        if(confirmation){
            System.out.println("Are you sure you want to quit? To confirm type \"yes\"!");
            String confirmation = CommandScanner.scanCommand();
            if(confirmation.equals("YES")){
                System.out.println("See you again :)!");
                System.exit(0);
            }
        }else{
            System.exit(0);
        }
    }
}
