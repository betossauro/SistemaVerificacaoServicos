package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Atividade;

public class AtividadeDAO {

	public Atividade inserir(Atividade novaAtividade) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ATIVIDADE (DESCRICAO, IDPRESTACAO) " + " VALUES (?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			query.setInt(1, novaAtividade.getIdCargo());
			query.setString(2, novaAtividade.getDescricao());
			query.execute();
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaAtividade.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao inserir atividade" + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novaAtividade;
	}
	
	public boolean atualizar(Atividade atividadeAlterada) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ATIVIDADE SET DESCRICAO = ?, IDPRESTACAO = ? "
				+ " WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			query.setInt(1, atividadeAlterada.getIdCargo());
			query.setString(2, atividadeAlterada.getDescricao());
			query.setInt(3, atividadeAlterada.getId());
			registrosAlterados = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar atividade existente.");
			System.out.println("Erro: " + e.getMessage());
		}
		return registrosAlterados > 0;
	}

	public Atividade consultarPorId(int id) {
		Atividade atividadeBuscada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ATIVIDADE " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				atividadeBuscada = montarAtividadeComResultadoDoBanco(resultado);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar cliente com id" + id + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return atividadeBuscada;
	}

	public List<Atividade> consultarTodos() {
		List<Atividade> atividades = new ArrayList<Atividade>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ATIVIDADE ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Atividade atividadeBuscada = montarAtividadeComResultadoDoBanco(resultado);
				atividades.add(atividadeBuscada);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todos os clientes. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return atividades;
	}

	public List<Atividade> consultarPorIdPrestacao(int idPrestacao) {
		Connection conexao = Banco.getConnection();
		List<Atividade> atividades = new ArrayList<Atividade>();
		String sql = " SELECT A.* FROM ATIVIDADE A, PRESTACAO_ATIVIDADE PA WHERE IDPRESTACAO = " + idPrestacao + " AND A.ID = PA.IDATIVIDADE ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Atividade atividadeConsultada = montarAtividadeComResultadoDoBanco(resultado);
				atividades.add(atividadeConsultada);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar todas as atividades." + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return atividades;
	}

	private Atividade montarAtividadeComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Atividade atividadeConsultada = new Atividade();
		atividadeConsultada.setId(resultado.getInt("id"));
		atividadeConsultada.setIdCargo(resultado.getInt("idcargo"));
		atividadeConsultada.setDescricao(resultado.getString("descricao"));
		return atividadeConsultada;
	}
}
