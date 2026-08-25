package SistemaDeAcademia;

import java.time.LocalDate;

public class AvaliacaoFisica {
    private LocalDate data;
    private double peso;
    private double percentualDeGordura;
    private double massaMuscular;
    private double altura;

    public AvaliacaoFisica(LocalDate data, double peso) {
        this.data = data;
        this.peso = peso;
        this.percentualDeGordura = percentualDeGordura;
        this.massaMuscular = massaMuscular;
        this.altura = altura;
    }

    public double calcularIMC(){
        return peso/(altura * altura);
    }

    public void exibir(){
        System.out.println("Data da avaliação: " + data);
        System.out.println("Peso: " + peso);
        System.out.println("Percentual de gordura: " + percentualDeGordura);
        System.out.println("Massa muscular: " + massaMuscular);
        System.out.println("Altura: " + altura);
        System.out.println("IMC: " + calcularIMC());
    }

    public LocalDate getData() {
        return data;
    }

    public double getPeso() {
        return peso;
    }

    public double getPercentualDeGordura() {
        return percentualDeGordura;
    }

    public double getAltura() {
        return altura;
    }

    public double getMassaMuscular() {
        return massaMuscular;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setPercentualDeGordura(double percentualDeGordura) {
        this.percentualDeGordura = percentualDeGordura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setMassaMuscular(double massaMuscular) {
        this.massaMuscular = massaMuscular;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
