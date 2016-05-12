package bank.dao;

import java.sql.SQLException;
import java.util.List;

import bank.entity.BankLongData;

public interface BankLongDataDao {
	
	
	//��bank_long���в�����ó��ڵ��ȶ���������
	
	
	//�������
	public void addBankLongData(BankLongData bankLongData) throws Exception; 
	
	//����������id�����idȷ�����ݲ���
	public BankLongData getBankLongDataByBY(String name,int year) throws Exception;
	
	//���ҳ�����bankdata��¼
	public List<BankLongData> getAllBankLongData() throws Exception;
	//������ݲ����������м�¼
	
	public List<BankLongData> getAllBankLongDataByYear(int year)throws Exception;
	
	public void delete(String sql)throws SQLException;
	
	
	public boolean checkExist(int bid,int year)throws SQLException;
	
	public List<BankLongData> getLongBankDataByBank(String banknames)throws SQLException;
}
