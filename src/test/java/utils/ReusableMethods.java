package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ReusableMethods {
    static ZonedDateTime dateTime;
    static DateTimeFormatter formatter;
    public static Map<String, Object> readJsonTextFile(String path) {

        ObjectMapper mapper = new ObjectMapper();
        File fileObj = new File(path);

        // use try-catch block to convert JSON data into Map

        try {
            // read JSON data from file using fileObj and map it using ObjectMapper and TypeReference classes
            Map<String, Object> userData = mapper.readValue(fileObj, new TypeReference<Map<String, Object>>() {
            });
            return userData;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDateTime(String pattern) {
        dateTime = ZonedDateTime.now();
        formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }


}
