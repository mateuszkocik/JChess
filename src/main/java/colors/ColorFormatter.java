package colors;

import java.io.IOException;
import java.util.Properties;

public class ColorFormatter{

    private final Properties colorProperties;

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

    public String getColoredText(String text, FontColor fontColor){
        StringBuilder sb = new StringBuilder(fontColor.getCode());
        sb.append(backgroundColor.getCode());
        sb.append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getTextWithBackground(String text, BackgroundColor backgroundColor){
        StringBuilder sb = new StringBuilder(backgroundColor.getCode());
        sb.append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getColoredTextWithBackground(String text, FontColor fontColor, BackgroundColor backgroundColor){
        StringBuilder sb = new StringBuilder(fontColor.getCode());
        sb.append(backgroundColor.getCode());
        sb.append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getColoredError(String errorText){
        return getColoredText(errorText,errorColor);
    }

    private void appendDefaultColors(StringBuilder sb){
        sb.append(textColor.getCode());
        sb.append(backgroundColor.getCode());
    }

    private String getValueFromProperties(String key){
        return colorProperties.getProperty(key);
    }

}
