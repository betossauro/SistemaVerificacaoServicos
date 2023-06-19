package controller;

import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleContext;

import model.bo.FuncionarioBO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.seletor.FuncionarioSeletor;
import model.vo.Funcionario;
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

	private void validarCamposObrigatorios(Funcionario f) throws CampoInvalidoException {
		String mensagemValidacao = "";
		if (f.getNome() == null || f.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inválido! \n";
		}
		mensagemValidacao += validarCpf(f);

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	private String validarCpf(Funcionario f) throws CampoInvalidoException {
		String validacao = "";

		if (f.getCpf() == null) {
			validacao += "Informe um CPF \n";
		} else {
			String cpfSemMascara = f.getCpf().replace(".", "");
			cpfSemMascara = f.getCpf().replace("-", "");
			f.setCpf(cpfSemMascara);
			if (f.getCpf().length() != 11) {
				validacao += "CPF deve possuir 11 dígitos\n";
			}
		}
		return validacao;
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
		boolean valido = (matricula != null && !matricula.isEmpty())
				&& (senha != null && !senha.isEmpty());
		if (valido) {
			funcionarioConsultado = bo.consultarPorLoginSenha(matricula, senha);
		} else {
			throw new CampoInvalidoException("Login ou senha inválidos!");
		}
		return funcionarioConsultado;
	}

}
