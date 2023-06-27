package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import controller.AtividadeController;
import controller.PrestacaoController;
import controller.SalaController;
import model.exception.CampoInvalidoException;
import model.vo.Atividade;
import model.vo.Funcionario;
import model.vo.Prestacao;
import model.vo.Sala;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PainelRegistroServico extends JPanel {

	private JButton btnCadastrar;
	private JComboBox cbServicoRealizado;
	private JLabel lblServicoRealizado;
	private JLabel lblHoraSaida;
	private JButton btnFinalizarServico;
	private JLabel lblHoraFinal;
	private JLabel lblRegistroServico;
	private JLabel lblSala;
	private JButton btnIniciarServico;
	private JComboBox cbSala;
	private JLabel lblHoraInicio;
	private JLabel lblHoraEntrada;
	private ArrayList<Sala> salas;
	private LocalDateTime horaFim;
	private ArrayList<Atividade> atividades;
	private List<Atividade> servicosRealizados = new ArrayList<Atividade>();
	private JButton btnAdicionarAtividade;
	protected Funcionario usuarioAutenticado;
	private LocalDateTime dataHora;
	private TimePicker horaEntrada;
	private TimePicker horaSaida;
	private TimePickerSettings settings;
	private JButton btnVoltar;
	private JTable tblServicos;
	private String[] nomesColunas = { "Serviço" };
	private JButton btnRemoverAtividade;
	private JSeparator separator;

	public JButton getBtnVoltar() {
		return btnVoltar;
	}
	
	public JButton getBtnCadastrar() {
		return btnCadastrar;
	}
	
	// Métodos usados no JTable
	private void limparTabela() {
		tblServicos.setModel(new DefaultTableModel(new Object[][] { nomesColunas, }, nomesColunas));
	}

	private void atualizarTabelaServicos() {
		this.limparTabela();

		DefaultTableModel model = (DefaultTableModel) tblServicos.getModel();
		// Preenche os valores na tabela linha a linha
		for (Atividade a : servicosRealizados) {
			Object[] novaLinhaDaTabela = new Object[2];

			novaLinhaDaTabela[0] = a.getDescricao();

			model.addRow(novaLinhaDaTabela);
		}
	}

	/**
	 * Create the panel.
	 */
	public PainelRegistroServico(final Funcionario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
		setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(100dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), }));

		lblRegistroServico = new JLabel("Registro de Serviço");
		lblRegistroServico.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblRegistroServico, "16, 6, 5, 1");

		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "16, 10");

		SalaController salaController = new SalaController();
		salas = (ArrayList<Sala>) salaController.consultarTodos();

		cbSala = new JComboBox(salas.toArray());
		cbSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cbSala, "20, 10, 5, 1, fill, fill");

		lblHoraInicio = new JLabel("Registrar Hora Inicial:");
		lblHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraInicio, "16, 14");

		horaEntrada = new TimePicker();
		add(horaEntrada, "20, 14, 5, 1, default, fill");

		btnIniciarServico = new JButton("Horário atual");
		btnIniciarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIniciarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (horaEntrada.getTime() == null) {
					horaEntrada.setTimeToNow();
					exibirComponentesHoraSaida();
				} else {
					horaEntrada.getTime();
					btnIniciarServico.setText("Horário Selecionado");
					exibirComponentesHoraSaida();
				}
			}
		});

		add(btnIniciarServico, "28, 14, default, fill");

		lblHoraFinal = new JLabel("Registrar Hora Final:");
		lblHoraFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHoraFinal.setVisible(false);
		add(lblHoraFinal, "16, 20");

		settings = new TimePickerSettings();
		settings.setAllowKeyboardEditing(false);

		horaSaida = new TimePicker(settings);
		horaSaida.setVisible(false);
		add(horaSaida, "20, 20, 5, 1, default, fill");
		
		if (horaSaida.getTime() == null) {
			
		} else {
			
		}

		btnFinalizarServico = new JButton("Horário atual");
		btnFinalizarServico.setVisible(false);
		btnFinalizarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (horaSaida.getTime() == null) {
					horaSaida.setTimeToNow();
					exibirComponentesServicos();
				} else {
					horaSaida.getTime();
					btnFinalizarServico.setText("Horário Selecionado");
					exibirComponentesServicos();
				}
			}
		});
		btnFinalizarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnFinalizarServico, "28, 20, default, fill");

		separator = new JSeparator();
		separator.setVisible(false);
		add(separator, "16, 26, 13, 1");

		lblServicoRealizado = new JLabel("Serviços Realizados:");
		lblServicoRealizado.setVisible(false);
		lblServicoRealizado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblServicoRealizado, "16, 30");

		AtividadeController atividadeController = new AtividadeController();
		atividades = (ArrayList<Atividade>) atividadeController.consultarPorIdCargo(usuarioAutenticado.getTipoCargo());

		cbServicoRealizado = new JComboBox(atividades.toArray());
		cbServicoRealizado.setVisible(false);
		cbServicoRealizado.setSelectedIndex(-1);
		add(cbServicoRealizado, "20, 30, 3, 1, fill, fill");

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.setEnabled(false);

		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prestacao prestacao = new Prestacao();
				prestacao.setIdFuncionario(usuarioAutenticado.getId());
				Sala salaSelecionada = (Sala) cbSala.getSelectedItem();
				prestacao.setIdSala(salaSelecionada.getId());
				LocalDate dataAtual = LocalDate.now();
				LocalTime horaInicio = horaEntrada.getTime();
				LocalDateTime dataHoraInicio = LocalDateTime.of(dataAtual, horaInicio);
				prestacao.setDataInicio(dataHoraInicio);

				LocalTime horaFinal = horaSaida.getTime();
				LocalDateTime dataHoraFinal = LocalDateTime.of(dataAtual, horaFinal);
				prestacao.setDataFim(dataHoraFinal);
				prestacao.setListaAtividades(servicosRealizados);

				PrestacaoController controller = new PrestacaoController();
				try {
					controller.inserir(prestacao);
					JOptionPane.showMessageDialog(null, "Serviço registrado com sucesso!", "Registro com sucesso",
							JOptionPane.INFORMATION_MESSAGE);
				} catch (CampoInvalidoException ex) {
					JOptionPane.showMessageDialog(null, "Preencha os seguintes campos: \n" + ex.getMessage(), "Erro",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		btnAdicionarAtividade = new JButton("+");
		btnAdicionarAtividade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdicionarAtividade.setVisible(false);
		btnAdicionarAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cbServicoRealizado.getSelectedIndex() != -1) {
					servicosRealizados.add((Atividade) cbServicoRealizado.getSelectedItem());
					atualizarTabelaServicos();
					btnCadastrar.setEnabled(true);
				} else {
					JOptionPane.showMessageDialog(null, "É necessário selecionar no mínimo uma atividade", "Atenção",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		add(btnAdicionarAtividade, "24, 30, fill, fill");

		tblServicos = new JTable();
		tblServicos.setVisible(false);
		tblServicos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(tblServicos, "28, 30, 1, 27, fill, fill");
		this.limparTabela();

		btnRemoverAtividade = new JButton("-");
		btnRemoverAtividade.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnRemoverAtividade.setVisible(false);
		btnRemoverAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servicosRealizados.remove((Atividade) cbServicoRealizado.getSelectedItem());
				atualizarTabelaServicos();
				if (servicosRealizados.size() < 1) {
					btnCadastrar.setEnabled(false);
				}
			}
		});
		add(btnRemoverAtividade, "24, 33, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "20, 79, default, fill");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCadastrar, "28, 79, default, fill");
	}

	public LocalDateTime mostrarHora() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
		dataHora = LocalDateTime.now();
		dataHora.format(formatador);
		return dataHora;
	}

	protected void exibirComponentesHoraSaida() {
		lblHoraFinal.setVisible(true);
		horaSaida.setVisible(true);
		btnFinalizarServico.setVisible(true);
	}

	protected void exibirComponentesServicos() {
		separator.setVisible(true);
		lblServicoRealizado.setVisible(true);
		cbServicoRealizado.setVisible(true);
		tblServicos.setVisible(true);
		btnAdicionarAtividade.setVisible(true);
		btnRemoverAtividade.setVisible(true);
	}
}
