package validator;

import colors.FontColor;

public class ColorChangeValidator implements CommandValidator{

    @Override
    public boolean validate(String command){
        var parts = command.split(" ");
        return parts.length == 2 && stringIsProperty(parts[0]) && stringIsColor(parts[1]);
    }

    private boolean stringIsProperty(String s){
        s = s.toLowerCase();
        return s.equals("text") || s.equals("background") || s.equals("error");
    }

    private boolean stringIsColor(String s){
        var colorsList = FontColor.values();
        for(FontColor color : colorsList) if(s.toUpperCase().equals(color.toString())) return true;
        return false;
    }

    @Override
    public String getError(){
        return "Command must have format: [text/background/error] [color] e.g. text YELLOW";
    }
}
