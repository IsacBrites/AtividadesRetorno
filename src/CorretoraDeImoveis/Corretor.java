package CorretoraDeImoveis;

import java.util.ArrayList;

public class Corretor {
    private int id;
    private String nome;
    private String creci;
    private double comissaoPercentual;
    private ArrayList<Imovel> imoveisCarteira;
    private int vendasRealizadas;

    public Corretor(int id, String nome, String creci, double comissaoPercentual) {
        this.id = id;
        this.nome = nome;
        this.creci = creci;
        this.comissaoPercentual = comissaoPercentual;
        this.imoveisCarteira = new ArrayList<>();
        this.vendasRealizadas = 0;
    }

    public void adicionarImovel(Imovel imovel){
        imoveisCarteira.add(imovel);
    }

    public void registrarVenda(){
        vendasRealizadas++;
    }

    public double calcularComissao(double valorVenda){
        return (valorVenda *  comissaoPercentual)/100;
    }

    public void exibir(){
        System.out.println("ID: " + id);
        System.out.println("Nome: " + nome);
        System.out.println("Creci: " + creci);
        System.out.println("Comissão percentual: " + comissaoPercentual);
        System.out.println("Vendas realizadas: " + vendasRealizadas);
        System.out.println("Imóveis na carteira: "+imoveisCarteira.toString());
    }



    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Imovel> getImoveisCarteira() {
        return imoveisCarteira;
    }

    public double getComissaoPercentual() {
        return comissaoPercentual;
    }

    public int getVendasRealizadas() {
        return vendasRealizadas;
    }

    public String getCreci() {
        return creci;
    }
}
