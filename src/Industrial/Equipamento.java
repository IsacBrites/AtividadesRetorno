package Industrial;

import java.time.LocalDate;

public class Equipamento {

    private String codigo;
    private String nome;
    private String setor;
    private LocalDate dataInstalacao;
    private int horasOperacao;
    private Status status;

    public Equipamento(String codigo, String nome, String setor,  LocalDate dataInstalacao, int horasOperacao, Status status) {
        this.codigo = codigo;
        this.nome = nome;
        this.setor = setor;
        this.dataInstalacao = dataInstalacao;
        this.horasOperacao = horasOperacao;
        this.status = status;
    }

    public void registrarOperacao(int horas) {
        horasOperacao += horas;
    }

    public void alterarStatus(Status status) {
        this.status = status;
    }

    public boolean precisaManutencaoPreventiva(){
        return horasOperacao % 500 < 10;
    }

    public void exibir(){
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Setor: " + setor);
        System.out.println("Data de instalacao: " + dataInstalacao);
        System.out.println("Horas operacao: " + horasOperacao);
        System.out.println("Status: " + status);
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {this.nome = nome;}
    public String getSetor() {
        return setor;
    }
    public void setSetor(String setor) {
        this.setor = setor;
    }
    public LocalDate getDataInstalacao() {
        return dataInstalacao;
    }
    public void setDataInstalacao(LocalDate dataInstalacao) {
        this.dataInstalacao = dataInstalacao;
    }
    public int getHorasOperacao() {
        return horasOperacao;
    }
    public void setHorasOperacao(int horasOperacao) {
        this.horasOperacao = horasOperacao;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
