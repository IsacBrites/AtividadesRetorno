package GestaoRestaurante;

import java.time.LocalTime;
import java.util.ArrayList;

public class Comanda {
    private int numero;
    private Mesa mesa;
    private Garcom garcom;
    private ArrayList<ItemComanda> itens;
    private boolean aberta;
    private LocalTime horaAbertura;
    private LocalTime horaFechamento;

    public Comanda(int numero, Mesa mesa, Garcom garcom, LocalTime horaAbertura){
        this.numero = numero;
        this.mesa = mesa;
        this.garcom = garcom;
        this.itens = new ArrayList<>();
        this.aberta = true;
        this.horaAbertura = horaAbertura;
        this.horaFechamento = null;
    }

    public void adicionarItem(ItemComanda item){
        this.itens.add(item);
    }

    public double calcularSubTotal(){
        double subTotal = 0;
        for(ItemComanda item : itens){
            subTotal += item.calcularSubTotal();
        }
        return subTotal;
    }

    public double calcularTaxaServico(){
        return calcularSubTotal() * 0.10;
    }

    public double calcularTotal(){
        return calcularTaxaServico() + calcularSubTotal();
    }
}
