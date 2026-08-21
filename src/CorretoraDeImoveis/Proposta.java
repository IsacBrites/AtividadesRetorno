package CorretoraDeImoveis;

import java.time.LocalDate;

public class Proposta {
    private int id;
    private Imovel imovel;
    private Cliente cliente;
    private Corretor corretor;
    private double valorProposto;
    private LocalDate data;
    private StatusProposta statusProposta;

    public Proposta(int id, Imovel imovel, Cliente cliente, Corretor corretor, double valorProposto, LocalDate data) {
        this.id = id;
        this.imovel = imovel;
        this.cliente = cliente;
        this.corretor = corretor;
        this.valorProposto = valorProposto;
        this.data = data;
        this.statusProposta = StatusProposta.PENDENTE;

    }

    public void aceitar (){
        this.statusProposta = StatusProposta.ACEITA;
    }
    public void recusar (){
        this.statusProposta = StatusProposta.RECUSADA;
    }

    public double percentualDoValorPedido(){
        return (valorProposto/imovel.getValorAnuncio()) * 100;
    }

    public void exibir(){
        System.out.println("ID: " + this.id);
        System.out.println("Cliente: " + this.cliente.getNome());
        System.out.println("Tipo do imovel: " + imovel.getTipo());
        System.out.println("Corretor: " + corretor.getNome());
        System.out.println("Data: " + this.data);
        System.out.println("Valor do pedido: " + this.valorProposto);
        System.out.println("Status: " + this.statusProposta.toString());
    }

    public int getId() {
        return id;
    }

    public LocalDate getData() {
        return data;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Corretor getCorretor() {
        return corretor;
    }

    public double getValorProposto() {
        return valorProposto;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public StatusProposta getStatusProposta() {
        return statusProposta;
    }
}
