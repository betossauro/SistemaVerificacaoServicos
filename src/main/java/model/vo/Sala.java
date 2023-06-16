package model.vo;

public class Sala {

	private int id;
	private String numero;
	private Boolean disponivel;

	public Sala(int id, String numero, Boolean disponivel) {
		this.id = id;
		this.numero = numero;
		this.disponivel = disponivel;
	}

	public Sala() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return "nº " + numero;
	}
}
