package Livraria;

import Apartamento.AreaComum;
import Apartamento.Morador;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reserva {
    private int id;
    private Livro livro;
    private Usuario usuario;
    private LocalDate dataReserva;
    private boolean atendida;
    private int posicaoFila;

    public Reserva(int id, Livro livro, Usuario usuario, LocalDate dataReserva, int posicao) {
        this.id = id;
        this.livro = livro;
        this.usuario = usuario;
        this.dataReserva = dataReserva;
        this.atendida = false;
        this.posicaoFila = 0;
    }



    public void atender(){
        this.atendida = true;
    }

    public void exibir(){
        System.out.println("ID: " + id);
        System.out.println("Livro: " + livro.getTitulo());
        System.out.println("Usuario: " + usuario);
        System.out.println("Data de Emprestimo: " + dataReserva);
        System.out.println("Atendimento: " + atendida);
        System.out.println("Posicao Fila: " + posicaoFila);
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public int getPosicaoFila() {
        return posicaoFila;
    }

    public LocalDate getDataReserva() {
        return dataReserva;
    }

    public boolean isAtendida() {
        return atendida;
    }

}
