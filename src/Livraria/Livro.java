package Livraria;

public class Livro {
    private int id;
    private String titulo ;
    private Autor autor;
    private String categoria;
    private int totalExemplares;
    private  int exemplaresDisponiveis;
    private boolean digital;

    public Livro(int id, String titulo, Autor autor, String categoria, int totalExemplares, boolean digital) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.categoria = categoria;
        this.totalExemplares = totalExemplares;
        this.exemplaresDisponiveis = totalExemplares;
        this.digital = digital;
    }

    public boolean temDisponivel() {
        return this.exemplaresDisponiveis > 0;
    }

    public boolean retirarExemplares() {
        if (this.exemplaresDisponiveis == 0) {
            return false;
        }

        this.exemplaresDisponiveis--;
        return true;
    }

    public void devolverExemplares() {
        if (this.exemplaresDisponiveis < this.totalExemplares) {
            this.exemplaresDisponiveis ++;
        }
    }

    public double percentualDisponiblidade(){
        if(totalExemplares == 0){
            return 0;
        }
          double resultado = (double) this.exemplaresDisponiveis / this.totalExemplares;
        return resultado * 100;
    }

    public void exibir(){
        System.out.println("Id: " + this.id);
        System.out.println("Nome: " + this.titulo);
        System.out.println("Autor: " + autor.getNome());
        System.out.println("Categoria: " + this.categoria);
        System.out.println("Total Exemplares: " + this.totalExemplares);
        System.out.println("Exemplates disponíveis: "+this.exemplaresDisponiveis);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public int getTotalExemplares() {
        return totalExemplares;
    }
    public void setTotalExemplares(int totalExemplares) {
        this.totalExemplares = totalExemplares;
    }

    public int getExemplaresDisponiveis() {
        return exemplaresDisponiveis;
    }
    public void setExemplaresDisponiveis(int exemplaresDisponiveis) {
        this.exemplaresDisponiveis = exemplaresDisponiveis;
    }

}
