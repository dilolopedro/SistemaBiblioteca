package ao.universidade.poo.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Aluno {

    private String nome;
    private String numeroMatricula;
    private String curso;

    private List<Livro> emprestimos = new ArrayList<>();

    private static final int MAX_EMPRESTIMOS = 3;

    public Aluno(String nome, String numeroMatricula, String curso) {
        setNome(nome);
        setNumeroMatricula(numeroMatricula);
        setCurso(curso);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        this.nome = nome;
    }

    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(String numeroMatricula) {

        if (numeroMatricula == null || numeroMatricula.isBlank()) {
            throw new IllegalArgumentException("Matrícula inválida");
        }

        this.numeroMatricula = numeroMatricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {

        if (curso == null || curso.isBlank()) {
            throw new IllegalArgumentException("Curso inválido");
        }

        this.curso = curso;
    }

    public List<Livro> getEmprestimos() {
        return new ArrayList<>(emprestimos);
    }

    public boolean matricularLivro(Livro livro) {

        if (livro == null) {
            throw new IllegalArgumentException("Livro inválido");
        }

        if (emprestimos.size() >= MAX_EMPRESTIMOS) {
            System.out.println(nome + " atingiu o limite máximo de empréstimos.");
            return false;
        }

        if (!livro.emprestar()) {
            return false;
        }

        emprestimos.add(livro);
        return true;
    }

    public boolean devolverLivro(Livro livro) {

        if (livro == null) {
            throw new IllegalArgumentException("Livro inválido");
        }

        boolean removido = emprestimos.remove(livro);

        if (removido) {
            livro.devolver();
        }

        return removido;
    }

    public String mostrarEmprestimos() {

        if (emprestimos.isEmpty()) {
            return nome + " não possui empréstimos.";
        }

        StringBuilder sb = new StringBuilder();

        sb.append("\n=== LIVROS DE ")
                .append(nome.toUpperCase())
                .append(" ===\n");

        for (Livro livro : emprestimos) {
            sb.append("- ")
                    .append(livro.info())
                    .append("\n");
        }

        return sb.toString();
    }

    @Override
    public String toString() {
        return nome + " | Matrícula: " + numeroMatricula + " | Curso: " + curso;
    }
}