package net.snascimento;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class Streams {

  public static void main(String[] args) {

    List<String> nomes = Arrays.asList("Joao", "Paulo", "Rafaela", "Renato");
    List<String> filteredNomes = nomes.stream()
        .filter(nome -> nome.startsWith("R"))
        .toList();
    // filteredNomes vai ter apenas Rafaela e Renato

    filteredNomes.forEach(System.out::println);
    System.out.println();

    List<Integer> numeros = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> numerosMap = numeros.stream()
        .map(numero -> numero * 2)
        .toList();
    // numerosMap vai conter 2, 4, 6, 8, 10

    numerosMap.forEach(System.out::println);
    System.out.println();

    List<Integer> numerosUnsorted = Arrays.asList(5, 3, 1, 4, 2);
    List<Integer> numerosSorted = numerosUnsorted.stream()
        .sorted()
        .toList();
    // numerosSorted vai conter 1, 2, 3, 4, 5

    numerosUnsorted.forEach(System.out::println);
    numerosSorted.forEach(System.out::println);
    System.out.println();

    List<Pessoa> pessoas = Arrays.asList(
        new Pessoa("Joao", 27),
        new Pessoa("Paulo", 27),
        new Pessoa("Renato", 22),
        new Pessoa("Rafaela", 26),
        new Pessoa("Rodolfo", 26),
        new Pessoa("Manoel", 28)
    );
    Map<Integer, List<Pessoa>> pessoasByIdade = pessoas.stream()
        .collect(groupingBy(Pessoa::getIdade));
    //pessoasByIdade vai ter um Map como abaixo
    // [22, [Pessoa{nome='Renato', idade=22}] ]
    // [26, [Pessoa{nome='Rafaela', idade=26}, Pessoa{nome='Rodolfo', idade=26}] ]
    // [27, [Pessoa{nome='Joao', idade=27}, Pessoa{nome='Paulo', idade=27}] ]
    // [28, [Pessoa{nome='Manoel', idade=28}] ]

    for (Map.Entry<Integer, List<Pessoa>> pessoa : pessoasByIdade.entrySet()) {
      System.out.println(pessoa.getKey());
      System.out.println(pessoa.getValue());
    }
    System.out.println();

    List<Integer> numerosOriginal = Arrays.asList(1, 2, 3, 4, 5);
    int reducaoSoma = numerosOriginal.stream()
        .reduce(0, (a, b) -> a + b);
    // a variável reducaoSoma vai ter valor 15

    System.out.println(reducaoSoma);
    System.out.println();

    List<String> nomesLista = Arrays.asList("Joao", "Paulo", "Rafaela", "Renato");
    nomesLista.parallelStream()
        .forEach(nome -> System.out.println("Ola, " + nome + "!"));
    // Os nomes são listados 'fora da ordem' por estarem sendo executados em paralelo

    List<List<Integer>> numerosFlatMap = Arrays.asList(
        Arrays.asList(1, 2, 3),
        Arrays.asList(4, 5, 6),
        Arrays.asList(7, 8, 9)
    );
    List<Integer> numerosAchatados = numerosFlatMap.stream()
        .flatMap(Collection::stream)
        .toList();
    // as 3 listas de números passam a ser uma só stream
    // e são coletadas em uma nova lista
    // [1,2,3,4,5,6,7,8,9]

    System.out.println(numerosAchatados);

    String[] arrayDePalavras = {"Java", "Streams", "é", "a", "melhor", "API", "que", "tem", "em",
        "Java"};
    Stream<String> streamDePalavras = Arrays.stream(arrayDePalavras);
    Map<String, Long> contadorDePalavrasUnicas = streamDePalavras
        .map(linha -> linha.split("\\s+"))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(groupingBy(identity(), counting()));
    // Cria um map que conta as palavras únicas em um stream
    // {que, 1}, {a, 1}, {Java, 2}, {melhor, 1}, {em, 1}, {é, 1},
    // {API, 1}, {tem, 1}, {Streams, 1}

    for (Map.Entry<String, Long> letra : contadorDePalavrasUnicas.entrySet()) {
      System.out.println("{" + letra.getKey() + ", " + letra.getValue() + "}");
    }
    System.out.println();

    Stream<String> palavras = Stream.of("Java", "Streams", "é", "a", "melhor", "API", "que", "tem",
        "em", "Java");
    Map<String, Long> letrasParaContar =
        palavras.map(palavra -> palavra.split(""))
            .flatMap(Arrays::stream)
            .collect(groupingBy(identity(), counting()));
    // Cria um map que tem a quantidade de letras de um grupo de Strings
    // {A, 1}, {a, 6}, {e, 5}, {h, 1}, {I, 1}, {é, 1}, {J, 2}, {l, 1},
    // {m, 4}, {o, 1}, {P, 1}, {q, 1}, {r, 2}, {s, 1}, {S, 1}, {t, 2},
    // {u, 1}, {v, 2}

    for (Map.Entry<String, Long> letras : letrasParaContar.entrySet()) {
      System.out.print("{" + letras + "}, ");
    }
    System.out.println();


  }

}
