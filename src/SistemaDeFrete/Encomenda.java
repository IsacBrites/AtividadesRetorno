package SistemaDeFrete;


import java.util.ArrayList;

public class Encomenda {
    private String codigo;
    private String origem;
    private String destino;
    private double distanciaKm;
    private double pesoKg;
    private Transportadora transportadoraEscolhida;
    private double valorFrete;
    private Status status;
    private ArrayList<Cotacao> cotacoes;

    public Encomenda(String codigo, String origem, String destino, double distanciaKm, double pesoKg) {
        this.codigo = codigo;
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.pesoKg = pesoKg;
        this.transportadoraEscolhida = null;
        this.valorFrete = 0.0;
        this.status = Status.PENDENTE;
        this.cotacoes = new ArrayList<>();

    }

    public void adicionarCotacao(Cotacao cotacao) {
        this.cotacoes.add(cotacao);
    }

    public Cotacao melhorPreco(){
        if(this.cotacoes.isEmpty()){
            return null;
        }

        Cotacao melhorPreco = cotacoes.getFirst();

        for(Cotacao cotacao : this.cotacoes){
            if (cotacao.getValorFrete() < melhorPreco.getValorFrete()){
                melhorPreco = cotacao;
            }
        }
        return melhorPreco;
    }

    public Cotacao melhorPrazo(){
        if(this.cotacoes.isEmpty()){
            return null;
        }
        Cotacao melhorPrazo = cotacoes.getFirst();

        for(Cotacao cotacao : this.cotacoes){
            if (cotacao.getPrazoEstimado() < melhorPrazo.getPrazoEstimado()){
                melhorPrazo = cotacao;
            }
        }
        return melhorPrazo;
    }


    public Cotacao melhorCustoBeneficio(){
        if(this.cotacoes.isEmpty()){
            return null;
        }
        Cotacao melhorCusto = cotacoes.getFirst();

        for(Cotacao cotacao : this.cotacoes){
            if (cotacao.custoBeneficio() < melhorCusto.custoBeneficio()){
                melhorCusto = cotacao;
        }
    }
    return melhorCusto;

    }

    public void escolherTransportadora(Cotacao cotacao){
        transportadoraEscolhida = cotacao.getTransportadora();
        valorFrete = cotacao.getValorFrete();
        status = Status.CONFIRMADO;
    }

    public Cotacao maiorPreco(){
        if(this.cotacoes.isEmpty()){
            return null;
        }

        Cotacao maiorPreco = cotacoes.getFirst();

        for(Cotacao cotacao : this.cotacoes){
            if (cotacao.getValorFrete() > maiorPreco.getValorFrete()){
                maiorPreco = cotacao;
            }
        }
        return maiorPreco;
    }


    public void exibirCotacoes(){

        for (Cotacao cotacao : this.cotacoes) {

            cotacao.exibir();

        }

    }





    public String getCodigo() {
        return codigo;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public Status getStatus() {
        return status;
    }

    public ArrayList<Cotacao> getCotacoes() {
        return cotacoes;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public Transportadora getTransportadoraEscolhida() {
        return transportadoraEscolhida;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public void setCotacoes(ArrayList<Cotacao> cotacoes) {
        this.cotacoes = cotacoes;
    }

    public void setPesoKg(double pesoKg) {
        this.pesoKg = pesoKg;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setTransportadoraEscolhida(Transportadora transportadoraEscolhida) {
        this.transportadoraEscolhida = transportadoraEscolhida;
    }

    public void setValorFrete(double valorFrete) {
        this.valorFrete = valorFrete;
    }
}
