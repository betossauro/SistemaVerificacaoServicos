package model.bo;

import java.util.List;

import model.dao.PrestacaoDAO;
import model.vo.Prestacao;

public class PrestacaoBO {
private PrestacaoDAO dao = new PrestacaoDAO();
	
	public Prestacao inserir(Prestacao novoServico) {
		return dao.inserir(novoServico);
	}

	public boolean atualizar(Prestacao servicoAlterado) {
		return dao.atualizar(servicoAlterado);
	}

	/*
	 * public boolean excluir(int id) throws CampoInvalidoException { FuncionarioDAO
	 * funcionarioDAO = new FuncionarioDAO();
	 * 
	 * if (funcionarioDAO.contarFuncionariosQueTrabalhamNoEndereco(id) > 0) { throw
	 * new
	 * CampoInvalidoException("Endereço não pode ser excluído, pois possui funcionário(s) associado(s)"
	 * ); }
	 * 
	 * return dao.excluir(id); }
	 */

	public Prestacao consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Prestacao> consultarTodos() {
		return dao.consultarTodos();
	}
}
