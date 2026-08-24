package SistemaDeAcademia;

import java.util.ArrayList;

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
}
