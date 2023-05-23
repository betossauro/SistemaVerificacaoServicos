package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

public class FuncionarioDAO {
	
	public Funcionario inserir(Funcionario novoFuncionario) {
        Connection conexao = Banco.getConnection();
        String sql = " INSERT INTO FUNCIONARIO (NOME, CPF, TELEFONE, DTNASCIMENTO, CTPS, IDTIPOUSUARIO, IDTIPOCARGO) " + " VALUES (?,?,?,?,?,?,?) ";
        PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
        try {
            query.setString(1, novoFuncionario.getNome());
            query.setString(2, novoFuncionario.getCpf());
            query.setString(3, novoFuncionario.getTelefone());
            query.setObject(4, novoFuncionario.getDtnascimento());
            query.setString(5, novoFuncionario.getCtps());
            query.setInt(6, novoFuncionario.getTipoUsuario().getValor());
            query.setInt(7, novoFuncionario.getTipoCargo().getValor());
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
        String sql = " UPDATE FUNCIONARIO SET NOME = ?, CPF = ?, TELEFONE = ?, DTNASCIMENTO = ?, CTPS = ?, IDTIPOUSUARIO = ?, IDTIPOCARGO = ? "
                + " WHERE ID = ?";
        PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
        int registrosAlterados = 0;
        try {
        	query.setString(1, funcionarioAlterado.getNome());
            query.setString(2, funcionarioAlterado.getCpf());
            query.setString(3, funcionarioAlterado.getTelefone());
            query.setObject(4, funcionarioAlterado.getDtnascimento());
            query.setString(5, funcionarioAlterado.getCtps());
            query.setInt(6, funcionarioAlterado.getTipoUsuario().getValor());
            query.setInt(7, funcionarioAlterado.getTipoCargo().getValor());
            query.setInt(8, funcionarioAlterado.getId());
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
                funcionarioBuscado = new Funcionario();
                funcionarioBuscado.setId(resultado.getInt("id"));
                funcionarioBuscado.setNome(resultado.getString("nome"));
                funcionarioBuscado.setCpf(resultado.getString("cpf"));
                funcionarioBuscado.setTelefone(resultado.getString("telefone"));
                funcionarioBuscado.setDtnascimento(resultado.getDate("dtnascimento"));
                funcionarioBuscado.setCtps(resultado.getString("ctps"));
                funcionarioBuscado.setTipoUsuario(TipoUsuario.getTipoUsuarioPorValor(resultado.getInt("idtipousuario")));
                funcionarioBuscado.setTipoCargo(TipoCargo.getTipoCargoPorValor(resultado.getInt("idtipocargo")));
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
        funcionarioBuscado.setDtnascimento(resultado.getDate("dtnascimento"));
        funcionarioBuscado.setCtps(resultado.getString("ctps"));
        funcionarioBuscado.setTipoUsuario(TipoUsuario.getTipoUsuarioPorValor(resultado.getInt("idtipousuario")));
        funcionarioBuscado.setTipoCargo(TipoCargo.getTipoCargoPorValor(resultado.getInt("idtipocargo")));
		return funcionarioBuscado;
	}
}
