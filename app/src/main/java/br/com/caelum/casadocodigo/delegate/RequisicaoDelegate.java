package br.com.caelum.casadocodigo.delegate;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;

public interface RequisicaoDelegate {
    public void lidaComSucesso(List<Livro> livros);

    void lidaComErro(Throwable t);
}
