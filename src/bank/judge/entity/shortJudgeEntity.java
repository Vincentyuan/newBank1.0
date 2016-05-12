/**
 * 
 */
package bank.judge.entity;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.DRateDao;
import bank.dao.DRateDaoImpl;
import bank.dao.NatDebtRateDao;
import bank.dao.NatDebtRateImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankShortData;
import bank.entity.DRate;
import bank.entity.JudgeRecord;
import bank.entity.NatDebtRate;
import bank.entity.Year;
import bank.frame.ClientContext;
import bank.judge.Judge;
import bank.judge.MaxMinValue;

/**
 * @author yuanping
 * 
 * ��д����set �����У����������������봫��
 * 
 * setbankDegree���� ��ʼ��p1 ��p2 
 * 
 * setYear���� ͬʱ��ʼ��r1 ��r2
 * 
 */
public class shortJudgeEntity {

	private String bankName;
	private int year;

	private double moreThanOneWithdrowRate;

	private double recycleRate;

	private double paymentRate;

	private double p1, p2;// ΥԼ�ʣ����������õȼ����
	private double r1, r2;// 3���5���ڹ�ծ����

	private double badRate, goodRate, betterRate;

	private String bankDegree;

	private String shortJudgeResult;

	
	//Ĭ�Ϲ��캯��
	public shortJudgeEntity() {
		// TODO Auto-generated constructor stub
	}

	// ת�ɱ������ݸ�ʽ��
	public JudgeRecord toShortJudgeRecord() throws SQLException {
		
		JudgeRecord record = new JudgeRecord();
		
		record.setBankname(this.bankName);
		record.setJudger(ClientContext.name);
		record.setTime(new Timestamp(System.currentTimeMillis()));
		record.setType("short");
		record.setYear(year);
		record.setShortrate(getShortJudgeResult());// get short judge degree
		record.setShortRange(getNumberRange());//��ȡ���������

		return record;
	}

	// ��ȡ�������ڱ���� this.year���ȶ��Ե�����
	public int getNumberRange() throws SQLException {
		// ��ȡ���ڵ�����
		BankShortDataDao dao = new BankShortDataDaoImpl();
		List<BankShortData> banks = dao.getShortBankDataByYear(year);
		BankShortData currentData = dao.getShortBankDataByBY(this.bankName,
				this.year);

		int range = 1;// �ȶ��Խ��С�ڱ���ݵľ������У���range���䣬��������range+1
		for (BankShortData bankShortData : banks) {
			if (Judge.gapShort(currentData, this) < (Judge.gapShort(
					bankShortData, this))) {
				range++;
			}
		}
		return range;

	}
	
	public double getGapResult() throws SQLException{
		
		BankShortDataDao tmp = new BankShortDataDaoImpl();
		BankShortData bank = tmp.getShortBankDataByBY(bankName, year);
		return Judge.gapShort(bank, this);
	}

	// ������һ�����ֵ��
	public double getJudgeNormalization() throws SQLException {

		// check whether max and min has been set����

		BankShortDataDao tmp = new BankShortDataDaoImpl();
		BankShortData bank = tmp.getShortBankDataByBY(bankName, year);
		double normalization = (Judge.gapShort(bank, this) - MaxMinValue.minShort)
				/ (MaxMinValue.maxShort - MaxMinValue.minShort) - 1;
		return normalization;
	}

	// ��������ȼ�
	public void getJudgeDegree() throws SQLException {

		double normalization = getJudgeNormalization();
		// �[-1,-0.8�����У�[-0.8,-0.6)������[-0.6,-0.4)�����ţ�[-0.4,-0.2)���ţ�[-0.2,0]
		if (normalization >= -0.2) {
			this.shortJudgeResult = "��";
		} else if (normalization >= -0.4) {
			this.shortJudgeResult = "����";
		} else if (normalization >= -0.6) {
			this.shortJudgeResult = "��";
		} else if (normalization >= -0.8) {
			this.shortJudgeResult = "��";
		} else {
			this.shortJudgeResult = "��";
		}

	}

