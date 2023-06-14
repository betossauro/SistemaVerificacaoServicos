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
	
	//REGRA 1: Caso o funcionário esqueça de registrar a hora de início, é possível 
	//selecionar manualmente o dia e hora em que o serviço foi realizado.

	//REGRA 2: O serviço só poderá ser registrado quando todos os campos forem 
	//preenchidos com ao menos uma opção.

	
	//REGRA 3: O funcionário poderá registrar posteriormente o serviço apenas no 
	//intervalo do mesmo dia em que este serviço foi realizado. 
}
