package model.vo;

public enum TipoDescricaoFaxina {
	
	FAXINA (1),
    ZELADORIA (2),
    TECNICO (3);

    private int valor;

    private TipoDescricaoFaxina(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoDescricaoFaxina getTipoDescricaoFaxinaPorValor(int valor) {
        TipoDescricaoFaxina tipoDescricaoFaxina = null;
        for (TipoDescricaoFaxina elemento : TipoDescricaoFaxina.values()) {
            if (elemento.getValor() == valor) {
                tipoDescricaoFaxina = elemento;
            }
        }
        return tipoDescricaoFaxina;
    }

}
