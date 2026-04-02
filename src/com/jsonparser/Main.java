package com.jsonparser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String json = "{\"nome\": \"João\", \"idade\": 25, \"ativo\": true}";

        Lexer lexer = new Lexer(json);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

    }
}
