package bank.frame;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bank.dao.UserDaoImpl;
import bank.dataManage.dataManagementPanel;
import bank.frame.panel.addPanel;
import bank.frame.panel.addShortDataPanel;
import bank.frame.panel.indexPanel;
import bank.frame.panel.longJudgePanel;
import bank.frame.panel.multiJudgePanel;
import bank.frame.panel.searchPanel;
import bank.frame.panel.shortJudgePanel;
import bank.frame.panel.workPanel;

public class MainFrame extends BaseFrame {

	private ClientContext context;
	private MyPanel mypanel;
	private JButton shortJudge, longJudge, multiJudge;

	private JButton index, addScreen, searchScreen, dataManagement, logOff;

	private JPanel north, west, workPanel;// borderlayout workPanel.center
											// 的主panel，
	private workPanel firstPanel;

	public MainFrame(ClientContext context) {
		// TODO Auto-generated constructor stub
		this.context = context;
		mypanel = new MyPanel("image/backGround3.jpg");
		shortJudge = new JButton("短期稳定性评估");
		longJudge = new JButton("长期稳定性评估");
		multiJudge = new JButton("综合稳定性评估");
		index = new JButton("首页");
		addScreen = new JButton("添加");
		searchScreen = new JButton("搜索");
		dataManagement = new JButton("数据库管理");
		logOff = new JButton("返回");
		workPanel = new JPanel();

		firstPanel = new indexPanel();

		north = new JPanel();
		north.setOpaque(false);
		west = new JPanel();
		west.setOpaque(false);
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		this.setTitle("银行排名软件（测试版）");
		this.setContentPane(mypanel);
		this.setVisible(true);
		this.setResizable(false);

		// enableEvents(AWTEvent.WINDOW_EVENT_MASK);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

				/*
				 * closeWindow close = closeWindow .getInstance((MainFrame)
				 * getObject()); close.init();
				 */// create a new window;

				int option = JOptionPane.showConfirmDialog(getObject(),
						"确定要退出么？", "询问", JOptionPane.YES_NO_OPTION,
						JOptionPane.INFORMATION_MESSAGE);

				if (option == JOptionPane.YES_OPTION) {
					super.windowClosing(e);
					closeWindow();
				} else {
					try {

						MainFrame frame = new MainFrame(context);
						frame.init();

					} catch (Exception e1) { // TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 400;
		this.setBounds((d.width - width) / 2, (d.height - height) / 2, width,
				height);

		mypanel.setLayout(new BorderLayout());

		north.setLayout(new FlowLayout(FlowLayout.RIGHT));
		north.add(index);
		north.add(addScreen);
		north.add(searchScreen);
		north.add(dataManagement);
		north.add(logOff);

		mypanel.add(north, BorderLayout.NORTH);

		west.setLayout(new GridLayout(3, 1, 0, 50));
		west.add(shortJudge);
		west.add(longJudge);
		west.add(multiJudge);

		mypanel.add(west, BorderLayout.WEST);
		mypanel.add(workPanel, BorderLayout.CENTER);
		workPanel.setOpaque(false);
		workPanel.setLayout(new BorderLayout());

		setBorderlayout(firstPanel);

		addListener();
	}

	private void addListener() {
		// TODO Auto-generated method stub
		index.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					changeToIndex(firstPanel);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		shortJudge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					changeToShortJudge();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		searchScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					changeToSearch();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		addScreen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					changeToAddPanel();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});
		longJudge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					changeToLongJudge();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		multiJudge.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					changeToMultJudge();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		dataManagement.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					if (new UserDaoImpl().checkAuthority(context.getUserName())) {
						changeToDataManagement();
					} else {
						JOptionPane.showMessageDialog(null, "您没有权限打开数据库");
					}

				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
	}

	public void setBorderlayout(workPanel work) throws Exception {
		// TODO Auto-generated method stub

		workPanel.add(work);
		work.init();

	}

	public void changeToIndex(workPanel work) throws Exception {
		// 根据按钮的顺序id
		workPanel.removeAll();
		setBorderlayout(work);
		workPanel.revalidate();
		repaint();
	}

	public void changeToShortJudge() throws Exception {
		workPanel.removeAll();
		setBorderlayout(shortJudgePanel.getInstance(this));
		workPanel.revalidate();
		repaint();
	}

	public void changeToAddPanel() throws Exception {
		workPanel.removeAll();
		setBorderlayout(addPanel.getInstance(this));
		workPanel.revalidate();
		repaint();
	}

	public void changeToLongJudge() throws Exception {
		workPanel.removeAll();
		setBorderlayout(longJudgePanel.getInstance(this));
		workPanel.revalidate();
		repaint();
	}

	public void changeToMultJudge() throws Exception {
		workPanel.removeAll();
		setBorderlayout(multiJudgePanel.getInstance(this));
		workPanel.revalidate();
		repaint();
	}

	public void changeToSearch() throws Exception {
		workPanel.removeAll();
		setBorderlayout(searchPanel.getInstance(this));
		workPanel.revalidate();
		repaint();
	}

	public void changeToDataManagement() throws Exception {
		workPanel.removeAll();
		setBorderlayout(new dataManagementPanel(this));
		workPanel.revalidate();
		repaint();
	}

	public JPanel getWorkPanel() {
		return workPanel;
	}

	public void setWorkPanel(JPanel workPanel) {
		this.workPanel = workPanel;
	}

	public ClientContext getClientContext() {
		return this.context;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MainFrame m = new MainFrame(null);
		m.init();

	}

	public Component getObject() {
		return this;
	}

	public void closeWindow() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
