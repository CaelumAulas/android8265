package br.com.caelum.casadocodigo.service;

import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import br.com.caelum.casadocodigo.converter.LivroConverter;
import br.com.caelum.casadocodigo.modelo.Livro;

public class WebClient {
    public List<Livro> fazRequisicao() {
        try {
            URL url = new URL("https://cdcmob.herokuapp.com/listarLivros?indicePrimeiroLivro=0&qtdLivros=20");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            String resposta = new Scanner(connection.getInputStream()).useDelimiter("\n").next();
            //converte de json pra lista de livros
            List<Livro> livros = new LivroConverter().fromJson(resposta);

            Log.i("LIVROS", resposta);
            return livros;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
