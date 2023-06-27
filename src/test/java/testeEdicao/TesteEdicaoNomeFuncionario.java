package testeEdicao;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

class TesteEdicaoNomeFuncionario {

	private static Funcionario funcionario;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		funcionario = new Funcionario(7, "Bento Miguel Gustavo Aragão", "06556898937", "82991822653",
				LocalDate.of(1944, 01, 02), "06556898937", "100006", "123456", TipoUsuario.FUNCIONARIO,
				TipoCargo.ZELADORIA, null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testeEdicaoNomeComSucesso() {
		funcionario.setNome("Bento Miguel Gustavo");
		assertEquals("Bento Miguel Gustavo", funcionario.getNome(), "O NOME NÃO FOI ALTERADO");
	}

	@Test
	void testeEdicaoNomeComFalha() {
		funcionario.setNome("Bento Miguel Gustavo");
		assertEquals("Bento Miguel Gustavo Aragão", funcionario.getNome(), "O NOME NÃO FOI ALTERADO");
	}

}
