package it.uniroma3.diadia;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Proprietà {
    
    static public final String propPath="diadia.properties";
    static public Properties prop;

    public Proprietà() {
        try {
            Proprietà.prop=new Properties();

            prop.load(new FileReader(propPath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static public String getProp(String nomeProp) {
        return prop.getProperty(nomeProp);
    }

}