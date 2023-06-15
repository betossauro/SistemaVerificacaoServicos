package model.bo;

import java.util.List;

import model.dao.PrestacaoDAO;
import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.vo.Prestacao;

public class PrestacaoBO {
	private PrestacaoDAO dao = new PrestacaoDAO();

	// REGRA 2: O serviço só poderá ser registrado quando todos os campos forem
	// preenchidos com ao menos uma opção.
	public Prestacao inserir(Prestacao novaPrestacao) throws CampoInvalidoException {
//		validarCamposObrigatorios(novaPrestacao);
		return dao.inserir(novaPrestacao);
	}

	// TODO Pode chamar de DTO?
//	private void validarCamposObrigatorios(Prestacao novaPrestacao) {
//		String mensagemValidacao = "";
//
//		mensagemValidacao += validarString(novaPrestacao.getIdFuncionario(), "idfuncionario");
//		mensagemValidacao += validarString(novaPrestacao.getIdSala(), "idsala");
//		mensagemValidacao += validarString(novaPrestacao.getDataInicio(), "datainicio");
//		mensagemValidacao += validarString(novaPrestacao.getDataFim(), "datafim");
//		mensagemValidacao += validarString();
//
//		if (!mensagemValidacao.isEmpty()) {
//			throw new CampoInvalidoException(mensagemValidacao);
//		}
//	}

	// TODO
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

	public String gerarPlanilha(List<PrestacaoDTO> prestacoes, String caminho) {
		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaServicos(prestacoes, caminho);
	}

	// REGRA 1: Caso o funcionário esqueça de registrar a hora de início, é possível
	// selecionar manualmente o dia e hora em que o serviço foi realizado.

	// REGRA 3: O funcionário poderá registrar posteriormente o serviço apenas no
	// intervalo do mesmo dia em que este serviço foi realizado.
}
