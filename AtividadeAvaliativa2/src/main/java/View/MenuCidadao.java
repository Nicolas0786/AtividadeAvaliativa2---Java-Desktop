package View;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerCidadao;
import Model.VO.CidadaoVO;
import Model.VO.OcorrenciaVO;

public class MenuCidadao {
	private static Set<Integer> set;

	JFrame frame;
	JTable table;
	DefaultTableModel model;
	JTable table2;
	DefaultTableModel model2;

	public void apresentarMenuCidadaoJframe() {
		final DateTimeFormatter formaterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		Object[][] dados = null;
		final String[] colunasCidadao = { "ID", "Nome", "Numero Documento", "Endereço", "Telefone", "E-mail" };

		try {

			List<CidadaoVO> cidadao = new ControllerCidadao().buscarDadosCidadao();
			dados = new String[cidadao.size()][6];
			int i = 0;

			for (CidadaoVO c : cidadao) {
				// while(table.getRowCount()==0) {

				dados[i][0] = String.valueOf(c.getIdCidadao());
				dados[i][1] = c.getNome();
				dados[i][2] = String.valueOf(c.getNumeroDocumento());
				dados[i][3] = c.getEndereco();
				dados[i][4] = c.getTelefone();
				dados[i][5] = c.getEmail();
				i++;
				// model.addRow(dados);
				// }

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		frame = new JFrame();
		frame.setTitle("Cidadao");
//		table = new JTable(dados, colunasCidadao);

		frame.setResizable(true);

		frame.setSize(720, 700);

		FlowLayout fl = new FlowLayout();

		model = new DefaultTableModel(dados, colunasCidadao);
		table = new JTable(model);
		table.setPreferredSize(new Dimension(690, 350));
		//table.setBounds(10, 20, 50, 90);
		

		// table.setPreferredSize(new Dimension(800,600));
		// table.setSize(500, 100);
		// table.setMaximumSize(new Dimension(500,100));
		// segunda tabela
		JScrollPane sp = new JScrollPane(table);
		frame.add(sp);
		final Object[][] dadosOcorrencia = null;
		final String[] colunasOcorrencia = { "ID", "ID_Cidadao", "Data", "Descrição", "Local" };
		// String[][] dadosOcoencia = new String[0][5];

		model2 = new DefaultTableModel(dadosOcorrencia, colunasOcorrencia);
		// table = new JTable(model);
		table2 = new JTable(model2);
		table2.setPreferredSize(new Dimension(690, 350));
		JScrollPane sp2 = new JScrollPane(table2);

		table.getModel().addTableModelListener(new TableModelListener() {
			public void tableChanged(TableModelEvent e) {
				if (e.getColumn() >= 0) {
					set.add(e.getFirstRow());
				}
			}
		});

		//
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getButton() == MouseEvent.BUTTON3) {

					int resposta = JOptionPane.showConfirmDialog(null, "Você confirma a deleção?");
					if (resposta == JOptionPane.YES_OPTION) {
						int[] selected = table.getSelectedRows();
						int reductor = 0;
						for (int rowIndex : selected) {
							String idCida = (String) table.getValueAt(rowIndex - reductor, 0);
							if (new ControllerCidadao().deleteRegistro(idCida)) {
								model.removeRow(rowIndex - reductor);
								reductor++;
							} else {
								System.err.print("Não foi possível excluir o registro:" + idCida);
							}
						}
						while (table2.getRowCount() > 0) {
							model2.removeRow(0);
						}
						
					}
				} if(evt.getButton() == MouseEvent.BUTTON1) {
					int row = table.rowAtPoint(evt.getPoint());
					String idd = (String) table.getValueAt(row, 0);
					String idCliente = (idd);

					while (table2.getRowCount() > 0) {
						model2.removeRow(0);
					}

					try {

						List<OcorrenciaVO> ocorrencia = new ControllerCidadao().buscarDadosOcorrencia(idCliente);
						String[][] dadosOcorrencia = new String[ocorrencia.size()][5];
						int i = 0;
						for (OcorrenciaVO o : ocorrencia) {
							dadosOcorrencia[i][0] = String.valueOf(o.getId());
							dadosOcorrencia[i][1] = String.valueOf(o.getIdCidadao());
							dadosOcorrencia[i][2] = formaterDate.format(o.getData());
							dadosOcorrencia[i][3] = o.getDescricao();
							dadosOcorrencia[i][4] = o.getLocal();
							model2.addRow(dadosOcorrencia[i]);
							i++;
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}if (evt.getButton() == MouseEvent.BUTTON1) {
					ArrayList<Integer> linhasAtualizar = new ArrayList<Integer>();
					int qtdLinhas = table.getRowCount();
					for (int i = 0; i < qtdLinhas; i++) {
						//int idCidadao = Integer.parseInt((String) table.getValueAt(i, 0));
						String idCidadao = (String) table.getValueAt(i, 0);
						String nome = (String) table.getValueAt(i, 1);
						String numero_doc = (String) table.getValueAt(i, 2);
						String endereço = (String) table.getValueAt(i, 3);
						String telefone = (String) table.getValueAt(i, 4);
						String email = (String) table.getValueAt(i, 5);
						
						if(idCidadao != null) {
						linhasAtualizar.add(i);
						new ControllerCidadao().atualizaRegistro(idCidadao, nome, numero_doc, endereço, telefone,
								email);
						}
					}

				}
			}

		});

		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				if (evt.getButton() == MouseEvent.BUTTON3) {
					int resposta = JOptionPane.showConfirmDialog(null, "Você confirma a deleção?");
					if (resposta == JOptionPane.YES_OPTION) {
						int[] selected = table2.getSelectedRows();
						int reductor = 0;
						for (int rowIndex : selected) {
							String idOcoren = (String) table2.getValueAt(rowIndex - reductor, 0);
							if (new ControllerCidadao().deleteRegistroOcorrencia(idOcoren)) {
								model2.removeRow(rowIndex - reductor);
								reductor++;
							} else {
								System.err.print("Não foi possível excluir o registro:" + idOcoren);
							}
						}
					}
				} else if (evt.getButton() == MouseEvent.BUTTON1) {
					ArrayList<Integer> linhasAtualizar = new ArrayList<Integer>();
					int qtdLinhas = table2.getRowCount();
					for (int i = 0; i < qtdLinhas; i++) {
						String idOcorrencia = (String) table2.getValueAt(i, 0);
						String idCidadao = (String) table2.getValueAt(i, 1);
						String data = (String) table2.getValueAt(i, 2);
						String descricao = (String) table2.getValueAt(i, 3);
						String local = (String) table2.getValueAt(i, 4);

						linhasAtualizar.add(i);
						new ControllerCidadao().atualizaRegistroOcorrencia(idOcorrencia, idCidadao, data, descricao,
								local);
					}
				}
			}
		});

		JScrollPane scroll = new JScrollPane(table);
		scroll.setPreferredSize(new Dimension(690, 250));
		frame.add(scroll);
		frame.setLayout(fl);

		JScrollPane scroll2 = new JScrollPane(table2);
		scroll2.setPreferredSize(new Dimension(690, 250));
		frame.add(scroll2);
		// frame.setLayout(fl);

		// bt inserir linha table
		JButton bt = new JButton();

		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		// bt = new JButton();
		bt.setPreferredSize(new Dimension(120, 20));
		bt.setText("Novo Cidadao");
		bt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				model.addRow(new String[colunasCidadao.length]);
			}
		});

		frame.add(bt);

		// bt incluir linha table 2
		JButton bt2 = new JButton();
		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		bt2.setPreferredSize(new Dimension(130, 20));
		bt2.setText("Nova Ocorrencia");
		bt2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				model2.addRow(new String[colunasOcorrencia.length]);
			}
		});
		frame.add(bt2);

		// bt salvar table 2

		JButton btSalva2 = new JButton();
		btSalva2.setPreferredSize(new Dimension(220, 20));
		btSalva2.setText("Gravar alterações Ocorrencia");

		table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		btSalva2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ArrayList<Integer> linhasIncluir = new ArrayList<Integer>();
				// ArrayList<Integer> linhasAutualizar = new ArrayList<Integer>();

				int qtdLinhas = table2.getRowCount();
				// int qtdColunas = table.getColumnCount();
				int idOco = 0;
				String idCidadao = "";
				String data = "";
				String descricao = "";
				String local = "";
				for (int i = 0; i < qtdLinhas; i++) {
					//String idOcorrencia = (String) table2.getValueAt(i, 0);
					idCidadao = (String) table2.getValueAt(i, 1);
					data = (String) table2.getValueAt(i, 2);
					descricao = (String) table2.getValueAt(i, 3);
					local = (String) table2.getValueAt(i, 4);
					//if (idOcorrencia == null) {
						linhasIncluir.add(i);
						//new ControllerCidadao().insereRegistroOcorrencia(idCidadao, data, descricao, local);
					//}
				}
				idOco = ControllerCidadao.insereRegistroOcorrencia(idCidadao, data, descricao, local);
				System.out.println(idOco);
				table2.setValueAt(idOco, table2.getRowCount()-1, 0);
			}

		});
		frame.add(btSalva2);

		set = new HashSet<Integer>();

		// bt salvar table
		JButton btSalva = new JButton();
		btSalva.setPreferredSize(new Dimension(180, 20));
		btSalva.setText("Gravar alterações");

		//table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		btSalva.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
				ArrayList<CidadaoVO> linhasIncluir = new ArrayList<CidadaoVO>();
				// ArrayList<Integer> linhasAutualizar = new ArrayList<Integer>();

				int qtdLinhas = table.getRowCount();
				// int qtdColunas = table.getColumnCount();
				int id = 0;
				String nome = "";
				String numero_doc = "";
				String endereço = "";
				String telefone = "";
				String email = "";
				
				
				for (int i = 0; i < qtdLinhas; i++) {
					//int idCidadao = Integer.parseInt((String) table.getValueAt(i, 0));
					nome = (String) table.getValueAt(i, 1);
					numero_doc = (String) table.getValueAt(i, 2);
					endereço = (String) table.getValueAt(i, 3);
					telefone = (String) table.getValueAt(i, 4);
					email = (String) table.getValueAt(i, 5);

					
				}
				id = ControllerCidadao.insereRegistro(nome, numero_doc, endereço, telefone, email);
				System.out.println(id);
				table.setValueAt(id, table.getRowCount()-1, 0);
				
			}

		});
		frame.add(btSalva);

		frame.setVisible(true);
		frame.add(sp2);
	}

}
