package GestaoRestaurante;

public class ItemComanda {
    private String nome;
    private int quantidade;
    private double precoUnitario;
    private Categoria categoria;

    public ItemComanda(String nome,  int quantidade, double precoUnitario, Categoria categoria) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
        this.categoria = categoria;
    }

    public double calcularSubTotal(){
        return precoUnitario*quantidade;
    }

    public String getNome() {
        return nome;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
