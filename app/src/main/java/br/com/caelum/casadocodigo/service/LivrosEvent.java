package br.com.caelum.casadocodigo.service;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;

public class LivrosEvent {

    private List<Livro> livros;

    public LivrosEvent(List<Livro> livros) {
        this.livros = livros;
    }

    public List<Livro> getLivros() {
        return livros;
    }
}
