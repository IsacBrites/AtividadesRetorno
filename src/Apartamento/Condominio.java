package Apartamento;


import Livraria.Reserva;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Condominio {
    private String nome;
    private ArrayList<Morador> moradores;
    private ArrayList<AreaComum> areaComums;
    private ArrayList<ReservaArea> reservas;
    private ArrayList<Ocorrencia> ocorrencias;
    private static int proximoIDReserva = 1;
    private static int proximoIDMorador = 1;

    public ArrayList<Morador> getMoradores() {
        return moradores;
    }

    public Condominio(String nome) {
        this.nome = nome;
        this.moradores = new ArrayList<>();
        this.areaComums = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.ocorrencias = new ArrayList<>();
        this.proximoIDReserva ++;
        this.proximoIDMorador ++;
    }

    public void cadastrarMorador(Morador morador) {
        this.moradores.add(morador);
    }

    public void cadastrarAreaComum(AreaComum areaComum) {
        this.areaComums.add(areaComum);
    }

    public boolean verificarDisponibilidade(String nomeArea, LocalDate data, LocalTime horaInicio, LocalTime horaFim) {

        for(ReservaArea reserva : reservas) {

            boolean isNomeArea = reserva.getArea().getNome().equals(nomeArea);
            boolean isDataArea = reserva.getData().isAfter(data);
            boolean isHoraInicioArea = reserva.getHoraInicio().isAfter(horaInicio);
            boolean isHoraFimArea = reserva.getHoraFim().isAfter(horaFim);
            boolean disponibilidade = reserva.getConfirmada();

            if (isNomeArea && isDataArea && isHoraInicioArea && isHoraFimArea && disponibilidade) {
                return false;
            }
        }

        return true;
    }

    public ReservaArea reservarArea(int aparatamento, String nomeArea, LocalDate data ,LocalTime horaInicio, LocalTime horaFim) {
        Morador moradorEncontrado = null;
        AreaComum areaComumEncontrado = null;

        for (Morador morador : moradores) {
            if (morador.getApartamento() == aparatamento) {
                moradorEncontrado = morador;
            }
        }
        if(moradorEncontrado == null){
            return null;
        }
        for (AreaComum areaComum : areaComums) {
            if(areaComum.getNome().equalsIgnoreCase(nomeArea)){
                areaComumEncontrado = areaComum;
            }
        }
        if(areaComumEncontrado == null){
            return null;
        }
        if (!verificarDisponibilidade(nomeArea, data, horaInicio, horaFim)) {
            return null;
        }
        Duration duracao = Duration.between(horaInicio, horaFim);
        long totalMinutos = duracao.toMinutes();


        double valor = areaComumEncontrado.getTaxaDeUso() * ((double) totalMinutos / 60);

        ReservaArea novaReserva = new ReservaArea(proximoIDReserva, moradorEncontrado, areaComumEncontrado,data ,horaInicio, horaFim, valor);
        proximoIDReserva++;

        reservas.add(novaReserva);
        return novaReserva;
    }

    public void cancelarReserva(int idReserva) {
        for (ReservaArea reserva : reservas) {
            if (reserva.getId() == idReserva) {
                reserva.cancelar();
                break;
            }
        }
    }

    public void gerarTaxasPendentes(String mes, double valorBase){
        for (Morador morador : moradores) {
            morador.adicionarTaxa(mes, valorBase);
        }
    }

    public void pagarTaxa(int apartamento, String mes){
        for (Morador morador : moradores) {
            if(morador.getApartamento() == apartamento){
                morador.pagarTaxa(mes);
            }
        }
    }

    public void confirmarReservas(int idReserva){
        for (ReservaArea reserva : reservas) {
            if (reserva.getId() == idReserva) {
                reserva.confirmar();
            }
        }
    }
    @Override
    public String toString(){
        return  "Ocorrência: " + ocorrencias;
    }
    public Ocorrencia registrarOcorrencia(int apartamento, TipoOcerrencia tipo, String descricao, int prioridade ){
        Morador moradorEncontrado = null;
        for (Morador morador : moradores) {
            if(morador.getApartamento() == apartamento){
                moradorEncontrado = morador;
            }
        }
        if(moradorEncontrado == null){
            return null;
        }
        Ocorrencia novaOcorrencia = new Ocorrencia(proximoIDMorador, moradorEncontrado, descricao, StatusOcorrencia.ABERTA,prioridade);
        proximoIDMorador++;
        ocorrencias.add(novaOcorrencia);
        return novaOcorrencia;
    }

    public void atualizarOcorrencia(int id, StatusOcorrencia status){
        for (Ocorrencia ocorrencia : ocorrencias) {
            if (ocorrencia.getId() == id) {
                ocorrencia.setStatus(status);
            }
        }
    }

    public ArrayList<Morador> moradoresInadimplentes(){
        ArrayList<Morador> moradoresInadimplentes = new ArrayList<>();
        for (Morador morador : moradores) {
            if (morador.inadimplente()) {
                moradoresInadimplentes.add(morador);
            }
        }
        return moradoresInadimplentes;
    }

    public double totalInadimplencia(){
        double totalInadimplencia = 0;
        for (Morador morador : moradores) {
            if (morador.inadimplente()) {
                totalInadimplencia += morador.totalDevido();
            }
        }
        return totalInadimplencia;
    }

    public ArrayList<Ocorrencia> ocorrenciasAbertas(){
        ArrayList<Ocorrencia> statusOcorrencias = new ArrayList<>();
        for(Ocorrencia ocorrencia : ocorrencias){
            if (ocorrencia.getStatus().equals(StatusOcorrencia.ABERTA)) {
                statusOcorrencias.add(ocorrencia);
            }
        }
        return statusOcorrencias;
    }

    public ArrayList<Ocorrencia> ocorrenciaPorPrioridade(int prioridade){
        ArrayList<Ocorrencia> prioridadeOcorrencia = new ArrayList<>();
        for (Ocorrencia ocorrencia : ocorrencias) {
            if(ocorrencia.getPrioridade() == prioridade){
                prioridadeOcorrencia.add(ocorrencia);
            }
        }
        return prioridadeOcorrencia;
    }

    public AreaComum areasMaisReservadas(){
        int contador = 0;
        int maiorQuantidade =0;
        AreaComum maisReservarda = null;
        for (AreaComum areaComum : areaComums) {
            for (ReservaArea reserva : reservas) {
                if (reserva.getArea() == areaComum){
                    contador++;
                }
            }
            if (contador > maiorQuantidade) {
                maiorQuantidade = contador;
                maisReservarda = areaComum;
            }


        }
        return maisReservarda;
    }

    public double receitaReservas(){
        double contador = 0;
        for (ReservaArea reserva : reservas) {
            if (reserva.getConfirmada()){
                contador += reserva.getValorPago();
            }
        }
        return contador;
    }

    public Morador moradorMaisAtivo(){
        int maiorQuantidade =0;
        Morador maisAtivo = null;
        for (Morador morador : moradores) {
            int contador = 0;
            for (ReservaArea reserva : reservas) {
                if(reserva.getMorador() == morador){
                    contador++;
                }
            }
            for (Ocorrencia ocorrencia : ocorrencias) {
                if(ocorrencia.getMorador() == morador){
                    contador++;
                }
            }
            if (contador > maiorQuantidade) {
                maiorQuantidade = contador;
                maisAtivo = morador;
            }
        }
        return maisAtivo;
    }

    public double totalTaxasPagas(){
        double totalTaxasPagas = 0;
        for (Morador morador : moradores) {
                for (Taxa taxa : morador.getTaxaPagas()){
                    totalTaxasPagas += taxa.getValor();
                }
        }
        return totalTaxasPagas;
    }

    public void relatorioFinanceiro(){
        System.out.println("Total de taxas pagas: " + totalTaxasPagas());
        System.out.println("Total de indadimplência: " + totalInadimplencia());
        System.out.println("Total de receita: "+receitaReservas());
    }

    public void relatorioGeral(){
        System.out.println("Total de moradores: " +moradores.size());
        System.out.println("Moradores inadimplentes: "+moradoresInadimplentes());
        System.out.println("Ocorrências abertas: "+ocorrenciasAbertas());
        System.out.println("Área mais reservada: "+ areasMaisReservadas());
        relatorioFinanceiro();
    }

}
