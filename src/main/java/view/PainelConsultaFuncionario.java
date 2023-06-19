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
import controller.SalaController;
import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
import model.vo.Funcionario;
import model.vo.Prestacao;
import model.vo.Sala;

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

public class PainelConsultaFuncionario extends JPanel {
	private JTable tblConsultaGerencia;
	private JButton btnVoltar;
	private JButton btnExportar;
	private JButton btnFiltrar;
	private JButton btnAvancar;
	private JButton btnRetroceder;
	private JLabel lblPagina;

	// FONTE: https://github.com/LGoodDatePicker/LGoodDatePicker
	private DatePickerSettings dateSettings;
	private JButton btnPegarData;

	// Atributos para a PAGINAÇÃO
	private final int TAMANHO_PAGINA = 5;
	private int paginaAtual = 1;
	private int totalPaginas = 0;

	private ArrayList<PrestacaoDTO> prestacoes;
	private String[] nomesColunas = { "Sala", "Data", "Serviço Realizado" };
	private JButton btnEditar;
	private JLabel lblPeriodoServico;
	private JLabel lblDataInicial;
	private DateTimePicker dataInicial;
	private JLabel lblDataFinal;
	private DateTimePicker dataFinal;
	private JLabel lblSala;
	private FuncionarioController controller;
	private JComboBox cbSala;
	private ArrayList<Sala> salas;

	private void limparTabelaConsulta() {
		tblConsultaGerencia.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaConsulta() {
		this.limparTabelaConsulta();
		DefaultTableModel model = (DefaultTableModel) tblConsultaGerencia.getModel();

		for (PrestacaoDTO p : prestacoes) {
			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = p.getNumeroSala();
			novaLinhaDaTabela[1] = p.getDataInicio() + p.getDataFim();
			novaLinhaDaTabela[2] = p.getServico();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 */
	public PainelConsultaFuncionario() {
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
				ColumnSpec.decode("max(150dlu;pref)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.MIN_COLSPEC,
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

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblPeriodoServico = new JLabel("Período de Serviço");
		lblPeriodoServico.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblPeriodoServico, "16, 6, 3, 1");

		lblDataInicial = new JLabel("De:");
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataInicial, "16, 10");

		dataInicial = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		dataInicial.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getTimePicker().getComponentToggleTimeMenuButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getTimePicker().getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getDatePicker().getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(dataInicial, "18, 10, 5, 1, fill, fill");
		lblDataFinal = new JLabel("Até:");
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataFinal, "30, 10");

		dataFinal = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		dataFinal.getTimePicker().getComponentTimeTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getTimePicker().getComponentToggleTimeMenuButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getDatePicker().getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getDatePicker().getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(dataFinal, "32, 10, 3, 1, fill, fill");

		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "16, 14");

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		SalaController salaController = new SalaController();
		salas = (ArrayList<Sala>) salaController.consultarTodos();

		cbSala = new JComboBox(salas.toArray());
		cbSala.setSelectedIndex(-1);
		add(cbSala, "18, 14, 5, 1, fill, fill");
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnFiltrar, "34, 18, default, fill");

		tblConsultaGerencia = new JTable();
		tblConsultaGerencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.limparTabelaConsulta();
		add(tblConsultaGerencia, "18, 24, 17, 1, fill, fill");

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
		add(btnRetroceder, "24, 26");

		lblPagina = new JLabel("1/1");
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "26, 26");

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
		add(btnAvancar, "28, 26");

		btnEditar = new JButton("Editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			// TODO
		});
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnEditar, "24, 32, 5, 1, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			// TODO
		});
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "34, 32, default, fill");

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
