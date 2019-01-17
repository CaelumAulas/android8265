package br.com.caelum.casadocodigo.fragment;

import android.app.Application;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.MinhaAplicacao;
import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo.inject.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.inject.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Autor;
import br.com.caelum.casadocodigo.modelo.Carrinho;
import br.com.caelum.casadocodigo.modelo.Item;
import br.com.caelum.casadocodigo.modelo.Livro;
import br.com.caelum.casadocodigo.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalhesLivroFragment extends Fragment {
    @BindView(R.id.detalhes_livro_foto) ImageView campoFoto;
    @BindView(R.id.detalhes_livro_nome) TextView campoNome;
    @BindView(R.id.detalhes_livro_autores) TextView campoAutores;
    @BindView(R.id.detalhes_livro_descricao) TextView campoDescricao;
    @BindView(R.id.detalhes_livro_num_paginas) TextView campoPaginas;
    @BindView(R.id.detalhes_livro_data_publicacao) TextView campoDataPublicacao;
    @BindView(R.id.detalhes_livro_isbn) TextView campoIsbn;
    @BindView(R.id.detalhes_livro_comprar_fisico) Button botaoComprarFisico;
    @BindView(R.id.detalhes_livro_comprar_ebook) Button botaoComprarEbook;
    @BindView(R.id.detalhes_livro_comprar_ambos) Button botaoComprarAmbos;

    @Inject
    Carrinho carrinho;
    private Livro livro;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);

        Bundle arguments = getArguments();
        livro = (Livro) arguments.getSerializable("livro");


        ButterKnife.bind(this,view);

        MinhaAplicacao application = (MinhaAplicacao) getActivity().getApplication();
        CasaDoCodigoComponent component = application.getComponent();
        component.inject(this);

        populaCamposCom(livro);

        return view;
    }

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void selecionaFisico() {
        Toast.makeText(getContext(), "Livro selecionado", Toast.LENGTH_LONG).show();
        Item item = new Item(livro, TipoDeCompra.FISICO);
        carrinho.adiciona(item);
    }

    @OnClick(R.id.detalhes_livro_comprar_ebook)
    public void selecionaEbook() {
        Toast.makeText(getContext(), "Livro selecionado", Toast.LENGTH_LONG).show();
        Item item = new Item(livro, TipoDeCompra.VIRTUAL);
        carrinho.adiciona(item);
    }

    @OnClick(R.id.detalhes_livro_comprar_ambos)
    public void selecionaAmbos() {
        Toast.makeText(getContext(), "Livro selecionado", Toast.LENGTH_LONG).show();
        Item item = new Item(livro, TipoDeCompra.JUNTOS);
        carrinho.adiciona(item);
    }

    private void populaCamposCom(Livro livro) {
        Picasso.get()
                .load(livro.getUrlFoto())
                .placeholder(R.drawable.livro)
                .fit()
                .into(campoFoto);


        campoNome.setText(livro.getNome());

        String autoresTexto = "";
        for (Autor autor : livro.getAutores()) {
            if (!autoresTexto.isEmpty()) {
                autoresTexto += ", ";
            }
            autoresTexto += autor.getNome();
        }
        campoAutores.setText(autoresTexto);

        campoDescricao.setText(livro.getDescricao());
        campoPaginas.setText(String.valueOf(livro.getNumPaginas()));
        campoDataPublicacao.setText(livro.getDataPublicacao());
        campoIsbn.setText(livro.getISBN());

        String textoComprarFisico = String.format("Comprar Livro Físico - R$ %.2f", livro.getValorFisico());
        botaoComprarFisico.setText(textoComprarFisico);

        String textoComprarEbook = String.format("Comprar E-book - R$ %.2f", livro.getValorVirtual());
        botaoComprarEbook.setText(textoComprarEbook);

        String textoComprarAmbos = String.format("Comprar Ambos - R$ %.2f", livro.getValorDoisJuntos());
        botaoComprarAmbos.setText(textoComprarAmbos);
    }
}
