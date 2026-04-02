package com.jsonparser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        String json = """
                { 
                    "nome": "João",
                    "idade": 25,
                    "ativo": true,
                    "notas": [9.5, 8.0, 7.5],
                    "endereco": {
                        "cidade": "Rio de Janeiro",
                        "cep": "12345-678"
                    }
                }
                       
                    
                """;

        Lexer lexer = new Lexer(json);
        List<Token> tokens = lexer.tokenize();

        for (Token token : tokens) {
            System.out.println(token);
        }

        Parser parser = new Parser(tokens);
        JsonValue result = parser.parse();

        System.out.println(result);


    }
}
