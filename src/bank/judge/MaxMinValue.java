package bank.judge;

import java.util.List;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.judge.entity.shortJudgeEntity;

public class MaxMinValue {
	
	public static double maxShort;
	public static double minShort;
	public static double maxLongDeposit;
	public static double minLongDeposit;
	public static double maxLongLoan;
	public static double minLongLoan;
	public static shortJudgeEntity userInput;
	public static int status;

	public MaxMinValue() {
		// TODO Auto-generated constructor stub
	}
	
	public static void getShortMaxMin(shortJudgeEntity entity) throws Exception{//compute the max and min for short 
		BankShortDataDao tool=new BankShortDataDaoImpl();
		List<BankShortData> banks=tool.getShortBankDataByYear(entity.getYear());
	
		double max=Judge.gapShort(banks.get(0),entity);  //initialize
		double min=Judge.gapShort(banks.get(0),entity);
		double tmp;
		
		
		for (BankShortData bank:banks) {
			tmp=Judge.gapShort(bank,entity);
			
			if(tmp>max){
				max=tmp;
			}
			else if (tmp<min) {
				min=tmp;
			}
				
			
		}
		MaxMinValue.maxShort=max;
		MaxMinValue.minShort=min;
		/*System.out.println(entity.getYear()+"Äê·Ý");
		System.out.println("max "+max+" year "+entity.getYear());
		System.out.println("min "+min+" year "+entity.getYear());*/
	}
	//max and min year 
	public static void getLongDepositMaxMin(int year) throws Exception{//compute the max and min for long
		BankLongDataDao tool=new BankLongDataImpl();
		List<BankLongData> banks=tool.getAllBankLongDataByYear(year);
		
		double max=Judge.gapLongDeposit(banks.get(0));
		double min=Judge.gapLongDeposit(banks.get(0));
		double tmp;
		
		
		for(BankLongData bank:banks){
			tmp=Judge.gapLongDeposit(bank);
			if(tmp>max)
				max=tmp;
			else if (tmp<min) 
				min=tmp;
		}
		MaxMinValue.maxLongDeposit=max;
		MaxMinValue.minLongDeposit=min;
	}
	public static void getLongLoanMaxMin(int year) throws Exception{
		BankLongDataDao tool=new BankLongDataImpl();
		List<BankLongData> banks=tool.getAllBankLongDataByYear(year);
		
		double max=Judge.gapLongLoan(banks.get(0));
		double min=Judge.gapLongLoan(banks.get(0));
		double tmp;
		
		
		for(BankLongData bank:banks){
			tmp=Judge.gapLongLoan(bank);
			
			if(tmp>max)
				max=tmp;
			else if (tmp<min) 
				min=tmp;
		}
		MaxMinValue.maxLongLoan=max;
		MaxMinValue.minLongLoan=min;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
