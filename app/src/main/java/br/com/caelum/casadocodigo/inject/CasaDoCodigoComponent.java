package br.com.caelum.casadocodigo.inject;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo.fragment.DetalhesLivroFragment;
import dagger.Component;

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {
    void inject(DetalhesLivroFragment detalhesLivroFragment);
    void inject(CarrinhoActivity carrinhoActivity);
}
