package bank.frame.panel;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.DRateDao;
import bank.dao.DRateDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankShortData;
import bank.entity.DRate;
import bank.entity.JudgeRecord;
import bank.entity.Year;
import bank.frame.MainFrame;
import bank.judge.Judge;
import bank.judge.entity.shortJudgeEntity;

public class shortJudgePanel extends workPanel {

	private JLabel bankL, yearL;
	private JComboBox years, bankNames;// ���к�bank��

	private JLabel moreThanOneWithdrowText;
	private JTextField moreThanOneWithdrowRate;

	private JLabel bankDegreeText;
	private JComboBox bankDegree;

	private JLabel recycleRateText;
	private JTextField recycleRate;

	private JLabel paymentRateText;
	private JTextField paymentRate;

	private JLabel badText, goodText, betterText;
	private JTextField badRate, goodRate, betterRate;

	private JButton tendency, assignment;

	private JPanel head, mid, bottom, end;

	private MainFrame frame;

	private static shortJudgePanel shortJudgePanel;

	public static int i = 0;

	// ��ʼ������
	public shortJudgePanel(MainFrame frame) throws Exception {

		this.frame = frame;
		years = new JComboBox();
		bankNames = new JComboBox();

		yearL = new JLabel("���:");
		bankL = new JLabel("����:");
		moreThanOneWithdrowText = new JLabel("1�����ϵ��г��ڴ�����ǰ֧ȡ������");
		moreThanOneWithdrowRate = new JTextField(5);
		bankDegreeText = new JLabel("���д���׼��ȼ���������������");
		bankDegree = new JComboBox();
		recycleRateText = new JLabel("ͨ�����յ��ֶβ����˿�Ļ����ʣ�");
		recycleRate = new JTextField(5);

		paymentRateText = new JLabel("������֧���ʽ������");
		paymentRate = new JTextField(5);
		badText = new JLabel("��");
		badRate = new JTextField(5);
		goodText = new JLabel("��");
		goodRate = new JTextField(5);
		betterText = new JLabel("��");
		betterRate = new JTextField(5);

		tendency = new JButton("���");
		assignment = new JButton("����");
		// yearL.setFont(new Font("΢���ź�",Font.BOLD,15));
		// bankL.setFont(new Font("΢���ź�",Font.BOLD,15));

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		end = new JPanel();
		end.setOpaque(false);
	
	}

	// ҳ�沼�ֺ�����
	public void init() throws Exception {
		this.setBounds(0, 0, 400, 400);
		this.add(head);
		this.add(mid);
		this.add(bottom);
		this.add(end);
		this.setOpaque(false);
		head.add(bankL);
		head.add(bankNames);
		head.add(yearL);
		head.add(years);

		mid.setLayout(new GridLayout(4, 2));
		// mid.setBounds(0, 0, 400, 400);
		mid.add(moreThanOneWithdrowText);
		mid.add(moreThanOneWithdrowRate);
		mid.add(bankDegreeText);
		mid.add(bankDegree);
		mid.add(recycleRateText);
		mid.add(recycleRate);

		mid.add(paymentRateText);
		mid.add(paymentRate);

		end.add(tendency);
		end.add(assignment);
		loadData();
		addListener();
		
	}

	// �����˵���������bank year
	private void loadData() throws Exception {
		// TODO Auto-generated method stub

		
		BankDao bankDao = new BankDaoImpl();
		List<Bank> banks = bankDao.getAllBank();
		for (Bank bank : banks) {
			bankNames.addItem(bank.getName());
		}
		
		YearDao yearDao = new YearDaoImpl();
		List<Year> yearAll = yearDao.getAllYear();

		for (Year year : yearAll) {
			years.addItem(year.getYear());
		}
		
		DRateDao dRateDao = new DRateDaoImpl();
		List<DRate> dRates = dRateDao.getAllDRates();
		for (DRate d : dRates) {
			bankDegree.addItem(d.getName());
		}
	}

