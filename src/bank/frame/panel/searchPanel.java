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

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.JudgeRecord;
import bank.entity.Year;
import bank.frame.ClientContext;
import bank.frame.MainFrame;

public class searchPanel extends workPanel {

	
	private JLabel guide;
	private JLabel shortJudge;
	private JLabel longJudge;
	private JLabel multiJudge;
	private JComboBox bankBox;
	private JComboBox yearBox;
	
	private JLabel  bank;
	private JLabel  year;
	
	private JButton shortJudgeButton,multiJudgeButton,longJudgeButton;
	
	private JPanel head,mid,bottom;
	
	private MainFrame frame;
	
	private static searchPanel searchPanel;
	
	public searchPanel (MainFrame frame){
		
		//this.frame=new MainFrame(frame.getClientContext());
		this.frame=frame;
		
		guide=new JLabel("评估记录查询");
		
		shortJudgeButton=new JButton("短期评级记录");
		multiJudgeButton=new JButton("综合评级记录");
		longJudgeButton=new JButton("长期评级记录");
		
		
		shortJudge=new JLabel("短期稳定性评级:");
		longJudge=new JLabel("长期稳定性评级:");
		multiJudge=new JLabel("综合稳定性评级:");
		
		bankBox=new JComboBox();
		yearBox=new JComboBox();
		
		bank=new JLabel("银行");
		year=new JLabel("年份");
		
		
		head=new JPanel();
		head.setOpaque(false);
		mid=new JPanel();
		mid.setOpaque(false);
		bottom=new JPanel();
		bottom.setOpaque(false);
				
	}
	
	public void init() throws Exception{
		
		this.setOpaque(false);
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new GridLayout(3,1));
		this.add(head);
		this.add(mid);
		this.add(bottom);
		
		head.add(guide);
		
		//mid.setLayout(new GridLayout(3,4));
		
		mid.add(bank);
		mid.add(bankBox);
		mid.add(year);
		mid.add(yearBox);
		
		
		bottom.add(shortJudgeButton);
		bottom.add(longJudgeButton);
		bottom.add(multiJudgeButton);
		
			
		fillBankYear();
		addListener();
		
	}
	
	public void fillBankYear() throws Exception{
		BankDao bankDao=new BankDaoImpl();
		List<Bank> banks = bankDao.getAllBank();
		// JOptionPane.showMessageDialog(null, "输入的数据不能为空");
		for (Bank bank : banks) {
			bankBox.addItem(bank.getName());
		//	bankBox[1].addItem(bank.getName());
		//	bankBox[2].addItem(bank.getName());
		}
		YearDao yearDao=new YearDaoImpl();
		List<Year> yearAll = yearDao.getAllYear();

		for (Year year:yearAll) {
			yearBox.addItem(year.getYear());
		//	yearBox[1].addItem(year.getYear());
		//	yearBox[2].addItem(year.getYear());
		}
	}
	
	public void addListener( ){
		shortJudgeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getWorkPanel().removeAll();
				try {
					
					frame.setBorderlayout(new shortJudgeReport(getData("short")));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "您没有保存过该银行的评级记录");
					//重画界面。
					try {
						frame.changeToSearch();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}
				
				frame.revalidate();
				frame.repaint();
			}
		});
		longJudgeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getWorkPanel().removeAll();
				try {
					
					frame.setBorderlayout(new longJudgeReport(getData("long")));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "您没有保存过该银行的评级记录");
					try {
						frame.changeToSearch();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
				}
				frame.revalidate();
				frame.repaint();
				
			}
		});
		multiJudgeButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getWorkPanel().removeAll();
				try {
					frame.setBorderlayout(new multiJudgeReport(getData("multi")));
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
				//	e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "您没有保存过该银行的评级记录");
					try {
						frame.changeToSearch();
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}

				frame.revalidate();
				frame.repaint();
			}
		});
		
	}
	
	protected JudgeRecord getData(String type) throws SQLException {
		// TODO Auto-generated method stub
		String bankNameString=bankBox.getSelectedItem().toString();
		int year=Integer.valueOf(yearBox.getSelectedItem().toString());
		JudgeRecordDao tool=new JudgeRecordDaoImpl();
		JudgeRecord result=tool.getRecordByNY(type, year, bankNameString);
		
		return result;
	}

	protected JudgeRecord getMultiJudgeReportData() {
		// TODO Auto-generated method stub
		
		return null;
	}

	protected JudgeRecord getLongJudgeReportData() {
		// TODO Auto-generated method stub
		return null;
	}

	protected JudgeRecord getShortJudgeReportData() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFrame(MainFrame frame){
		this.frame=new MainFrame(frame.getClientContext());
	}
	public static searchPanel getInstance(MainFrame frame){
		if (searchPanel==null) {
			return new searchPanel(frame);
		}else {
			return searchPanel;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
