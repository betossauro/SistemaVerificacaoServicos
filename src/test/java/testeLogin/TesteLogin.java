package testeLogin;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

class TesteLogin {

	private static Funcionario gerente;
	private static Funcionario funcionario;
	private static Funcionario funcionarioDasativado;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		gerente = new Funcionario(1, "Luis Alberto", "10810699966", "48998577999", LocalDate.of(1999, 05, 19),
				"10810699966", "000001", "000011", TipoUsuario.ADMINISTRADOR, TipoCargo.GERENCIA, null);

		funcionario = new Funcionario(7, "Bento Miguel Gustavo Aragão", "06556898937", "82991822653",
				LocalDate.of(1944, 01, 02), "06556898937", "100006", "123456", TipoUsuario.FUNCIONARIO,
				TipoCargo.ZELADORIA, null);

		funcionarioDasativado = new Funcionario(4, "Joaquim José da Silva", "08725041632", "11999635214",
				LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO,
				TipoCargo.FAXINA, LocalDate.of(2023, 06, 26));
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testeLoginGerenteValido() {
		assertEquals("000001", gerente.getMatricula());
		assertEquals("000011", gerente.getSenha());
		assertEquals(null, gerente.getDataDesligamento());
	}

	@Test
	void testeLoginGerenteInvalido() {
		assertEquals("000001", gerente.getMatricula(), "MATRICULA INVALIDA");
		assertEquals("000012", gerente.getSenha(), "SENHA INVÁLIDA");
		assertEquals(null, gerente.getDataDesligamento());
	}

	@Test
	void testeLoginFuncionarioValido() {
		assertEquals("100006", funcionario.getMatricula());
		assertEquals("123456", funcionario.getSenha());
		assertEquals(null, funcionario.getDataDesligamento());
	}

	@Test
	void testeLoginFuncionarioInvalido() {
		assertEquals("1000015", funcionario.getMatricula(), "MATRICULA INVALIDA");
		assertEquals("123456", funcionario.getSenha(), "SENHA INVÁLIDA");
		assertEquals(null, funcionario.getDataDesligamento(), "USUARIO DESATIVADO");
	}

	@Test
	void testeLoginFuncionarioDesativado() {
		assertEquals("100002", funcionarioDasativado.getMatricula(), "MATRICULA INVALIDA");
		assertEquals("123456", funcionarioDasativado.getSenha(), "SENHA INVÁLIDA");
		assertEquals(null, funcionarioDasativado.getDataDesligamento(), "USUARIO DESATIVADO");
	}

}
