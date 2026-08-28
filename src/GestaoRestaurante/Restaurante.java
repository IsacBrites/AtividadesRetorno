package GestaoRestaurante;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Restaurante {
    private String nome;
    private ArrayList<Mesa> mesas;
    private ArrayList<Garcom> garcons;
    private ArrayList<Comanda> comandas;
    private static int proximoNumeroComanda = 1;


    public Restaurante(){
        mesas = new ArrayList<>();
        garcons = new ArrayList<>();
        comandas = new ArrayList<>();
    }

    public void cadastrarMesa(Mesa mesa){
        mesas.add(mesa);
    }

    public void cadastrarGarcom(Garcom garcom){
        garcons.add(garcom);
    }

    public Garcom garcomMenorCarga(){
        Garcom menor = garcons.get(0);
        for (Garcom garcom : garcons){
            if (garcom.cargaAtual() < menor.cargaAtual()){
                menor = garcom;
            }
        }
        return menor;
    }

    public Comanda abrirComanda(int numeroMesa, LocalTime hora){
        if (mesas.isEmpty()){
            return null;
        }
        Garcom garcom = garcomMenorCarga();
        for (Mesa mesa : mesas) {
            if (mesa.getNumero() == numeroMesa){
                if (!mesa.isOcupada()){
                    Comanda novaComanda = new Comanda(proximoNumeroComanda, mesa, garcom, hora);
                    comandas.add(novaComanda);
                    mesa.ocupar(garcom);
                    garcom.atribuirMesa(numeroMesa);
                    proximoNumeroComanda++;
                    return novaComanda;
                }
            }
        }
        return null;
    }

    public void adicionarPedido(int numeroComanda, ItemComanda item){
        for (Comanda comanda : comandas){
            if (comanda.getNumero() == numeroComanda){
                comanda.adicionarItem(item);
                break;
            }
        }
    }
    public void fecharComanda(int numeroComanda, LocalTime hora, double gorjeta){
        for (Comanda comanda : comandas){
            if (comanda.getNumero() == numeroComanda){
                comanda.fechar(hora);
                comanda.getMesa().liberar();
                comanda.getGarcom().liberarMesa(comanda.getMesa().getNumero());
                comanda.getGarcom().registrarAtendimento();
                comanda.getGarcom().receberGorjeta(gorjeta);
                break;
            }
        }
    }

    public ArrayList<Mesa> mesasDisponiveis() {
        ArrayList<Mesa> disponiveis = new ArrayList<>();
        for (Mesa mesa : mesas) {
            if (!mesa.isOcupada()) {
                disponiveis.add(mesa);
            }
        }
        return disponiveis;
    }

    public ArrayList<Comanda> comandasAbertas() {
        ArrayList<Comanda> abertas = new ArrayList<>();
        for (Comanda comanda : comandas) {
            if (comanda.isAberta()) {
                abertas.add(comanda);
            }
        }
        return abertas;
    }

    public double faturamentoTotal() {
        double total = 0;
        for (Comanda comanda : comandas) {
            if (!comanda.isAberta()) {
                total += comanda.calcularTotal();
            }
        }
        return total;
    }

    public double ticketMedio() {
        int totalFechadas = 0;
        for (Comanda comanda : comandas) {
            if (!comanda.isAberta()) {
                totalFechadas++;
            }
        }
        if (totalFechadas == 0) return 0;
        return faturamentoTotal() / totalFechadas;
    }

    public Garcom garcomMaisGorjetas() {
        if (garcons.isEmpty()) return null;
        Garcom destaque = garcons.get(0);
        for (Garcom garcom : garcons) {
            if (garcom.getGorjetasRecebidas() > destaque.getGorjetasRecebidas()) {
                destaque = garcom;
            }
        }
        return destaque;
    }

    public double tempoMedioAtendimento() {
        long somaTempo = 0;
        int totalFechadas = 0;
        for (Comanda comanda : comandas) {
            if (!comanda.isAberta()) {
                somaTempo += comanda.tempoAtendimento();
                totalFechadas++;
            }
        }
        if (totalFechadas == 0) return 0;
        return (double) somaTempo / totalFechadas;
    }

    public String itemMaisPedido(){
        int quantidade = 0;
        String nome = "";
        HashMap<String, Integer> busca = new HashMap<>();
        for (Comanda comanda : comandas) {
            for (ItemComanda item : comanda.getItens()){
                quantidade = item.getQuantidade();
                nome = item.getNome();
                busca.put(nome, busca.getOrDefault(nome, 0) + quantidade);
            }
        }
        int maiorQuantidade = 0;
        String nomeMaisPedido = "";
        if (busca.isEmpty()) {
            return "";
        }
        for (String key : busca.keySet()) {
            if (busca.get(key) > maiorQuantidade) {
                maiorQuantidade = busca.get(key);
                nomeMaisPedido = key;
            }
        }
        return nomeMaisPedido;
    }
}
