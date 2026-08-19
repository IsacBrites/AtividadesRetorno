package Apartamento;

import java.util.ArrayList;

public class Morador {
    private String nome;
    private int apartamento;
    private int bloco;
    private String telefone;
    private String email;
    private boolean proprietario;
    private ArrayList<Taxa> taxasPendentes;
    private ArrayList<Taxa> taxaPagas;

    public Morador(String nome, int apartamento, int bloco, String telefone, String email) {
        this.nome = nome;
        this.apartamento = apartamento;
        this.bloco = bloco;
        this.telefone = telefone;
        this.email = email;
        this.proprietario = false;
        this.taxasPendentes = new ArrayList<>();
        this.taxaPagas = new ArrayList<>();
    }

    public void adicionarTaxa(String mes, double valor) {
        for (Taxa taxa : taxasPendentes) {
            if (taxa.getMes().equals(mes)) {
                return;
            }
        }
        taxasPendentes.add(new Taxa(mes, valor));
    }



    public void pagarTaxa(String mes) {

        for (Taxa taxa : taxasPendentes) {
            if (taxa.getMes().equals(mes)) {
                taxaPagas.add(taxa);
            }
        }
        if (taxasPendentes.isEmpty()) {
            return;
        }

        taxasPendentes.removeIf(taxa -> taxa.getMes().equals(mes));
    }

    public double totalDevido() {
        double total = 0;

        for (Taxa taxa : taxasPendentes) {
            total += taxa.getValor();
        }

        return total;
    }

    @Override
    public String toString() {
        return "Nome: "+ nome;
    }

    public boolean inadimplente() {
        return totalDevido() > 0;
    }

    public void exibir() {
        System.out.println("Nome: " + nome);
        System.out.println("Apartamento: " + apartamento);
        System.out.println("Bloco: " + bloco);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Proprietário: " + proprietario);
        System.out.println("Taxas pendentes: " + taxasPendentes);
    }

    public String getNome() {
        return nome;
    }

    public int getApartamento() {
        return apartamento;
    }

    public int getBloco() {
        return bloco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public boolean isProprietario() {
        return proprietario;
    }

    public ArrayList<Taxa> getTaxasPendentes() {
        return taxasPendentes;
    }

    public ArrayList<Taxa> getTaxaPagas() {
        return taxaPagas;
    }
}