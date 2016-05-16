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
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import bank.dao.BankLongDataDao;
import bank.dao.BankLongDataImpl;
import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.BankLongData;
import bank.entity.JudgeRecord;
import bank.frame.ClientContext;
import bank.frame.MainFrame;
import bank.judge.Judge;
import bank.judge.entity.longJudgeEntity;
import bank.judge.entity.shortJudgeEntity;
import bank.util.ImageEntry;
import bank.util.ImageHandle;

public class longJudgeReport extends workPanel {

	private JLabel header, bankl, bank, yearl, year, depositl, deposit, loanl,
			loan, judgerl, judger, timel, time;

	private JButton generateImage, tendency, save;

	private JPanel head, mid, bottom, white;
	
	private JPanel longjugeJPanel,midRestJPanel;

	private JudgeRecord record;

	private longJudgeTendency showImage;

	private MainFrame frame;

	public longJudgeReport(JudgeRecord record) {
		// TODO Auto-generated constructor stub
		// this.frame=frame;
		this.record = record;
		header = new JLabel("银行长期稳定性报告");
		bankl = new JLabel("    银行:");
		bank = new JLabel(record.getBankname());
		yearl = new JLabel("    年份:");
		year = new JLabel(String.valueOf(record.getYear()));
		depositl = new JLabel("    长期稳定性（存款维度）:");
		deposit = new JLabel(record.getDeposit());
		loanl = new JLabel("    长期稳定性（贷款维度）:");
		loan = new JLabel(record.getLoan());
		judgerl = new JLabel("    评定人:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    时间:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		generateImage = new JButton("生成图片");
		tendency = new JButton("趋势图");
		save = new JButton("保存");

		this.setOpaque(false);

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		white = new JPanel();
		white.setOpaque(false);
		
		this.longjugeJPanel=new JPanel();
		this.longjugeJPanel.setOpaque(false);
		
		this.midRestJPanel=new JPanel();
		this.midRestJPanel.setOpaque(false);

	}

	public void init() {
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new GridLayout(6, 1));
		this.add(head);
		this.add(mid);
		this.add(longjugeJPanel);
		this.add(midRestJPanel);
		this.add(bottom);
		this.add(white);

		head.add(header);

		mid.setLayout(new GridLayout(1, 4));
		mid.add(bankl);
		mid.add(bank);
		mid.add(yearl);
		mid.add(year);
		
		longjugeJPanel.setLayout(new GridLayout(2,2));
		longjugeJPanel.add(depositl);
		longjugeJPanel.add(deposit);
		longjugeJPanel.add(loanl);
		longjugeJPanel.add(loan);
		
		midRestJPanel.setLayout(new GridLayout(1,4));
		midRestJPanel.add(judgerl);
		midRestJPanel.add(judger);
		midRestJPanel.add(timel);
		midRestJPanel.add(time);

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
					JOptionPane.showMessageDialog(getObject(), "数据库读取有问题");
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
						setShowImage(new longJudgeTendency(
								record.getBankname(), String.valueOf(record
										.getYear())));

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
				try {
					generateTendencyImage();
					JOptionPane.showMessageDialog(null, "图片生成成功");

					setShowImage(null);

				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});

	}

	public JudgeRecord toJudgeRecord() {
		return this.record;
	}

	public Component getObject() {
		return this;
	}

	public boolean checkExist() throws SQLException {
		JudgeRecordDao tmpDao = new JudgeRecordDaoImpl();

		return tmpDao
				.checkExist(record.getBankname(), record.getYear(), "long");
	}

	public void generateTendencyImage() throws Exception {

		// 生成图片，一共6个。result range grade.

		ImageEntry resultDeposit = new ImageEntry();
		ImageEntry resultLoan = new ImageEntry();
		ImageEntry rangeDeposit = new ImageEntry();
		ImageEntry rangeLoan = new ImageEntry();
		ImageEntry gradeDeposit = new ImageEntry();
		ImageEntry gradeLoan = new ImageEntry();

		String banknameString = record.getBankname();
		String yearString = String.valueOf(record.getYear());

		resultDeposit.setBankName(banknameString);
		resultDeposit.setYear(yearString);
		resultDeposit.setImageName(banknameString + "存款安全边际率");
		try {
			resultDeposit.setAbscissa(getYearArray());
			resultDeposit.setOrdinate(getOrdinateResultDeposit());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		resultLoan.setBankName(banknameString);
		resultLoan.setYear(yearString);
		resultLoan.setImageName(banknameString + "贷款安全边际率");
		try {
			resultLoan.setAbscissa(getYearArray());
			resultLoan.setOrdinate(getOrdinateResultLoan());
			
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		rangeDeposit.setBankName(banknameString);
		rangeDeposit.setYear(yearString);
		rangeDeposit.setImageName(banknameString + "长期存款" + "稳定性排名(存款维度)");
		try {
			rangeDeposit.setAbscissa(getYearArray());
			rangeDeposit.setOrdinate(getOrdinateRangeDeposit());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		rangeLoan.setBankName(banknameString);
		rangeLoan.setYear(yearString);
		rangeLoan.setImageName(banknameString + "长期贷款" + "稳定性排名(贷款维度)");
		try {
			rangeLoan.setAbscissa(getYearArray());
			rangeLoan.setOrdinate(getOrdinateRangeLoan());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		gradeDeposit.setBankName(banknameString);
		gradeDeposit.setYear(yearString);
		gradeDeposit.setImageName(banknameString + "长期存款" + "稳定性等级(存款维度)");
		try {
			gradeDeposit.setAbscissa(getYearArray());
			gradeDeposit.setOrdinate(getOrdinateGradeDeposit());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		gradeLoan.setBankName(banknameString);
		gradeLoan.setYear(yearString);
		gradeLoan.setImageName(banknameString + "长期贷款" + "稳定性等级(贷款维度)");
		try {
			gradeLoan.setAbscissa(getYearArray());
			gradeLoan.setOrdinate(getOrdinateGradeLoan());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// JOptionPane.showMessageDialog(null, "Data collection finished");
		ImageHandle handle = new ImageHandle();
		try {
			handle.saveImage(resultDeposit);
			handle.saveImage(rangeDeposit);
			handle.saveImage(gradeDeposit);
			handle.saveImage(resultLoan);
			handle.saveImage(rangeLoan);
			handle.saveImage(gradeLoan);
			// JOptionPane.showMessageDialog(null, "image generate finished");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		/*
		 * longJudgeTendency l=new longJudgeTendency(banknameString,
		 * yearString); try { l.init(); } catch (Exception e1) { // TODO
		 * Auto-generated catch block e1.printStackTrace(); }
		 */

	}

	public int[] getYearArray() throws SQLException {
		YearDao yearDao = new YearDaoImpl();
		int[] year = new int[yearDao.getAllYear().size()];

		for (int i = 0; i < yearDao.getAllYear().size(); i++) {
			year[i] = yearDao.getAllYear().get(i).getYear();
			// System.out.println("year["+i+"]"+year[i]);
		}

		return year;
	}

	public double[] getOrdinateResultDeposit() throws NumberFormatException,
			Exception {

		double[] tmp = new double[getYearArray().length];

		longJudgeEntity longJudgeEntity = new longJudgeEntity();
		longJudgeEntity.setBankname(record.getBankname());
		longJudgeEntity.setYear(record.getYear());
		tmp = longJudgeEntity.getDepositResultRange();

		return tmp;

	}

	public double[] getOrdinateResultLoan() throws Exception {
		
		double[] tmp = new double[getYearArray().length];

		longJudgeEntity longJudgeEntity = new longJudgeEntity();
		longJudgeEntity.setBankname(record.getBankname());
		longJudgeEntity.setYear(record.getYear());

		tmp = longJudgeEntity.getLoanResultRange();

		return tmp;
	}

	public double[] getOrdinateRangeDeposit() throws Exception {
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];

		longJudgeEntity entity = new longJudgeEntity();
		entity.setBankname(record.getBankname());
		entity.setYear(Integer.valueOf(String.valueOf(record.getYear())));

		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = entity.getRangeDeposit()[i];
		}
		return tmp;

	}

	public double[] getOrdinateRangeLoan() throws Exception {
		longJudgeEntity entity = new longJudgeEntity();
		entity.setBankname(record.getBankname());
		entity.setYear(Integer.valueOf(String.valueOf(record.getYear())));
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];
		for (int i = 0; i < tmp.length; i++) {
			// System.out.println(i+"cycling result");
			tmp[i] = entity.getRangeLoan()[i];

		}
		return tmp;
	}

	public double[] getOrdinateGradeDeposit() throws Exception {

		longJudgeEntity entity = new longJudgeEntity();
		entity.setBankname(record.getBankname());
		entity.setYear(Integer.valueOf(String.valueOf(record.getYear())));
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];

		for (int i = 0; i < tmp.length; i++) {
			//
			// 差，中，良，较优，优
			// System.out.println(entity.toShortJudgeRecord().getShortrate());

			switch (entity.getRangeGradeDeposit()[i]) {
			case "差":
				tmp[i] = 0;
				break;
			case "中":
				tmp[i] = 1;
				break;
			case "良":
				tmp[i] = 2;
				break;
			case "较优":
				tmp[i] = 3;
				break;
			case "优":
				tmp[i] = 4;
				break;

			default:
				tmp[i] = -1;
				break;
			}
		}

		return tmp;
	}

	public double[] getOrdinateGradeLoan() throws Exception {

		longJudgeEntity entity = new longJudgeEntity();
		entity.setBankname(record.getBankname());
		entity.setYear(Integer.valueOf(String.valueOf(record.getYear())));
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];

		for (int i = 0; i < tmp.length; i++) {
			//
			// 差，中，良，较优，优
			// System.out.println(entity.toShortJudgeRecord().getShortrate());
			switch (entity.getRangeGradeLoan()[i]) {
			case "差":
				tmp[i] = 0;
				break;
			case "中":
				tmp[i] = 1;
				break;
			case "良":
				tmp[i] = 2;
				break;
			case "较优":
				tmp[i] = 3;
				break;
			case "优":
				tmp[i] = 4;
				break;

			default:
				tmp[i] = -1;
				break;
			}
		}

		return tmp;
	}

	/**
	 * @return the showImage
	 */
	public longJudgeTendency getShowImage() {
		return showImage;
	}

	/**
	 * @param showImage
	 *            the showImage to set
	 */
	public void setShowImage(longJudgeTendency showImage) {
		this.showImage = showImage;
	}

	public boolean checkImageExist() {
		// check whether the image has existed.

		File file = new File("./test");
		String[] fileList = file.list();
		int total = 0;
		for (int i = 0; i < fileList.length; i++) {

			if (fileList[i].equals(record.getBankname() + "贷款安全边际率" + ".jpg")
					|| fileList[i].equals(record.getBankname() + "存款安全边际率"
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "长期存款稳定性排名(存款维度)" //ok
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "长期贷款稳定性排名(贷款维度)"//ok
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "长期存款稳定性等级(存款维度)"//ok
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "长期贷款稳定性等级(贷款维度)"//ok
							+ ".jpg")) {

				total++;
			}
		}
		System.out.println("total matched :" +total +" size of files in test :"+fileList.length);
		if (total == 6) {
			return true;
		} else {
			return false;
		}

	}

}
