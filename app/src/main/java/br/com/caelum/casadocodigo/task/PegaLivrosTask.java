package br.com.caelum.casadocodigo.task;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.adapter.LivroAdapterRecyclerView;
import br.com.caelum.casadocodigo.converter.LivroConverter;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.WebClient;

public class PegaLivrosTask extends AsyncTask<Object, Object, List<Livro>> {

    private RecyclerView recyclerView;

    public PegaLivrosTask(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    @Override
    protected List<Livro> doInBackground(Object[] objects) {
        //faz requisiçao pro servidor
        List<Livro> livros = new WebClient().fazRequisicao();
        return livros;
    }

    @Override
    protected void onPostExecute(List<Livro> livros) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new LivroAdapterRecyclerView(livros));
    }
}
