package br.com.caelum.casadocodigo.service;

public class ErroRequisicaoEvent {
    private Throwable throwable;

    public ErroRequisicaoEvent(Throwable throwable) {
        this.throwable = throwable;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
