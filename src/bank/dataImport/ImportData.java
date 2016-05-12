package bank.dataImport;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import jxl.Cell;
import jxl.Sheet;
import jxl.read.biff.BiffException;
import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.DRateDao;
import bank.dao.DRateDaoImpl;
import bank.dao.NatDebtRateDao;
import bank.dao.NatDebtRateImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.Bank;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.entity.DRate;
import bank.entity.Year;

public class ImportData {

	public ImportData() {
		// TODO Auto-generated constructor stub
	}

	public String getFilePath() throws Exception {

		// 创建文件选择器

		JFileChooser fileChooser = new JFileChooser();
		// 设置当前目录
		fileChooser.setCurrentDirectory(new File("."));
		fileChooser.setAcceptAllFileFilterUsed(false);
		final String[][] fileENames = { { ".xls", "MS-Excel 2003 文件(*.xls)" } };

		// 显示所有文件
		fileChooser.addChoosableFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return true;
			}

			public String getDescription() {
				return "所有文件(*.*)";
			}
		});

		// 循环添加需要显示的文件
		for (final String[] fileEName : fileENames) {

			fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
				public boolean accept(File file) {
					if (file.getName().endsWith(fileEName[0])
							|| file.isDirectory()) {
						return true;
					}
					return false;
				}

				public String getDescription() {
					return fileEName[1];
				}

			});
		}

		try {
			fileChooser.showDialog(null, null);
		} catch (Exception e) {
			// TODO: handle exception
		}

		// fileChooser.setVisible(true);
		// System.out.println(fileChooser.getSelectedFile().getAbsolutePath());
		if (fileChooser.getSelectedFile() == null)
			return null;
		else
			return fileChooser.getSelectedFile().getAbsolutePath();
	}

	public void insertBankData(Sheet sheet) throws SQLException {
		BankDao bankDao = new BankDaoImpl();
		Bank bank = new Bank();

		for (int i = 3; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);

			int exist = bankDao.checkExist(cells[0].getContents().toString(),
					Integer.valueOf(cells[1].getContents().toString()));

			switch (exist) {
			case 0:
				if (!isEmpty(cells)) {
					bank.setName(cells[0].getContents().toString());
					bank.setId(Integer.valueOf(cells[1].getContents()
							.toString()));

					bankDao.insertBank(bank);
				} else {
					JOptionPane.showMessageDialog(null, "银行数据有空白");
				}
				break;
			case 1:
				break;
			case 2:
				JOptionPane.showMessageDialog(null, "请数据库内银行数据再重新导入");
				break;

			default:
				break;
			}

		}
	}

	public void insertYearData(Sheet sheet) throws NumberFormatException,
			SQLException {
		YearDao yearDao = new YearDaoImpl();
		for (int i = 3; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);

			if (!yearDao.checkExist(Integer.valueOf(cells[0].getContents()
					.toString()))) {
				if (!isEmpty(cells)) {
					yearDao.insertYear(Integer.valueOf(cells[0].getContents()
							.toString()));
				}

			} else {

			}

		}

	}

	public void insertDrate(Sheet sheet) throws SQLException {

		DRateDao dRateDao = new DRateDaoImpl();

		DRate dRate = new DRate();
		java.util.List<String> drateNames=dRateDao.getAllDRatesName();

		for (int i = 2; i < sheet.getRows(); i++) {

			Cell[] cells = sheet.getRow(i);

			dRate.setName(cells[0].getContents().toString());
			dRate.setP1(Double.valueOf(cells[1].getContents().toString()));
			dRate.setP2(Double.valueOf(cells[2].getContents().toString()));

			boolean exist=false;
			for (String nameString : drateNames) {
				if (dRate.getName().equals(nameString)) {
					exist=true;
				}
			}
			if (!exist) {
				dRateDao.insert(dRate);
			}
			

		}

	}

	public void insertAndebtinterestRate(Sheet sheet)
			throws Exception {
		NatDebtRateDao natDebtRateDao = new NatDebtRateImpl();
		YearDao yearDao = new YearDaoImpl();
		java.util.List<Year> yearList = yearDao.getAllYear();
		int[] years = new int [yearList.size()];

		
		
		for (int i = 0; i < yearList.size(); i++) {
			years[i] = yearList.get(i).getYear();

		}

		for (int i = 2; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);

			if (!isEmpty(cells)) {
				// if year not in the list
				boolean inYear = false;

				for (int year : years) {
					if (year == Integer.valueOf(cells[0].getContents()
							.toString())) {
						
						inYear = true;
					}
				}

				if (!inYear) {
					yearDao.insertYear(Integer.valueOf(cells[0].getContents()
							.toString()));
				}
				
				
				//check exist 
				if (!natDebtRateDao.checkExist(Integer.valueOf(cells[0].getContents()
						.toString()))) {
					//insert the data 
					natDebtRateDao.insertDRate(
							Integer.valueOf(cells[0].getContents().toString()),
							Double.valueOf(cells[1].getContents().toString()),
							Double.valueOf(cells[2].getContents().toString()));

				}
				
			} else {
				JOptionPane.showMessageDialog(null, "国债利率表有空白！请查案第" + i + "行");
			}

		}

	}

	public void insertShortData(Sheet sheet) throws NumberFormatException,
			SQLException, Exception {
		BankShortDataDao bankShortDataDao = new BankShortDataDaoImpl();

		BankShortData bankShortData = new BankShortData();

		for (int i = 3; i < sheet.getRows(); i++) {
			Cell[] cells = sheet.getRow(i);
			if (!isEmpty(cells)) {
				if (!bankShortDataDao.checkExist(
						Integer.valueOf(cells[1].getContents().toString()),
						Integer.valueOf(cells[2].getContents().toString()))) {

					// insert bank id and year

					bankShortData.setBid(Integer.valueOf(cells[1].getContents()
							.toString()));
					bankShortData.setYear(Integer.valueOf(cells[2]
							.getContents().toString()));

					bankShortData.setLess_fiveloan_recycle(Double
							.valueOf(cells[3].getContents().toString()));
					bankShortData.setMore_fiveloan_recycle(Double
							.valueOf(cells[4].getContents().toString()));
					bankShortData.setLess_fivy_b((Double.valueOf(cells[5]
							.getContents().toString())));
					bankShortData.setMore_fivy_b(Double.valueOf(cells[6]
							.getContents().toString()));

					bankShortData.setLess_thrm_b(Double.valueOf(cells[7]
							.getContents().toString()));

					bankShortData.setLess_twim_b(Double.valueOf(cells[8]
							.getContents().toString()));
					bankShortData.setSr_mon(Double.valueOf(cells[9]
							.getContents().toString()));

					bankShortData.setLess_thrm_s(Double.valueOf(cells[10]
							.getContents().toString()));
					bankShortData.setLess_twim_s(Double.valueOf(cells[11]
							.getContents().toString()));

					bankShortData.setLess_fivy_s(Double.valueOf(cells[12]
							.getContents().toString()));
					bankShortData.setMore_fivy_s(Double.valueOf(cells[13]
							.getContents().toString()));

					bankShortDataDao.insertShortData(bankShortData);

				} else {
					// 重复处理？
				}

			} else {
				JOptionPane.showMessageDialog(null, "短期数据中有空白");
			}

		}

	}

	public void insertLongData(Sheet sheet) throws Exception {

		BankLongDataDao longDataDao = new BankLongDataImpl();

		BankLongData longData = new BankLongData();
		for (int i = 3; i < sheet.getRows(); i++) {

			Cell[] cells = sheet.getRow(i);

			if (!isEmpty(cells)) {
				//System.out.println(cells[1].getContents().toString()+" "+cells[2].getContents().toString());
				//System.out.println("exist?" +longDataDao.checkExist(
				//		Integer.valueOf(cells[1].getContents().toString()),
				//		Integer.valueOf(cells[2].getContents().toString())));
				
				if (!longDataDao.checkExist(
						Integer.valueOf(cells[1].getContents().toString()),
						Integer.valueOf(cells[2].getContents().toString()))) {
					// insert the bank and year to the database

					longData.setBank_Id(Integer.valueOf(cells[1].getContents()
							.toString()));
					longData.setYear(Integer.valueOf(cells[2].getContents()
							.toString()));
					longData.setIB_Asset(Double.valueOf(cells[3].getContents()
							.toString()));
					longData.setLoan(Double.valueOf(cells[4].getContents()
							.toString()));
					longData.setInvestment(Double.valueOf(cells[5]
							.getContents().toString()));
					longData.setIn_Center_Bank(Double.valueOf(cells[6]
							.getContents().toString()));
					longData.setCall_Loan_ToBan(Double.valueOf(cells[7]
							.getContents().toString()));
					longData.setFund_Raise_Amount(Double.valueOf(cells[8]
							.getContents().toString()));
					longData.setBorrowing(Double.valueOf(cells[9].getContents()
							.toString()));
					longData.setIssue_Bonds(Double.valueOf(cells[10]
							.getContents().toString()));
					longData.setDeposit(Double.valueOf(cells[11].getContents()
							.toString()));
					longData.setR1_Loan(Double.valueOf(cells[12].getContents()
							.toString()));
					
				//	System.out.println("bank id :"+longData.getBank_Id()+"year:"+longData.getYear()+"row 12 :"+cells[12].getContents()
				//			.toString());
					longData.setR2_Investment(Double.valueOf(cells[13]
							.getContents().toString()));
					
					
					longData.setR3_C_EmFunds(Double.valueOf(cells[14]
							.getContents().toString()));
					longData.setR4_Offer(Double.valueOf(cells[15].getContents()
							.toString()));
					longData.setR5_Borrow(Double.valueOf(cells[16]
							.getContents().toString()));
					longData.setR6_Bond(Double.valueOf(cells[17].getContents()
							.toString()));
					longData.setR7_Deposit(Double.valueOf(cells[18]
							.getContents().toString()));
					longData.setTaking(Double.valueOf(cells[19].getContents()
							.toString()));
					
					longData.setExtra_Deposit(Double.valueOf(cells[20]
							.getContents().toString()));
					longData.setActual_Loan_Amount(Double.valueOf(cells[21]
							.getContents().toString()));

					longDataDao.addBankLongData(longData);
				} else {
				}
			} else {
				JOptionPane.showMessageDialog(null, "长期数据中有空白");
			}

		}

	}

	public void saveData() throws NumberFormatException, Exception {

		String filePath = getFilePath();
		if (filePath == null) {
			return;
		} else {

			jxl.Workbook rw = null;
			try {
				rw = jxl.Workbook.getWorkbook(new File(filePath));

				String fileName = getFileName(filePath);

	//			System.out.println(filePath + "  " + fileName + "  "
	//					+ fileName.equals("shortDataTemplate.xls"));

				
				switch (fileName) {
				case "shortDataTemplate.xls":

					int shortLength = checkSheetNumber(rw);
					for (int i = 0; i < shortLength; i++) {

						String sheetName = rw.getSheet(i).getName();
						if (isAYear(sheetName)) {
							insertShortData(rw.getSheet(i));
						} else {

							switch (sheetName) {
							case "AverageNationDebtRate":
								insertAndebtinterestRate(rw.getSheet(i));
								break;
							case "CreditRate":
								insertDrate(rw.getSheet(i));
								break;
							default:
								break;
							}
						}
					}
					JOptionPane.showMessageDialog(null,
							"short data import finished");
					break;

				case "longDataTemplate.xls":

					int longLength = checkSheetNumber(rw);
					System.out.println(longLength);
					for (int i = 0; i < longLength; i++) {
					//	System.out.println("is a year"+isAYear(rw.getSheet(i).getName()));
					//	System.out.println(rw.getSheet(i));
						if (isAYear(rw.getSheet(i).getName())) {
							
							insertLongData(rw.getSheet(i));
						}else {
							JOptionPane.showMessageDialog(null,
									"the year:"+rw.getSheet(i).getName() +"is not in the database");
						}

					}
					JOptionPane.showMessageDialog(null,
							"long data import finished");
					break;
				case "bankYearData.xls":

					int bankYearData = checkSheetNumber(rw);
					for (int i = 0; i < bankYearData; i++) {
						if (rw.getSheet(i).getName().equals("bank")) {
							insertBankData(rw.getSheet(i));
						} else {
							insertYearData(rw.getSheet(i));
						}
					}

					JOptionPane.showMessageDialog(null,
							"year data import finished");
					break;

				default:
					JOptionPane.showMessageDialog(null, "这个消息可能是因为平台问题导致");
					break;
				}

				filePath = null;
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			rw.close();
		}
	}

	private int checkSheetNumber(jxl.Workbook rw) {
		// TODO Auto-generated method stub

		return rw.getNumberOfSheets();

	}

	public String getFileName(String url) {
		String[] s = null;

		String osname = System.getProperty("os.name").toLowerCase();

		System.out.println(osname);
		System.out.println(url + "\\");
		if (osname.contains("windows")) {
			File f = new File(url);
			return f.getName();

		} else {

			s = url.split("/");
			int length = s.length;

			return s[length - 1];
		}
	}

	public boolean isEmpty(Cell[] cells) {
		boolean status = false;
		for (int i = 0; i < cells.length; i++) {

			if (cells[i].getContents().toString().equals("")) {
				return true;
			}

		}
		return status;

	}

	public boolean isAYear(String tableName) {

		try {
			Integer.valueOf(tableName);
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
