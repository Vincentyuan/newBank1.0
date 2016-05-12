package bank.dataManage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.dao.NatDebtRateDao;
import bank.dao.NatDebtRateImpl;
import bank.dao.UserDao;
import bank.dao.UserDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.entity.JudgeRecord;
import bank.frame.BaseFrame;
import bank.frame.ClientContext;
import bank.frame.MainFrame;
import bank.frame.MyPanel;
import bank.frame.panel.indexPanel;
import bank.frame.panel.workPanel;

public class JTableForMoreEntry extends BaseFrame {

	private JButton save, add, delete;

	private JTable jTable;

	private JScrollPane jScrollPane;

	private JPanel header, body, toolbar;

	private dataManagementPanel dPanel;

	private String currentTable;

	private ClientContext context;
	private JPanel mypanel;
	private JButton shortJudge, longJudge, multiJudge;

	private JButton index, addScreen, searchScreen, dataManagement, logOff;

	private JPanel north, workPanel;// borderlayout workPanel.center 的主panel，

	private workPanel[] work; // workpanel的内容panel，不同的按钮更新不同的panel。

	public JTableForMoreEntry(dataManagementPanel dPanel) {
		// TODO Auto-generated constructor stub

		// mypanel=new MyPanel("image/backGround3.jpg");

		this.currentTable = dPanel.getCurrentTable();

		jScrollPane = new JScrollPane();

		mypanel = new JPanel();

		body = new JPanel();

		toolbar = new JPanel();
		save = new JButton("保存修改");
		add = new JButton("添加");
		delete = new JButton("删除");

		north = new JPanel();
		north.setOpaque(false);

		jScrollPane = new JScrollPane();
		// west=new JPanel();
		// west.setOpaque(false);
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		this.setTitle(currentTable);
		this.setContentPane(mypanel);
		this.setVisible(true);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		// setComponent();

		if (currentTable.equals("短期数据")) {
			this.setBounds(0, 0, 1000, 500);
		} else if (currentTable.equals("评级记录")) {
			this.setBounds(0, 0, 600, 500);
		} else {
			this.setBounds(0, 0, 600, 500);
		}

		mypanel.setLayout(new BorderLayout());

	//	toolbar.add(add);
	//	toolbar.add(save);
		toolbar.add(delete);

		mypanel.add(toolbar, BorderLayout.NORTH);

		mypanel.add(jScrollPane, BorderLayout.CENTER);

		addListener();

		showTables(currentTable);
	}

	public void addListener() {
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (currentTable == null) {
						JOptionPane.showMessageDialog(null, "请先选择数据表");
					} else {

						if (jTable.getSelectedRows().length > 0) {
							deleteRows(jTable.getName());
						} else {
							JOptionPane.showMessageDialog(null, "您还没有选择要删除的数据");
						}
					}

				} catch (SQLException e1) {
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
				JOptionPane.showMessageDialog(null, "功能尚未定义");
			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JOptionPane.showMessageDialog(null, "功能尚未定义");
			}
		});
	}

	public void showTables(String tableName) throws Exception {

		// String [] header={"username"};
		switch (tableName) {

		case "长期数据":
			bankLongTable();
			break;

		case "短期数据":
			bankShortTable();
			break;

		case "评级记录":
			judgeRecordTable();
			break;

		default:
			break;
		}

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
		jTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		jTable.setColumnSelectionAllowed(true);
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

	public void deleteRows(String tableName) throws Exception {

		// delete from user where username='yuan';
		String from = "delete from ";
		String where = " where ";
		String and = " and ";
		// 生成筛选条件
		String sql = "";
		// 生成条件语句
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
				String year = (String) jTable.getModel().getValueAt(
						selection[i], 1);
				sql = from + tableName + where + "name = '" + year + "'";

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

				sql = from + tableName + where + "Bank_id='" + bank_id + "' "
						+ and + "Year_id='" + year + "'";
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
				showTables(currentTable);

			}
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
				sql = from + tableName + where + "year_id='" + year_id + "'";
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
				sql = from + tableName + where + "userName = '" + userName
						+ "'";

				tmp.delete(sql);
				
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

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		JTableForMoreEntry t = new JTableForMoreEntry(null);
		t.init();
	}

}
