package utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class utility {
    public JSONObject jsonReader(String path) throws IOException, org.json.simple.parser.ParseException {
        String jsonBody = new String(Files.readAllBytes(Paths.get(path)));
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonBody);
    }
}
