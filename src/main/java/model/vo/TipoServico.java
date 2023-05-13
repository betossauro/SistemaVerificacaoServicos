package model.vo;

public enum TipoServico {

    FAXINA (1),
    ZELADORIA (2),
    TECNICO (3);

    private int valor;

    private TipoServico(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoServico getTipoServicoPorValor(int valor) {
        TipoServico tipoServico = null;
        for (TipoServico elemento : TipoServico.values()) {
            if (elemento.getValor() == valor) {
                tipoServico = elemento;
            }
        }
        return tipoServico;
    }
}
