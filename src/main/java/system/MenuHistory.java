package system;

import menu.Menu;
import menu.WelcomeMenu;

import java.util.Stack;

public class MenuHistory{

    private static MenuHistory instance;
    private final Stack<Menu> history;

    public MenuHistory(){
        this.history = new Stack<>();
    }

    public static MenuHistory getInstance(){
        if(instance == null){
            instance = new MenuHistory();
        }
        return instance;
    }

    public Menu getPreviousMenu(){
        return history.empty() ? new WelcomeMenu() : history.pop();
    }

    public void addMenuToHistory(Menu menu){
        history.push(menu);
    }

}
