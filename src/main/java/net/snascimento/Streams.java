package net.snascimento;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        .collect(toList());
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
        .collect(Collectors.groupingBy(Pessoa::getIdade));
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

  }

}
