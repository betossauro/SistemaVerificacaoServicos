package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.vo.Endereco;

public class EnderecoDAO {
	
//	  private int id;
//    private String rua;
//    private String numero;
//    private String bairro;
//    private String cep;
//    private String cidade;
//    private String estado;
    
	public Endereco inserir(Endereco novoEndereco) {
		Connection conexao = Banco.getConnection();
		String sql = " INSERT INTO ENDERECO (RUA, NUMERO, BAIRRO, CEP, CIDADE, ESTADO)" + " VALUES (?,?,?,?,?,?)";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);

		try {
			query.setString(1, novoEndereco.getRua());
			query.setString(2, novoEndereco.getNumero());
			query.setString(3, novoEndereco.getBairro());
			query.setString(4, novoEndereco.getCep());
			query.setString(5, novoEndereco.getCidade());
			query.setString(6, novoEndereco.getEstado());
			query.execute();

			// Preencher o id gerado no banco no objeto
			ResultSet resultado = query.getGeneratedKeys();
			if (resultado.next()) {
				novoEndereco.setId(resultado.getInt(1));
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao inserir endereço. \nCausa: " + e.getMessage());
		} finally {
			// Fechar a conexão
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return novoEndereco;
	}
	public boolean atualizar(Endereco enderecoEditado) {
		boolean atualizou = false;
		Connection conexao = Banco.getConnection();
		String sql = " UPDATE ENDERECO SET RUA = ?, NUMERO = ?, BAIRRO = ?, CEP = ?, CIDADE = ?, ESTADO = ? "
				+ " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setString(1, enderecoEditado.getRua());
			query.setString(2, enderecoEditado.getNumero());
			query.setString(3, enderecoEditado.getBairro());
			query.setString(4, enderecoEditado.getCep());
			query.setString(5, enderecoEditado.getCidade());
			query.setString(6, enderecoEditado.getEstado());
			query.setInt(7, enderecoEditado.getId());

			int quantidadeLinhasAtualizadas = query.executeUpdate();
			if (quantidadeLinhasAtualizadas > 0) {
				// se fosse igual a zero isso indicaria que não houve atualização em nenhuma das
				// variaveis
				atualizou = true;
			}
		} catch (SQLException excecao) {
			System.out.println("\nErro ao atualizar endereço. " + "\n Causa: " + excecao.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return atualizou;
	}
	
	public Endereco consultarPorId(int id) {
		Endereco enderecoConsultado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM ENDERECO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
			if (resultado.next()) {
				enderecoConsultado = converterDeResultSetParaEntidade(resultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar endereço com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		return enderecoConsultado;
	}

	public List<Endereco> consultarTodos() {
		Connection conexao = Banco.getConnection();
		List<Endereco> listaEnderecosVO = new ArrayList<Endereco>();
		String sql = " SELECT * FROM ENDERECO ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				Endereco enderecoConsultado = converterDeResultSetParaEntidade(resultado);

				listaEnderecosVO.add(enderecoConsultado);
			}

		} catch (SQLException e) {
			System.out.println("\nErro ao buscar endereços" + "\n Causa" + e.getMessage());
		} finally {
			Banco.closeStatement(query);
			Banco.closeConnection(conexao);
		}
		return listaEnderecosVO;
	}

	/**
	 * Chama as entidades necessárias para a criação de um objeto endereço
	 * 
	 * Método feito para que as consultas diminuam a quantidade de linhas, visto q
	 * ambas (por id e todos) possuem este mesmo trecho de código
	 * 
	 * @param resultado
	 * @return enderecoConsultado
	 */
	private Endereco converterDeResultSetParaEntidade(ResultSet resultado) throws SQLException {
		Endereco enderecoConsultado = new Endereco();
		enderecoConsultado.setId(resultado.getInt("id"));
		enderecoConsultado.setRua(resultado.getString("rua"));
		enderecoConsultado.setNumero(resultado.getString("numero"));
		enderecoConsultado.setBairro(resultado.getString("bairro"));
		enderecoConsultado.setCep(resultado.getString("cep"));
		enderecoConsultado.setCidade(resultado.getString("cidade"));
		enderecoConsultado.setEstado(resultado.getString("estado"));
		return enderecoConsultado;
	}

	public boolean excluir(int id) {
		boolean excluiu = false;
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM ENDERECO " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			int quantidadeLinhasAtualizadas = query.executeUpdate();
			excluiu = quantidadeLinhasAtualizadas > 0;

		} catch (SQLException e) {
			System.out.println("Erro ao excluir endereço com id: " + id + "\n Causa" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return excluiu;
	}
}
