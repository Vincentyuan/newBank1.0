package bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import bank.entity.JudgeRecord;
import bank.util.Jdbc;

public class JudgeRecordDaoImpl implements JudgeRecordDao {

	private Connection con;

	public JudgeRecordDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void insertRecord(JudgeRecord record) throws SQLException {
		// TODO Auto-generated method stub
		// System.out.println(record.getBankname()+" "+record.getLoan()+" "+record.getType());
		con = Jdbc.getCon();
		String sql = "insert into judgerecord(type,bankname,year,judger,time,deposit,"
				+ "loan,shortrate,multideposit,multiloan) values('"
				+ record.getType()
				+ "','"
				+ record.getBankname()
				+ "','"
				+ record.getYear()
				+ "','"
				+ record.getJudger()
				+ "','"
				+ record.getTime()
				+ "','"
				+ record.getDeposit()
				+ "','"
				+ record.getLoan()
				+ "','"
				+ record.getShortrate()
				+ "','"
				+ record.getMultideposit() + "','" + record.getMultiloan()
				/*
				 * + "','" +record.getShortRange() +"','" +record.getLongRange()
				 */
				+ "')";
		PreparedStatement ps = con.prepareStatement(sql);
		// add argues
		/*
		 * ps.setString(1, record.getType()); ps.setString(2,
		 * record.getBankname()); ps.setInt(3, record.getYear());
		 * ps.setString(4, record.getJudger()); ps.setDate(5, record.getTime());
		 * ps.setString(6, record.getDeposit()); ps.setString(7,
		 * record.getLoan()); ps.setString(8, record.getShortrate());
		 * ps.setString(9, record.getMultideposit()); ps.setString(10,
		 * record.getMultiloan());
		 */
		// excute the statement

		ps.execute();
		ps.close();
		Jdbc.release(con);

	}

	@Override
	public JudgeRecord getRecordByNY(String type, int year, String bankname)
			throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from judgerecord where type=? and year =?  and bankname =?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, type);
		ps.setInt(2, year);
		ps.setString(3, bankname);

		ResultSet rs = ps.executeQuery();
		// new one object to contain the data and return this object
		if (rs.next()) {
			JudgeRecord tmpJudgeRecord = new JudgeRecord();
			tmpJudgeRecord.setId(rs.getInt("id"));
			tmpJudgeRecord.setBankname(rs.getString("bankname"));
			tmpJudgeRecord.setYear(rs.getInt("year"));
			tmpJudgeRecord.setType(rs.getString("type"));
			tmpJudgeRecord.setJudger(rs.getString("judger"));
			// System.out.println(rs.getTimestamp(6));
			tmpJudgeRecord.setTime(rs.getTimestamp("time"));
			tmpJudgeRecord.setShortrate(rs.getString("shortrate"));
			tmpJudgeRecord.setDeposit(rs.getString("deposit"));
			tmpJudgeRecord.setLoan(rs.getString("loan"));
			tmpJudgeRecord.setMultideposit(rs.getString("multideposit"));
			tmpJudgeRecord.setMultiloan(rs.getString("multiloan"));
			tmpJudgeRecord.setShortRange(rs.getInt("shortrange"));
			tmpJudgeRecord.setLongRange(rs.getInt("longrange"));

			rs.close();
			ps.close();
			Jdbc.release(con);

			return tmpJudgeRecord;
		} else {
		//	System.out.println("null");
			rs.close();
			ps.close();
			Jdbc.release(con);
			return null;
		}
	}

	@Override
	public boolean checkExist(String bankname, int year, String type)
			throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from judgerecord where bankname=?and year=? and type=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, bankname);
		ps.setInt(2, year);
		ps.setString(3, type);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return true;
		}

		else {
			rs.close();
			ps.close();
			Jdbc.release(con);
			return false;
		}

	}

	public static void main(String[] args) throws SQLException {
		JudgeRecordDao tDao = new JudgeRecordDaoImpl();
		JudgeRecord r = new JudgeRecord("short", "中国工商银行", 1994, "yuanping",
				new Timestamp(System.currentTimeMillis()), "优", "良", "差", "",
				"", -1, -1);
		tDao.insertRecord(r);
		// System.out.println(tDao.getRecordByNY("short", 1994,
		// "中国工商银行").getDeposit());

		// JudgeRecord tmpJudgeRecord=tDao.getRecordByNY("short", year,
		// bankname)
	}

	@Override
	public ArrayList<JudgeRecord> getAllRecords() throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from judgerecord ";
		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		ArrayList<JudgeRecord> tmp = new ArrayList<>();
		// new one object to contain the data and return this object
		while (rs.next()) {
			JudgeRecord tmpJudgeRecord = new JudgeRecord();
			tmpJudgeRecord.setId(rs.getInt("id"));
			tmpJudgeRecord.setBankname(rs.getString("bankname"));
			tmpJudgeRecord.setYear(rs.getInt("year"));
			tmpJudgeRecord.setType(rs.getString("type"));
			tmpJudgeRecord.setJudger(rs.getString("judger"));
			// System.out.println(rs.getTimestamp(6));
			tmpJudgeRecord.setTime(rs.getTimestamp("time"));
			tmpJudgeRecord.setShortrate(rs.getString("shortrate"));
			tmpJudgeRecord.setDeposit(rs.getString("deposit"));
			tmpJudgeRecord.setLoan(rs.getString("loan"));
			tmpJudgeRecord.setMultideposit(rs.getString("multideposit"));
			tmpJudgeRecord.setMultiloan(rs.getString("multiloan"));
			tmpJudgeRecord.setShortRange(rs.getInt("shortrange"));
			tmpJudgeRecord.setLongRange(rs.getInt("longrange"));

			tmp.add(tmpJudgeRecord);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return tmp;

	}
	
	public void delete(String sql) throws SQLException{
		con=Jdbc.getCon();
		
		PreparedStatement ps=con.prepareStatement(sql);
		
		ps.execute();
		
		ps.close();
		Jdbc.release(con);
	}

}
