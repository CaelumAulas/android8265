package br.com.caelum.casadocodigo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.delegate.LivroDelegate;
import br.com.caelum.casadocodigo.modelo.Livro;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LivroAdapterRecyclerView extends RecyclerView.Adapter {
    private List<Livro> livros;

    public LivroAdapterRecyclerView(List<Livro> livros) {
        this.livros = livros;
    }


    @Override
    public int getItemViewType(int posicao) {
        return posicao % 2; //retorna 0 ou 1
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup pai, int tipoDaView) {
        int tipoDoLayout;
        if (tipoDaView == 0) {
            tipoDoLayout = R.layout.item_livro_par;
        } else {
            tipoDoLayout = R.layout.item_livro_impar;
        }
        View viewItem = LayoutInflater.from(pai.getContext()).inflate(tipoDoLayout, pai, false);
        return new ListaLivroViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int posicao) {
        Livro livro = livros.get(posicao);

        ListaLivroViewHolder listaLivroViewHolder = (ListaLivroViewHolder) viewHolder;
        listaLivroViewHolder.campoNome.setText(livro.getNome());
        Picasso.get().load(livro.getUrlFoto()).fit().into(listaLivroViewHolder.campoFoto);
    }

    @Override
    public int getItemCount() {
        return livros.size();
    }

    class ListaLivroViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_livro_nome) TextView campoNome;
        @BindView(R.id.item_livro_foto) ImageView campoFoto;

        public ListaLivroViewHolder(@NonNull final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.item_livro)
        public void cliqueNoItem() {
            Livro livro = livros.get(getAdapterPosition());
            LivroDelegate delegate = (LivroDelegate) itemView.getContext();
            delegate.lidaComClique(livro);
        }

    }
}
