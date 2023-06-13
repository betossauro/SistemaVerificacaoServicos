package controller;

import java.util.List;

import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;

public class PrestacaoServicoController {
	public String gerarPlanilhaVisuGerente(List<PrestacaoDTO> prestacoes, String caminho)
			throws CampoInvalidoException {

		if (prestacoes == null || caminho == null || caminho.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicosVisuGerente(prestacoes, caminho);
	}

	public String gerarPlanilhaVisuFuncionario(List<PrestacaoDTO> prestacoes, String caminho)
			throws CampoInvalidoException {

		if (prestacoes == null || caminho == null || caminho.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicoVisuFuncionario(prestacoes, caminho);
	}

}
