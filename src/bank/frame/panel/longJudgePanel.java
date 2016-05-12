package bank.frame.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.entity.JudgeRecord;
import bank.entity.Year;
import bank.frame.ClientContext;
import bank.frame.MainFrame;
import bank.judge.Judge;
import bank.judge.entity.longJudgeEntity;
import bank.util.ImageEntry;
import bank.util.ImageHandle;

public class longJudgePanel extends workPanel {

	private JLabel bankName, year, bad, good, better;
	private JComboBox bankt, yeart;

	private JTextField badt, goodt, bettert;
	private JButton assess, tendency;

	private JPanel head, mid, bottom;

	private MainFrame frame;

	private static longJudgePanel longJudgePanel;

	public longJudgePanel(MainFrame frame) {

		// this.frame=new MainFrame(frame.getClientContext());

		this.frame = frame;

		bankName = new JLabel("���У�");
		bankt = new JComboBox();
		year = new JLabel("��ݣ�");
		yeart = new JComboBox();
		better = new JLabel("�ţ�");
		bettert = new JTextField(5);
		good = new JLabel("����");
		goodt = new JTextField(5);
		bad = new JLabel("�");
		badt = new JTextField(5);
		assess = new JButton("����");
		tendency = new JButton("����ͼ");

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
	}

	public void init() throws Exception {
		this.setBounds(0, 0, 400, 400);
		// this.setLayout(null);
		this.setOpaque(false);
		this.add(head);
		head.add(bankName);
		head.add(bankt);
		head.add(year);
		head.add(yeart);

		this.add(mid);
		/*
		 * mid.add(better); mid.add(bettert); mid.add(good); mid.add(goodt);
		 * mid.add(bad); mid.add(badt);
		 */
		this.add(bottom);
		// bottom.add(tendency);
		bottom.add(assess);
		fillBankYear();
		addListener();
	}

	public void addListener() {
		assess.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					frame.getWorkPanel().removeAll();

					frame.setBorderlayout(new longJudgeReport(
							getLongJudgeReportData()));

					frame.revalidate();
					frame.repaint();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "�������ݲ������������");
				}

			}
		});

	}

	private void fillBankYear() throws Exception {
		// TODO Auto-generated method stub

		BankDao bankDao = new BankDaoImpl();
		List<Bank> banks = bankDao.getAllBank();
		// JOptionPane.showMessageDialog(null, "��������ݲ���Ϊ��");
		for (Bank bank : banks) {
			bankt.addItem(bank.getName());
		}
		YearDao yearDao = new YearDaoImpl();
		List<Year> yearAll = yearDao.getAllYear();

		for (Year year : yearAll) {
			yeart.addItem(year.getYear());
		}
	}

	public JudgeRecord getLongJudgeReportData() throws Exception {
		longJudgeEntity l = new longJudgeEntity();
		l.setBankname(bankt.getSelectedItem().toString());
		l.setYear(Integer.valueOf(yeart.getSelectedItem().toString()));
		l.getLongJudge();

		return l.toLongJudgeRecord();
	}

	public static longJudgePanel getInstance(MainFrame frame) {
		if (longJudgePanel == null) {
			return new longJudgePanel(frame);
		} else {
			return longJudgePanel;
		}
	}

}
