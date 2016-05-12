package bank.judge;

import java.security.KeyStore.Entry;
import java.sql.SQLException;

import bank.dao.BankDao;
import bank.dao.BankDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.BankLongData;
import bank.entity.BankShortData;
import bank.judge.entity.shortJudgeEntity;
import bank.util.StaticValue;

public class Judge {

	public Judge() {
		// TODO Auto-generated constructor stub
	}

	public static double gapShort(BankShortData bank, shortJudgeEntity entity)
			throws SQLException {

		double e = 2.718281828;

		double afterMid = Math.pow(e, -3 * entity.getR1())
				* (bank.getLess_fivy_b()
						* (entity.getRecycleRate() * entity.getP1() + (1 - entity
								.getP1()) * entity.getPaymentRate()) - bank
						.getLess_fiveloan_recycle() * entity.getP1())
				+ Math.pow(e, -5 * entity.getR2())
				// +0.839876854
				* (bank.getMore_fivy_b()
						* (entity.getRecycleRate() * entity.getP2() + (1 - entity
								.getP2()) * entity.getPaymentRate()) - bank
						.getMore_fiveloan_recycle() * entity.getP2());
		double result = ((afterMid + bank.getLess_thrm_b()
				+ bank.getLess_twim_b() + bank.getSr_mon()) - (bank
				.getLess_thrm_s() + bank.getLess_twim_s() + entity
				.getMoreThanOneWithdrowRate()
				* (bank.getLess_fivy_s() + bank.getMore_fivy_s())))
				/ (afterMid + bank.getLess_thrm_b() + bank.getLess_twim_b() + bank
						.getSr_mon());
		// System.out.println("-3 "+Math.pow(e,-3*entity.getR1())+" r1 "+entity.getR1()+" -5 "+Math.pow(e,-5*entity.getR2())+"R2 "+entity.getR2());
		// System.out.println("aftermid"+afterMid+bank.getBankname()+bank.getYear());
		// BankDao tBankDao=new BankDaoImpl();
		// System.out.println(tBankDao.getBankById(bank.getBid()).getName()+" 缺口率： "+result);

		/*
		 * if (entity.getYear()==2006 && entity.getBankName().equals("中国工商银行"))
		 * { System.out.println(entity.getBankName()+entity.getYear());
		 * System.out.println(entity.getMoreThanOneWithdrowRate() + " " +
		 * entity.getBankDegree() + " " + entity.getRecycleRate() + " " +
		 * entity.getPaymentRate()); System.out.println("afterMid"+afterMid);
		 * System.out.println("result "+result); }
		 */
		
		//

		return result;
	}

	public static double gapLong(BankLongData bank) {

		double C = bank.getIB_Asset();
		double N = bank.getR1_Loan();
		double T = bank.getR4_Offer();
		double R = bank.getR3_C_EmFunds();
		double P = bank.getR2_Investment();
		double AA = bank.getTaking();
		double H = bank.getFund_Raise_Amount();
		double Z = bank.getR7_Deposit();
		double X = bank.getR6_Bond();
		double V = bank.getR5_Borrow();
		double AK = bank.getActual_Loan_Amount();

		double O = bank.getInvestment() / C * 100;
		double Q = bank.getIn_Center_Bank() / C * 100;
		double S = bank.getCall_Loan_ToBan() / C * 100;
		double U = bank.getBorrowing() / bank.getFund_Raise_Amount() * 100;
		double W = bank.getIssue_Bonds() / bank.getFund_Raise_Amount() * 100;
		double Y = bank.getDeposit() / bank.getFund_Raise_Amount() * 100;

		double AC = (U * V + W * X + Y * Z) / 100;
		double AI = ((AA + H * AC) / C - (O * P + Q * R + S * T) / 100) / N;

		double AJ = AI * C;

		double AL = (AK - AJ) / AK;

		return AL;

	}

	public static double gapLongDeposit(BankLongData bank) throws SQLException {

		double N = bank.getR1_Loan();
		double M = bank.getLoan() / bank.getIB_Asset() * 100;
		double O = bank.getInvestment() / bank.getIB_Asset() * 100;
		double P = bank.getR2_Investment();
		double Q = bank.getIn_Center_Bank() / bank.getIB_Asset() * 100;
		double R = bank.getR3_C_EmFunds();
		double S = bank.getCall_Loan_ToBan() / bank.getIB_Asset() * 100;
		double T = bank.getR4_Offer();
		double U = bank.getBorrowing() / bank.getFund_Raise_Amount() * 100;
		double V = bank.getR5_Borrow();
		double W = bank.getIssue_Bonds() / bank.getFund_Raise_Amount() * 100;
		double X = bank.getR6_Bond();
		double Z = bank.getR7_Deposit();
		double AA = bank.getTaking();
		double L = bank.getFund_Raise_Amount() / bank.getIB_Asset() * 100;
		double Y = bank.getDeposit() / bank.getFund_Raise_Amount() * 100;// 存款/计息负债
		double AG = bank.getDeposit();

		double AB = (M * N + O * P + Q * R + S * T) / 100;
		double AC = (U * V + W * X + Y * Z) / 100;
		double AD = AA / (AB / L * 100 - AC);
		double AF = AD * Y / 100;
//		System.out.println("ag"+AG);
//		System.out.println("af"+AF);

		double AH = (AG - AF) / AG * 100;

		return AH;
	}

	public static double gapLongLoan(BankLongData bank) throws SQLException {

		double AA = bank.getTaking();
		double H = bank.getFund_Raise_Amount();
		double C = bank.getIB_Asset();
		double P = bank.getR2_Investment();
		double R = bank.getR3_C_EmFunds();
		double T = bank.getR4_Offer();
		double N = bank.getR1_Loan();
		double AN = bank.getActual_Loan_Amount();
		double U = bank.getBorrowing() / bank.getFund_Raise_Amount() * 100;
		double V = bank.getR5_Borrow();
		double W = bank.getIssue_Bonds() / bank.getFund_Raise_Amount() * 100;
		double X = bank.getR6_Bond();
		double Y = bank.getDeposit() / bank.getFund_Raise_Amount() * 100;// 存款/计息负债
		double Z = bank.getR7_Deposit();

		double AC = (U * V + W * X + Y * Z) / 100;
		double O = bank.getInvestment() / bank.getIB_Asset() * 100;
		double Q = bank.getIn_Center_Bank() / bank.getIB_Asset() * 100;
		double S = bank.getCall_Loan_ToBan() / bank.getIB_Asset() * 100;
		double AL = ((AA + H * AC) / C - (O * P + Q * R + S * T) / 100) / N;
		double AM = AL * bank.getIB_Asset();
		double AO = (AN - AM) / AN * 100;

		
		return AO;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
