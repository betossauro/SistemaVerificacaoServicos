package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.vo.Funcionario;
import model.vo.TipoUsuario;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frmBemVindo;
	private JMenuBar menuBar;
	private JMenu mnCadastrar;
	private JMenuItem mntmCadastrarUsuario;
	private JMenu mnConsultar;
	private JMenuItem mntmConsultarServico;
	private JMenuItem mntmConsultarFuncionario;
	private JMenu mnGerenciar;
	private JMenuItem mntmGerenciarFuncionarios;
	private Funcionario usuarioAutenticado;
	private PainelMenuGerencia painelMenuGerencia;
	private PainelMenuFuncionario painelMenuFuncionario;
	private PainelConsultaGerenciamentoFuncionarios painelGerenciamentoFuncionarios;
	private PainelCadastroUsuario painelEdicaoFuncionario;

	/**
	 * Create the application.
	 */
	public TelaPrincipal(Funcionario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBemVindo = new JFrame();
		frmBemVindo.setTitle("Bem vindo");
		frmBemVindo.setBounds(100, 100, 1200, 1000);
		frmBemVindo.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmBemVindo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frmBemVindo.setJMenuBar(menuBar);
		painelMenuGerencia = new PainelMenuGerencia();
		painelMenuGerencia.setVisible(false);

		painelMenuFuncionario = new PainelMenuFuncionario();
		painelMenuFuncionario.setVisible(false);

		if (usuarioAutenticado.getTipoUsuario().getValor() == TipoUsuario.ADMINISTRADOR.getValor()) {
			frmBemVindo.setTitle("Bem vindo, " + usuarioAutenticado.getNome());
			painelMenuGerencia.setVisible(true);
			frmBemVindo.setContentPane(painelMenuGerencia);
			frmBemVindo.setVisible(true);
		} else if (usuarioAutenticado.getTipoUsuario().getValor() == TipoUsuario.FUNCIONARIO.getValor()) {
			frmBemVindo.setTitle("Bem vindo, " + usuarioAutenticado.getNome());
			menuBar.setVisible(false);
			painelMenuFuncionario.setVisible(true);
			frmBemVindo.setContentPane(painelMenuFuncionario);
			frmBemVindo.setVisible(true);
		}

		mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);

		mntmCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnCadastrar.add(mntmCadastrarUsuario);

		mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);

		mntmConsultarServico = new JMenuItem("Consultar Serviço");
		mntmConsultarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnConsultar.add(mntmConsultarServico);

		mntmConsultarFuncionario = new JMenuItem("Consultar Funcionário");
		mntmConsultarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnConsultar.add(mntmConsultarFuncionario);

		mnGerenciar = new JMenu("Gerenciar");
		menuBar.add(mnGerenciar);
		// Na hora de criar o painel, chamar o usuarioAutenticado em
		// PainelRegistroServico
		mntmGerenciarFuncionarios = new JMenuItem("Gerenciar Funcionários");
		mntmGerenciarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarCliqueBotaoEditarDoPainelConsultaGerenciamentoFuncionario();
				registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario();
			}
		});
		mnGerenciar.add(mntmGerenciarFuncionarios);

		// TODO
		// Mostrar/esconder itens do menu conforme usuário autenticado
	}

	protected void registrarCliqueBotaoEditarDoPainelConsultaGerenciamentoFuncionario() {
		painelGerenciamentoFuncionarios.getBtnEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelEdicaoFuncionario = new PainelCadastroUsuario(
						painelGerenciamentoFuncionarios.getFuncionarioSelecionado());
				painelGerenciamentoFuncionarios.setVisible(true);

				// Atualiza a tela principal
				frmBemVindo.setContentPane(painelGerenciamentoFuncionarios);
				frmBemVindo.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario() {
		if (painelGerenciamentoFuncionarios == null) {
			painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios();
		}

		// Registrar o evento de clique no voltar do
		// PainelConsultaGerenciamentoFuncionarios
		painelGerenciamentoFuncionarios.getBtnVoltar().addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// Lógica do clique no botão Voltar
				// Mostra menu de gerente
				painelMenuGerencia = new PainelMenuGerencia();
				painelMenuGerencia.setVisible(true);
				registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario();
				frmBemVindo.setContentPane(painelGerenciamentoFuncionarios);
				frmBemVindo.revalidate();
			}
		});
	}

}
