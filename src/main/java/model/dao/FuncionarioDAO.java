package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

public class FuncionarioDAO {

	public Funcionario inserir(Funcionario novoFuncionario) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO FUNCIONARIO (NOME, CPF, TELEFONE, DATANASCIMENTO, CTPS, MATRICULA, SENHA, DATTDESLIGAMENTO, IDTIPOCARGO, IDTIPOUSUARIO) "
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
		String sql = " SELECT * FROM CLIENTE " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				funcionarioBuscado = montarFuncionarioComResultadoDoBanco(resultado);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar cliente com id" + id + "\nCausa: " + erro.getMessage());
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
//    public boolean excluir(int id) {
//        Connection conexao = Banco.getConnection();
//        String sql = " DELETE FROM FUNCIONARIO WHERE ID = " + id;
//        Statement query = Banco.getStatement(conexao);
//        int quantidadeLinhasAfetadas = 0;
//        try {
//            quantidadeLinhasAfetadas = query.executeUpdate(sql);
//        } catch (SQLException e) {
//            System.out.println("Erro ao excluir funcionário.");
//            System.out.println("Erro: " + e.getMessage());
//        }
//        boolean excluiu = quantidadeLinhasAfetadas > 0;
//
////        if (excluiu) {
////            TelefoneDAO telefoneDAO = new TelefoneDAO();
////            telefoneDAO.desativarTelefones(id);
////        }
//        return excluiu;
//    }

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
}
