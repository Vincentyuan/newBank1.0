package test;
import java.io.*;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.entity.BankShortData;
import jxl.*;

public class Summary {

	public static void main(String[] args) {
		String sourcefilePath = "test/test1.xls";
		try{
			//一个excel文件
			jxl.Workbook rw = jxl.Workbook.getWorkbook(new File(sourcefilePath)); 
			
			//获取一个sheet，可以根据编号获取也可以根据名称获取
			
			BankShortDataDao bankShortDataDao=new BankShortDataDaoImpl();
			BankDao bankName=new BankDaoImpl();
			
			
			
			for (int i = 2006; i < 2014; i++) {
				
				Sheet sheet = rw.getSheet(String.valueOf(i));
				
				for (int j = 3; j < 19; j++) {
					Cell [] cells=sheet.getRow(j);
					
					BankShortData shortData=bankShortDataDao
							.getShortBankDataByBYId(Integer.valueOf(cells[1].getContents()),
									Integer.valueOf(cells[2].getContents()));
					shortData.setLess_fiveloan_recycle(Double.valueOf(cells[11].getContents()));
					shortData.setMore_fiveloan_recycle(Double.valueOf(cells[12].getContents()));
					
					bankShortDataDao.update(shortData);
					
					
				}
				
				
				
			}
			
			
		/*	//获得某一行，第4行
			Cell[] cells = sheet0.getRow(0);
			for (Cell cell : cells) {
				System.out.println(cell.getContents());
			}
			
			//获得一个cell,(0,0)是这个单元格的位置
			Cell cell = sheet0.getCell(1,0);
			
			//获得这个cell的内容
			String content = cell.getContents();
			
			//打印这个cell所存数据的内容
			System.out.println(cell.getType());*/
			
			rw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}