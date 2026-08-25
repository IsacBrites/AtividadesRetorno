package SistemaDeAcademia;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class TesteAcademia {

    public static void main(String[] args) {

        Random random = new Random();

        Academia academia = new Academia("Java Fit Central");

        Plano p1 = new Plano("Bronze", 3,89.90);
        Plano p2 = new Plano("Prata", 4,119.90);
        Plano p3 = new Plano("Ouro", 5,149.90);
        Plano p4 = new Plano("VIP", 6 ,199.90);
        Plano p5 = new Plano("Black", 12, 249.90);

        Plano [] planos = {p1,p2,p3,p4,p5};

        for (Plano plano : planos) {

            academia.cadastrarPlano(plano);

        }

        ArrayList<Aluno> alunosCadastrados = new ArrayList<>();

        for (int i = 0; i <= 60; i++) {
            Plano planoSorteado = planos[random.nextInt(planos.length)];
            Aluno aluno = new Aluno("Aluno "+i,String.format("111.222.333-%02d", i), LocalDate.now().minusMonths(random.nextInt(12) + 1), planoSorteado);
            academia.matricularAluno(aluno);
            alunosCadastrados.add(aluno);
        }

        for (Aluno aluno : alunosCadastrados) {
            int qtdAvaliacoes = random.nextInt(4)+3;
            double pesoBase = 70 + (random.nextDouble()*30);

            for (int j = 0; j < qtdAvaliacoes; j++) {
                LocalDate dataAv = LocalDate.now().minusMonths((qtdAvaliacoes-j));

                double peso = pesoBase=(j*(1.0 + random.nextDouble()*1.5));
                aluno.registrarAvaliacao(new AvaliacaoFisica(dataAv,peso));
            }

        }

        for (int i = 0; i <500; i ++){
            Aluno alunoSorteado = alunosCadastrados.get(random.nextInt(alunosCadastrados.size()));
            LocalDate dataCheckin = LocalDate.now().minusDays(random.nextInt(180));
            alunoSorteado.registrarChekin(dataCheckin);
        }

        System.out.println("=== RELATÓRIO GERAL DE OPERAÇÕES ===");
        System.out.printf("Receita Mensal Total: R$ %.2f%n", academia.receitaMensalTotal());
        System.out.printf("Média de Frequência Geral: %.2f presenças/aluno%n", academia.mediaFrequencia());

        Plano popular = academia.planoMaisPopular();
        System.out.println("Plano Mais Popular: " + (popular != null ? popular.getNome() : "Nenhum"));

        Aluno assiduo = academia.alunoMaisAssiduo();
        if (assiduo != null) {
            System.out.println("Aluno Mais Assíduo: " + assiduo.getNome() + " (" + assiduo.getChekins().size() + " check-ins)");
        }

        System.out.println("\n--- TOP 5 MELHORES EVOLUÇÕES ---");
        ArrayList<Aluno> top5 = academia.alunosComMelhorEvolucao();
        for (int i = 0; i < top5.size(); i++) {
            Aluno a = top5.get(i);
            System.out.printf("%dº %s | Variação de Peso: %.2f kg%n", (i + 1), a.getNome(), a.perdaGanhoPeso());
        }

        System.out.println("\n--- ALUNOS SEM AVALIAÇÃO RECENTE (+90 DIAS) ---");
        LocalDate limite = LocalDate.now().minusDays(90);
        int contPendentes = 0;

        for (Aluno a : alunosCadastrados) {
            AvaliacaoFisica ult = a.ultimaAvaliacao();
            if (ult == null || ult.getData().isBefore(limite)) {
                System.out.println("- " + a.getNome() + " | Última avaliação: " + (ult != null ? ult.getData() : "Nenhuma"));
                contPendentes++;
            }
        }
        if (contPendentes == 0) {
            System.out.println("Todos os alunos estão com as avaliações em dia!");
        }
    }
}
