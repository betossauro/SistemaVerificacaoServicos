package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPrincipal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnCadastrar;
	private JMenuItem mntmCadastrarUsuario;
	private JMenu mnConsultar;
	private JMenuItem mntmConsultarServico;
	private JMenuItem mntmConsultarFuncionario;
	private JMenu mnGerenciar;
	private JMenuItem mntmGerenciarFuncionarios;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1200, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
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
		
		mntmGerenciarFuncionarios = new JMenuItem("Gerenciar Funcionários");
		mntmGerenciarFuncionarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		mnGerenciar.add(mntmGerenciarFuncionarios);
	}

}
