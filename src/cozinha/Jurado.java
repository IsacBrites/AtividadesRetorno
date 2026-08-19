package cozinha;

public class Jurado {
    private int id;
    private String nome;
    private String especialidade;
    private int avaliacoesFeitas;

    public Jurado(int id, String nome, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.avaliacoesFeitas = 0;
    }

    public void registrarAvaliacao(){
        avaliacoesFeitas++;
    }

    @Override
    public String toString() {
        return "Jurado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", especialidade='" + especialidade + '\'' +
                ", avaliacoesFeitas=" + avaliacoesFeitas +
                '}';
    }

    public void exibir(){
        System.out.println("ID: " + this.id);
        System.out.println("Nome: " + this.nome);
        System.out.println("Especialidade: " + this.especialidade);
        System.out.println("Avaliacoes feitas: " + avaliacoesFeitas);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEspecialidade() {
        return especialidade;
    }
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
    public int getAvaliacoesFeitas() {
        return avaliacoesFeitas;
    }
    public void setAvaliacoesFeitas(int avaliacoesFeitas) {
        this.avaliacoesFeitas = avaliacoesFeitas;
    }

}