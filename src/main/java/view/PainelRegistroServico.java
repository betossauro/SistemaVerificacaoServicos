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
	private JComboBox cbOcorrencia;
	private JLabel lblOcorrencia;
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
	private ArrayList<Ocorrencia> ocorrencias;
	private List<Atividade> servicosRealizados = new ArrayList<Atividade>();
	private List<Ocorrencia> ocorrenciasSelecionadas = new ArrayList<Ocorrencia>();
	private JButton btnAdicionarAtividade;
	private JButton btnAdicionarOcorrencia;
	private JButton btnRegistrarOcorrencia;
	private JLabel lblNewLabel;
	protected Funcionario usuarioAutenticado;

	/**
	 * Create the panel.
	 */
	public PainelRegistroServico(Funcionario usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
		setLayout(new FormLayout(new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC, FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(74dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(65dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("max(107dlu;default):grow"),
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
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, RowSpec.decode("max(25dlu;default)"),
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), }));

		lblRegistroServico = new JLabel("Registro de Serviço");
		lblRegistroServico.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblRegistroServico, "16, 6, 5, 1");

		lblNewLabel = new JLabel("Bem vindo, ");
		add(lblNewLabel, "22, 6");

		lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "16, 10");

		SalaController salaController = new SalaController();
		salas = (ArrayList<Sala>) salaController.consultarTodos();

		cbSala = new JComboBox(salas.toArray());
		cbSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cbSala, "20, 10, 7, 1, fill, fill");

		lblHoraInicio = new JLabel("Horário Inicial:");
		lblHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraInicio, "16, 14");
		add(btnIniciarServico, "20, 14, 3, 1, default, fill");

		lblHoraEntrada = new JLabel("HH:mm");
		lblHoraEntrada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraEntrada, "24, 14, center, default");

		btnIniciarServico = new JButton("Iniciar Serviço");
		btnIniciarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIniciarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horaInicio = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
				lblHoraEntrada.setText(horaInicio.format(DateTimeFormatter.ofPattern("HH:mm")));
			}
		});

		lblHoraFinal = new JLabel("Horário Final:");
		lblHoraFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraFinal, "16, 18");

		lblHoraSaida = new JLabel("HH:mm");
		lblHoraSaida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraSaida, "24, 18, center, default");

		btnFinalizarServico = new JButton("Finalizar Serviço");
		btnFinalizarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				horaFim = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());
				lblHoraSaida.setText(horaInicio.format(DateTimeFormatter.ofPattern("HH:mm")));
			}
		});
		btnFinalizarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnFinalizarServico, "20, 18, 3, 1, default, fill");

		JSeparator separator = new JSeparator();
		add(separator, "16, 22, 13, 1");

		lblServicoRealizado = new JLabel("Serviços Realizados:");
		lblServicoRealizado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblServicoRealizado, "16, 26");

		AtividadeController atividadeController = new AtividadeController();
		atividades = (ArrayList<Atividade>) atividadeController.consultarTodos();

		cbServicoRealizado = new JComboBox(atividades.toArray());
		add(cbServicoRealizado, "20, 26, 5, 1, fill, fill");

		JSeparator separator_1 = new JSeparator();
		add(separator_1, "16, 44, 13, 1");

		lblOcorrencia = new JLabel("Ocorrências:");
		lblOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblOcorrencia, "16, 48");
		// Se uma ocorrência for selecionada, o botão de registrar serviço ficará
		// indisponível até que a ocorrência seja registrada clicando no botão
		// "Registrar Ocorrência" (relacionado ao PainelRegistroServico)

		OcorrenciaController ocorrenciaController = new OcorrenciaController();
		ocorrencias = (ArrayList<Ocorrencia>) ocorrenciaController.consultarTodos();

		cbOcorrencia = new JComboBox(ocorrencias.toArray());
		cbOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cbOcorrencia, "20, 48, 5, 1, fill, fill");

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

		btnRegistrarOcorrencia = new JButton("Registrar Ocorrência");
		btnRegistrarOcorrencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
			// TODO
		});
		btnRegistrarOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnRegistrarOcorrencia, "28, 60, default, fill");
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCadastrar, "28, 66, default, fill");

		btnAdicionarAtividade = new JButton("+");
		btnAdicionarAtividade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				servicosRealizados.add((Atividade) cbServicoRealizado.getSelectedItem());
			}
			// TODO
		});
		add(btnAdicionarAtividade, "26, 26, fill, fill");

		btnAdicionarOcorrencia = new JButton("+");
		btnAdicionarOcorrencia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ocorrenciasSelecionadas.add((Ocorrencia) cbOcorrencia.getSelectedItem());
			}
		});
		add(btnAdicionarOcorrencia, "26, 48, fill, fill");
	}
}
