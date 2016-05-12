package bank.entity;

import java.sql.Date;
import java.sql.Timestamp;

public class JudgeRecord {

	private int id;
	private String type; //short,long,multy
	private String bankname;
	private int year;
	private String judger;
	
	private Timestamp time;
	private String deposit;
	private String loan;
	private String shortrate;
	private String multideposit;
	private String multiloan;
	
	private int shortRange,longRange;
	public JudgeRecord() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public JudgeRecord(String type, String bankname, int year, String judger,
			Timestamp time, String deposit, String loan, String shortrate,
			String multideposit, String multiloan,int shortRange,int longRange) {
		super();
		this.type = type;
		this.bankname = bankname;
		this.year = year;
		this.judger = judger;
		this.time = time;
		this.deposit = deposit;
		this.loan = loan;
		this.shortrate = shortrate;
		this.multideposit = multideposit;
		this.multiloan = multiloan;
		this.shortRange=shortRange;
		this.longRange=longRange;
	}




	public JudgeRecord(int id, String type, String bankname, int year,
			String judger, Timestamp time, String deposit, String loan,
			String shortrate, String multideposit, String multiloan) {
		super();
		this.id = id;
		this.type = type;
		this.bankname = bankname;
		this.year = year;
		this.judger = judger;
		this.time = time;
		this.deposit = deposit;
		this.loan = loan;
		this.shortrate = shortrate;
		this.multideposit = multideposit;
		this.multiloan = multiloan;
	}




	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the bankname
	 */
	public String getBankname() {
		return bankname;
	}

	/**
	 * @param bankname the bankname to set
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
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the judger
	 */
	public String getJudger() {
		return judger;
	}

	/**
	 * @param judger the judger to set
	 */
	public void setJudger(String judger) {
		this.judger = judger;
	}

	/**
	 * @return the time
	 */
	public Timestamp getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Timestamp time) {
		this.time = time;
	}

	/**
	 * @return the deposit
	 */
	public String getDeposit() {
		return deposit;
	}

	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	/**
	 * @return the loan
	 */
	public String getLoan() {
		return loan;
	}

	/**
	 * @param loan the loan to set
	 */
	public void setLoan(String loan) {
		this.loan = loan;
	}

	/**
	 * @return the shortrate
	 */
	public String getShortrate() {
		return shortrate;
	}

	/**
	 * @param shortrate the shortrate to set
	 */
	public void setShortrate(String shortrate) {
		this.shortrate = shortrate;
	}

	/**
	 * @return the multideposit
	 */
	public String getMultideposit() {
		return multideposit;
	}

	/**
	 * @param multideposit the multideposit to set
	 */
	public void setMultideposit(String multideposit) {
		this.multideposit = multideposit;
	}

	/**
	 * @return the multiloan
	 */
	public String getMultiloan() {
		return multiloan;
	}

	/**
	 * @param multiloan the multiloan to set
	 */
	public void setMultiloan(String multiloan) {
		this.multiloan = multiloan;
	}




	/**
	 * @return the shortRange
	 */
	public int getShortRange() {
		return shortRange;
	}




	/**
	 * @param shortRange the shortRange to set
	 */
	public void setShortRange(int shortRange) {
		this.shortRange = shortRange;
	}




	/**
	 * @return the longRange
	 */
	public int getLongRange() {
		return longRange;
	}




	/**
	 * @param longRange the longRange to set
	 */
	public void setLongRange(int longRange) {
		this.longRange = longRange;
	}

	public double [] getArrayDeposit(){
		double [] arrayShort=new double [5];
		for (int i = 0; i < arrayShort.length; i++) {
			arrayShort[i]=0;
		}
		arrayShort[getNumber(this.deposit)]=1;
		return arrayShort;
	}
	public double[] getArrayBorrow(){
		double [] arrayShort=new double [5];
		for (int i = 0; i < arrayShort.length; i++) {
			arrayShort[i]=0;
		}
		arrayShort[getNumber(this.loan)]=1;
		return arrayShort;
	}
	
	public int [] getArrayShort(){
		int [] arrayShort=new int [5];
		for (int i = 0; i < arrayShort.length; i++) {
			arrayShort[i]=0;
		}
		arrayShort[getNumber(this.shortrate)]=1;
		
		return arrayShort;
		
	}
	
	public int getNumber(String r){
		
		switch (r) {
		case "优":
			return 4;
		case "较优":
			return 3;
		case "良":
			return 2;
			
		case "中":
			return 1;
		case "差":
			return 0;
		default:
			return -1;
		}
		
		
	}



	

}
