package Apartamento;

import java.time.LocalTime;

public class AreaComum {
    private String nome;
    private int capacidade;
    private double taxaDeUso;
    private LocalTime horarioAbertura;
    private LocalTime horarioFechamento;

    public AreaComum(String nome, int capacidade, double taxaDeUso,  LocalTime horarioAbertura, LocalTime horarioFechamento) {
        this.nome = nome;
        this.capacidade = capacidade;
        this.taxaDeUso = taxaDeUso;
        this.horarioAbertura = horarioAbertura;
        this.horarioFechamento = horarioFechamento;
    }

    @Override
    public String toString() {
        return "Área: " + nome;
    }

    public void exibir(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Capacidade: " + this.capacidade);
        System.out.println("Taxa de Uso: " + this.taxaDeUso);
        System.out.println("Horario Abertura: " + this.horarioAbertura);
        System.out.println("Horario Fechamento: " + this.horarioFechamento);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getCapacidade() {
        return capacidade;
    }
    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
    public double getTaxaDeUso() {
        return taxaDeUso;
    }
    public void setTaxaDeUso(double taxaDeUso) {
        this.taxaDeUso = taxaDeUso;
    }
    public LocalTime getHorarioAbertura() {
        return horarioAbertura;
    }
    public void setHorarioAbertura(LocalTime horarioAbertura) {
        this.horarioAbertura = horarioAbertura;
    }
    public LocalTime getHorarioFechamento() {
        return horarioFechamento;
    }
    public void setHorarioFechamento(LocalTime horarioFechamento) {
        this.horarioFechamento = horarioFechamento;
    }

}
