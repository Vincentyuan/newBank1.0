package bank.dao;

import java.sql.SQLException;
import java.util.List;

import bank.entity.BankLongData;

public interface BankLongDataDao {
	
	
	//对bank_long进行操作获得长期的稳定性评估。
	
	
	//添加数据
	public void addBankLongData(BankLongData bankLongData) throws Exception; 
	
	//根据银行名id和年份id确定数据查找
	public BankLongData getBankLongDataByBY(String name,int year) throws Exception;
	
	//查找出所有bankdata记录
	public List<BankLongData> getAllBankLongData() throws Exception;
	//根据年份查找所有银行记录
	
	public List<BankLongData> getAllBankLongDataByYear(int year)throws Exception;
	
	public void delete(String sql)throws SQLException;
	
	
	public boolean checkExist(int bid,int year)throws SQLException;
	
	public List<BankLongData> getLongBankDataByBank(String banknames)throws SQLException;
}
