package bank.dao;

import java.sql.SQLException;
import java.util.List;

import bank.entity.Year;

public interface YearDao {

	// 获得所有的年份
	public  List<Year> getAllYear() throws SQLException;
	public int getYearIdByYear(int year)throws SQLException;
	public int getYearByYearId(int id)throws SQLException;
	public boolean insertYear(int year) throws SQLException;
	public void delete(String sql)throws SQLException;
	public boolean checkExist(int year)throws SQLException;
}
