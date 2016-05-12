package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bank.util.Jdbc;

public class DataBaseInfoDaoImpl implements DataBaseInfoDao {

	private Connection con;
	public DataBaseInfoDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String[] getTablesName() throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select *  from sqlite_sequence ";
	//	String sql="select * from year";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		
	//	System.out.println(rs.getInt(1)+"result"+" rs.next"+rs.getString("name"));
		String [] tmpStrings = new String[8] ; 
		
		int i=0;
		while (rs.next()) {
		
			tmpStrings[i++]=rs.getString("name");
		}
		
		rs.close();
		ps.close();
		Jdbc.release(con);		
		
		return tmpStrings;
	}
	
	public void delete(String sql) throws SQLException{
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
