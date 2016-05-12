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
			//һ��excel�ļ�
			jxl.Workbook rw = jxl.Workbook.getWorkbook(new File(sourcefilePath)); 
			
			//��ȡһ��sheet�����Ը��ݱ�Ż�ȡҲ���Ը������ƻ�ȡ
			
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
			
			
		/*	//���ĳһ�У���4��
			Cell[] cells = sheet0.getRow(0);
			for (Cell cell : cells) {
				System.out.println(cell.getContents());
			}
			
			//���һ��cell,(0,0)�������Ԫ���λ��
			Cell cell = sheet0.getCell(1,0);
			
			//������cell������
			String content = cell.getContents();
			
			//��ӡ���cell�������ݵ�����
			System.out.println(cell.getType());*/
			
			rw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}