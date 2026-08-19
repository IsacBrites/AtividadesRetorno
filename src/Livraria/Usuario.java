package Livraria;

import java.util.ArrayList;

public class Usuario {
    private String matricula;
    private String nome;
    private String tipo;
    private String curso;
    private ArrayList<Integer> emprestimosAtivos;
    private ArrayList<Integer> historico;
    private double multasPendentes;

    public Usuario(String matricula, String nome, String tipo, String curso) {
        this.matricula = matricula;
        this.nome = nome;
        this.tipo = tipo;
        this.curso = curso;
        this.emprestimosAtivos = new ArrayList<>();
        this.historico = new ArrayList<>();
        this.multasPendentes = 0;
    }

    public int limiteEmprestimos(){
        if (tipo.equalsIgnoreCase("Aluno")) {
            return  3;
        }else if (tipo.equalsIgnoreCase("Professor".toLowerCase())) {
            return 8;
        }
        else {
            return 0;
        }
    }

    public boolean podeEmprestar(){
        if (multasPendentes > 0){
            return false;
        }
        if (emprestimosAtivos.size() < limiteEmprestimos()){
            return true;

        }
        return false;
    }

    public void adicionarEmprestimo(int id){
        if (podeEmprestar()){
            emprestimosAtivos.add(id);
            historico.add(id);
        }
    }

    public void removerEmprestimo(int id){
        for (int i = 0; i < emprestimosAtivos.size(); i++){
            if (emprestimosAtivos.get(i) == id){
                emprestimosAtivos.remove(i);
                break;
            }
        }
    }

    public void pagarMulta(double valor){
        if (valor <= 0){
            return;
        }
        else if (valor > 0){
            multasPendentes = multasPendentes - valor;
            if (multasPendentes < 0){
                multasPendentes = 0;
            }
        }
    }


    public void adicionarMulta(double valor){
        if (valor > 0){
            multasPendentes = multasPendentes + valor;
        }
    }
    public void exibir(){
        System.out.println("Matricula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + tipo);
        System.out.println("Curso: " + curso);
        System.out.println("Emprestimos: " + emprestimosAtivos);
        System.out.println("Historico: " + historico);
        System.out.println("Multas pendentes: " + multasPendentes);
    }

    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getCurso() {
        return curso;
    }
    public void setCurso(String curso) {
        this.curso = curso;
    }
    public ArrayList<Integer> getEmprestimosAtivos() {
        return emprestimosAtivos;
    }
    public void setEmprestimosAtivos(ArrayList<Integer> emprestimosAtivos) {
        this.emprestimosAtivos = emprestimosAtivos;
    }
    public ArrayList<Integer> getHistorico() {
        return historico;
    }
    public void setHistorico(ArrayList<Integer> historico) {
        this.historico = historico;
    }
    public double getMultasPendentes() {
        return multasPendentes;
    }
    public void setMultasPendentes(double multasPendentes) {
        this.multasPendentes = multasPendentes;
    }
}
