package Apartamento;

import java.time.LocalDate;
import java.time.LocalTime;

public class TesteCondominio {
    public static void main(String[] args) {
        Condominio condominio = new Condominio("Condominio Teste");
        for (int i = 1; i <= 80; i++){
            Morador morador = new Morador("Moradador: "+i, i, i,"5531999999999", "Morador"+i + "@gmail.com");
            condominio.cadastrarMorador(morador);
        }
        AreaComum salao = new AreaComum("Salão", 100, 80.00, LocalTime.of(8, 0), LocalTime.of(23, 0));
        condominio.cadastrarAreaComum(salao);
        AreaComum churrasqueira = new AreaComum("Churraqueira", 20, 40.00, LocalTime.of(9, 0), LocalTime.of(22, 0));
        condominio.cadastrarAreaComum(churrasqueira);
        AreaComum piscina = new AreaComum("Piscina", 50, 20.00, LocalTime.of(8, 0), LocalTime.of(20, 0));
        condominio.cadastrarAreaComum(piscina);
        AreaComum quadra = new AreaComum("Quadra", 30, 30.00, LocalTime.of(8, 0), LocalTime.of(22, 0));
        condominio.cadastrarAreaComum(quadra);
        AreaComum espacoGourmet = new AreaComum("Espaço Gourmet", 30, 60.00, LocalTime.of(10, 0), LocalTime.of(23, 0));
        condominio.cadastrarAreaComum(espacoGourmet);
        AreaComum academia = new AreaComum("Academia", 25, 15.00,  LocalTime.of(6, 0), LocalTime.of(22, 0));
        condominio.cadastrarAreaComum(academia);

        condominio.gerarTaxasPendentes("Agosto", 500);
        condominio.gerarTaxasPendentes("Setembro", 500);
        condominio.gerarTaxasPendentes("Outubro", 500);

        for (int i = 1; i <= 56; i++){
            condominio.pagarTaxa(i, "Agosto");
            condominio.pagarTaxa(i, "Setembro");
            condominio.pagarTaxa(i, "Outubro");
        }

        for (int i = 1; i <= 50; i++){

            String nomeArea = "";
            switch ((i-1) %6){
                case 0:
                    nomeArea = "Salão";
                    break;
                case 1:
                    nomeArea = "Churraqueira";
                    break;

                case 2:
                    nomeArea = "Piscina";
                    break;

                case 3:
                    nomeArea = "Quadra";
                    break;

                case 4:
                    nomeArea ="Espaço Gourmet";
                    break;

                case 5:
                    nomeArea = "Academia";
                    break;
            }
            LocalDate data = LocalDate.of(2026, 8, (i -1)%31 +1);

            LocalTime horaInicio = null;
            LocalTime horaFim = null;

            switch ((i-1) % 4){
                case 0:
                    horaInicio = LocalTime.of(8, 0);
                    horaFim = LocalTime.of(10, 0);
                    break;

                case 1:
                    horaInicio = LocalTime.of(10, 0);
                    horaFim = LocalTime.of(12, 0);
                    break;

                case 2:
                    horaInicio = LocalTime.of(14, 0);
                    horaFim = LocalTime.of(16, 0);
                    break;

                case 3:
                    horaInicio = LocalTime.of(16, 0);
                    horaFim = LocalTime.of(18, 0);
                    break;
            }
            condominio.reservarArea(i, nomeArea, data, horaInicio, horaFim);
        }
        for (int i = 1; i <= 40; i++){
            condominio.confirmarReservas(i);
        }

        for (int i = 1; i <= 30; i++){
            TipoOcerrencia tipo = null;
            switch ((i-1) %3){
                case 0:
                    tipo = TipoOcerrencia.MANUNTECAO;
                    break;
                case 1:
                    tipo = TipoOcerrencia.RECLAMACAO;
                    break;
                case 2:
                    tipo = TipoOcerrencia.SUGESTAO;
                    break;
            }
            String descricao = "Ocorrência "+ i;
            int prioridade = (i-1)%5+1;

            condominio.registrarOcorrencia(i, tipo, descricao, prioridade);
        }
        for (int i = 1; i <= 10; i++) {
            condominio.atualizarOcorrencia(i, StatusOcorrencia.RESOLVIDA);
        }

        for (int i = 11; i <= 20; i++) {
            condominio.atualizarOcorrencia(i, StatusOcorrencia.EM_ANDAMENTO);
        }
        System.out.println("========== RELATÓRIO DO CONDOMÍNIO ==========");

        System.out.println("\n===== MORADORES =====");
        System.out.println("Total de moradores: " + condominio.getMoradores().size());

        System.out.println("\n===== INADIMPLÊNCIA =====");
        System.out.println("Moradores inadimplentes: " + condominio.moradoresInadimplentes());
        System.out.println("Total da inadimplência: R$ " + condominio.totalInadimplencia());
        System.out.println("Total de taxas pagas: R$ " + condominio.totalTaxasPagas());

        System.out.println("\n===== RESERVAS =====");
        System.out.println("Área mais reservada: " + condominio.areasMaisReservadas());
        System.out.println("Receita das reservas: R$ " + condominio.receitaReservas());

        System.out.println("\n===== OCORRÊNCIAS =====");
        System.out.println("Ocorrências abertas: " + condominio.ocorrenciasAbertas());
        System.out.println("Ocorrências com prioridade >= 3: " + condominio.ocorrenciaPorPrioridade(3));

        System.out.println("\n===== MORADOR MAIS ATIVO =====");
        System.out.println("Morador mais ativo: " + condominio.moradorMaisAtivo());

        System.out.println("\n== RELATÓRIO FINANCEIRO =====");
        condominio.relatorioFinanceiro();

        System.out.println("\n===== RELATÓRIO GERAL =====");
        condominio.relatorioGeral();
    }

}
