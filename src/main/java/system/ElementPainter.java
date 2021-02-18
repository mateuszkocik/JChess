package system;

import colors.BackgroundColor;
import colors.Color;
import colors.ColoredElement;
import colors.FontColor;

import static colors.ColoredElement.*;

public class ElementPainter{

    private static final ColorProperties colorProperties = ColorProperties.getInstance();

    public static String colorText(String text, Color color){
        StringBuilder sb = new StringBuilder()
                .append(getFontCode(color))
                .append(getDefaultBackgroundCode())
                .append(text);
        appendDefaultColorCodes(sb);

        return sb.toString();
    }

    public static String colorBackground(String text, Color color){
        StringBuilder sb = new StringBuilder()
                .append(getBackgroundCode(color))
                .append(text);
        appendDefaultColorCodes(sb);

        return sb.toString();
    }

    public static String colorTextAndBackground(String text, Color fontColor, Color backgroundColor){
        StringBuilder sb = new StringBuilder()
                .append(getFontCode(fontColor))
                .append(getBackgroundCode(backgroundColor))
                .append(text);
        appendDefaultColorCodes(sb);

        return sb.toString();
    }

    public static String colorElement(String text, ColoredElement element){
        String propertyVal = colorProperties.getProperty(element);
        Color color = Color.valueOf(propertyVal);
        switch(element){
            case TEXT:
            case ERROR:
                return colorText(text, color);
            case BACKGROUND:
                return colorBackground(text, color);
        }
        return null;
    }

    private static String getFontCode(Color color){
        return FontColor.valueOf(color.toString()).getCode();
    }

    private static String getBackgroundCode(Color color){
        return BackgroundColor.valueOf(color.toString()).getCode();
    }

    private static String getDefaultTextCode(){
        return FontColor.valueOf(colorProperties.getProperty(TEXT)).getCode();
    }

    private static String getDefaultBackgroundCode(){
        return BackgroundColor.valueOf(colorProperties.getProperty(BACKGROUND)).getCode();
    }

    public static void printDefaultColorCodes(){
        System.out.print(getDefaultTextCode() + getDefaultBackgroundCode());
    }

    private static void appendDefaultColorCodes(StringBuilder sb){
        sb.append(getDefaultTextCode()).append(getDefaultBackgroundCode());
    }
}
