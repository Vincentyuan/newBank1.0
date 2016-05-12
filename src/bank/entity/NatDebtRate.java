package bank.entity;

public class NatDebtRate {
	
	private int id;
	private int year;
	private double  threeyearrate,fiveyearrate;
	

	public NatDebtRate() {
		// TODO Auto-generated constructor stub
	}
	


	public NatDebtRate(int id, int year, double threeyearrate,
			double fiveyearrate) {
		super();
		this.id = id;
		this.year = year;
		this.threeyearrate = threeyearrate;
		this.fiveyearrate = fiveyearrate;
	}



	/**
	 * @return the id
	 */
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
	 * @return the year
	 */
	public int getYear() {
		return year;
	}


	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}


	/**
	 * @return the threeyearrate
	 */
	public double getThreeyearrate() {
		return threeyearrate;
	}


	/**
	 * @param threeyearrate the threeyearrate to set
	 */
	public void setThreeyearrate(double threeyearrate) {
		this.threeyearrate = threeyearrate;
	}


	/**
	 * @return the fiveyearrate
	 */
	public double getFiveyearrate() {
		return fiveyearrate;
	}


	/**
	 * @param fiveyearrate the fiveyearrate to set
	 */
	public void setFiveyearrate(double fiveyearrate) {
		this.fiveyearrate = fiveyearrate;
	}
	

}
