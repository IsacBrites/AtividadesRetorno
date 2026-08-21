package CorretoraDeImoveis;

import java.util.ArrayList;

public class Imoboliaria {
    private String nome;
    private ArrayList<Imovel> imoveis;
    private ArrayList<Corretor> corretores;
    private ArrayList<Cliente> clientes;
    private ArrayList<Proposta> propostas;
    private static int proximoIdProposta =1;

    public Imoboliaria(String nome){
        this.nome = nome;
        this.imoveis = new ArrayList<>();
        this.corretores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.propostas = new ArrayList<>();
    }

    public void cadastrarImovel(Imovel imovel){
        this.imoveis.add(imovel);
    }
    public void cadastrarCorretor(Corretor corretor){
        this.corretores.add(corretor);
    }
    public void cadastrarCliente(Cliente cliente){
        this.clientes.add(cliente);
    }

}
