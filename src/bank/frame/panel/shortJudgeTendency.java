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
	private JMenuItem range, grade, result;// �������ȼ�����һ�������
	private String bankname;
	private String year;
	private MyPanel[] work; // workpanel������panel����ͬ�İ�ť���²�ͬ��panel��

	public shortJudgeTendency(String bankname, String year) {
		// TODO Auto-generated constructor stub
		this.bankname = bankname;
		this.year = year;
		frame = new JFrame();
		menuBar = new JMenuBar();
		range = new JMenuItem("�����ȶ�������");
		grade = new JMenuItem("�����ȶ���");
		result = new JMenuItem("������ȱ����");
		work = new MyPanel[3];

		// check whether the image exist

		work[0] = new MyPanel("test/" + bankname + "������ȱ���ʵȼ�" + ".jpg");
		work[1] = new MyPanel("test/" + bankname +"����" + "�ȶ�������" + ".jpg");
		work[2] = new MyPanel("test/" + bankname + "����" + "�ȶ���"  + ".jpg");

	}

	//init ����������centerָ�����м�ҳ��
	public void init(int center) {

		frame.setTitle("�����ȶ���");

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
		
		//�趨λ���Լ���С
		frame.setBounds((d.width - width) / 2, (d.height - height) / 2,
				700,400);
		
		frame.setVisible(true);
	}
//�����¼�
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
			if (fileList[i].equals(bankname + "����" + "�ȶ���" + ".jpg")
					|| fileList[i].equals(bankname + "����" + "�ȶ�������" + ".jpg")
					|| fileList[i].equals(bankname + "����" + "�ȶ��Եȼ�" + ".jpg")) {

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
		shortJudgeTendency s = new shortJudgeTendency("�й���������",
				String.valueOf(2006));
		s.init();
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		init(1);
		addActionListener();
	}

	
	
	
	
	
	//��������frame��ܡ�����
	public void reGenerateFrame(JPanel panel) {

		menuBar.add(range);
		menuBar.add(grade);
		menuBar.add(result);
		Container container = frame.getContentPane();
		container.add(menuBar, BorderLayout.NORTH);
		JPanel initPanel = new MyPanel("test/" + bankname + "�ȶ���" + ".jpg");
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
