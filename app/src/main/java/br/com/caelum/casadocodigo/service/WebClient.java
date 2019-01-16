package br.com.caelum.casadocodigo.service;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WebClient {

    private Context contexto;

    public WebClient(Context contexto) {
        this.contexto = contexto;
    }

    public void pegaLivros(int indicePrimeiroLivro, int qtdLivros) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cdcmob.herokuapp.com/")
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();

        LivroService livroService = retrofit.create(LivroService.class);
        Call<List<Livro>> call = livroService.listaLivros(indicePrimeiroLivro, qtdLivros);
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                List<Livro> livros = response.body();
                //avisa que deu certo -> dispara evento (broadcast)
                LivrosEvent event = new LivrosEvent(livros);
                EventBus.getDefault().post(event);
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                //avisa que deu ruim -> dispara evento (broadcast)
                ErroRequisicaoEvent event = new ErroRequisicaoEvent(t);
                EventBus.getDefault().post(event);
            }
        });
    }
}
