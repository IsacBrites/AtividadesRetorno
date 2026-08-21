package CorretoraDeImoveis;

import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String cpf;
    private String telefone;
    private double orcamentoMaximo;
    private ArrayList<Integer> propostasFeitas;

    public Cliente(String nome, String cpf, String telefone, double orcamentoMaximo){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.orcamentoMaximo = orcamentoMaximo;
        this.propostasFeitas = new ArrayList<>();
    }

    public void adicionarProposta(int id){
        propostasFeitas.add(id);
    }

    public void exibir(){
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Orçamento: "+orcamentoMaximo);
        System.out.println("Propostas feitas: "+propostasFeitas);
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public ArrayList<Integer> getPropostasFeitas() {
        return propostasFeitas;
    }

    public double getOrcamentoMaximo() {
        return orcamentoMaximo;
    }
}
