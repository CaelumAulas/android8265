package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.modelo.Livro;

public class LivroAdapterRecyclerView extends RecyclerView.Adapter {
    private ArrayList<Livro> livros;

    public LivroAdapterRecyclerView(ArrayList<Livro> livros) {
        this.livros = livros;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pai, int posicao) {

        View viewItem = LayoutInflater.from(pai.getContext()).inflate(R.layout.item_livro_impar, pai, false);
        return new ListaLivroViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int posicao) {
        Livro livro = livros.get(posicao);

        ListaLivroViewHolder listaLivroViewHolder = (ListaLivroViewHolder) viewHolder;
        listaLivroViewHolder.campoNome.setText(livro.getNome());
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }


    private class ListaLivroViewHolder extends RecyclerView.ViewHolder {
        private TextView campoNome;
        private ImageView campoFoto;

        public ListaLivroViewHolder(@NonNull View itemView) {
            super(itemView);

            campoNome = itemView.findViewById(R.id.item_livro_nome);
            campoFoto = itemView.findViewById(R.id.item_livro_foto);
        }
    }
}
