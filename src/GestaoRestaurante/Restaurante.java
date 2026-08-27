package GestaoRestaurante;

import java.time.LocalTime;
import java.util.ArrayList;

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
}
