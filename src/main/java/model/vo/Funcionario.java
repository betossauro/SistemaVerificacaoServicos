package model.vo;

import java.util.Date;

public class Funcionario {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private Date dtnascimento;
    private String ctps;
    private TipoUsuario tipoUsuario;
    private TipoCargo tipoCargo;

    public Funcionario(int id, String nome, String cpf, String telefone, Date dtnascimento, String ctps, TipoUsuario tipoUsuario, TipoCargo tipoCargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.dtnascimento = dtnascimento;
        this.ctps = ctps;
        this.tipoUsuario = tipoUsuario;
        this.tipoCargo = tipoCargo;
    }

    public Funcionario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Date getDtnascimento() {
        return dtnascimento;
    }

    public void setDtnascimento(Date dtnascimento) {
        this.dtnascimento = dtnascimento;
    }

    public String getCtps() {
        return ctps;
    }

    public void setCtps(String ctps) {
        this.ctps = ctps;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public TipoCargo getTipoServico() {
        return tipoCargo;
    }

    public void setTipoServico(TipoCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                ", dtnascimento=" + dtnascimento +
                ", ctps='" + ctps + '\'' +
                ", tipoUsuario=" + tipoUsuario +
                ", tipoCargo=" + tipoCargo +
                '}';
    }
}
