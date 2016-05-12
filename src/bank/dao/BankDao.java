package bank.dao;

import java.sql.SQLException;
import java.util.List;

import bank.entity.Bank;

public interface BankDao {

	public List<Bank> getAllBank() throws SQLException;
	

	public Bank getBankById(int bid) throws SQLException;
	public int getBankIdByName(String name)throws SQLException;
	
	public boolean insertBank(String bankName) throws SQLException;
	public void delete(String sql)throws SQLException;
	
	public void insertBank(Bank bank)throws SQLException;
	public int checkExist(String bankName,int id)throws SQLException;
}
