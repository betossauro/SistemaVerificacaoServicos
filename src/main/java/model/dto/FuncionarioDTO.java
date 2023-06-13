package model.dto;

public class FuncionarioDTO {
	private String nome;
	private String cargo;
	private String dataDesligamento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(String dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}
}
