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
 * 重写过得set 函数有：故这两个参数必须传入
 * 
 * setbankDegree（） 初始化p1 和p2 
 * 
 * setYear（） 同时初始化r1 和r2
 * 
 */
public class shortJudgeEntity {

	private String bankName;
	private int year;

	private double moreThanOneWithdrowRate;

	private double recycleRate;

	private double paymentRate;

	private double p1, p2;// 违约率，由银行信用等级获得
	private double r1, r2;// 3年和5年期国债利率

	private double badRate, goodRate, betterRate;

	private String bankDegree;

	private String shortJudgeResult;

	
	//默认构造函数
	public shortJudgeEntity() {
		// TODO Auto-generated constructor stub
	}

	// 转成报表数据格式。
	public JudgeRecord toShortJudgeRecord() throws SQLException {
		
		JudgeRecord record = new JudgeRecord();
		
		record.setBankname(this.bankName);
		record.setJudger(ClientContext.name);
		record.setTime(new Timestamp(System.currentTimeMillis()));
		record.setType("short");
		record.setYear(year);
		record.setShortrate(getShortJudgeResult());// get short judge degree
		record.setShortRange(getNumberRange());//获取当年的排名

		return record;
	}

	// 获取该银行在本年份 this.year的稳定性的排名
	public int getNumberRange() throws SQLException {
		// 获取年内的排名
		BankShortDataDao dao = new BankShortDataDaoImpl();
		List<BankShortData> banks = dao.getShortBankDataByYear(year);
		BankShortData currentData = dao.getShortBankDataByBY(this.bankName,
				this.year);

		int range = 1;// 稳定性结果小于本年份的具体银行，则range不变，若大于这range+1
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

	// 评级归一化结果值。
	public double getJudgeNormalization() throws SQLException {

		// check whether max and min has been set？？

		BankShortDataDao tmp = new BankShortDataDaoImpl();
		BankShortData bank = tmp.getShortBankDataByBY(bankName, year);
		double normalization = (Judge.gapShort(bank, this) - MaxMinValue.minShort)
				/ (MaxMinValue.maxShort - MaxMinValue.minShort) - 1;
		return normalization;
	}

	// 获得评级等级
	public void getJudgeDegree() throws SQLException {

		double normalization = getJudgeNormalization();
		// 差：[-1,-0.8）；中：[-0.8,-0.6)；良：[-0.6,-0.4)；较优：[-0.4,-0.2)；优：[-0.2,0]
		if (normalization >= -0.2) {
			this.shortJudgeResult = "优";
		} else if (normalization >= -0.4) {
			this.shortJudgeResult = "较优";
		} else if (normalization >= -0.6) {
			this.shortJudgeResult = "良";
		} else if (normalization >= -0.8) {
			this.shortJudgeResult = "中";
		} else {
			this.shortJudgeResult = "差";
		}

	}

	// 评级结果写入字段shortjudgeresult中。
	public void setShortJudgeResult() throws Exception {
		// 计算短期评级。
		BankShortDataDao tmp = new BankShortDataDaoImpl();
		BankShortData bank = tmp.getShortBankDataByBY(bankName, year);

		MaxMinValue.getShortMaxMin(this);// 获取本年度的缺口率最大最小值。
		// 归一化
		double normalization = (Judge.gapShort(bank, this) - MaxMinValue.minShort)
				/ (MaxMinValue.maxShort - MaxMinValue.minShort) - 1;

	//	System.out.println(this.getBankName()+this.getYear()+"  normalization"+normalization);
		// 差：[-1,-0.8）；中：[-0.8,-0.6)；良：[-0.6,-0.4)；较优：[-0.4,-0.2)；优：[-0.2,0]
		if (normalization >= -0.2) {
			this.shortJudgeResult = "优";
		} else if (normalization >= -0.4) {
			this.shortJudgeResult = "较优";
		} else if (normalization >= -0.6) {
			this.shortJudgeResult = "良";
		} else if (normalization >= -0.8) {
			this.shortJudgeResult = "中";
		} else {
			this.shortJudgeResult = "差";
		}

	}

	// 获取每一年缺口率的排名。
	public int[] getRange() throws SQLException {
		YearDao yearDao = new YearDaoImpl();

		int[] tmp = new int[yearDao.getAllYear().size()];

		// 获取最小年份。tmp 下标为0 对应的值为最小年份的值。

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}


		BankShortDataDao shortDataDao = new BankShortDataDaoImpl();
		List<BankShortData> shortDatas = new ArrayList<>();

		for (int t = 0; t < tmp.length; t++)// 最后的结果
		{
			int range = 1;
			// 按年份，挨个获取银行 minYear+t=现在的年份。
			shortDatas = shortDataDao.getShortBankDataByYear(minYear + t);
			
			BankShortData choose = shortDataDao.getShortBankDataByBY(bankName,
					minYear + t);
			
			double nowRadio = Judge.gapShort(choose, this);//当前银行minyear+t年份的缺口率
			
			//计算在minyear +t 的排名
			for (BankShortData bankShortData : shortDatas) {
				if (Judge.gapShort(bankShortData, this) > nowRadio)
					range++;
			}

			tmp[t] = range;
		}

		return tmp;
	}

	// 获取每一年的评级等级
	
	//不复用，getNumberRange方法，原因在于该方法需要用到本类的成员变量  ？？
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

		for (int t = 0; t < rangeGrade.length; t++)// 最后的结果
		{
			// 按年份，挨个获取银行 minYear+t=现在的年份。

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
	 * setYear（） 同时初始化r1 和r2
	 */
	public void setYear(String year) throws SQLException {
		this.year = Integer.parseInt(year);
		// 初始化r1和r2
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
	 * setbankDegree（） 初始化p1 和p2
	 */
	public void setBankDegree(String bankDegree) throws SQLException {
		this.bankDegree = bankDegree;
		// 初始化p1和p2
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