	// �������д���ֶ�shortjudgeresult�С�
	public void setShortJudgeResult() throws Exception {
		// �������������
		BankShortDataDao tmp = new BankShortDataDaoImpl();
		BankShortData bank = tmp.getShortBankDataByBY(bankName, year);

		MaxMinValue.getShortMaxMin(this);// ��ȡ����ȵ�ȱ���������Сֵ��
		// ��һ��
		double normalization = (Judge.gapShort(bank, this) - MaxMinValue.minShort)
				/ (MaxMinValue.maxShort - MaxMinValue.minShort) - 1;

	//	System.out.println(this.getBankName()+this.getYear()+"  normalization"+normalization);
		// �[-1,-0.8�����У�[-0.8,-0.6)������[-0.6,-0.4)�����ţ�[-0.4,-0.2)���ţ�[-0.2,0]
		if (normalization >= -0.2) {
			this.shortJudgeResult = "��";
		} else if (normalization >= -0.4) {
			this.shortJudgeResult = "����";
		} else if (normalization >= -0.6) {
			this.shortJudgeResult = "��";
		} else if (normalization >= -0.8) {
			this.shortJudgeResult = "��";
		} else {
			this.shortJudgeResult = "��";
		}

	}

	// ��ȡÿһ��ȱ���ʵ�������
	public int[] getRange() throws SQLException {
		YearDao yearDao = new YearDaoImpl();

		int[] tmp = new int[yearDao.getAllYear().size()];

		// ��ȡ��С��ݡ�tmp �±�Ϊ0 ��Ӧ��ֵΪ��С��ݵ�ֵ��

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}


		BankShortDataDao shortDataDao = new BankShortDataDaoImpl();
		List<BankShortData> shortDatas = new ArrayList<>();

		for (int t = 0; t < tmp.length; t++)// ���Ľ��
		{
			int range = 1;
			// ����ݣ�������ȡ���� minYear+t=���ڵ���ݡ�
			shortDatas = shortDataDao.getShortBankDataByYear(minYear + t);
			
			BankShortData choose = shortDataDao.getShortBankDataByBY(bankName,
					minYear + t);
			
			double nowRadio = Judge.gapShort(choose, this);//��ǰ����minyear+t��ݵ�ȱ����
			
			//������minyear +t ������
			for (BankShortData bankShortData : shortDatas) {
				if (Judge.gapShort(bankShortData, this) > nowRadio)
					range++;
			}

			tmp[t] = range;
		}

