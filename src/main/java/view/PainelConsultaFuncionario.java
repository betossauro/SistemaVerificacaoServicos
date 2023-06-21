package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.PrestacaoController;
import controller.SalaController;
import model.dto.PrestacaoDTO;
import model.seletor.PrestacaoSeletor;
import model.vo.Funcionario;
import model.vo.Sala;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JSeparator;

public class PainelConsultaFuncionario extends JPanel {
	private JTable tblConsultaFuncionario;
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
	private PrestacaoSeletor seletor = new PrestacaoSeletor();

	private ArrayList<PrestacaoDTO> prestacoes;
	private String[] nomesColunas = { "Sala", "Data", "Serviço Realizado" };
	private JButton btnEditar;
	private JLabel lblPeriodoServico;
	private JLabel lblDataInicial;
	private DatePicker dataInicial;
	private JLabel lblDataFinal;
	private DatePicker dataFinal;
	private JLabel lblSala;
	private PrestacaoController controller;
	private JComboBox cbSala;
	private ArrayList<Sala> salas;
	private PrestacaoDTO prestacaoSelecionada;
	private JSeparator separator;

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	private void limparTabelaConsulta() {
		tblConsultaFuncionario.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaConsulta() {
		this.limparTabelaConsulta();
		DefaultTableModel model = (DefaultTableModel) tblConsultaFuncionario.getModel();

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
	 * 
	 * @param usuarioAutenticado
	 */
	public PainelConsultaFuncionario(Funcionario usuarioAutenticado) {
		setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(30dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;pref)"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(50dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(50dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(50dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.MIN_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblPeriodoServico = new JLabel("Período de Serviço");
		lblPeriodoServico.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblPeriodoServico, "16, 6, 5, 1");

		lblDataInicial = new JLabel("De:");
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataInicial, "16, 10");

		dataInicial = new DatePicker(dateSettings);
		dataInicial.getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataInicial.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(dataInicial, "20, 10, 5, 1, fill, fill");
		lblDataFinal = new JLabel("Até:");
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataFinal, "32, 10");

		dataFinal = new DatePicker();
		dataFinal.getComponentToggleCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 12));
		dataFinal.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(dataFinal, "34, 10, 3, 1, fill, fill");

		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "16, 14");

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPrestacoesComFiltros();
				atualizarTabelaConsulta();
			}
		});

		SalaController salaController = new SalaController();
		salas = (ArrayList<Sala>) salaController.consultarTodos();

		cbSala = new JComboBox(salas.toArray());
		cbSala.setSelectedIndex(-1);
		add(cbSala, "20, 14, 5, 1, fill, fill");

		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscarPrestacoesComFiltros();
				atualizarTabelaConsulta();
			}
		});
		add(btnFiltrar, "36, 18, default, fill");

		tblConsultaFuncionario = new JTable();
		tblConsultaFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		this.limparTabelaConsulta();

		separator = new JSeparator();
		add(separator, "15, 22, 22, 1");
		add(tblConsultaFuncionario, "20, 26, 17, 1, fill, fill");

		btnAvancar = new JButton(">");
		btnAvancar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				paginaAtual++;
				buscarPrestacoesComFiltros();
				lblPagina.setText(paginaAtual + " / " + totalPaginas);
				btnRetroceder.setEnabled(paginaAtual > 1);
				btnAvancar.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnAvancar, "30, 28");

		btnRetroceder = new JButton("<");
		btnRetroceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				paginaAtual--;
				buscarPrestacoesComFiltros();
				lblPagina.setText(paginaAtual + " / " + totalPaginas);
				btnRetroceder.setEnabled(paginaAtual > 1);
				btnAvancar.setEnabled(paginaAtual < totalPaginas);
			}
		});
		add(btnRetroceder, "26, 28");

		lblPagina = new JLabel("1 / " + totalPaginas);
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "28, 28");

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "36, 34, default, fill");

	}

	protected void buscarPrestacoesComFiltros() {
		seletor = new PrestacaoSeletor();
		seletor.setLimite(TAMANHO_PAGINA);
		seletor.setPagina(paginaAtual);

		seletor.setNumeroSala((String) cbSala.getSelectedItem());
		seletor.setDataInicio(dataInicial.getDate());
		seletor.setDataFim(dataFinal.getDate());

		prestacoes = (ArrayList<PrestacaoDTO>) controller.consultarComFiltros(seletor);
		atualizarTabelaConsulta();
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
}
