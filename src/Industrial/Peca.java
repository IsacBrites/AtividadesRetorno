package Industrial;

public class Peca {
    private String codigo;
    private String nome;
    private int estoque;
    private double custoUnitario;
    private int estoqueMinimo;

    public Peca (String codigo, String nome, int estoque, double custoUnitario, int estoqueMinimo) {
        this.codigo = codigo;
        this.nome = nome;
        this.estoque = estoque;
        this.custoUnitario = custoUnitario;
        this.estoqueMinimo = estoqueMinimo;
    }

    public boolean retirar(int quantidade){
        if (estoque >= quantidade){
            estoque-=quantidade;
            return true;
        }
        return false;
    }

    public void repor(int quantidade){
        estoque+=quantidade;
    }

    public boolean estoqueEsgotando(){
        return estoque <= estoqueMinimo;
    }

    public void exibir(){
        System.out.println("Codigo: " + codigo);
        System.out.println("Nome: " + nome);
        System.out.println("Estoque: " + estoque);
        System.out.println("Custo: " + custoUnitario);
        System.out.println("Estoque Minimo: " + estoqueMinimo);
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }
    public double getCustoUnitario() {
        return custoUnitario;
    }
    public void setCustoUnitario(double custoUnitario) {
        this.custoUnitario = custoUnitario;
    }
    public int getEstoqueMinimo() {
        return estoqueMinimo;
    }
    public void setEstoqueMinimo(int estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

}
