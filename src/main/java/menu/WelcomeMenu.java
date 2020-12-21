package menu;

public class WelcomeMenu implements Menu{

    @Override
    public void showContent(){
        System.out.println("Welcome in the Jchess game. What would you like to do?\n");
        System.out.println("1. Play jchess game");
        System.out.println("2. Options");
        System.out.println("3. Guide");
        System.out.println("4. Quit\n");
    }

    @Override
    public void processCommand(String command){

        switch(command){
            case "1" : break;
            case "2" : break;
            case "3" : break;
            case "4" : System.exit(0); break;
            default : displayError();
        }
    }


}
