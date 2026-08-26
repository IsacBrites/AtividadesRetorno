package SistemaDeFrete;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaFrete {
    private ArrayList<Transportadora> transportadoras;
    private ArrayList<Encomenda> encomendas;

    public SistemaFrete(){
        transportadoras = new ArrayList<>();
        encomendas = new ArrayList<>();
    }

    public void adicionarEncomenda(Encomenda encomenda) {
        encomendas.add(encomenda);
    }

    public void cadastrarTransportadora(Transportadora transportadora) {
        if (transportadoras == null) {
            return;
        }
        this.transportadoras.add(transportadora);
    }

    public void solicitarCotacao(Encomenda encomenda) {
        for (Transportadora transportadora : this.transportadoras) {
            double valorCalculado = transportadora.calcularFrete(encomenda.getDistanciaKm(), encomenda.getPesoKg());
            Cotacao cotacao = new Cotacao(transportadora, valorCalculado, transportadora.getPrazoMedioDias(), LocalDate.now());
            encomenda.adicionarCotacao(cotacao);
        }
    }

    public void processarEncomenda(Encomenda encomenda, Criterio criterio) {
        Cotacao cotacaoEscolhida = null;
        switch (criterio) {
            case PRECO:
                cotacaoEscolhida = encomenda.melhorPreco();
                break;
            case PRAZO:
                cotacaoEscolhida = encomenda.melhorPrazo();
                break;
            case CUSTO_BENEFICIO:
                cotacaoEscolhida =encomenda.melhorCustoBeneficio();
                break;
        }
        if (cotacaoEscolhida != null) {
            encomenda.escolherTransportadora(cotacaoEscolhida);
        }
    }

    public void finalizarEntrega(String codigoEncomenda, boolean noPrazo){
        for (Encomenda encomenda : this.encomendas) {
            if (encomenda.getCodigo().equals(codigoEncomenda)) {
                encomenda.getTransportadoraEscolhida().registrarEntrega(noPrazo);
                encomenda.setStatus(Status.ENTREGUE);
                return;
            }
        }
    }

    public Transportadora transportadoraMaisEscolhida(){
        Transportadora transportadoraVencedora = null;
        int recordeEscolhas = 0;
        if (this.transportadoras == null) {
            return null;
        }

        for (Transportadora transportadora : this.transportadoras) {
            int contadorEscolhida = 0;

            for (Encomenda encomenda : this.encomendas) {
                if (encomenda.getTransportadoraEscolhida().equals(transportadora)) {
                    contadorEscolhida++;

                }
            }
            if (contadorEscolhida > recordeEscolhas) {
                recordeEscolhas = contadorEscolhida;
                transportadoraVencedora = transportadora;
            }

        }
        return transportadoraVencedora;
    }

    public Transportadora transportadoraMaisPontual(){
        Transportadora transportadoraVencedora = null;
        double maiorTaxa = -1.0;
        for (Transportadora transportadora : this.transportadoras) {
            if (transportadora.taxaPontualidade()> maiorTaxa) {
                transportadoraVencedora = transportadora;
                maiorTaxa = transportadora.taxaPontualidade();
            }
        }
        return transportadoraVencedora;
    }

    public double economiaTotal(){
        double valorTotal = 0;
        for (Encomenda encomenda : this.encomendas) {
           if (encomenda.getStatus() != Status.PENDENTE) {
               valorTotal += encomenda.maiorPreco().getValorFrete() - encomenda.getValorFrete();

           }
        }
        return valorTotal;
    }

    public double custoMedioFrete(){
        double soma = 0.0;
        int contador = 0;
        for (Encomenda encomenda : this.encomendas) {
            if (encomenda.getStatus() != Status.PENDENTE) {
                soma += encomenda.getValorFrete();
                contador++;
            }
        }
        if (contador == 0){
            return 0.0;
        }
        return soma/contador;
    }

    public ArrayList<Encomenda> getEncomendas() {
        return encomendas;
    }

    public ArrayList<Transportadora> getTransportadoras() {
        return transportadoras;
    }
}
