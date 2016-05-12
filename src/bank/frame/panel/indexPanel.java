package bank.frame.panel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class indexPanel extends workPanel{
	
	private JLabel index;
	private JPanel header,contain;
	private JTextArea info;
	public indexPanel(){
		String content="评估说明：\n    本软件主要用于评估银行稳定性"
				+ "，分别从短期、长期及综合三个方面，对每家银"
				+ "进行评估，最后根据每家银行的评估结果划分"
				+ "等级。同时，可以根据每家银行累积的评估结"
				+ "果，以图表形式反应其未来可能的变化趋势。";
		index=new JLabel("欢迎使用");
	//	this.setBorder(new BorderLayout());
		//this.add(header,BorderLayout.NORTH);
		//this.add(contain,BorderLayout.CENTER);
	//	this.setBorder(new GridLayout(2, 1));
		header=new JPanel();
		header.setOpaque(false);
		contain=new JPanel();
		contain.setOpaque(false);
		info=new JTextArea(content,10,35);
		info.setLineWrap(true);

	}
	private void setBorder(BorderLayout borderLayout) {
		// TODO Auto-generated method stub
		
	}
	public void init(){
		this.setBounds(0, 0, 400, 400);
		this.setOpaque(false);
		this.add(header);
		header.add(index);
		this.add(contain);
		contain.add(info);
	}

}
