package model.seletor;

import model.vo.TipoCargo;

public class FuncionarioSeletor extends BaseSeletor {

	private String nome;
	private TipoCargo tipoCargo;
	private Boolean ativo;

	@Override
	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0) || this.tipoCargo != null || this.ativo == null;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}