		return tmp;
	}

	// ��ȡÿһ��������ȼ�
	
	//�����ã�getNumberRange������ԭ�����ڸ÷�����Ҫ�õ�����ĳ�Ա����  ����
	public String[] getRangeGrade() throws Exception {

		YearDao yearDao = new YearDaoImpl();
		String[] rangeGrade = new String[yearDao.getAllYear().size()];

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		shortJudgeEntity tmp = new shortJudgeEntity();

		for (int t = 0; t < rangeGrade.length; t++)// ���Ľ��
		{
			// ����ݣ�������ȡ���� minYear+t=���ڵ���ݡ�

			tmp.setBankName(this.bankName);
			tmp.setYear(String.valueOf(minYear + t));

			tmp.setBankDegree(this.bankDegree);

			tmp.setMoreThanOneWithdrowRate(this.moreThanOneWithdrowRate);
			tmp.setRecycleRate(this.recycleRate);
			tmp.setPaymentRate(this.paymentRate);

			tmp.setShortJudgeResult();

			String grade = tmp.getShortJudgeResult();

			rangeGrade[t] = grade;
		}

		return rangeGrade;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the p1
	 */
	public double getP1() {
		return p1;
	}

	/**
	 * @param p1
	 *            the p1 to set
	 */
	public void setP1(double p1) {
		this.p1 = p1;
	}

	/**
	 * @return the p2
	 */
	public double getP2() {
		return p2;
	}

	/**
	 * @param p2
	 *            the p2 to set
	 */
	public void setP2(double p2) {
		this.p2 = p2;
	}

	/**
	 * @return the r1
	 */
	public double getR1() {
		return r1;
	}

	/**
	 * @param r1
	 *            the r1 to set
	 */
	public void setR1(double r1) {
		this.r1 = r1;
	}

	/**
	 * @return the r2
	 */
	public double getR2() {
		return r2;
	}

	/**
	 * @param r2
	 *            the r2 to set
	 */
	public void setR2(double r2) {
		this.r2 = r2;
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
	 * @throws SQLException
	 * 
	 * setYear���� ͬʱ��ʼ��r1 ��r2
	 */
	public void setYear(String year) throws SQLException {
		this.year = Integer.parseInt(year);
		// ��ʼ��r1��r2
		NatDebtRateDao tmp = new NatDebtRateImpl();
		NatDebtRate tDebtRate = new NatDebtRate();
		tDebtRate = tmp.getNatDebtRateByYear(Integer.parseInt(year));

		this.r1 = tDebtRate.getThreeyearrate();
		this.r2 = tDebtRate.getFiveyearrate();
	}

	/**
	 * @return the moreThanOneWithdrowRate
	 */
	public double getMoreThanOneWithdrowRate() {
		return this.moreThanOneWithdrowRate;
	}

	/**
	 * @param moreThanOneWithdrowRate
	 *            the moreThanOneWithdrowRate to set
	 */
	public void setMoreThanOneWithdrowRate(double moreThanOneWithdrowRate) {
		this.moreThanOneWithdrowRate = moreThanOneWithdrowRate;

	}

	/**
	 * @return the bankDegree
	 */
	public String getBankDegree() {
		return bankDegree;
	}

	/**
	 * @param bankDegree
	 *            the bankDegree to set
	 * @throws SQLException
	 * 
	 * setbankDegree���� ��ʼ��p1 ��p2
	 */
	public void setBankDegree(String bankDegree) throws SQLException {
		this.bankDegree = bankDegree;
		// ��ʼ��p1��p2
		DRateDao tmpDao = new DRateDaoImpl();
		DRate tDRate = new DRate();
		tDRate = tmpDao.getDRateByDegree(bankDegree);
		this.p1 = tDRate.getP1();
		this.p2 = tDRate.getP2();
	}

	/**
	 * @return the recycleRate
	 */
	public double getRecycleRate() {
		return recycleRate;
	}

	/**
	 * @param recycleRate
	 *            the recycleRate to set
	 */
	public void setRecycleRate(double recycleRate) {
		this.recycleRate = recycleRate;
	}

	/**
	 * @return the paymentRate
	 */
	public double getPaymentRate() {
		return paymentRate;
	}

	/**
	 * @param paymentRate
	 *            the paymentRate to set
	 */
	public void setPaymentRate(double paymentRate) {
		this.paymentRate = paymentRate;
	}

	public void setPaymentRate1(double paymentRate) {
		this.paymentRate = paymentRate;
	}

	/**
	 * @return the badRate
	 */
	public double getBadRate() {
		return badRate;
	}

	/**
	 * @param badRate
	 *            the badRate to set
	 */
	public void setBadRate(double badRate) {
		this.badRate = badRate;
	}

	/**
	 * @return the goodRate
	 */
	public double getGoodRate() {
		return goodRate;
	}

	/**
	 * @param goodRate
	 *            the goodRate to set
	 */
	public void setGoodRate(double goodRate) {
		this.goodRate = goodRate;
	}

	/**
	 * @return the betterRate
	 */
	public double getBetterRate() {
		return betterRate;
	}

	/**
	 * @param betterRate
	 *            the betterRate to set
	 */
	public void setBetterRate(double betterRate) {
		this.betterRate = betterRate;
	}

	/**
	 * @return the shortJudgeResult
	 */
	public String getShortJudgeResult() {
		return shortJudgeResult;
	}

	/**
	 * @param year
	 *            the year to set
	 * @throws SQLException 
	 */
	public void setYear(int year) throws SQLException {
		this.year = year;
		NatDebtRateDao tmp = new NatDebtRateImpl();
		NatDebtRate tDebtRate = new NatDebtRate();
		tDebtRate = tmp.getNatDebtRateByYear(year);

		this.r1 = tDebtRate.getThreeyearrate();
		this.r2 = tDebtRate.getFiveyearrate();
	}

	/**
	 * @param shortJudgeResult
	 *            the shortJudgeResult to set
	 */
	public void setShortJudgeResult(String shortJudgeResult) {
		this.shortJudgeResult = shortJudgeResult;
	}

	/**
	 * @param shortJudgeResult
	 *            the shortJudgeResult to set
	 * @throws Exception
	 */

}
