package cozinha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;

public class Competicao {
    private String nome;
    private ArrayList<Chef> chefs;
    private ArrayList<Jurado> jurados;
    private ArrayList<Rodada> rodadas;
    private String campeao;
    private static int proximoIDPrato = 1;

    public Competicao(String nome) {
        this.nome = nome;
        this.chefs = new ArrayList<>();
        this.jurados = new ArrayList<>();
        this.rodadas = new ArrayList<>();

    }


    public void inscreverChef(Chef chef){
        chefs.add(chef);
    }

    public void convidarJurado(Jurado jurado){
        jurados.add(jurado);
    }

    public Rodada criarRodada(String tema){
        if (rodadas == null){
            rodadas = new ArrayList<>();
        }
        Rodada novaRodada = new Rodada(rodadas.size()+1, tema);
        rodadas.add(novaRodada);
        return novaRodada;
    }

    public Prato apresentarPrato(Chef chef, Rodada rodada, String nome, String categoria, int tempoDePreparo){
        Prato prato = new Prato(proximoIDPrato, nome, chef, categoria, tempoDePreparo);
        rodada.adicionarPrato(prato);
        proximoIDPrato++;
        return prato;
    }

    public void avaliarPrato(Prato prato, Jurado jurado, double nota){
        if (nota < 0 ||  nota > 10){
            System.out.println("Nota inválida");
            return;
        }
        prato.receberNota(jurado, nota);
    }

    public void finalizarRodada(Rodada rodada){
        Chef eliminado = null;
        eliminado = rodada.eliminarUltimoColocado();
        chefs.remove(eliminado);
    }

    public ArrayList<Chef> chefsAtivos(){
        return chefs;
    }

    public String determinarCampeao() {
        if (chefs.size() == 1) {
            campeao = chefs.getFirst().getNomeChef();
        } else if (chefs.size() > 1) {
            campeao = melhorChef().getNomeChef();
        }

        return campeao;
    }

    public Chef melhorChef(){
        double melhorMedia = 0;
        Chef melhorChef = null;

        Chef melhorChefe = chefs.getFirst();
        for (Chef chef : chefs){

            double soma = 0;
            int quantidade = 0;

            for (Rodada rodada : rodadas){

                for (Prato prato : rodada.getPratos()){

                    if (prato.getChef().equals(chef)){

                        for (Avaliacao avaliacao : prato.getAvaliacoes()){
                            soma += avaliacao.getNota();
                            quantidade++;
                        }

                    }
                }
            }

            double mediaDoChefe = soma/quantidade;

            if (mediaDoChefe > melhorMedia){
                melhorChef = chef;
                melhorMedia = mediaDoChefe;
            }
        }
        return melhorChef;
    }

    public Prato pratoMaisBemAvaliado(){

        double melhorMedia = 0;
        Prato maisBemAvaliado = rodadas.getFirst().getPratos().getFirst();

        for(Rodada rodada : rodadas){

            for (Prato prato : rodada.getPratos()){

                double soma = 0;
                int quantidade = 0;

                for (Avaliacao avaliacao : prato.getAvaliacoes()){
                    soma += avaliacao.getNota();
                    quantidade++;

                }

                double media = soma/quantidade;

                if (media > melhorMedia){
                    melhorMedia = media;
                    maisBemAvaliado = prato;
                }
            }

        }
        return maisBemAvaliado;
    }

    public Jurado juradoMaisRigoroso(){
        Jurado juradoRigoroso = null;
        double menorMedia = 10;
        for (Jurado jurado : jurados){

            double soma = 0;
            double quantidade = 0;

            for (Rodada rodada : rodadas){

                for (Prato prato : rodada.getPratos()){

                    for (Avaliacao avaliacao : prato.getAvaliacoes()){

                        if (avaliacao.getJurado().equals(jurado)){
                            soma += avaliacao.getNota();
                            quantidade++;
                        }

                    }
                }
            }

            double media = soma/quantidade;
            if (media < menorMedia){
                menorMedia = media;
                juradoRigoroso = jurado;
            }
        }
        return juradoRigoroso;
    }

    public String igredienteMaisUsado(){
        HashMap<String, Integer> contador = new HashMap<>();
        for (Rodada rodada : rodadas){

            for (Prato prato : rodada.getPratos()){
                
                for (String ingrediente : prato.getIngredientesPrincipais()){
                    contador.put(ingrediente, contador.getOrDefault(ingrediente, 0) + 1);
                }
            }
        }
        int maiorQuantidade = 0;
        String ingredienteMaisUsado = "";
        for (String ingrediente : contador.keySet()){
            if (maiorQuantidade < contador.get(ingrediente)){
                maiorQuantidade = contador.get(ingrediente);
                ingredienteMaisUsado = ingrediente;
            }
        }
        return ingredienteMaisUsado;
    }

    public void historicoCompeticao(){
        for (Rodada rodada : rodadas){
            rodada.exibirResultados();
        }
    }

    public void relatorioFinal() {
        System.out.println();

        System.out.println(" RELATÓRIO FINAL");


        System.out.println("Campeão: " + determinarCampeao());

        System.out.println();
        System.out.println("Melhor prato:");
        Prato melhorPrato = pratoMaisBemAvaliado();
        melhorPrato.exibir();

        System.out.println();
        System.out.println("Jurado mais rigoroso:");
        Jurado juradoRigoroso = juradoMaisRigoroso();
        juradoRigoroso.exibir();

        System.out.println();
        System.out.println("Ingrediente mais usado: " + igredienteMaisUsado());

        int contador = 0;

        for (Rodada rodada : rodadas) {
            for (Prato prato : rodada.getPratos()) {
                contador++;
            }
        }

        System.out.println("Total de pratos apresentados: " + contador);


        System.out.println(" FIM DA COMPETIÇÃO");

    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Chef> getChefs() {
        return chefs;
    }

    public ArrayList<Jurado> getJurados() {
        return jurados;
    }

    public ArrayList<Rodada> getRodadas() {
        return rodadas;
    }

    public String getCampeao() {
        return campeao;
    }

    public static int getProximoIDPrato() {
        return proximoIDPrato;
    }
}
