package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.dto.PrestacaoDTO;
import model.seletor.FuncionarioSeletor;
import model.seletor.PrestacaoSeletor;
import model.vo.Funcionario;
import model.vo.Prestacao;

public class PrestacaoDAO {

	public Prestacao inserir(Prestacao novaPrestacao) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO PRESTACAO (IDFUNCIONARIO, IDSALA, DATAINICIO, DATAFIM) " + " VALUES (?,?,?,?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			query.setInt(1, novaPrestacao.getIdFuncionario());
			query.setInt(2, novaPrestacao.getIdSala());
			query.setTimestamp(3, java.sql.Timestamp.valueOf(novaPrestacao.getDataInicio()));
			query.setTimestamp(4, java.sql.Timestamp.valueOf(novaPrestacao.getDataFim()));
			query.execute();
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novaPrestacao.setId(resultado.getInt(1));
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao inserir prestação de serviço" + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novaPrestacao;
	}

	public boolean atualizar(Prestacao prestacaoAlterada) {
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE PRESTACAO SET IDFUNCIONARIO = ?, IDSALA = ?, DATAINICIO = ?, DATAFIM = ? "
				+ " WHERE ID = ?";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		int registrosAlterados = 0;
		try {
			query.setInt(1, prestacaoAlterada.getIdFuncionario());
			query.setInt(2, prestacaoAlterada.getIdSala());
			query.setTimestamp(3, java.sql.Timestamp.valueOf(prestacaoAlterada.getDataInicio()));
			query.setTimestamp(4, java.sql.Timestamp.valueOf(prestacaoAlterada.getDataFim()));
			query.setInt(5, prestacaoAlterada.getId());
			registrosAlterados = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar prestação existente.");
			System.out.println("Erro: " + e.getMessage());
		}

		return registrosAlterados > 0;
	}

	public Prestacao consultarPorId(int id) {
		Prestacao prestacaoBuscada = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PRESTACAO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				prestacaoBuscada = montarPrestacaoComResultadoDoBanco(resultado);
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao buscar prestação com id" + id + "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return prestacaoBuscada;
	}

	public List<Prestacao> consultarTodos() {
		List<Prestacao> prestacoes = new ArrayList<Prestacao>();
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PRESTACAO ";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Prestacao prestacaoBuscada = montarPrestacaoComResultadoDoBanco(resultado);
				prestacoes.add(prestacaoBuscada);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todas as prestações. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return prestacoes;
	}

	public List<PrestacaoDTO> consultarDTO(PrestacaoSeletor seletor) {
		List<PrestacaoDTO> prestacoesDTO = new ArrayList<PrestacaoDTO>();
		Connection conexao = Banco.getConnection();
		String sql = "SELECT F.ID as idFuncionario, F.NOME as nomeFuncionario, TC.descricao as nomeCargo, "
				+ " S.ID as idSala, S.NUMERO as numeroSala,P.DATAINICIO as dataInicio, P.DATAFIM as dataFim,"
				+ " A.DESCRICAO as servico, O.DESCRICAO as ocorrencia"
				+ "FROM PRESTACAO P, FUNCIONARIO F, TIPOCARGO TC, SALA S, ATIVIDADE A, OCORRENCIA O "
				+ "WHERE P.idFuncionario = F.id " + "AND TC.id = F.IDTIPOCARGO " + "AND S.id = P.IDSALA ";

		if (seletor.temFiltro()) {
			sql = preencherFiltros(sql, seletor);
		}

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				PrestacaoDTO dto = new PrestacaoDTO();
				dto.setIdFuncionario(resultado.getString("idFuncionario"));
				dto.setNomeFuncionario(resultado.getString("nomeFuncionario"));
				dto.setNomeCargo(resultado.getString("nomeCargo"));
				dto.setIdSala(resultado.getString("idSala"));
				dto.setNumeroSala(resultado.getString("numeroSala"));
				dto.setDataInicio(resultado.getString("dataInicio"));
				dto.setDataFim(resultado.getString("dataFim"));
				dto.setServico(resultado.getString("servico"));
				dto.setOcorrencia(resultado.getString("ocorrencia"));

				prestacoesDTO.add(dto);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar todas as prestações. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return prestacoesDTO;
	}

	private String preencherFiltros(String sql, PrestacaoSeletor seletor) {

		if (seletor.getNomeFuncionario() != null) {
			sql += " AND f.NOME LIKE '%" + seletor.getNomeFuncionario() + "%'";
		}

		if (seletor.getTipoCargo() != null) {
			sql += " AND TC.descricao = " + seletor.getTipoCargo().name();
		}

		if (seletor.getNumeroSala() != null) {
			sql += " AND S.NUMERO = " + seletor.getNumeroSala();
		}

		if (seletor.getDataInicio() != null && seletor.getDataFim() != null) {
			sql += " AND P.DATAINICIO BETWEEN " + seletor.getDataInicio() + " AND " + seletor.getDataFim();
		}

		if (seletor.getDataInicio() != null) {
			sql += " AND P.DATAINICIO AFTER " + seletor.getDataInicio();
		}

		if (seletor.getDataFim() != null) {
			sql += " AND P.DATAFIM BEFORE " + seletor.getDataFim();
		}

		if (seletor.getOcorrencia() != null) {
			sql += " AND O.DESCRICAO LIKE '%" + seletor.getOcorrencia() + "%'";
		}
		return sql;
	}

	private Prestacao montarPrestacaoComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Prestacao prestacaoBuscada = new Prestacao();
		prestacaoBuscada.setId(resultado.getInt("id"));
		prestacaoBuscada.setIdFuncionario(resultado.getInt("idfuncionario"));
		prestacaoBuscada.setIdSala(resultado.getInt("idsala"));
		prestacaoBuscada.setDataInicio(resultado.getTimestamp("datainicio").toLocalDateTime());
		prestacaoBuscada.setDataFim(resultado.getTimestamp("datafim").toLocalDateTime());

		OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO();
		prestacaoBuscada.setListaOcorrencias(ocorrenciaDAO.consultarPorIdPrestacao(prestacaoBuscada.getId()));

		AtividadeDAO atividadeDAO = new AtividadeDAO();
		prestacaoBuscada.setListaAtividades(atividadeDAO.consultarPorIdPrestacao(prestacaoBuscada.getId()));
		return prestacaoBuscada;
	}

	public boolean funcionarioTemPrestacaoPendente(int idFuncionario) {
		boolean prestacaoPendente = false;
		Connection conexao = Banco.getConnection();
		String sql = "SELECT COUNT(*) FROM PRESTACAO WHERE IDFUNCIONARIO = ? AND " + "DATAFIM IS NULL;";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, idFuncionario);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				prestacaoPendente = resultado.getInt(1) > 0;
			}
		} catch (SQLException erro) {
			System.out.println("Erro ao verificar pendencias relacionadas ao funcionário com id: " + idFuncionario
					+ "\nCausa: " + erro.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return prestacaoPendente;
	}

	public int contarTotalRegistrosDTOComFiltros(PrestacaoSeletor seletor) {
		int total = 0;
		Connection conexao = Banco.getConnection();
		String sql = "SELECT COUNT(p.id)"
				+ "FROM PRESTACAO P, FUNCIONARIO F, TIPOCARGO TC, SALA S, ATIVIDADE A, OCORRENCIA O "
				+ "WHERE P.idFuncionario = F.id " + "AND TC.id = F.IDTIPOCARGO " + "AND S.id = P.IDSALA ";

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
			System.out.println("Erro contar o total de prestações" + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return total;
	}

}
