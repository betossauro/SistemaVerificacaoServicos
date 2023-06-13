package model.bo;

import java.util.List;

import model.dao.PrestacaoDAO;
import model.dto.PrestacaoDTO;
import model.gerador.GeradorPlanilhas;
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

	public String gerarPlanilhaVisuGerente(List<PrestacaoDTO> prestacoes, String caminho) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicosVisuGerente(prestacoes, caminho);
	}

	public String gerarPlanilhaVisuFuncionario(List<PrestacaoDTO> prestacoes, String caminho) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicoVisuFuncionario(prestacoes, caminho);
	}
}
