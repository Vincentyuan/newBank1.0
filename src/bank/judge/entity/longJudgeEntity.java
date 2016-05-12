package bank.judge.entity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.entity.JudgeRecord;
import bank.entity.Year;
import bank.frame.ClientContext;
import bank.judge.Judge;
import bank.judge.MaxMinValue;
import bank.util.Jdbc;

public class longJudgeEntity {

	private String bankname;
	private int year;
	private String longJudgeDeposit;
	private String longJudgeLoan;

	public longJudgeEntity() {
		// TODO Auto-generated constructor stub
	}

	public JudgeRecord toLongJudgeRecord() {
		// TODO Auto-generated method stub
		JudgeRecord record = new JudgeRecord();
		record.setBankname(bankname);
		record.setYear(year);
		record.setJudger(ClientContext.name);
		record.setTime(new Timestamp(System.currentTimeMillis()));
		record.setType("long");
		record.setDeposit(longJudgeDeposit);
		record.setLoan(longJudgeLoan);

		return record;
	}

	/**
	 * @return the bankname
	 */
	public String getBankname() {
		return bankname;
	}

	/**
	 * @param bankname
	 *            the bankname to set
	 */
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the longJudgeDeposit
	 */
	public String getLongJudgeDeposit() {
		return longJudgeDeposit;
	}

	/**
	 * @param longJudgeDeposit
	 *            the longJudgeDeposit to set
	 */
	public void setLongJudgeDeposit(String longJudgeDeposit) {
		this.longJudgeDeposit = longJudgeDeposit;
	}

	/**
	 * @return the longJudgeLoan
	 */
	public String getLongJudgeLoan() {
		return longJudgeLoan;
	}

	/**
	 * @param longJudgeLoan
	 *            the longJudgeLoan to set
	 */
	public void setLongJudgeLoan(String longJudgeLoan) {
		this.longJudgeLoan = longJudgeLoan;
	}

	public void getLongJudge() throws Exception { // ������ʹ�����߼�����������
		
		BankLongDataDao l = new BankLongDataImpl();
		BankLongData bank = l.getBankLongDataByBY(bankname, year);

		// System.out.println("gap:"+Judge.gapLongDeposit(bank)+"loan"+Judge.gapLongLoan(bank));

		MaxMinValue.getLongDepositMaxMin(year);
		MaxMinValue.getLongLoanMaxMin(year);

		/* System.out.println("max DEPOSIT"
		 +MaxMinValue.maxLongDeposit+" min "+MaxMinValue.minLongDeposit);
		 System.out.println("max LOAN"
		 +MaxMinValue.maxLongLoan+" min "+MaxMinValue.minLongLoan);
*/
		// �[0,0.2�����У�[0.2,0.4)������[0.4,0.6)�����ţ�[0.6,0.8)���ţ�[0.8,1]
		double longDeposit = (Judge.gapLongDeposit(bank) - MaxMinValue.minLongDeposit)
				/ (MaxMinValue.maxLongDeposit - MaxMinValue.minLongDeposit);

		double longLoan = (Judge.gapLongLoan(bank) - MaxMinValue.minLongLoan)
				/ (MaxMinValue.maxLongLoan - MaxMinValue.minLongLoan);
		/*System.out.println("ah"+Judge.gapLongDeposit(bank));
		System.out.println("ap"+Judge.gapLongLoan(bank));
		System.out.println(this.year+" "+this.bankname+": ���"+longDeposit);
		System.out.println(this.year+" "+this.bankname+": ����"+longLoan);*/

		setLongJudgeDeposit(getDegree(longDeposit));
		setLongJudgeLoan(getDegree(longLoan));

	}

	public String getDegree(double n) {

		if (n >= 0.8) {
			return "��";
		} else if (n >= 0.6) {
			return "����";
		} else if (n >= 0.4) {
			return "��";
		} else if (n >= 0.2) {
			return "��";
		} else {
			return "��";
		}
	}

	public double[] getDepositResultRange() throws Exception {

		YearDao yearDao = new YearDaoImpl();

		double[] result = new double [yearDao.getAllYear().size()];
		// System.out.println(yearDao.getAllYear().size());
		// ��ȡ��С��ݡ�tmp �±�Ϊ0 ��Ӧ��ֵΪ��С��ݵ�ֵ��

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		BankLongDataDao longDataDao = new BankLongDataImpl();
		

		for (int t = 0; t < result.length; t++)// ���Ľ��
		{
			BankLongData choose = longDataDao.getBankLongDataByBY(bankname,
					minYear + t);
			result[t]=Judge.gapLongDeposit(choose);
		}

		return result;
	}

