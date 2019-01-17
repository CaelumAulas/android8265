package br.com.caelum.casadocodigo;

import android.app.Application;

import br.com.caelum.casadocodigo.inject.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo.inject.DaggerCasaDoCodigoComponent;
import br.com.caelum.casadocodigo.modelo.Carrinho;

public class MinhaAplicacao extends Application {
    private CasaDoCodigoComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component =  DaggerCasaDoCodigoComponent.builder().build();
    }

    public CasaDoCodigoComponent getComponent() {
        return component;
    }
}
