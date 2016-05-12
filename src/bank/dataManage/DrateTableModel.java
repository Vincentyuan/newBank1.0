package bank.dataManage;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.Bank;
import bank.entity.DRate;
import bank.entity.Year;

public class DrateTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "ÐòºÅ","ÆÀ¼¶","p1","p2" };

	public Class[] m_colTypes = { Integer.class, String.class,Double.class, Double.class};

	public Vector DataVector;

	public DrateTableModel(Vector DataVector) {
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
		DRate dRate=(DRate) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			dRate.setId((Integer)value);
			break;
		case 1:
			dRate.setName((String)value);
			break;
		case 2:
			dRate.setP1((Double)value);
			break;
		case 3:
			dRate.setP2((Double)value);
			
		
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		DRate dRate=(DRate) DataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return dRate.getId();
		case 1:
			return dRate.getName();
		case 2:
			return dRate.getP1();
		case 3:
			return dRate.getP2();
		
		default:
			return new String();
		}

	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
