package bank.frame;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import bank.dao.UserDao;
import bank.dao.UserDaoImpl;

public class Register extends BaseFrame {

	private ClientContext context;
	private MyPanel panel;
	private JTextField userName;

	private JPasswordField password;
	private JPasswordField password1;
	private JLabel user, passwd, passwd1;

	private JButton sure, cancel;

	public Register() {
		// TODO Auto-generated constructor stub
		context = new ClientContext();
		panel = new MyPanel("image/backGround3.jpg");
		userName = new JTextField();
		password = new JPasswordField();
		password.setEchoChar('*');
		password1 = new JPasswordField();
		password1.setEchoChar('*');
		user = new JLabel("用户名:");
		passwd = new JLabel("密码:");
		passwd1 = new JLabel("确认密码");
		sure = new JButton("确定");
		cancel = new JButton("取消");
		user.setFont(new Font("微软雅黑", Font.BOLD, 15));
		passwd.setFont(new Font("微软雅黑", Font.BOLD, 15));
		passwd1.setFont(new Font("微软雅黑", Font.BOLD, 15));
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		this.setTitle("用户注册");
		this.setContentPane(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);

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
		panel.add(passwd1);
		passwd1.setBounds(100, 160, 120, 20);
		panel.add(password1);
		password1.setBounds(160, 160, 120, 20);

		panel.add(sure);
		sure.setBounds(100, 210, 80, 20);
		panel.add(cancel);
		cancel.setBounds(180, 210, 80, 20);

		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Logon logon = new Logon();
				logon.init();
				setUnVisible();

			}
		});

		sure.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (getName().length() > 0
						&& (getPasswd().equals(password1.getText().toString()))) {
					UserDao add = new UserDaoImpl();
					try {
						if (add.checkExist(getName())) {
							JOptionPane.showMessageDialog(getObject(), "用户已存在");
						} else {
							boolean addresult = add.addUser(getName(),
									getPasswd());
							if (addresult) {
								int option = JOptionPane.showConfirmDialog(
										getObject(), "注册成功", " ",
										JOptionPane.DEFAULT_OPTION,
										JOptionPane.INFORMATION_MESSAGE);
								if (option == JOptionPane.YES_OPTION) {
									// System.out.println("finished");
									Logon o = new Logon();
									o.init();
									setUnVisible();
								}
							} else {
								JOptionPane.showMessageDialog(getObject(),
										"注册失败");
							}
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(getObject(), "用户名为空或密码不一致");
				}

			}
		});

	}

	protected void setUnVisible() {
		// TODO Auto-generated method stub
		this.setVisible(false);

	}

	public String getName() {
		return userName.getText().toString().trim();
	}

	public String getPasswd() {
		return password.getText().toString().trim();
	}

	public Component getObject() {
		return this;
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Register register = new Register();
		register.init();
	}
}
