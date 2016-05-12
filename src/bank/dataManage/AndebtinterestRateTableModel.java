package bank.dataManage;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.NatDebtRate;
import bank.entity.Year;

public class AndebtinterestRateTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "序号","年份", "三年期国债利率","五年期国债利率" };

	public Class[] m_colTypes = { Integer.class,Integer.class, Double.class, Double.class};

	public Vector DataVector;
	
	public DecimalFormat formatter;

	public AndebtinterestRateTableModel(Vector DataVector) {
		// TODO Auto-generated constructor stub
		super();
		this.DataVector = DataVector;
		formatter=new DecimalFormat("#.#########");
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
	
	public void setValueAt(Object value, int row, int col){
		NatDebtRate rate=(NatDebtRate) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			rate.setId((Integer)value);
			break;
		case 1:
			
			try {
				rate.setYear(new YearDaoImpl().getYearByYearId((Integer)value));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case 2:
			rate.setThreeyearrate((Double)value);
			break;
		case 3:
			rate.setFiveyearrate((Double)value);
	
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		NatDebtRate rate=(NatDebtRate) DataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return rate.getId();
		case 1:
			return rate.getYear();
			
		case 2:
			return rate.getThreeyearrate();
		case 3:
			return rate.getFiveyearrate();
		
		default:
			return new String();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
