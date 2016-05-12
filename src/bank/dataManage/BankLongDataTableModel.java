package bank.dataManage;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.entity.Year;

public class BankLongDataTableModel extends AbstractTableModel implements TableModel {

	public String[] m_colNames = { "序号", "银行","年份","生息资产",
			"贷款","投资","存放央行","拆分同业",
			"计息负债","同业拆入","发放债券",	"存款",
			"平均贷款率","投资收益率","缴央行准备金率","拆出平均利率",
			"平均拆入率","平均债券利率","平均存款利率",
			"营业支出","实际存款额","实际贷款额"};

	public Class[] m_colTypes = { Integer.class, String.class, Integer.class,Double.class,
			Double.class,Double.class,Double.class,Double.class,
			Double.class,Double.class,Double.class,Double.class,
			Double.class,Double.class,Double.class,Double.class,
			Double.class,Double.class,Double.class,
			Double.class,Double.class,Double.class};

	public Vector DataVector;

	public BankLongDataTableModel(Vector DataVector) {
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
		BankLongData bank=(BankLongData) DataVector.elementAt(row);
		
		switch (col) {
		case 0:
			bank.setId((Integer)value);
			break;
		case 1:
			bank.setBankName((String)value);
			break;
		case 2:
			try {
				bank.setYear((Integer)value);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			bank.setIB_Asset((Double)value);
			break;
		case 4:
			bank.setLoan((Double)value);
			break;
		case 5:
			bank.setInvestment((Double)value);
			break;
		case 6:
			bank.setIn_Center_Bank((Double)value);
			break;
		case 7:
			bank.setCall_Loan_ToBan((Double)value);
			break;
		case 8:
			bank.setFund_Raise_Amount((Double)value);
			break;
		case 9:
			bank.setBorrowing((Double)value);
			break;
		case 10:
			bank.setIssue_Bonds((Double)value);
			break;
		case 11:
			bank.setDeposit((Double)value);
			break;
		case 12:
			bank.setR1_Loan((Double)value);
			break;
		case 13:
			bank.setR2_Investment((Double)value);
			break;
		case 14:
			bank.setR3_C_EmFunds((Double)value);
			break;
		case 15:
			bank.setR4_Offer((Double)value);
			break;
		case 16:
			bank.setR5_Borrow((Double)value);
			break;
		case 17:
			bank.setR6_Bond((Double)value);
			break;
		case 18:
			bank.setR7_Deposit((Double)value);
			break;
		case 19:
			bank.setTaking((Double)value);
			break;
		case 20:
			bank.setExtra_Deposit((Double)value);
			break;
		case 21:
			bank.setActual_Loan_Amount((Double)value);
			break;
			
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
	//	System.out.println(DataVector.size());
		BankLongData bank=(BankLongData) DataVector.elementAt(rowIndex);
		switch (columnIndex) {
		case 0:
			return bank.getId();
		case 1:
			return bank.getBankName();
		case 2:
			return bank.getYear();
		case 3:
			return bank.getIB_Asset();
		case 4:
			return bank.getLoan();
		case 5:
			return bank.getInvestment();
		case 6:
			return bank.getIn_Center_Bank();
		case 7:
			return bank.getCall_Loan_ToBan();
		case 8:
			return bank.getFund_Raise_Amount();
		case 9:
			return bank.getBorrowing();
		case 10:
			return bank.getIssue_Bonds();
		case 11:
			return bank.getDeposit();
		case 12:
			return bank.getR1_Loan();
		case 13:
			return bank.getR2_Investment();
		case 14:
			return bank.getR3_C_EmFunds();
		case 15:
			return bank.getR4_Offer();
		case 16:
			return bank.getR5_Borrow();
		case 17:
			return bank.getR6_Bond();
		case 18:
			return bank.getR7_Deposit();
		case 19:
			return bank.getTaking();
		case 20:
			return bank.getExtra_Deposit();
		case 21:
			return bank.getActual_Loan_Amount();
		default:
			return new String();
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	

}
