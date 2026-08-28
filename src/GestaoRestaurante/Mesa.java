package GestaoRestaurante;

public class Mesa {
    private int numero;
    private int capacidade;
    private boolean ocupada;
    private Garcom garcomResponsavel;

    public Mesa(int numero, int capacidade ){
        this.numero = numero;
        this.capacidade = capacidade;
        this.ocupada = false;
        this.garcomResponsavel = null;
    }

    public void ocupar(Garcom garcom){
        this.garcomResponsavel = garcom;
        this.ocupada = true;
    }

    public void liberar(){
        this.garcomResponsavel = null;
        this.ocupada = false;
    }

    public void exibir(){
        System.out.println("Mesa: " + this.numero + " Capacidade para " + this.capacidade + " pessoas");
        System.out.println(garcomResponsavel!= null ? garcomResponsavel.getNome() : "Nenhum");
    }

    public int getNumero() {
        return numero;
    }

    public int getCapacidade() {
        return capacidade;
    }



    public Garcom getGarcomResponsavel() {
        return garcomResponsavel;
    }

    public boolean isOcupada(){
        return ocupada;
    }
}