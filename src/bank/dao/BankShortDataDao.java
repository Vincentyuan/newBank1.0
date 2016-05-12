package bank.dao;

import java.sql.SQLException;
import java.util.List;

import bank.entity.BankShortData;

public interface BankShortDataDao {
	
	public List<BankShortData> getShortBankDataByYear(int year) throws SQLException;
	public BankShortData getShortBankDataByBY(String name, int year) throws SQLException;
	public List<BankShortData> getAllShortData() throws Exception;
	public List<BankShortData> getShortBankDataByBank(String bankname)throws Exception;
	public void insertShortData(BankShortData bank)throws Exception;
	public void delete(String sql)throws SQLException;
	public BankShortData getShortBankDataByBYId(int bid, int year) throws SQLException;
	public void update(BankShortData shortData) throws SQLException;
	public boolean checkExist(int bid,int year)throws SQLException;
	
}
