package controller;

import java.util.List;

import model.bo.AtividadeBO;
import model.exception.CampoInvalidoException;
import model.vo.Atividade;
import model.vo.TipoCargo;

public class AtividadeController {
	AtividadeBO bo = new AtividadeBO();

	public Atividade inserir(Atividade novaSala) throws CampoInvalidoException {
		return bo.inserir(novaSala);
	}

	public boolean atualizar(Atividade atividadeAlterada) {
		return bo.atualizar(atividadeAlterada);
	}

	public boolean excluir(Atividade atividade) throws CampoInvalidoException {
		return bo.excluir(atividade);
	}

	public Atividade consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Atividade> consultarTodos() {
		return bo.consultarTodos();
	}

	public List<Atividade> consultarPorIdPrestacao(int idPrestacao) {
		return bo.consultarPorIdPrestacao(idPrestacao);
	}

	public List<Atividade> consultarPorIdCargo(TipoCargo idCargo) {
		return bo.consultarPorIdCargo(idCargo);
	}
}
