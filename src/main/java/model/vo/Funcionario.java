package model.vo;

import java.time.LocalDate;

public class Funcionario {

	private int id;
	private String nome;
	private String cpf;
	private String telefone;
	private LocalDate dataNascimento;
	private String ctps;
	private String matricula;
	private String senha;
	private TipoUsuario tipoUsuario;
	private TipoCargo tipoCargo;
	private LocalDate dataDesligamento;

	public Funcionario(int id, String nome, String cpf, String telefone, LocalDate dataNascimento, String ctps,
			String matricula, String senha, TipoUsuario tipoUsuario, TipoCargo tipoCargo, LocalDate dataDesligamento) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.dataNascimento = dataNascimento;
		this.ctps = ctps;
		this.matricula = matricula;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.tipoCargo = tipoCargo;
		this.dataDesligamento = dataDesligamento;
	}

	public Funcionario() {
		super();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCtps() {
		return ctps;
	}

	public void setCtps(String ctps) {
		this.ctps = ctps;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public TipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public LocalDate getDataDesligamento() {
		return dataDesligamento;
	}

	public void setDataDesligamento(LocalDate dataDesligamento) {
		this.dataDesligamento = dataDesligamento;
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", telefone=" + telefone
				+ ", dataNascimento=" + dataNascimento + ", ctps=" + ctps + ", matricula=" + matricula + ", senha="
				+ senha + ", tipoUsuario=" + tipoUsuario + ", tipoCargo=" + tipoCargo + ", dataDesligamento="
				+ dataDesligamento + "]";
	}
}
