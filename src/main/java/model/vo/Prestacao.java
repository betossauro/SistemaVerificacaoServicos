package model.vo;

import java.time.LocalDateTime;
import java.util.List;

public class Prestacao {

	private int id;
	private int idFuncionario;
	private int idSala;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
	private List<Atividade> listaAtividades;
	private List<Ocorrencia> listaOcorrencias;

	public Prestacao(int id, int idFuncionario, int idSala, LocalDateTime dataInicio, LocalDateTime dataFim, List<Atividade> listaAtividades,
			List<Ocorrencia> listaOcorrencias) {
		super();
		this.id = id;
		this.idFuncionario = idFuncionario;
		this.idSala = idSala;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.listaAtividades = listaAtividades;
		this.listaOcorrencias = listaOcorrencias;
	}

	public Prestacao() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	public int getIdSala() {
		return idSala;
	}

	public void setIdSala(int idSala) {
		this.idSala = idSala;
	}

	public LocalDateTime getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDateTime dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDateTime getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDateTime dataFim) {
		this.dataFim = dataFim;
	}

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	public List<Ocorrencia> getListaOcorrencias() {
		return listaOcorrencias;
	}

	public void setListaOcorrencias(List<Ocorrencia> listaOcorrencias) {
		this.listaOcorrencias = listaOcorrencias;
	}

	@Override
	public String toString() {
		return "Prestacao [id=" + id + ", idFuncionario=" + idFuncionario + ", idSala=" + idSala + ", dataInicio="
				+ dataInicio + ", dataFim=" + dataFim + ", listaAtividades=" + listaAtividades + ", listaOcorrencias="
				+ listaOcorrencias + "]";
	}
}
