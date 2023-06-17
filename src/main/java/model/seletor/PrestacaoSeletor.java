package model.seletor;

public class PrestacaoSeletor {
	private Integer idSala;

	public boolean temFiltros() {
		return idSala != null;
	}

	public Integer getIdSala() {
		return idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}

}
