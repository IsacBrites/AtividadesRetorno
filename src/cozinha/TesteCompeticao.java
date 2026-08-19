package cozinha;

import java.util.Random;

public class TesteCompeticao {
    public static void main(String[] args) {

        Competicao competicao = new Competicao("Desafio");
        Random random = new Random();

        Chef chef1 = new Chef(1, "Gordon", "Carnes", 10);
        Chef chef2 = new Chef(2, "Rafael", "Massas", 8);
        Chef chef3 = new Chef(3, "Bruno", "Frutos do Mar", 7);
        Chef chef4 = new Chef(4, "Carlos", "Confeitaria", 6);
        Chef chef5 = new Chef(5, "Henrique", "Culinária Italiana", 9);
        Chef chef6 = new Chef(6, "Lucas", "Culinária Brasileira", 5);
        Chef chef7 = new Chef(7, "Mateus", "Carnes", 7);
        Chef chef8 = new Chef(8, "Felipe", "Massas", 6);
        Chef chef9 = new Chef(9, "André", "Culinária Japonesa", 8);
        Chef chef10 = new Chef(10, "Pedro", "Confeitaria", 4);
        Chef chef11 = new Chef(11, "João", "Culinária Francesa", 9);
        Chef chef12 = new Chef(12, "Marcos", "Culinária Brasileira", 6);

        competicao.inscreverChef(chef1);
        competicao.inscreverChef(chef2);
        competicao.inscreverChef(chef3);
        competicao.inscreverChef(chef4);
        competicao.inscreverChef(chef5);
        competicao.inscreverChef(chef6);
        competicao.inscreverChef(chef7);
        competicao.inscreverChef(chef8);
        competicao.inscreverChef(chef9);
        competicao.inscreverChef(chef10);
        competicao.inscreverChef(chef11);
        competicao.inscreverChef(chef12);


        Jurado jurado1 = new Jurado(1, "Ana", "Culinária Francesa");
        Jurado jurado2 = new Jurado(2, "Roberto", "Culinária Brasileira");
        Jurado jurado3 = new Jurado(3, "Juliana", "Confeitaria");
        Jurado jurado4 = new Jurado(4, "Marcelo", "Massas");
        Jurado jurado5 = new Jurado(5, "Patrícia", "Carnes");

        competicao.convidarJurado(jurado1);
        competicao.convidarJurado(jurado2);
        competicao.convidarJurado(jurado3);
        competicao.convidarJurado(jurado4);
        competicao.convidarJurado(jurado5);


        Rodada rodada1 = competicao.criarRodada("Culinária Brasileira");
        Rodada rodada2 = competicao.criarRodada("Massas");
        Rodada rodada3 = competicao.criarRodada("Frutos do Mar");
        Rodada rodada4 = competicao.criarRodada("Alta Gastronomia");
        Rodada rodada5 = competicao.criarRodada("Comida de Rua");
        Rodada rodada6 = competicao.criarRodada("Prato Surpresa");


        for (Rodada rodada : competicao.getRodadas()){
            for (Chef chef : competicao.chefsAtivos()){
                String nome = "Prato  Surpresa";
                String categoria = "teste";
                int tempo = 30;
                Prato prato = competicao.apresentarPrato(chef, rodada,  nome, categoria, tempo );
                for (Jurado jurado : competicao.getJurados()){
                    competicao.avaliarPrato(prato,jurado, random.nextDouble()*10);
                }
            }
            competicao.finalizarRodada(rodada);
        }
        competicao.determinarCampeao();
        competicao.historicoCompeticao();
        competicao.pratoMaisBemAvaliado();
        competicao.juradoMaisRigoroso();
        competicao.igredienteMaisUsado();
        competicao.relatorioFinal();

        competicao.historicoCompeticao();

        System.out.println();

        competicao.relatorioFinal();

    }
}
