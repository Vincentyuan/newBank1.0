package bank.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.NatDebtRate;

public interface NatDebtRateDao {
	
	public NatDebtRate getNatDebtRateByYear(int year) throws SQLException;
	public boolean insertDRate(int year,double r1,double r2)throws SQLException;
	
	public ArrayList<NatDebtRate> getAllNatDebtRates()throws SQLException;
	public void delete(String sql)throws SQLException;
	public boolean checkExist(int year) throws Exception;
}
