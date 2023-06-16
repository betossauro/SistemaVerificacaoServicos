package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.FuncionarioController;
import model.exception.CampoInvalidoException;
import model.seletor.FuncionarioSeletor;
import model.vo.Funcionario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class PainelConsultaGerenciamentoFuncionarios extends JPanel {

	private JTextField txtNome;
	private JTextField txtCargo;
	private JTable tblConsultaGerencia;
	private JButton btnVoltar;
	private JButton btnExportar;
	private JButton btnFiltrar;
	private JLabel lblCargo;
	private JLabel lblNome;
	private JLabel lblFuncionario;
	private JButton btnAvancar;
	private JButton btnRetroceder;
	private JButton btnEditar;
	private JLabel lblPagina;

	// Atributos para a PAGINAÇÃO
	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;
	private FuncionarioSeletor seletor = new FuncionarioSeletor();

	// FONTE: https://github.com/LGoodDatePicker/LGoodDatePicker
	private DatePickerSettings dateSettings;
	private JButton btnPegarData;

	private ArrayList<Funcionario> funcionarios;
	private String[] nomesColunas = { "Nome", "Cargo", "Data de desligamento" };
	private JRadioButton rdbtnAtivos;
	private JRadioButton rdbtnInativos;
	private JLabel lblStatus;
	private FuncionarioController controller;

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
	 */
	public PainelConsultaGerenciamentoFuncionarios() {
		setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(50dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		lblFuncionario = new JLabel("Filtrar Funcionário");
		lblFuncionario.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblFuncionario, "16, 6, 5, 1");

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "16, 10, left, default");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtNome, "20, 10, 5, 1, fill, fill");
		txtNome.setColumns(10);

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCargo, "32, 10, left, default");

		txtCargo = new JTextField();
		txtCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtCargo, "34, 10, 3, 1, fill, fill");
		txtCargo.setColumns(10);

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblStatus, "16, 14");

		rdbtnAtivos = new JRadioButton("Ativos");
		rdbtnAtivos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAtivos.setHorizontalAlignment(SwingConstants.LEFT);
		add(rdbtnAtivos, "20, 14");

		rdbtnInativos = new JRadioButton("Inativos");
		rdbtnInativos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInativos.setHorizontalAlignment(SwingConstants.LEFT);
		add(rdbtnInativos, "20, 16");

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarFuncionariosComFiltros();
				atualizarTabelaFuncionarios();
			}
		});
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnFiltrar, "36, 22, default, fill");

		tblConsultaGerencia = new JTable();
		add(tblConsultaGerencia, "20, 28, 19, 1, fill, fill");

		lblPagina = new JLabel("1 / " + totalPaginas);
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina);

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
		add(btnAvancar, "30, 30");

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
		add(btnRetroceder, "26, 30");

		controller = new FuncionarioController();
		btnExportar = new JButton("Exportar Excel");
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
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnExportar, "20, 36, default, fill");

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnEditar, "26, 36, 5, 1, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "36, 36, default, fill");

	}

	protected void buscarFuncionariosComFiltros() {
		seletor = new FuncionarioSeletor();
		seletor.setLimite(TAMANHO_PAGINA);
		seletor.setPagina(paginaAtual);
		seletor.setNome(txtNome.getText());
		seletor.setTipoCargo(txtCargo.getText());
		// TODO Não sei como fazer com o filtro de data
		// Inativos são aquiles que possuemm data de desligamento, se a data de
		// desligamento é null então o cliente está ativo
		//
		// => seletor.setDataDesligamento();

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

		btnExportar = new JButton("Exportar Excel");
		// TODO 
//	btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 14));
//	add(btnExportar, "18, 32, default, fill");
//	controller = new FuncionarioController();
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
	}
}
