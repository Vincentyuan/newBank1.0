package bank.frame.panel;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.jfree.ui.RefineryUtilities;

import bank.frame.BaseFrame;
import bank.frame.MyPanel;

public class shortJudgeTendency extends BaseFrame {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenuItem range, grade, result;// 排名，等级，归一化结果。
	private String bankname;
	private String year;
	private MyPanel[] work; // workpanel的内容panel，不同的按钮更新不同的panel。

	public shortJudgeTendency(String bankname, String year) {
		// TODO Auto-generated constructor stub
		this.bankname = bankname;
		this.year = year;
		frame = new JFrame();
		menuBar = new JMenuBar();
		range = new JMenuItem("短期稳定性排名");
		grade = new JMenuItem("短期稳定性");
		result = new JMenuItem("流动性缺口率");
		work = new MyPanel[3];

		// check whether the image exist

		work[0] = new MyPanel("test/" + bankname + "流动性缺口率等级" + ".jpg");
		work[1] = new MyPanel("test/" + bankname +"短期" + "稳定性排名" + ".jpg");
		work[2] = new MyPanel("test/" + bankname + "短期" + "稳定性"  + ".jpg");

	}

	//init 方法，加载center指定的中间页面
	public void init(int center) {

		frame.setTitle("短期稳定性");

		menuBar.add(range);
		menuBar.add(grade);
		menuBar.add(result);
		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		container.add(work[center], BorderLayout.CENTER);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 700;
		int height = 600;
		
		//设定位置以及大小
		frame.setBounds((d.width - width) / 2, (d.height - height) / 2,
				700,400);
		
		frame.setVisible(true);
	}
//监听事件
	public void addActionListener() {
		result.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getContentPane().remove(1);
				init(0);
				// frame.revalidate();
				// frame.repaint();

			}
		});
		range.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// frame.getContentPane().removeAll();
				frame.getContentPane().remove(1);
				init(1);
				// frame.revalidate();
				// frame.repaint();
			}
		});
		grade.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// frame.getContentPane().removeAll();
				frame.getContentPane().remove(1);
				init(2);
				// frame.revalidate();
				// frame.repaint();

			}
		});
	}

	
	
	public boolean checkExist() {
		// check whether the image has existed.

		File file = new File("./test");
		String[] fileList = file.list();
		int total = 0;
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i].equals(bankname + "短期" + "稳定性" + ".jpg")
					|| fileList[i].equals(bankname + "短期" + "稳定性排名" + ".jpg")
					|| fileList[i].equals(bankname + "短期" + "稳定性等级" + ".jpg")) {

				total++;
			}
		}
		if (total == 3) {
			return true;
		} else {
			return false;
		}

	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		shortJudgeTendency s = new shortJudgeTendency("中国工商银行",
				String.valueOf(2006));
		s.init();
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		init(1);
		addActionListener();
	}

	
	
	
	
	
	//重新生成frame框架。弃用
	public void reGenerateFrame(JPanel panel) {

		menuBar.add(range);
		menuBar.add(grade);
		menuBar.add(result);
		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		JPanel initPanel = new MyPanel("test/" + bankname + "稳定性" + ".jpg");
		container.add(initPanel, BorderLayout.CENTER);
		frame.pack();
		RefineryUtilities.centerFrameOnScreen(frame);

		frame.setSize(500, 290);
		frame.setVisible(true);
		addActionListener();
		revalidate();
		repaint();

	}


}
