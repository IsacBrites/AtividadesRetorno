package SistemaDeAcademia;

import java.util.ArrayList;

public class Treino {
    private String nome;
    private String diaDaSemana;
    private ArrayList<Exercicio> exercicios;

    public Treino(String nome, String diaDaSemana) {
        this.nome = nome;
        this.diaDaSemana = diaDaSemana;
        this.exercicios = new ArrayList<>();
    }

    public void adicionarExercicio(Exercicio exercicio) {
        exercicios.add(exercicio);
    }

    public int totalExercicios(){
        return exercicios.size();
    }

    public ArrayList<String> gruposMuscularesTrabalhados(){
        ArrayList<String> gruposMuscularesTrabalhados = new ArrayList<>();
        for(Exercicio exercicio : exercicios){
            if(!gruposMuscularesTrabalhados.contains(exercicio.getGrupoMuscular())){
                gruposMuscularesTrabalhados.add(exercicio.getGrupoMuscular());
            }
        }
        return gruposMuscularesTrabalhados;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }

    public void exibir(){
        System.out.println("Nome: " + nome);
        System.out.println("Dia da Semana: " + diaDaSemana);
        System.out.println("Grupos: " + gruposMuscularesTrabalhados());
        System.out.println("Total de Exercicios: " + totalExercicios());
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Exercicio> getExercicios() {
        return exercicios;
    }

    public String getDiaDaSemana() {
        return diaDaSemana;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDiaDaSemana(String diaDaSemana) {
        this.diaDaSemana = diaDaSemana;
    }

    public void setExercicios(ArrayList<Exercicio> exercicios) {
        this.exercicios = exercicios;
    }
}
