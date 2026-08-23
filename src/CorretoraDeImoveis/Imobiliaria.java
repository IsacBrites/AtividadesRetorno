package CorretoraDeImoveis;

import java.time.LocalDate;
import java.util.ArrayList;

public class Imobiliaria {
    private String nome;
    private ArrayList<Imovel> imoveis;
    private ArrayList<Corretor> corretores;
    private ArrayList<Cliente> clientes;
    private ArrayList<Proposta> propostas;
    private static int proximoIdProposta =1;

    public Imobiliaria(String nome){
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

    public Proposta fazerProposta(String cpfCliente, String codigoImovel, String creciCorretor, double valor){
        Cliente cliente = buscarCliente(cpfCliente);
        Imovel imovel = buscarImovel(codigoImovel);
        Corretor corretor = buscarCorretor(creciCorretor);

        Proposta proposta = new Proposta(proximoIdProposta, imovel, cliente, corretor, valor, LocalDate.now());
        cliente.adicionarProposta(proximoIdProposta);
        propostas.add(proposta);
        proximoIdProposta++;
        return proposta;

    }

    public void aceitarProposta(int id){
        for (Proposta proposta : propostas){
            if (proposta.getId() == id){
                Imovel imovelVendido =  proposta.getImovel();
                proposta.aceitar();
                proposta.getImovel().setDisponivel(false);
                proposta.getCorretor().registrarVenda();
                for (Proposta varedura : propostas){
                    if (varedura.getStatusProposta() == StatusProposta.PENDENTE&& imovelVendido.equals(varedura.getImovel())){
                        varedura.recusar();
                    }
                }
            }
        }
    }

    public ArrayList<Imovel> imoveisDisponivels(Tipo tipo){
        ArrayList<Imovel> imoveisDisponivels = new ArrayList<>();
        for (Imovel imovel : imoveis){
            if (imovel.getTipo().equals(tipo) && imovel.isDisponivel()){
                imoveisDisponivels.add(imovel);
            }
        }
        return imoveisDisponivels;
    }

    public Imovel imovelMaisVisitado(){
        Imovel imovelMaisVisitado = null;
        int contador = -1;
        for (Imovel imovel : imoveis){
            if (imovel.totalVisitas() > contador){
                contador = imovel.totalVisitas();
                imovelMaisVisitado = imovel;
            }
        }
        return imovelMaisVisitado;
    }

    public Corretor corretorQueMaisVende(){
        Corretor corretorQueMaisVende = null;
        int contador = -1;
        for (Corretor corretor : corretores){
            if (corretor.getVendasRealizadas() > contador){
                contador = corretor.getVendasRealizadas();
                corretorQueMaisVende = corretor;
            }
        }
        return corretorQueMaisVende;
    }

    public double comissaoTotalCorretor(String creci){
        double soma = 0;
        for (Proposta proposta : propostas){
            Corretor corretor = proposta.getCorretor();
            double valorPorposta = proposta.getValorProposto();
            if (corretor.getCreci().equals(creci) && proposta.getStatusProposta() == StatusProposta.ACEITA){
                soma += corretor.calcularComissao(valorPorposta);
            }
        }
        return soma;
    }

    public double taxaConversao(){
        ArrayList<Proposta> aceitas = new ArrayList<>();
        for (Proposta proposta : propostas){
            if (proposta.getStatusProposta() == StatusProposta.ACEITA){
                aceitas.add(proposta);
            }
        }
        if(propostas.isEmpty()){
            return 0;
        }
        return ((double) aceitas.size() /propostas.size()) * 100;
    }

    public double ticketMedioVendas(){
        double total =0;
        ArrayList<Proposta> aceitas = new ArrayList<>();
        for (Proposta proposta : propostas){
            if (proposta.getStatusProposta() == StatusProposta.ACEITA){
                aceitas.add(proposta);
                total += proposta.getValorProposto();
            }
        }
        if(aceitas.isEmpty()){
            return 0;
        }
        return total/aceitas.size();
    }


    public void relatorioGeral(){
        int disponivel = 0;
        int indisponivel = 0;
        for (Imovel imovel : imoveis){
            if (imovel.isDisponivel()){
                disponivel++;
            }
            else {
                indisponivel++;
            }
        }
        int pendente =0;
        int aceita = 0;
        int recusada = 0;

        for (Proposta proposta : propostas){
            if (proposta.getStatusProposta() == StatusProposta.PENDENTE){
                pendente++;
            }
            else if (proposta.getStatusProposta() == StatusProposta.ACEITA){
                aceita++;
            }
            else {
                recusada++;
            }
        }
        String nomeCorretor = (corretorQueMaisVende() != null) ? corretorQueMaisVende().getNome() : "Nenhum";
        String codigoImovel = (imovelMaisVisitado() != null) ? imovelMaisVisitado().getCodigo() : "Nenhum";

        System.out.println("Imóveis disponíveis: " + disponivel);
        System.out.println("Imóveis indisponíveis: " + indisponivel);
        System.out.println("Total de propostas " + propostas.size() + " | Aceitas: " + aceita + " | Recusada: " + recusada + " | Pendente: " + pendente);
        System.out.println("Corretor destaque: "+nomeCorretor);
        System.out.println("Imoóvel mais visitado: " + codigoImovel);
        System.out.println("Taxa de conversão: " + taxaConversao()+"%");
        System.out.println("Ticket médio: "+ticketMedioVendas());
    }
}
