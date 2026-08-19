package Industrial;

public class Tecnico {
    private int id;
    private String nome;
    private String especialidade;
    private int ordensAtendidas;

    public Tecnico(int id, String nome, String especialidade, int ordensAtendidas) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
        this.ordensAtendidas = ordensAtendidas;
    }

    public void registrarAtendimento(){
        ordensAtendidas++;
    }

    public void exibir(){
        System.out.println("ID: "+id);
        System.out.println("Nome: "+nome);
        System.out.println("Especialidade: "+especialidade);
        System.out.println("Ordens: "+ordensAtendidas);
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
    public int getOrdensAtendidas() {
        return ordensAtendidas;
    }
    public void setOrdensAtendidas(int ordensAtendidas) {
        this.ordensAtendidas = ordensAtendidas;
    }
}
