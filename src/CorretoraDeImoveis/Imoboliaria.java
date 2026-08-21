package CorretoraDeImoveis;

import java.time.LocalDate;
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

    public Imovel buscarImovel (String codigo){
        for (Imovel imovel : imoveis){
            if (imovel.getCodigo().equals(codigo)){
                return imovel;
            }
        }
        return null;
    }

    public Cliente buscarCliente(String cpf){
        for (Cliente cliente : clientes){
            if (cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

    public Corretor buscarCorretor(String crei){
        for (Corretor corretor : corretores){
            if (corretor.getCreci().equals(crei)){
                return corretor;
            }
        }
        return null;
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

    public void agendarVisita(String codigoImovel, LocalDate data){
        Imovel imovel = buscarImovel(codigoImovel);
        if (imovel != null){
            imovel.registrarVisistas(data);
        }
    }

}
