package model.bo;

import java.util.List;

import model.dao.EnderecoDAO;
import model.dao.FuncionarioDAO;
import model.exception.CampoInvalidoException;
import model.vo.Endereco;

public class EnderecoBO {
	private EnderecoDAO dao = new EnderecoDAO();
	
	public Endereco inserir(Endereco novoEndereco) {
		return dao.inserir(novoEndereco);
	}

	public boolean atualizar(Endereco enderecoAlterado) {
		return dao.atualizar(enderecoAlterado);
	}

	public boolean excluir(int id) throws CampoInvalidoException {
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

		if (funcionarioDAO.contarFuncionariosQueTrabalhamNoEndereco(id) > 0) {
			throw new CampoInvalidoException("Endereço não pode ser excluído, pois possui funcionário(s) associado(s)");
		}

		return dao.excluir(id);
	}

	public Endereco consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Endereco> consultarTodos() {
		return dao.consultarTodos();
	}
}
