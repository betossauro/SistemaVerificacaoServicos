package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Funcionario;
import model.vo.Sala;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;
import model.vo.TipoUsuarioVO;

public class FuncionarioDAO {

	public Funcionario inserir(Funcionario novoFuncionario) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO FUNCIONARIO (NOME, CPF, TELEFONE, DATANASCIMENTO, CTPS, MATRICULA, SENHA, DATADESLIGAMENTO, IDTIPOCARGO, IDTIPOUSUARIO) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			query.setString(1, novoFuncionario.getNome());
			query.setString(2, novoFuncionario.getCpf());
			query.setString(3, novoFuncionario.getTelefone());
			query.setDate(4, java.sql.Date.valueOf(novoFuncionario.getDataNascimento()));
			query.setString(5, novoFuncionario.getCtps());
			query.setString(6, novoFuncionario.getMatricula());
			query.setString(7, novoFuncionario.getSenha());
			query.setDate(8, java.sql.Date.valueOf(novoFuncionario.getDataDesligamento()));
			query.setInt(9, novoFuncionario.getTipoUsuario().getValor());
			query.setInt(10, novoFuncionario.getTipoCargo().getValor());
			query.execute();
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoFuncionario.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao inserir funcionário" + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novoFuncionario;
	}

	public boolean atualizar(Funcionario funcionarioAlterado) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE FUNCIONARIO SET NOME = ?, TELEFONE = ?, SENHA = ?, DATADESLIGAMENTO = ?, IDTIPOUSUARIO = ?, IDTIPOCARGO = ? "
				+ " WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			query.setString(1, funcionarioAlterado.getNome());
			query.setString(2, funcionarioAlterado.getTelefone());
			query.setString(3, funcionarioAlterado.getSenha());
			query.setDate(4, java.sql.Date.valueOf(funcionarioAlterado.getDataDesligamento()));
			query.setInt(5, funcionarioAlterado.getTipoUsuario().getValor());
			query.setInt(6, funcionarioAlterado.getTipoCargo().getValor());
			query.setInt(7, funcionarioAlterado.getId());
			registrosAlterados = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar funcionário existente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public Funcionario consultarPorId(int id) {
		Funcionario funcionarioBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM FUNCIONARIO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				funcionarioBuscado = montarFuncionarioComResultadoDoBanco(resultado);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar funcionário com id" + id + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return funcionarioBuscado;
	}

	public List<Funcionario> consultarTodos() {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM FUNCIONARIO ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Funcionario funcionarioBuscado = montarFuncionarioComResultadoDoBanco(resultado);
				funcionarios.add(funcionarioBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return funcionarios;
	}

	public boolean cpfJaUtilizado(String cpfBuscado) {
		boolean cpfJaUtilizado = false;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT COUNT(*) FROM FUNCIONARIO " + " WHERE CPF = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, cpfBuscado);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				cpfJaUtilizado = resultado.getInt(1) > 0;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao verificar uso do CPF: " + cpfBuscado + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return cpfJaUtilizado;
	}

	// TODO exclusão lógica
	public boolean excluir(Funcionario funcionario) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE FUNCIONARIO SET DATADESLIGAMENTO = ? " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setDate(1,  java.sql.Date.valueOf(funcionario.getDataDesligamento()));
			query.setInt(2, funcionario.getId());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				excluiu = true;
			}
		} catch (SQLException excecao) {
			System.out.println("\nErro ao excluir funcionario. " + "\n Causa: " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return excluiu;
	}
	
	private Funcionario montarFuncionarioComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Funcionario funcionarioBuscado = new Funcionario();
		funcionarioBuscado.setId(resultado.getInt("id"));
		funcionarioBuscado.setNome(resultado.getString("nome"));
		funcionarioBuscado.setCpf(resultado.getString("cpf"));
		funcionarioBuscado.setTelefone(resultado.getString("telefone"));
		funcionarioBuscado.setDataNascimento(resultado.getDate("datanascimento").toLocalDate());
		funcionarioBuscado.setCtps(resultado.getString("ctps"));
		funcionarioBuscado.setMatricula(resultado.getString("matricula"));
		funcionarioBuscado.setSenha(resultado.getString("senha"));
		funcionarioBuscado.setDataDesligamento(resultado.getDate("datadesligamento").toLocalDate());
		funcionarioBuscado.setTipoUsuario(TipoUsuario.getTipoUsuarioPorValor(resultado.getInt("idtipousuario")));
		funcionarioBuscado.setTipoCargo(TipoCargo.getTipoCargoPorValor(resultado.getInt("idtipocargo")));
		return funcionarioBuscado;
	}

	public int contarFuncionariosQueTrabalhamNoEndereco(int id) {
		int totalFuncionariosDoEnderecoBuscado = 0;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT COUNT(ID) FROM FUNCIONARIO " + " WHERE ID = ? ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				totalFuncionariosDoEnderecoBuscado = resultado.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Erro contar os funcionarios que trabalham em um endereço. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return totalFuncionariosDoEnderecoBuscado;
	}
	
	public ArrayList<TipoUsuario> consultarTipoUsuario() {
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);
		ResultSet resultado = null;
		ArrayList<TipoUsuario> listaTipoUsuario = new ArrayList<TipoUsuario>();
		String query = "SELECT descricao FROM tipousuario";
		try {
			resultado = stmt.executeQuery(query);
			while(resultado.next()){
				TipoUsuario tipoUsuario = TipoUsuario.valueOf(resultado.getString(1));
				listaTipoUsuario.add(tipoUsuario);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao executar a query do método consultarTipoUsuarioDAO.");
			System.out.println("Erro: " + erro.getMessage());
		} finally {
			Banco.closeResultSet(resultado);
			Banco.closeStatement(stmt);
			Banco.closeConnection(conn);
		}
		return listaTipoUsuario;
	}
}
