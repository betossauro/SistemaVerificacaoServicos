package model.seletor;

import java.time.LocalDate;

import model.vo.TipoCargo;

public class PrestacaoSeletor extends BaseSeletor {
	private String nomeFuncionario;
	private TipoCargo tipoCargo;
	private Integer idSala;
	private String numeroSala;

	public String getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(String numeroSala) {
		this.numeroSala = numeroSala;
	}

	private LocalDate dataInicio;
	private LocalDate dataFim;
	private String servico;

	@Override
	public boolean temFiltro() {
		return (this.nomeFuncionario != null && this.nomeFuncionario.trim().length() > 0) || this.tipoCargo != null
				|| this.idSala != null || this.numeroSala != null || this.dataInicio != null || this.dataFim != null
				|| this.servico != null;
	}

	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public TipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public Integer getIdSala() {
		return idSala;
	}

	public void setIdSala(Integer idSala) {
		this.idSala = idSala;
	}
}
