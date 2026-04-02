package com.jsonparser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        /*String json = """
                { 
                    "nome": "João",
                    "idade": 25,
                    "ativo": true,
                    "notas": [9.5, 8.0, 7.5],
                    "endereco": {
                        "cidade": "Rio de Janeiro",
                        "cep": "12345-678"
                    }
                
                       
                    
                """;

        try {

            Lexer lexer = new Lexer(json);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            JsonValue result = parser.parse();

            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println("Erro ao fazer parse: " + e.getMessage());
        }

    } */

        // Teste 1 - falta fecha chaves
        testar("{\"nome\": \"João\"");

        // Teste 2 - falta o valor
        testar("{\"nome\": }");

        // Teste 3 - falta os dois pontos
        testar("{\"nome\" \"João\"}");
    }

    static void testar(String json) {
        System.out.println("Input: " + json);
        try {
            Lexer lexer = new Lexer(json);
            List<Token> tokens = lexer.tokenize();

            Parser parser = new Parser(tokens);
            JsonValue result = parser.parse();

            System.out.println("Resultado: " + result);
        } catch (RuntimeException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        System.out.println("---");
    }
}




