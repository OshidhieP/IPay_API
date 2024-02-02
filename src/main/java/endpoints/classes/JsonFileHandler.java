package endpoints.classes;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.File;

public class JsonFileHandler {

    public static JSONObject readJsonFromFile(File file) {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(file);
            return (JSONObject) parser.parse(reader);
        } catch (Exception e) {
            e.printStackTrace();
            return new JSONObject();
        }
    }
}