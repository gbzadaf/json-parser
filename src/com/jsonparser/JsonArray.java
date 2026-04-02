package com.jsonparser;

import java.util.List;

public class JsonArray extends JsonValue {

    public final List<JsonValue> values;

    public JsonArray(List<JsonValue> values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return values.toString();
    }

}
