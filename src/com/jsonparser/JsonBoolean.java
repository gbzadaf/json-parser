package com.jsonparser;

public class JsonBoolean extends JsonValue {

    public final boolean value;

    public JsonBoolean(boolean value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
