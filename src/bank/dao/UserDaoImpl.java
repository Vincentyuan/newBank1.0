package bank.dao;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.UserInfo;
import bank.util.Jdbc;

public class UserDaoImpl implements UserDao {
	private Connection con = null;
	public boolean checkPassword(String name,String passwd) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select password from user where userName= ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		String getPasswd=rs.getString("password");
		
		if(getPasswd.equals(passwd))
		{
			rs.close();
			ps.close();
			Jdbc.release(con);
			return true;
		}else{
			rs.close();
			ps.close();
			Jdbc.release(con);
			return false;
		}
		
	}

	@Override
	public boolean addUser(String name, String passwd) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "insert into user(userName,password) values ('"+name+"', '"+passwd+"')";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		Jdbc.release(con);
		
		if(this.checkPassword(name, passwd))
		{
			return true;
		}
		else{
			return false;
		}
			
	}
	
	public static void main(String[] args) throws SQLException{
		UserDao use=new UserDaoImpl();
		
	}

	@Override
	public boolean checkExist(String name) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from user where userName= ? ";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		
		ResultSet rs = ps.executeQuery();
		
		
		if(rs.next())
		{
			rs.close();
			ps.close();
			Jdbc.release(con);
			return true;
		}else{
			rs.close();
			ps.close();
			Jdbc.release(con);
			return false;
		}
	}

	@Override
	public ArrayList<UserInfo>  getTableContent() throws SQLException {
		// TODO Auto-generated method stub
		
		con=Jdbc.getCon();
		
		String sql="select * from user";
		PreparedStatement ps=con.prepareStatement(sql);
				
		ResultSet rs=ps.executeQuery();
		
		ArrayList<UserInfo> tmp=new ArrayList<>();
		
		while(rs.next()){
			UserInfo userInfo=new UserInfo();
			userInfo.setId(rs.getInt("user_id"));
			userInfo.setNameString(rs.getString("userName"));
			userInfo.setPasswdString(rs.getString("password"));
			userInfo.setPosition(rs.getInt("authority"));
			tmp.add(userInfo);
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
	public boolean checkAuthority(String name) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from user where userName=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, name);
		
		ResultSet rs=ps.executeQuery();
		
		int auth=rs.getInt("authority");
		
		if (auth==1) {
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

	@Override
	public void setAsAdminister(String sql) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.executeUpdate();
		
		
		ps.close();
		Jdbc.release(con);
	}

	public void cancellAdminister(String sql) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.executeUpdate();
		
		
		ps.close();
		Jdbc.release(con);
	}


	

}
