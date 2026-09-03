package EmprestimosTI;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class DepartamentoTI {
    private ArrayList<Equipamento> equipamentos;
    private ArrayList<Funcionario> funcionarios;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<ChamadoManutencao> chamadoManutencao;
    private static int proximoIdEmprestimo = 1;
    private static int proximoIdChamado = 1;

    public DepartamentoTI() {
        this.equipamentos = new ArrayList<>();
        this.funcionarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.chamadoManutencao = new ArrayList<>();

    }

    public void cadastrarEquipamento(Equipamento equipamento) {
        this.equipamentos.add(equipamento);
    }

    public void cadastrarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    public Funcionario buscarFuncionario(String matricula){
        if (this.funcionarios.isEmpty()) {
            return null;
        }
        for (Funcionario funcionario : this.funcionarios) {
            if (funcionario.getMatricula().equals(matricula)) {
                return funcionario;
            }
        }
        return null;
    }

    public Equipamento buscarEquipamento(String patrimonio){
        if (this.equipamentos.isEmpty()) {
            return null;
        }
        for (Equipamento equipamento : this.equipamentos) {
            if (equipamento.getPatrimonio().equals(patrimonio)) {
               return equipamento;
            }
        }
        return null;
    }

    public Emprestimo buscarEmprestimo(int id){
        if (this.emprestimos.isEmpty()) {
            return null;
        }
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }
        return null;
    }

    public ChamadoManutencao buscarChamado(int id){
        if (this.chamadoManutencao.isEmpty()) {
            return null;
        }
        for (ChamadoManutencao chamadoManutencao : this.chamadoManutencao) {
            if (chamadoManutencao.getId() == id) {
                return chamadoManutencao;
            }
        }
        return null;
    }

    public Emprestimo emprestarEquipamento(String matricula, String patrimonio, LocalDate data){

        Funcionario funcionario = this.buscarFuncionario(matricula);
        Equipamento equipamento = this.buscarEquipamento(patrimonio);

        if (funcionario!=null && equipamento!=null && equipamento.isDisponivel()){
            Emprestimo emprestimo = new Emprestimo(proximoIdEmprestimo, funcionario, equipamento, data);
            proximoIdEmprestimo++;
            emprestimo.getEquipamento().setDisponivel(false);
            emprestimo.getFuncionario().pegarEquipamento(patrimonio);
            this.emprestimos.add(emprestimo);
            return emprestimo;
        }
        return null;
    }

    public void devolverEquipamento(int idEmprestimo, LocalDate data, String obs, Estado novoEstado){
        Emprestimo emprestimo = this.buscarEmprestimo(idEmprestimo);
        if (emprestimo != null && !emprestimo.isDevolvido()){
            emprestimo.devolver(data, obs, novoEstado);
            emprestimo.getFuncionario().removerEquipamento(emprestimo.getEquipamento().getPatrimonio());
        }
    }

    public ChamadoManutencao abrirChamado(String patrimonio, String problema, LocalDate data){

        Equipamento equipamento = this.buscarEquipamento(patrimonio);
        if (equipamento != null && equipamento.isDisponivel()){
            ChamadoManutencao novoChamado = new ChamadoManutencao(proximoIdChamado, equipamento, problema, data);
            proximoIdChamado++;
            equipamento.setDisponivel(false);
            chamadoManutencao.add(novoChamado);
            return novoChamado;
        }
        return null;
    }

    public void resolverChamado(int idChamado, LocalDate data, double custo){
        ChamadoManutencao chamado = this.buscarChamado(idChamado);
        if (chamado != null && !chamado.isResolvido()){
            chamado.resolver(data, custo);
        }
    }

    public ArrayList<Equipamento> equipamentosDisponiveis(Tipo tipo){
        ArrayList<Equipamento> equipamentosDisponiveis = new ArrayList<>();
        for (Equipamento equipamento : this.equipamentos) {
            if (equipamento.getTipo().equals(tipo)&&equipamento.isDisponivel()){
                equipamentosDisponiveis.add(equipamento);
            }
        }
        return equipamentosDisponiveis;
    }

    public ArrayList<Emprestimo> emprestimosAtrasados(LocalDate data){
        ArrayList<Emprestimo> emprestimosAtrasados = new ArrayList<>();
        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.estaAtrasado(data)){
                emprestimosAtrasados.add(emprestimo);
            }
        }
        return emprestimosAtrasados;
    }

    public double custoTotalManutencao(){
        double total = 0.0;
        for(ChamadoManutencao chamadoManutencao : this.chamadoManutencao){
            if (chamadoManutencao.isResolvido()){
                total += chamadoManutencao.getCustoReparo();
            }
        }
        return total;
    }

    private int contarChamadosDoEquipamento(Equipamento equipamento) {
        int total = 0;
        for (ChamadoManutencao chamado : this.chamadoManutencao) {
            if (chamado.getEquipamento().equals(equipamento)) {
                total++;
            }
        }
        return total;
    }

    public double tempoMedioResolucaoChamado(){
        long somaDias = 0;
        int totalResolvidos = 0;

        for (ChamadoManutencao chamadoManutencao : this.chamadoManutencao) {
            if (chamadoManutencao.isResolvido()){
                somaDias += ChronoUnit.DAYS.between(chamadoManutencao.getDataAbertura(), chamadoManutencao.getDataResolucao());
                totalResolvidos++;
            }
        }
        if (totalResolvidos == 0){
            return 0;
        }
        return (double) somaDias/totalResolvidos;
    }

    public void resumoSistema() {
        int equipamentosDisponiveis = 0;
        for (Equipamento eq : this.equipamentos) {
            if (eq.isDisponivel()) {
                equipamentosDisponiveis++;
            }
        }

        int chamadosResolvidos = 0;
        for (ChamadoManutencao chamado : this.chamadoManutencao) {
            if (chamado.isResolvido()) {
                chamadosResolvidos++;
            }
        }
        int chamadosAbertos = this.chamadoManutencao.size() - chamadosResolvidos;

        System.out.println("       RESUMO DO DEPARTAMENTO DE TI       ");
        System.out.println("INVENTÁRIO E PESSOAL:");
        System.out.println(" - Equipamentos Cadastrados: " + this.equipamentos.size());
        System.out.println(" - Equipamentos Disponíveis: " + equipamentosDisponiveis);
        System.out.println(" - Funcionários Cadastrados: " + this.funcionarios.size());
        System.out.println();
        System.out.println("OPERAÇÃO DE EMPRÉSTIMOS:");
        System.out.println(" - Histórico de Empréstimos: " + this.emprestimos.size());
        System.out.println(" - Empréstimos Atrasados Hoje: " + emprestimosAtrasados(LocalDate.now()).size());
        System.out.println();
        System.out.println("SUPORTE E MANUTENÇÃO:");
        System.out.println(" - Total de Chamados: " + this.chamadoManutencao.size());
        System.out.println("   * Em Aberto: " + chamadosAbertos);
        System.out.println("   * Resolvidos: " + chamadosResolvidos);
        System.out.println(" - Tempo Médio de Resolução: " + String.format("%.1f", tempoMedioResolucaoChamado()) + " dias");
        System.out.println(" - Custo Acumulado em Reparos: R$ " + String.format("%.2f", custoTotalManutencao()));
    }
}
