package CorretoraDeImoveis;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TesteImobiliaria {
    public static void main(String[] args) {
        Imobiliaria imobiliaria = new Imobiliaria("Imobiliária Elite Reais");
        Random random = new Random(42);

        String[] nomesCorretores = {
                "Roberto Andrade", "Camila Guimarães", "Marcelo Oliveira", "Juliana Paes",
                "Fernando Pestana", "Patricia Abravanel", "Gabriel Monteiro", "Vanessa Camargo",
                "Thiago Lacerda", "Amanda Ribeiro"
        };
        String[] crecis = {
                "CRECI-14205-F", "CRECI-28910-F", "CRECI-33104-F", "CRECI-19482-F",
                "CRECI-41029-F", "CRECI-22019-F", "CRECI-58192-F", "CRECI-31048-F",
                "CRECI-17482-F", "CRECI-29401-F"
        };
        double[] comissoes = {5.0, 5.5, 6.0, 5.0, 5.5, 6.0, 5.0, 5.5, 6.0, 5.0};

        for (int i = 0; i < 10; i++) {
            imobiliaria.cadastrarCorretor(new Corretor(i + 1, nomesCorretores[i], crecis[i], comissoes[i]));
        }

        Tipo[] tiposDisponiveis = Tipo.values();
        String[] bairros = {"Jardins", "Moema", "Alphaville", "Leblon", "Savassi", "Batel", "Pinheiros", "Ipanema"};
        List<String> codigosImoveis = new ArrayList<>();

        for (int i = 1; i <= 40; i++) {
            Tipo tipo = tiposDisponiveis[i % tiposDisponiveis.length];
            String bairro = bairros[i % bairros.length];
            String codigo = "IMO-" + i;
            String endereco = "Rua dos " + bairro + ", nº " + (100 + i);
            double valorAnuncio = 250000.0 + (i * 35000.0);
            double areaM2 = 50.0 + (i * 3.0);
            int quartos = (i % 4) + 1;
            String proprietario = "Proprietário " + i;

            codigosImoveis.add(codigo);
            imobiliaria.cadastrarImovel(new Imovel(codigo, tipo, endereco, valorAnuncio, areaM2, quartos, proprietario));
        }

        String[] nomes = {"Carlos", "Beatriz", "Lucas", "Mariana", "Rafael", "Fernanda", "Bruno", "Leticia", "Diego", "Aline"};
        String[] sobrenomes = {"Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Almeida", "Pereira", "Lima", "Gomes"};
        List<String> cpfsClientes = new ArrayList<>();

        for (int i = 1; i <= 60; i++) {
            String nomeCompleto = nomes[i % nomes.length] + " " + sobrenomes[(i * 3) % sobrenomes.length];
            String cpf = String.format("%03d.%03d.%03d-%02d", 100 + i, 200 + i, 300 + i, i % 99);
            String telefone = "(11) 9" + String.format("%04d-%04d", 8000 + i, 1000 + i);
            double orcamento = 400000.0 + (i * 25000.0);

            cpfsClientes.add(cpf);
            imobiliaria.cadastrarCliente(new Cliente(nomeCompleto, cpf, telefone, orcamento));
        }

        String imovelDestaque = codigosImoveis.get(3);
        for (int i = 0; i < 40; i++) {
            imobiliaria.agendarVisita(imovelDestaque, LocalDate.now().minusDays(i % 15));
        }
        for (int i = 0; i < 60; i++) {
            String codRandom = codigosImoveis.get(random.nextInt(codigosImoveis.size()));
            imobiliaria.agendarVisita(codRandom, LocalDate.now().minusDays(i % 30));
        }

        List<Proposta> propostasCriadas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            String cpf = cpfsClientes.get(i % cpfsClientes.size());
            String codigoImovel = codigosImoveis.get(i % 20);
            String creci = crecis[i % crecis.length];
            double valorOferta = 240000.0 + (i * 30000.0);

            Proposta p = imobiliaria.fazerProposta(cpf, codigoImovel, creci, valorOferta);
            propostasCriadas.add(p);
        }

        for (int id = 1; id <= 20; id++) {
            imobiliaria.aceitarProposta(id);
        }

        int recusadas = 0;
        for (Proposta p : propostasCriadas) {
            if (recusadas >= 15) break;
            if (p.getStatusProposta() == StatusProposta.PENDENTE) {
                p.recusar();
                recusadas++;
            }
        }

        Tipo tipoConsulta = tiposDisponiveis[0];
        System.out.println("=== IMÓVEIS DISPONÍVEIS (" + tipoConsulta + ") ===");
        List<Imovel> disponiveis = imobiliaria.imoveisDisponivels(tipoConsulta);
        for (Imovel imovel : disponiveis) {
            System.out.println("Código: " + imovel.getCodigo() + " | Valor Anúncio: R$ " + String.format("%.2f", imovel.getValorAnuncio()));
        }

        System.out.println("\n=== IMÓVEL MAIS VISITADO ===");
        Imovel maisVisitado = imobiliaria.imovelMaisVisitado();
        if (maisVisitado != null) {
            System.out.println("Código: " + maisVisitado.getCodigo() + " | Total de Visitas: " + maisVisitado.totalVisitas());
        }

        System.out.println("\n=== CORRETOR MAIS VENDEDOR ===");
        Corretor campeao = imobiliaria.corretorQueMaisVende();
        if (campeao != null) {
            System.out.println("Nome: " + campeao.getNome() + " | CRECI: " + campeao.getCreci() + " | Vendas Fechadas: " + campeao.getVendasRealizadas());
        }

        System.out.println("\n=== COMISSÃO TOTAL DE 3 CORRETORES ===");
        for (int i = 0; i < 3; i++) {
            String creciConsultado = crecis[i];
            double comissao = imobiliaria.comissaoTotalCorretor(creciConsultado);
            System.out.println(nomesCorretores[i] + " (" + creciConsultado + ") -> Comissão Acumulada: R$ " + String.format("%.2f", comissao));
        }

        System.out.println("\n=== TAXA DE CONVERSÃO DO SISTEMA ===");
        System.out.println("Taxa: " + String.format("%.2f", imobiliaria.taxaConversao()) + "%");

        System.out.println("\n=== RELATÓRIO GERAL DA IMOBILIÁRIA ===");
        imobiliaria.relatorioGeral();
    }
}