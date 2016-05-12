package bank.dao;

import java.sql.SQLException;

import bank.entity.DataBaseInfo;

public interface DataBaseInfoDao {
	
	public String  [] getTablesName() throws SQLException;

	public void delete(String sql)throws SQLException;
}
