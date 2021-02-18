import system.ElementPainter;
import system.MenuController;

public class Main{

    public static void main(String[] args){
        ElementPainter.printDefaultColorCodes();
        MenuController.getInstance().process();
    }

}
