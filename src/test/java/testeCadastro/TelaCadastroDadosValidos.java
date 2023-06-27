package testeCadastro;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

class TelaCadastroDadosValidos {
	

	private static Funcionario funcionarioAdm;
	private static Funcionario funcionarioNormal;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		funcionarioAdm = new Funcionario(1, "Luis Alberto", "", null, null, null, null, null, null, null, null);
		funcionarioNormal = new Funcionario(1, "Luis Alberto", "", null, null, null, null, null, null, null, null);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}
	
	Funcionario funcionarioTeste(Integer id, String nome, String cpf, String telefone, LocalDate dataNascimento, String ctps,
			String matricula, String senha, TipoUsuario tipoUsuario, TipoCargo tipoCargo, LocalDate dataDesligamento) {
		Funcionario funcionarioTeste = new Funcionario();
		return funcionarioTeste;
	}
	

	@Test
	void test() throws Exception {
		Funcionario teste = funcionarioTeste(null, null, null, null, null, null, null, null, null, null, null);
		assertEquals(funcionarioAdm.getTipoCargo(), teste.getTipoCargo());
	}

}
