package br.com.caelum.casadocodigo.service;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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
    private RequisicaoDelegate delegate;

    public WebClient(RequisicaoDelegate delegate) {
        this.delegate = delegate;
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

                delegate.lidaComSucesso(livros);
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                delegate.lidaComErro(t);
            }
        });
    }
}
