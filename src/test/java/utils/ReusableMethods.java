package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.github.javafaker.Faker;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Normalizer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class ReusableMethods {

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

}
