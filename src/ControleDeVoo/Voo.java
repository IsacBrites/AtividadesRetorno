package ControleDeVoo;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Voo {
    private int numero;
    private String origem;
    private String destino;
    private Aeronave aeronave;
    private LocalDateTime dataHora;
    private double precoEconomica;
    private double precoExecutiva;
    private Status status;
    private ArrayList<Passageiro> passageirosExecutiva;
    private ArrayList<Passageiro> passageirosEconomica;
    private ArrayList<Bagagem> bagagens;
    private int distanciaKm;

    public Voo(int numero, String origem, String destino,  Aeronave aeronave, LocalDateTime dataHora, double precoEconomica, double precoExecutiva, int distanciaKm) {
        this.numero = numero;
        this.origem = origem;
        this.destino = destino;
        this.aeronave = aeronave;
        this.dataHora = dataHora;
        this.precoEconomica = precoEconomica;
        this.precoExecutiva = precoExecutiva;
        this.status = Status.PROGRAMADO;
        this.passageirosExecutiva = new ArrayList<>();
        this.passageirosEconomica = new ArrayList<>();
        this.bagagens = new ArrayList<>();
        this.distanciaKm = distanciaKm;
    }

    public int vagasDisponiveis(ClasseVoo classe){
        if (classe.equals(ClasseVoo.ECONOMICA)){
            return (int) (aeronave.getCapacidadeEconomica() - passageirosEconomica.size());
        }
        if (classe.equals(ClasseVoo.EXECUTIVA)){
            return (int)  (aeronave.getCapacidadeExecutiva() - passageirosExecutiva.size());
        }
        return 0;
    }

    public boolean reservarAssento(Passageiro passageiro, ClasseVoo classe){
        vagasDisponiveis(classe);
        int overbookingEconomica = (int) (aeronave.getCapacidadeEconomica() * 0.05);
        int overbookingExecutiva = (int) (aeronave.getCapacidadeExecutiva() * 0.05);
        int capacidadeMaximaEconomica = (int) (aeronave.getCapacidadeEconomica() + overbookingEconomica);
        int capacidadeMaximaExecutiva = (int) (aeronave.getCapacidadeExecutiva() + overbookingExecutiva);

        if (ClasseVoo.ECONOMICA.equals(classe)){
            int quantidadeAtual = passageirosEconomica.size();
            if (quantidadeAtual < capacidadeMaximaEconomica){
                passageirosEconomica.add(passageiro);
                return true;
            }

        }

        if (ClasseVoo.EXECUTIVA.equals(classe)){
            int quantidadeAtual = passageirosExecutiva.size();
            if (quantidadeAtual < capacidadeMaximaExecutiva){
                passageirosExecutiva.add(passageiro);
                return true;
            }
        }
        return false;
    }

    public void cancelarReserva(Passageiro passageiro,  ClasseVoo classe){
        if (classe.equals(ClasseVoo.ECONOMICA)){
            passageirosEconomica.remove(passageiro);
        }
        if (classe.equals(ClasseVoo.EXECUTIVA)){
            passageirosExecutiva.remove(passageiro);
        }
    }

    public boolean temOverbooking(){
        int capacidadeMax = aeronave.capacidadeTotal();

        return passageirosEconomica.size() + passageirosExecutiva.size() > capacidadeMax;
    }

    public boolean fazerCheckin(Passageiro passageiro){
        if (passageirosEconomica.contains(passageiro) || passageirosExecutiva.contains(passageiro) ){
            passageiro.adicionarMilhas(distanciaKm);
            return true;
        }
        return false;
    }

    public double despacharBagagem(Bagagem bagagem){
        bagagens.add(bagagem);
       return bagagem.calcularTaxaExcesso();
    }

    public int totalPassageiros(){
        return passageirosExecutiva.size() + passageirosEconomica.size();
    }

    public double taxaOcupacao(){
        return (double) (totalPassageiros() / aeronave.capacidadeTotal()) * 100;
    }

    public double receitaTotal(){
        double receitaTotal = 0;
        receitaTotal = (passageirosExecutiva.size() * precoExecutiva) + (passageirosEconomica.size() * precoEconomica);
        for (Bagagem bagagem : bagagens){
            receitaTotal += bagagem.calcularTaxaExcesso();
        }
        return receitaTotal;
    }

    public void exibir(){
        System.out.println("Número do voo: "+numero);
        System.out.println("Origem: "+origem);
        System.out.println("Destino: "+destino);
        System.out.println("Taxa de ocupação: "+taxaOcupacao());
        System.out.println("Data e hora do voo: "+ dataHora);
        System.out.println("Preço economica: "+precoEconomica);
        System.out.println("Preço executiva: "+precoExecutiva);
        System.out.println("Modelo da aeronave: "+ aeronave.getModelo());
        System.out.println("Status: " + status);
        System.out.println("Total de passageiros: " + totalPassageiros());
    }


    public Aeronave getAeronave() {
        return aeronave;
    }

    public double getPrecoEconomica() {
        return precoEconomica;
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<Passageiro> getPassageirosExecutiva() {
        return passageirosExecutiva;
    }

    public double getPrecoExecutiva() {
        return precoExecutiva;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Status getStatus() {
        return status;
    }

    public String getDestino() {
        return destino;
    }

    public String getOrigem() {
        return origem;
    }

    public ArrayList<Bagagem> getBagagens() {
        return bagagens;
    }

    public ArrayList<Passageiro> getPassageirosEconomica() {
        return passageirosEconomica;
    }

}