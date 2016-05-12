package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.util.Jdbc;

public class BankLongDataImpl implements BankLongDataDao {

	private Connection con = null;

	// 保存失败的异常处理
	@Override
	public void addBankLongData(BankLongData bankLongData) throws Exception {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "insert into bank_long(Bank_Id,Year_id,IB_Asset,Loan,"
				+ "Investment,In_Center_Bank,Call_Loan_ToBan,Fund_Raise_Amount,"
				+ "Borrowing,Issue_Bonds,Deposit,R1_Loan,R2_Investment,R3_C_EmFunds,"
				+ "R4_Offer,R5_Borrow,R6_Bond,R7_Deposit,Taking,"
				+ "Extra_Deposit,Actual_Loan_Amount,Actual_RI_Loan "
				+ ") values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, bankLongData.getBank_Id());
		ps.setInt(2, bankLongData.getYear());
		ps.setDouble(3, bankLongData.getIB_Asset());
		ps.setDouble(4, bankLongData.getLoan());
		ps.setDouble(5, bankLongData.getInvestment());
		ps.setDouble(6, bankLongData.getIn_Center_Bank());
		ps.setDouble(7, bankLongData.getCall_Loan_ToBan());
		ps.setDouble(8, bankLongData.getFund_Raise_Amount());
		ps.setDouble(9, bankLongData.getBorrowing());
		ps.setDouble(10, bankLongData.getIssue_Bonds());
		ps.setDouble(11, bankLongData.getDeposit());
		ps.setDouble(12, bankLongData.getR1_Loan());
		ps.setDouble(13, bankLongData.getR2_Investment());
		ps.setDouble(14, bankLongData.getR3_C_EmFunds());
		ps.setDouble(15, bankLongData.getR4_Offer());
		ps.setDouble(16, bankLongData.getR5_Borrow());
		ps.setDouble(17, bankLongData.getR6_Bond());
		ps.setDouble(18, bankLongData.getR7_Deposit());
		ps.setDouble(19, bankLongData.getTaking());
		ps.setDouble(20, bankLongData.getExtra_Deposit());
		ps.setDouble(21, bankLongData.getActual_Loan_Amount());
		ps.setDouble(22, bankLongData.getActual_RI_Loan());
		ps.executeUpdate();

		ps.close();
		Jdbc.release(con);

	}

