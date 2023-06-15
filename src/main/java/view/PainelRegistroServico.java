package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Font;

public class PainelRegistroServico extends JPanel {

	/**
	 * Create the panel.
	 */
	public PainelRegistroServico() {
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
						RowSpec.decode("max(25dlu;default)"), FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						RowSpec.decode("max(25dlu;default)"), }));

		JLabel lblRegistroServico = new JLabel("Registro de Serviço");
		lblRegistroServico.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblRegistroServico, "16, 6, 5, 1");

		JLabel lblSala = new JLabel("Sala:");
		lblSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSala, "16, 10");

		JLabel lblNoSala = new JLabel("Nº Sala");
		lblNoSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNoSala, "20, 10");

		JButton btnIniciarServico = new JButton("Iniciar Serviço");
		btnIniciarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnIniciarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});

		JComboBox cbSala = new JComboBox();
		cbSala.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cbSala, "22, 10, 3, 1, fill, fill");

		JLabel lblHoraInicio = new JLabel("Horário Inicial:");
		lblHoraInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraInicio, "16, 14");
		add(btnIniciarServico, "20, 14, 3, 1, default, fill");

		JLabel lblHoraEntrada = new JLabel("HH:mm");
		lblHoraEntrada.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraEntrada, "24, 14, center, default");

		JLabel lblHoraFinal = new JLabel("Horário Final:");
		lblHoraFinal.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraFinal, "16, 18");

		JButton btnFinalizarServico = new JButton("Finalizar Serviço");
		btnFinalizarServico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFinalizarServico.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(btnFinalizarServico, "20, 18, 3, 1, default, fill");

		JLabel lblHoraSaida = new JLabel("HH:mm");
		lblHoraSaida.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblHoraSaida, "24, 18, center, default");

		JSeparator separator = new JSeparator();
		add(separator, "16, 22, 13, 1");

		JLabel lblServicoRealizado = new JLabel("Serviços Realizados:");
		lblServicoRealizado.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblServicoRealizado, "16, 26");

		JComboBox cbServicoRealizado = new JComboBox();
		add(cbServicoRealizado, "20, 26, 5, 1, fill, fill");

		JSeparator separator_1 = new JSeparator();
		add(separator_1, "16, 40, 13, 1");

		JLabel lblOcorrencia = new JLabel("Ocorrências:");
		lblOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblOcorrencia, "16, 42");

		JComboBox cbOcorrencia = new JComboBox();
		cbOcorrencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cbOcorrencia, "20, 42, 5, 1, fill, fill");

		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCadastrar, "28, 68, default, fill");

	}

}
