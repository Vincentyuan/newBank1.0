package bank.frame.panel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.entity.Year;
import bank.judge.entity.longJudgeEntity;

public class addLongDataPanel extends workPanel {

	private JLabel bankl, yearl, IB_Assetl, Loanl, Investmentl,
			in_Center_Bankl, Call_Loan_ToBanl, Fund_Raise_Amountl, Borrowingl,
			Issue_Bondsl, Depositl, R1_Loanl, R2_Investmentl, R3_C_EmFundsl,
			R4_Offerl, R5_Borrowl, R6_Bondl, R7_Depositl, Takingl,
			Extra_Depositl, Actual_Loan_Amountl, Actual_RI_Loanl;

	private JTextField  IB_Assett, Loant, Investmentt,
			in_Center_Bankt, Call_Loan_ToBant, Fund_Raise_Amountt, Borrowingt,
			Issue_Bondst, Depositt, R1_Loant, R2_Investmentt, R3_C_EmFundst,
			R4_Offert, R5_Borrowt, R6_Bondt, R7_Depositt, Takingt,
			Extra_Depositt, Actual_Loan_Amountt, Actual_RI_Loant;

	private JComboBox bankt, yeart;
	
	

	private JButton save;

	public addLongDataPanel() {

		
		this.bankl = new JLabel("���У�");
		this.bankt = new JComboBox();
		this.yearl = new JLabel("���");
		this.yeart = new JComboBox();
		this.IB_Assetl = new JLabel("��Ϣ�ʲ�");
		this.IB_Assett = new JTextField();
		this.Loanl = new JLabel("����");
		this.Loant = new JTextField();
		this.Investmentl = new JLabel("Ͷ��");
		this.Investmentt = new JTextField();
		this.in_Center_Bankl = new JLabel("�������");
		this.in_Center_Bankt = new JTextField();
		this.Call_Loan_ToBanl = new JLabel("���ͬҵ");
		this.Call_Loan_ToBant = new JTextField();
		this.Fund_Raise_Amountl = new JLabel("��Ϣ��ծ");
		this.Fund_Raise_Amountt = new JTextField();
		this.Borrowingl = new JLabel("ͬҵ����");
		this.Borrowingt = new JTextField();
		this.Issue_Bondsl = new JLabel("����ծȯ");
		this.Issue_Bondst = new JTextField();
		
		this.Depositl=new JLabel("���");
		this.Depositt=new JTextField();

		this.R1_Loanl = new JLabel("ƽ��������");
		this.R1_Loant = new JTextField();
		this.R2_Investmentl = new JLabel("Ͷ��������");
		this.R2_Investmentt = new JTextField();

		this.R3_C_EmFundsl = new JLabel("�Ͻ�����׼����");
		this.R3_C_EmFundst = new JTextField();
		this.R4_Offerl = new JLabel("���ƽ������");
		this.R4_Offert = new JTextField();

		this.Extra_Depositl = new JLabel("ʵ�ʴ��");
		this.Extra_Depositt = new JTextField();
		this.Actual_Loan_Amountl = new JLabel("ʵ�ʴ����");
		this.Actual_Loan_Amountt = new JTextField();

		this.Takingl = new JLabel("Ӫҵ֧��");
		this.Takingt = new JTextField();
		this.R5_Borrowl = new JLabel("ƽ��������");
		this.R5_Borrowt = new JTextField();

		this.R6_Bondl = new JLabel("ƽ��ծȯ����");
		this.R6_Bondt = new JTextField();
		this.R7_Depositl = new JLabel("ƽ���������");
		this.R7_Depositt = new JTextField();

		this.save = new JButton("����");
		this.setOpaque(false);
	}

