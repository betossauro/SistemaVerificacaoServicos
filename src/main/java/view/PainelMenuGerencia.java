package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PainelMenuGerencia extends JPanel {

	private JLabel lblGerenciar;
	private JLabel lblConsultar;
	private JLabel lblCadastrar;
	private JLabel lblIconeGerenciar;
	private JLabel lblIconeConsultar;
	private JLabel lblIconeCadastrar;
	
	

	public JLabel getLblIconeGerenciar() {
		return lblIconeGerenciar;
	}

	public JLabel getLblIconeConsultar() {
		return lblIconeConsultar;
	}

	public JLabel getLblIconeCadastrar() {
		return lblIconeCadastrar;
	}



	/**
	 * Create the panel.
	 */
	public PainelMenuGerencia() {
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(225px;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(225px;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.UNRELATED_GAP_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(225px;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		lblIconeCadastrar = new JLabel("");
		lblIconeCadastrar.setIcon(new ImageIcon(PainelMenuGerencia.class.getResource("/view/icons/register.png")));
		lblIconeCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblIconeCadastrar, "10, 28");

		lblIconeConsultar = new JLabel("");
		lblIconeConsultar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lblIconeConsultar.setIcon(new ImageIcon(PainelMenuGerencia.class.getResource("/view/icons/consultar 1.png")));
		lblIconeConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblIconeConsultar, "32, 28");

		lblIconeGerenciar = new JLabel("");
		lblIconeGerenciar.setIcon(new ImageIcon(PainelMenuGerencia.class.getResource("/view/icons/gerenciar 1.png")));
		lblIconeGerenciar.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblIconeGerenciar, "54, 28");

		lblCadastrar = new JLabel("Cadastrar Usuário");
		lblCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCadastrar.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblCadastrar, "10, 30");

		lblConsultar = new JLabel("Consultar Serviços");
		lblConsultar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConsultar.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblConsultar, "32, 30");

		lblGerenciar = new JLabel("Gerenciar Usuários");
		lblGerenciar.setHorizontalAlignment(SwingConstants.CENTER);
		lblGerenciar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblGerenciar, "54, 30");

	}

}
