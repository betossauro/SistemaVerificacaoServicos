package view;

import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.FuncionarioController;
import controller.PrestacaoController;
import model.exception.CampoInvalidoException;
import model.seletor.FuncionarioSeletor;
import model.vo.Funcionario;
import model.vo.TipoCargo;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class PainelConsultaGerenciamentoFuncionarios extends JPanel {
	private JTable tblConsultaGerencia;
	private JButton btnVoltar;

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	private JButton btnExportar;
	private JButton btnFiltrar;
	private JButton btnAvancar;
	private JButton btnRetroceder;
	private JLabel lblPagina;

	// Atributos para a PAGINAÇÃO
	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private FuncionarioSeletor seletor = new FuncionarioSeletor();

	private ArrayList<Funcionario> funcionarios;
	private String[] nomesColunas = { "Nome", "Cargo", "Data de desligamento" };
	private JButton btnEditar;

	public JButton getBtnEditar() {
		return btnEditar;
	}

	private JLabel lblFuncionario;
	private JLabel lblNome;
	private JLabel lblCargo;
	private JLabel lblStatus;
	private FuncionarioController controller;
	private JTextField txtNome;
	private JRadioButton rdbtnAtivos;
	private JRadioButton rdbtnInativos;
	private ButtonGroup grupoStatus;
	private JComboBox cbCargo;
	private Funcionario funcionarioSelecionado;
	private JSeparator separator;

	private void limparTabelaConsulta() {
		tblConsultaGerencia.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaFuncionarios() {
		this.limparTabelaConsulta();
		DefaultTableModel model = (DefaultTableModel) tblConsultaGerencia.getModel();

		for (Funcionario f : funcionarios) {
			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = f.getNome();
			novaLinhaDaTabela[1] = f.getTipoCargo().name();
			novaLinhaDaTabela[2] = f.getDataDesligamento();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 * @param usuarioAutenticado 
	 */
	public PainelConsultaGerenciamentoFuncionarios(Funcionario usuarioAutenticado) {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(75dlu;pref)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(75dlu;pref)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(30dlu;min)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		lblFuncionario = new JLabel("Funcionário");
		lblFuncionario.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblFuncionario, "16, 6, 7, 1");

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "16, 10");

		txtNome = new JTextField();
		add(txtNome, "20, 10, 8, 1, fill, fill");
		txtNome.setColumns(10);
		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCargo, "34, 10");

		cbCargo = new JComboBox(TipoCargo.values());
		cbCargo.setSelectedIndex(-1);
		add(cbCargo, "38, 10, fill, fill");

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblStatus, "16, 14");

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarFuncionariosComFiltros();
				atualizarTabelaFuncionarios();
			}
		});

		grupoStatus = new ButtonGroup();

		rdbtnAtivos = new JRadioButton("Ativos");
		add(rdbtnAtivos, "20, 14");

		rdbtnInativos = new JRadioButton("Inativos");
		add(rdbtnInativos, "22, 14");

		grupoStatus.add(rdbtnAtivos);
		grupoStatus.add(rdbtnInativos);

		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarFuncionariosComFiltros();
				atualizarTabelaFuncionarios();
			}
		});
		add(btnFiltrar, "38, 18, default, fill");

		tblConsultaGerencia = new JTable();
		tblConsultaGerencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.limparTabelaConsulta();
		tblConsultaGerencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int indiceSelecionado = tblConsultaGerencia.getSelectedRow();

				if (indiceSelecionado > 0) {
					btnEditar.setEnabled(true);
					funcionarioSelecionado = funcionarios.get(indiceSelecionado - 1);
				} else {
					btnEditar.setEnabled(false);
				}
			}
		});
		
		separator = new JSeparator();
		add(separator, "16, 22, 23, 1");
		add(tblConsultaGerencia, "20, 26, 19, 1, fill, fill");

		btnRetroceder = new JButton("<");
		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarFuncionariosComFiltros();
				lblPagina.setText(paginaAtual + " / " + totalPaginas);
				btnRetroceder.setEnabled(paginaAtual > 1);
				btnAvancar.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnRetroceder, "28, 28");

		lblPagina = new JLabel("1 / " + totalPaginas);
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "30, 28");

		btnAvancar = new JButton(">");
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarFuncionariosComFiltros();
				lblPagina.setText(paginaAtual + " / " + totalPaginas);
				btnRetroceder.setEnabled(paginaAtual > 1);
				btnAvancar.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnAvancar, "32, 28");

		btnExportar = new JButton("Exportar Excel");
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnExportar, "20, 34, 3, 1, default, fill");
		controller = new FuncionarioController();
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");
				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = controller.gerarPlanilha(funcionarios, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnEditar.setEnabled(false);
		add(btnEditar, "28, 34, 5, 1, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "38, 34, default, fill");
	}

	protected void buscarFuncionariosComFiltros() {
		seletor = new FuncionarioSeletor();
		seletor.setLimite(TAMANHO_PAGINA);
		seletor.setPagina(paginaAtual);
		seletor.setNome(txtNome.getText());
		seletor.setTipoCargo((TipoCargo) cbCargo.getSelectedItem());
		//TODO
		if (rdbtnAtivos.isSelected()) {
			seletor.setAtivo(true);
		} else if (rdbtnInativos.isSelected()) {
			seletor.setAtivo(false);
		}
		funcionarios = (ArrayList<Funcionario>) controller.consultarComFiltros(seletor);
		atualizarTabelaFuncionarios();
		atualizarQuantidadePaginas();
	}

	private void atualizarQuantidadePaginas() {
		// Cálculo do total de páginas (poderia ser feito no backend)
		int totalRegistros = controller.contarTotalRegistrosComFiltros(seletor);
		// QUOCIENTE da divisão inteira
		totalPaginas = totalRegistros / TAMANHO_PAGINA;
		// RESTO da divisão inteira
		if (totalRegistros % TAMANHO_PAGINA > 0) {
			totalPaginas++;
		}
		lblPagina.setText(paginaAtual + " / " + totalPaginas);
	}

	public Funcionario getFuncionarioSelecionado() {
		return funcionarioSelecionado;
	}
}


