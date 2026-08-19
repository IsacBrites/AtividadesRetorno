package ControleDeVoo;

public class Aeronave {
    private String modelo;
    private int capacidadeEconomica;
    private int capacidadeExecutiva;
    private double capacidadeCarga;

    public Aeronave(String modelo, int capacidadeEconomica, int capacidadeExecutiva, double capacidadeCarga) {
        this.modelo = modelo;
        this.capacidadeEconomica = capacidadeEconomica;
        this.capacidadeExecutiva = capacidadeExecutiva;
        this.capacidadeCarga = capacidadeCarga;
    }

    public int capacidadeTotal(){
        return  capacidadeEconomica + capacidadeExecutiva;
    }

    public double getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public double getCapacidadeEconomica() {
        return capacidadeEconomica;
    }

    public double getCapacidadeExecutiva() {
        return capacidadeExecutiva;
    }

    public String getModelo() {
        return modelo;
    }
}
