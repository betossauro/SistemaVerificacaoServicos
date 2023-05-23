package model.vo;

public enum TipoDescricaoTecnico {
	
	FAXINA (1),
    ZELADORIA (2),
    TECNICO (3);

    private int valor;

    private TipoDescricaoTecnico(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static TipoDescricaoTecnico getTipoDescricaoTecnicoPorValor(int valor) {
        TipoDescricaoTecnico tipoDescricaoTecnico = null;
        for (TipoDescricaoTecnico elemento : TipoDescricaoTecnico.values()) {
            if (elemento.getValor() == valor) {
                tipoDescricaoTecnico = elemento;
            }
        }
        return tipoDescricaoTecnico;
    }

}