	@Override
	public List<BankLongData> getAllBankLongData() throws Exception {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from bank_long ";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<BankLongData> arr = new ArrayList<BankLongData>();

		while (rs.next()) {
			BankLongData banklong = new BankLongData();

			banklong.setId(rs.getInt("id"));
			banklong.setBank_Id(rs.getInt("Bank_Id"));
			banklong.setYear(rs.getInt("Year_id"));
			banklong.setIB_Asset(rs.getDouble("IB_Asset"));
			banklong.setLoan(rs.getDouble("Loan"));
			banklong.setInvestment(rs.getDouble("Investment"));
			banklong.setIn_Center_Bank(rs.getDouble("In_Center_Bank"));
			banklong.setCall_Loan_ToBan(rs.getDouble("Call_Loan_ToBan"));
			banklong.setFund_Raise_Amount(rs.getDouble("Fund_Raise_Amount"));
			banklong.setBorrowing(rs.getDouble("Borrowing"));
			banklong.setIssue_Bonds(rs.getDouble("Issue_Bonds"));
			banklong.setDeposit(rs.getDouble("Deposit"));
			banklong.setR1_Loan(rs.getDouble("R1_Loan"));
			banklong.setR2_Investment(rs.getDouble("R2_Investment"));
			banklong.setR3_C_EmFunds(rs.getDouble("R3_C_EmFunds"));
			banklong.setR4_Offer(rs.getDouble("R4_Offer"));
			banklong.setR5_Borrow(rs.getDouble("R5_Borrow"));
			banklong.setR6_Bond(rs.getDouble("R6_Bond"));
			banklong.setR7_Deposit(rs.getDouble("R7_Deposit"));
			banklong.setTaking(rs.getDouble("Taking"));
			banklong.setExtra_Deposit(rs.getDouble("Extra_Deposit"));
			banklong.setActual_Loan_Amount(rs.getDouble("Actual_Loan_Amount"));
			banklong.setActual_RI_Loan(rs.getDouble("Actual_RI_Loan"));

			arr.add(banklong);

		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return arr;

	}

	@Override
	public BankLongData getBankLongDataByBY(String name, int year)
			throws Exception {
		// TODO Auto-generated method stub

		con = Jdbc.getCon();
		String sql = "select * from bank_long where Year_id = ? and Bank_Id= ?";
		PreparedStatement ps = con.prepareStatement(sql);
		BankDao tmp = new BankDaoImpl();

		ps.setInt(1, year);

		ps.setInt(2, tmp.getBankIdByName(name));
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			BankLongData banklong = new BankLongData();

			banklong.setId(rs.getInt("id"));
			banklong.setBank_Id(rs.getInt("Bank_Id"));
			banklong.setYear(rs.getInt("Year_id"));
			banklong.setIB_Asset(rs.getDouble("IB_Asset"));
			banklong.setLoan(rs.getDouble("Loan"));
			banklong.setInvestment(rs.getDouble("Investment"));
			banklong.setIn_Center_Bank(rs.getDouble("In_Center_Bank"));
			banklong.setCall_Loan_ToBan(rs.getDouble("Call_Loan_ToBan"));
			banklong.setFund_Raise_Amount(rs.getDouble("Fund_Raise_Amount"));
			banklong.setBorrowing(rs.getDouble("Borrowing"));
			banklong.setIssue_Bonds(rs.getDouble("Issue_Bonds"));
			banklong.setDeposit(rs.getDouble("Deposit"));
			banklong.setR1_Loan(rs.getDouble("R1_Loan"));
			banklong.setR2_Investment(rs.getDouble("R2_Investment"));
			banklong.setR3_C_EmFunds(rs.getDouble("R3_C_EmFunds"));
			banklong.setR4_Offer(rs.getDouble("R4_Offer"));
			banklong.setR5_Borrow(rs.getDouble("R5_Borrow"));
			banklong.setR6_Bond(rs.getDouble("R6_Bond"));
			banklong.setR7_Deposit(rs.getDouble("R7_Deposit"));
			banklong.setTaking(rs.getDouble("Taking"));
			banklong.setExtra_Deposit(rs.getDouble("Extra_Deposit"));
			banklong.setActual_Loan_Amount(rs.getDouble("Actual_Loan_Amount"));
			banklong.setActual_RI_Loan(rs.getDouble("Actual_RI_Loan"));

			rs.close();
			ps.close();
			Jdbc.release(con);
			return banklong;
		} else {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return null;
		}

	}

	/*
	 * public int getIdByYear(int year) throws SQLException{ con=Jdbc.getCon();
	 * String sql = "select id from year where year_id = ? "; PreparedStatement
	 * ps = con.prepareStatement(sql); ps.setInt(1,year); ResultSet rs =
	 * ps.executeQuery(); int id=rs.getInt("id"); rs.close(); ps.close();
	 * Jdbc.release(con); return id;
	 * 
	 * }
	 */

	@Override
	public List<BankLongData> getAllBankLongDataByYear(int year)
			throws Exception {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from bank_long where year_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, year);

		ResultSet rs = ps.executeQuery();
		List<BankLongData> arr = new ArrayList<BankLongData>();

