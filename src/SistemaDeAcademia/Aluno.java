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

        return avMaisRecente.getPeso()- avMaisAntiga.getPeso();

    }

    public int frequenciaMensal(LocalDate mesInicio, LocalDate mesFim){
        int contador = 0;

        for (LocalDate data : chekins){
            if (!data.isAfter(mesFim) && !data.isBefore(mesInicio)){
                contador++;
            }
        }
        return contador;
    }

    public AvaliacaoFisica ultimaAvaliacao(){
        ArrayList<AvaliacaoFisica> avaliacoeCopy = new ArrayList<>(avaliacoes);
        if (avaliacoes.isEmpty()){
            return null;
        }

        avaliacoeCopy.sort(Comparator.comparing(AvaliacaoFisica::getData));

        return avaliacoes.getLast();
    }

    public void exibir(){
        System.out.println("Nome do aluno: " + this.nome);
        System.out.println("CPF do aluno: " + this.cpf);
        System.out.println("Data da matrícula: " + this.dataMatricula);
        System.out.println("Plano: " + this.plano.getNome());
        System.out.println("Avaliacoes: " + this.avaliacoes);
        System.out.println("Chekins: " + this.chekins);


    }

    public ArrayList<AvaliacaoFisica> getAvaliacoes() {
        return avaliacoes;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public Plano getPlano() {
        return plano;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<LocalDate> getChekins() {
        return chekins;
    }
}
