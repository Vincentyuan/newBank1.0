package bank.frame.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.NatDebtRateDao;
import bank.dao.NatDebtRateImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.frame.MainFrame;

public class addPanel extends workPanel {

	private JLabel newBank;
	private JLabel newYear;
	private JLabel newThreeYearRate;
	private JLabel newFiveYearRate;

	private JTextField bankName, year, newThreeYear, newFiveYear;

	private JButton newShortData;
	private JButton newLongData;
	private JButton save;
	private JButton saveNewBank;

	private JPanel head, mid, bottom, bottom2;

	private MainFrame frame;

	private static addPanel addPanel;

	public addPanel(MainFrame frame) {

		// this.frame=new MainFrame(frame.getClientContext());

		this.frame = frame;

		newBank = new JLabel("��������");
		newYear = new JLabel("�������");
		newThreeYearRate = new JLabel("���������ڹ�ծ����");
		newFiveYearRate = new JLabel("���������ڹ�ծ����");
		save = new JButton("������ݺ�����");
		bankName = new JTextField(5);
		year = new JTextField(5);
		newThreeYear = new JTextField(5);
		newFiveYear = new JTextField(5);
		newShortData = new JButton("��Ӷ�������");
		newLongData = new JButton("��ӳ�������");
		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		bottom2 = new JPanel();
		bottom2.setOpaque(false);
		saveNewBank = new JButton("��������");

	}

	public void init() {

		this.setOpaque(false);
		this.setBounds(0, 0, 400, 400);

		this.add(head);
		this.add(mid);
		this.add(bottom);
		this.add(bottom2);

		// head.add(newBank);
		// head.add(bankName);
		head.add(newYear);
		head.add(year);

		mid.setLayout(new GridLayout(2, 2));
		mid.add(newThreeYearRate);
		mid.add(newThreeYear);
		mid.add(newFiveYearRate);
		mid.add(newFiveYear);

		bottom.add(save);
		bottom.add(newShortData);
		bottom.add(newLongData);

		// bottom2.setLayout(new GridLayout(3,1));
		bottom2.add(newBank);
		bottom2.add(bankName);
		bottom2.add(saveNewBank);

		addListerner();

	}

	private void addListerner() {
		// TODO Auto-generated method stub
		newShortData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// ��δ������case��

				// frame.changePanelContaine(new addShortDataPanel());

				frame.getWorkPanel().removeAll();
				try {
					frame.setBorderlayout(new addShortDataPanel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.revalidate();
				frame.repaint();

			}
		});
		newLongData.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// ��δ������case��

				// frame.changePanelContaine(new addShortDataPanel());

				frame.getWorkPanel().removeAll();
				try {
					frame.setBorderlayout(new addLongDataPanel());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.revalidate();
				frame.repaint();

			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// ����Ч��

				// save bank
				if (checkValid()) {

					try {
						// save year
						YearDao yeardao = new YearDaoImpl();
						yeardao.insertYear(getYear());

						// save debate
						NatDebtRateDao nat = new NatDebtRateImpl();

						nat.insertDRate(getYear(), getR1(), getR2());
						JOptionPane.showMessageDialog(null, "����ɹ���");
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "����Ϊ�ջ��ʽ����");
				}
			}
		});

		saveNewBank.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (!getBankName().equals(""))

						try {
							BankDao bank = new BankDaoImpl();
							bank.insertBank(getBankName());
							JOptionPane.showMessageDialog(null, "����ɹ���");
						} catch (SQLException e3) {
							// TODO Auto-generated catch block
							e3.printStackTrace();
						}
					else {
						JOptionPane.showMessageDialog(null, "����Ϊ�ջ��ʽ����");
					}

				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "����Ϊ�ջ��ʽ����");
				}
			}
		});
	}

	public int getYear() {
		return Integer.valueOf(year.getText().toString());
	}

	public double getR1() {// THREE YEAR
		return Double.valueOf(newThreeYear.getText().toString());
	}

	public double getR2() {
		return Double.valueOf(newFiveYear.getText().toString());
	}

	public String getBankName() {
		return bankName.getText().toString();
	}

	public boolean checkValid() {
		try {
			// getBankName();
			getYear();
			getR1();
			getR2();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean checkBankYear() {
		try {
			getBankName();
			getYear();

			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public void setFrame(MainFrame frame) {
		this.frame = new MainFrame(frame.getClientContext());
	}

	public static addPanel getInstance(MainFrame frame) {
		if (addPanel == null) {
			return new addPanel(frame);
		} else {
			return addPanel;
		}
	}
}