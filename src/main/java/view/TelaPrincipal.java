package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import model.vo.Funcionario;
import model.vo.TipoUsuario;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frmTelaInicial;
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
	private PainelConsultaFuncionario painelConsultaFuncionario;
	private PainelConsultaGerencia painelConsultaGerencia;
	private PainelRegistroServico painelRegistroServico;
	private PainelCadastroUsuario painelCadastroUsuario;

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
		frmTelaInicial = new JFrame();
		frmTelaInicial.setTitle("Bem vindo");
		frmTelaInicial.setBounds(100, 100, 1200, 1000);
		frmTelaInicial.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmTelaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuBar = new JMenuBar();
		frmTelaInicial.setJMenuBar(menuBar);
		painelMenuGerencia = new PainelMenuGerencia();
		painelMenuGerencia.setVisible(false);

		painelMenuFuncionario = new PainelMenuFuncionario();
		painelMenuFuncionario.setVisible(false);

		if (usuarioAutenticado.getTipoUsuario().getValor() == TipoUsuario.ADMINISTRADOR.getValor()) {
			frmTelaInicial.setTitle("Bem vindo, " + usuarioAutenticado.getNome().split(" ")[0]);
			menuBar.setVisible(true);
			painelMenuGerencia.setVisible(true);
			frmTelaInicial.setContentPane(painelMenuGerencia);
			frmTelaInicial.setVisible(true);
			registrarCliqueBotaoCadastrarUsuarioTelaGerente();
			registrarCliqueBotaoConsultarServicosTelaGerente();
			registrarCliqueBotaoGerenciarUsuarioTelaGerente();
		} else if (usuarioAutenticado.getTipoUsuario().getValor() == TipoUsuario.FUNCIONARIO.getValor()) {
			frmTelaInicial.setTitle("Bem vindo, " + usuarioAutenticado.getNome().split(" ")[0]);
			menuBar.setVisible(false);
			painelMenuFuncionario.setVisible(true);
			frmTelaInicial.setContentPane(painelMenuFuncionario);
			frmTelaInicial.setVisible(true);
			registrarCliqueBotaoRegistrarServicoTelaFuncionario();
			registrarCliqueBotaoConsultarServicosTelaFuncionario();
		}

		mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);

		// Item menu bar
		mntmCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(usuarioAutenticado);
				painelCadastroUsuario.setVisible(true);
				frmTelaInicial.setContentPane(painelCadastroUsuario);
				frmTelaInicial.revalidate();
			}
		});
		mnCadastrar.add(mntmCadastrarUsuario);

		mnConsultar = new JMenu("Consultar");
		menuBar.add(mnConsultar);

		// Item menu bar
		mntmConsultarServico = new JMenuItem("Consultar Serviço");
		mntmConsultarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelConsultaGerencia = new PainelConsultaGerencia(usuarioAutenticado);
				painelConsultaGerencia.setVisible(true);
				frmTelaInicial.setContentPane(painelConsultaGerencia);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaGerencia();
			}
		});
		mnConsultar.add(mntmConsultarServico);

		// Item menu bar
		mntmConsultarFuncionario = new JMenuItem("Consultar Funcionário");
		mntmConsultarFuncionario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios(usuarioAutenticado);
				painelGerenciamentoFuncionarios.setVisible(true);
				painelGerenciamentoFuncionarios.getBtnEditar().setVisible(false);
				frmTelaInicial.setContentPane(painelGerenciamentoFuncionarios);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario();
			}
		});
		mnConsultar.add(mntmConsultarFuncionario);

		// Item menu bar
		mnGerenciar = new JMenu("Gerenciar");
		menuBar.add(mnGerenciar);
		mntmGerenciarFuncionarios = new JMenuItem("Gerenciar Funcionários");
		mntmGerenciarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios(usuarioAutenticado);
				painelGerenciamentoFuncionarios.setVisible(true);
				frmTelaInicial.setContentPane(painelGerenciamentoFuncionarios);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario();
				registrarCliqueBotaoEditarDoPainelConsultaGerenciamentoFuncionario();
			}
		});
		mnGerenciar.add(mntmGerenciarFuncionarios);
	}

	// Item tela menu gerente
	protected void registrarCliqueBotaoCadastrarUsuarioTelaGerente() {
		if (painelCadastroUsuario == null) {
			painelCadastroUsuario = new PainelCadastroUsuario(usuarioAutenticado);
		}
		painelMenuGerencia.getLblIconeCadastrar().addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(usuarioAutenticado);
				painelCadastroUsuario.setVisible(true);
				frmTelaInicial.setContentPane(painelCadastroUsuario);
				frmTelaInicial.revalidate();
			}
		});

	}

	// Item tela menu gerente
	protected void registrarCliqueBotaoConsultarServicosTelaGerente() {
		if (painelConsultaGerencia == null) {
			painelConsultaGerencia = new PainelConsultaGerencia(usuarioAutenticado);
		}
		painelMenuGerencia.getLblIconeConsultar().addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				painelConsultaGerencia = new PainelConsultaGerencia(usuarioAutenticado);
				painelConsultaGerencia.setVisible(true);
				frmTelaInicial.setContentPane(painelConsultaGerencia);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaGerencia();
			}
		});
	}

	// Item tela menu gerente
	protected void registrarCliqueBotaoGerenciarUsuarioTelaGerente() {
		if (painelGerenciamentoFuncionarios == null) {
			painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios(usuarioAutenticado);
		}
		painelMenuGerencia.getLblIconeGerenciar().addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios(usuarioAutenticado);
				painelGerenciamentoFuncionarios.setVisible(true);
				frmTelaInicial.setContentPane(painelGerenciamentoFuncionarios);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario();
			}
		});
	}

	// Item tela menu funcionário
	protected void registrarCliqueBotaoRegistrarServicoTelaFuncionario() {
		if (painelRegistroServico == null) {
			painelRegistroServico = new PainelRegistroServico(usuarioAutenticado);
		}
		painelMenuFuncionario.getLblIconeRegistrar().addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {

				painelRegistroServico = new PainelRegistroServico(usuarioAutenticado);
				painelRegistroServico.setVisible(true);
				frmTelaInicial.setContentPane(painelRegistroServico);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaFuncionario();
			}
		});
	}

	// Item tela menu funcionário
	protected void registrarCliqueBotaoConsultarServicosTelaFuncionario() {
		if (painelConsultaFuncionario == null) {
			painelConsultaFuncionario = new PainelConsultaFuncionario(usuarioAutenticado);
		}
		painelMenuFuncionario.getLblIconeConsultar().addMouseListener(new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
				painelConsultaFuncionario = new PainelConsultaFuncionario(usuarioAutenticado);
				painelConsultaFuncionario.setVisible(true);
				frmTelaInicial.setContentPane(painelConsultaFuncionario);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelConsultaFuncionario();
			}
		});
	}

	// Botões
	
	protected void registrarCliqueBotaoEditarDoPainelConsultaGerenciamentoFuncionario() {
		if (painelGerenciamentoFuncionarios == null) {
			painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios(usuarioAutenticado);
		}
		painelGerenciamentoFuncionarios.getBtnEditar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(
						painelGerenciamentoFuncionarios.getFuncionarioSelecionado());
				painelGerenciamentoFuncionarios.setVisible(true);
				// Atualiza a tela principal
				frmTelaInicial.setContentPane(painelGerenciamentoFuncionarios);
				frmTelaInicial.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario() {
		if (painelGerenciamentoFuncionarios == null) {
			painelGerenciamentoFuncionarios = new PainelConsultaGerenciamentoFuncionarios(usuarioAutenticado);
		}
		painelGerenciamentoFuncionarios.getBtnVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelMenuGerencia = new PainelMenuGerencia();
				painelMenuGerencia.setVisible(true);
				frmTelaInicial.setContentPane(painelMenuGerencia);
				frmTelaInicial.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelConsultaGerencia() {
		if (painelConsultaGerencia == null) {
			painelConsultaGerencia = new PainelConsultaGerencia(usuarioAutenticado);
		}
		painelConsultaGerencia.getBtnVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelMenuGerencia = new PainelMenuGerencia();
				painelMenuGerencia.setVisible(true);
				frmTelaInicial.setContentPane(painelMenuGerencia);
				frmTelaInicial.revalidate();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelConsultaFuncionario() {
		if (painelConsultaFuncionario == null) {
			painelConsultaFuncionario = new PainelConsultaFuncionario(usuarioAutenticado);
		}
		painelConsultaFuncionario.getBtnVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelMenuFuncionario = new PainelMenuFuncionario();
				painelMenuFuncionario.setVisible(true);
				frmTelaInicial.setContentPane(painelMenuFuncionario);
				frmTelaInicial.revalidate();
			}
		});
	}

}
