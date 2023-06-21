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

	public Prestacao(int id, int idFuncionario, int idSala, LocalDateTime dataInicio, LocalDateTime dataFim, List<Atividade> listaAtividades) {
		super();
		this.id = id;
		this.idFuncionario = idFuncionario;
		this.idSala = idSala;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.listaAtividades = listaAtividades;
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


	@Override
	public String toString() {
		return "Prestacao [id=" + id + ", idFuncionario=" + idFuncionario + ", idSala=" + idSala + ", dataInicio="
				+ dataInicio + ", dataFim=" + dataFim + ", listaAtividades=" + listaAtividades + "]";
	}
}
