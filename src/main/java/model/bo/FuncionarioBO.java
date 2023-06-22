package model.bo;

import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleContext;

import model.dao.FuncionarioDAO;
import model.dao.PrestacaoDAO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.seletor.FuncionarioSeletor;
import model.vo.Funcionario;
import model.vo.TipoUsuario;

public class FuncionarioBO {
	private FuncionarioDAO dao = new FuncionarioDAO();

	public Funcionario inserir(Funcionario novoFuncionario) throws CampoInvalidoException {
		if (dao.cpfJaUtilizado(novoFuncionario.getCpf())) {
			throw new CampoInvalidoException("CPF informado já foi utilizado");
		}
		// Caso CPF não utilizado -> salvar e devolver o funcionario
		return dao.inserir(novoFuncionario);
	}

	public boolean atualizar(Funcionario funcionarioAlterado) throws CampoInvalidoException {
		Funcionario funcionarioOriginal = dao.consultarPorId(funcionarioAlterado.getId());

		if (!funcionarioAlterado.getCpf().equals(funcionarioOriginal.getCpf())) {
			throw new CampoInvalidoException("CPF não pode ser alterado");
		}
		return dao.atualizar(funcionarioAlterado);
	}

	public boolean excluir(Funcionario funcionario) throws CampoInvalidoException {
		PrestacaoDAO prestacaoDAO = new PrestacaoDAO();
		if (prestacaoDAO.funcionarioTemPrestacaoPendente(funcionario.getId())) {
			throw new CampoInvalidoException(
					"Funcionario não pode ser excluído, pois possui uma atividade em andamento");
		}
		return dao.excluir(funcionario);
	}

	public Funcionario consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Funcionario> consultarTodos() {
		return dao.consultarTodos();
	}

	public int contarFuncionariosQueTrabalhamNoEndereco(int id) {
		return dao.contarFuncionariosQueTrabalhamNoEndereco(id);
	}

	public ArrayList<TipoUsuario> consultarTipoUsuario() {
		return dao.consultarTipoUsuario();
	}

	public String gerarPlanilha(List<Funcionario> funcionarios, String caminho) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaFuncionarios(funcionarios, caminho);
	}

	public int contarTotalRegistrosComFiltros(FuncionarioSeletor seletor) {
		return dao.contarTotalRegistrosComFiltros(seletor);
	}

	public List<Funcionario> consultarComFiltros(FuncionarioSeletor seletor) {
		return dao.consultarComFiltros(seletor);
	}

	public Funcionario consultarPorLoginSenha(String matricula, String senha) {
		Funcionario funcionarioConsultado = dao.consultarPorLoginSenha(matricula, senha);  
		return funcionarioConsultado;
	}
}
