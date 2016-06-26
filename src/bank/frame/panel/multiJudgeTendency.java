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
	private JMenuItem Deposit, Borrow;// 排名，等级，归一化结果。
	private JMenuItem rangeLoan, gradeLoan, resultLoan;
	private String bankname;
	private String year;
	private MyPanel[] work; // workpanel的内容panel，不同的按钮更新不同的panel。

	public multiJudgeTendency() {
		// TODO Auto-generated constructor stub
	}

	public multiJudgeTendency(String bankname, String year) {
		// TODO Auto-generated constructor stub
		this.bankname = bankname;
		this.year = year;
		frame = new JFrame();
		menuBar = new JMenuBar();

		Deposit = new JMenuItem("综合稳定性评级（存款维度）");
		Borrow = new JMenuItem("综合稳定性评级（贷款维度）");
		

		work = new MyPanel[2];
		// System.out.println("test/"+bankname+"长期存款归一化值"+".jpg");
		work[0] = new MyPanel("test/" + bankname + "综合稳定性评级（存款维度）"+year + "年.jpg");
		work[1] = new MyPanel("test/" + bankname + "综合稳定性评级（贷款维度）"+year + "年.jpg");
		
	

	}

	public void init(int center) {

		frame.setTitle("综合稳定性");
		
		

		menuBar.add(Deposit);
		menuBar.add(Borrow);

		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		// JPanel initPanel=new MyPanel("test/"+bankname+"归一化值"+".jpg");
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
		multiJudgeTendency s = new multiJudgeTendency("中国工商银行",
				String.valueOf(2006));
		s.init();
	}

	
}
