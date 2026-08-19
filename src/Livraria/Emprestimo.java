package Livraria;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataDeEmprestimo;
    private LocalDate dataPrevisao;
    private LocalDate dataDevolucao;
    private int  renovacao;
    private boolean devolvido;

    public Emprestimo(int id, Livro livro, Usuario usuario, LocalDate dataDeEmprestimo) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataDeEmprestimo = dataDeEmprestimo;
        this.dataPrevisao = dataDeEmprestimo.plusDays(14);
        this.dataDevolucao = null;
        this.renovacao = 0;
        this.devolvido = false;
    }

    public boolean renovar(LocalDate novaDataPrevisao){
        if (renovacao >=2 ){
            return false;
        }
        else{
            renovacao++;
            dataPrevisao = novaDataPrevisao;
            return true;
        }
    }

    public void devolver(LocalDate date){
        dataDevolucao = date;
        if (devolvido){
            return;
        }
        else {
            livro.devolverExemplares();
            devolvido = true;
        }
    }

    public boolean estaAtrasado(LocalDate dataAtual){
        if (dataAtual.isAfter(dataPrevisao) && !devolvido){
            return true;
        }
        return false;
    }

    public long diasAtraso(LocalDate dataAtual){
        if (!estaAtrasado(dataAtual)){
            return 0;
        }
        else {
            return ChronoUnit.DAYS.between(dataPrevisao, dataAtual);
        }
    }
    public double calcularMulta(LocalDate dataAtual){
        double multa = 0;
        multa = diasAtraso(dataAtual) * 1.5;
        return multa;
    }

    public void exibir(){
        System.out.println("ID: " + id);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuario: " + usuario);
        System.out.println("Data de Emprestimo: " + dataDeEmprestimo);
        System.out.println("Data de Prevista: " + dataPrevisao);
        System.out.println("Data de Devolucao: " + dataDevolucao);
        System.out.println("Renovacao: " + renovacao);
        System.out.println("Devolvido: "+devolvido);
        System.out.println("Multa: " + calcularMulta(dataDeEmprestimo));
    }

    public int getId() {
        return id;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getRenovacao() {
        return renovacao;
    }

    public LocalDate getDataDeEmprestimo() {
        return dataDeEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public LocalDate getDataPrevisao() {
        return dataPrevisao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }
}
