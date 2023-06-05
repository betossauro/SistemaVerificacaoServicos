package model.vo;

public class Ocorrencia {

	private int id;
	private String descricao;
	private int idPrestacao;
	
	public Ocorrencia(int id, String descricao, int idPrestacao) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.idPrestacao = idPrestacao;
	}

	public Ocorrencia() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getIdPrestacao() {
		return idPrestacao;
	}

	public void setIdPrestacao(int idPrestacao) {
		this.idPrestacao = idPrestacao;
	}

	@Override
	public String toString() {
		return "Ocorrencia [id=" + id + ", descricao=" + descricao + ", idPrestacao=" + idPrestacao + "]";
	}
}
