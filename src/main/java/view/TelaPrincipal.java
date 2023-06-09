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
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;

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
	private JMenu mnEspacoGerente;
	private JMenu mnSair;
	private TelaLogin telaLogin;
	private JMenuItem mntmDesconectar;
	private JMenu mnEspacoFuncionario;

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

		mnCadastrar = new JMenu("Cadastrar");
		menuBar.add(mnCadastrar);

		// Item menu bar
		mntmCadastrarUsuario = new JMenuItem("Cadastrar Usuário");
		mntmCadastrarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelCadastroUsuario = new PainelCadastroUsuario(null);
				painelCadastroUsuario.setVisible(true);
				frmTelaInicial.setContentPane(painelCadastroUsuario);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				montarMenuGerente();
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
				montarMenuGerente();
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
				montarMenuGerente();
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
				montarMenuGerente();
			}
		});
		mnGerenciar.add(mntmGerenciarFuncionarios);

		mnEspacoGerente = new JMenu(
				"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ");
		menuBar.add(mnEspacoGerente);
		
		mnEspacoFuncionario = new JMenu(
				"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ");
		menuBar.add(mnEspacoFuncionario);

		mnSair = new JMenu("Sair");
		mnSair.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/view/icons/exit.png")));
		menuBar.add(mnSair);

		mntmDesconectar = new JMenuItem("Desconectar");
		mntmDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				telaLogin = new TelaLogin();
				telaLogin.setVisible(true);
				frmTelaInicial.setVisible(false);
				frmTelaInicial.dispose();
			}
		});
		mnSair.add(mntmDesconectar);

		
		if (usuarioAutenticado.getTipoUsuario().getValor() == TipoUsuario.ADMINISTRADOR.getValor()) {
			frmTelaInicial.setTitle("Bem vindo, " + usuarioAutenticado.getNome().split(" ")[0]);
			menuBar.setVisible(true);
            mnEspacoFuncionario.setVisible(false);
			painelMenuGerencia.setVisible(true);
			frmTelaInicial.setContentPane(painelMenuGerencia);
			frmTelaInicial.setVisible(true);
			montarMenuGerente();
		} else if (usuarioAutenticado.getTipoUsuario().getValor() == TipoUsuario.FUNCIONARIO.getValor()) {
			frmTelaInicial.setTitle("Bem vindo, " + usuarioAutenticado.getNome().split(" ")[0]);
			menuBar.setVisible(true);
			painelMenuFuncionario.setVisible(true);
			frmTelaInicial.setContentPane(painelMenuFuncionario);
			frmTelaInicial.setVisible(true);
			montarMenuFuncionario();
			esconderItensMenu();
		}
	}

	private void esconderItensMenu() {
		mnEspacoFuncionario.setVisible(true);
		mnCadastrar.setVisible(false);
		mnConsultar.setVisible(false);
		mnGerenciar.setVisible(false);
		mnEspacoGerente.setVisible(false);
		

	}

	// Item tela menu gerente
	protected void registrarCliqueBotaoCadastrarUsuarioTelaGerente() {
		if (painelCadastroUsuario == null) {
			painelCadastroUsuario = new PainelCadastroUsuario(null);
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
				painelCadastroUsuario = new PainelCadastroUsuario(null);
				painelCadastroUsuario.setVisible(true);
				frmTelaInicial.setContentPane(painelCadastroUsuario);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				montarMenuGerente();
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
				montarMenuGerente();
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
				registrarCliqueBotaoEditarDoPainelConsultaGerenciamentoFuncionario();
				registrarCliqueBotaoVoltarDoPainelConsultaGerenciamentoFuncionario();
				montarMenuGerente();
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
				registrarCliqueBotaoVoltarDoPainelRegistrarServico();
				registrarCliqueBotaoRegistrarDoPainelRegistrarServico();
				montarMenuFuncionario();
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
				montarMenuFuncionario();
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
				painelCadastroUsuario.setVisible(true);
				// Atualiza a tela principal
				frmTelaInicial.setContentPane(painelCadastroUsuario);
				frmTelaInicial.revalidate();
				registrarCliqueBotaoVoltarDoPainelCadastroUsuario();
				montarMenuGerente();
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
				montarMenuGerente();
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
				montarMenuGerente();
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
				montarMenuFuncionario();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelCadastroUsuario() {
		if (painelCadastroUsuario == null) {
			painelCadastroUsuario = new PainelCadastroUsuario(usuarioAutenticado);
		}
		painelCadastroUsuario.getBtnVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelMenuGerencia = new PainelMenuGerencia();
				painelMenuGerencia.setVisible(true);
				frmTelaInicial.setContentPane(painelMenuGerencia);
				frmTelaInicial.revalidate();
				montarMenuGerente();
			}
		});
	}

	protected void registrarCliqueBotaoVoltarDoPainelRegistrarServico() {
		if (painelRegistroServico == null) {
			painelRegistroServico = new PainelRegistroServico(usuarioAutenticado);
		}
		painelRegistroServico.getBtnVoltar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelMenuFuncionario = new PainelMenuFuncionario();
				painelMenuFuncionario.setVisible(true);
				frmTelaInicial.setContentPane(painelMenuFuncionario);
				frmTelaInicial.revalidate();
				montarMenuFuncionario();
			}
		});
	}
	
	protected void registrarCliqueBotaoRegistrarDoPainelRegistrarServico() {
		if (painelRegistroServico == null) {
			painelRegistroServico = new PainelRegistroServico(usuarioAutenticado);
		}
		painelRegistroServico.getBtnCadastrar().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				painelMenuFuncionario = new PainelMenuFuncionario();
				painelMenuFuncionario.setVisible(true);
				frmTelaInicial.setContentPane(painelMenuFuncionario);
				frmTelaInicial.revalidate();
				montarMenuFuncionario();
			}
		});
	}

	protected void montarMenuGerente() {
		registrarCliqueBotaoCadastrarUsuarioTelaGerente();
		registrarCliqueBotaoConsultarServicosTelaGerente();
		registrarCliqueBotaoGerenciarUsuarioTelaGerente();
	}

	protected void montarMenuFuncionario() {
		registrarCliqueBotaoRegistrarServicoTelaFuncionario();
		registrarCliqueBotaoConsultarServicosTelaFuncionario();
	}

}
