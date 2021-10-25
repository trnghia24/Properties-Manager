package persistence;

import org.json.JSONObject;

// This class references code from this repo
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writeable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
