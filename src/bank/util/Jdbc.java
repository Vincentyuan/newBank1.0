package bank.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Jdbc {
	public static Connection getCon(){
		String driver = "org.sqlite.JDBC";
		
		String url = "jdbc:sqlite:dataBase/bank.db";
		try {
			Class.forName(driver);
			
			return DriverManager.getConnection(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static void release(Connection con){
		if (con != null){
			try {
				con.close();
				return ;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return;
	}

}