	public void init() throws SQLException {
		this.setLayout(new GridLayout(11, 4));
		this.add(bankl);
		this.add(bankt);
		this.add(yearl);
		this.add(yeart);
		this.add(IB_Assetl);
		this.add(IB_Assett);
		this.add(Loanl);
		this.add(Loant);
		this.add(Investmentl);
		this.add(Investmentt);
		this.add(in_Center_Bankl);
		this.add(in_Center_Bankt);
		this.add(Call_Loan_ToBanl);
		this.add(Call_Loan_ToBant);
		this.add(Fund_Raise_Amountl);
		this.add(Fund_Raise_Amountt);
		this.add(Borrowingl);
		this.add(Borrowingt);
		this.add(Issue_Bondsl);
		this.add(Issue_Bondst);
		this.add(R1_Loanl);
		this.add(R1_Loant);
		this.add(R2_Investmentl);
		this.add(R2_Investmentt);
		this.add(R3_C_EmFundsl);
		this.add(R3_C_EmFundst);
		this.add(R4_Offerl);
		this.add(R4_Offert);
		this.add(Extra_Depositl);
		this.add(Extra_Depositt);
		this.add(Actual_Loan_Amountl);
		this.add(Actual_Loan_Amountt);
		this.add(Takingl);
		this.add(Takingt);
		this.add(R5_Borrowl);
		this.add(R5_Borrowt);
		this.add(R6_Bondl);
		this.add(R6_Bondt);
		this.add(R7_Depositl);
		this.add(R7_Depositt);
		this.add(Depositl);
		this.add(Depositt);
		this.add(save);

		loadBYData();
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (checkValid()) {
					BankLongDataDao longDataDao = new BankLongDataImpl();
					try {
						longDataDao.addBankLongData(getInput());
						JOptionPane.showMessageDialog(getObject(), "����ɹ�");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(getObject(), "ʧ��");
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(getObject(), "������������");
				}
			}

		});

	}
	
	public void loadBYData() throws SQLException{
		
		
		BankDao bankDao = new BankDaoImpl();
		List<Bank> banks = bankDao.getAllBank();
		// JOptionPane.showMessageDialog(null, "��������ݲ���Ϊ��");
		for (Bank bank : banks) {
			bankt.addItem(bank.getName());
		}
		YearDao yearDao = new YearDaoImpl();
		List<Year> yearAll = yearDao.getAllYear();

		for (Year year : yearAll) {
			// System.out.println(year.getYear());
			yeart.addItem(year.getYear());
		}
		
		
	}

	public boolean checkValid() {
		try {
			getInput();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}

	public BankLongData getInput() throws SQLException {
		BankLongData tmp = new BankLongData();
		
		tmp.setYear_Id(new YearDaoImpl().
				getYearIdByYear(Integer.valueOf(yeart.getSelectedItem().toString())));
		tmp.setActual_Loan_Amount(Double.valueOf(Actual_Loan_Amountt.getText()
				.toString()));
		tmp.setBank_Id(new BankDaoImpl().getBankIdByName(bankt.getSelectedItem().toString()));
		tmp.setBorrowing(Double.valueOf(Borrowingt.getText().toString()));
		tmp.setCall_Loan_ToBan(Double.valueOf(Call_Loan_ToBant.getText()
				.toString()));
		tmp.setDeposit(Double.valueOf(Depositt.getText().toString()));
		tmp.setExtra_Deposit(Double
				.valueOf(Extra_Depositt.getText().toString()));
		tmp.setFund_Raise_Amount(Double.valueOf(Fund_Raise_Amountt.getText()
				.toString()));
		tmp.setIB_Asset(Double.valueOf(IB_Assett.getText().toString()));
		tmp.setIn_Center_Bank(Double.valueOf(in_Center_Bankt.getText()
				.toString()));
		tmp.setInvestment(Double.valueOf(Investmentt.getText().toString()));
		tmp.setIssue_Bonds(Double.valueOf(Issue_Bondst.getText().toString()));
		tmp.setLoan(Double.valueOf(Loant.getText().toString()));
		tmp.setR1_Loan(Double.valueOf(R1_Loant.getText().toString()));
		tmp.setR2_Investment(Double
				.valueOf(R2_Investmentt.getText().toString()));
		tmp.setR3_C_EmFunds(Double.valueOf(R3_C_EmFundst.getText().toString()));
		tmp.setR4_Offer(Double.valueOf(R4_Offert.getText().toString()));
		tmp.setR5_Borrow(Double.valueOf(R5_Borrowt.getText().toString()));
		tmp.setR6_Bond(Double.valueOf(R6_Bondt.getText().toString()));
		tmp.setR7_Deposit(Double.valueOf(R7_Depositt.getText().toString()));

		return tmp;

	}

	public Component getObject() {
		// TODO Auto-generated method stub
		return this;
	}
}
