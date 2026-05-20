package ao.universidade.poo.biblioteca;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Biblioteca biblioteca = new Biblioteca();

        Aluno aluno = new Aluno(
                "Utilizador Principal",
                "2025001",
                "Engenharia Informática"
        );

        int opcao;

        do {

            System.out.println(" =========== SISTEMA DE BIBLIOTECA ===========");
                    System.out.println("1 - Cadastrar livro");
            System.out.println("2 - Listar livros");
            System.out.println("3 - Emprestar livro");
            System.out.println("4 - Devolver livro");
            System.out.println("5 - Mostrar empréstimos");
            System.out.println("6 - Buscar livro por título");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:

                    try {

                        System.out.print("Título: ");
                        String titulo = scanner.nextLine();

                        System.out.print("Autor: ");
                        String autor = scanner.nextLine();

                        System.out.print("ISBN (13 caracteres): ");
                        String isbn = scanner.nextLine();

                        Livro livro = new Livro(titulo, autor, isbn);

                        biblioteca.adicionarLivro(livro);

                        System.out.println("Livro cadastrado com sucesso!");

                    } catch (IllegalArgumentException erro) {

                        System.out.println("Erro: " + erro.getMessage());
                    }

                    break;

                case 2:

                    biblioteca.listarLivros();
                    break;

                case 3:

                    System.out.print("Digite o título do livro: ");
                    String tituloEmprestimo = scanner.nextLine();

                    Livro livroEmprestimo = biblioteca.buscarPorTitulo(
                            tituloEmprestimo
                    );

                    if (livroEmprestimo == null) {

                        System.out.println("Livro não encontrado.");

                    } else {

                        boolean sucesso = aluno.matricularLivro(
                                livroEmprestimo
                        );

                        if (sucesso) {
                            System.out.println("Livro emprestado com sucesso!");
                        } else {
                            System.out.println("Livro indisponível.");
                        }
                    }

                    break;

                case 4:

                    System.out.print("Digite o título do livro: ");
                    String tituloDevolucao = scanner.nextLine();

                    Livro livroDevolucao = biblioteca.buscarPorTitulo(
                            tituloDevolucao
                    );

                    if (livroDevolucao == null) {

                        System.out.println("Livro não encontrado.");

                    } else {

                        boolean devolvido = aluno.devolverLivro(
                                livroDevolucao
                        );

                        if (devolvido) {
                            System.out.println("Livro devolvido com sucesso!");
                        } else {
                            System.out.println("Este livro não está emprestado.");
                        }
                    }

                    break;

                case 5:

                    System.out.println(aluno.mostrarEmprestimos());
                    break;

                case 6:

                    System.out.print("Digite o título do livro: ");
                    String busca = scanner.nextLine();

                    Livro encontrado = biblioteca.buscarPorTitulo(busca);

                    if (encontrado != null) {
                        System.out.println(encontrado.info());
                    } else {
                        System.out.println("Livro não encontrado.");
                    }

                    break;

                case 0:

                    System.out.println("Sistema encerrado.");
                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}