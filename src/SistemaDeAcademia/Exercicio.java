package SistemaDeAcademia;

public class Exercicio {
    private String nome;
    private int series;
    private int repeticoes;
    private double carga;
    private String grupoMuscular;

    public Exercicio(String nome, int series, int repeticoes, String grupoMuscular) {
        this.nome = nome;
        this.series = series;
        this.repeticoes = repeticoes;
        this.grupoMuscular = grupoMuscular;
        this.carga = 0.0;
    }

    public void exibir(){
        System.out.println("Nome: " + this.nome);
        System.out.println("Serie: " + this.series);
        System.out.println("Repeticoes: " + this.repeticoes);
        System.out.println("Grupo Muscular: " + this.grupoMuscular);
        System.out.println("Carga: " + this.carga);
    }

    public String getNome() {
        return nome;
    }

    public double getCarga() {
        return carga;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public int getSeries() {
        return series;
    }

    public String getGrupoMuscular() {
        return grupoMuscular;
    }

    public void setCarga(double carga) {
        this.carga = carga;
    }

    public void setGrupoMuscular(String grupoMuscular) {
        this.grupoMuscular = grupoMuscular;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    public void setSeries(int series) {
        this.series = series;
    }
}