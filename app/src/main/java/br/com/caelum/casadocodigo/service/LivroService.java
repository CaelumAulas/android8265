package br.com.caelum.casadocodigo.service;

import java.util.List;

import br.com.caelum.casadocodigo.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LivroService {

    @GET("listarLivros")
    Call<List<Livro>> listaLivros(@Query("indicePrimeiroLivro") int indiceDoPrimeiroLivro, @Query("qtdLivros") int quantidadeLivros);

}
