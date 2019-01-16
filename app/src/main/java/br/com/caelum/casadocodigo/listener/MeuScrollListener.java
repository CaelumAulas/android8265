package br.com.caelum.casadocodigo.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import br.com.caelum.casadocodigo.service.WebClient;

public class MeuScrollListener extends RecyclerView.OnScrollListener {
        private int totalDeItensAtualmente = 0;
        private int totalDeItensAnteriormente = 0;
        private int ultimoItemVisivel = 0;
    private boolean carregandoLivros = true;

    @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int qtdScrollHorizontal, int qtdScrollVertical) {
            totalDeItensAtualmente = recyclerView.getLayoutManager().getItemCount();
            LinearLayoutManager linearLayoutManager = (LinearLayoutManager)recyclerView.getLayoutManager();
            ultimoItemVisivel = linearLayoutManager.findLastVisibleItemPosition();


        if (!carregandoLivros && ultimoItemVisivel >= totalDeItensAtualmente -1) {
            if (totalDeItensAtualmente < 20) {
                new WebClient(recyclerView.getContext()).pegaLivros(totalDeItensAtualmente, 5);
                carregandoLivros = true;
            }
        }

        if (carregandoLivros) {
                if (totalDeItensAtualmente > totalDeItensAnteriormente) {
                    totalDeItensAnteriormente = totalDeItensAtualmente;
                    carregandoLivros = false;
                }
            }



        }

}
