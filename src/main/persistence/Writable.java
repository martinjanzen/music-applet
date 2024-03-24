package persistence;

import org.json.JSONObject;

// Represents an interface that has a method toJson which can be implemented by inheritors.
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
