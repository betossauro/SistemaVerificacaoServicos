package model.vo;

public enum TipoCargo {

    FAXINA (1),
    ZELADORIA (2),
    TECNICO (3);

    private int valor;

    private TipoCargo(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoCargo getTipoServicoPorValor(int valor) {
        TipoCargo tipoServico = null;
        for (TipoCargo elemento : TipoCargo.values()) {
            if (elemento.getValor() == valor) {
                tipoServico = elemento;
            }
        }
        return tipoServico;
    }
}
