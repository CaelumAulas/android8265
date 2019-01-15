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

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapterRecyclerView;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {
    @BindView(R.id.lista_livros) RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup pai, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, pai, false);

        ArrayList<Livro> livros = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Livro livro = new Livro();
            livro.setNome("Livro Legal " + i);
            livros.add(livro);
        }

        ButterKnife.bind(this, view);
        LivroAdapterRecyclerView adapter = new LivroAdapterRecyclerView(livros);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
