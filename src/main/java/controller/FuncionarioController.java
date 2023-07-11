package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.accessibility.AccessibleContext;

import model.bo.FuncionarioBO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilhas;
import model.seletor.FuncionarioSeletor;
import model.vo.Atividade;
import model.vo.Funcionario;
import model.vo.Prestacao;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

public class FuncionarioController {
	private FuncionarioBO bo = new FuncionarioBO();

	public Funcionario inserir(Funcionario novoFuncionario) throws CampoInvalidoException {
		this.validarCamposObrigatorios(novoFuncionario);
		return bo.inserir(novoFuncionario);
	}

	public boolean atualizar(Funcionario funcionarioAlterado) throws CampoInvalidoException {
		this.validarCamposObrigatorios(funcionarioAlterado);
		return bo.atualizar(funcionarioAlterado);
	}

	private void validarCamposObrigatorios(Funcionario novoFuncionario) throws CampoInvalidoException {
		String mensagemValidacao = "";

		mensagemValidacao += validarString(novoFuncionario.getNome(), "Nome");
		mensagemValidacao += validarString(novoFuncionario.getCpf(), "CPF");
		mensagemValidacao += validarTelefone(novoFuncionario.getTelefone());
		mensagemValidacao += validarData(novoFuncionario.getDataNascimento(), "Data de Nascimento");
		mensagemValidacao += validarString(novoFuncionario.getCtps(), "CTPs");
		if (novoFuncionario.getTipoUsuario() == null) {
			mensagemValidacao += validarTipoUsuario(novoFuncionario.getTipoUsuario(), "Tipo de Usuário");
		}
		if (novoFuncionario.getTipoCargo() == null) {
			mensagemValidacao += validarTipoCargo(novoFuncionario.getTipoCargo(), "Cargo do Usuário");
		}
		mensagemValidacao += validarMatricula(novoFuncionario.getMatricula());
		mensagemValidacao += validarSenha(novoFuncionario.getSenha());

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	private String validarTipoCargo(TipoCargo tipoCargo, String nomeCampo) {
		boolean valido = (tipoCargo != null);

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	private String validarTipoUsuario(TipoUsuario tipoUsuario, String nomeCampo) {
		boolean valido = (tipoUsuario != null);

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}

	private String validarString(String texto, String nomeCampo) {
		boolean valido = (texto != null) && !texto.trim().isEmpty();

		if (valido) {
			return "";
		} else {
			return "- " + nomeCampo + "\n";
		}
	}
	
	private String validarMatricula(String texto) {
		boolean valido = false;
	    if (texto != null && texto.length() > 0) {
	        String expression = "^[0-9]{6,6}$";
	        Pattern pattern = Pattern.compile(expression);
	        Matcher matcher = pattern.matcher(texto);
	        if (matcher.matches()) {
	        	valido = true;
	        }
	    }
	    
	    if (valido) {
			return "";
		} else {
			return "- Matrícula deve conter apenas 6 dígitos numéricos\n";
		}
	}
	
	private String validarTelefone(String telefone) {
        boolean valido = false;
        if (telefone != null && telefone.length() > 0) {
            String expression = "^[1-9]{2}+9([0-9]{8})";
            Pattern pattern = Pattern.compile(expression);
            Matcher matcher = pattern.matcher(telefone);
            if (matcher.matches()) {
                valido = true;
            }
        } else {
        	return "- Celular\n";
        }

        if (valido) {
            return "";
        } else {
            return "- O terceiro dígito do telefone é obrigatóriamente 9\n";
        }
    } 

	
	private String validarSenha(String texto) {
		boolean valido = false;
	    if (texto != null && texto.length() > 0) {
	        String expression = "^[0-9]{6,6}$";
	        Pattern pattern = Pattern.compile(expression);
	        Matcher matcher = pattern.matcher(texto);
	        if (matcher.matches()) {
	        	valido = true;
	        }
	    }
	    
	    if (valido) {
			return "";
		} else {
			return "- Senha deve conter apenas 6 dígitos numéricos\n";
		}
	}

	private String validarData(LocalDate data, String nomeCampo) {
        boolean valido = ((data != null && data.getYear() > 1940) && (data.getYear() <= (LocalDate.now().getYear() - 18)));

        if (valido) {
            return "";
        } else {
            return "- " + nomeCampo + "\n";
        }
    }

	public boolean excluir(Funcionario funcionario) throws CampoInvalidoException {
		return bo.excluir(funcionario);
	}

	public Funcionario consultarPorId(int id) {
		return bo.consultarPorId(id);
	}

	public List<Funcionario> consultarTodos() {
		return bo.consultarTodos();
	}

	public int contarFuncionariosQueTrabalhamNoEndereco(int id) {
		return bo.contarFuncionariosQueTrabalhamNoEndereco(id);
	}

	public ArrayList<TipoUsuario> consultarTipoUsuario() {
		return bo.consultarTipoUsuario();
	}

	public int contarTotalRegistrosComFiltros(FuncionarioSeletor seletor) {
		return bo.contarTotalRegistrosComFiltros(seletor);
	}

	public String gerarPlanilha(List<Funcionario> funcionarios, String caminho) throws CampoInvalidoException {

		if (funcionarios == null || caminho == null || caminho.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilhas gerador = new GeradorPlanilhas();
		return gerador.geradorPlanilhaFuncionarios(funcionarios, caminho);
	}

	public List<Funcionario> consultarComFiltros(FuncionarioSeletor seletor) {
		return bo.consultarComFiltros(seletor);
	}

	public Funcionario consultarPorLoginSenha(String matricula, String senha) throws CampoInvalidoException {
		Funcionario funcionarioConsultado = null;
		boolean valido = (matricula != null && !matricula.isEmpty()) && (senha != null && !senha.isEmpty());
		if (valido) {
			funcionarioConsultado = bo.consultarPorLoginSenha(matricula, senha);
		} else {
			throw new CampoInvalidoException("Login ou senha inválidos!");
		}
		return funcionarioConsultado;
	}
	
	

}