	public double[] getLoanResultRange() throws Exception {

		YearDao yearDao = new YearDaoImpl();

		double[] result = new double [yearDao.getAllYear().size()];
		// System.out.println(yearDao.getAllYear().size());
		// ��ȡ��С��ݡ�tmp �±�Ϊ0 ��Ӧ��ֵΪ��С��ݵ�ֵ��

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		BankLongDataDao longDataDao = new BankLongDataImpl();
		 

		for (int t = 0; t < result.length; t++)// ���Ľ��
		{
			BankLongData choose = longDataDao.getBankLongDataByBY(bankname,
					minYear + t);
			result[t]=Judge.gapLongLoan(choose);
			/*if (minYear+t==2014) {
				System.out.println("2014 result :"+result[t]);
			}*/
		}

		return result;
	}

	public int[] getRangeDeposit() throws Exception {
		YearDao yearDao = new YearDaoImpl();

		int[] tmp = new int[yearDao.getAllYear().size()];
		// System.out.println(yearDao.getAllYear().size());
		// ��ȡ��С��ݡ�tmp �±�Ϊ0 ��Ӧ��ֵΪ��С��ݵ�ֵ��

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		// ��ȡ���е����м�¼��

		BankLongDataDao longDataDao = new BankLongDataImpl();
		List<BankLongData> longDatas = new ArrayList<>();

		for (int t = 0; t < tmp.length; t++)// ���Ľ��
		{
			int range = 1;
			// ������ڽ��м��㣬��������ڣ���������
			// ����ݣ�������ȡ���� minYear+t=���ڵ���ݡ�
			longDatas = longDataDao.getAllBankLongDataByYear(minYear + t);
			// System.out.println(bankname+"  bankname "+"year"+hello);

			BankLongData choose = longDataDao.getBankLongDataByBY(bankname,
					minYear + t);
			double nowRadio = Judge.gapLongDeposit(choose);

			for (BankLongData bankLongData : longDatas) {
				if (Judge.gapLongDeposit(bankLongData) > nowRadio)
					range++;
			}

			tmp[t] = range;
		}

		return tmp;
	}

	public int[] getRangeLoan() throws Exception {
		YearDao yearDao = new YearDaoImpl();

		int[] tmp = new int[yearDao.getAllYear().size()];
		// System.out.println(yearDao.getAllYear().size());
		// ��ȡ��С��ݡ�tmp �±�Ϊ0 ��Ӧ��ֵΪ��С��ݵ�ֵ��

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		// ��ȡ���е����м�¼��

		BankLongDataDao longDataDao = new BankLongDataImpl();
		List<BankLongData> longDatas = new ArrayList<>();

		for (int t = 0; t < tmp.length; t++)// ���Ľ��
		{
			int range = 1;
			// ������ڽ��м��㣬��������ڣ���������
			// ����ݣ�������ȡ���� minYear+t=���ڵ���ݡ�
			longDatas = longDataDao.getAllBankLongDataByYear(minYear + t);
			// System.out.println(bankname+"  bankname "+"year"+hello);

			BankLongData choose = longDataDao.getBankLongDataByBY(bankname,
					minYear + t);
			double nowRadio = Judge.gapLongLoan(choose);

			for (BankLongData bankLongData : longDatas) {
				if (Judge.gapLongLoan(bankLongData) > nowRadio)
					range++;
			}

			tmp[t] = range;
		}

		return tmp;
	}

	public String[] getRangeGradeDeposit() throws Exception {

		YearDao yearDao = new YearDaoImpl();
		String[] rangeGrade = new String[yearDao.getAllYear().size()];

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		longJudgeEntity tmp = new longJudgeEntity();

		for (int t = 0; t < rangeGrade.length; t++)// ���Ľ��
		{
			// ������ڽ��м��㣬��������ڣ���������
			// ����ݣ�������ȡ���� minYear+t=���ڵ���ݡ�

			tmp.setBankname(bankname);
			tmp.setYear(minYear + t);
			tmp.getLongJudge();

			String grade = tmp.getLongJudgeDeposit();

			rangeGrade[t] = grade;
		}

		return rangeGrade;
	}

	public String[] getRangeGradeLoan() throws Exception {

		YearDao yearDao = new YearDaoImpl();
		String[] rangeGrade = new String[yearDao.getAllYear().size()];

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		longJudgeEntity tmp = new longJudgeEntity();

		for (int t = 0; t < rangeGrade.length; t++)// ���Ľ��
		{
			// ������ڽ��м��㣬��������ڣ���������
			// ����ݣ�������ȡ���� minYear+t=���ڵ���ݡ�

			tmp.setBankname(bankname);
			tmp.setYear(minYear + t);
			tmp.getLongJudge();

			String grade = tmp.getLongJudgeLoan();

			rangeGrade[t] = grade;
		}

		return rangeGrade;
	}

}
