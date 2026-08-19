package ControleDeVoo;

public class Passageiro {
    private String nome;
    private String cpf;
    private String passaporte;
    private int milhas;

    public Passageiro(String nome, String cpf, String passaporte, int milhas) {
        this.nome = nome;
        this.cpf = cpf;
        this.passaporte = passaporte;
        this.milhas = milhas;
    }

    public void adicionarMilhas(int milhas) {
        this.milhas += milhas;
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CPF: " + cpf + ", Passaporte: "+ passaporte + ", Milhas: " + milhas;
    }

    public void exibir(){
        System.out.println("Nome: " + this.nome);
        System.out.println("CPF: " + this.cpf);
        System.out.println("Passaporte: " + this.passaporte);
        System.out.println("Milhas: " + this.milhas);
    }

    public String getNome() {
        return nome;
    }

    public int getMilhas() {
        return milhas;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPassaporte() {
        return passaporte;
    }
}
