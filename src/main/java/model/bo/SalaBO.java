package model.bo;

import java.util.List;

import model.dao.SalaDAO;
import model.vo.Sala;

public class SalaBO {
private SalaDAO dao = new SalaDAO();
	
	public Sala inserir(Sala novoServico) {
		return dao.inserir(novoServico);
	}

	public boolean atualizar(Sala servicoAlterado) {
		return dao.atualizar(servicoAlterado);
	}

	//REGRA 1: Exclusão lógica (UPDATE) (setando como indisponível para sempre)
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

	public Sala consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Sala> consultarTodos() {
		return dao.consultarTodos();
	}
	
	//REGRA 2: Quando registrada uma nova sala ela está automaticamente disponivel
}
