package bank.dataManage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.DRateDao;
import bank.dao.DRateDaoImpl;
import bank.dao.DataBaseInfoDao;
import bank.dao.DataBaseInfoDaoImpl;
import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.dao.NatDebtRateDao;
import bank.dao.NatDebtRateImpl;
import bank.dao.UserDao;
import bank.dao.UserDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.dataImport.ImportData;
import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.entity.DRate;
import bank.entity.JudgeRecord;
import bank.entity.NatDebtRate;
import bank.entity.UserInfo;
import bank.entity.Year;
import bank.frame.MainFrame;
import bank.frame.panel.workPanel;

public class dataManagementPanel extends workPanel {

	private JButton importDataButton;
	private JComboBox tableList;
	private JButton viewTableButton;

	private JButton save, add, delete;

	private JTable jTable;

	private JScrollPane jScrollPane;

	private JPanel header, body, toolbar;

	private MainFrame frame;

	private String currentTable;

	public dataManagementPanel(MainFrame frame) {
		// TODO Auto-generated constructor stub

		this.frame = frame;

		importDataButton = new JButton("��������");
		tableList = new JComboBox();
		viewTableButton = new JButton("��");
		header = new JPanel();

		// jTable=new JTable(10,10);

		jScrollPane = new JScrollPane();
		body = new JPanel();

		toolbar = new JPanel();
		save = new JButton("�����޸�");
		add = new JButton("���");
		delete = new JButton("ɾ��");

		header.setOpaque(false);
		body.setOpaque(false);
		toolbar.setOpaque(false);

	}

	public void init() throws SQLException {
		// this.setBounds(0, 0, 200, 200);
		// this.frame.setBounds(0, 0, 1000, 1000);
		this.setOpaque(false);

		this.setLayout(new BorderLayout());

		this.add(header, BorderLayout.NORTH);
		header.add(importDataButton);
		header.add(tableList);
		header.add(viewTableButton);

		this.add(body, BorderLayout.CENTER);

		body.setLayout(new BorderLayout());
		body.add(toolbar, BorderLayout.NORTH);
	//	toolbar.add(add);
	//	toolbar.add(save);
		toolbar.add(delete);

		body.add(toolbar, BorderLayout.NORTH);
	//	toolbar.add(add); //���ֱ����ӡ�
	//	toolbar.add(save);//����޸ı��档��ʱע��
		toolbar.add(delete);

		body.add(jScrollPane, BorderLayout.CENTER);

		loadTablesName();
		addListener();
		// showTables("�û�");
		// showTables("bank");
	}

	public void loadTablesName() throws SQLException {
		DataBaseInfoDao tables = new DataBaseInfoDaoImpl();
		String[] tmp = tables.getTablesName();
		for (String string : tmp) {

			switch (string) {
			case "bank":
				tableList.addItem("����");
				break;
			case "year":
				tableList.addItem("���");
				break;
			case "bank_long":
				tableList.addItem("��������");
				break;
			case "drate":
				tableList.addItem("���õȼ�");
				break;
			case "bank_data":
				tableList.addItem("��������");
				break;
			case "andebtinterestRate":
				tableList.addItem("��ծ����");
				break;
			case "user":
				tableList.addItem("�û�");
				break;
			case "judgerecord":
				tableList.addItem("������¼");
				break;

			default:
				break;
			}

		}
	}