		while (rs.next()) {
			BankLongData banklong = new BankLongData();

			banklong.setId(rs.getInt("id"));
			banklong.setBank_Id(rs.getInt("Bank_Id"));
			banklong.setYear(rs.getInt("Year_id"));
			banklong.setIB_Asset(rs.getDouble("IB_Asset"));
			banklong.setLoan(rs.getDouble("Loan"));
			banklong.setInvestment(rs.getDouble("Investment"));
			banklong.setIn_Center_Bank(rs.getDouble("In_Center_Bank"));
			banklong.setCall_Loan_ToBan(rs.getDouble("Call_Loan_ToBan"));
			banklong.setFund_Raise_Amount(rs.getDouble("Fund_Raise_Amount"));
			banklong.setBorrowing(rs.getDouble("Borrowing"));
			banklong.setIssue_Bonds(rs.getDouble("Issue_Bonds"));
			banklong.setDeposit(rs.getDouble("Deposit"));
			banklong.setR1_Loan(rs.getDouble("R1_Loan"));
			banklong.setR2_Investment(rs.getDouble("R2_Investment"));
			banklong.setR3_C_EmFunds(rs.getDouble("R3_C_EmFunds"));
			banklong.setR4_Offer(rs.getDouble("R4_Offer"));
			banklong.setR5_Borrow(rs.getDouble("R5_Borrow"));
			banklong.setR6_Bond(rs.getDouble("R6_Bond"));
			banklong.setR7_Deposit(rs.getDouble("R7_Deposit"));
			banklong.setTaking(rs.getDouble("Taking"));
			banklong.setExtra_Deposit(rs.getDouble("Extra_Deposit"));
			banklong.setActual_Loan_Amount(rs.getDouble("Actual_Loan_Amount"));
			banklong.setActual_RI_Loan(rs.getDouble("Actual_RI_Loan"));

			arr.add(banklong);

		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return arr;
	}

	public void delete(String sql) throws SQLException {
		con = Jdbc.getCon();

		PreparedStatement ps = con.prepareStatement(sql);

		ps.execute();

		ps.close();
		Jdbc.release(con);
	}

	public static void main(String[] args) throws Exception {
		BankLongData b = new BankLongData();
		BankLongDataImpl t = new BankLongDataImpl();
		System.out.println("read" + t.getBankLongDataByBY("中国工商银行", 2006));
	}

	@Override
	public boolean checkExist(int bid, int year) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();

		String sql = "select * from bank_long where bank_id=? and year_id=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, bid);
		ps.setInt(2, year);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return true;

		} else {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return false;
		}

	}
	
	public List<BankLongData> getLongBankDataByBank(String bankname) throws SQLException{
		
		con = Jdbc.getCon();
		String sql = "select * from bank_long where Bank_Id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		BankDao bankDao=new BankDaoImpl();
		ps.setInt(1, bankDao.getBankIdByName(bankname));

		ResultSet rs = ps.executeQuery();
		List<BankLongData> arr = new ArrayList<BankLongData>();

		while (rs.next()) {
			BankLongData banklong = new BankLongData();

			banklong.setId(rs.getInt("id"));
			banklong.setBank_Id(rs.getInt("Bank_Id"));
			banklong.setYear(rs.getInt("Year_id"));
			banklong.setIB_Asset(rs.getDouble("IB_Asset"));
			banklong.setLoan(rs.getDouble("Loan"));
			banklong.setInvestment(rs.getDouble("Investment"));
			banklong.setIn_Center_Bank(rs.getDouble("In_Center_Bank"));
			banklong.setCall_Loan_ToBan(rs.getDouble("Call_Loan_ToBan"));
			banklong.setFund_Raise_Amount(rs.getDouble("Fund_Raise_Amount"));
			banklong.setBorrowing(rs.getDouble("Borrowing"));
			banklong.setIssue_Bonds(rs.getDouble("Issue_Bonds"));
			banklong.setDeposit(rs.getDouble("Deposit"));
			banklong.setR1_Loan(rs.getDouble("R1_Loan"));
			banklong.setR2_Investment(rs.getDouble("R2_Investment"));
			banklong.setR3_C_EmFunds(rs.getDouble("R3_C_EmFunds"));
			banklong.setR4_Offer(rs.getDouble("R4_Offer"));
			banklong.setR5_Borrow(rs.getDouble("R5_Borrow"));
			banklong.setR6_Bond(rs.getDouble("R6_Bond"));
			banklong.setR7_Deposit(rs.getDouble("R7_Deposit"));
			banklong.setTaking(rs.getDouble("Taking"));
			banklong.setExtra_Deposit(rs.getDouble("Extra_Deposit"));
			banklong.setActual_Loan_Amount(rs.getDouble("Actual_Loan_Amount"));
			banklong.setActual_RI_Loan(rs.getDouble("Actual_RI_Loan"));

			arr.add(banklong);

		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return arr;
		
	}
	
}
