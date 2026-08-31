package GestaoRestaurante;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Random;

public class TesteRestaurante {

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Random random = new Random();

        for (int i = 1; i <= 25; i++) {
            restaurante.cadastrarMesa(new Mesa(i, random.nextInt(10) + 1));
        }

        String[] nomesGarcons = {
                "Carlos", "Ana", "Roberto", "Julia",
                "Fernanda", "Lucas", "Beatriz", "Gabriel"
        };
        for (int i = 0; i < nomesGarcons.length; i++) {
            restaurante.cadastrarGarcom(new Garcom( nomesGarcons[i]));
        }

        ItemComanda[] cardapio = {
                new ItemComanda("Refrigerante", 1, 7.0, Categoria.BEBIDA),
                new ItemComanda("Suco Natural", 1, 9.0, Categoria.BEBIDA),
                new ItemComanda("Cerveja Artesanal", 1, 15.0, Categoria.BEBIDA),
                new ItemComanda("Picanha Grelhada", 1, 65.0, Categoria.PRATO_PRINCIPAL),
                new ItemComanda("Massa Carbonara", 1, 42.0, Categoria.PRATO_PRINCIPAL),
                new ItemComanda("Salmão ao Molho de Maracujá", 1, 58.0, Categoria.PRATO_PRINCIPAL),
                new ItemComanda("Pudim de Leite", 1, 12.0, Categoria.SOBREMESA),
                new ItemComanda("Petit Gateau", 1, 22.0, Categoria.SOBREMESA)
        };

        LocalTime horaAtual = LocalTime.of(11, 30);

        for (int i = 1; i <= 60; i++) {
            int numeroMesa = ((i - 1) % 25) + 1;
            horaAtual = horaAtual.plusMinutes(8);

            Comanda comanda = restaurante.abrirComanda(numeroMesa, horaAtual);

            if (comanda != null) {
                int qtdItensDiferentes = 3 + random.nextInt(6);
                for (int j = 0; j < qtdItensDiferentes; j++) {
                    ItemComanda modelo = cardapio[random.nextInt(cardapio.length)];
                    int quantidadePedida = 1 + random.nextInt(3);

                    ItemComanda novoItem = new ItemComanda(
                            modelo.getNome(),
                            quantidadePedida,
                            modelo.getPrecoUnitario(),
                            modelo.getCategoria()
                    );
                    restaurante.adicionarPedido(comanda.getNumero(), novoItem);
                }

                if (i <= 50) {
                    LocalTime horaFechamento = horaAtual.plusHours(1).plusMinutes(random.nextInt(30));
                    double gorjeta = 5.0 + (random.nextDouble() * 25.0);
                    restaurante.fecharComanda(comanda.getNumero(), horaFechamento, gorjeta);
                }
            }
        }


        System.out.println("==================================================");
        System.out.println("            PAINEL DE CONTROLE E RESULTADOS       ");
        System.out.println("==================================================\n");

        ArrayList<Mesa> disponiveis = restaurante.mesasDisponiveis();
        System.out.println("--> Mesas Disponíveis (" + disponiveis.size() + "):");
        for (Mesa mesa : disponiveis) {
            System.out.print("[" + mesa.getNumero() + "] ");
        }
        System.out.println("\n");

        ArrayList<Comanda> abertas = restaurante.comandasAbertas();
        System.out.println("--> Comandas em Aberto (" + abertas.size() + "):");
        for (Comanda c : abertas) {
            System.out.println("    - Comanda #" + c.getNumero() + " | Mesa: " + c.getMesa().getNumero() + " | Garçom: " + c.getGarcom().getNome());
        }
        System.out.println();

        System.out.printf("--> Faturamento Total: R$ %.2f\n", restaurante.faturamentoTotal());
        System.out.printf("--> Ticket Médio: R$ %.2f\n", restaurante.ticketMedio());
        System.out.println("--> Item Mais Pedido: " + restaurante.itemMaisPedido());

        Garcom destaque = restaurante.garcomMaisGorjetas();
        System.out.println("--> Garçom com Mais Gorjetas: " + (destaque != null ? destaque.getNome() + " (R$ " + String.format("%.2f", destaque.getGorjetasRecebidas()) + ")" : "Nenhum"));

        System.out.printf("--> Tempo Médio de Atendimento: %.1f horas\n\n", restaurante.tempoMedioAtendimento());

        System.out.println("==================================================");
        System.out.println("                 RELATÓRIO GERAL                  ");
        System.out.println("==================================================");
        restaurante.relatorioGeral();
    }
}