package bank.dao;

import java.sql.SQLException;
import java.util.List;

import bank.entity.DRate;

public interface DRateDao {

	public List<DRate> getAllDRates() throws SQLException;
	public DRate getDRateByDegree(String degree)throws SQLException;
	public void delete(String sql)throws SQLException;
	public void insert(DRate dRate)throws SQLException;
	public List<String> getAllDRatesName()throws SQLException;
	
}
