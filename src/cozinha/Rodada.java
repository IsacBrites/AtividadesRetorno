package cozinha;

import java.util.ArrayList;
import java.util.Comparator;

public class Rodada {
    private int rodada;
    private String tema;
    private ArrayList<Prato> pratos;
    private ArrayList<Chef> eliminados;
    public Rodada(int rodada, String tema) {
        this.rodada = rodada;
        this.tema = tema;
        this.pratos = new ArrayList<>();
        this.eliminados = new ArrayList<>();
    }
    public void adicionarPrato(Prato prato) {
        this.pratos.add(prato);
    }

    public ArrayList<Prato> classificacao(){
        ArrayList<Prato> pratosOrdenados = new ArrayList<>();

        for (Prato p : pratos){
            pratosOrdenados.add(p);
        }
        Comparator<Prato> comparador = Comparator.comparingDouble((Prato p) -> p.notaMedia()).reversed();
        pratosOrdenados.sort(comparador);
        return pratosOrdenados;
    }

    public Chef eliminarUltimoColocado(){
        Prato piorPrato = pratos.getFirst();
        for(Prato p : pratos){
            if(p.notaMedia() < piorPrato.notaMedia()){
                piorPrato = p;
            }
        }
        Chef chefElminado = piorPrato.getChef();
        eliminados.add(chefElminado);
        return chefElminado;
    }

    public Prato melhorPrato(){
        Prato melhorPrato = pratos.getFirst();
        for (Prato p : pratos){
            if(p.notaMedia() > melhorPrato.notaMedia()){
                melhorPrato = p;
            }
        }
        return melhorPrato;
    }

    public void exibirResultados(){
        int posicao = 1;
        ArrayList<Prato> pratosResultado = classificacao();
        for (Prato p : pratosResultado){
            System.out.println(posicao + "º - " + p.getNome() + " - "+ " Média: "+ p.notaMedia());
            System.out.println(p.getNome());
            System.out.println(p.getIngredientesPrincipais());
            posicao++;
        }
    }

    public ArrayList<Chef> getEliminados() {
        return eliminados;
    }

    public ArrayList<Prato> getPratos() {
        return pratos;
    }

    public int getRodada() {
        return rodada;
    }

    public String getTema() {
        return tema;
    }
}
