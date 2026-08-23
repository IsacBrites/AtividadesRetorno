package CorretoraDeImoveis;

import java.time.LocalDate;
import java.util.ArrayList;

public class Imovel {
    private String codigo;
    private Tipo tipo;
    private String endereco;
    private double valorAnuncio;
    private double areaM2;
    private int quartos;
    private boolean disponivel;
    private String proprietario;
    private ArrayList<LocalDate> visitas;

    public Imovel(String codigo, Tipo tipo, String endereco, double valorAnuncio, double areaM2, int quartos , String proprietario) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.endereco = endereco;
        this.valorAnuncio = valorAnuncio;
        this.areaM2 = areaM2;
        this.quartos = quartos;
        this.proprietario = proprietario;
        this.disponivel = true;
        this.visitas = new ArrayList<>();
    }

    public void registrarVisistas(LocalDate data){
        this.visitas.add(data);
    }

    public int totalVisitas(){
        return this.visitas.size();
    }

    public double valorPorM2(){
        return valorAnuncio/areaM2;
    }

    public void exibir(){
        System.out.println("Codigo: " + this.codigo);
        System.out.println("Tipo: " + this.tipo);
        System.out.println("Endereco: " + this.endereco);
        System.out.println("ValorAnuncio: " + this.valorAnuncio);
        System.out.println("Quartos: " + this.quartos);
        System.out.println("Proprietario: " + this.proprietario);
        System.out.println("Disponivel: " + this.disponivel);
        System.out.println("Valor por metro²: "+valorPorM2());
    }

    @Override
    public String toString() {
        return "Tipo: "+ tipo;
    }

    public String getCodigo() {
        return codigo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public ArrayList<LocalDate> getVisitas() {
        return visitas;
    }

    public double getAreaM2() {
        return areaM2;
    }

    public double getValorAnuncio() {
        return valorAnuncio;
    }

    public int getQuartos() {
        return quartos;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getProprietario() {
        return proprietario;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}