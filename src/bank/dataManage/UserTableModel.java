package bank.dataManage;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.UserInfo;

public class UserTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "序号", "用户名", "密码","权限" };

	public Class[] m_colTypes = { Integer.class, String.class, String.class ,String.class};

	public Vector userDataVector;

	public UserTableModel(Vector userDataVector) {
		// TODO Auto-generated constructor stub
		super();
		this.userDataVector = userDataVector;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return userDataVector.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return m_colNames.length;
	}

	public String getColumnName(int col) {
		return m_colNames[col];
	}

	public Class getColumnClass(int col) {
		return m_colTypes[col];
	}
	
	public void setValueAt(Object value, int row, int col,int auth) {
		UserInfo userInfo=(UserInfo) userDataVector.elementAt(row);
		
		switch (col) {
		case 0:
			userInfo.setId((Integer)value);
			break;
		case 1:
			userInfo.setNameString((String)value);
			break;
		case 2:
			userInfo.setPasswdString((String)value);
			break;
		case 3:
			userInfo.setPosition(auth);
		
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		UserInfo userInfo = (UserInfo) userDataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return userInfo.getId();
		case 1:
			return userInfo.getNameString();
		case 2:
			return userInfo.getPasswdString();
		case 3:
			return userInfo.getPosition();
		default:
			return new String();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public UserInfo getRow(int row){
		UserInfo userInfo=(UserInfo) userDataVector.elementAt(row);
		
		return userInfo;
	}

}
