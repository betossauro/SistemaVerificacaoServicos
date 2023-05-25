package model.vo;

import java.util.Date;
import java.util.List;

public class Prestacao {

    private int id;
    private int idFuncionario;
    private int idSala;
    private Date dtInicio;
    private Date dtFim;
    private List<Atividade> listaAtividades;

    public Prestacao(int id, int idFuncionario, int idSala, Date dtInicio, Date dtFim,
			List<Atividade> listaAtividades) {
		super();
		this.id = id;
		this.idFuncionario = idFuncionario;
		this.idSala = idSala;
		this.dtInicio = dtInicio;
		this.dtFim = dtFim;
		this.listaAtividades = listaAtividades;
	}

	public Prestacao() {
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

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

	public List<Atividade> getListaAtividades() {
		return listaAtividades;
	}

	public void setListaAtividades(List<Atividade> listaAtividades) {
		this.listaAtividades = listaAtividades;
	}

	@Override
	public String toString() {
		return "PrestacaoServico [id=" + id + ", idFuncionario=" + idFuncionario + ", idSala=" + idSala + ", dtInicio="
				+ dtInicio + ", dtFim=" + dtFim + ", listaAtividades=" + listaAtividades + "]";
	}
}
