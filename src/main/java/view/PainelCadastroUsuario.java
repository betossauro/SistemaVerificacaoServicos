package view;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

public class PainelCadastroUsuario extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PainelCadastroUsuario() {
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblCadastroUsuario = new JLabel("Cadastro de Usuário");
		add(lblCadastroUsuario, "16, 6");
		
		JLabel lblNome = new JLabel("Nome:");
		add(lblNome, "16, 10");
		
		textField = new JTextField();
		add(textField, "18, 10, 3, 1, fill, default");
		textField.setColumns(10);
		
		textField = new JTextField();
		add(textField, "18, 10, fill, default");
		textField.setColumns(10);
		
		JLabel lblCPF = new JLabel("CPF:");
		add(lblCPF, "16, 14");
		
		JFormattedTextField formattedTextField_4 = new JFormattedTextField();
		add(formattedTextField_4, "18, 14, 3, 1, fill, default");
		
		JLabel lblTelefone = new JLabel("Telefone:");
		add(lblTelefone, "16, 18");
		
		JFormattedTextField formattedTextField_3 = new JFormattedTextField();
		add(formattedTextField_3, "18, 18, 3, 1, fill, default");
		
		JLabel lblDataNascimento = new JLabel("Data de Nascimento:");
		add(lblDataNascimento, "16, 22");
		
		JLabel lblCTPS = new JLabel("CTPS:");
		add(lblCTPS, "16, 26");
		
		JFormattedTextField formattedTextField_2 = new JFormattedTextField();
		add(formattedTextField_2, "18, 26, 3, 1, fill, default");
		
		JLabel lblNivelAcesso = new JLabel("Nível de Acesso:");
		add(lblNivelAcesso, "16, 30");
		
		JRadioButton rdbtnGerencia = new JRadioButton("Gerência");
		add(rdbtnGerencia, "18, 30");
		
		JRadioButton rdbtnFuncionario = new JRadioButton("Funcionário");
		add(rdbtnFuncionario, "20, 30");
		
		JLabel lblCargo = new JLabel("Cargo:");
		add(lblCargo, "16, 34");
		
		JComboBox comboBox = new JComboBox();
		add(comboBox, "18, 34, 3, 1, fill, default");
		
		JLabel lblMatricula = new JLabel("Matrícula:");
		add(lblMatricula, "16, 38");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		add(formattedTextField_1, "18, 38, 3, 1, fill, default");
		
		JLabel lblSenha = new JLabel("Senha:");
		add(lblSenha, "16, 42");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		add(formattedTextField, "18, 42, 3, 1, fill, default");
		
		JLabel lblAtuacao = new JLabel("Atuação:");
		add(lblAtuacao, "16, 46");
		
		JCheckBox chkInativo = new JCheckBox("Inativo");
		add(chkInativo, "18, 46, 3, 1");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		add(btnCadastrar, "20, 54");

	}

}
