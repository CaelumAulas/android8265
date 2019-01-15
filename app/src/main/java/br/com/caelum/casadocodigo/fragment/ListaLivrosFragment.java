package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapterRecyclerView;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {
    @BindView(R.id.lista_livros) RecyclerView recyclerView;
    private List<Livro> livros = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup pai, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, pai, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(new LivroAdapterRecyclerView(livros));

        return view;
    }

    public void populaLista(List<Livro> livros) {
        this.livros.clear();
        this.livros.addAll(livros);
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }
}
