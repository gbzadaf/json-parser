# json-parser

#  JSON Parser — Do Zero em Java

Parser de JSON construído do zero em Java, sem nenhuma biblioteca externa. O projeto implementa todas as etapas de um parser real: tokenização, análise sintática e representação de dados.

---

##  Arquitetura

```
String JSON → Lexer → Tokens → Parser → JsonValue
```

| Componente | Responsabilidade |
|---|---|
| `Lexer` | Lê a string caractere por caractere e gera tokens |
| `Parser` | Consome os tokens e monta a estrutura de dados |
| `JsonValue` | Classe base para os 6 tipos JSON suportados |

---

## Tipos suportados

- `JsonString`
- `JsonNumber`
- `JsonBoolean`
- `JsonNull`
- `JsonArray`
- `JsonObject`

---

## Exemplo de uso

```java
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

Parser parser = new Parser(tokens);
JsonObject obj = (JsonObject) parser.parse();

System.out.println(obj.fields.get("nome"));   // "João"
System.out.println(obj.fields.get("idade"));  // 25.0

JsonArray notas = (JsonArray) obj.fields.get("notas");
System.out.println(notas.values.get(0));      // 9.5
```

---

## Tratamento de erros

O parser identifica erros com mensagens claras e posição exata:

```
Input: {"nome": }
Erro: Erro na posição 3: '}' não é um valor JSON válido

Input: {"nome" "João"}
Erro: Erro na posição 2: esperado ':' mas encontrou uma string ('João')
```

---

## O que aprendi

- Como funciona um **Lexer** (tokenizador)
- Como implementar um **Parser recursivo descendente**
- Representação de tipos com **herança e polimorfismo**
- Tratamento de erros com **mensagens contextuais**