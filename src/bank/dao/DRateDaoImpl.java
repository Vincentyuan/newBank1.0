package bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bank.entity.DRate;
import bank.util.Jdbc;

public class DRateDaoImpl implements DRateDao {

	private Connection con;

	@Override
	public List<DRate> getAllDRates() throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from drate";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		System.out.println(rs.getFetchSize() + " drate all");
		List<DRate> tmp = new ArrayList<DRate>();
		while (rs.next()) {
			DRate t = new DRate();
			t.setId(rs.getInt("id"));
			t.setName(rs.getString("name"));
			t.setP1(rs.getDouble("p1"));
			t.setP2(rs.getDouble("p2"));
			tmp.add(t);
		}
		rs.close();
		ps.close();
		Jdbc.release(con);
		return tmp;
	}

	@Override
	public DRate getDRateByDegree(String degree) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "select * from drate where name=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, degree);
		ResultSet rs = ps.executeQuery();
		DRate tmpDRate = new DRate();
		tmpDRate.setId(rs.getInt("id"));
		tmpDRate.setName(rs.getString("name"));
		tmpDRate.setP1(rs.getDouble("p1"));
		tmpDRate.setP2(rs.getDouble("p2"));
		rs.close();
		ps.close();
		Jdbc.release(con);
		return tmpDRate;
	}

	public void delete(String sql) throws SQLException {
		con = Jdbc.getCon();

		PreparedStatement ps = con.prepareStatement(sql);

		ps.execute();

		ps.close();
		Jdbc.release(con);
	}

	@Override
	public void insert(DRate dRate) throws SQLException {
		// TODO Auto-generated method stub
		con = Jdbc.getCon();
		String sql = "insert into drate (name,p1,p2)values(?,?,?)";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, dRate.getName());
		ps.setDouble(2, dRate.getP1());
		ps.setDouble(3, dRate.getP2());

		ps.executeUpdate();

		ps.close();
		Jdbc.release(con);
	}

	@Override
	public List<String> getAllDRatesName() throws SQLException {
		// TODO Auto-generated method stub

		con = Jdbc.getCon();
		String sqlString = "select * from drate";
		PreparedStatement psPreparedStatement = con.prepareStatement(sqlString);

		ResultSet rsResultSet = psPreparedStatement.executeQuery();

		List<String> dratenameList = new ArrayList<String>();
		while (rsResultSet.next()) {
			dratenameList.add(rsResultSet.getString("name"));
		}

		rsResultSet.close();
		psPreparedStatement.close();
		Jdbc.release(con);
		return dratenameList;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		DRateDao tDao = new DRateDaoImpl();
		tDao.getAllDRates();
		System.out.println(tDao.getAllDRatesName());

	}

}
