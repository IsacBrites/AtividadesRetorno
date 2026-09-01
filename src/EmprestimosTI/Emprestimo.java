package EmprestimosTI;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private int id;
    private Equipamento equipamento;
    private Funcionario funcionario;
    private LocalDate dataRetirada;
    private LocalDate dataPrevistaDevolucao;
    private LocalDate dataDevolucaoReal;
    private boolean devolvido;
    private String observacao;

    public Emprestimo(int id, Funcionario funcionario, Equipamento equipamento, LocalDate dataRetirada ){
        this.id = id;
        this.funcionario = funcionario;
        this.equipamento = equipamento;
        this.dataRetirada = dataRetirada;
        this.dataPrevistaDevolucao = dataRetirada.plusDays(30);
        this.dataDevolucaoReal = null;
        this.devolvido = false;
        this.observacao = "";
    }

    public void devolver(LocalDate data, String observacao, Estado novoEstado){
        dataDevolucaoReal = data;
        devolvido = true;
        equipamento.alterarEstado(novoEstado);
        equipamento.setDisponivel(true);
        equipamento.adicionarHistorico(observacao);
        this.observacao = observacao;
    }

    public boolean estaAtrasado(LocalDate dataAtual){
        if(devolvido){
            return dataDevolucaoReal.isAfter(dataPrevistaDevolucao);
        }
        else{
            return dataAtual.isAfter(dataPrevistaDevolucao);
        }
    }

    public long diasUso(LocalDate dataAtual){
        if (devolvido){
            return ChronoUnit.DAYS.between(dataRetirada, dataDevolucaoReal);
        }
       else{
           return ChronoUnit.DAYS.between(dataRetirada, dataAtual);
        }
    }

    public void exibir(){
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.funcionario.getNome());
        System.out.println("Data de Retirada: " + this.dataRetirada);
        System.out.println("Data de Prevista: " + this.dataPrevistaDevolucao);
        System.out.println("Data de Devolução: " + this.dataDevolucaoReal);
        System.out.println("Observacao: " + this.observacao);
    }

    public int getId() {
        return id;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public LocalDate getDataDevolucaoReal() {
        return dataDevolucaoReal;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public String getObservacao() {
        return observacao;
    }

    public boolean isDevolvido() {
        return devolvido;
    }
}
