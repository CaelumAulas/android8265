package br.com.caelum.casadocodigo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapterListaUnica;
import br.com.caelum.casadocodigo.adapter.LivroAdapterRecyclerView;
import br.com.caelum.casadocodigo.listener.MeuScrollListener;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ListaLivrosFragment extends Fragment {
    @BindView(R.id.lista_livros) RecyclerView recyclerView;
    private List<Livro> livros = new ArrayList<>();
    private FirebaseRemoteConfig firebaseRemoteConfig;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setDefaults(R.xml.remote_config_defaults);

        firebaseRemoteConfig.fetch(20).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Log.i("Teste A/B", "deu certo!");
                    firebaseRemoteConfig.activateFetched();
                } else {
                    Log.i("Teste A/B", "falhou", task.getException());
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup pai, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, pai, false);
        ButterKnife.bind(this, view);

        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));

        if (firebaseRemoteConfig.getBoolean("lista_item_unico")) {
            recyclerView.setAdapter(new LivroAdapterListaUnica(livros));
        } else {
            recyclerView.setAdapter(new LivroAdapterRecyclerView(livros));
        }
        recyclerView.addOnScrollListener(new MeuScrollListener());

        return view;
    }

    public void populaLista(List<Livro> livros) {
        this.livros.addAll(livros);
        Log.i("LIVROS", String.valueOf(this.livros.size()));
        this.recyclerView.getAdapter().notifyDataSetChanged();
    }
}
