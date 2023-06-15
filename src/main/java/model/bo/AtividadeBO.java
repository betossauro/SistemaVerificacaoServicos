package model.bo;

import java.util.List;

import model.dao.AtividadeDAO;
import model.exception.CampoInvalidoException;
import model.vo.Atividade;

public class AtividadeBO {
	AtividadeDAO dao = new AtividadeDAO();

	// REGRA 1: O usuário precisa selecionar pelo menos uma atividade
	// (relacionado ao PainelRegistroServico)
	public Atividade inserir(Atividade novaSala) throws CampoInvalidoException {
		return dao.inserir(novaSala);
	}

	public boolean atualizar(Atividade atividadeAlterada) {
		return dao.atualizar(atividadeAlterada);
	}

	public boolean excluir(Atividade atividade) throws CampoInvalidoException {
		return dao.excluir(atividade);
	}

	public Atividade consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Atividade> consultarTodos() {
		return dao.consultarTodos();
	}

	public List<Atividade> consultarPorIdPrestacao(int idPrestacao) {
		return dao.consultarPorIdPrestacao(idPrestacao);
	}
}
