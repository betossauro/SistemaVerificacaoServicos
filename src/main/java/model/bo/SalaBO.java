package model.bo;

import java.util.List;

import model.dao.SalaDAO;
import model.exception.CampoInvalidoException;
import model.vo.Sala;

public class SalaBO {
	private SalaDAO dao = new SalaDAO();

	// REGRA 1: Quando registrada uma nova sala ela está automaticamente disponivel
	public Sala inserir(Sala novaSala) {
		return dao.inserir(novaSala);
	}

	public boolean atualizar(Sala salaAlterada) {
		return dao.atualizar(salaAlterada);
	}

	// REGRA 2: Exclusão lógica (UPDATE) (setando como indisponível para sempre)
	public boolean excluir(Sala sala) throws CampoInvalidoException {
		if (sala.isDisponivel() == false) {
			throw new CampoInvalidoException("Sala não pode ser excluída, pois possui uma atividade em andamento");
		}
		return dao.excluir(sala);
	}

	public Sala consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Sala> consultarTodos() {
		return dao.consultarTodos();
	}

	
}
