package Apartamento;

public class Taxa {
    private String mes;
    private double valor;

    public Taxa(String mes, double valor) {
        this.mes = mes;
        this.valor = valor;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Mês: " + mes + " | Valor: R$ " + valor;
    }
}