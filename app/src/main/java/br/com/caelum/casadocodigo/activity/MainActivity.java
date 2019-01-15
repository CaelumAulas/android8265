package br.com.caelum.casadocodigo.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.LivroAdapterRecyclerView;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.delegate.RequisicaoDelegate;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.WebClient;

public class MainActivity extends AppCompatActivity implements LivroDelegate, RequisicaoDelegate {

    private ListaLivrosFragment listaLivrosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WebClient(this).pegaLivros();

        listaLivrosFragment = new ListaLivrosFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_principal, listaLivrosFragment);
        transaction.commit();
    }

    public void lidaComClique(Livro livro) {
        //passar o livro pro fragmento de detalhes
        DetalhesLivroFragment detalhesLivroFragment = new DetalhesLivroFragment();
        Bundle args = new Bundle();
        args.putSerializable("livro", livro);
        detalhesLivroFragment.setArguments(args);
        //ir pro fragmento de detalhes
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_principal, detalhesLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void lidaComSucesso(List<Livro> livros) {
        listaLivrosFragment.populaLista(livros);
    }

    @Override
    public void lidaComErro(Throwable t) {
        Toast.makeText(this, "deu ruim!", Toast.LENGTH_LONG).show();
        Log.i("EXCEÇAO", t.getMessage());
    }

}
