package cozinha;

public class Avaliacao {
    private Jurado jurado;
    private double nota;

    public Avaliacao(Jurado jurado, double nota) {
        this.jurado = jurado;
        this.nota = nota;
    }
    public Jurado getJurado() {
        return jurado;
    }
    public void setJurado(Jurado jurado) {
        this.jurado = jurado;
    }
    public double getNota() {
        return nota;
    }
    public void setNota(double nota) {
        this.nota = nota;
    }
}
