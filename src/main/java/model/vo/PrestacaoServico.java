package model.vo;

import java.util.Date;

public class PrestacaoServico {

    private int id;
    private int idFuncionario;
    private int idSala;
    private Date dtInicio;
    private Date dtFim;
    private String descricao;

    public PrestacaoServico(int id, int idFuncionario, int idSala, Date dtInicio, Date dtFim, String descricao) {
        this.id = id;
        this.idFuncionario = idFuncionario;
        this.idSala = idSala;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.descricao = descricao;
    }

    public PrestacaoServico() {
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "PrestacaoServico{" +
                "id=" + id +
                ", idFuncionario=" + idFuncionario +
                ", idSala=" + idSala +
                ", dtInicio=" + dtInicio +
                ", dtFim=" + dtFim +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
