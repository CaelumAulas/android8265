package br.com.caelum.casadocodigo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import br.com.caelum.casadocodigo.delegate.RequisicaoDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;

public class BuscaLivrosReceiver extends BroadcastReceiver {
    private RequisicaoDelegate delegate;

    public BuscaLivrosReceiver(RequisicaoDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        boolean deuCerto = intent.getBooleanExtra("deuCerto", false);
        if(deuCerto){
            List<Livro> livros = (List<Livro>) intent.getSerializableExtra("livros");
            delegate.lidaComSucesso(livros);
        } else {
            Throwable erro = (Throwable) intent.getSerializableExtra("erro");
            delegate.lidaComErro(erro);
        }
    }
}
