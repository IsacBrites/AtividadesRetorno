package GestaoRestaurante;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
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

    public void fechar(LocalTime hora){
        horaFechamento = hora;
        aberta = false;
    }

    public long tempoAtendimento(){
        if (horaAbertura == null) {
            return 0;
        }
        return ChronoUnit.HOURS.between(horaAbertura, horaFechamento);
    }

    public ArrayList<ItemComanda> itensPorCategoria(Categoria categoria){
        ArrayList<ItemComanda> itensCategoria = new ArrayList<>();
        for(ItemComanda item : itens){
            if (item.getCategoria().equals(categoria)){
                itensCategoria.add(item);
            }
        }
        return itensCategoria;
    }

    public void exibir(){
        System.out.println("Numero: "+numero);
        System.out.println("Mesa: "+mesa.getNumero());
        System.out.println("Garcom: "+garcom.getNome());
        System.out.println("Aberta: "+aberta);
        System.out.println("HoraAbertura: "+horaAbertura);
        System.out.println("HoraFechamento: "+horaFechamento);
        System.out.println("SubTotal: "+calcularSubTotal());
        System.out.println("TaxaServico: "+calcularTaxaServico());
        System.out.println("Total: "+calcularTotal());
    }

    public int getNumero() {
        return numero;
    }

    public ArrayList<ItemComanda> getItens() {
        return itens;
    }

    public Garcom getGarcom() {
        return garcom;
    }

    public LocalTime getHoraAbertura() {
        return horaAbertura;
    }

    public LocalTime getHoraFechamento() {
        return horaFechamento;
    }

    public Mesa getMesa() {
        return mesa;
    }
}
