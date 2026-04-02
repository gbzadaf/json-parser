package com.jsonparser;

import java.util.ArrayList;
import java.util.List;

public class Lexer {

    private final String input;
    private int pos;

    public Lexer(String input) {
        this.input = input;
        this.pos = 0;
    }

    public List<Token> tokenize() {

        List<Token> tokens = new ArrayList<>();

        while (pos < input.length()) {
            char c = input.charAt(pos);

            if (Character.isWhitespace(c)) {
                pos++;
                continue;
            }


            switch (c) {
                case '{' -> {
                    tokens.add(new Token(TokenType.LBRACE, "{"));
                    pos++;
                }
                case '}' -> {
                    tokens.add(new Token(TokenType.RBRACE, "}"));
                    pos++;
                }
                case '[' -> {
                    tokens.add(new Token(TokenType.LBRACKET, "["));
                    pos++;
                }
                case ']' -> {
                    tokens.add(new Token(TokenType.RBRACKET, "]"));
                    pos++;
                }
                case ':' -> {
                    tokens.add(new Token(TokenType.COLON, ":"));
                    pos++;
                }
                case ',' -> {
                    tokens.add(new Token(TokenType.COMMA, ","));
                    pos++;
                }
                case '"' -> tokens.add(readString());
                default -> {
                    if (Character.isDigit(c) || c == '-') {
                        tokens.add(readNumber());
                    } else if (input.startsWith("true", pos)) {
                        tokens.add(new Token(TokenType.BOOLEAN, "true"));
                        pos += 4;
                    } else if (input.startsWith("false", pos)) {
                        tokens.add(new Token(TokenType.BOOLEAN, "false"));
                        pos += 5;
                    } else if (input.startsWith("null", pos)) {
                        tokens.add(new Token(TokenType.NULL, "null"));
                        pos += 4;
                    } else {
                        throw new RuntimeException("Caractere inesperado" + c + " na posicão " + pos);
                    }
                }

            }
        }
        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private Token readString() {
        pos++; //pula o abre aspas

        StringBuilder sb = new StringBuilder();

        while (pos < input.length() && input.charAt(pos) != '"') {
            if (input.charAt(pos) == '\\') {
                pos++; //pula a barra
                sb.append(input.charAt(pos)); //adiciona o caractere escapado

            } else {
                sb.append(input.charAt(pos));
            }
            pos++;
        }

        pos++; //pula o fecha aspas
        return new Token(TokenType.STRING, sb.toString());

    }

    private Token readNumber() {
        StringBuilder sb = new StringBuilder();

        while (pos < input.length()) {
            char c = input.charAt(pos);
            if (Character.isDigit(c) || c == '.' || c == '-' || c == 'e' || c == 'E') {
                sb.append(c);
                pos++;
            } else {
                break;
            }
        }
        return new Token(TokenType.NUMBER, sb.toString());
    }


}