package EmprestimosTI;

import java.time.LocalDate;

public class TesteDepartamento {
    public static void main(String[] args) {
        DepartamentoTI departamento = new DepartamentoTI();

        Funcionario f1 = new Funcionario("F001", "Ana Silva", "Desenvolvimento");
        Funcionario f2 = new Funcionario("F002", "Carlos Souza", "Infraestrutura");
        departamento.cadastrarFuncionario(f1);
        departamento.cadastrarFuncionario(f2);

        Equipamento e1 = new Equipamento("EQ001", Tipo.NOTEBOOK, "Dell Latitude 5420",Estado.NOVO);
        Equipamento e2 = new Equipamento("EQ002", Tipo.MONITOR, "Monitor LG 29 UltraWide", Estado.BOM);
        Equipamento e3 = new Equipamento("EQ003",Tipo.PERIFERICO, "Teclado Mecânico Logitech", Estado.BOM);
        departamento.cadastrarEquipamento(e1);
        departamento.cadastrarEquipamento(e2);
        departamento.cadastrarEquipamento(e3);

        LocalDate hoje = LocalDate.now();

        System.out.println(" REALIZANDO EMPRÉSTIMOS ");
        departamento.emprestarEquipamento("F001", "EQ001", hoje.minusDays(10));
        departamento.emprestarEquipamento("F002", "EQ002", hoje.minusDays(20));

        System.out.println("\n=== GESTÃO DE MANUTENÇÃO ===");
        departamento.abrirChamado("EQ003", "Teclas do teclado falhando", hoje.minusDays(5));

        departamento.resolverChamado(1, hoje.minusDays(3), 120.0);

        System.out.println("\n PROCESSANDO DEVOLUÇÃO ");
        departamento.devolverEquipamento(1, hoje, "Devolvido sem avarias", Estado.BOM);

        System.out.println("\n RESULTADOS DE FILTROS E RELATÓRIOS ");

        System.out.println("\n[Equipamentos Disponíveis do Tipo NOTEBOOK]:");
        departamento.equipamentosDisponiveis(Tipo.NOTEBOOK).forEach(eq -> System.out.println("- " + eq.getPatrimonio() + ": " + eq.getModelo()));

        System.out.println("\n[Empréstimos Atrasados]:");
        departamento.emprestimosAtrasados(hoje).forEach(emp -> System.out.println("- ID: " + emp.getId() + " | Funcionario: " + emp.getFuncionario().getNome()));

        System.out.println("\n[Métricas Financeiras e de Manutenção]:");
        System.out.println("- Custo Total de Manutenções: R$ " + departamento.custoTotalManutencao());
        System.out.println("- Tempo Médio de Resolução de Chamados: " + departamento.tempoMedioResolucaoChamado() + " dia(s)");

        System.out.println("\n=== RESUMO GERAL DO DEPARTAMENTO ===");
        departamento.resumoSistema();
    }
}