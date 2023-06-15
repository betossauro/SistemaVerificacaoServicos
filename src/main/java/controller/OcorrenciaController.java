package controller;

import java.util.List;

import model.bo.OcorrenciaBO;
import model.vo.Ocorrencia;

public class OcorrenciaController {
	private OcorrenciaBO bo = new OcorrenciaBO();

	public Ocorrencia inserir(Ocorrencia novaOcorrencia) {
		return bo.inserir(novaOcorrencia);
	}

	public boolean atualizar(Ocorrencia OcorrenciaAlterada) {
		return bo.atualizar(OcorrenciaAlterada);
	}

	public Ocorrencia consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Ocorrencia> consultarTodos() {
		return bo.consultarTodos();
	}

	public List<Ocorrencia> consultarPorIdPrestacao(int idPrestacao) {
		return bo.consultarPorIdPrestacao(idPrestacao);
	}
}
