package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Sala;

public class SalaDAO {
//	 	private int id;
//	    private String numero;
//	    private boolean disponivel;

	
	// TODO 
	public Sala inserir(Sala novaSala) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO SALA (NUMERO, DISPONIVEL)" + " VALUES (?, TRUE)";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			query.setString(1, novaSala.getNumero());
			query.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaSala.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao inserir sala. \nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novaSala;
	}

	public boolean atualizar(Sala salaEditada) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE SALA SET NUMERO = ?, DISPONIVEL = ? " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, salaEditada.getNumero());
			query.setBoolean(2, salaEditada.isDisponivel());
			query.setInt(3, salaEditada.getId());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				// se fosse igual a zero isso indicaria que não houve atualização em nenhuma das
				// variaveis
				atualizou = true;
			}
		} catch (SQLException excecao) {
			System.out.println("\nErro ao atualizar sala. " + "\n Causa: " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return atualizou;
	}

	public Sala consultarPorId(int id) {
		Sala salaConsultada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM SALA " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				salaConsultada = converterDeResultSetParaEntidade(resultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar sala com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		return salaConsultada;
	}

	public List<Sala> consultarTodos() {
		Connection conexao = Banco.getConnection();
		List<Sala> listaSalasVO = new ArrayList<Sala>();
		String sql = " SELECT * FROM SALA ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Sala salaConsultada = converterDeResultSetParaEntidade(resultado);

				listaSalasVO.add(salaConsultada);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar salas" + "\n Causa" + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		return listaSalasVO;
	}

	/**
	 * Chama as entidades necessárias para a criação de um objeto sala
	 * 
	 * Método feito para que as consultas diminuam a quantidade de linhas, visto q
	 * ambas (por id e todos) possuem este mesmo trecho de código
	 * 
	 * @param resultado
	 * @return enderecoConsultado
	 */
	private Sala converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Sala salaConsultada = new Sala();
		salaConsultada.setId(resultado.getInt("id"));
		salaConsultada.setNumero(resultado.getString("numero"));
		salaConsultada.setDisponivel(resultado.getBoolean("disponivel"));
		return salaConsultada;
	}

	public boolean excluir(Sala sala) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE SALA SET DISPONIVEL = ? " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setBoolean(1, (Boolean) null);
			query.setInt(2, sala.getId());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				excluiu = true;
			}
		} catch (SQLException excecao) {
			System.out.println("\nErro ao excluir sala. " + "\n Causa: " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return excluiu;
	}
}
