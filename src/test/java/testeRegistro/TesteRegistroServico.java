package testeRegistro;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.vo.Atividade;
import model.vo.Prestacao;

class TesteRegistroServico {

	private static Prestacao servico;
	private static ArrayList<Atividade> atividades;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Atividade atv1 = new Atividade(1, 1, "Limpeza rotineira");
		Atividade atv2 = new Atividade(2, 1, "Abastecimento");
		Atividade atv3 = new Atividade(3, 1, "Limpeza pesada CHAO");
		Atividade atv4 = new Atividade(4, 2, "Manutenção ELÉTRICA");
	    Atividade atv5 = new Atividade(5, 2, "Manutenção HIDRÁULICA");
	    Atividade atv6 = new Atividade(6, 2, "Manutenção AR CONDICIONADO");
		atividades = new ArrayList<Atividade>();
		atividades.add(atv1);
		atividades.add(atv2);
		atividades.add(atv3);
		atividades.add(atv4);
		atividades.add(atv5);
		atividades.add(atv6);
		servico = new Prestacao(1, 4, 2, LocalDateTime.of(2023, 06, 22, 17, 39), LocalDateTime.of(2023, 06, 22, 17, 59), atividades);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testRegistroSucesso() {
		assertEquals(1, servico.getId(), "ID Incorreto");
		assertEquals(4, servico.getIdFuncionario(), "ID do Funcionário Incorreto");
		assertEquals(2, servico.getIdSala(), "ID da Sala Incorreto");
		assertEquals(LocalDateTime.of(2023, 06, 22, 17, 39), servico.getDataInicio(), "Data de Inicio Incorreto");
		assertEquals(LocalDateTime.of(2023, 06, 22, 17, 59), servico.getDataFim(), "Data de Fim Incorreto");
		assertEquals(atividades, servico.getListaAtividades(), "Selecionar ao menos uma atividade");
	}

	@Test
	void testRegistroSemAtividade() {
		assertEquals(1, servico.getId(), "ID Incorreto");
		assertEquals(4, servico.getIdFuncionario(), "ID do Funcionário Incorreto");
		assertEquals(2, servico.getIdSala(), "ID da Sala Incorreto");
		assertEquals(LocalDateTime.of(2023, 06, 22, 17, 39), servico.getDataInicio(), "Data de Inicio Incorreto");
		assertEquals(LocalDateTime.of(2023, 06, 22, 17, 59), servico.getDataFim(), "Data de Fim Incorreto");
		assertEquals(null, servico.getListaAtividades(), "Selecionar ao menos uma atividade");
	}

}
