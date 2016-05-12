package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import bank.entity.Bank;
import bank.util.Jdbc;

public class BankDaoImpl implements BankDao {

	private Connection con;
	@Override
	public List<Bank> getAllBank() throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from bank";
		PreparedStatement ps=con.prepareStatement(sql);
		ResultSet rs=ps.executeQuery();
		List<Bank> bankNames= new ArrayList<Bank>();
		while (rs.next()) {
			Bank tmp=new Bank();
			tmp.setRow_id(rs.getInt("row_id"));
			tmp.setId(rs.getInt("id"));
			tmp.setName(rs.getString("name"));
			bankNames.add(tmp);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return bankNames;
	}

	

	@Override
	public Bank getBankById(int bid) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		String sql="select * from bank where id=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setInt(1, bid);
		ResultSet rs=ps.executeQuery();
		Bank bank=new Bank();
		bank.setRow_id(rs.getInt("row_id"));
		bank.setId(rs.getInt("id"));
		bank.setName(rs.getString("name"));
		rs.close();
		ps.close();
		Jdbc.release(con);
		return bank;
	}
	
	public int getBankIdByName(String name) throws SQLException{
		con=Jdbc.getCon();
		String sql="select id from bank where name=?";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs=ps.executeQuery();
		
		int bid=(rs.getInt("id"));
		
		rs.close();
		ps.close();
		Jdbc.release(con);
		return bid;
		
	}

	//废弃
	@Override
	public boolean insertBank(String bankName) throws SQLException {
		// TODO Auto-generated method stub
		
		con=Jdbc.getCon();
		String sql="insert into bank(name,id) values(?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		
		List<Bank> banks=getAllBank();
		int max=banks.get(0).getId();
		for (int i = 0; i < banks.size(); i++) {
			if(banks.get(i).getId()>max)
				max=banks.get(i).getId();
		}
		
		ps.setString(1, bankName);
		ps.setInt(2, max+1);
		ps.execute();
		
		if(bankName.equals(getBankById(getBankIdByName(bankName)).getName())){
			
			ps.close();
			Jdbc.release(con);
			
			return true;
		}else {
			
			ps.close();
			Jdbc.release(con);
			
			return false;
		}
		
		
		
	}
	
	public void delete(String sql) throws SQLException{
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		BankDaoImpl tBankDaoImpl=new BankDaoImpl();
	//	System.out.println("dafds"+tBankDaoImpl.getBankIdByName("中国工商银行"));
	//	System.out.println(tBankDaoImpl.insertBank("hello"));
	}



	@Override
	public void insertBank(Bank bank) throws SQLException {
		// TODO Auto-generated method stub
		con=Jdbc.getCon();
		
		String sql="insert into bank (name,id) values(?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, bank.getName());
		ps.setInt(2, bank.getId());
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
		
		
	}
	
	public int checkExist(String name,int id)throws SQLException{
		//根据名字找
		//若已经存在返回1，若不存在返回0，若有名字但是id与新的不同则返回2
		con=Jdbc.getCon();
		
		String sql="select * from bank where name =?";
		
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1, name);
		
		ResultSet rs=ps.executeQuery();
		
		if (rs.next()) {
			if (id==rs.getInt("id")) {
				rs.close();
				ps.close();
				Jdbc.release(con);
				return 1;
			}else {
				rs.close();
				ps.close();
				Jdbc.release(con);
				
				return 2;
			}
		}else {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return 0;
		}
		
		
		
	}
	
}
