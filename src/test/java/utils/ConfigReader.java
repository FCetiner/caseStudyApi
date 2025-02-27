package utils;

import java.io.*;
import java.util.Properties;


public class ConfigReader {
    public static Properties properties;

    static {
        String path="configuration.properties";
        try {

            FileInputStream fis=new FileInputStream(path);
            properties=new Properties();
            properties.load(fis);
            fis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
    public static Reader readFile(String fileName) {
        try {
            Reader reader = new FileReader(System.getProperty("user.dir") + "/src/test/resources/" +fileName);
            return reader;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
