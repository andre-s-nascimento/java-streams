package net.snascimento;

public class Pessoa {

  String nome;
  int idade;

  Pessoa(String nome, int idade){
    this.nome = nome;
    this.idade = idade;
  }

  public int getIdade() {
    return idade;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    return "Pessoa[" +
        "nome='" + nome + '\'' +
        ", idade=" + idade +
        ']';
  }
}
