package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.AccessibleContext;

import model.seletor.FuncionarioSeletor;
import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

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
			if (novoFuncionario.getDataDesligamento() != null) {
				query.setDate(8, java.sql.Date.valueOf(novoFuncionario.getDataDesligamento()));
			}
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
			System.out.println("Erro ao buscar todos os funcionários. \n Causa:" + e.getMessage());
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

	// Exclusão lógica
	public boolean excluir(Funcionario funcionario) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE FUNCIONARIO SET DATADESLIGAMENTO = ? " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setDate(1, java.sql.Date.valueOf(funcionario.getDataDesligamento()));
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
		if (resultado.getDate("datadesligamento") != null) {
			funcionarioBuscado.setDataDesligamento(resultado.getDate("datadesligamento").toLocalDate());
		}
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
			while (resultado.next()) {
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

	public int contarTotalRegistrosComFiltros(FuncionarioSeletor seletor) {
		int total = 0;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT COUNT(*) FROM FUNCIONARIO ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			if (resultado.next()) {
				total = resultado.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Erro contar o total de funcionarios" + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return total;
	}

	public List<Funcionario> consultarComFiltros(FuncionarioSeletor seletor) {
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from funcionario ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		if (seletor.temPaginacao()) {
			sql += " LIMIT " + seletor.getLimite() + " OFFSET " + seletor.getOffset();
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();

			while (resultado.next()) {
				Funcionario funcionarioBuscado = montarFuncionarioComResultadoDoBanco(resultado);
				funcionarios.add(funcionarioBuscado);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os funcionarios. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return funcionarios;
	}

	private String preencherFiltros(String sql, FuncionarioSeletor seletor) {
		boolean primeiro = true;
		if (seletor.getNome() != null && !seletor.getNome().trim().isEmpty()) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}

			sql += " nome LIKE '%" + seletor.getNome() + "%'";
			primeiro = false;
		}

		if (seletor.getTipoCargo() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " IDTIPOCARGO = " + seletor.getTipoCargo().getValor();
			primeiro = false;
		}
		//TODO
		if (seletor.getAtivo() != null) {
			if (primeiro) {
				sql += " WHERE ";
			} else {
				sql += " AND ";
			}
			sql += " DATADESLIGAMENTO IS " + (seletor.getAtivo() == true ? "" : " NOT ") + " NULL";
			primeiro = false;
		}

		return sql;
	}

	public Funcionario consultarPorLoginSenha(String matricula, String senha) {
		Funcionario funcionarioConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql =  " SELECT * FROM FUNCIONARIO "
				    + " WHERE MATRICULA = ? AND SENHA = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		
		try {
			query.setString(1, matricula);
			query.setString(2, senha);
			ResultSet resultado = query.executeQuery();
			
			if(resultado.next()) {
				funcionarioConsultado = montarFuncionarioComResultadoDoBanco(resultado);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar usuario com login: + " + matricula
								+ "\n Causa: " + e.getMessage());	
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return funcionarioConsultado;
	}
}
