package persistence;


import com.sun.tools.javah.Mangle;
import model.Property;
import model.ManagementList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ManagementList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseManagementList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses workroom from JSON object and returns it
    private ManagementList parseManagementList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        ManagementList list = new ManagementList();
        addProperties(list, jsonObject);
        return list;
    }

    // MODIFIES: list
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addProperties(ManagementList list, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("properties");
        for (Object json : jsonArray) {
            JSONObject nextProperty = (JSONObject) json;
            addProperty(list, nextProperty);
        }
    }

    // MODIFIES: list
    // EFFECTS: parses thingy from JSON object and adds it to workroom
    private void addProperty(ManagementList list, JSONObject jsonObject) {
        String address = jsonObject.getString("address");
        Double price = jsonObject.getDouble("price");
        Integer capacity = jsonObject.getInt("capacity");
        Boolean status = jsonObject.getBoolean("status");
        Boolean paid = jsonObject.getBoolean("paid?");
        Property p = new Property();
        p.setAddress(address);
        p.setPrice(price);
        p.setCapacity(capacity);
        p.setStatus(status);
        p.setPaid(paid);
        list.addProperty(p);
    }
}