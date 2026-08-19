package cozinha;

import java.util.ArrayList;

public class Chef {
    private int  idChef;
    private String nomeChef;
    private String especialidade;
    private int anosExperiencia;
    private ArrayList<Integer> pratosApresentados;

    public Chef(int idChef, String nomeChef, String especialidade, int anosExperiencia) {
        this.idChef = idChef;
        this.nomeChef = nomeChef;
        this.especialidade = especialidade;
        this.anosExperiencia = anosExperiencia;
       pratosApresentados = new ArrayList<>();
    }

    public void adicionarPrato(int idPrato) {
        pratosApresentados.add(idPrato);
    }

    public double mediaGeral(ArrayList<Double> todasAsNotas){
        double soma = 0.0;
        if (todasAsNotas.isEmpty()){
            return 0;
        }
        for (Double nota : todasAsNotas) {
            soma += nota;
        }
        return soma/todasAsNotas.size();
    }

    @Override
    public String toString() {
        return "Chef{" +
                "id=" + idChef +
                ", nome='" + nomeChef + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", experienciaAnos=" + anosExperiencia +
                '}';
    }

    public void exibir(){
        System.out.println("ID do Chef: " + idChef);
        System.out.println("Nome do Chef: " + nomeChef);
        System.out.println("Especialidade do Chef: " + especialidade);
        System.out.println("Ano Experiencia do do Chef: " + anosExperiencia);
        System.out.println("Pratos apresentados: " + pratosApresentados);
    }

    public ArrayList<Integer> getPratosApresentados() {
        return pratosApresentados;
    }
    public void setPratosApresentados(ArrayList<Integer> pratosApresentados) {
        this.pratosApresentados = pratosApresentados;
    }
    public int getIdChef() {
        return idChef;
    }
    public void setIdChef(int idChef) {
        this.idChef = idChef;
    }
    public String getNomeChef() {
        return nomeChef;
    }
    public void setNomeChef(String nomeChef) {
        this.nomeChef = nomeChef;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public int getAnosExperiencia() {
        return anosExperiencia;
    }
    public void setAnosExperiencia(int anosExperiencia) {
        this.anosExperiencia = anosExperiencia;
    }

}
