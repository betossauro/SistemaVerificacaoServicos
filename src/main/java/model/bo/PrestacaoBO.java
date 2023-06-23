package model.bo;

import java.time.LocalDateTime;
import java.util.List;

import model.dao.PrestacaoDAO;
import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.seletor.PrestacaoSeletor;
import model.vo.Atividade;
import model.vo.Prestacao;

public class PrestacaoBO {
	private PrestacaoDAO dao = new PrestacaoDAO();

	public Prestacao inserir(Prestacao novaPrestacao) throws CampoInvalidoException {
		validarCamposObrigatorios(novaPrestacao);
		return dao.inserir(novaPrestacao);
	}

	private void validarCamposObrigatorios(Prestacao novaPrestacao) throws CampoInvalidoException {
		String mensagemValidacao = "";

		mensagemValidacao += validarString(String.valueOf(novaPrestacao.getIdFuncionario()), "idfuncionario");
		mensagemValidacao += validarString(String.valueOf(novaPrestacao.getIdSala()), "idsala");
		mensagemValidacao += validarData(novaPrestacao.getDataInicio(), "datainicio");
		mensagemValidacao += validarData(novaPrestacao.getDataFim(), "datafim");
		mensagemValidacao += validarAtividades(novaPrestacao.getListaAtividades(), "atividades");

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	private String validarAtividades(List<Atividade> listaAtividades, String nomeCampo) {
		boolean valido = (listaAtividades != null);

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	private String validarData(LocalDateTime data, String nomeCampo) {
		boolean valido = (data != null);

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	private String validarString(String texto, String nomeCampo) {
		boolean valido = (texto != null) && !texto.trim().isEmpty();

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	public boolean atualizar(Prestacao servicoAlterado) {
		return dao.atualizar(servicoAlterado);
	}

	public Prestacao consultarPorId(int id) {
		return dao.consultarPorId(id);
	}

	public List<Prestacao> consultarTodos() {
		return dao.consultarTodos();
	}

	public List<PrestacaoDTO> consultarDTO(PrestacaoSeletor seletor) {
		return dao.consultarDTO(seletor);
	}

	public boolean funcionarioTemPrestacaoPendente(int idFuncionario) {
		return dao.funcionarioTemPrestacaoPendente(idFuncionario);
	}

	public String gerarPlanilha(List<PrestacaoDTO> prestacoes, String caminho) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicos(prestacoes, caminho);
	}

	public List<PrestacaoDTO> consultarComFiltros(PrestacaoSeletor seletor) {
		return dao.consultarDTO(seletor);
	}

	public int contarTotalRegistrosComFiltros(PrestacaoSeletor seletor) {
		return dao.contarTotalRegistrosDTOComFiltros(seletor);
	}
}
