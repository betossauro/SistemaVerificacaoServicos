package model.vo;

public class Atividade {

	private int id;
	private int idCargo;
	private String descricao;

	public Atividade(int id, int idCargo, String descricao) {
		super();
		this.id = id;
		this.idCargo = idCargo;
		this.descricao = descricao;
	}

	public Atividade() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(int idCargo) {
		this.idCargo = idCargo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return descricao;
	}
}
