package bank.frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bank.dao.UserDao;
import bank.dao.UserDaoImpl;

//验证成功后开始初始化工作
public class Logon extends BaseFrame {

	private ClientContext context;
	private JPanel panel;
	private JTextField userName;
	private JPasswordField password;
	private JLabel user, passwd;

	private mKeyListener m1;

	private JButton submit, register;

	public Logon() {
		context = new ClientContext();
		panel = new MyPanel("image/backGround3.jpg");
		userName = new JTextField();
		password = new JPasswordField();
		password.setEchoChar('*');
		user = new JLabel("用户名:");
		passwd = new JLabel("密码:");
		submit = new JButton("登录");
		register = new JButton("注册");
		user.setFont(new Font("微软雅黑", Font.BOLD, 15));
		passwd.setFont(new Font("微软雅黑", Font.BOLD, 15));
		// System.out.println("new finish");

		m1 = new mKeyListener();

	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		// 布局文件重新写【2个函数，页面布局，和页面内容】 setLayout（）
		this.setTitle("用户登录");
		// this.setContentPane(panel);
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
		setComponent();
		addListener();
	}

	protected void setUnVisible() {
		// TODO Auto-generated method stub
		this.setVisible(false);

	}

	protected void loadMainFrame() throws Exception {
		// TODO Auto-generated method stub
		MainFrame main = new MainFrame(this.context);
		main.init();
	}

	public void setComponent() {

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int width = 600;
		int height = 400;
		this.setBounds((d.width - width) / 2, (d.height - height) / 2, width,
				height);

		panel.add(user);
		user.setBounds(100, 100, 140, 20);
		panel.add(userName);
		userName.setBounds(160, 100, 120, 20);
		panel.add(passwd);
		passwd.setBounds(100, 130, 140, 20);
		panel.add(password);
		password.setBounds(160, 130, 120, 20);

		panel.add(submit);
		submit.setBounds(100, 180, 80, 20);
		panel.add(register);
		register.setBounds(180, 180, 80, 20);
		// System.out.println("init finished");
	}

	private void addListener() {
		// TODO Auto-generated method stub

		submit.addKeyListener(m1);

		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// call function to process event
				submitFunction();
			}
		});

		register.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Register register = new Register();
				try {
					register.init();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setUnVisible();
			}
		});

		password.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				// System.out.println(new String(pwf.getPassword()));

				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					// call submit function.
					submitFunction();
				}
			}
		});

	}

	public void submitFunction() {
		// 判断并且跳转
		UserDao user = new UserDaoImpl();
		try {
			if (user.checkExist(getName())) {
				try {
					boolean checkResult = user.checkPassword(getName(),
							getPasswd());
					if (checkResult) {
						context.setUserName(getName());
						loadMainFrame();
						setUnVisible();
						// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						// System.exit(NORMAL);;
					} else {
						JOptionPane.showMessageDialog(null, "密码不正确");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				JOptionPane.showMessageDialog(null, "用户名不存在");
			}
		} catch (HeadlessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	public String getName() {
		return userName.getText().toString().trim();
	}

	public String getPasswd() {
		return password.getText().toString();
	}

	public class mKeyListener implements KeyListener {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 10) {
				System.out.println("You clicked button");
			}

		}

		public void keyTyped(KeyEvent e) {

		}

		public void keyReleased(KeyEvent e) {

		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logon l = new Logon();
		l.init();
	}

}
