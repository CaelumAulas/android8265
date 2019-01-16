package br.com.caelum.casadocodigo.service;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.casadocodigo.converter.LivroConverter;
import br.com.caelum.casadocodigo.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo.delegate.RequisicaoDelegate;
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

    public void pegaLivros() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cdcmob.herokuapp.com/")
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();

        LivroService livroService = retrofit.create(LivroService.class);
        Call<List<Livro>> call = livroService.listaLivros();
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                List<Livro> livros = response.body();
                //avisa que deu certo -> dispara evento (broadcast)

                Intent intent = new Intent("minha ação de requisição");
                intent.putExtra("deuCerto", true);
                intent.putExtra("livros", (ArrayList<Livro>) livros);
                LocalBroadcastManager.getInstance(contexto).sendBroadcast(intent);
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                //avisa que deu ruim -> dispara evento (broadcast)

                Intent intent = new Intent("minha ação de requisição");
                intent.putExtra("deuCerto", false);
                intent.putExtra("erro",t);
                LocalBroadcastManager.getInstance(contexto).sendBroadcast(intent);
            }
        });
    }
}
