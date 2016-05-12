package bank.dataManage;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.Bank;
import bank.entity.JudgeRecord;
import bank.entity.Year;

/*
 * 需要优化一下。
 */

public class JudgeRecordTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "序号", "评估类型","银行名","年份",
			"评估人","评估时间","短期评级","长期存款",
			"长期贷款","综合存款","综合贷款" };

	public Class[] m_colTypes = { Integer.class,String.class,String.class,Integer.class,
			String.class,Timestamp.class,String.class,String.class,
			String.class,String.class,String.class};

	public Vector DataVector;

	public JudgeRecordTableModel(Vector DataVector) {
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
		JudgeRecord record=(JudgeRecord) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			record.setId((Integer)value);
			break;
		case 1:
			record.setType((String)value);
			break;
		case 2:
			record.setBankname((String)value);
			break;
		case 3:
			record.setYear((Integer)value);
			break;
		case 4:
			record.setJudger((String)value);
			break;
		case 5:
			record.setTime((Timestamp)value);
			break;
		case 6:
			record.setShortrate((String)value);
			break;
		case 7:
			record.setDeposit((String)value);
			break;
		case 8:
			record.setLoan((String)value);
			break;
		case 9:
			record.setMultideposit((String)value);
			break;
		case 10:
			record.setMultiloan((String)value);
			break;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		JudgeRecord record=(JudgeRecord) DataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return record.getId();
		case 1:
			return record.getType();
		case 2:
			return record.getBankname();
		case 3:
			return record.getYear();
		case 4:
			return record.getJudger();
		case 5:
			return record.getTime();
		case 6:
			return record.getShortrate();
		case 7:
			return record.getDeposit();
		case 8:
			return record.getLoan();
		case 9:
			return record.getMultideposit();
		case 10:
			return record.getMultiloan();
		
		default:
			return new String();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
