package view;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.FuncionarioController;
import model.exception.CampoInvalidoException;
import model.vo.Funcionario;
import model.vo.TipoCargo;
import model.vo.TipoUsuario;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import com.github.lgooddatepicker.components.DatePicker;

public class PainelCadastroUsuario extends JPanel {
	private JTextField txtNome;
	private JLabel lblCPF;
	private JFormattedTextField txtCPF;
	private JLabel lblTelefone;
	private JFormattedTextField txtTelefone;
	private JLabel lblDataNascimento;
	private JLabel lblCTPS;
	private JFormattedTextField txtCTPS;
	private JLabel lblNivelAcesso;
	private JRadioButton rdbtnGerencia;
	private JRadioButton rdbtnFuncionario;
	private JLabel lblCargo;
	private JComboBox cbCargo;
	private JLabel lblMatricula;
	private JLabel lblSenha;
	private JLabel lblAtuacao;
	private JCheckBox chkInativo;
	private JButton btnCadastrar;
	private JLabel lblNome;
	private JLabel lblCadastroUsuario;
	private MaskFormatter mascaraCpf;
	private MaskFormatter mascaraTelefone;
	private MaskFormatter mascaraCtps;
	private MaskFormatter mascaraMatricula;
	private MaskFormatter mascaraSenha;
	private Funcionario funcionario;
	private ButtonGroup grupoCargo;
	private DatePicker dataNascimento;
	private JButton btnVoltar;
	private JTextField txtMatricula;
	private JPasswordField txtSenha;

	public JButton getBtnVoltar() {
		return btnVoltar;
	}

	/**
	 * Create the panel.
	 */
	public PainelCadastroUsuario(Funcionario funcionarioParaEditar) {
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
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(150dlu;default)"),
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
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.UNRELATED_GAP_ROWSPEC,
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
				RowSpec.decode("max(25dlu;default)"),
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
				RowSpec.decode("max(25dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));

		lblCadastroUsuario = new JLabel("Cadastro de Usuário");
		lblCadastroUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		add(lblCadastroUsuario, "16, 6, 7, 1");

		lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNome, "16, 10");

		txtNome = new JTextField();
		txtNome.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtNome, "20, 10, 5, 1, fill, fill");
		txtNome.setColumns(10);

