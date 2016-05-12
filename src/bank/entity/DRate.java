package bank.entity;

import bank.dao.BankDaoImpl;


public class DRate {

	private int id;
	private String name;
	private double p1;
	private double p2;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	
	
	
	public DRate(int id,String name, double p1,double p2) {
		// TODO Auto-generated constructor stub
		this.id=id;
		this.name=name;
		this.p1=p1;
		this.p2=p2;
	}
	public DRate()
	{
		
	}
	/**
	 * @return the id
	 */
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * @return the p1
	 */
	public double getP1() {
		return p1;
	}
	/**
	 * @param p1 the p1 to set
	 */
	public void setP1(double p1) {
		this.p1 = p1;
	}
	/**
	 * @return the p2
	 */
	public double getP2() {
		return p2;
	}
	/**
	 * @param p2 the p2 to set
	 */
	public void setP2(double p2) {
		this.p2 = p2;
	}
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BankDaoImpl b=new BankDaoImpl();
		//System.out.println((b.getDRate("AAA"))[1]);
	}

}
