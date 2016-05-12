package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bank.entity.NatDebtRate;
import bank.util.Jdbc;

public class NatDebtRateImpl implements NatDebtRateDao {
	
	private Connection con;


	@Override
	public NatDebtRate getNatDebtRateByYear(int year) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from andebtinterestRate where year=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, year);
		ResultSet rs=ps.executeQuery();
		NatDebtRate tmp=new NatDebtRate();
		tmp.setId(rs.getInt("id"));
		tmp.setYear(rs.getInt("year"));
		tmp.setThreeyearrate(rs.getDouble("threeyearrate"));
		tmp.setFiveyearrate(rs.getDouble("fiveyearrate"));
		
		rs.close();
		ps.close();
		Jdbc.release(con);
		return tmp;
	}
	@Override
	public boolean insertDRate(int year,double r1, double r2) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="insert into andebtinterestRate(year,threeyearrate,fiveyearrate)"
				+ " values(?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, year);
		ps.setDouble(2, r1);
		ps.setDouble(3, r2);
		ps.execute();
		
		if(year==(getNatDebtRateByYear(year)).getYear()){
			
			ps.close();
			Jdbc.release(con);
			
			return true;
		}else {
			
			ps.close();
			Jdbc.release(con);
			
			return false;
		}
	}


	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		NatDebtRateDao tDao=new NatDebtRateImpl();
		System.out.println(tDao.getNatDebtRateByYear(2006).getThreeyearrate());
	}
	@Override
	public ArrayList<NatDebtRate> getAllNatDebtRates() throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from andebtinterestRate";
		PreparedStatement ps=con.prepareStatement(sql);
		
		ResultSet rs=ps.executeQuery();
		
		ArrayList< NatDebtRate> tmp=new ArrayList<>();
		
		while (rs.next()) {
			NatDebtRate rate=new NatDebtRate();
			rate.setYear(rs.getInt("year"));
			rate.setThreeyearrate(rs.getDouble("threeyearrate"));
			rate.setFiveyearrate(rs.getDouble("fiveyearrate"));
			tmp.add(rate);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		
		return tmp;
	}
	
	public void delete(String sql) throws SQLException{
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
	}
	@Override
	public boolean checkExist(int year) throws Exception {
		// TODO Auto-generated method stub
		
		con = Jdbc.getCon();

		String sql = "select * from andebtinterestRate where year=?";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, year);
		
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
	
	

}
