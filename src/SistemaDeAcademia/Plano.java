package SistemaDeAcademia;

import java.util.ArrayList;

public class Plano {
    private String nome;
    private int duracaoMeses;
    private double valorMensal;
    private ArrayList<Treino> treinos;

    public Plano(String nome, int duracaoMeses, double valorMensal) {
        this.nome = nome;
        this.duracaoMeses = duracaoMeses;
        this.valorMensal = valorMensal;
        this.treinos = new ArrayList<>();
    }

    public void adicionarTreino(Treino treino) {
        this.treinos.add(treino);
    }

    public double valorTotal(){
        return valorMensal * duracaoMeses;
    }

    public void exibir(){
        System.out.println("Nome: " + nome);
        System.out.println("Valor mensal: " + valorMensal);
        System.out.println("Duração em meses: "+duracaoMeses);
        System.out.println("Valor total do plano: "+valorTotal());
        System.out.println("Treinos: "+treinos.toString());
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Treino> getTreinos() {
        return treinos;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public int getDuracaoMeses() {
        return duracaoMeses;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTreinos(ArrayList<Treino> treinos) {
        this.treinos = treinos;
    }

    public void setDuracaoMeses(int duracaoMeses) {
        this.duracaoMeses = duracaoMeses;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }
}
