package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.BankLongData;
import bank.entity.Year;
import bank.util.Jdbc;

public class YearDaoImpl implements YearDao {

	private Connection con = null;
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		/*YearDao y=new YearDaoImpl();
		List<Year> year=new ArrayList<Year>();
		year=y.getAllYear();
		System.out.println(year.size());
		for(Year tmp: year)
		{
			System.out.println(tmp.getYear());
		}*/
		YearDao yearDao=new YearDaoImpl();
		System.out.println(yearDao.insertYear(55));
		
	}
	

	@Override
	public List<Year> getAllYear() throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from year";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		List<Year> years=new ArrayList<Year>();
		while(rs.next()){
			Year tmp=new Year();
			tmp.setId(rs.getInt("id"));
			
			tmp.setYear(rs.getInt("year"));
			years.add(tmp);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return years;
	}


	@Override
	public int getYearIdByYear(int year) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from year where year=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, year);
		
		ResultSet rs=ps.executeQuery();
		int year_id=rs.getInt("id");
		
		rs.close();
		ps.close();
		Jdbc.release(con);
		
		
		return year_id;
	}


	@Override
	public boolean insertYear(int year) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="insert into year(year) values(?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, year);
		ps.execute();
		
		if(year==getYearByYearId(getYearIdByYear(year))){
			
			ps.close();
			Jdbc.release(con);
			
			return true;
		}else {
			
			ps.close();
			Jdbc.release(con);
			
			return false;
		}
		
	}


	@Override
	public int getYearByYearId(int id) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from year where id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, id);
		
		ResultSet rs=ps.executeQuery();
		int year=rs.getInt("year");
		
		rs.close();
		ps.close();
		Jdbc.release(con);
		
		
		return year;
	}
	
	public void delete(String sql) throws SQLException{
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
	}


	@Override
	public boolean checkExist(int year) throws SQLException {
		// TODO Auto-generated method stub
		
		con=Jdbc.getCon();
		String sql="select * from year where year=?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.setInt(1, year);
		
		ResultSet rs=ps.executeQuery();
		
		if (rs.next()) {
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
