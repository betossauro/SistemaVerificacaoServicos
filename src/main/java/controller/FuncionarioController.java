package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleContext;

import model.bo.FuncionarioBO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.seletor.FuncionarioSeletor;
import model.vo.Atividade;
import model.vo.Funcionario;
import model.vo.Prestacao;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

public class FuncionarioController {
	private FuncionarioBO bo = new FuncionarioBO();

	public Funcionario inserir(Funcionario novoFuncionario) throws CampoInvalidoException {
		this.validarCamposObrigatorios(novoFuncionario);
		return bo.inserir(novoFuncionario);
	}

	public boolean atualizar(Funcionario funcionarioAlterado) throws CampoInvalidoException {
		this.validarCamposObrigatorios(funcionarioAlterado);
		return bo.atualizar(funcionarioAlterado);
	}

	private void validarCamposObrigatorios(Funcionario novoFuncionario) throws CampoInvalidoException {
		String mensagemValidacao = "";

		mensagemValidacao += validarString(String.valueOf(novoFuncionario.getNome()), "nome");
		mensagemValidacao += validarString(String.valueOf(novoFuncionario.getCpf()), "CPF");
		mensagemValidacao += validarString(String.valueOf(novoFuncionario.getTelefone()), "telefone");
		mensagemValidacao += validarData(novoFuncionario.getDataNascimento(), "data nascimento");
		mensagemValidacao += validarString(String.valueOf(novoFuncionario.getCtps()), "CTPs");
		mensagemValidacao += validarTipoUsuario(novoFuncionario.getTipoUsuario(), "tipo usuário");
		mensagemValidacao += validarTipoCargo(novoFuncionario.getTipoCargo(), "tipo cargo");
		mensagemValidacao += validarString(String.valueOf(novoFuncionario.getMatricula()), "matricula");
		mensagemValidacao += validarString(String.valueOf(novoFuncionario.getSenha()), "senha");

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	private String validarTipoCargo(TipoCargo tipoCargo, String string) {
		boolean valido = (tipoCargo != null);

		if (valido) {
			return "";
		} else {
			return "- " + tipoCargo + "\n";
		}
	}

	private String validarTipoUsuario(TipoUsuario tipoUsuario, String string) {
		boolean valido = (tipoUsuario != null);

		if (valido) {
			return "";
		} else {
			return "- " + tipoUsuario + "\n";
		}
	}

	private String validarString(String texto, String nomeCampo) {
		boolean valido = (texto != null) && !texto.trim().isEmpty();

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	private String validarData(LocalDate data, String nomeCampo) {
		boolean valido = (data != null);

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	public boolean excluir(Funcionario funcionario) throws CampoInvalidoException {
		return bo.excluir(funcionario);
	}

	public Funcionario consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Funcionario> consultarTodos() {
		return bo.consultarTodos();
	}

	public int contarFuncionariosQueTrabalhamNoEndereco(int id) {
		return bo.contarFuncionariosQueTrabalhamNoEndereco(id);
	}

	public ArrayList<TipoUsuario> consultarTipoUsuario() {
		return bo.consultarTipoUsuario();
	}

	public int contarTotalRegistrosComFiltros(FuncionarioSeletor seletor) {
		return bo.contarTotalRegistrosComFiltros(seletor);
	}

	public String gerarPlanilha(List<Funcionario> funcionarios, String caminho) throws CampoInvalidoException {

		if (funcionarios == null || caminho == null || caminho.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaFuncionarios(funcionarios, caminho);
	}

	public List<Funcionario> consultarComFiltros(FuncionarioSeletor seletor) {
		return bo.consultarComFiltros(seletor);
	}

	public Funcionario consultarPorLoginSenha(String matricula, String senha) throws CampoInvalidoException {
		Funcionario funcionarioConsultado = null;
		boolean valido = (matricula != null && !matricula.isEmpty()) && (senha != null && !senha.isEmpty());
		if (valido) {
			funcionarioConsultado = bo.consultarPorLoginSenha(matricula, senha);
		} else {
			throw new CampoInvalidoException("Login ou senha inválidos!");
		}
		return funcionarioConsultado;
	}

}
