package model.vo;

public enum TipoUsuario {

    ADMINISTRADOR (1),
    FUNCIONARIO (2);

    private int valor;

    private TipoUsuario(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoUsuario getTipoUsuarioPorValor(int valor) {
        TipoUsuario tipoUsuario = null;
        for (TipoUsuario elemento : TipoUsuario.values()) {
            if (elemento.getValor() == valor) {
                tipoUsuario = elemento;
            }
        }
        return tipoUsuario;
    }
}
