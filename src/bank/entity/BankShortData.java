package bank.entity;

import java.sql.SQLException;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;

public class BankShortData {
	
	private int sbid;
	private int bid;
	private int year;
	private double less_thrm_b;
	private double less_twim_b;
	private double less_fivy_b;
	private double more_fivy_b;
	private double sr_mon;
	private double less_thrm_s;
	private double less_twim_s;
	private double less_fivy_s;
	private double more_fivy_s;
	private double soc_mon;
	private double short_dig;
	private double long_dig;
	
	private double less_fiveloan_recycle;
	private double more_fiveloan_recycle;
	
	
	/**
	 * @return the less_fiveloan_recycle
	 */
	public double getLess_fiveloan_recycle() {
		return less_fiveloan_recycle;
	}


	/**
	 * @param less_fiveloan_recycle the less_fiveloan_recycle to set
	 */
	public void setLess_fiveloan_recycle(double less_fiveloan_recycle) {
		this.less_fiveloan_recycle = less_fiveloan_recycle;
	}


	/**
	 * @return the more_fiveloan_recycle
	 */
	public double getMore_fiveloan_recycle() {
		return more_fiveloan_recycle;
	}


	/**
	 * @param more_fiveloan_recycle the more_fiveloan_recycle to set
	 */
	public void setMore_fiveloan_recycle(double more_fiveloan_recycle) {
		this.more_fiveloan_recycle = more_fiveloan_recycle;
	}


	private String bankname;
	
	public BankShortData(){}
	
	
	public BankShortData(int sbid,int bid, int year, double less_thrm_b, double less_twim_b,
			double less_fivy_b, double more_fivy_b, double sr_mon,
			double less_thrm_s, double less_twim_s, double less_fivy_s,
			double more_fivy_s, double soc_mon, double short_dig,
			double long_dig) {
		super();
		this.sbid=sbid;
		this.bid = bid;
		this.year = year;
		this.less_thrm_b = less_thrm_b;
		this.less_twim_b = less_twim_b;
		this.less_fivy_b = less_fivy_b;
		this.more_fivy_b = more_fivy_b;
		this.sr_mon = sr_mon;
		this.less_thrm_s = less_thrm_s;
		this.less_twim_s = less_twim_s;
		this.less_fivy_s = less_fivy_s;
		this.more_fivy_s = more_fivy_s;
		this.soc_mon = soc_mon;
		this.short_dig = short_dig;
		this.long_dig = long_dig;
		
		BankDao tBankDao=new BankDaoImpl();
		try {
			this.bankname=tBankDao.getBankById(bid).getName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * @return the bid
	 */
	public int getBid() {
		return bid;
	}


	/**
	 * @param bid the bid to set
	 */
	public void setBid(int bid) {
		this.bid = bid;
		BankDao tBankDao=new BankDaoImpl();
		try {
			this.bankname=tBankDao.getBankById(bid).getName();
		//System.out.println(this.bankname);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public int getSbid() {
		return sbid;
	}
	public void setSbid(int sbid) {
		this.sbid = sbid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public double getLess_thrm_b() {
		return less_thrm_b;
	}
	public void setLess_thrm_b(double less_thrm_b) {
		this.less_thrm_b = less_thrm_b;
	}
	public double getLess_twim_b() {
		return less_twim_b;
	}
	public void setLess_twim_b(double less_twim_b) {
		this.less_twim_b = less_twim_b;
	}
	public double getLess_fivy_b() {
		return less_fivy_b;
	}
	public void setLess_fivy_b(double less_fivy_b) {
		this.less_fivy_b = less_fivy_b;
	}
	public double getMore_fivy_b() {
		return more_fivy_b;
	}
	public void setMore_fivy_b(double more_fivy_b) {
		this.more_fivy_b = more_fivy_b;
	}
	public double getSr_mon() {
		return sr_mon;
	}
	public void setSr_mon(double sr_mon) {
		this.sr_mon = sr_mon;
	}
	public double getLess_thrm_s() {
		return less_thrm_s;
	}
	public void setLess_thrm_s(double less_thrm_s) {
		this.less_thrm_s = less_thrm_s;
	}
	public double getLess_twim_s() {
		return less_twim_s;
	}
	public void setLess_twim_s(double less_twim_s) {
		this.less_twim_s = less_twim_s;
	}
	public double getLess_fivy_s() {
		return less_fivy_s;
	}
	public void setLess_fivy_s(double less_fivy_s) {
		this.less_fivy_s = less_fivy_s;
	}
	public double getMore_fivy_s() {
		return more_fivy_s;
	}
	public void setMore_fivy_s(double more_fivy_s) {
		this.more_fivy_s = more_fivy_s;
	}
	public double getSoc_mon() {
		return soc_mon;
	}
	public void setSoc_mon(double soc_mon) {
		this.soc_mon = soc_mon;
	}
	public double getShort_dig() {
		return short_dig;
	}
	public void setShort_dig(double short_dig) {
		this.short_dig = short_dig;
	}
	public double getLong_dig() {
		return long_dig;
	}
	public void setLong_dig(double long_dig) {
		this.long_dig = long_dig;
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
	
	public void printAllElement(){
		System.out.println("bankname"+bankname +" year "+year);
		System.out.println("less_thrm_b"+less_thrm_b+" less_twim_b "+less_twim_b );
		
		System.out.println("less_fivy_b"+less_fivy_b+"more_fivy_b "+more_fivy_b);
		System.out.println("sr_mon"+sr_mon+"less_thrm_s"+less_thrm_s
				+" less_twim_s"+less_twim_s+"less_fivy_s"+less_fivy_s);
		System.out.println("more_fivy_s"+more_fivy_s+"soc_mon"+soc_mon+
				"less_fiveloan_recycle"+less_fiveloan_recycle+
				"more_fiveloan_recycle"+more_fiveloan_recycle);
		System.out.println("short_dig"+short_dig+"long_dig"+long_dig);
	}
	
	

}
