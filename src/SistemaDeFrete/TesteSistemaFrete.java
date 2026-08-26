package SistemaDeFrete;

import java.util.Random;

public class TesteSistemaFrete {
    public static void main(String[] args) {
        SistemaFrete sistema = new SistemaFrete();
        Random random = new Random();


        Transportadora t1 = new Transportadora("ExpressLog", 1.5, 0.8, 2, 4.8);
        Transportadora t2 = new Transportadora("RapidoFrete", 2.0, 1.0, 1, 3.9);
        Transportadora t3 = new Transportadora("BrasilCarga", 1.0, 0.5, 5, 4.2);
        Transportadora t4 = new Transportadora("EcoEntrega", 1.2, 0.6, 4, 4.7);
        Transportadora t5 = new Transportadora("AlfaTrans", 1.8, 0.9, 2, 3.5);
        Transportadora t6 = new Transportadora("MegaLogistica", 0.9, 0.4, 6, 4.0);
        Transportadora t7 = new Transportadora("PontoAPonto", 2.2, 1.2, 1, 4.9);
        Transportadora t8 = new Transportadora("TransSul", 1.4, 0.7, 3, 4.1);

        sistema.cadastrarTransportadora(t1);
        sistema.cadastrarTransportadora(t2);
        sistema.cadastrarTransportadora(t3);
        sistema.cadastrarTransportadora(t4);
        sistema.cadastrarTransportadora(t5);
        sistema.cadastrarTransportadora(t6);
        sistema.cadastrarTransportadora(t7);
        sistema.cadastrarTransportadora(t8);

        for (int i = 1; i <= 50; i++) {
            String codigo = String.format("ENC-%03d", i);
            double distancia = 50.0 + random.nextDouble() * 950.0;
            double peso = 1.0 + random.nextDouble() * 49.0;

            Encomenda encomenda = new Encomenda(codigo, "Origem-" + i, "Destino-" + i, distancia, peso);
            sistema.adicionarEncomenda(encomenda);
        }

        for (Encomenda encomenda : sistema.getEncomendas()) {
            sistema.solicitarCotacao(encomenda);
        }

        int totalEncomendas = sistema.getEncomendas().size();
        for (int i = 0; i < totalEncomendas; i++) {
            Encomenda encomenda = sistema.getEncomendas().get(i);
            if (i < totalEncomendas / 2) {
                sistema.processarEncomenda(encomenda, Criterio.PRECO);
            } else {
                sistema.processarEncomenda(encomenda, Criterio.CUSTO_BENEFICIO);
            }
        }

        for (int i = 0; i < totalEncomendas; i++) {
            Encomenda encomenda = sistema.getEncomendas().get(i);
            boolean noPrazo = (i < totalEncomendas * 0.85); // 85% das entregas dentro do prazo
            sistema.finalizarEntrega(encomenda.getCodigo(), noPrazo);
        }

        System.out.println("==================================================");
        System.out.println("       RELATÓRIO GERAL DO SISTEMA DE FRETE        ");
        System.out.println("==================================================");
        System.out.println("Total de encomendas processadas: " + totalEncomendas);

        Transportadora maisEscolhida = sistema.transportadoraMaisEscolhida();
        System.out.println("Transportadora mais escolhida: " +
                (maisEscolhida != null ? maisEscolhida.getNome() : "Nenhuma"));

        Transportadora maisPontual = sistema.transportadoraMaisPontual();
        System.out.println("Transportadora mais pontual: " +
                (maisPontual != null ? maisPontual.getNome() + " (" + String.format("%.1f", maisPontual.taxaPontualidade()) + "%)" : "Nenhuma"));

        System.out.printf("Economia total gerada pelo sistema: R$ %.2f%n", sistema.economiaTotal());
        System.out.printf("Custo médio do frete por encomenda: R$ %.2f%n", sistema.custoMedioFrete());

    }
}