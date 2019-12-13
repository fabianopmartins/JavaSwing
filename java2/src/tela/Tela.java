package tela;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import controller.AlunoController;
import models.Aluno;

public class Tela {

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
	
	ArrayList<String> dados = new ArrayList<>();
	
	String[] dados1;
	
	int count = 0;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela window = new Tela();
					window.frmCadastroDeAluno.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Tela() {
		AlunoController ac = new AlunoController();
		initialize();
		carregarTabela();
	}

	private void initialize() {
		frmCadastroDeAluno = new JFrame();
		frmCadastroDeAluno.setTitle("Cadastro de Aluno");
		frmCadastroDeAluno.setResizable(false);
		frmCadastroDeAluno.setBounds(100, 100, 1013, 480);
		frmCadastroDeAluno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeAluno.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 977, 340);
		frmCadastroDeAluno.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome.setBounds(21, 0, 46, 14);
		panel.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(21, 23, 248, 20);
		panel.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf.setBounds(21, 54, 46, 14);
		panel.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(21, 79, 248, 20);
		panel.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setBounds(21, 110, 46, 14);
		panel.add(lblEmail);

		JLabel lblListaDeLunos = new JLabel("Lista de Alunos");
		lblListaDeLunos.setForeground(Color.GRAY);
		lblListaDeLunos.setHorizontalAlignment(SwingConstants.CENTER);
		lblListaDeLunos.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblListaDeLunos.setBounds(528, 0, 439, 23);
		panel.add(lblListaDeLunos);

		JLabel label_1 = new JLabel("Pesquisas");
		label_1.setForeground(Color.GRAY);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(329, 23, 141, 23);
		panel.add(label_1);

		JLabel label_2 = new JLabel("ID");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(329, 57, 35, 14);
		panel.add(label_2);

		JLabel label_3 = new JLabel("Nome");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setBounds(385, 57, 85, 14);
		panel.add(label_3);

		JLabel label_4 = new JLabel("ID");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setBounds(32, 220, 46, 14);
		panel.add(label_4);

		JLabel lblNome_1 = new JLabel("Nome");
		lblNome_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNome_1.setBounds(329, 147, 46, 14);
		panel.add(lblNome_1);

		JLabel lblCpf_1 = new JLabel("CPF");
		lblCpf_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpf_1.setBounds(329, 194, 46, 14);
		panel.add(lblCpf_1);

		JLabel lblEmail_1 = new JLabel("E-mail");
		lblEmail_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail_1.setBounds(329, 245, 46, 14);
		panel.add(lblEmail_1);

		txtEmail = new JTextField();
		txtEmail.setBounds(21, 135, 248, 20);
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

				count++;
				
				while (jTable.getModel().getRowCount() > 0) {
					((DefaultTableModel) jTable.getModel()).removeRow(0);
				}

				carregarTabela();

				txtIdAtu.setText("");
				txtIdRem.setText("");
				txtNome.setText("");
				txtCpf.setText("");
				txtEmail.setText("");

				JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso!");
			}
		});
		btnGravar.setBounds(21, 166, 75, 23);
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
		btnAtualizar.setBounds(106, 241, 88, 23);
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

				txtIdAtu.setText("");
				txtIdRem.setText("");
				txtNome.setText("");
				txtCpf.setText("");
				txtEmail.setText("");

				JOptionPane.showMessageDialog(null, "Aluno removido!");
			}
		});
		btnRemover.setBounds(105, 308, 89, 23);
		panel.add(btnRemover);

		JLabel label = new JLabel("ID");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(32, 284, 46, 14);
		panel.add(label);

		txtIdRem = new JTextField();
		txtIdRem.setColumns(10);
		txtIdRem.setBounds(32, 309, 46, 20);
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
		btnLimpar.setBounds(194, 166, 75, 23);
		panel.add(btnLimpar);

		txtIdPesq = new JTextField();
		txtIdPesq.setColumns(10);
		txtIdPesq.setBounds(329, 82, 35, 20);
		panel.add(txtIdPesq);

		txtNomePesq1 = new JTextField();
		txtNomePesq1.setColumns(10);
		txtNomePesq1.setBounds(384, 82, 86, 20);
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

					txtIdAtu.setText(Integer.toString(al.getId()));
					txtIdRem.setText(Integer.toString(al.getId()));
					txtNome.setText(al.getNome());
					txtCpf.setText(al.getCpf());
					txtEmail.setText(al.getEmail());
				} else {
					al = ac.buscaPorId(Integer.parseInt(txtIdPesq.getText()));

					txtNomePesq.setText(al.getNome());
					txtCpfPesq.setText(al.getCpf());
					txtEmailPesq.setText(al.getEmail());

					txtIdAtu.setText(Integer.toString(al.getId()));
					txtIdRem.setText(Integer.toString(al.getId()));
					txtNome.setText(al.getNome());
					txtCpf.setText(al.getCpf());
					txtEmail.setText(al.getEmail());
				}

				txtIdPesq.setText("");
				txtNomePesq1.setText("");

				txtIdPesq.setEnabled(true);
				txtNomePesq1.setEnabled(true);
			}
		});
		button.setBounds(329, 113, 141, 23);
		panel.add(button);

		JLabel lblRemoverAluno = new JLabel("Remover Aluno");
		lblRemoverAluno.setForeground(Color.GRAY);
		lblRemoverAluno.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRemoverAluno.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoverAluno.setBounds(21, 264, 248, 20);
		panel.add(lblRemoverAluno);

		JLabel lblAtualizarCadastroDo = new JLabel("Atualizar Cadastro do Aluno");
		lblAtualizarCadastroDo.setForeground(Color.GRAY);
		lblAtualizarCadastroDo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblAtualizarCadastroDo.setHorizontalAlignment(SwingConstants.CENTER);
		lblAtualizarCadastroDo.setBounds(21, 199, 248, 20);
		panel.add(lblAtualizarCadastroDo);

		txtIdAtu = new JTextField();
		txtIdAtu.setBounds(32, 242, 46, 20);
		panel.add(txtIdAtu);
		txtIdAtu.setColumns(10);

		txtNomePesq = new JTextField();
		txtNomePesq.setEditable(false);
		txtNomePesq.setBounds(329, 167, 141, 20);
		panel.add(txtNomePesq);
		txtNomePesq.setColumns(10);

		txtCpfPesq = new JTextField();
		txtCpfPesq.setEditable(false);
		txtCpfPesq.setBounds(329, 217, 141, 20);
		panel.add(txtCpfPesq);
		txtCpfPesq.setColumns(10);

		txtEmailPesq = new JTextField();
		txtEmailPesq.setEditable(false);
		txtEmailPesq.setBounds(329, 270, 141, 20);
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
		btnLimparPesq.setBounds(329, 306, 141, 23);
		panel.add(btnLimparPesq);

		modelo = (new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});

		jTable = new JTable(modelo);
		ScrollPane scroll = new ScrollPane();

		jTable.setEnabled(true);
		jTable.setRowSelectionAllowed(true);
		jTable.setBounds(528, 61, 449, 275);

		String[] colunas = { "Id", "Nome", "CPF", "E-mail" };

		modelo.setColumnIdentifiers(colunas);
