package bank.dataManage;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.UserInfo;
import bank.entity.Year;

public class YearTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "ÐòºÅ", "Äê·Ý" };

	public Class[] m_colTypes = { Integer.class, Integer.class };

	public Vector DataVector;

	public YearTableModel(Vector DataVector) {
		// TODO Auto-generated constructor stub
		super();
		this.DataVector = DataVector;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return DataVector.size();
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
	
	public void setValueAt(Object value, int row, int col) {
		Year year=(Year) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			year.setId((Integer)value);
			break;
		case 1:
			year.setYear((Integer)value);
			break;
		
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Year year = (Year) DataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return year.getId();
		case 1:
			return year.getYear();
		
		default:
			return new String();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
