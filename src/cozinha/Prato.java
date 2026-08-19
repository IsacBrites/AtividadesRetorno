package cozinha;

import java.util.ArrayList;

public class Prato {
    private int idPrato;
    private String nome;
    private Chef chef;
    private String categoria;
    private ArrayList<String> ingredientesPrincipais;
    private int tempoPreparoMinutos;
    private ArrayList<Avaliacao> avaliacoes;

    public Prato(int idPrato, String nome, Chef chef, String categoria, int tempoPreparoMinutos) {
        this.idPrato = idPrato;
        this.nome = nome;
        this.chef = chef;
        this.categoria = categoria;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
        this.avaliacoes = new ArrayList<>();
        this.ingredientesPrincipais = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Prato{" +
                "id=" + idPrato +
                ", nome='" + nome + '\'' +
                ", chef=" + chef.getNomeChef() +
                ", categoria='" + categoria + '\'' +
                ", tempoPreparoMinutos=" + tempoPreparoMinutos +
                ", notaMedia=" + notaMedia() +
                '}';
    }

    public void adicionarIngrediente(String ingrediente) {
        ingredientesPrincipais.add(ingrediente);
    }

    public void receberNota(Jurado j, double nota){
        avaliacoes.add(new Avaliacao(j, nota));
        j.registrarAvaliacao();
    }

    public double notaMedia(){
        if(avaliacoes.isEmpty()){
            return 0;
        }
        double soma = 0;
        for(Avaliacao av: avaliacoes){
            soma += av.getNota();
        }
        return soma/avaliacoes.size();
    }

    public double notaMaxima(){
        double maiorNota= 0;
        for(Avaliacao av: avaliacoes){
            if(av.getNota() > maiorNota){
                maiorNota = av.getNota();
            }
        }
        return maiorNota;
    }

    public double notaMinima(){
        double menorNota= avaliacoes.isEmpty() ? 0 : avaliacoes.getFirst().getNota();
        for(Avaliacao av: avaliacoes){
            if(av.getNota() < menorNota){
                menorNota = av.getNota();
            }
        }
        return menorNota;
    }

    public int totalAvaliacoes(){
        return avaliacoes.size();
    }

    public void exibir(){
        System.out.println("Id prato: " + idPrato);
        System.out.println("Nome do prato: " + nome);
        System.out.println("Categoria do prato: " + categoria);
        System.out.println("Tempo de preaparo: " + tempoPreparoMinutos);
        System.out.println("Total de avaliacoes: " + totalAvaliacoes());
        System.out.println("Média nota: " + notaMedia());
        System.out.println("Maior nota: " + notaMaxima());
        System.out.println("Menor nota: " + notaMinima());
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public ArrayList<String> getIngredientesPrincipais() {
        return ingredientesPrincipais;
    }

    public Chef getChef() {
        return chef;
    }

    public int getIdPrato() {
        return idPrato;
    }
    public String getCategoria() {
        return categoria;
    }
    public int getTempoPreparoMinutos() {
        return tempoPreparoMinutos;
    }

}
