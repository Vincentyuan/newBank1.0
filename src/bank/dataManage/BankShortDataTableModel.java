package bank.dataManage;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.Bank;
import bank.entity.BankShortData;
import bank.entity.Year;

public class BankShortDataTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "序号", "银行","年份",
			"存款准备金","0-3个月贷款",	"0-3个月存款",
			"3-12个月贷款","3-12个月存款","1-5年贷款","1-5年存款",
			"5年以上贷款","5年以上存款" };

	public Class[] m_colTypes = { Integer.class,String.class,Integer.class,
			Double.class, Double.class,Double.class,
			Double.class,Double.class,Double.class,Double.class,
			Double.class,Double.class};

	public Vector DataVector;

	public BankShortDataTableModel(Vector DataVector) {
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
		BankShortData bank=(BankShortData) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			bank.setSbid((Integer)value);
			break;
		case 1:
			bank.setBankname((String)value);
			break;
		case 2:
			bank.setYear((Integer)value);
			break;
		case 3:
			bank.setSr_mon((Double)value);
			break;
		case 4:
			bank.setLess_thrm_b((Double)value);
			break;
		case 5:
			bank.setLess_thrm_s((Double)value);
			break;
		case 6:
			bank.setLess_twim_b((Double)value);
			break;
		case 7:
			bank.setLess_twim_s((Double)value);
			break;
		case 8:
			bank.setLess_fivy_b((Double)value);
			break;
		case 9:
			bank.setLess_fivy_s((Double)value);
			break;
		case 10:
			bank.setMore_fivy_b((Double)value);
			break;
		case 11:
			bank.setMore_fivy_s((Double)value);
			break;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		BankShortData bank=(BankShortData) DataVector.elementAt(rowIndex);
		
		switch (columnIndex) {
		case 0:
			return bank.getSbid();
		
		case 1:
			return bank.getBankname();
		case 2:
			return bank.getYear();
		case 3:
			return bank.getSr_mon();
		case 4:
			return bank.getLess_thrm_b();
		case 5:
			return bank.getLess_thrm_s();
		case 6:
			return bank.getLess_twim_b();
		case 7:
			return bank.getLess_twim_s();
		case 8:
			return bank.getLess_fivy_b();
		case 9:
			return bank.getLess_fivy_s();
		case 10:
			return bank.getMore_fivy_b();
		case 11:
			return bank.getMore_fivy_s();
			
		default:
			return new String("");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
