package colors;

import java.io.IOException;
import java.util.Properties;

public class ColorFormatter{

    private Properties colorProperties;

    private BackgroundColor backgroundColor;
    private FontColor textColor;
    private FontColor errorColor;

    private final String colorsFileName = "color.settings";

    public ColorFormatter(){
        this.colorProperties = new Properties();
        try{
            this.colorProperties.load(getClass().getClassLoader().getResourceAsStream(colorsFileName));
        }catch(IOException | NullPointerException e){
            System.err.println("Problem with color configuration file");
            System.exit(1);
        }
        this.backgroundColor = BackgroundColor.valueOf(getValueFromProperties("background"));
        this.textColor = FontColor.valueOf(getValueFromProperties("text"));
        this.errorColor = FontColor.valueOf(getValueFromProperties("error"));
    }

    public void displayDefaultColors(){
        System.out.print(textColor);
        System.out.print(backgroundColor);
    }

    public String getColorText(String text, FontColor fontColor){
        StringBuilder sb = new StringBuilder(fontColor.toString());
        sb.append(backgroundColor.toString());
        sb.append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getTextWithBackground(String text, BackgroundColor backgroundColor){
        StringBuilder sb = new StringBuilder(backgroundColor.toString());
        sb.append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getColorTextWithBackground(String text, FontColor fontColor, BackgroundColor backgroundColor){
        StringBuilder sb = new StringBuilder(fontColor.toString());
        sb.append(backgroundColor.toString());
        sb.append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    private void appendDefaultColors(StringBuilder sb){
        sb.append(textColor.toString());
        sb.append(backgroundColor.toString());
    }

    private String getValueFromProperties(String key){
        return colorProperties.getProperty(key);
    }

}
