package bank.frame.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankShortData;
import bank.entity.Year;

public class addShortDataPanel extends workPanel {
	
	private JLabel prompt;
	private JLabel bankl,yearl;
	private JComboBox bankt;
	private JComboBox yeart;
	private JLabel liquidLoan,liquidDeposit;
	private JLabel lessThreeMonth,moreThreeMonth,lessFiveYear,moreFiveYear,depositPrepare;
	private JTextField lessThreeMonthL,lessThreeMonthD,moreThreeMonthL,moreThreeMonthD,
	lessFiveYearL,lessFiveYearD,moreFiveYearL,moreFiveYearD,depositPrepareL,depositPrepareD;
	
	
	private JLabel oneTofiveRecycleRateText;
	private JTextField oneTofiveRecycleRate;

	private JLabel moreThanFiveRecycleRateText;
	private JTextField moreThanFiveRecycleRate;
	
	
	private JButton save;
	
	
	private JPanel [] panels;

	public addShortDataPanel(){
		
		
		this.prompt=new JLabel("已有银行新增短期数据");
		this.bankl=new JLabel("银行：");
		this.yearl=new JLabel("年份：");
		this.bankt=new JComboBox();
		this.yeart=new JComboBox();
		this.liquidDeposit=new JLabel("      流动性负债  存款 ");
		this.liquidLoan=new JLabel("  流动性资产  贷款  ");
		this.lessThreeMonth=new JLabel("0-3个月");
		this.lessThreeMonthL=new JTextField();
		this.lessThreeMonthD=new JTextField();
		this.moreThreeMonth=new JLabel("3-12个月");
		this.moreThreeMonthL=new JTextField();
		this.moreThreeMonthD=new JTextField();
		this.lessFiveYear=new JLabel("1-5年");
		this.lessFiveYearL=new JTextField();
		this.lessFiveYearD=new JTextField();
		this.moreFiveYear=new JLabel("5年以上");
		this.moreFiveYearL=new JTextField();
		this.moreFiveYearD=new JTextField();
		this.depositPrepare=new JLabel("存款准备金:");
		this.depositPrepareL=new JTextField();
		this.depositPrepareD=new JTextField();
		
		
		this.oneTofiveRecycleRateText = new JLabel("1-5年贷款可收回金额：");
		this.oneTofiveRecycleRate = new JTextField(5);
		this.moreThanFiveRecycleRateText = new JLabel("5年以上贷款可收回金额：");
		this.moreThanFiveRecycleRate = new JTextField(5);
		
		
		this.save=new JButton("保存");
		
		this.setOpaque(false);
		this.panels=new JPanel[5];
		for(int i=0;i<5;i++){
			panels[i]=new JPanel();
			panels[i].setOpaque(false);
		}
	}
	public void init() throws SQLException{
		this.setBounds(0, 0, 400, 400);
		for(int i=0;i<5;i++)
			this.add(panels[i]);
		
		panels[0].add(prompt);
		panels[1].add(bankl);
		panels[1].add(bankt);
		panels[1].add(yearl);
		panels[1].add(yeart);
		
		panels[2].setLayout(new GridLayout(5,3));
		panels[2].add(new JLabel("  "));
		panels[2].add(liquidLoan);
		panels[2].add(liquidDeposit);
		panels[2].add(lessThreeMonth);
		panels[2].add(lessThreeMonthL);
		panels[2].add(lessThreeMonthD);
		panels[2].add(moreThreeMonth);
		panels[2].add(moreThreeMonthL);
		panels[2].add(moreThreeMonthD);
		panels[2].add(lessFiveYear);
		panels[2].add(lessFiveYearL);
		panels[2].add(lessFiveYearD);
		panels[2].add(moreFiveYear);
		panels[2].add(moreFiveYearL);
		panels[2].add(moreFiveYearD);
	//	panels[2].add(depositPrepare);
	//	panels[2].add(depositPrepareL);
	//	panels[2].add(depositPrepareD);
		
		panels[3].setLayout(new GridLayout(3,2));
		panels[3].add(depositPrepare);
		panels[3].add(depositPrepareD);
		panels[3].add(oneTofiveRecycleRateText);
		panels[3].add(oneTofiveRecycleRate);
		panels[3].add(moreThanFiveRecycleRateText);
		panels[3].add(moreThanFiveRecycleRate);
		
		panels[4].add(save);
		
		loadBYdata();
		
		save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (checkValid()) {
					BankShortDataDao shortDataDao=new BankShortDataDaoImpl();
					try {
						shortDataDao.insertShortData(getBankShortData());
						JOptionPane.showMessageDialog(null, "添加成功");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "添加失败");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					//	JOptionPane.showMessageDialog(null, "添加");
					}
				}else {
					JOptionPane.showMessageDialog(null, "输入格式有误");
				}
			}
		});
		
	}
	public boolean checkValid(){
		try {
			getBankShortData();
						
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}
	
	public void loadBYdata() throws SQLException{
		BankDao bankDao = new BankDaoImpl();
		List<Bank> banks = bankDao.getAllBank();
		// JOptionPane.showMessageDialog(null, "输入的数据不能为空");
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
	
	public BankShortData getBankShortData() throws SQLException{
		BankShortData bank=new BankShortData();
		bank.setBid(new BankDaoImpl().getBankIdByName(bankt.getSelectedItem().toString()));
		bank.setYear(Integer.valueOf(yeart.getSelectedItem().toString()));
		bank.setLess_fivy_b(Double.valueOf(lessFiveYearL.getText().toString()));
		bank.setLess_fivy_s(Double.valueOf(lessFiveYearD.getText().toString()));
		bank.setMore_fivy_b(Double.valueOf(moreFiveYearL.getText().toString()));
		bank.setMore_fivy_s(Double.valueOf(moreFiveYearD.getText().toString()));
		bank.setLess_thrm_b(Double.valueOf(lessThreeMonthL.getText().toString()));
		bank.setLess_thrm_s(Double.valueOf(lessThreeMonthD.getText().toString()));
		bank.setLess_twim_b(Double.valueOf(moreThreeMonthL.getText().toString()));
		bank.setLess_twim_s(Double.valueOf(moreThreeMonthD.getText().toString()));
		
		bank.setSr_mon(Double.valueOf(depositPrepareD.getText().toString()));
		bank.setLess_fiveloan_recycle(Double.valueOf(oneTofiveRecycleRate.getText().toString()));
		bank.setMore_fiveloan_recycle(Double.valueOf(moreThanFiveRecycleRate.getText().toString()));
		
		
		return bank;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
