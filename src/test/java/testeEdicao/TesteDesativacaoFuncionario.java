package testeEdicao;

import static org.junit.jupiter.api.Assertions.*;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class TesteDesativacaoFuncionario {

	private static Funcionario funcionario;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		funcionario = new Funcionario(8, "Samuel Bruno Duarte", "04214228316", "92996317588",
				LocalDate.of(1947, 02, 04), "04214228316", "100007", "123456", TipoUsuario.FUNCIONARIO,
				TipoCargo.FAXINA, null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testeDesativacaoFuncionario() {
		funcionario.setDataDesligamento(LocalDate.of(2023, 06, 27));
		assertNotEquals(null, funcionario.getDataDesligamento(), "FUNCIONÁRIO ATIVO!");
	}

	@Test
	void testeFuncionarioAtivo() {
		funcionario.setDataDesligamento(null);
		assertEquals(LocalDate.of(2023, 06, 27), funcionario.getDataDesligamento(),
				"O FUNCIONÁRIO NÃO PODE SER DESATIVADO POIS POSSUI UM SERVIÇO EM ANDAMENTO!");
	}
}
