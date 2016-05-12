package bank.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import bank.entity.JudgeRecord;

public interface JudgeRecordDao {
	
	public void insertRecord(JudgeRecord record) throws SQLException;
	public JudgeRecord getRecordByNY(String type,int year,String bankname) throws SQLException;//get record by year name type
	public boolean checkExist(String bankName,int year,String type)throws SQLException;
	public ArrayList<JudgeRecord> getAllRecords()throws SQLException;
	public void delete(String sql)throws SQLException;
}
