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
        funcionarioAdm = new Funcionario(1, "Luis Alberto", "10810699966", "48998577999", LocalDate.of(1999, 05, 19), "10810699966", "000001", "000011", TipoUsuario.ADMINISTRADOR, TipoCargo.GERENCIA, null);
        funcionarioNormal = new Funcionario(7, "Bento Miguel Gustavo Aragão", "06556898937", "82991822653",
                LocalDate.of(1944, 01, 02), "06556898937", "100006", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.ZELADORIA, null);
    }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    Funcionario funcionarioTeste(Integer id, String nome, String cpf, String telefone, LocalDate dataNascimento, String ctps, String matricula, String senha, TipoUsuario tipoUsuario, TipoCargo tipoCargo, LocalDate dataDesligamento) {
        Funcionario funcionarioTeste = new Funcionario(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        return funcionarioTeste;
    }

    @Test
    void testNome() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals("Joaquim José da Silva", teste.getNome());
    }

    @Test
    void testCpf() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testTelefone() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testDataNascimento() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testCtps() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testMatricula() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testSenha() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testTipoCargo() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(TipoCargo.FAXINA, teste.getTipoCargo());
    }

    @Test
    void testTipoUsuario() throws Exception {
        Funcionario teste = funcionarioTeste(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
        assertEquals(funcionarioNormal.getTipoUsuario(), teste.getTipoUsuario());
    }

}
