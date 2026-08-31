package EmprestimosTI;


import java.util.ArrayList;

public class Funcionario {
    private String matricula;
    private String nome;
    private String departamento;
    private ArrayList<String> patrimonios;

    public Funcionario(String matricula, String nome, String departamento) {
        this.matricula = matricula;
        this.nome = nome;
        this.departamento = departamento;
        this.patrimonios = new ArrayList<>();
    }

    public void pegarEquipamento(String patrimonio){
        patrimonios.add(patrimonio);
    }

    public void removerEquipamento(String patrimonio){
        patrimonios.removeIf(patrimonio::equals);
    }

    public int quantidadeEquipamentos(){
        return patrimonios.size();
    }

    public void exibir(){
        System.out.println("Matricula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Departamento: " + departamento);
        System.out.println("Patrimonios: " + patrimonios);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<String> getPatrimonios() {
        return patrimonios;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getMatricula() {
        return matricula;
    }
}
