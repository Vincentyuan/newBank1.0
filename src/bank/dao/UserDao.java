package bank.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bank.entity.UserInfo;


public interface UserDao {
	
	public boolean checkPassword(String name,String passwd)throws SQLException;
	
	public boolean addUser(String name,String passwd) throws SQLException;
	
	public boolean checkExist(String name)throws SQLException;
	
	public ArrayList<UserInfo> getTableContent()throws SQLException;
	
	public void delete(String sql)throws SQLException;
	
	public boolean checkAuthority(String name) throws SQLException;
	
	public void setAsAdminister(String sql) throws SQLException;
	public void cancellAdminister(String sql) throws SQLException;
}
