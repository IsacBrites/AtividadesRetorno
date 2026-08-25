package SistemaDeFrete;
//Crie uma classe SistemaFrete com:
//- Atributos: transportadoras (ArrayList<Transportadora>), encomendas (ArrayList<Encomenda>)
//- Método cadastrarTransportadora(Transportadora t)
//- Método solicitarCotacoes(Encomenda e) que:
//  - Para cada transportadora cadastrada
//  - Calcula frete e prazo estimado
//  - Cria cotação
//  - Adiciona à encomenda
//- Método processarEncomenda(Encomenda e, String criterio) que:
//  - Se criterio = "preco": escolhe melhor preço
//  - Se criterio = "prazo": escolhe melhor prazo
//  - Se criterio = "custoBeneficio": escolhe melhor relação
//  - Confirma escolha
//- Método finalizarEntrega(String codigoEncomenda, boolean noPrazo) que:
//  - Busca encomenda
//  - Registra entrega na transportadora escolhida
//- Método transportadoraMaisEscolhida() que:
//  - Conta quantas vezes cada transportadora foi escolhida
//  - Retorna a mais utilizada
//- Método transportadoraMaisPontual() que retorna maior taxa de pontualidade
//- Método economiaTotal() que:
//  - Para cada encomenda, compara valor escolhido com o mais caro cotado
//  - Soma a diferença (economia gerada)
//- Método custoMedioFrete() que calcula média dos fretes confirmados
//- Método relatorioGeral() que exibe:
//  - Total de encomendas processadas
//  - Transportadora mais utilizada
//  - Transportadora mais pontual
//  - Economia total gerada
//  - Custo médio de frete

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaFrete {
    private ArrayList<Transportadora> transportadoras;
    private ArrayList<Encomenda> encomendas;

    public void cadastrarTransportadora(Transportadora transportadora) {
        this.transportadoras.add(transportadora);
    }

    public void solicitarCotacao(Encomenda encomenda) {
        for (Transportadora transportadora : this.transportadoras) {
            double valorCalculado = transportadora.calcularFrete(encomenda.getDistanciaKm(), encomenda.getPesoKg());
            Cotacao cotacao = new Cotacao(transportadora, valorCalculado, transportadora.getPrazoMedioDias(), LocalDate.now());
            encomenda.adicionarCotacao(cotacao);
        }
    }
}
