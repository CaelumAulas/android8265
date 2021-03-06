package br.com.caelum.casadocodigo.activity;

import android.app.Application;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.MinhaAplicacao;
import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.adapter.ItensAdapter;
import br.com.caelum.casadocodigo.inject.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.inject.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CarrinhoActivity extends AppCompatActivity {
    @Inject
    Carrinho carrinho;
    @BindView(R.id.lista_itens_carrinho) RecyclerView recyclerView;
    @BindView(R.id.valor_carrinho) TextView valorTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);
        ButterKnife.bind(this);

        MinhaAplicacao application = (MinhaAplicacao) getApplication();
        CasaDoCodigoComponent component = application.getComponent();
        component.inject(this);

        ItensAdapter adapter = new ItensAdapter(carrinho.getItens(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        valorTotal.setText("R$ "+carrinho.getValorTotal());
    }
}
