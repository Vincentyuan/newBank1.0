package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.BankShortData;
import bank.entity.Year;
import bank.util.Jdbc;

public class BankShortDataDaoImpl implements BankShortDataDao {

	private Connection con;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<BankShortData> getShortBankDataByYear(int year)
			throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from bank_data where year =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, year);
		ResultSet rs = ps.executeQuery();
		List<BankShortData> shortDatas = new ArrayList<BankShortData>();
		while (rs.next()) {
			BankShortData bankData = new BankShortData();
			bankData.setLess_fivy_b(rs.getDouble("less_fivy_b"));
			bankData.setLess_fivy_s(rs.getDouble("less_fivy_s"));
			bankData.setLess_thrm_b(rs.getDouble("less_thrm_b"));
			bankData.setLess_thrm_s(rs.getDouble("less_thrm_s"));
			bankData.setLess_twim_b(rs.getDouble("less_twim_b"));
			bankData.setLess_twim_s(rs.getDouble("less_twim_s"));
			bankData.setLong_dig(rs.getDouble("long_dig"));
			bankData.setMore_fivy_b(rs.getDouble("more_fivy_b"));
			bankData.setMore_fivy_s(rs.getDouble("more_fivy_s"));
			bankData.setShort_dig(rs.getDouble("short_dig"));
			bankData.setSoc_mon(rs.getDouble("soc_mon"));
			bankData.setSr_mon(rs.getDouble("sr_mon"));
			bankData.setBid(rs.getInt("bid"));
			bankData.setSbid(rs.getInt("sbid"));
			bankData.setYear(rs.getInt("year"));
			bankData.setLess_fiveloan_recycle(rs.getDouble("less_fiveloan_recycle"));
			bankData.setMore_fiveloan_recycle(rs.getDouble("more_fiveloan_recycle"));

			shortDatas.add(bankData);

		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return shortDatas;
	}

	@Override
	public BankShortData getShortBankDataByBY(String name, int year)
			throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from bank_data where year=? and bid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, year);

		ps.setInt(2, new BankDaoImpl().getBankIdByName(name));
		ResultSet rs = ps.executeQuery();
		BankShortData shortData = new BankShortData();

		shortData.setLess_fivy_b(rs.getDouble("less_fivy_b"));
		shortData.setLess_fivy_s(rs.getDouble("less_fivy_s"));
		shortData.setLess_thrm_b(rs.getDouble("less_thrm_b"));
		shortData.setLess_thrm_s(rs.getDouble("less_thrm_s"));
		shortData.setLess_twim_b(rs.getDouble("less_twim_b"));
		shortData.setLess_twim_s(rs.getDouble("less_twim_s"));
		shortData.setLong_dig(rs.getDouble("long_dig"));
		shortData.setMore_fivy_b(rs.getDouble("more_fivy_b"));
		shortData.setMore_fivy_s(rs.getDouble("more_fivy_s"));
		shortData.setShort_dig(rs.getDouble("short_dig"));
		shortData.setSoc_mon(rs.getDouble("soc_mon"));
		shortData.setSr_mon(rs.getDouble("sr_mon"));
		shortData.setBid(rs.getInt("bid"));
		shortData.setSbid(rs.getInt("sbid"));
		shortData.setYear(rs.getInt("year"));
		shortData.setLess_fiveloan_recycle(rs.getDouble("less_fiveloan_recycle"));
		shortData.setMore_fiveloan_recycle(rs.getDouble("more_fiveloan_recycle"));

		rs.close();
		ps.close();
		Jdbc.release(con);

		return shortData;
	}

	public List<BankShortData> getAllShortData() throws Exception {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		List<BankShortData> bankDatas = new ArrayList<BankShortData>();
		BankShortData bankData = null;
		String sql = "select * from bank_data";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bankData = new BankShortData();
			bankData.setBid(rs.getInt("bid"));
			bankData.setLess_fivy_b(rs.getDouble("less_fivy_b"));
			bankData.setLess_fivy_s(rs.getDouble("less_fivy_s"));
			bankData.setLess_thrm_b(rs.getDouble("less_thrm_b"));
			bankData.setLess_thrm_s(rs.getDouble("less_thrm_s"));
			bankData.setLess_twim_b(rs.getDouble("less_twim_b"));
			bankData.setLess_twim_s(rs.getDouble("less_twim_s"));
			bankData.setLong_dig(rs.getDouble("long_dig"));
			bankData.setMore_fivy_b(rs.getDouble("more_fivy_b"));
			bankData.setMore_fivy_s(rs.getDouble("more_fivy_s"));
			bankData.setShort_dig(rs.getDouble("short_dig"));
			bankData.setSoc_mon(rs.getDouble("soc_mon"));
			bankData.setSr_mon(rs.getDouble("sr_mon"));
			bankData.setLess_fiveloan_recycle(rs.getDouble("less_fiveloan_recycle"));
			bankData.setMore_fiveloan_recycle(rs.getDouble("more_fiveloan_recycle"));


			bankData.setYear(rs.getInt("year"));
			bankDatas.add(bankData);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return bankDatas;
	}

	@Override
	public List<BankShortData> getShortBankDataByBank(String bankname)
			throws Exception {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		List<BankShortData> bankDatas = new ArrayList<BankShortData>();
		BankShortData bankData = null;
		String sql = "select * from bank_data where bid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, new BankDaoImpl().getBankIdByName(bankname));

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			bankData = new BankShortData();
			bankData.setLess_fivy_b(rs.getDouble("less_fivy_b"));
			bankData.setLess_fivy_s(rs.getDouble("less_fivy_s"));
			bankData.setLess_thrm_b(rs.getDouble("less_thrm_b"));
			bankData.setLess_thrm_s(rs.getDouble("less_thrm_s"));
			bankData.setLess_twim_b(rs.getDouble("less_twim_b"));
			bankData.setLess_twim_s(rs.getDouble("less_twim_s"));
			bankData.setLong_dig(rs.getDouble("long_dig"));
			bankData.setMore_fivy_b(rs.getDouble("more_fivy_b"));
			bankData.setMore_fivy_s(rs.getDouble("more_fivy_s"));
			bankData.setShort_dig(rs.getDouble("short_dig"));
			bankData.setSoc_mon(rs.getDouble("soc_mon"));
			bankData.setSr_mon(rs.getDouble("sr_mon"));
			
			bankData.setLess_fiveloan_recycle(rs.getDouble("less_fiveloan_recycle"));
			bankData.setMore_fiveloan_recycle(rs.getDouble("more_fiveloan_recycle"));


			bankData.setYear(rs.getInt("year"));
			bankDatas.add(bankData);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return bankDatas;
	}

	@Override
	public void insertShortData(BankShortData bank) throws Exception {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "insert into bank_data(bid,year,long_dig,short_dig,soc_mon,more_fivy_s,"
				+ "less_fivy_s,less_twim_s,less_thrm_s,sr_mon,more_fivy_b,less_fivy_b,less_twim_b,"
				+ "less_thrm_b,less_fiveloan_recycle,more_fiveloan_recycle) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, bank.getBid());
		ps.setInt(2, bank.getYear());
		ps.setDouble(3, bank.getLong_dig());
		ps.setDouble(4, bank.getShort_dig());
		ps.setDouble(5, bank.getSoc_mon());
		ps.setDouble(6, bank.getMore_fivy_s());
		ps.setDouble(7, bank.getLess_fivy_s());
		ps.setDouble(8, bank.getLess_twim_s());
		ps.setDouble(9, bank.getLess_thrm_s());
		ps.setDouble(10, bank.getSr_mon());
		ps.setDouble(11, bank.getMore_fivy_b());
		ps.setDouble(12, bank.getLess_fivy_b());
		ps.setDouble(13, bank.getLess_twim_b());
		ps.setDouble(14, bank.getLess_thrm_b());
		ps.setDouble(15, bank.getLess_fiveloan_recycle());
		ps.setDouble(16, bank.getMore_fiveloan_recycle());
		
		ps.execute();

		ps.close();
		Jdbc.release(con);


	}
	
	public void delete(String sql) throws SQLException{
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
	}

	@Override
	public BankShortData getShortBankDataByBYId(int bid, int year)
			throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from bank_data where year=? and bid=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, year);

		ps.setInt(2, bid);
		ResultSet rs = ps.executeQuery();
		BankShortData shortData = new BankShortData();

		shortData.setLess_fivy_b(rs.getDouble("less_fivy_b"));
		shortData.setLess_fivy_s(rs.getDouble("less_fivy_s"));
		shortData.setLess_thrm_b(rs.getDouble("less_thrm_b"));
		shortData.setLess_thrm_s(rs.getDouble("less_thrm_s"));
		shortData.setLess_twim_b(rs.getDouble("less_twim_b"));
		shortData.setLess_twim_s(rs.getDouble("less_twim_s"));
		shortData.setLong_dig(rs.getDouble("long_dig"));
		shortData.setMore_fivy_b(rs.getDouble("more_fivy_b"));
		shortData.setMore_fivy_s(rs.getDouble("more_fivy_s"));
		shortData.setShort_dig(rs.getDouble("short_dig"));
		shortData.setSoc_mon(rs.getDouble("soc_mon"));
		shortData.setSr_mon(rs.getDouble("sr_mon"));
		shortData.setBid(rs.getInt("bid"));
		shortData.setSbid(rs.getInt("sbid"));
		shortData.setYear(rs.getInt("year"));
		shortData.setLess_fiveloan_recycle(rs.getDouble("less_fiveloan_recycle"));
		shortData.setMore_fiveloan_recycle(rs.getDouble("more_fiveloan_recycle"));


		rs.close();
		ps.close();
		Jdbc.release(con);

		return shortData;
	}

	@Override
	public void update(BankShortData shortData) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="update bank_data "   //3+3+3+2+2+2
				+ "set long_dig=?,short_dig=?,soc_mon=?,"
				+ "more_fivy_s=?,less_fivy_s=?,less_twim_s=?,"
				+ "less_thrm_s=?, sr_mon=?,more_fivy_b=?,"
				+ "less_fivy_b=?,less_thrm_b=?,"
				+ "less_fiveloan_recycle=?,"
				+ "more_fiveloan_recycle=? "
				+ " where bid=? and year=?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setDouble(1, shortData.getLong_dig());
		ps.setDouble(2, shortData.getShort_dig());
		ps.setDouble(3, shortData.getSoc_mon());

		ps.setDouble(4, shortData.getMore_fivy_s());
		ps.setDouble(5, shortData.getLess_fivy_s());
		ps.setDouble(6, shortData.getLess_twim_s());
		
		ps.setDouble(7, shortData.getLess_thrm_s());
		ps.setDouble(8, shortData.getSr_mon());
		ps.setDouble(9, shortData.getMore_fivy_b());
		
		ps.setDouble(10, shortData.getLess_fivy_b());
		ps.setDouble(11, shortData.getLess_thrm_b());
		ps.setDouble(12, shortData.getLess_fiveloan_recycle());
		ps.setDouble(13, shortData.getMore_fiveloan_recycle());
		
		ps.setInt(14, shortData.getBid());
		ps.setInt(15, shortData.getYear());
		
		
		ps.executeUpdate();
		
		ps.close();
		Jdbc.release(con);
		
		
	}

	@Override
	public boolean checkExist(int bid,int year) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from bank_data where bid=? and year=? ";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, bid);
		ps.setInt(2, year);
		
		ResultSet rs=ps.executeQuery();

		if(rs.next()){
			rs.close();
			ps.close();
			Jdbc.release(con);
			return true;
		}else {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return false;
		}
		
	}

}
