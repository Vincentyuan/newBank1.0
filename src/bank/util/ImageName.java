package bank.util;

public class ImageName {

	public ImageName() {
		// TODO Auto-generated constructor stub
	}
	//use title as the name of pic and the key 
	public static String [] getLongNameArray(String bankName){
		String [] longName =  new String[6];
		longName[0] = bankName + "�����ȶ�������(���ά��)";
		longName[1] = bankName + "�����ȶ��Եȼ�(���ά��)";
		longName[2] = bankName + "��ȫ�߼���";
		longName[3] = bankName + "�����ȶ�������(����ά��)";
		longName[4] = bankName + "�����ȶ��Եȼ�(����ά��)";
		longName[5] = bankName + "���ȫ�߼���";
		
		return longName;
	}
	
	public static String [] getShortNameArray(String bankName){
		String [] shortName = new String[3];
		shortName[0] = bankName + "�����ȶ�������";
		shortName[1] = bankName + "�����ȶ��Եȼ�";
		shortName[2] = bankName + "������ȱ����";
		return shortName;
	}
	public static String [] getComprehensiveNameArray(){
		String [] compreName = new String [2];
		
		return compreName;
	}

}
