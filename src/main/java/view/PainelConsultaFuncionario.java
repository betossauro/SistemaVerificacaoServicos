package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.FuncionarioController;
import controller.PrestacaoServicoController;
import model.dto.FuncionarioDTO;
import model.dto.PrestacaoDTO;
import model.exception.CampoInvalidoException;
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

import com.github.lgooddatepicker.components.TimePickerSettings;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;

public class PainelConsultaFuncionario extends JPanel {

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

	// FONTE: https://github.com/LGoodDatePicker/LGoodDatePicker
	private DatePickerSettings dateSettings;
	private JButton btnPegarData;

	private ArrayList<FuncionarioDTO> funcionarios;
	private String[] nomesColunas = { "Nome", "Cargo", "Data de desligamento" };
	private JRadioButton rdbtnAtivos;
	private JRadioButton rdbtnInativos;
	private JLabel lblStatus;
	private FuncionarioController controller;

	private void limparTabelaConsulta() {
		tblConsultaGerencia.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaConsulta() {
		this.limparTabelaConsulta();

		// TODO
		DefaultTableModel model = (DefaultTableModel) tblConsultaGerencia.getModel();

		for (FuncionarioDTO f : funcionarios) {
			Object[] novaLinhaDaTabela = new Object[3];
			novaLinhaDaTabela[0] = f.getNome();
			novaLinhaDaTabela[1] = f.getCargo();
			novaLinhaDaTabela[2] = f.getDataDesligamento();

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
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(125dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(24dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(125dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(125dlu;default)"),
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
		lblFuncionario.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblFuncionario, "16, 6, 7, 1");

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "16, 10, left, default");

		txtNome = new JTextField();
		add(txtNome, "20, 10, 7, 1, fill, fill");
		txtNome.setColumns(10);

		// Configurações da parte de DATAS do componente
		dateSettings = new DatePickerSettings();
		dateSettings.setAllowKeyboardEditing(false);
				
						lblCargo = new JLabel("Cargo:");
						lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
						add(lblCargo, "30, 10, left, default");
		
				txtCargo = new JTextField();
				add(txtCargo, "32, 10, 5, 1, fill, fill");
				txtCargo.setColumns(10);

		lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblStatus, "16, 14");

		rdbtnAtivos = new JRadioButton("Ativos");
		rdbtnAtivos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnAtivos.setHorizontalAlignment(SwingConstants.LEFT);
		add(rdbtnAtivos, "20, 14, 3, 1");

		rdbtnInativos = new JRadioButton("Inativos");
		rdbtnInativos.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnInativos.setHorizontalAlignment(SwingConstants.LEFT);
		add(rdbtnInativos, "20, 16, 3, 1");
		
				btnFiltrar = new JButton("Filtrar");
				btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				add(btnFiltrar, "36, 22, default, fill");

		tblConsultaGerencia = new JTable();
		add(tblConsultaGerencia, "20, 28, 17, 1, fill, fill");
		
				btnRetroceder = new JButton("<");
				add(btnRetroceder, "26, 30");

		lblPagina = new JLabel("1/1");
		lblPagina.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPagina.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblPagina, "28, 30");
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
								resultado = controller.gerarPlanilhaFuncionarios(funcionarios, caminhoEscolhido);
								JOptionPane.showMessageDialog(null, resultado);
							} catch (CampoInvalidoException e1) {
								JOptionPane.showConfirmDialog(null, e1.getMessage(), "Atenção", JOptionPane.WARNING_MESSAGE);
							}
						}
					}
				});
				
						btnAvancar = new JButton(">");
						add(btnAvancar, "30, 30");
				btnExportar.setFont(new Font("Tahoma", Font.PLAIN, 12));
				add(btnExportar, "20, 36, default, fill");

		btnEditar = new JButton("Editar");
		btnEditar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnEditar, "28, 36, default, fill");
		
				btnVoltar = new JButton("Voltar");
				btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
				add(btnVoltar, "36, 36, default, fill");

	}

}
