package SistemaDeFrete;

import java.time.LocalDate;

public class Cotacao {
    private Transportadora transportadora;
    private double valorFrete;
    private int prazoEstimado;
    private LocalDate data;

    public Cotacao(Transportadora transportadora, double valorFrete, int prazoEstimado, LocalDate data) {
        this.transportadora = transportadora;
        this.valorFrete = valorFrete;
        this.prazoEstimado = prazoEstimado;
        this.data = data;
    }

    public double custoBeneficio(){
        return valorFrete/prazoEstimado;
    }

    public void exibir(){
        System.out.println("Transportadora: "+ transportadora.getNome());
        System.out.println("Valor Frete: "+ valorFrete);
        System.out.println("Prazo Estimado: "+ prazoEstimado);
        System.out.println("Data: "+ data);
        System.out.println("Custo: "+ custoBeneficio());
    }

    public LocalDate getData() {
        return data;
    }

    public double getValorFrete() {
        return valorFrete;
    }

    public int getPrazoEstimado() {
        return prazoEstimado;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }
}
