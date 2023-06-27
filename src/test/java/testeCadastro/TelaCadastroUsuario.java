package testeCadastro;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

class TelaCadastroUsuario {


    private static Funcionario gerente;
    private static Funcionario funcionario;

    @BeforeAll
    static void setUpBeforeClass() throws Exception {
        gerente = new Funcionario(1, "Luis Alberto", "10810699966", "48998577999", LocalDate.of(1999, 05, 19), "10810699966", "000001", "000011", TipoUsuario.ADMINISTRADOR, TipoCargo.GERENCIA, null);
        funcionario = new Funcionario(4, "Joaquim José da Silva", "08725041632", "11999635214", LocalDate.of(1995, 03, 05), "08725041632", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.FAXINA, null);
    }
        
    @AfterAll
    static void tearDownAfterClass() throws Exception {
    }

    Funcionario funcionarioTeste(Integer id, String nome, String cpf, String telefone, LocalDate dataNascimento, String ctps, String matricula, String senha, TipoUsuario tipoUsuario, TipoCargo tipoCargo, LocalDate dataDesligamento) {
        Funcionario funcionarioTeste = new Funcionario(7, "Bento Miguel Gustavo Aragão", "08725041632", "82991822653", LocalDate.of(1944, 01, 02), "06556898937", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.ZELADORIA, null);
        return funcionarioTeste;
    } //06556898937

    @Test
    void testNome() throws Exception {
        assertEquals("Joaquim José da Silva", funcionario.getNome());
    }

    @Test
    void testCpf() throws Exception {
        assertEquals("08725041632", funcionario.getCpf());
    }
    
    // Teste para dar falha
    @Test
    void testCpfDuplicado() throws Exception {
    	Funcionario teste = funcionarioTeste(7, "Bento Miguel Gustavo Aragão", "08725041632", "82991822653", LocalDate.of(1944, 01, 02), "06556898937", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.ZELADORIA, null);
    	assertNotEquals(teste.getCpf(), funcionario.getCpf(), "CPF JÁ UTILIZADO");
    }

    @Test
    void testTelefone() throws Exception {
        assertEquals("11999635214", funcionario.getTelefone());
    }

    @Test
    void testDataNascimento() throws Exception {
        assertEquals(LocalDate.of(1995, 03, 05), funcionario.getDataNascimento());
    }

    @Test
    void testCtps() throws Exception {
        assertEquals("08725041632", funcionario.getCtps());
    }

    @Test
    void testMatricula() throws Exception {
        assertEquals("100002", funcionario.getMatricula());
    }
    
    // Teste para dar falha
    @Test
    void testMatriculaDuplicada() throws Exception {
    	Funcionario teste = funcionarioTeste(7, "Bento Miguel Gustavo Aragão", "08725041632", "82991822653", LocalDate.of(1944, 01, 02), "06556898937", "100002", "123456", TipoUsuario.FUNCIONARIO, TipoCargo.ZELADORIA, null);
    	assertNotEquals(teste.getMatricula(), funcionario.getMatricula(), "MATRÍCULA JÁ UTILIZADA");
    }

    @Test
    void testSenha() throws Exception {
        assertEquals("123456", funcionario.getSenha());
    }

    @Test
    void testTipoCargo() throws Exception {
        assertEquals(TipoCargo.FAXINA, funcionario.getTipoCargo());
    }

    @Test
    void testTipoUsuario() throws Exception {
        assertEquals(TipoUsuario.FUNCIONARIO, funcionario.getTipoUsuario());
    }

    @Test
    void testDataDesligamento() throws Exception {
        assertEquals(null, funcionario.getDataDesligamento());
    }

}
