package SistemaDeAcademia;

import java.util.ArrayList;
import java.util.Comparator;

public class Academia {
    private String nome;
    private ArrayList<Aluno> alunos;
    private ArrayList<Plano> planos;

    public Academia(String nome) {
        this.nome = nome;
        this.alunos = new ArrayList<>();
        this.planos = new ArrayList<>();
    }

    public void cadastrarPlano(Plano plano) {
        this.planos.add(plano);
    }

    public void matricularAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public Aluno buscarAluno(String cpf){
        for (Aluno aluno : this.alunos) {
            if (aluno.getCpf().equals(cpf)) {
                return aluno;
            }
        }
        return null;
    }

    public ArrayList<Aluno> alunosPorPlano(String nomePlano){
        ArrayList<Aluno> alunosPorPlano = new ArrayList<>();
        for (Aluno aluno : this.alunos) {
            if (aluno.getPlano().getNome().equals(nomePlano)) {
                alunosPorPlano.add(aluno);
            }
        }
        return alunosPorPlano;
    }

    public Aluno alunoMaisAssiduo(){
        Aluno alunoAssiduo = alunos.getFirst();
        for (Aluno aluno : this.alunos) {

            if(aluno.getChekins().size() > alunoAssiduo.getChekins().size()) {
                alunoAssiduo = aluno;
            }
        }
        return alunoAssiduo;
    }

    public double receitaMensalTotal(){
        double total = 0;

        if (alunos.isEmpty()) {
            return 0.0;
        }

        for (Aluno aluno : this.alunos) {
            if (aluno.getPlano() != null) {
                total+= aluno.getPlano().getValorMensal();
            }

        }
        return total;
    }

    public double mediaFrequencia(){
        double media = 0;

        double totalChekins =0;
        if (alunos.isEmpty()) {
            return 0.0;
        }
        for (Aluno aluno : this.alunos) {
            if (aluno.getPlano() != null) {
                totalChekins += aluno.getChekins().size();
            }

        }
        media = totalChekins / this.alunos.size();
        return media;
    }

    public ArrayList<Aluno> alunosComMelhorEvolucao(){

        if (this.alunos.isEmpty()) {
            return new ArrayList<>();
        }

        ArrayList<Aluno> ranking = new ArrayList<>(this.alunos);

        ranking.sort(Comparator.comparingDouble(Aluno::perdaGanhoPeso));

        int limite = Math.min(ranking.size(), 5);

        return  new ArrayList<>(ranking.subList(0, limite));
    }

    public Plano planoMaisPopular(){
        if (this.planos.isEmpty()) {
            return null;
        }

        Plano planoMaisPopular = this.planos.get(0);
        int numAlunos = alunosPorPlano(planoMaisPopular.getNome()).size();

        for (Plano plano : this.planos) {
            int quantidadeAtual = alunosPorPlano(plano.getNome()).size();
            if (quantidadeAtual > numAlunos) {
                numAlunos = quantidadeAtual;
                planoMaisPopular = plano;
            }
        }
        return planoMaisPopular;
    }

    public void relatorioGeral(){
        System.out.println("Total de alunos: " + this.alunos.size());
        System.out.println("Receita mensal: " +receitaMensalTotal());
        System.out.println("Plano mais popular: "+planoMaisPopular().getNome());
        System.out.println("Frequência média: "+ mediaFrequencia());
        System.out.println("Alunos em destaque: "+alunosComMelhorEvolucao());
    }
}
