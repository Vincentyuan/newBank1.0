package bank.frame.panel;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.entity.JudgeRecord;
import bank.frame.ClientContext;
import bank.util.ImageEntry;
import bank.util.ImageHandle;

public class multiJudgeReport extends workPanel {

	private JLabel header, bankl, bank, yearl, year, shortJLabel1, shortJLabel,
			depositl, deposit, loanl, loan, judgerl, judger, timel, time;

	private JButton generateImage, tendency, save;

	private multiJudgeTendency showImage;

	private JPanel head, mid, bottom, white;
	
	private JPanel midOneLineJPanel,midEndJPanel;

	private JudgeRecord multi;

	public multiJudgeReport(JudgeRecord multi) {
		// TODO Auto-generated constructor stub
		// this.frame=frame;
		this.multi = multi;

		header = new JLabel("银行综合稳定性报告");
		bankl = new JLabel("    银行:");
		bank = new JLabel(multi.getBankname());
		yearl = new JLabel("    年份:");
		year = new JLabel(String.valueOf(multi.getYear()));
		shortJLabel1 = new JLabel("    短期稳定性评级");
		shortJLabel = new JLabel(multi.getShortrate());
		depositl = new JLabel("    综合稳定性评级（存款维度）");
		deposit = new JLabel(multi.getMultideposit());
		loanl = new JLabel("    综合稳定性评级（贷款维度）");
		loan = new JLabel(multi.getMultiloan());
		judgerl = new JLabel("    评定人:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    时间:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		tendency = new JButton("趋势图");
		generateImage = new JButton("生成图片");
		save = new JButton("保存");
		 

		this.setOpaque(false);

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		midOneLineJPanel=new JPanel();
		midOneLineJPanel.setOpaque(false);
		midEndJPanel=new JPanel();
		midEndJPanel.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		white = new JPanel();
		white.setOpaque(false);

	}

	public void init() {
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new GridLayout(6, 1));
		this.add(head);
		this.add(mid);
		this.add(midOneLineJPanel);
		this.add(midEndJPanel);
		this.add(bottom);
		this.add(white);

		head.add(header);

		mid.setLayout(new GridLayout(2, 4));
		mid.add(bankl);
		mid.add(bank);
		mid.add(yearl);
		mid.add(year);
		mid.add(shortJLabel1);
		mid.add(shortJLabel);
		mid.add(new JLabel());
		mid.add(new JLabel());
		
		midOneLineJPanel.setLayout(new GridLayout(2,2));
		midOneLineJPanel.add(depositl);
		
		midOneLineJPanel.add(deposit);
		midOneLineJPanel.add(loanl);
		midOneLineJPanel.add(loan);
		
		midEndJPanel.setLayout(new GridLayout(1,4));
		
		midEndJPanel.add(judgerl);
		midEndJPanel.add(judger);
		midEndJPanel.add(timel);
		midEndJPanel.add(time);

		// bottom.add(tendency);
		bottom.add(generateImage);
		bottom.add(tendency);
		bottom.add(save);
		addListener();
	}

	public void addListener() {
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JudgeRecordDao dao = new JudgeRecordDaoImpl();
				try {
					if (!checkExist()) {
						try {
							dao.insertRecord(toJudgeRecord());
							JOptionPane.showMessageDialog(getObject(), "保存成功");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(getObject(), "保存失败");
							e1.printStackTrace();
						}
					} else {
						JOptionPane
								.showMessageDialog(getObject(), "您已经保存过该记录了");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getObject(), "数据库连接问题");
				}

			}
		});

		tendency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {

					if (checkImageExist() == false) {
						JOptionPane.showMessageDialog(null, "请先生成图片");

					} else {
						setShowImage(new multiJudgeTendency(

						multi.getBankname(), String.valueOf(multi.getYear())));

						showImage.init();
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});

		generateImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// generate the graph
				ImageHandle imageHandle = new ImageHandle();

				ImageEntry depositEntry = getDepositEntry();

				try {
					imageHandle.saveImage(depositEntry);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				ImageEntry borrowEntry = getBorrowEntry();

				try {
					imageHandle.saveImage(borrowEntry);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "图片生成成功");
			}
		});
	}

	public JudgeRecord toJudgeRecord() {
		return this.multi;
	}

	public Component getObject() {
		return this;
	}

	public ImageEntry getDepositEntry() {
		ImageEntry entry = new ImageEntry();
		entry.setBankName(multi.getBankname());
		entry.setYear(String.valueOf(multi.getYear()));
		entry.setImageName(multi.getBankname()+"综合稳定性评级（存款维度）"+ multi.getYear()+"年");
		entry.setAbscissa(multi.getArrayShort());
		entry.setOrdinate(multi.getArrayDeposit());
		
		/*int [] s=multi.getArrayShort();
		for (int i : s) {
			System.out.println("短期数组"+" " +i);
		}
		

		double[] D=multi.getArrayDeposit();
		for (double i : D) {
			System.out.println("长期存款数组" +" " +i);
		}
		*/
		return entry;

	}

	public ImageEntry getBorrowEntry() {
		ImageEntry entry = new ImageEntry();
		entry.setBankName(multi.getBankname());
		entry.setYear(String.valueOf(multi.getYear()));
		entry.setImageName(multi.getBankname()  + "综合稳定性评级（贷款维度）"+ multi.getYear()+"年");
		entry.setAbscissa(multi.getArrayShort());
		entry.setOrdinate(multi.getArrayBorrow());
		
		/*System.out.println("短期数组"+multi.getArrayShort());
		System.out.println("长期贷款"+multi.getArrayBorrow());
		
		
		double[] B=multi.getArrayBorrow();
		for (double i : B) {
			System.out.println("长期贷款数组" +" " +i);
		}
		*/
		return entry;

	}

	public void setShowImage(multiJudgeTendency multi) {
		this.showImage = multi;
	}

	public boolean checkImageExist() {
		// check whether the image has existed.

		File file = new File("./test");
		String[] fileList = file.list();
		int total = 0;
		for (int i = 0; i < fileList.length; i++) {

			if (fileList[i].equals(multi.getBankname() + "综合稳定性评级（存款维度）"
					+ multi.getYear() + "年.jpg")
					|| fileList[i].equals(multi.getBankname() + "综合稳定性评级（贷款维度）"
							+ multi.getYear() + "年.jpg")) {

				total++;
			}
		}
		if (total == 2) {
			return true;
		} else {
			return false;
		}

	}

	public boolean checkExist() throws SQLException {
		JudgeRecordDao tmpDao = new JudgeRecordDaoImpl();

		return tmpDao.checkExist(multi.getBankname(), multi.getYear(), "multi");
	}

}