		lblCPF = new JLabel("CPF:");
		lblCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCPF, "16, 14");

		try {
			mascaraCpf = new MaskFormatter("###.###.###-##");
			mascaraCpf.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtCPF = new JFormattedTextField(mascaraCpf);
		txtCPF.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtCPF, "20, 14, 5, 1, fill, fill");

		lblTelefone = new JLabel("Telefone:");
		lblTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblTelefone, "16, 18");

		try {
			mascaraTelefone = new MaskFormatter("(##)#####-####");
			mascaraTelefone.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtTelefone = new JFormattedTextField(mascaraTelefone);
		txtTelefone.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtTelefone, "20, 18, 5, 1, fill, fill");

		lblDataNascimento = new JLabel("Data de Nascimento:");
		lblDataNascimento.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDataNascimento, "16, 22");

		dataNascimento = new DatePicker();
		add(dataNascimento, "20, 22, 5, 1, fill, fill");

		lblCTPS = new JLabel("CTPS:");
		lblCTPS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCTPS, "16, 26");

		try {
			mascaraCtps = new MaskFormatter("####### ####");
			mascaraCtps.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtCTPS = new JFormattedTextField(mascaraCtps);
		txtCTPS.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtCTPS, "20, 26, 5, 1, fill, fill");

		lblNivelAcesso = new JLabel("Nível de Acesso:");
		lblNivelAcesso.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblNivelAcesso, "16, 30");

		grupoCargo = new ButtonGroup();

		rdbtnGerencia = new JRadioButton("Gerência");
		rdbtnGerencia.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(rdbtnGerencia, "20, 30, default, fill");

		rdbtnFuncionario = new JRadioButton("Funcionário");
		rdbtnFuncionario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(rdbtnFuncionario, "22, 30, default, fill");

		grupoCargo.add(rdbtnGerencia);
		grupoCargo.add(rdbtnFuncionario);

		lblCargo = new JLabel("Cargo:");
		lblCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblCargo, "16, 34");

		cbCargo = new JComboBox(TipoCargo.values());
		cbCargo.setSelectedIndex(-1);
		cbCargo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(cbCargo, "20, 34, 5, 1, fill, fill");

		lblMatricula = new JLabel("Matrícula:");
		lblMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblMatricula, "16, 38");

		try {
			mascaraMatricula = new MaskFormatter("######");
			mascaraMatricula.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtMatricula = new JTextField(6);
		txtMatricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtMatricula, "20, 38, 5, 1, fill, fill");
		txtMatricula.setColumns(10);

		lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSenha, "16, 42");

		try {
			mascaraSenha = new MaskFormatter("########");
			mascaraSenha.setValueContainsLiteralCharacters(false);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtSenha = new JPasswordField(6);
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtSenha, "20, 42, 5, 1, fill, fill");
		txtSenha.setColumns(10);

		lblAtuacao = new JLabel("Atuação:");
		lblAtuacao.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblAtuacao, "16, 46");
		lblAtuacao.setVisible(false);
		
		chkInativo = new JCheckBox("Inativo");
		chkInativo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(chkInativo, "20, 46, default, fill");
		chkInativo.setVisible(false);

		btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				funcionario.setNome(txtNome.getText());
				try {
					String cpfSemMascara = (String) mascaraCpf.stringToValue(txtCPF.getText());
					funcionario.setCpf(cpfSemMascara);
				} catch (ParseException e1) {
				}
				try {
					String telefoneSemMascara = (String) mascaraTelefone.stringToValue(txtTelefone.getText());
					funcionario.setTelefone(telefoneSemMascara);
				} catch (ParseException e1) {
				}
				LocalDate dataSelecionada = dataNascimento.getDate();
				funcionario.setDataNascimento(dataSelecionada);
				try {
					String ctpsSemMascara = (String) mascaraCtps.stringToValue(txtCTPS.getText());
					funcionario.setCtps(ctpsSemMascara);
				} catch (ParseException e1) {
				}
				if (rdbtnGerencia.isSelected()) {
					funcionario.setTipoUsuario(TipoUsuario.ADMINISTRADOR);
				} else if (rdbtnFuncionario.isSelected()) {
					funcionario.setTipoUsuario(TipoUsuario.FUNCIONARIO);
				}
				funcionario.setTipoCargo((TipoCargo) cbCargo.getSelectedItem());
				if (chkInativo.isSelected()) {
					funcionario.setDataDesligamento(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));
				}
				funcionario.setMatricula(txtMatricula.getText());
				funcionario.setSenha(txtSenha.getText());

				FuncionarioController controller = new FuncionarioController();
				try {
					if (funcionario.getId() == null) {
						controller.inserir(funcionario);
						JOptionPane.showMessageDialog(null, "Funcionário cadastrado com sucesso!", 
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);
					} else {
						controller.atualizar(funcionario);
						JOptionPane.showMessageDialog(null, "Funcionário atualizado com sucesso!", 
								"Sucesso", JOptionPane.INFORMATION_MESSAGE);

					}
				} catch (CampoInvalidoException ex) {
					JOptionPane.showMessageDialog(null, "Preencha os seguintes campos: \n" + ex.getMessage(), "Erro",
							JOptionPane.WARNING_MESSAGE);
				}
				limparTelaCadastro();
			}
		});
		btnCadastrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCadastrar, "24, 68, default, fill");

		btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnVoltar, "20, 68, default, fill");

		if (funcionarioParaEditar != null) {
			lblAtuacao.setVisible(true);
			chkInativo.setVisible(true);
			
			this.funcionario = funcionarioParaEditar;
			txtNome.setText(funcionarioParaEditar.getNome());
			txtCPF.setText(funcionarioParaEditar.getCpf());
			txtTelefone.setText(funcionarioParaEditar.getTelefone());
			dataNascimento.setDate(funcionarioParaEditar.getDataNascimento());
			txtCTPS.setText(funcionarioParaEditar.getCtps());
			rdbtnGerencia.setSelected(funcionarioParaEditar.getTipoUsuario() == TipoUsuario.ADMINISTRADOR);
			rdbtnFuncionario.setSelected(funcionarioParaEditar.getTipoUsuario() == TipoUsuario.FUNCIONARIO);
			cbCargo.setSelectedItem(funcionarioParaEditar.getTipoCargo());
			chkInativo.setSelected(funcionarioParaEditar.getDataDesligamento() != null);
			txtMatricula.setText(funcionarioParaEditar.getMatricula());
			txtSenha.setText(funcionarioParaEditar.getSenha());
			
			txtCPF.setEditable(false);
			txtCTPS.setEditable(false);
			dataNascimento.setEnabled(false);
			txtMatricula.setEditable(false);
			lblCadastroUsuario.setText("Edição de Usuário");
			btnCadastrar.setText("Alterar Usuário");
		} else {
			this.funcionario = new Funcionario();
		}
	}
	
	private void limparTelaCadastro() {
		txtNome.setText(null);
		txtCPF.setText(null);
		txtTelefone.setText(null);
		dataNascimento.setDate(null);
		txtCTPS.setText(null);
		rdbtnGerencia.setSelected(false);
		rdbtnFuncionario.setSelected(false);
		cbCargo.setSelectedItem(null);
		chkInativo.setSelected(false);
		txtMatricula.setText(null);
		txtSenha.setText(null);
	}

}
