package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import model.vo.Funcionario;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.Font;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PainelConsultaGerencia extends JPanel {
	private JTextField txtNome;
	private JTextField txtSala;
	private JTextField txtCargo;
	private JTextField txtOcorrencia;
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
	
	private ArrayList<Funcionario> funcionarios;
	private String[] nomesColunas = { "Nome", "Cargo", "Sala", "Data de Serviço", "Serviço Realizado", "Ocorrência" };
	private JButton btnRetroceder;
	
	private void limparTabelaConsulta() {
		tblConsultaGerencia.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaConsulta() {
		this.limparTabelaConsulta();

		DefaultTableModel model = (DefaultTableModel) tblConsultaGerencia.getModel();
		//TODO
		for (Funcionario f : funcionarios) {
			Object[] novaLinhaDaTabela = new Object[7];
			novaLinhaDaTabela[0] = f.getNome();
			novaLinhaDaTabela[1] = f.getTipoCargo();
			novaLinhaDaTabela[2] = f.getDataDesligamento();
//			novaLinhaDaTabela[3] = f.
//			novaLinhaDaTabela[4] = f.
//			novaLinhaDaTabela[5] = f.
//			novaLinhaDaTabela[6] = f.
//
//			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 */
	public PainelConsultaGerencia() {
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
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
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
				FormSpecs.MIN_COLSPEC,
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

		lblFuncionario = new JLabel("Filtrar Funcionário");
		lblFuncionario.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblFuncionario, "16, 6, 3, 1");

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "16, 10, left, default");

		txtNome = new JTextField();
		add(txtNome, "18, 10, fill, fill");
		txtNome.setColumns(10);

		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "28, 10, left, default");

		txtSala = new JTextField();
		add(txtSala, "30, 10, fill, fill");
		txtSala.setColumns(10);

		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCargo, "16, 14, left, default");

		txtCargo = new JTextField();
		add(txtCargo, "18, 14, fill, fill");
		txtCargo.setColumns(10);

		lblPeriodoServico = new JLabel("Período de Serviço");
		lblPeriodoServico.setFont(new Font("Tahoma", Font.BOLD, 14));
		add(lblPeriodoServico, "16, 18, 3, 1, left, default");

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);

		lblDataInicial = new JLabel("De:");
		lblDataInicial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataInicial, "16, 22, left, default");
		
		dataInicial = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		dataInicial.getDatePicker().getComponentToggleCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		add(dataInicial, "18, 22, fill, fill");

		lblDataFinal = new JLabel("Até:");
		lblDataFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataFinal, "28, 22");
		
		dataFinal = new DateTimePicker((DatePickerSettings) null, (TimePickerSettings) null);
		add(dataFinal, "30, 22, fill, fill");

		lblOcorrencia = new JLabel("Ocorrência:");
		lblOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblOcorrencia, "16, 26, left, default");

		txtOcorrencia = new JTextField();
		add(txtOcorrencia, "18, 26, fill, fill");
		txtOcorrencia.setColumns(10);
		
				btnFiltrar = new JButton("Filtrar");
				btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				add(btnFiltrar, "30, 30, default, fill");

		tblConsultaGerencia = new JTable();
		add(tblConsultaGerencia, "18, 34, 13, 1, fill, fill");
		
		btnRetroceder = new JButton("<");
		add(btnRetroceder, "20, 36");

		lblPagina = new JLabel("1/1");
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "22, 36, 5, 1");

		btnAvancar = new JButton(">");
		add(btnAvancar, "28, 36");

		btnExportar = new JButton("Exportar Excel");
		btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnExportar, "18, 42, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnVoltar, "30, 42, default, fill");

	}

}
