package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonFixer {
    public static JsonObject parse(String request) {
        JsonObject rootObj = (JsonObject) JsonParser.parseString(request);
        return rootObj;
    }
}