	// button �����¼�
	public void addListener() {
		// ������ť��Ӧ�¼������㵱ǰ��������¼�����ݲ�����shortJudgeReport
		assignment.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkValid()) {

					frame.getWorkPanel().removeAll();
					try {
						frame.setBorderlayout(new shortJudgeReport(
								getShortJudgeReportData(), getInputData()));
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					frame.revalidate();
					frame.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "��������,��������ݲ����ڣ��������");
				}
			}
		});
		// ����������
		tendency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				recycleRate.setText("");
				moreThanOneWithdrowRate.setText("");
				paymentRate.setText("");
			}
		});
	}

	// ��ö������ݱ�������
	public JudgeRecord getShortJudgeReportData() throws Exception {

		return getUserInput().toShortJudgeRecord();

	}

	// ��ȡ�û����벢���ɶ�Ӧ��ʵ�����
	public shortJudgeEntity getUserInput() throws Exception {

		shortJudgeEntity entity = new shortJudgeEntity();

		entity.setBankName(bankNames.getSelectedItem().toString());
		entity.setYear(years.getSelectedItem().toString());
		entity.setBankDegree(bankDegree.getSelectedItem().toString());
		entity.setMoreThanOneWithdrowRate(Double.valueOf(
				moreThanOneWithdrowRate.getText()).doubleValue());
		entity.setRecycleRate(Double.valueOf(recycleRate.getText())
				.doubleValue());
		entity.setPaymentRate(Double.valueOf(paymentRate.getText())
				.doubleValue());

		entity.setShortJudgeResult();


		return entity;
		/*
		 * 0.5 0 A 0.7
		 */
	}

	// ����������ݵĶ����������ݲ�����report��
	public inputData getInputData() {

		inputData tmp = new inputData();
		tmp.bankDegreeString = bankDegree.getSelectedItem().toString();
		tmp.moreThanOneWithdrowRate = Double.valueOf(
				moreThanOneWithdrowRate.getText()).doubleValue();
		tmp.recycleRate = Double.valueOf(recycleRate.getText()).doubleValue();
		tmp.paymentRate = Double.valueOf(paymentRate.getText()).doubleValue();

		return tmp;
	}

	// ���������Ƿ����Ҫ��
	public boolean checkValid() {
		try {
			// getUserInput();
			getShortJudgeReportData();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		return true;
	}

	// ʹ�õ���ģʽ��ö�������ʾ��
	public static shortJudgePanel getInstance(MainFrame frame) throws Exception {
		if (shortJudgePanel == null) {
			return new shortJudgePanel(frame);
		} else {
			return shortJudgePanel;
		}
	}

	// getter��setter����

	public JComboBox getYears() {
		return years;
	}

	public void setYears(JComboBox years) {
		this.years = years;
	}

	public JComboBox getBankNames() {
		return bankNames;
	}

	public void setBankNames(JComboBox bankNames) {
		this.bankNames = bankNames;
	}

	public JLabel getBankL() {
		return bankL;
	}

	public void setBankL(JLabel bankL) {
		this.bankL = bankL;
	}

	public JLabel getYearL() {
		return yearL;
	}

	public void setYearL(JLabel yearL) {
		this.yearL = yearL;
	}

	public JLabel getMoreThanOneWithdrowText() {
		return moreThanOneWithdrowText;
	}

	public void setMoreThanOneWithdrowText(JLabel moreThanOneWithdrowText) {
		this.moreThanOneWithdrowText = moreThanOneWithdrowText;
	}

	public JTextField getMoreThanOneWithdrowRate() {
		return moreThanOneWithdrowRate;
	}

	public void setMoreThanOneWithdrowRate(JTextField moreThanOneWithdrowRate) {
		this.moreThanOneWithdrowRate = moreThanOneWithdrowRate;
	}

	public JLabel getBankDegreeText() {
		return bankDegreeText;
	}

	public void setBankDegreeText(JLabel bankDegreeText) {
		this.bankDegreeText = bankDegreeText;
	}

	public JComboBox getBankDegree() {
		return bankDegree;
	}

	public void setBankDegree(JComboBox bankDegree) {
		this.bankDegree = bankDegree;
	}

	public JLabel getRecycleRateText() {
		return recycleRateText;
	}

	public void setRecycleRateText(JLabel recycleRateText) {
		this.recycleRateText = recycleRateText;
	}

	public JTextField getRecycleRate() {
		return recycleRate;
	}

	public void setRecycleRate(JTextField recycleRate) {
		this.recycleRate = recycleRate;
	}

	public JLabel getPaymentRateText() {
		return paymentRateText;
	}

	public void setPaymentRateText(JLabel paymentRateText) {
		this.paymentRateText = paymentRateText;
	}

	public JTextField getPaymentRate() {
		return paymentRate;
	}

	public void setPaymentRate(JTextField paymentRate) {
		this.paymentRate = paymentRate;
	}

	public JLabel getBadText() {
		return badText;
	}

	public void setBadText(JLabel badText) {
		this.badText = badText;
	}

	public JLabel getGoodText() {
		return goodText;
	}

	public void setGoodText(JLabel goodText) {
		this.goodText = goodText;
	}

	public JLabel getBetterText() {
		return betterText;
	}

	public void setBetterText(JLabel betterText) {
		this.betterText = betterText;
	}

	public JTextField getBadRate() {
		return badRate;
	}

	public void setBadRate(JTextField badRate) {
		this.badRate = badRate;
	}

	public JTextField getGoodRate() {
		return goodRate;
	}

	public void setGoodRate(JTextField goodRate) {
		this.goodRate = goodRate;
	}

	public JTextField getBetterRate() {
		return betterRate;
	}

	public void setBetterRate(JTextField betterRate) {
		this.betterRate = betterRate;
	}

	public JButton getAssignment() {
		return assignment;
	}

	public void setAssignment(JButton assignment) {
		this.assignment = assignment;
	}

	public static void main(String[] args) throws Exception {
		JFrame t = new JFrame();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 400;

		t.setBounds((d.width - width) / 2, (d.height - height) / 2, width,
				height);

		shortJudgePanel s = new shortJudgePanel(null);

		t.add(s);
		// t.setBounds(, y, width, height);
		// s.setBounds(0, 0, 400, 400);
		s.init();
	}

	// �м���
	class inputData {
		public String bankDegreeString;
		public double moreThanOneWithdrowRate;
		public double recycleRate;
		public double paymentRate;

	}

}
