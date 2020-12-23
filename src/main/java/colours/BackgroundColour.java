package colours;

public enum BackgroundColour{
    BLACK("\033[40m"),
    RED("\033[41m"),
    GREEN("\033[42m"),
    YELLOW("\033[43m"),
    BLUE("\033[44m"),
    PURPLE("\033[45m"),
    CYAN("\033[46m"),
    WHITE("\033[47m");

    String code;

    BackgroundColour(String code){
        this.code = code;
    }

    @Override
    public String toString(){
        return code;
    }
}
