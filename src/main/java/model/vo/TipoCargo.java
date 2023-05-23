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

    public static TipoCargo getTipoCargoPorValor(int valor) {
        TipoCargo tipoCargo = null;
        for (TipoCargo elemento : TipoCargo.values()) {
            if (elemento.getValor() == valor) {
                tipoCargo = elemento;
            }
        }
        return tipoCargo;
    }
}
