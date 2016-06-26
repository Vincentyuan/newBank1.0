package bank.frame.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import bank.frame.BaseFrame;
import bank.frame.MyPanel;

public class multiJudgeTendency extends BaseFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem Deposit, Borrow;// �������ȼ�����һ�������
	private JMenuItem rangeLoan, gradeLoan, resultLoan;
	private String bankname;
	private String year;
	private MyPanel[] work; // workpanel������panel����ͬ�İ�ť���²�ͬ��panel��

	public multiJudgeTendency() {
		// TODO Auto-generated constructor stub
	}

	public multiJudgeTendency(String bankname, String year) {
		// TODO Auto-generated constructor stub
		this.bankname = bankname;
		this.year = year;
		frame = new JFrame();
		menuBar = new JMenuBar();

		Deposit = new JMenuItem("�ۺ��ȶ������������ά�ȣ�");
		Borrow = new JMenuItem("�ۺ��ȶ�������������ά�ȣ�");
		

		work = new MyPanel[2];
		// System.out.println("test/"+bankname+"���ڴ���һ��ֵ"+".jpg");
		work[0] = new MyPanel("test/" + bankname + "�ۺ��ȶ������������ά�ȣ�"+year + "��.jpg");
		work[1] = new MyPanel("test/" + bankname + "�ۺ��ȶ�������������ά�ȣ�"+year + "��.jpg");
		
	

	}

	public void init(int center) {

		frame.setTitle("�ۺ��ȶ���");
		
		

		menuBar.add(Deposit);
		menuBar.add(Borrow);

		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		// JPanel initPanel=new MyPanel("test/"+bankname+"��һ��ֵ"+".jpg");
		container.add(work[center], BorderLayout.CENTER);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 700;
		int height = 700;
		
		frame.setBounds((d.width - width) / 2, (d.height - height) / 2,
				630,630);
		
		frame.setSize(460, 505);
		frame.setVisible(true);

		// container.add(panel,BorderLayout.CENTER);
	}

	public void init() throws Exception {
		// TODO Auto-generated method stub
	//	System.out.println("init method has been called");
	
		init(0);
		addActionListener();
	}

	public void addActionListener() {
		Deposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(0);
				frame.revalidate();
				frame.repaint();

			}
		});
		Borrow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);

				init(1);
			}
		});

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		multiJudgeTendency s = new multiJudgeTendency("�й���������",
				String.valueOf(2006));
		s.init();
	}

	
}