	public void addListener() {
		viewTableButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					currentTable = tableList.getSelectedItem().toString();

					if (currentTable.equals("��������")
							|| currentTable.equals("��������")
							|| currentTable.equals("������¼")) {
						JTableForMoreEntry moreEntry = new JTableForMoreEntry(
								getObject());
						moreEntry.init();

					} else {
						showTables(currentTable);
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				currentTable = (tableList.getSelectedItem().toString());
			}
		});
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (currentTable == null) {
						JOptionPane.showMessageDialog(null, "����ѡ�����ݱ�");
					} else {
				//		System.out.println(jTable.getSelectedRows().length);
						try {
							if (jTable.getSelectedRows().length > 0) {
								deleteRows(jTable.getName());
						//		System.out.println(jTable.getName());
							} else {
								JOptionPane.showMessageDialog(null,
										"����û��ѡ��Ҫɾ��������");
							}
						} catch (Exception e2) {
							// TODO: handle exception
							e2.printStackTrace();
							JOptionPane.showMessageDialog(null, "������ѡ��Ҫɾ��������");

						}

					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		importDataButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ImportData importData=new ImportData();
				
				try {
					importData.saveData();
				} catch (IndexOutOfBoundsException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				

			}
		});
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "������δ����");
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "������δ����");
			}
		});
	}

	public void showTables(String tableName) throws Exception {

		// String [] header={"username"};
		switch (tableName) {
		case "����":
			bankTable();
			break;
		case "���":
			yearTable();
			break;
		case "��������":
			bankLongTable();
			break;
		case "���õȼ�":
			drateTable();
			break;
		case "��������":
			bankShortTable();
			break;
		case "��ծ����":
			andebtinterestRateTable();
			break;
		case "�û�":
			userTable();
			break;
		case "������¼":
			judgeRecordTable();
			break;

		default:
			break;
		}

	}

	public void userTable() throws SQLException {

		Object[][] dataObjects = {};
		Vector userDataVector = new Vector();

		UserDao userTabeDao = new UserDaoImpl();
		ArrayList<UserInfo> userInfos = userTabeDao.getTableContent();
		for (int i = 0; i < userInfos.size(); i++) {

			userDataVector.addElement(new UserInfo(i + 1, userInfos.get(i)
					.getNameString(), userInfos.get(i).getPasswdString(),
					userInfos.get(i).getPosition()));
		}

		jTable = new JTable(new UserTableModel(userDataVector));
		jTable.setName("user");
		jTable.addMouseListener(new MouseAdapter() {

			public void mousePressed(MouseEvent evt) {
				if (evt.isPopupTrigger()) {
					// ȡ���Ҽ����������
					int row = evt.getY() / jTable.getRowHeight();
			//		System.out.println(row);

					JPopupMenu pop = new JPopupMenu();
					JMenuItem menuItem = new JMenuItem("��Ϊ����Ա");
					JMenuItem cancelItem = new JMenuItem("ȡ������ԱȨ��");
					pop.add(menuItem);
					pop.add(cancelItem);

					pop.show(evt.getComponent(), evt.getX(), evt.getY());

					String userName = (String) jTable.getModel().getValueAt(
							row, 1);

					menuItem.addActionListener(new setAdministerListener(
							userName, "setAdminister"));
					cancelItem.addActionListener(new setAdministerListener(
							userName, "cancel"));
				}
			}

		});
		jScrollPane.setViewportView(jTable);
	}

	public void yearTable() throws SQLException {

		Object[][] dataObjects = {};
		Vector yearDataVector = new Vector();

		YearDao yearDao = new YearDaoImpl();
		ArrayList<Year> yearList = (ArrayList<Year>) yearDao.getAllYear();
		for (int i = 0; i < yearList.size(); i++) {

			Year y = new Year(yearList.get(i).getYear());
			y.setId(i + 1);
			yearDataVector.addElement(y);
		}

		jTable = new JTable(new YearTableModel(yearDataVector));
		jTable.setName("year");
		jScrollPane.setViewportView(jTable);
	}

	public void judgeRecordTable() throws SQLException {
		Object[][] dataObjects = {};
		Vector draftDataVector = new Vector();

		JudgeRecordDao recordDao = new JudgeRecordDaoImpl();
		ArrayList<JudgeRecord> recordList = (ArrayList<JudgeRecord>) recordDao
				.getAllRecords();
		for (int i = 0; i < recordList.size(); i++) {

			JudgeRecord record = recordList.get(i);
			record.setId(i + 1);
			draftDataVector.addElement(record);
		}

		jTable = new JTable(new JudgeRecordTableModel(draftDataVector));
		jTable.setName("judgerecord");
		jScrollPane.setViewportView(jTable);
	}

	public void drateTable() throws SQLException {

		Object[][] dataObjects = {};
		Vector draftDataVector = new Vector();

		DRateDao drateDao = new DRateDaoImpl();
		ArrayList<DRate> drateLists = (ArrayList<DRate>) drateDao
				.getAllDRates();
		for (int i = 0; i < drateLists.size(); i++) {

			DRate dRate = drateLists.get(i);
			dRate.setId(i + 1);
			draftDataVector.addElement(dRate);
		}

		jTable = new JTable(new DrateTableModel(draftDataVector));
		jTable.setName("drate");
		jScrollPane.setViewportView(jTable);
	}

	public void bankLongTable() throws Exception {
		Object[][] dataObjects = {};
		Vector longDataVector = new Vector();

		BankLongDataDao shortDataDao = new BankLongDataImpl();
		ArrayList<BankLongData> bankLongDatas = (ArrayList<BankLongData>) shortDataDao
				.getAllBankLongData();
		for (int i = 0; i < bankLongDatas.size(); i++) {

			BankLongData bankLongData = bankLongDatas.get(i);
			bankLongData.setId(i + 1);
			longDataVector.addElement(bankLongData);
		}

		jTable = new JTable(new BankLongDataTableModel(longDataVector));
		jTable.setName("bank_long");
		jScrollPane.setViewportView(jTable);
	}

	public void bankShortTable() throws Exception {

		Object[][] dataObjects = {};
		Vector shortDataVector = new Vector();

		BankShortDataDao shortDataDao = new BankShortDataDaoImpl();
		ArrayList<BankShortData> bankShortDatas = (ArrayList<BankShortData>) shortDataDao
				.getAllShortData();
		for (int i = 0; i < bankShortDatas.size(); i++) {

			BankShortData bankShortData = bankShortDatas.get(i);
			bankShortData.setSbid(i + 1);
			shortDataVector.addElement(bankShortData);
		}

		jTable = new JTable(new BankShortDataTableModel(shortDataVector));
		jTable.setName("bank_data");
		jScrollPane.setViewportView(jTable);

	}

	public void bankTable() throws SQLException {

		Object[][] dataObjects = {};
		Vector draftDataVector = new Vector();

		BankDao bankDao = new BankDaoImpl();
		ArrayList<Bank> bankList = (ArrayList<Bank>) bankDao.getAllBank();
		for (int i = 0; i < bankList.size(); i++) {

			Bank bank = bankList.get(i);
			bank.setId(i + 1);
			draftDataVector.addElement(bank);
		}

		jTable = new JTable(new BankTableModel(draftDataVector));
		jTable.setName("bank");
		jScrollPane.setViewportView(jTable);
	}

	public void andebtinterestRateTable() throws SQLException {

		Object[][] dataObjects = {};
		Vector draftDataVector = new Vector();

		NatDebtRateDao rateDao = new NatDebtRateImpl();
		ArrayList<NatDebtRate> natLists = rateDao.getAllNatDebtRates();
		for (int i = 0; i < natLists.size(); i++) {

			NatDebtRate nat = natLists.get(i);
			nat.setId(i + 1);
			draftDataVector.addElement(nat);
		}

		jTable = new JTable(new AndebtinterestRateTableModel(draftDataVector));
		jTable.setName("andebtinterestRate");
		jScrollPane.setViewportView(jTable);
	}

	public void deleteRows(String tableName) throws Exception {

		// delete from user where username='yuan';
		String from = "delete from ";
		String where = " where ";
		String and = " and ";
		// ����ɸѡ����
		String sql = "";
		// �����������
		int[] selection = jTable.getSelectedRows();
		switch (tableName) {
		case "bank":
			BankDao bankDao = new BankDaoImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				String bankName = (String) jTable.getModel().getValueAt(
						selection[i], 1);
				sql = from + tableName + where + "name = '" + bankName + "'";

				bankDao.delete(sql);
				
			}
			showTables(currentTable);
			break;
		case "year":
			YearDao yearDao = new YearDaoImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				String year = String.valueOf( jTable.getModel().getValueAt(
						selection[i], 1));
				sql = from + tableName + where + "year = '" + year + "'";

				yearDao.delete(sql);
				
			}
			showTables(currentTable);
			break;
		case "bank_long":
			BankLongDataDao longDataDao = new BankLongDataImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				String bank = (String) jTable.getModel().getValueAt(
						selection[i], 1);
				int bank_id = new BankDaoImpl().getBankIdByName(bank);

				int year = (int) jTable.getModel().getValueAt(selection[i], 2);
				int year_id = (Integer) new YearDaoImpl().getYearIdByYear(year);

				sql = from + tableName + where + "Bank_id='" + bank_id + "' "
						+ and + "Year_id='" + year_id + "'";
				longDataDao.delete(sql);
				
			}
			showTables(currentTable);
			break;
		case "drate":
			DRateDao dRateDao = new DRateDaoImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				String name = (String) jTable.getModel().getValueAt(
						selection[i], 1);

				sql = from + tableName + where + "name='" + name + "'";
				dRateDao.delete(sql);
				

			}
			showTables(currentTable);
			break;
		case "bank_data":
			BankShortDataDao shortDataDao = new BankShortDataDaoImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				String bank = (String) jTable.getModel().getValueAt(
						selection[i], 1);

				int bid = new BankDaoImpl().getBankIdByName(bank);
				int year = (int) jTable.getModel().getValueAt(selection[i], 2);
				sql = from + tableName + where + "bid='" + bid + "' " + and
						+ "year='" + year + "'";
				shortDataDao.delete(sql);
				
			}
			showTables(currentTable);
			break;
		case "andebtinterestRate":
			NatDebtRateDao nat = new NatDebtRateImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				int year_id = (int) jTable.getModel().getValueAt(selection[i],
						1);
				sql = from + tableName + where + "year='" + year_id + "'";
				nat.delete(sql);
				
			}
			showTables(currentTable);
			break;
		case "user":
			UserDao tmp = new UserDaoImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);
				String userName = (String) jTable.getModel().getValueAt(
						selection[i], 1);
				
				if (!userName.equals("root")) {
					sql = from + tableName + where + "userName = '" + userName
							+ "'";
					tmp.delete(sql);
				}
				else {
					JOptionPane.showMessageDialog(null, "�޷�ɾ��root�û�");
				}
				
				
			}
			showTables(currentTable);
			break;
		case "judgerecord":
			JudgeRecordDao recordDao = new JudgeRecordDaoImpl();
			for (int i = 0; i < selection.length; i++) {
				selection[i] = jTable.convertRowIndexToModel(selection[i]);

				String type = (String) jTable.getModel().getValueAt(
						selection[i], 1);
				String bankName = (String) jTable.getModel().getValueAt(
						selection[i], 2);
				int year = (int) jTable.getModel().getValueAt(selection[i], 3);
				sql = from + tableName + where + "type='" + type + "' " + and
						+ "bankname='" + bankName + "' " + and + "year='"
						+ year + "'";
				recordDao.delete(sql);
				
			}
			showTables(currentTable);
			break;

		default:
			break;
		}

	}

	public String getCurrentTable() {
		return currentTable;
	}

	public dataManagementPanel getObject() {
		return this;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// new dataManagementPanel().init();
	}

	class setAdministerListener implements ActionListener {

		private String usernameString;
		private String typeString;

		public setAdministerListener(String name, String type) {
			usernameString = name;
			typeString = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			UserDao userDao = new UserDaoImpl();
			if (usernameString.equals("root")) {
				JOptionPane.showMessageDialog(null, "root�˻��������޸�");
			} else {

				if (typeString.equals("setAdminister")) {
					String sqlString = "update user set authority=1 where userName='"
							+ usernameString + "'";

					try {
						userDao.setAsAdminister(sqlString);
						try {
							showTables(currentTable);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// JOptionPane.showMessageDialog(null,
						// "���óɹ��������´򿪱��鿴");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					String sqlString = "update user set authority=0 where userName='"
							+ usernameString + "'";

					try {
						userDao.setAsAdminister(sqlString);
						try {
							showTables(currentTable);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// JOptionPane.showMessageDialog(null,
						// "���óɹ��������´򿪱��鿴");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "����ʧ��");
					}
				}

			}
		}

	}

}
