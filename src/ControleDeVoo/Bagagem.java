package ControleDeVoo;

public class Bagagem {
    private int codigo;
    private double peso;
    private Tipo tipo;
    private Passageiro passageiro;

    public Bagagem(int codigo, double peso, Tipo tipo, Passageiro passageiro) {
        this.codigo = codigo;
        this.peso = peso;
        this.tipo = tipo;
        this.passageiro = passageiro;
    }

    public boolean excedeLimite(){
        if (tipo.equals(Tipo.DESPACHADA) && peso >23){
            return true;
        }
        else if (tipo.equals(Tipo.MAO) && peso >10){
            return true;
        }
        return false;
    }

    public double calcularTaxaExcesso(){
        double limite = 0;
        if (Tipo.DESPACHADA.equals(tipo)){
            limite = 23;
        }
        else if (Tipo.MAO.equals(tipo)){
            limite = 10;
        }
        if (excedeLimite()){
            return (peso - limite) * 15;
        }
        return 0;
    }

    public double getPeso() {
        return peso;
    }

    public int getCodigo() {
        return codigo;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void exibir(){
        System.out.println("Código: " + codigo);
        System.out.println("Peso: " + peso);
        System.out.println("Tipo: " + tipo);
        System.out.println("Passageiro: " + passageiro);
    }
}
