package br.com.caelum.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.service.ErroRequisicaoEvent;
import br.com.caelum.casadocodigo.service.LivrosEvent;
import br.com.caelum.casadocodigo.service.WebClient;

public class MainActivity extends AppCompatActivity implements LivroDelegate {

    private ListaLivrosFragment listaLivrosFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new WebClient(this).pegaLivros(0,5);

        EventBus.getDefault().register(this);

        listaLivrosFragment = new ListaLivrosFragment();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.frame_principal, listaLivrosFragment);
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus eventBus = EventBus.getDefault();
        eventBus.unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_carrinho) {
            Intent intent = new Intent(this, CarrinhoActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
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

    @Subscribe
    public void lidaComSucesso(LivrosEvent event) {

        listaLivrosFragment.populaLista(event.getLivros());
    }

    @Subscribe
    public void lidaComErro(ErroRequisicaoEvent event) {
        Toast.makeText(this, "deu ruim!", Toast.LENGTH_LONG).show();
        Log.i("EXCEÇAO", event.getThrowable().getMessage());
    }

}
