package bank.dataManage;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.Bank;
import bank.entity.Year;

public class BankTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "ÐòºÅ", "ÒøÐÐÃû" };

	public Class[] m_colTypes = { Integer.class,String.class};
	

	public Vector DataVector;

	public BankTableModel(Vector DataVector) {
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
		Bank bank=(Bank) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			bank.setId((Integer)value);
			break;
		case 1:
			bank.setName((String)value);
			break;
	
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Bank bank=(Bank) DataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return bank.getId();
		case 1:
			return bank.getName();
		
		default:
			return new String();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
