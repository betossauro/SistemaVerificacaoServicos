// TODO: Classe de ocorrência ficará para a próxima versão do projeto, pois devido ao tempo restante, foi decidido que seria melhor ter a introdução
// desta classe em um próximo momento.

//package model.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import model.vo.Ocorrencia;
//
//public class OcorrenciaDAO {
//
//	public Ocorrencia inserir (Ocorrencia novaOcorrencia) {
//		Connection conexao = Banco.getConnection();
//		String sql = " INSERT INTO OCORRENCIA (DESCRICAO, IDPRESTACAO) " + " VALUES (?,?) ";
//		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
//		try {
//			query.setString(1, novaOcorrencia.getDescricao());
//			query.setInt(2, novaOcorrencia.getIdPrestacao());
//			query.execute();
//			ResultSet resultado = query.getGeneratedKeys();
//			if (resultado.next()) {
//				novaOcorrencia.setId(resultado.getInt(1));
//			}
//		} catch (SQLException erro) {
//			System.out.println("Erro ao inserir ocorrência" + "\nCausa: " + erro.getMessage());
//		} finally {
//			Banco.closePreparedStatement(query);
//			Banco.closeConnection(conexao);
//		}
//		return novaOcorrencia;
//	}
//	
//	public boolean atualizar(Ocorrencia ocorrenciaAlterada) {
//		Connection conexao = Banco.getConnection();
//		String sql = " UPDATE OCORRENCIA SET DESCRICAO = ?, IDPRESTACAO = ? "
//				+ " WHERE ID = ?";
//		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
//		int registrosAlterados = 0;
//		try {
//			query.setString(1, ocorrenciaAlterada.getDescricao());
//			query.setInt(2, ocorrenciaAlterada.getIdPrestacao());
//			query.setInt(3, ocorrenciaAlterada.getId());
//			registrosAlterados = query.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("Erro ao atualizar prestação existente.");
//			System.out.println("Erro: " + e.getMessage());
//		}
//
//		return registrosAlterados > 0;
//	}
//
//	public Ocorrencia consultarPorId(int id) {
//		Ocorrencia ocorrenciaBuscada = null;
//		Connection conexao = Banco.getConnection();
//		String sql = " SELECT * FROM OCORRENCIA " + " WHERE ID = ? ";
//		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
//		try {
//			query.setInt(1, id);
//			ResultSet resultado = query.executeQuery();
//			if (resultado.next()) {
//				ocorrenciaBuscada = montarOcorrenciaComResultadoDoBanco(resultado);
//			}
//		} catch (SQLException erro) {
//			System.out.println("Erro ao buscar ocorrência com id" + id + "\nCausa: " + erro.getMessage());
//		} finally {
//			Banco.closePreparedStatement(query);
//			Banco.closeConnection(conexao);
//		}
//		return ocorrenciaBuscada;
//	}
//
//	public List<Ocorrencia> consultarTodos() {
//		List<Ocorrencia> prestacoes = new ArrayList<Ocorrencia>();
//		Connection conexao = Banco.getConnection();
//		String sql = " SELECT * FROM OCORRENCIA ";
//
//		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
//		try {
//			ResultSet resultado = query.executeQuery();
//			while (resultado.next()) {
//				Ocorrencia prestacaoBuscada = montarOcorrenciaComResultadoDoBanco(resultado);
//				prestacoes.add(prestacaoBuscada);
//			}
//
//		} catch (Exception e) {
//			System.out.println("Erro ao buscar todas as ocorrências. \n Causa:" + e.getMessage());
//		} finally {
//			Banco.closePreparedStatement(query);
//			Banco.closeConnection(conexao);
//		}
//		return prestacoes;
//	}
//
//	public List<Ocorrencia> consultarPorIdPrestacao(int idPrestacao) {
//		Connection conexao = Banco.getConnection();
//		List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
//		String sql = " SELECT * FROM OCORRENCIA WHERE IDPRESTACAO = " + idPrestacao;
//		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
//		try {
//			ResultSet resultado = query.executeQuery();
//			while (resultado.next()) {
//				Ocorrencia ocorrenciaConsultada = montarOcorrenciaComResultadoDoBanco(resultado);
//				ocorrencias.add(ocorrenciaConsultada);
//			}
//		} catch (SQLException erro) {
//			System.out.println("Erro ao buscar todos as ocorrências com id " + idPrestacao + "\nCausa: " + erro.getMessage());
//		} finally {
//			Banco.closePreparedStatement(query);
//			Banco.closeConnection(conexao);
//		}
//		return ocorrencias;
//	}
//
//	private Ocorrencia montarOcorrenciaComResultadoDoBanco(ResultSet resultado) throws SQLException {
//		Ocorrencia ocorrenciaConsultada = new Ocorrencia();
//		ocorrenciaConsultada.setId(resultado.getInt("id"));
//		ocorrenciaConsultada.setDescricao(resultado.getString("descricao"));
//		ocorrenciaConsultada.setIdPrestacao(resultado.getInt("idprestacao"));
//		return ocorrenciaConsultada;
//	}
//
//}
