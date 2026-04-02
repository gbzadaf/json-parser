package com.jsonparser;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Parser {

    private final List<Token> tokens;
    private int pos;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.pos = 0;

    }

    private Token current() {
        return tokens.get(pos);
    }

    private Token consume(TokenType expected) {
        Token token = current();
        if (token.type != expected) {
            throw new RuntimeException(
                    "Esperado " + expected + " mas encontrou " + token.type + " ('" + token.value +"')");
        }
        pos++;
        return token;

    }
    public JsonValue parse() {
        JsonValue value = parseValue();
        consume(TokenType.EOF);
        return value;

    }

    private JsonValue parseValue() {
        Token token = current();

        return switch (token.type) {
            case STRING -> { pos++; yield new JsonString(token.value); }
            case NUMBER -> { pos++; yield new JsonNumber(Double.parseDouble(token.value)); }
            case BOOLEAN -> { pos++; yield new JsonBoolean(Boolean.parseBoolean(token.value)); }
            case NULL -> { pos++; yield new JsonNull(); }
            case LBRACE -> parseObject();
            case LBRACKET -> parseArray();
            default -> throw new RuntimeException("Token inesperado: " + token.type);
        };

    }

    private JsonObject parseObject() {
        consume(TokenType.LBRACE);
        Map<String, JsonValue> fields = new LinkedHashMap<>();

        while (current().type != TokenType.RBRACE) {
            String key = consume(TokenType.STRING).value;
            consume(TokenType.COLON);
            JsonValue value = parseValue();
            fields.put(key, value);

            if (current().type == TokenType.COMMA) {
                pos++;
            }
        }
        consume(TokenType.RBRACE);
        return new JsonObject(fields);
    }

    private JsonArray parseArray() {
        consume(TokenType.LBRACKET);
        List<JsonValue> values = new ArrayList<>();

        while (current().type != TokenType.RBRACKET) {
            values.add(parseValue());

            if (current().type == TokenType.COMMA) {
                pos++;
            }
        }
        consume(TokenType.RBRACKET);
        return new JsonArray(values);

    }

}
