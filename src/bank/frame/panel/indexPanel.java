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
		String content="����˵����\n    �������Ҫ�������������ȶ���"
				+ "���ֱ�Ӷ��ڡ����ڼ��ۺ��������棬��ÿ����"
				+ "����������������ÿ�����е������������"
				+ "�ȼ���ͬʱ�����Ը���ÿ�������ۻ���������"
				+ "������ͼ����ʽ��Ӧ��δ�����ܵı仯���ơ�";
		index=new JLabel("��ӭʹ��");
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
