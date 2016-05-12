package bank.entity;

import java.sql.SQLException;

import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.YearDaoImpl;

public class BankLongData {

	private int id;
	private int Bank_Id;
	
	private int Year_Id;
	private double IB_Asset;
	private double Loan;
	private double Investment;
	private double In_Center_Bank;
	private double Call_Loan_ToBan;
	private double Fund_Raise_Amount;
	private double Borrowing;
	private double Issue_Bonds;
	private double Deposit;
	private double R1_Loan;
	private double R2_Investment;
	private double R3_C_EmFunds;
	private double R4_Offer;
	private double R5_Borrow;
	private double R6_Bond;
	private double R7_Deposit;
	private double Taking;
	private double Extra_Deposit;
	private double Actual_Loan_Amount;
	private double Actual_RI_Loan;
	
	
	private String bankName;
	private int year;
	
	public BankLongData(){}
	
	
	
	/**
	 * @param id
	 * @param bank_Name
	 * @param year
	 * @param iB_Asset
	 * @param loan
	 * @param investment
	 * @param in_Center_Bank
	 * @param call_Loan_ToBan
	 * @param fund_Raise_Amount
	 * @param borrowing
	 * @param issue_Bonds
	 * @param deposit
	 * @param r1_Loan
	 * @param r1_Investment
	 * @param r3_C_EmFunds
	 * @param r4_Offer
	 * @param r5_Borrow
	 * @param r6_Bond
	 * @param r7_Deposit
	 * @param taking
	 * @param extra_Deposit
	 * @param actual_Loan_Amount
	 * @param actual_RI_Loan
	 */
	public BankLongData(int id, int bank_Id, int year_Id, double iB_Asset,
			double loan, double investment, double in_Center_Bank,
			double call_Loan_ToBan, double fund_Raise_Amount, double borrowing,
			double issue_Bonds, double deposit, double r1_Loan,
			double r2_Investment, double r3_C_EmFunds, double r4_Offer,
			double r5_Borrow, double r6_Bond, double r7_Deposit, double taking,
			double extra_Deposit, double actual_Loan_Amount,
			double actual_RI_Loan) {
		this.id = id;
		Bank_Id = bank_Id;
		this.Year_Id= year_Id;
		IB_Asset = iB_Asset;
		Loan = loan;
		Investment = investment;
		In_Center_Bank = in_Center_Bank;
		Call_Loan_ToBan = call_Loan_ToBan;
		Fund_Raise_Amount = fund_Raise_Amount;
		Borrowing = borrowing;
		Issue_Bonds = issue_Bonds;
		Deposit = deposit;
		R1_Loan = r1_Loan;
		R2_Investment = r2_Investment;
		R3_C_EmFunds = r3_C_EmFunds;
		R4_Offer = r4_Offer;
		R5_Borrow = r5_Borrow;
		R6_Bond = r6_Bond;
		R7_Deposit = r7_Deposit;
		Taking = taking;
		Extra_Deposit = extra_Deposit;
		Actual_Loan_Amount = actual_Loan_Amount;
		//Actual_RI_Loan = actual_RI_Loan;
		
		try {
			this.bankName=new BankDaoImpl().getBankById(bank_Id).getName();
			this.year=new YearDaoImpl().getYearByYearId(year_Id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	 * @return the bank_Name
	 */
	public int getBank_Id() {
		return Bank_Id;
	}
	/**
	 * @param bank_Name the bank_Name to set
	 */
	public void setBank_Id(int bank_Id) {
		Bank_Id = bank_Id;
		try {
			this.bankName=new BankDaoImpl().getBankById(bank_Id).getName();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @return the year
	 */
	public int getYear_Id() {
		return Year_Id;
	}
	/**
	 * @param year the year to set
	 * @throws SQLException 
	 */
	public void setYear(int year_Id) throws SQLException {
		this.Year_Id = year_Id;
		this.year=year_Id;
	}
	/**
	 * @return the iB_Asset
	 */
	public double getIB_Asset() {
		return IB_Asset;
	}
	/**
	 * @param iB_Asset the iB_Asset to set
	 */
	public void setIB_Asset(double iB_Asset) {
		IB_Asset = iB_Asset;
	}
	/**
	 * @return the loan
	 */
	public double getLoan() {
		return Loan;
	}
	/**
	 * @param loan the loan to set
	 */
	public void setLoan(double loan) {
		Loan = loan;
	}
	/**
	 * @return the investment
	 */
	public double getInvestment() {
		return Investment;
	}
	/**
	 * @param investment the investment to set
	 */
	public void setInvestment(double investment) {
		Investment = investment;
	}
	/**
	 * @return the in_Center_Bank
	 */
	public double getIn_Center_Bank() {
		return In_Center_Bank;
	}
	/**
	 * @param in_Center_Bank the in_Center_Bank to set
	 */
	public void setIn_Center_Bank(double in_Center_Bank) {
		In_Center_Bank = in_Center_Bank;
	}
	/**
	 * @return the call_Loan_ToBan
	 */
	public double getCall_Loan_ToBan() {
		return Call_Loan_ToBan;
	}
	/**
	 * @param call_Loan_ToBan the call_Loan_ToBan to set
	 */
	public void setCall_Loan_ToBan(double call_Loan_ToBan) {
		Call_Loan_ToBan = call_Loan_ToBan;
	}
	/**
	 * @return the fund_Raise_Amount
	 */
	public double getFund_Raise_Amount() {
		return Fund_Raise_Amount;
	}
	/**
	 * @param fund_Raise_Amount the fund_Raise_Amount to set
	 */
	public void setFund_Raise_Amount(double fund_Raise_Amount) {
		Fund_Raise_Amount = fund_Raise_Amount;
	}
	/**
	 * @return the borrowing
	 */
	public double getBorrowing() {
		return Borrowing;
	}
	/**
	 * @param borrowing the borrowing to set
	 */
	public void setBorrowing(double borrowing) {
		Borrowing = borrowing;
	}
	/**
	 * @return the issue_Bonds
	 */
	public double getIssue_Bonds() {
		return Issue_Bonds;
	}
	/**
	 * @param issue_Bonds the issue_Bonds to set
	 */
	public void setIssue_Bonds(double issue_Bonds) {
		Issue_Bonds = issue_Bonds;
	}
	/**
	 * @return the deposit
	 */
	public double getDeposit() {
		return Deposit;
	}
	/**
	 * @param deposit the deposit to set
	 */
	public void setDeposit(double deposit) {
		Deposit = deposit;
	}
	/**
	 * @return the r1_Loan
	 */
	public double getR1_Loan() {
		return R1_Loan;
	}
	/**
	 * @param r1_Loan the r1_Loan to set
	 */
	public void setR1_Loan(double r1_Loan) {
		R1_Loan = r1_Loan;
	}
	/**
	 * @return the r2_Investment
	 */
	public double getR2_Investment() {
		return R2_Investment;
	}
	/**
	 * @param r1_Investment the r1_Investment to set
	 */
	public void setR2_Investment(double r2_Investment) {
		R2_Investment = r2_Investment;
	}
	/**
	 * @return the r3_C_EmFunds
	 */
	public double getR3_C_EmFunds() {
		return R3_C_EmFunds;
	}
	/**
	 * @param r3_C_EmFunds the r3_C_EmFunds to set
	 */
	public void setR3_C_EmFunds(double r3_C_EmFunds) {
		R3_C_EmFunds = r3_C_EmFunds;
	}
	/**
	 * @return the r4_Offer
	 */
	public double getR4_Offer() {
		return R4_Offer;
	}
	/**
	 * @param r4_Offer the r4_Offer to set
	 */
	public void setR4_Offer(double r4_Offer) {
		R4_Offer = r4_Offer;
	}
	/**
	 * @return the r5_Borrow
	 */
	public double getR5_Borrow() {
		return R5_Borrow;
	}
	/**
	 * @param r5_Borrow the r5_Borrow to set
	 */
	public void setR5_Borrow(double r5_Borrow) {
		R5_Borrow = r5_Borrow;
	}
	/**
	 * @return the r6_Bond
	 */
	public double getR6_Bond() {
		return R6_Bond;
	}
	/**
	 * @param r6_Bond the r6_Bond to set
	 */
	public void setR6_Bond(double r6_Bond) {
		R6_Bond = r6_Bond;
	}
	/**
	 * @return the r7_Deposit
	 */
	public double getR7_Deposit() {
		return R7_Deposit;
	}
	/**
	 * @param r7_Deposit the r7_Deposit to set
	 */
	public void setR7_Deposit(double r7_Deposit) {
		R7_Deposit = r7_Deposit;
	}
	/**
	 * @return the taking
	 */
	public double getTaking() {
		return Taking;
	}
	/**
	 * @param taking the taking to set
	 */
	public void setTaking(double taking) {
		Taking = taking;
	}
	/**
	 * @return the extra_Deposit
	 */
	public double getExtra_Deposit() {
		return Extra_Deposit;
	}
	/**
	 * @param extra_Deposit the extra_Deposit to set
	 */
	public void setExtra_Deposit(double extra_Deposit) {
		Extra_Deposit = extra_Deposit;
	}
	/**
	 * @return the actual_Loan_Amount
	 */
	public double getActual_Loan_Amount() {
		return Actual_Loan_Amount;
	}
	/**
	 * @param actual_Loan_Amount the actual_Loan_Amount to set
	 */
	public void setActual_Loan_Amount(double actual_Loan_Amount) {
		Actual_Loan_Amount = actual_Loan_Amount;
	}
	/**
	 * @return the actual_RI_Loan
	 */
	public double getActual_RI_Loan() {
		return Actual_RI_Loan;
	}
	/**
	 * @param actual_RI_Loan the actual_RI_Loan to set
	 */
	public void setActual_RI_Loan(double actual_RI_Loan) {
		Actual_RI_Loan = actual_RI_Loan;
	}



	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}



	/**
	 * @param bankName the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}



	/**
	 * @param year_Id the year_Id to set
	 */
	public void setYear_Id(int year_Id) {
		Year_Id = year_Id;
	}



	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}
	
	
	
}
