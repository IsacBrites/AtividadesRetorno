package Livraria;

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    private String nome;
    private ArrayList<Livro> livros;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Emprestimo> emprestimos;
    private ArrayList<Reserva> reservas;
    private ArrayList<Autor> autores;
    private static int proximoIdEmprestimo = 1;
    private static int proximoIdReserva = 1;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.reservas = new ArrayList<>();
        this.autores = new ArrayList<>();
    }

    private Usuario buscarUsuario(String matricula) {
        for (int i = 0; i < this.usuarios.size(); i++) {
            Usuario usuario = this.usuarios.get(i);
            if (usuario.getMatricula().equals(matricula)) {
                return usuario;
            }
        }
        return null;
    }

    private Livro buscarLivro(int idLivro) {
        for (int i = 0; i < this.livros.size(); i++) {
            Livro livro = this.livros.get(i);
            if (livro.getId() == idLivro) {
                return livro;
            }
        }
        return null;
    }

    public Emprestimo realizarEmprestimo(String matricula, int idLivro, LocalDate data) {
        Usuario usuario = buscarUsuario(matricula);
        Livro livro = buscarLivro(idLivro);

        if (usuario == null || livro == null) {
            return null;
        }

        if (!usuario.podeEmprestar() || !livro.temDisponivel()) {
            return null;
        }
        Emprestimo emprestimo = new Emprestimo(proximoIdEmprestimo, livro, usuario, data);
        emprestimos.add(emprestimo);
        usuario.adicionarEmprestimo(proximoIdEmprestimo);
        livro.retirarExemplares();

        proximoIdEmprestimo++;
        return emprestimo;


    }

    private Emprestimo buscarEmprestimo(int id) {
        for (int i = 0; i < this.emprestimos.size(); i++) {
            Emprestimo emprestimo = this.emprestimos.get(i);

            if (emprestimo.getId() == id) {
                return emprestimo;
            }
        }
        return null;
    }

    public void devolverLivro(int idEmprestimo, LocalDate data) {

        Emprestimo emprestimo = buscarEmprestimo(idEmprestimo);

        if (emprestimo == null) {
            return;
        }

        double multa = emprestimo.calcularMulta(data);

        emprestimo.devolver(data);

        emprestimo.getUsuario().removerEmprestimo(idEmprestimo);

        if (multa > 0) {
            emprestimo.getUsuario().adicionarMulta(multa);
        }
    }

    public boolean renovarEmprestimo(int idEmprestimo, LocalDate novaData) {
        Emprestimo emprestimo = buscarEmprestimo(idEmprestimo);
        if (emprestimo == null) {
            return false;
        }
        return emprestimo.renovar(novaData);
    }

    public Reserva criarReserva(String matricula, int idLivro, LocalDate data) {
        Usuario usuario = buscarUsuario(matricula);
        Livro livro = buscarLivro(idLivro);

        if (usuario == null || livro == null) {
            return null;
        }
        if (livro.temDisponivel()) {
            return null;
        }

        int posicao = 1;

        for (Reserva reserva : reservas) {
            if (reserva.getLivro().getId() == idLivro && !reserva.isAtendida()) {
                posicao++;
            }
        }

        Reserva reserva = new Reserva(proximoIdReserva, livro, usuario, data, posicao);
        proximoIdReserva++;

        return reserva;
    }

    public ArrayList<Reserva> filasReserva(int idLivro) {

        ArrayList<Reserva> fila = new ArrayList<>();

        for (Reserva reserva : this.reservas) {

            if (reserva.getLivro().getId() == idLivro && !reserva.isAtendida()) {
                fila.add(reserva);
            }
        }
        return fila;
    }

    public ArrayList<Emprestimo> livrosAtrasados(LocalDate dataAtual) {
        ArrayList<Emprestimo> atrasados = new ArrayList<>();

        for (Emprestimo emprestimo : this.emprestimos) {
            if (emprestimo.estaAtrasado(dataAtual)) {
                atrasados.add(emprestimo);
            }
        }
        return atrasados;
    }

    public ArrayList<Usuario> usuariosComMultas() {
        ArrayList<Usuario> usuariosComMultas = new ArrayList<>();

        for (Usuario usuario : this.usuarios) {
            if (usuario.getMultasPendentes() > 0) {
                usuariosComMultas.add(usuario);
            }
        }
        return usuariosComMultas;
    }

    public Livro livroMaisEmprestado() {
        Livro maisEmprestado = null;
        int maiorQuantidade = 0;
        for (Livro livro : livros) {
            int quantidade = 0;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLivro().getId() == livro.getId()) {
                    quantidade++;
                }
            }
            if (quantidade > maiorQuantidade) {
                maiorQuantidade = quantidade;
                maisEmprestado = livro;
            }
        }
        return maisEmprestado;
    }

    public Autor autorMaisLido() {
        Autor maisLido = null;
        int maiorQuantidade = 0;
        for (Autor autor : autores) {
            int quantidade = 0;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getLivro().getId() == autor.getId()) {
                    quantidade++;

                }
            }
            if (quantidade > maiorQuantidade) {
                maiorQuantidade = quantidade;
                maisLido = autor;
            }
        }
        return maisLido;
    }

    public double taxaDeOcupacaoAcervo() {
        double totalAcervo = 0;
        double emprestado = 0;
        for (Livro livro : livros) {
            totalAcervo += livro.getTotalExemplares();
            emprestado = livro.getTotalExemplares() - livro.getExemplaresDisponiveis();
        }
        if (totalAcervo == 0) {
            return 0;
        }
        return (emprestado / totalAcervo) * 100;
    }

    public double relatorioMultas() {
        double totalMultas = 0;
        for (Usuario usuario : usuarios) {
            totalMultas += usuario.getMultasPendentes();
        }
        return totalMultas;
    }

    public void relatorioGeral() {
        System.out.println("= RELATÓRIO GERAL =");

        System.out.println("Total de livros: " + livros.size());

        int ativos = 0;
        for (Emprestimo emprestimo : emprestimos) {
            if (!emprestimo.isDevolvido()) {
                ativos++;
            }
        }
        System.out.println("Empréstimos ativos: " + ativos);

        System.out.println("Livros atrasados: " + livrosAtrasados(LocalDate.now()).size());

        int reservasPendentes = 0;
        for (Reserva reserva : reservas) {
            if (!reserva.isAtendida()) {
                reservasPendentes++;
            }
        }
        System.out.println("Reservas pendentes: " + reservasPendentes);

        System.out.println("Multas pendentes: R$ " + relatorioMultas());

        Livro livro = livroMaisEmprestado();
        if (livro != null) {
            System.out.println("Livro mais emprestado: " + livro.getTitulo());
        }

        Autor autor = autorMaisLido();
        if (autor != null) {
            System.out.println("Autor mais lido: " + autor.getNome());
        }

        System.out.printf("Taxa de ocupação: %.2f%%\n", taxaDeOcupacaoAcervo());
    }

    public void cadastrarLivro(Livro livro) {
        livros.add(livro);
    }

    public void cadastrarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public void cadastrarAutor(Autor autor) {
        autores.add(autor);
    }

    public static void main(String[] args) {

        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");


        Autor[] autores = new Autor[15];

        for (int i = 0; i < 15; i++) {
            autores[i] = new Autor(i + 1, "Autor " + (i + 1), "Brasil");
            biblioteca.cadastrarAutor(autores[i]);
        }

        for (int i = 0; i < 60; i++) {

            Autor autor = autores[i % 15];

            Livro livro = new Livro(
                    i + 1,
                    "Livro " + (i + 1),
                    autor,
                    "Categoria",
                    3,
                    false
            );

            autor.adicionarObra(livro.getId());

            biblioteca.cadastrarLivro(livro);
        }


        for (int i = 0; i < 50; i++) {

            String tipo;

            if (i < 40) {
                tipo = "aluno";
            } else {
                tipo = "professor";
            }

            Usuario usuario = new Usuario(
                    "2026" + i,
                    "Usuario " + i,
                    tipo,
                    "SI"
            );

            biblioteca.cadastrarUsuario(usuario);
        }


        for (int i = 0; i < 100; i++) {

            String matricula = "2026" + (i % 50);

            int livro = (i % 60) + 1;

            biblioteca.realizarEmprestimo(
                    matricula,
                    livro,
                    LocalDate.now()
            );
        }

        for (int i = 1; i <= 20; i++) {

            biblioteca.renovarEmprestimo(
                    i,
                    LocalDate.now().plusDays(21)
            );

        }

        for (int i = 0; i < 15; i++) {

            biblioteca.criarReserva(
                    "2026" + i,
                    (i % 10) + 1,
                    LocalDate.now()
            );

        }

        for (int i = 1; i <= 70; i++) {

            biblioteca.devolverLivro(
                    i,
                    LocalDate.now().plusDays(20)
            );

        }


        System.out.println("\nUSUÁRIOS COM MULTAS");

        for (Usuario usuario : biblioteca.usuariosComMultas()) {
            usuario.exibir();
        }

        System.out.println("\nLIVROS ATRASADOS");

        for (Emprestimo e : biblioteca.livrosAtrasados(LocalDate.now())) {
            e.exibir();
        }

        System.out.println("\nLivro mais emprestado:");

        Livro livro = biblioteca.livroMaisEmprestado();

        if (livro != null) {
            livro.exibir();
        }

        System.out.println("\nAutor mais lido:");

        Autor autor = biblioteca.autorMaisLido();

        if (autor != null) {
            autor.exibir();
        }

        System.out.printf("\nTaxa de ocupação: %.2f%%\n",
                biblioteca.taxaDeOcupacaoAcervo());

        System.out.println("\nMultas totais: R$ "
                + biblioteca.relatorioMultas());

        biblioteca.relatorioGeral();

    }
}


