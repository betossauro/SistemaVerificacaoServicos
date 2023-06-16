package model.seletor;

import java.time.LocalDate;

public class FuncionarioSeletor extends BaseSeletor{

	private String nome;
	private String tipoCargo;
	private LocalDate dataDesligamento;
	
	@Override
	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0)
			|| this.tipoCargo != null
			|| this.dataDesligamento != null;
	}

	
	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getTipoCargo() {
		return tipoCargo;
	}


	public void setTipoCargo(String tipoCargo) {
		this.tipoCargo = tipoCargo;
	}


	public LocalDate getDataDesligamento() {
		return dataDesligamento;
	}


	public void setDataDesligamento(LocalDate dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	public boolean temPaginacao() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
