package system;

import menu.Menu;
import menu.WelcomeMenu;

import java.util.Stack;

public class MenuHistory{

    private final Stack<Menu> history;

    public MenuHistory(){
        this.history = new Stack<>();
    }

    public Menu getPreviousMenu(){
        return history.empty() ? new WelcomeMenu() : history.pop();
    }

    public void addMenuToHistory(Menu menu){
        history.push(menu);
    }
}
