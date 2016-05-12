package bank.frame;

import javax.swing.JFrame;

import bank.frame.panel.addShortDataPanel;
import bank.frame.panel.workPanel;

public abstract class BaseFrame extends JFrame{
	ClientContext context;
	
	public abstract void init() throws Exception;
	public void setBorderlayout(workPanel workPanel) throws Exception {
		// TODO Auto-generated method stub
		
	}
	public void changePanelContaine(workPanel workPanel) {
		// TODO Auto-generated method stub
		
	}
}
