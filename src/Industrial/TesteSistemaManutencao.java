package Industrial;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TesteSistemaManutencao {
    public static void main(String[] args) {

        SistemaManutencao sistema = new SistemaManutencao();

        Random rand = new Random();

        ArrayList<Tecnico> tecnicos = new ArrayList<>();
        ArrayList<Equipamento> equipamentos = new ArrayList<>();
        ArrayList<Peca> pecas = new ArrayList<>();

        for (int i = 1; i<=8 ;i++){
            Tecnico tecnico = new Tecnico(i, "Técnico", "MAT", i);
            sistema.cadastrarTecnico(tecnico);
            tecnicos.add(tecnico);
        }

        for (int i = 1; i<=30 ;i++){
            Peca peca = new Peca("P"+i, "Peca", 50 +i, 10, 3);
            sistema.cadastrarPeca(peca);
            pecas.add(peca);
        }

        for (int i = 1; i<=40 ;i++){
            Equipamento equipamento = new Equipamento("EQP" + i, "Máquina " +i,  "Setor: "+ (i%3), LocalDate.now(), i*30, Status.OPERACIONAL);
            sistema.cadastrarEquipamento(equipamento);
            equipamentos.add(equipamento);
        }

        ArrayList<Equipamento> precisamPreventva = sistema.verificarPreventiva();

        System.out.println("Equipamentos precisando de preventiva: "+ precisamPreventva);

        ArrayList<OrdemServico> ordensAbertas = new ArrayList<>();

        LocalDate hoje = LocalDate.now();

        for (int i = 1; i<=60 ;i++){
            Tipo tipoOs = (i%3 == 0) ? Tipo.PREVENTIVA : Tipo.CORRETIVA;
            String codEquipamento = "EQP" + (rand.nextInt(40) + 1);

            LocalDate dataAbertura = hoje.minusDays(rand.nextInt(15)+1);

            OrdemServico os = sistema.abrirOrdem(codEquipamento, tipoOs, "Falha "+ i, dataAbertura);

            if (os != null) {
                ordensAbertas.add(os);

                Tecnico tecnicoAleatorio = tecnicos.get(rand.nextInt(8));
                sistema.atribuirTecnico(os.getNumero(), tecnicoAleatorio);

                Peca pecaAleatoria = pecas.get(rand.nextInt(30));

                os.adicionarPeca(pecaAleatoria, rand.nextInt(3)+1);
            }
        }

        System.out.println("RESULTADOS INDIVIDUAIS");

        System.out.println("Peças com estoque baixo/em falta:");
        for (Peca p : sistema.pecasEmFalta()) {
            System.out.println(" - " + p.getNome() + " (Estoque: " + p.getEstoque() + ")");
        }

        Equipamento piorEq = sistema.equipamentoComMaisFalhas();
        System.out.println("\n Equipamento mais problemático: " + (piorEq != null ? piorEq.getCodigo() : "Nenhum"));

        Tecnico melhorTec = sistema.tecnicoMaisProdutivo();
        System.out.println("Técnico mais produtivo: " + (melhorTec != null ? melhorTec.getNome() : "Nenhum"));

        System.out.printf("Tempo médio de resolução: %.1f dias\n", sistema.tempoMedioResolucao());

        System.out.printf("Custo total das manutenções: R$ %.2f\n", sistema.custoTotalManutencao());


        System.out.println("\n");
        System.out.println("RELATÓRIO GERAL DO SISTEMA ");

        sistema.relatorioGeral();
    }
}