//		modelo.setRowCount(0);

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

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(552, 41, 46, 14);
		panel.add(lblNewLabel);

		JLabel lblNome_2 = new JLabel("NOME");
		lblNome_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNome_2.setBounds(668, 41, 46, 14);
		panel.add(lblNome_2);

		JLabel lblCpf_2 = new JLabel("CPF");
		lblCpf_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblCpf_2.setBounds(768, 41, 46, 14);
		panel.add(lblCpf_2);

		JLabel lblEmail_2 = new JLabel("E-MAIL");
		lblEmail_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_2.setBounds(876, 41, 46, 14);
		panel.add(lblEmail_2);
		
		JButton btnDados = new JButton("Dados");
		btnDados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < dados.size(); i++) {
					System.out.println(dados.get(i));
					AlunoController ac = new AlunoController();
					
					ac.removeAll(dados.get(i));
				}
				
				while (jTable.getModel().getRowCount() > 0) {
					((DefaultTableModel) jTable.getModel()).removeRow(0);
				}

				carregarTabela();
				
				
			}
		});
		btnDados.setBounds(716, 380, 89, 23);
		frmCadastroDeAluno.getContentPane().add(btnDados);

		this.jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				

			    if(e.getValueIsAdjusting()) {
			        return;
			    }
				

				// ListSelectionModel lsm = (ListSelectionModel) e.getSource();

				AlunoController ac = new AlunoController();
				Aluno al = new Aluno();

//				int linha = jTable.getSelectedRow();

				// Pega o dado de dentro da celula da tabela.
//				String nome = (String) jTable.getValueAt(jTable.getSelectedRow(), 1);
				
				// Pega o codigo e...
				
				
					Object idCol = jTable.getValueAt(jTable.getSelectedRow(), 0);
					Object nomeCol = jTable.getValueAt(jTable.getSelectedRow(), 1);
					Object cpfCol = jTable.getValueAt(jTable.getSelectedRow(), 2);
					Object emailCol = jTable.getValueAt(jTable.getSelectedRow(), 3);

					
					dados.add(String.valueOf(idCol));
					
//					dados = new String[10] = {String.valueOf(idCol)};
					
					
					
					// converte pra inteiro.
					txtIdAtu.setText(String.valueOf(idCol));
					txtIdRem.setText(String.valueOf(idCol));
					txtNome.setText((String) nomeCol);
					txtCpf.setText((String) cpfCol);
					txtEmail.setText((String) emailCol);
				
//				for (int i = 0; i < id.length; i++) {
//					System.out.println(id[i]);
//					JOptionPane.showMessageDialog(null, "Aluno removido!" + id[i]);
//				}
//
////				txtIdRemVar.setText(Integer.toString(id));				
////				al.setId(id);
//
////				ac.remover(al);
////
////				while (jTable.getModel().getRowCount() > 0) {
////					((DefaultTableModel) jTable.getModel()).removeRow(0);
////				}
//
//				carregarTabela();
			}
		});
	}

	public void carregarTabela() {
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