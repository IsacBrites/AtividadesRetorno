package Industrial;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class OrdemServico {
    private int numero;
    private Equipamento equipamento;
    private Tecnico tecnico;
    private Tipo tipo;
    private LocalDate dataAbertura;
    private LocalDate dataConclusao;
    private String descricaoProblema;
    private StatusOrdem statusOrdem;
    private ArrayList<PecasUtilizadas> pecasUtilizadas;

    public OrdemServico(int numero, Equipamento equipamento, Tecnico tecnico, Tipo tipo, LocalDate dataAbertura, String descricaoProblema) {
        this.numero = numero;
        this.equipamento = equipamento;
        this.tecnico = tecnico;
        this.tipo = tipo;
        this.dataAbertura = dataAbertura;
        this.dataConclusao = null;
        this.descricaoProblema = descricaoProblema;
        this.statusOrdem = StatusOrdem.ABERTA;
        pecasUtilizadas = new ArrayList<>();
    }

    public boolean adicionarPeca(Peca p, int quantidade){
        if (p.retirar(quantidade)){
            pecasUtilizadas.add(new PecasUtilizadas(p, quantidade));
            return true;
        }
        return false;
    }

    public double custoTotal(){
        double total = 0;
        for (PecasUtilizadas p : pecasUtilizadas){
            total += p.getQuantidade() * p.getPeca().getCustoUnitario();
        }
        return total;
    }

    public void concluir(LocalDate dataConclusao){
        this.dataConclusao = dataConclusao;
        statusOrdem = StatusOrdem.CONCLUIDA;
        Status status = Status.OPERACIONAL;
        if (tecnico != null){
            tecnico.registrarAtendimento();
        }
        equipamento.alterarStatus(status);

    }
    public long tempoResolucao(){
        return ChronoUnit.DAYS.between(dataAbertura, dataConclusao);
    }

    public int getNumero() {
        return numero;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public ArrayList<PecasUtilizadas> getPecasUtilizadas() {
        return pecasUtilizadas;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public LocalDate getDataConclusao() {
        return dataConclusao;
    }

    public StatusOrdem getStatusOrdem() {
        return statusOrdem;
    }

    public String getDescricaoProblema() {
        return descricaoProblema;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public void setDataConclusao(LocalDate dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public void setDescricaoProblema(String descricaoProblema) {
        this.descricaoProblema = descricaoProblema;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPecasUtilizadas(ArrayList<PecasUtilizadas> pecasUtilizadas) {
        this.pecasUtilizadas = pecasUtilizadas;
    }

    public void setStatusOrdem(StatusOrdem statusOrdem) {
        this.statusOrdem = statusOrdem;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
