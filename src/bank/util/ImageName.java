package bank.util;

public class ImageName {

	public ImageName() {
		// TODO Auto-generated constructor stub
	}
	//use title as the name of pic and the key 
	public static String [] getLongNameArray(String bankName){
		String [] longName =  new String[6];
		longName[0] = bankName + "长期稳定性排名(存款维度)";
		longName[1] = bankName + "长期稳定性等级(存款维度)";
		longName[2] = bankName + "存款安全边际率";
		longName[3] = bankName + "长期稳定性排名(贷款维度)";
		longName[4] = bankName + "长期稳定性等级(贷款维度)";
		longName[5] = bankName + "贷款安全边际率";
		
		return longName;
	}
	
	public static String [] getShortNameArray(String bankName){
		String [] shortName = new String[3];
		shortName[0] = bankName + "短期稳定性排名";
		shortName[1] = bankName + "短期稳定性等级";
		shortName[2] = bankName + "流动性缺口率";
		return shortName;
	}
	public static String [] getComprehensiveNameArray(){
		String [] compreName = new String [2];
		
		return compreName;
	}

}
