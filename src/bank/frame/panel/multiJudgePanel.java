package bank.frame.panel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.DRateDao;
import bank.dao.DRateDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.DRate;
import bank.entity.JudgeRecord;
import bank.entity.Year;
import bank.frame.ClientContext;
import bank.frame.MainFrame;
import bank.judge.entity.longJudgeEntity;
import bank.judge.entity.shortJudgeEntity;

public class multiJudgePanel extends workPanel {

	private JLabel bank, year;
	private JComboBox bankt, yeart;

	private JLabel moreThanOneWithdrowText;
	private JTextField moreThanOneWithdrowRate;

	private JLabel bankDegreeText;
	private JComboBox bankDegree;

	private JLabel recycleRateText;
	private JTextField recycleRate;

	/*
	 * private JLabel oneTofiveRecycleRateText; private JTextField
	 * oneTofiveRecycleRate;
	 * 
	 * private JLabel moreThanFiveRecycleRateText; private JTextField
	 * moreThanFiveRecycleRate;
	 */
	private JLabel paymentRateText;
	private JTextField paymentRate;

	private JButton tendency, assess;

	private JPanel head, mid, bottom;

	private MainFrame frame;

	private static multiJudgePanel multiJudgePanel;

	public multiJudgePanel(MainFrame frame) {
		// TODO Auto-generated constructor stub

		// this.frame=new MainFrame(frame.getClientContext());
		this.frame = frame;

		this.bank = new JLabel("银行：");
		this.year = new JLabel("年份：");
		this.bankt = new JComboBox();
		this.yeart = new JComboBox();

		moreThanOneWithdrowText = new JLabel("1年以上的中长期存款的提前支取比例：");
		moreThanOneWithdrowRate = new JTextField(5);
		bankDegreeText = new JLabel("银行贷款准入登记：");
		bankDegree = new JComboBox();
		recycleRateText = new JLabel("通过清收等手段部分账款的回收率：");
		recycleRate = new JTextField(5);

		/*
		 * oneTofiveRecycleRateText = new JLabel("1-5年贷款可收回金额：");
		 * oneTofiveRecycleRate = new JTextField(5); moreThanFiveRecycleRateText
		 * = new JLabel("5年以上贷款可收回金额："); moreThanFiveRecycleRate = new
		 * JTextField(5);
		 */

		paymentRateText = new JLabel("买入贷款方支付资金比例：");
		paymentRate = new JTextField(5);

		this.assess = new JButton("评估");
		this.tendency = new JButton("清除");

		this.mid = new JPanel();
		mid.setOpaque(false);
		this.bottom = new JPanel();
		bottom.setOpaque(false);
		this.head = new JPanel();
		head.setOpaque(false);
	}

