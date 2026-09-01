package EmprestimosTI;

import java.util.ArrayList;

public class Equipamento {
    private String patrimonio;
    private Tipo tipo;
    private String modelo;
    private Estado estadoConservacao;
    private boolean disponivel;
    private ArrayList<String> historicoUso;

    public Equipamento(String patrimonio, Tipo tipo, String modelo, Estado estadoConservacao) {
        this.patrimonio = patrimonio;
        this.tipo = tipo;
        this.modelo = modelo;
        this.estadoConservacao = estadoConservacao;
        this.disponivel = true;
        this.historicoUso = new ArrayList<>();
    }

    public void adicionarHistorico(String registro){
        this.historicoUso.add(registro);
    }

    public void alterarEstado(Estado novoEstado){
        this.estadoConservacao = novoEstado;
    }

    public void exibir(){
        System.out.println("Patrimonio: " + patrimonio);
        System.out.println("Tipo: " + tipo);
        System.out.println("Modelo: " + modelo);
        System.out.println("Estado Conservacao: " + estadoConservacao);
        System.out.println("Disponivel: " + disponivel);
        System.out.println("Historico: " + historicoUso);
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public Estado getEstadoConservacao() {
        return estadoConservacao;
    }

    public String getModelo() {
        return modelo;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    is
}
