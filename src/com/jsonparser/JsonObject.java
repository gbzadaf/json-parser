package com.jsonparser;

import java.util.Map;

public class JsonObject extends JsonValue {

    public final Map<String, JsonValue> fields;

    public JsonObject(Map<String, JsonValue> fields) {
        this.fields = fields;
    }

    @Override
    public String toString() {
        return fields.toString();
    }

}
