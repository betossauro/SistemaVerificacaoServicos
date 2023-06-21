package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
import controller.OcorrenciaController;
import controller.SalaController;
import model.vo.Atividade;
import model.vo.Funcionario;
import model.vo.Ocorrencia;
import model.vo.Prestacao;
import model.vo.Sala;

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
	private LocalDateTime horaInicio;
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

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	/**
	 * Create the panel.
	 */
	public PainelRegistroServico(Funcionario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
		setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"), FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.UNRELATED_GAP_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"), }));

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
		add(horaEntrada, "20, 14, default, fill");
		btnIniciarServico = new JButton("Horário atual");
		btnIniciarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIniciarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horaInicio = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
				lblHoraEntrada.setText(horaInicio.format(DateTimeFormatter.ofPattern("HH:mm")));
			}
		});
		add(btnIniciarServico, "22, 14, 3, 1, default, fill");

		lblHoraEntrada = new JLabel("");
		lblHoraEntrada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraEntrada, "26, 14, center, default");

		lblHoraFinal = new JLabel("Registrar Hora Final:");
		lblHoraFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraFinal, "16, 20");

		settings = new TimePickerSettings();
		settings.setAllowKeyboardEditing(false);

		horaSaida = new TimePicker(settings);
		add(horaSaida, "20, 20, default, fill");

		btnFinalizarServico = new JButton("Horário atual");
		btnFinalizarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horaFim = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
				lblHoraSaida.setText(horaInicio.format(DateTimeFormatter.ofPattern("HH:mm")));
			}
		});
		btnFinalizarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnFinalizarServico, "22, 20, 3, 1, default, fill");

		lblHoraSaida = new JLabel("");
		lblHoraSaida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraSaida, "26, 20, center, default");

		JSeparator separator = new JSeparator();
		add(separator, "16, 26, 11, 1");

		lblServicoRealizado = new JLabel("Serviços Realizados:");
		lblServicoRealizado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblServicoRealizado, "16, 30");

		AtividadeController atividadeController = new AtividadeController();
		atividades = (ArrayList<Atividade>) atividadeController.consultarTodos();

		cbServicoRealizado = new JComboBox(atividades.toArray());
		add(cbServicoRealizado, "20, 30, 3, 1, fill, fill");
		
		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Prestacao prestacao = new Prestacao();
				// TODO
//				prestacao.setIdFuncionario(usuarioAutenticado.getId());
				Sala salaSelecionada = (Sala) cbSala.getSelectedItem();
				prestacao.setIdSala(salaSelecionada.getId());

				prestacao.setDataInicio(horaInicio);
				prestacao.setDataFim(horaFim);

			}
		});

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "20, 77, default, fill");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCadastrar, "26, 77, default, fill");

		btnAdicionarAtividade = new JButton("+");
		btnAdicionarAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servicosRealizados.add((Atividade) cbServicoRealizado.getSelectedItem());
			}
			// TODO
		});
		add(btnAdicionarAtividade, "24, 30, fill, fill");
	}

	public LocalDateTime mostrarHora() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");
		dataHora = LocalDateTime.now();
		dataHora.format(formatador);
		return dataHora;
	}
}
