package SistemaDeAcademia;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class Aluno {
    private String nome;
    private String cpf;
    private LocalDate dataMatricula;
    private Plano plano;
    private ArrayList<AvaliacaoFisica> avaliacoes;
    private ArrayList<LocalDate> chekins;

    public Aluno(String nome, String cpf, LocalDate dataMatricula, Plano plano) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataMatricula = dataMatricula;
        this.plano = plano;
        this.avaliacoes = new ArrayList<>();
        this.chekins = new ArrayList<>();
    }

    public void registrarAvaliacao(AvaliacaoFisica avaliacao){
        this.avaliacoes.add(avaliacao);
    }

    public void registrarChekin(LocalDate date){
        this.chekins.add(date);
    }

    public ArrayList<Double> evolucaoPeso(){
        ArrayList<AvaliacaoFisica> avaliacoesPeso = new ArrayList<>(avaliacoes);
        ArrayList<Double> peso = new ArrayList<>();
        avaliacoesPeso.sort(Comparator.comparing(AvaliacaoFisica::getData));
       for (AvaliacaoFisica av : avaliacoesPeso) {
           peso.add(av.getPeso());
       }
        return peso;
    }

    public double perdaGanhoPeso(){
        if (avaliacoes.isEmpty() || avaliacoes.size() < 2){
            return 0;
        }
        ArrayList<AvaliacaoFisica> avaliacoesPeso = new ArrayList<>(avaliacoes);

        avaliacoesPeso.sort(Comparator.comparing(AvaliacaoFisica::getData));

        AvaliacaoFisica avMaisRecente = null;
        AvaliacaoFisica avMaisAntiga = null;

        avMaisAntiga = avaliacoesPeso.getFirst();
        avMaisRecente = avaliacoesPeso.getLast();

        return avMaisAntiga.getPeso() - avMaisRecente.getPeso();

    }
}
