package bank.entity;

public class Bank {
	private int row_id;
	private int id;
	private String name;
	
	public Bank(){
		
	}
	public Bank(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Bank(String text) {
		// TODO Auto-generated constructor stub
		this.name=text;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the row_id
	 */
	public int getRow_id() {
		return row_id;
	}
	/**
	 * @param row_id the row_id to set
	 */
	public void setRow_id(int row_id) {
		this.row_id = row_id;
	}
	
}
