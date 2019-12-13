package tela;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import models.Aluno;
import controller.AlunoController;
import java.awt.Color;

public class Tela1 {

	private JFrame frmCadastroDeAluno;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtEmail;
	private JTable jTable;
	private JTextField txtIdRem;
	private JTextField txtIdPesq;
	private JTextField txtNomePesq1;
	private JTextField txtIdAtu;
	private JTextField txtNomePesq;
	private JTextField txtCpfPesq;
	private JTextField txtEmailPesq;
	private EntityManagerFactory emf;
	private EntityManager em;
	DefaultTableModel modelo = new DefaultTableModel();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela1 window = new Tela1();
					window.frmCadastroDeAluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela1() {
		AlunoController ac = new AlunoController();
		initialize();
		carregarTabela();
	}

	private void initialize() {
		frmCadastroDeAluno = new JFrame();
		frmCadastroDeAluno.setTitle("Cadastro de Aluno");
		frmCadastroDeAluno.setResizable(false);
		frmCadastroDeAluno.setBounds(100, 100, 1013, 431);
		frmCadastroDeAluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeAluno.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 977, 380);
		frmCadastroDeAluno.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(23, 38, 46, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(23, 61, 248, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBounds(23, 92, 46, 14);
		panel.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(23, 117, 248, 20);
		panel.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(23, 148, 46, 14);
		panel.add(lblEmail);

		JLabel lblListaDeLunos = new JLabel("Lista de Alunos");
		lblListaDeLunos.setForeground(Color.GRAY);
		lblListaDeLunos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeLunos.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblListaDeLunos.setBounds(528, 18, 439, 23);
		panel.add(lblListaDeLunos);

		JLabel label_1 = new JLabel("Pesquisas");
		label_1.setForeground(Color.GRAY);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(331, 61, 141, 23);
		panel.add(label_1);

		JLabel label_2 = new JLabel("ID");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(331, 95, 35, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Nome");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(387, 95, 85, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("ID");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(34, 258, 46, 14);
		panel.add(label_4);

		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome_1.setBounds(331, 185, 46, 14);
		panel.add(lblNome_1);

		JLabel lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf_1.setBounds(331, 232, 46, 14);
		panel.add(lblCpf_1);

		JLabel lblEmail_1 = new JLabel("E-mail");
		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail_1.setBounds(331, 283, 46, 14);
		panel.add(lblEmail_1);

		txtEmail = new JTextField();
		txtEmail.setBounds(23, 173, 248, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		JButton btnGravar = new JButton("Gravar");
		btnGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AlunoController ac = new AlunoController();
				Aluno al = new Aluno();

				if (txtIdAtu.getText().equals("")) {
					al.setNome(txtNome.getText());
					al.setCpf(txtCpf.getText());
					al.setEmail(txtEmail.getText());
				} else {
					al.setId(Integer.parseInt(txtIdAtu.getText()));
					al.setNome(txtNome.getText());
					al.setCpf(txtCpf.getText());
					al.setEmail(txtEmail.getText());
				}

				ac.salvar(al);

				while (jTable.getModel().getRowCount() > 0) {
					((DefaultTableModel) jTable.getModel()).removeRow(0);
				}

				carregarTabela();

				txtIdAtu.setText("");
				txtNome.setText("");
				txtCpf.setText("");
				txtEmail.setText("");

				JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
			}
		});
		btnGravar.setBounds(23, 204, 75, 23);
		panel.add(btnGravar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AlunoController ac = new AlunoController();
				Aluno al = new Aluno();

				al = ac.buscaPorId(Integer.parseInt(txtIdAtu.getText()));

				txtIdAtu.setText(Integer.toString(al.getId()));
				txtNome.setText(al.getNome());
				txtCpf.setText(al.getCpf());
				txtEmail.setText(al.getEmail());
			}
		});
		btnAtualizar.setBounds(108, 279, 88, 23);
		panel.add(btnAtualizar);

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AlunoController ac = new AlunoController();
				Aluno al = new Aluno();
				al.setId(Integer.parseInt(txtIdRem.getText()));
				ac.remover(al);

				while (jTable.getModel().getRowCount() > 0) {
					((DefaultTableModel) jTable.getModel()).removeRow(0);
				}

				carregarTabela();

				txtIdRem.setText("");
				JOptionPane.showMessageDialog(null, "Aluno removido!");
			}
		});
		btnRemover.setBounds(107, 346, 89, 23);
		panel.add(btnRemover);

		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(34, 322, 46, 14);
		panel.add(label);

		txtIdRem = new JTextField();
		txtIdRem.setColumns(10);
		txtIdRem.setBounds(34, 347, 46, 20);
		panel.add(txtIdRem);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIdPesq.setText("");
				txtNome.setText("");
				txtCpf.setText("");
				txtEmail.setText("");
				txtIdAtu.setText("");
				txtIdRem.setText("");
			}
		});
		btnLimpar.setBounds(196, 204, 75, 23);
		panel.add(btnLimpar);

		txtIdPesq = new JTextField();
		txtIdPesq.setColumns(10);
		txtIdPesq.setBounds(331, 120, 35, 20);
		panel.add(txtIdPesq);

		txtNomePesq1 = new JTextField();
		txtNomePesq1.setColumns(10);
		txtNomePesq1.setBounds(386, 120, 86, 20);
		panel.add(txtNomePesq1);

		JButton button = new JButton("Pesquisar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AlunoController ac = new AlunoController();
				Aluno al = new Aluno();

				if (txtIdPesq.getText().equals("")) {
					al = ac.buscaPorNome(txtNomePesq1.getText());

					txtNomePesq.setText(al.getNome());
					txtCpfPesq.setText(al.getCpf());
					txtEmailPesq.setText(al.getEmail());
				} else {
					al = ac.buscaPorId(Integer.parseInt(txtIdPesq.getText()));

					txtNomePesq.setText(al.getNome());
					txtCpfPesq.setText(al.getCpf());
					txtEmailPesq.setText(al.getEmail());
				}

				txtIdPesq.setText("");
				txtNomePesq1.setText("");

				txtIdPesq.setEnabled(true);
				txtNomePesq1.setEnabled(true);
			}
		});
		button.setBounds(331, 151, 141, 23);
		panel.add(button);

		JLabel lblRemoverAluno = new JLabel("Remover Aluno");
		lblRemoverAluno.setForeground(Color.GRAY);
		lblRemoverAluno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRemoverAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoverAluno.setBounds(23, 302, 248, 20);
		panel.add(lblRemoverAluno);

		JLabel lblAtualizarCadastroDo = new JLabel("Atualizar Cadastro do Aluno");
		lblAtualizarCadastroDo.setForeground(Color.GRAY);
		lblAtualizarCadastroDo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAtualizarCadastroDo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizarCadastroDo.setBounds(23, 237, 248, 20);
		panel.add(lblAtualizarCadastroDo);

		txtIdAtu = new JTextField();
		txtIdAtu.setBounds(34, 280, 46, 20);
		panel.add(txtIdAtu);
		txtIdAtu.setColumns(10);

		txtNomePesq = new JTextField();
		txtNomePesq.setEditable(false);
		txtNomePesq.setBounds(331, 205, 141, 20);
		panel.add(txtNomePesq);
		txtNomePesq.setColumns(10);

		txtCpfPesq = new JTextField();
		txtCpfPesq.setEditable(false);
		txtCpfPesq.setBounds(331, 255, 141, 20);
		panel.add(txtCpfPesq);
		txtCpfPesq.setColumns(10);

		txtEmailPesq = new JTextField();
		txtEmailPesq.setEditable(false);
		txtEmailPesq.setBounds(331, 308, 141, 20);
		panel.add(txtEmailPesq);
		txtEmailPesq.setColumns(10);

		JButton btnLimparPesq = new JButton("Limpar");
		btnLimparPesq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIdPesq.setText("");
				txtNomePesq.setText("");
				txtNomePesq1.setText("");
				txtCpfPesq.setText("");
				txtEmailPesq.setText("");
			}
		});
		btnLimparPesq.setBounds(331, 344, 141, 23);
		panel.add(btnLimparPesq);

		modelo = (new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		jTable = new JTable(new DefaultTableModel(new Object[][] {}, new String[] {}));
		ScrollPane scroll = new ScrollPane();

		jTable.setEnabled(true);
		jTable.setRowSelectionAllowed(true);
		jTable.setBounds(528, 61, 449, 275);

		String[] colunas = { "Id", "Nome", "CPF", "E-mail" };

		modelo.setColumnIdentifiers(colunas);
		modelo.setRowCount(0);

//		Object[] objetos = new Object[4];
//		objetos[0] = "Id";
//		objetos[1] = "Nome";
//		objetos[2] = "CPF";
//		objetos[3] = "E-mail";
//		modelo.addRow(objetos);

		jTable.setModel(modelo);

		frmCadastroDeAluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeAluno.setVisible(true);

		scroll.setBounds(528, 61, 449, 275);
		panel.add(scroll);
		scroll.add(jTable);

		JLabel lblCadastro = new JLabel("Cadastro");
		lblCadastro.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCadastro.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastro.setBounds(23, 0, 248, 27);
		panel.add(lblCadastro);

		this.jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {

				// ListSelectionModel lsm = (ListSelectionModel) e.getSource();

				AlunoController ac = new AlunoController();
				Aluno al = new Aluno();

//				int linha = jTable.getSelectedRow();

				// Pega o dado de dentro da celula da tabela.
//				String nome = (String) jTable.getValueAt(jTable.getSelectedRow(), 1);

				// Pega o codigo e...
				Object idCol = jTable.getValueAt(jTable.getSelectedRow(), 0);

				// converte pra inteiro.
				int[] id = {(Integer) idCol};

				System.out.println(id);

//				al.setId(id);

//				ac.remover(al);
//
//				while (jTable.getModel().getRowCount() > 0) {
//					((DefaultTableModel) jTable.getModel()).removeRow(0);
//				}

				carregarTabela();

				JOptionPane.showMessageDialog(null, "Aluno removido!");
			}
		});
	}

	public void carregarTabela() {
		jTable.setModel(modelo);

		emf = Persistence.createEntityManagerFactory("aluno");
		em = emf.createEntityManager();
		em.getTransaction().begin();
		Query consulta = em.createQuery("SELECT aluno FROM Aluno aluno");
		@SuppressWarnings("unchecked")
		List<Aluno> alunos = consulta.getResultList();

		for (Aluno aluno : alunos) {
			modelo.addRow(new Object[] { aluno.getId(), aluno.getNome(), aluno.getCpf(), aluno.getEmail() });
		}
	}
}