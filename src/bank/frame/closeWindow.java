package bank.frame;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import bank.frame.panel.longJudgePanel;

public class closeWindow extends BaseFrame {

	
	public JButton sureButton;
	public JButton cancelButton;
	public String suggestionString;
	
	private static closeWindow closeWindow;
	
	public static MainFrame frame;
	
	public closeWindow(  ) {
		// TODO Auto-generated constructor stub
		this.frame=frame;
//		frame.setEnabled(false);
		sureButton=new JButton("确认退出");
		cancelButton=new JButton("不退出");
		
		
	}
	
	public void init(){
	//	JFrame f=new JFrame();
	
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 200;
		int height = 80;
		this.setBounds((d.width - width) / 2, (d.height - height) / 2, width,
				height);
		
		this.setTitle("确认退出么？");
		this.setLayout(new GridLayout(1,2));
		this.add(cancelButton);
		this.add(sureButton);
		this.setVisible(true);
		
		
		addActionListener();
	}
	
	public void addActionListener(){
		sureButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public static closeWindow getInstance(MainFrame frame){
		if (closeWindow==null) {
			return new closeWindow();
		}else {
			return closeWindow;
		}
	}
	public static void main(String [] args){
		closeWindow c=closeWindow.getInstance(null);
		c.init();
				
		
		
	}

}
