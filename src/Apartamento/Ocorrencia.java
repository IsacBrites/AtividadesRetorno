package Apartamento;


public class Ocorrencia {
    private int id;
    private Morador morador;
    private String descricao;
    private StatusOcorrencia status;
    private int prioridade;


    public Ocorrencia(int id, Morador morador, String descricao, StatusOcorrencia status, int prioridade){
        this.id = id;
        this.morador = morador;
        this.descricao = descricao;
        this.status = status;
        this.prioridade = prioridade;
    }

    @Override
    public String toString() {
        return "Ocorrencia{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", prioridade=" + prioridade +
                ", status=" + status +
                '}';
    }

    public int getId() {
        return id;
    }

    public Morador getMorador() {
        return morador;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public StatusOcorrencia getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setStatus(StatusOcorrencia status) {
        this.status = status;
    }
}


