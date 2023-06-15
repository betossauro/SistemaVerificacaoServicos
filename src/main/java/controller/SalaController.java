package controller;

import java.util.List;

import model.bo.SalaBO;
import model.exception.CampoInvalidoException;
import model.vo.Sala;

public class SalaController {
	private SalaBO bo = new SalaBO();

	public Sala inserir(Sala novaSala) {
		return bo.inserir(novaSala);
	}

	public boolean atualizar(Sala salaAlterada) {
		return bo.atualizar(salaAlterada);
	}

	public boolean excluir(Sala sala) throws CampoInvalidoException {
		return bo.excluir(sala);
	}

	public Sala consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Sala> consultarTodos() {
		return bo.consultarTodos();
	}
}