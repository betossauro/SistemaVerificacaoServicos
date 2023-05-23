package model.vo;

public enum TipoDescricaoZeladoria {
	
	FAXINA (1),
    ZELADORIA (2),
    TECNICO (3);

    private int valor;

    private TipoDescricaoZeladoria(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoDescricaoZeladoria getTipoDescricaoZeladoriaPorValor(int valor) {
        TipoDescricaoZeladoria tipoDescricaoZeladoria = null;
        for (TipoDescricaoZeladoria elemento : TipoDescricaoZeladoria.values()) {
            if (elemento.getValor() == valor) {
                tipoDescricaoZeladoria = elemento;
            }
        }
        return tipoDescricaoZeladoria;
    }
}
