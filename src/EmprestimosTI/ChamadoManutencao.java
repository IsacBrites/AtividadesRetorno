package EmprestimosTI;

import java.time.LocalDate;

public class ChamadoManutencao {
    private int id;
    private Equipamento equipamento;
    private String problema;
    private LocalDate dataAbertura;
    private LocalDate dataResolucao;
    private boolean resolvido;
    private double custoReparo;

    public ChamadoManutencao(int id, Equipamento equipamento, String problema, LocalDate dataAbertura) {
        this.id = id;
        this.equipamento = equipamento;
        this.problema = problema;
        this.dataAbertura = dataAbertura;
        this.dataResolucao = null;
        this.custoReparo = 0.0;
        this.resolvido = false;
    }

    public void resolver(LocalDate data, double custo){
        dataResolucao = data;
        this.custoReparo = custo;
        this.resolvido = true;
    }

    public void exibir(){
        System.out.println("ID: " + this.id);
        System.out.println("Problema: " + this.problema);
        System.out.println("DataAbertura: " + this.dataAbertura);
        System.out.println("DataResolucao: " + this.dataResolucao);
        System.out.println("Custo Reparo: " + this.custoReparo);
        System.out.println("Resolvido: " + this.resolvido);
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public double getCustoReparo() {
        return custoReparo;
    }

    public LocalDate getDataResolucao() {
        return dataResolucao;
    }

    public String getProblema() {
        return problema;
    }

    public boolean isResolvido() {
        return resolvido;
    }
}

