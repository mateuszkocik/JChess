package colors;

import java.io.*;
import java.util.Properties;

import static colors.ColoredElement.*;

public class ColorFormatter{

    private final Properties colorProperties;
    private final String colorFileName = ".jchess-color-settings";

    public ColorFormatter(){
        this.colorProperties = new Properties();
        try{
            loadColorProperties();
        }catch(Exception e){
            System.err.println("Problem with opening color configuration file");
            e.printStackTrace();
        }
    }

    private void loadColorProperties() throws Exception{
        InputStream propFileIS;
        try{
            propFileIS = new FileInputStream(new File(System.getProperty("user.home"), colorFileName));
        }catch(IOException e){
            propFileIS = getClass().getClassLoader().getResourceAsStream("color.settings");
        }
        this.colorProperties.load(propFileIS);
    }

    public String getColoredText(String text, Color color){
        StringBuilder sb = new StringBuilder()
                .append(getFontCode(color))
                .append(getCodeFromProp(BACKGROUND))
                .append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getTextWithBackground(String text, Color color){
        StringBuilder sb = new StringBuilder()
                .append(getBackgroundCode(color))
                .append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getColoredTextWithBackground(String text, Color fontColor, Color backgroundColor){
        StringBuilder sb = new StringBuilder()
                .append(getFontCode(fontColor))
                .append(getBackgroundCode(backgroundColor))
                .append(text);
        appendDefaultColors(sb);

        return sb.toString();
    }

    public String getColoredError(String errorText){
        return getColoredText(errorText, Color.valueOf(getValueFromProperties(ERROR)));
    }

    private String getFontCode(Color color){
        return FontColor.valueOf(color.toString()).getCode();
    }

    private String getBackgroundCode(Color color){
        return BackgroundColor.valueOf(color.toString()).getCode();
    }

    private String getCodeFromProp(ColoredElement element){
        String value = getValueFromProperties(element);
        switch(element){
            case TEXT:
            case ERROR:
                return FontColor.valueOf(value).getCode();
            case BACKGROUND:
                return BackgroundColor.valueOf(value).getCode();
        }
        return null;
    }

    private String getValueFromProperties(ColoredElement element){
        return colorProperties.getProperty(element.toString());
    }

    public void displayDefaultColors(){
        System.out.print(getCodeFromProp(TEXT));
        System.out.print(getCodeFromProp(BACKGROUND));
    }

    private void appendDefaultColors(StringBuilder sb){
        sb
                .append(getCodeFromProp(TEXT))
                .append(getCodeFromProp(BACKGROUND));
    }

    public void setElementColor(ColoredElement element, Color color){
        colorProperties.setProperty(element.toString(), color.toString());
        saveInSettingsFile();
    }

    private void saveInSettingsFile(){
        try{
            colorProperties.store(new FileOutputStream(new File(System.getProperty("user.home"), colorFileName)), null);
        }catch(IOException e){
            System.err.println("Problem with saving color configuration file");
            e.printStackTrace();
        }
    }


}
