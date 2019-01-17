package br.com.caelum.casadocodigo.inject;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.modelo.Carrinho;
import dagger.Module;
import dagger.Provides;

@Module
public class CasaDoCodigoModule {
    @Singleton
    @Provides
    public Carrinho provideCarrinho() {
        return new Carrinho();
    }
}
