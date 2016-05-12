package bank.entity;

public class UserInfo {

	private int id;
	private String nameString;
	private String passwdString;
	private int position; //1为管理员，
	
	public UserInfo() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public UserInfo(int id,String nameString, String passwdString,int positon) {
		super();
		this.nameString = nameString;
		this.passwdString = passwdString;
		this.id = id;
		this.position=positon;
	}



	/**
	 * @return the nameString
	 */
	public String getNameString() {
		return nameString;
	}
	/**
	 * @param nameString the nameString to set
	 */
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	/**
	 * @return the passwdString
	 */
	public String getPasswdString() {
		return passwdString;
	}
	/**
	 * @param passwdString the passwdString to set
	 */
	public void setPasswdString(String passwdString) {
		this.passwdString = passwdString;
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
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}



	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}
	

}
