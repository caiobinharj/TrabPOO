public class Denuncia {
    private Usuario usuarioDenunciante;
    private Usuario usuarioDenunciado;
    private String motivo;

    public Denuncia(Usuario usuarioDenunciante, Usuario usuarioDenunciado, String motivo) {
        this.usuarioDenunciante = usuarioDenunciante;
        this.usuarioDenunciado = usuarioDenunciado;
        this.motivo = motivo;
    }

    public Usuario getDenunciante() {
        return usuarioDenunciante;
    }

    public Usuario getUsuarioDenunciado() {
        return usuarioDenunciado;
    }

    public String getMotivo() {
        return motivo;
    }
}
