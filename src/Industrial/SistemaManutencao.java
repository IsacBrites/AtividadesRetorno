package Industrial;

import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaManutencao {
    private ArrayList<Equipamento> equipamentos;
    private ArrayList<Peca> pecas;
    private ArrayList<Tecnico> tecnicos;
    private ArrayList<OrdemServico> ordens;
    private static int proximoNumeroOs = 1;

    public SistemaManutencao(){
        equipamentos = new ArrayList<>();
        pecas = new ArrayList<>();
        tecnicos = new ArrayList<>();
        ordens = new ArrayList<>();
    }

    public void cadastrarEquipamento(Equipamento equipamento){
        equipamentos.add(equipamento);
    }

    public void cadastrarPeca(Peca peca){
        pecas.add(peca);
    }

    public void cadastrarTecnico(Tecnico tecnico){
        tecnicos.add(tecnico);
    }

    public OrdemServico abrirOrdem(String codigoEquip, Tipo tipo, String descricao, LocalDate data){
        for (Equipamento equipamento : equipamentos){
            if (equipamento.getCodigo().equalsIgnoreCase(codigoEquip)){
                equipamento.alterarStatus(Status.EM_MANUTENCAO);
                OrdemServico novaOrdem = new OrdemServico(proximoNumeroOs, equipamento, null, tipo, data, descricao);
                proximoNumeroOs++;
                ordens.add(novaOrdem);
                return novaOrdem;
            }
        }
        return null;
    }

    public void atribuirTecnico(int numOrdem, Tecnico tecnico){
        for (OrdemServico ordem : ordens){
            if (ordem.getNumero() == numOrdem){
                ordem.setTecnico(tecnico);
                return;
            }
        }
    }

    public void finalizarOrdem(int numOrdem, LocalDate dataConclusao){
        for (OrdemServico ordem : ordens){
            if (ordem.getNumero() == numOrdem){
                ordem.concluir(dataConclusao);
                ordem.setDataConclusao(dataConclusao);
                return;
            }
        }
    }

    public ArrayList<Equipamento> verificarPreventiva(){
        ArrayList<Equipamento> equipamentosPreventiva = new ArrayList<>();
        for (Equipamento equipamento : equipamentos){
            if (equipamento.precisaManutencaoPreventiva()){
                equipamentosPreventiva.add(equipamento);
            }
        }
        return equipamentosPreventiva;
    }

    public ArrayList<Peca> pecasEmFalta() {
        ArrayList<Peca> pecasEmFalta = new ArrayList<>();
        for (Peca peca : pecas){
            if (peca.estoqueEsgotando()){
                pecasEmFalta.add(peca);
            }
        }
        return pecasEmFalta;
    }

    public ArrayList<OrdemServico> ordensAbertas(){
        ArrayList<OrdemServico> ordensAbertas = new ArrayList<>();
        for (OrdemServico ordem : ordens){
            if (ordem.getStatusOrdem() == StatusOrdem.ABERTA){
                ordensAbertas.add(ordem);
            }
        }
        return ordensAbertas;
    }

    public double custoTotalManutencao(){
        double total = 0;
        for (OrdemServico ordem : ordens){
            if (ordem.getStatusOrdem() == StatusOrdem.CONCLUIDA){
                total += ordem.custoTotal();
            }
        }
        return total;
    }

    public Equipamento equipamentoComMaisFalhas(){
        Equipamento equipamentoComMaisFalha = null;
        int maiorContagem = 0;
        for (Equipamento equipamento : equipamentos){
            int contador = 0;
            for (OrdemServico ordem : ordens){
                if (ordem.getEquipamento().equals(equipamento) && ordem.getTipo() == Tipo.CORRETIVA){
                    contador++;
                }
            }
            if (contador > maiorContagem){
                maiorContagem = contador;
                equipamentoComMaisFalha = equipamento;
            }
        }
        return equipamentoComMaisFalha;
    }

    public Tecnico tecnicoMaisProdutivo(){
        Tecnico tecnicoMaisProdutivo = null;
        int maiorContagem = 0;
        for (Tecnico tecnico : tecnicos){
           if (tecnico.getOrdensAtendidas() > maiorContagem){
               maiorContagem = tecnico.getOrdensAtendidas();
               tecnicoMaisProdutivo = tecnico;
           }

        }
        return tecnicoMaisProdutivo;
    }

    public double tempoMedioResolucao(){
        long tempoMedioResolucao = 0;
        int ordensConcluidas = 0;
        for (OrdemServico ordem : ordens){
            if (ordem.getStatusOrdem() == StatusOrdem.CONCLUIDA){
                tempoMedioResolucao += ordem.tempoResolucao();
                ordensConcluidas++;
            }
        }
        if (ordensConcluidas == 0){
            return 0;
        }
        return (double) tempoMedioResolucao / ordensConcluidas;
    }

    public String taxaPreventivaVsCorretiva(){
        int totalPreventiva = 0;
        int totalCorretiva = 0;

        for(OrdemServico ordem : ordens){
            if (ordem.getTipo() == Tipo.CORRETIVA){
                totalCorretiva++;
            }
            if (ordem.getTipo() == Tipo.PREVENTIVA){
                totalPreventiva++;
            }
        }
        int totalOrdens = totalCorretiva + totalPreventiva;

        if (totalOrdens == 0){
            return "";
        }

        double porcCorretiva = (double) (totalCorretiva * 100) /totalOrdens;
        double porcPreventiva = (double) (totalPreventiva * 100) /totalOrdens;

        return String.format("Preventivas: %.1f%% | Corretivas: %.1f%%", porcPreventiva, porcCorretiva);
    }

    public void relatorioGeral(){
        System.out.println("Total de equipamentos: "+ equipamentos.size());
        System.out.println("Precisnado de preventiva: "+ verificarPreventiva().size());
        System.out.println("Ordens abertas: "+ ordens.size());
        System.out.println("Peças em falta: "+pecasEmFalta().size());
        System.out.println("Custo total manutenção: "+custoTotalManutencao());
        Equipamento eq = equipamentoComMaisFalhas();
        System.out.println("Equipamento com mais falhas: " + (eq != null ? eq.getCodigo() : "Nenhum"));
        Tecnico tec = tecnicoMaisProdutivo();
        System.out.println("Técnico mais produtivo: " + (tec != null ? tec.getNome() : "Nenhum"));
        System.out.println("Tempo medio resolucao: "+tempoMedioResolucao());
        System.out.println("Taxa de ordens: "+taxaPreventivaVsCorretiva());
    }
}
