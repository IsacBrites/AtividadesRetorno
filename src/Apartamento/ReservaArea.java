package Apartamento;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReservaArea {
    private int id;
    private AreaComum area;
    private Morador morador;
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;
    private double valorPago;
    private boolean confirmada;

    public ReservaArea (int id, Morador morador, AreaComum area, LocalDate data, LocalTime horaInicio, LocalTime horaFim ,double valorPago){
        this.id = id;
        this.morador=morador;
        this.area = area;
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        this.valorPago = valorPago;
        this.confirmada = false;
    }

    public void confirmar(){
        this.confirmada = true;
    }

    public void cancelar(){
        this.confirmada = false;
    }

    public double duracaoHoras(){
        Duration duracao = Duration.between(this.horaInicio, this.horaFim);
        return duracao.toHours();
    }
    public int getId() {
        return id;
    }

    public AreaComum getArea() {
        return area;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getHoraFim() {
        return horaFim;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public Morador getMorador() {
        return morador;
    }

    public boolean getConfirmada() {
        return confirmada;
    }
    public void exibir(){
        System.out.println("ID: " + this.id);
        System.out.println("Area: " + this.area.toString());
        System.out.println("Morador: " + this.morador.toString());
        System.out.println("Data: " + this.data);
        System.out.println("Hora Inicio: " + this.horaInicio);
        System.out.println("Hora Fim: " + this.horaFim);
        System.out.println("Valor pago: " + this.valorPago);
        System.out.println("Confirmada: " + this.confirmada);
    }


}
