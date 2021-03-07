package system;

import colors.Color;
import colors.ColoredElement;

import java.io.*;
import java.util.Properties;

public class ColorProperties{

    private static ColorProperties instance;
    private final String COLOR_FILENAME = ".jchess-color-settings";
    private final Properties properties;

    public ColorProperties(){
        this.properties = new Properties();
        load();
    }

    private void load(){
        InputStream propFileIS;
        try{
            propFileIS = new FileInputStream(new File(System.getProperty("user.home"), COLOR_FILENAME));
        }catch(IOException e){
            propFileIS = getClass().getClassLoader().getResourceAsStream("color.settings");
        }
        try{
            properties.load(propFileIS);
        }catch(IOException e){
            System.err.println("Reading color properties file failed");
            e.printStackTrace();
        }
    }

    public String getProperty(ColoredElement element){
        return properties.getProperty(element.toString());
    }

    public Color getElementColor(ColoredElement element){
        String colorString = getProperty(element);
        return Color.valueOf(colorString);
    }

    public void setProperty(ColoredElement element, Color color){
        properties.setProperty(element.toString(), color.toString());
        save();
    }

    public void save(){
        try{
            properties.store(new FileOutputStream(new File(System.getProperty("user.home"), COLOR_FILENAME)), null);
        }catch(IOException e){
            System.err.println("Saving color properties file failed");
            e.printStackTrace();
        }
    }

    public static ColorProperties getInstance(){
        if(instance == null){
            instance = new ColorProperties();
        }
        return instance;
    }
}
