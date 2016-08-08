package bank.frame.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import bank.frame.BaseFrame;
import bank.frame.MyPanel;
import bank.util.ImageName;

public class longJudgeTendency extends BaseFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem rangeDeposit, gradeDeposit, resultDeposit;// 排名，等级，归一化结果。
	private JMenuItem rangeLoan, gradeLoan, resultLoan;
	private JMenu depositMenu, loanMenu;
	private String bankname;
	private String year;
	private MyPanel[] work; // workpanel的内容panel，不同的按钮更新不同的panel。

	private String [] imageName;
	public longJudgeTendency() {
		// TODO Auto-generated constructor stub
	}

	public longJudgeTendency(String bankname, String year) {
		// TODO Auto-generated constructor stub
		this.bankname = bankname;
		this.year = year;
		frame = new JFrame();
		menuBar = new JMenuBar();
		
		depositMenu=new JMenu("存款维度");
		loanMenu= new JMenu("贷款维度");
		

		rangeDeposit = new JMenuItem("长期稳定性排名(存款维度)");
		gradeDeposit = new JMenuItem("长期稳定性等级(存款维度)");
		resultDeposit = new JMenuItem("存款安全边际率");
		rangeLoan = new JMenuItem("长期稳定性排名(贷款维度)");
		gradeLoan = new JMenuItem("长期稳定性等级(贷款维度)");
		resultLoan = new JMenuItem("贷款安全边际率");
		
		imageName = ImageName.getLongNameArray(bankname);

		work = new MyPanel[6];
		// System.out.println("test/"+bankname+"长期存款归一化值"+".jpg");
		/*work[0] = new MyPanel("test/" + bankname + "存款安全边际率" + ".jpg");
		work[1] = new MyPanel("test/" + bankname + "贷款安全边际率" + ".jpg");
		work[2] = new MyPanel("test/" + bankname + "长期存款稳定性排名(存款维度)" + ".jpg");
		work[3] = new MyPanel("test/" + bankname + "长期贷款稳定性排名(贷款维度)" + ".jpg");
		work[4] = new MyPanel("test/" + bankname + "长期存款稳定性等级(存款维度)" + ".jpg");
		work[5] = new MyPanel("test/" + bankname + "长期贷款稳定性等级(贷款维度)" + ".jpg");
		*/
		for (int i = 0; i < imageName.length; i++) {
			work[i] = new MyPanel("test/"+imageName[i]+".jpg");
		}
	

	}

	public void init(int center) {

		frame.setTitle("长期稳定性");
		
		menuBar.add(depositMenu);
		menuBar.add(loanMenu);
		

		depositMenu.add(rangeDeposit);
		depositMenu.add(gradeDeposit);
		depositMenu.add(resultDeposit);
		loanMenu.add(rangeLoan);
		loanMenu.add(gradeLoan);
		loanMenu.add(resultLoan);

		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		// JPanel initPanel=new MyPanel("test/"+bankname+"归一化值"+".jpg");
		container.add(work[center], BorderLayout.CENTER);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);

//		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
//		int width = 700;
//		int height = 600;
//		
//		frame.setBounds((d.width - width) / 2, (d.height - height) / 2,
//				700,400);
		
		frame.setSize(720, 445);
		frame.setVisible(true);

		// container.add(panel,BorderLayout.CENTER);
	}

	public void init() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("init method has been called");
	
		init(0);
		addActionListener();
	}

	public void addActionListener() {
		resultDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(2);
				frame.revalidate();
				frame.repaint();

			}
		});
		resultLoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);

				init(5);
			}
		});
		rangeDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);

				init(0);
			}
		});
		rangeLoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(3);
			}
		});
		gradeDeposit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(1);
			}
		});
		gradeLoan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(4);
			}
		});

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		longJudgeTendency s = new longJudgeTendency("中国工商银行",
				String.valueOf(2006));
		s.init();
	}

	
}
