package Livraria;

import java.util.ArrayList;

public class Autor {
    private int id;
    private String nome;
    private String nacionalidade;
    private ArrayList<Integer> obras;
    public Autor(int id, String nome, String nacionalidade) {
        this.id = id;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.obras = new ArrayList<>();
    }

    public void adicionarObra(int idLivros){
        this.obras.add(id);
    }

    public void totalObras(){
        int total = this.obras.size();
    }

    public void exibir (){
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("Nacionalidade: " + this.nacionalidade);
        System.out.println("Obras: " + this.obras);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getNacionalidade() {
        return nacionalidade;
    }
    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }
    public ArrayList<Integer> getObras() {
        return obras;
    }
    public void setObras(ArrayList<Integer> obras) {
        this.obras = obras;
    }

}
