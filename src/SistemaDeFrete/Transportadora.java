package SistemaDeFrete;

public class Transportadora {
    private String nome;
    private double precoBaseKm;
    private double precoBaseKg;
    private int prazoMedioDias;
    private double avaliacaoMedia;
    private int entregasRealizadas;
    private int entregasNoPrazo;

    public Transportadora(String nome, double precoBaseKm, double precoBaseKg, int prazoMedioDias, double avaliacaoMedia) {
        this.nome = nome;
        this.precoBaseKm = precoBaseKm;
        this.precoBaseKg = precoBaseKg;
        this.prazoMedioDias = prazoMedioDias;
        this.avaliacaoMedia = avaliacaoMedia;
        this.entregasRealizadas = 0;
        this.entregasNoPrazo = 0;
    }

    public double calcularFrete(double distanciaKm, double pesoKg){
        return (distanciaKm * precoBaseKg) + (pesoKg * precoBaseKm);
    }

    public void registrarEntrega(boolean noPrazo){
        entregasRealizadas++;
        if(noPrazo){
            entregasNoPrazo++;
        }
    }

    public double taxaPontualidade(){
        return (double) entregasNoPrazo/entregasRealizadas;
    }

    public void exibir(){
        System.out.println("Nome: " + nome);
        System.out.println("Preço por KM: " + precoBaseKm);
        System.out.println("Preço por KG: " + precoBaseKg);
        System.out.println("Prazo médio: " + prazoMedioDias);
        System.out.println("Avalição média: " + avaliacaoMedia);
        System.out.println("Taxa de pontualidade: "+taxaPontualidade());
    }

    @Override
    public String toString() {
        return "Nome: "+ nome;
    }

    public String getNome() {
        return nome;
    }

    public double getAvaliacaoMedia() {
        return avaliacaoMedia;
    }

    public double getPrecoBaseKg() {
        return precoBaseKg;
    }

    public double getPrecoBaseKm() {
        return precoBaseKm;
    }

    public int getEntregasNoPrazo() {
        return entregasNoPrazo;
    }

    public int getEntregasRealizadas() {
        return entregasRealizadas;
    }

    public int getPrazoMedioDias() {
        return prazoMedioDias;
    }

}
