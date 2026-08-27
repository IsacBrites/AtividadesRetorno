package GestaoRestaurante;

import java.util.ArrayList;

public class Garcom {
    private String nome;
    private int mesasAtendidas;
    private double gorjetasRecebidas;
    private ArrayList<Integer> mesasAtuais;

    public Garcom(String nome) {
        this.nome = nome;
        this.mesasAtendidas = 0;
        this.gorjetasRecebidas = 0;
        this.mesasAtuais = new ArrayList<>();
    }

    public void atribuirMesa(int numeroMesa){
        mesasAtuais.add(numeroMesa);
    }

    public void liberarMesa(int numeroMesa){
        mesasAtuais.removeIf(mesa -> mesa == numeroMesa);
    }

    public void receberGorjeta(double valor){
        gorjetasRecebidas+=valor;
    }

    public void registrarAtendimento(){
        mesasAtendidas++;
    }


    public int cargaAtual(){
        return  mesasAtuais.size();
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }

    public void exibir(){
        System.out.println("Nome: " + nome);
        System.out.println("Mesas Atendidas: " + mesasAtendidas);
        System.out.println("Gorjetas Recebidas: " + gorjetasRecebidas);
        System.out.println("Número de mesas atuais: " + mesasAtuais.size());
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Integer> getMesasAtuais() {
        return mesasAtuais;
    }

    public double getGorjetasRecebidas() {
        return gorjetasRecebidas;
    }

    public int getMesasAtendidas() {
        return mesasAtendidas;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setGorjetasRecebidas(double gorjetasRecebidas) {
        this.gorjetasRecebidas = gorjetasRecebidas;
    }

    public void setMesasAtendidas(int mesasAtendidas) {
        this.mesasAtendidas = mesasAtendidas;
    }

    public void setMesasAtuais(ArrayList<Integer> mesasAtuais) {
        this.mesasAtuais = mesasAtuais;
    }
}
