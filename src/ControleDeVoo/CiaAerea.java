package ControleDeVoo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class CiaAerea {

    private String nome;
    private ArrayList<Voo> voos;
    private ArrayList<Passageiro> passageiros;

    public CiaAerea(String nome) {
        this.nome = nome;
        this.voos = new ArrayList<>();
        this.passageiros = new ArrayList<>();
    }

    public void cadastrarVoo(Voo voo) {
        this.voos.add(voo);
    }

    public void cadastrarPassageiro(Passageiro passageiro) {
        this.passageiros.add(passageiro);
    }

    public Voo buscarVoo(int numero) {
        for (Voo voo : this.voos) {
            if (voo.getNumero() == numero) {
                return voo;
            }
        }
        return null;
    }

    public Passageiro buscarPassageiro(String cpf) {
        for (Passageiro passageiro : this.passageiros) {
            if (passageiro.getCpf().equals(cpf)) {
                return passageiro;
            }
        }
        return null;
    }

    public ArrayList<Voo> voosDisponiveis(String origem, String destino) {
        ArrayList<Voo> voosDisponiveis = new ArrayList<>();

        for (Voo voo : this.voos) {
            if (voo.getOrigem().equals(origem)
                    && voo.getDestino().equals(destino)) {
                voosDisponiveis.add(voo);
            }
        }

        if (voosDisponiveis.isEmpty()) {
            return null;
        }

        return voosDisponiveis;
    }

    public boolean fazerReserva(Passageiro passageiro, int numeroVoo, ClasseVoo classe) {
        for (Voo voo : this.voos) {
            if (voo.getNumero() == numeroVoo) {
                return voo.reservarAssento(passageiro, classe);
            }
        }

        return false;
    }

    public ArrayList<Voo> voosComOverbooking() {
        ArrayList<Voo> voosComOverbooking = new ArrayList<>();

        for (Voo voo : this.voos) {
            if (voo.temOverbooking()) {
                voosComOverbooking.add(voo);
            }
        }

        return voosComOverbooking;
    }

    public ArrayList<Voo> voosMaisLotado(int top) {
        ArrayList<Voo> voosMaisLotado = new ArrayList<>(voos);

        Comparator<Voo> comparador =
                Comparator.comparingDouble(Voo::taxaOcupacao).reversed();

        voosMaisLotado.sort(comparador);

        int quantidade = Math.min(top, voosMaisLotado.size());

        return new ArrayList<>(voosMaisLotado.subList(0, quantidade));
    }

    public Passageiro passageiroComMaisMilhas() {
        if (passageiros.isEmpty()) {
            return null;
        }

        Passageiro passageiroComMaisMilhas = passageiros.getFirst();

        for (Passageiro passageiro : passageiros) {
            if (passageiro.getMilhas() > passageiroComMaisMilhas.getMilhas()) {
                passageiroComMaisMilhas = passageiro;
            }
        }

        return passageiroComMaisMilhas;
    }

    public double receitaTotalCia() {
        double receita = 0;

        for (Voo voo : this.voos) {
            receita += voo.receitaTotal();
        }

        return receita;
    }

    public ArrayList<Bagagem> bagensComExcesso() {
        ArrayList<Bagagem> bagensComExcesso = new ArrayList<>();

        for (Voo voo : this.voos) {
            for (Bagagem bagagem : voo.getBagagens()) {
                if (bagagem.excedeLimite()) {
                    bagensComExcesso.add(bagagem);
                }
            }
        }

        return bagensComExcesso;
    }

    public double arrecadacaoTaxasBagagem() {
        double taxa = 0;

        for (Voo voo : this.voos) {
            for (Bagagem bagagem : voo.getBagagens()) {
                taxa += bagagem.calcularTaxaExcesso();
            }
        }

        return taxa;
    }

    public String rotaMaisPopular() {
        HashMap<String, Integer> rotaMaisPopular = new HashMap<>();

        for (Voo voo : this.voos) {
            String rota = voo.getOrigem() + "-" + voo.getDestino();

            if (rotaMaisPopular.containsKey(rota)) {
                rotaMaisPopular.put(
                        rota,
                        rotaMaisPopular.get(rota) + 1
                );
            } else {
                rotaMaisPopular.put(rota, 1);
            }
        }

        int maiorQuantidade = 0;
        String rotaMP = null;

        for (String rota : rotaMaisPopular.keySet()) {
            int quantidade = rotaMaisPopular.get(rota);

            if (quantidade > maiorQuantidade) {
                maiorQuantidade = quantidade;
                rotaMP = rota;
            }
        }

        return rotaMP;
    }

    public void exibir() {
        System.out.println("Nome: " + nome);
        System.out.println("Quantidade de voos: " + voos.size());
        System.out.println("Receita total: " + receitaTotalCia());
        System.out.println("Rota mais popular: " + rotaMaisPopular());
        System.out.println("Passageiro com mais milhas: " + passageiroComMaisMilhas());
        System.out.println("Arrecadação com bagens: " + arrecadacaoTaxasBagagem());
        System.out.println("Voos com overbooking: " + voosComOverbooking());
    }
}