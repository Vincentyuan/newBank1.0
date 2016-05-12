package test;

import javax.swing.JOptionPane;

import org.omg.CORBA.PUBLIC_MEMBER;

public class testOS {

	public testOS() {
		// TODO Auto-generated constructor stub
		String osname = System.getProperty("os.name").toLowerCase();

		JOptionPane.showMessageDialog(null, "the Operation System is :"+osname);

		
		if (osname.contains("windows")) {
			JOptionPane.showMessageDialog(null, "it is a windows ");

		} else {
			JOptionPane.showMessageDialog(null, "not a windows system.");
		}
		
	}
	public static void main(String [] args){
		String osname = System.getProperty("os.name").toLowerCase();

		JOptionPane.showMessageDialog(null, "the Operation System is :"+osname);

		
		if (osname.contains("windows")) {
			JOptionPane.showMessageDialog(null, "it is a windows ");

		} else {
			JOptionPane.showMessageDialog(null, "not a windows system.");
		}
	}

}