	public void init() throws Exception {
		this.setOpaque(false);
		this.setBounds(0, 0, 400, 400);
		this.add(head);
		this.add(mid);
		this.add(bottom);

		this.add(head);
		head.setBounds(30, 30, 100, 100);
		head.add(bank);
		head.add(bankt);
		head.add(year);
		head.add(yeart);

		this.add(mid);
		mid.setLayout(new GridLayout(4, 2));
		// mid.setBounds(0, 0, 400, 400);
		mid.add(moreThanOneWithdrowText);
		mid.add(moreThanOneWithdrowRate);
		mid.add(bankDegreeText);
		mid.add(bankDegree);
		mid.add(recycleRateText);
		mid.add(recycleRate);

		/*
		 * mid.add(oneTofiveRecycleRateText); mid.add(oneTofiveRecycleRate);
		 * mid.add(moreThanFiveRecycleRateText);
		 * mid.add(moreThanFiveRecycleRate);
		 */

		mid.add(paymentRateText);
		mid.add(paymentRate);

		this.add(bottom);
		bottom.setBounds(0, 0, 100, 100);
		bottom.add(tendency);
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
					if (checkValid()) {
						frame.getWorkPanel().removeAll();

						frame.setBorderlayout(new multiJudgeReport(
								getMultiJudgeReportData()));

						frame.revalidate();
						frame.repaint();
					} else {
						JOptionPane.showMessageDialog(null, "输入有误，或银行、年份数据不存在");
					}
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "输入有误,或短期/长期对应数据缺失");
				}
				
			}
		});
		tendency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// oneTofiveRecycleRate.setText("");
				recycleRate.setText("");
				// moreThanFiveRecycleRate.setText("");
				moreThanOneWithdrowRate.setText("");
				paymentRate.setText("");
			}
		});
	}

	public void fillBankYear() throws Exception {
		BankDao bankDao = new BankDaoImpl();
		List<Bank> banks = bankDao.getAllBank();
		// JOptionPane.showMessageDialog(null, "输入的数据不能为空");
		for (Bank bank : banks) {
			bankt.addItem(bank.getName());
		}
		YearDao yearDao = new YearDaoImpl();
		List<Year> yearAll = yearDao.getAllYear();

		for (Year year : yearAll) {
			yeart.addItem(year.getYear());
		}
		DRateDao dRateDao = new DRateDaoImpl();
		List<DRate> dRates = dRateDao.getAllDRates();
		for (DRate d : dRates) {
			bankDegree.addItem(d.getName());
		}
	}

	public JudgeRecord getLongJudgeReportData() throws Exception {
		longJudgeEntity l = new longJudgeEntity();
		l.setBankname(bankt.getSelectedItem().toString());
		l.setYear(Integer.valueOf(yeart.getSelectedItem().toString()));
		l.getLongJudge();

		return l.toLongJudgeRecord();
	}

	public JudgeRecord getMultiJudgeReportData() throws Exception {
		JudgeRecord multi = new JudgeRecord();

		JudgeRecord s = getShortJudgeReportData();
		JudgeRecord l = getLongJudgeReportData();
		multi = l;
		multi.setShortrate(s.getShortrate());
		multi.setType("multi");
		String[] tmpStrings = { "优", "较优", "良", "中", "差" };

		int deposit = -1;
		int loan = -1;

		for (int i = 0; i < tmpStrings.length; i++) {
			if (l.getDeposit().equals(tmpStrings[i]))
				deposit = i + 1;
			if (l.getLoan().equals(tmpStrings[i]))
				loan = i + 1;
		}

		multi.setMultideposit(s.getShortrate() + deposit);
		multi.setMultiloan(s.getShortrate() + loan);

		return multi;
	}

	public JudgeRecord getShortJudgeReportData() throws Exception {

		return getUserInput().toShortJudgeRecord();

	}

	public shortJudgeEntity getUserInput() throws Exception {
		shortJudgeEntity entity = new shortJudgeEntity();

		entity.setBankName(bankt.getSelectedItem().toString());
		entity.setYear(yeart.getSelectedItem().toString());
		entity.setBankDegree(bankDegree.getSelectedItem().toString());

		entity.setMoreThanOneWithdrowRate(Double.valueOf(
				moreThanOneWithdrowRate.getText()).doubleValue());
		entity.setRecycleRate(Double.valueOf(recycleRate.getText())
				.doubleValue());
		// entity.setOneTofiveRecycleRate(Double.valueOf(oneTofiveRecycleRate.getText()).doubleValue());
		// entity.setMoreThanFiveRecycleRate(Double.valueOf(moreThanFiveRecycleRate.getText()).doubleValue());
		entity.setPaymentRate(Double.valueOf(paymentRate.getText())
				.doubleValue());

		entity.setShortJudgeResult();
		return entity;
		/*
		 * shortJudgeEntity e=new shortJudgeEntity(); e.setBankName("中国工商银行");
		 * e.setYear("2006"); e.setBankDegree("A");
		 * e.setMoreThanOneWithdrowRate(53108.94);
		 * e.setMoreThanFiveRecycleRate(56778.96); e.setPaymentRate(0.7);
		 * e.setShortJudgeResult(); return e;
		 */
	}

	public boolean checkValid() {
		try {
			getUserInput();
			getShortJudgeReportData();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static multiJudgePanel getInstance(MainFrame frame) {
		if (multiJudgePanel == null) {
			return new multiJudgePanel(frame);
		} else {
			return multiJudgePanel;
		}
	}

}
