package com.jsonparser;

public class JsonNumber extends JsonValue {

    public final double value;

    public JsonNumber(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
