package com.utility;

import com.constants.Env;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    // read property files
    public static String readProperty(Env env , String propertyName)  {

        //System.out.println(System.getProperty("user.dir")); // path of my project
        File propFile = new File(System.getProperty("user.dir") + "\\config\\"+ env +".properties");
        FileReader fileReader = null;
        Properties properties = new Properties();
        try {
            fileReader = new FileReader(propFile);
            properties.load(fileReader);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        String value = properties.getProperty(propertyName.toUpperCase());
        return value;
    }
}
