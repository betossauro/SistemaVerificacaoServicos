package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.OcorrenciaController;
import controller.PrestacaoController;
import controller.SalaController;
import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
import model.vo.Funcionario;
import model.vo.Ocorrencia;
import model.vo.Sala;
import model.vo.TipoCargo;

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

import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class PainelConsultaGerencia extends JPanel {
	private JTextField txtNome;
	private JTable tblConsultaGerencia;
	private JButton btnVoltar;
	private JButton btnExportar;
	private JButton btnFiltrar;
	private JLabel lblOcorrencia;
	private JLabel lblDataFinal;
	private JLabel lblDataInicial;
	private JLabel lblPeriodoServico;
	private JLabel lblCargo;
	private JLabel lblSala;
	private JLabel lblNome;
	private JLabel lblFuncionario;
	private JButton btnAvancar;
	private JLabel lblPagina;

	// FONTE: https://github.com/LGoodDatePicker/LGoodDatePicker
	private DatePickerSettings dateSettings;
	private JButton btnPegarData;
	private DateTimePicker dataInicial;
	private DateTimePicker dataFinal;

	// Atributos para a PAGINAÇÃO
	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;

	private ArrayList<PrestacaoDTO> prestacoes;
	private String[] nomesColunas = { "Nome", "Cargo", "Sala", "Data de Serviço", "Serviço Realizado", "Ocorrência" };
	private JButton btnRetroceder;
	private PrestacaoController controller;
	private JComboBox cbCargo;
	private JComboBox cbSala;
	private ArrayList<Sala> salas;
	private JComboBox cbOcorrencias;
	private ArrayList<Ocorrencia> ocorrencias;

	private void limparTabelaConsulta() {
		tblConsultaGerencia.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaConsulta() {
		this.limparTabelaConsulta();

		DefaultTableModel model = (DefaultTableModel) tblConsultaGerencia.getModel();
		for (PrestacaoDTO p : prestacoes) {
			Object[] novaLinhaDaTabela = new Object[7];

			novaLinhaDaTabela[0] = p.getNomeFuncionario();
			novaLinhaDaTabela[1] = p.getNomeCargo();
			novaLinhaDaTabela[2] = p.getNumeroSala();
			novaLinhaDaTabela[3] = p.getDataInicio() + p.getDataFim();
			novaLinhaDaTabela[4] = p.getServico();
			novaLinhaDaTabela[5] = p.getOcorrencia();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 */
	public PainelConsultaGerencia() {
		setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(50dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.MIN_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default):grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
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
		add(txtNome, "20, 10, 3, 1, fill, fill");
		txtNome.setColumns(10);

		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "32, 10, left, default");

		SalaController salaController = new SalaController();
		salas = (ArrayList<Sala>) salaController.consultarTodos();

		cbSala = new JComboBox(salas.toArray());
		cbSala.setSelectedIndex(-1);
		add(cbSala, "34, 10, 3, 1, fill, fill");

		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCargo, "16, 14, left, default");

		cbCargo = new JComboBox(TipoCargo.values());
		cbCargo.setSelectedIndex(-1);
		add(cbCargo, "20, 14, 3, 1, fill, fill");

		lblPeriodoServico = new JLabel("Período de Serviço");
		lblPeriodoServico.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPeriodoServico, "16, 18, 5, 1, left, default");

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblDataInicial = new JLabel("De:");
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataInicial, "16, 22, left, default");

		dataInicial = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		dataInicial.getTimePicker().getComponentToggleTimeMenuButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getTimePicker().getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getDatePicker().getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getDatePicker().getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			// TODO É PARA EXISTIR ESSE ACTION LISTENER MSM?
		});
		add(dataInicial, "20, 22, 3, 1, fill, fill");

		lblDataFinal = new JLabel("Até:");
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataFinal, "32, 22");

		dataFinal = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		dataFinal.getTimePicker().getComponentToggleTimeMenuButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getTimePicker().getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getDatePicker().getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getDatePicker().getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			// TODO É PARA EXISTIR ESSE ACTION LISTENER MSM?
		});
		add(dataFinal, "34, 22, 3, 1, fill, fill");

		lblOcorrencia = new JLabel("Ocorrência:");
		lblOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblOcorrencia, "16, 26, left, default");

		OcorrenciaController ocorrenciaController = new OcorrenciaController();
		ocorrencias = (ArrayList<Ocorrencia>) ocorrenciaController.consultarTodos();

		cbOcorrencias = new JComboBox(ocorrencias.toArray());
		add(cbOcorrencias, "20, 26, 3, 1, fill, fill");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnFiltrar, "36, 30, default, fill");

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO
			}
		});

		tblConsultaGerencia = new JTable();
		tblConsultaGerencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.limparTabelaConsulta();
		add(tblConsultaGerencia, "20, 34, 17, 1, fill, fill");

		btnRetroceder = new JButton("<");
		// TODO
//		btnRetroceder.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				paginaAtual--;
//				buscarFuncionariosComFiltros();
//				lblPagina.setText(paginaAtual + " / " + totalPaginas);
//				btnRetroceder.setEnabled(paginaAtual > 1);
//				btnAvancar.setEnabled(paginaAtual < totalPaginas);
//			}
//		});
		btnRetroceder.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnRetroceder, "26, 36");

		lblPagina = new JLabel("1/1");
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "28, 36");

		btnAvancar = new JButton(">");
		// TODO
//		btnAvancar.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				paginaAtual++;
//				buscarFuncionariosComFiltros();
//				lblPagina.setText(paginaAtual + " / " + totalPaginas);
//				btnRetroceder.setEnabled(paginaAtual > 1);
//				btnAvancar.setEnabled(paginaAtual < totalPaginas);
//			}
//		});
		btnAvancar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnAvancar, "30, 36");

		btnExportar = new JButton("Exportar Excel");
		controller = new PrestacaoController();
		btnExportar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser janelaSelecaoDestinoArquivo = new JFileChooser();
				janelaSelecaoDestinoArquivo.setDialogTitle("Selecione um destino para a planilha...");
				int opcaoSelecionada = janelaSelecaoDestinoArquivo.showSaveDialog(null);
				if (opcaoSelecionada == JFileChooser.APPROVE_OPTION) {
					String caminhoEscolhido = janelaSelecaoDestinoArquivo.getSelectedFile().getAbsolutePath();
					String resultado;
					try {
						resultado = controller.gerarPlanilha(prestacoes, caminhoEscolhido);
						JOptionPane.showMessageDialog(null, resultado);
					} catch (CampoInvalidoException e1) {
						JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnExportar, "20, 42, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			// TODO
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "36, 42, default, fill");

	}

	// TODO
//	private void atualizarQuantidadePaginas() {
//		// Cálculo do total de páginas (poderia ser feito no backend)
//		int totalRegistros = controller.contarTotalRegistrosComFiltros(seletor);
//		// QUOCIENTE da divisão inteira
//		totalPaginas = totalRegistros / TAMANHO_PAGINA;
//		// RESTO da divisão inteira
//		if (totalRegistros % TAMANHO_PAGINA > 0) {
//			totalPaginas++;
//		}
//		lblPagina.setText(paginaAtual + " / " + totalPaginas);
//	}

}
