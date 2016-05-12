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

import bank.dao.BankShortDataDao;
import bank.dao.BankShortDataDaoImpl;
import bank.dao.JudgeRecordDao;
import bank.dao.JudgeRecordDaoImpl;
import bank.dao.YearDao;
import bank.dao.YearDaoImpl;
import bank.entity.BankShortData;
import bank.entity.JudgeRecord;
import bank.entity.Year;
import bank.frame.ClientContext;
import bank.frame.panel.shortJudgePanel.inputData;
import bank.judge.Judge;
import bank.judge.entity.shortJudgeEntity;
import bank.util.ImageEntry;
import bank.util.ImageHandle;

public class shortJudgeReport extends workPanel {

	private JLabel header, bankl, bank, yearl, year, shortjudgel, shortjudge,
			judgerl, judger, timel, time;

	private JButton generateButton, tendency, save;

	private JPanel head, mid, bottom, white;

	private JudgeRecord record;

	private inputData userInputData;

	private shortJudgeTendency showImage;

	public shortJudgeReport(JudgeRecord record, inputData userInputData) {
		// TODO Auto-generated constructor stub
		this.record = record;
		this.userInputData = userInputData;
		header = new JLabel("�����ȶ��Զ�������");
		bankl = new JLabel("    ����:");
		bank = new JLabel(record.getBankname());
		yearl = new JLabel("    ���:");
		year = new JLabel(String.valueOf(record.getYear()));
		shortjudgel = new JLabel("�����ȶ���" + ":");
		shortjudge = new JLabel(record.getShortrate());
		judgerl = new JLabel("    ������:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    ʱ��:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		generateButton = new JButton("����ͼƬ");
		tendency = new JButton("����ͼ");
		save = new JButton("����");

		this.setOpaque(false);

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		white = new JPanel();
		white.setOpaque(false);

	}

	public shortJudgeReport(JudgeRecord record) {
		// TODO Auto-generated constructor stub
		this.record = record;
		header = new JLabel("�����ȶ��Զ�������");
		bankl = new JLabel("    ����:");
		bank = new JLabel(record.getBankname());
		yearl = new JLabel("    ���:");
		year = new JLabel(String.valueOf(record.getYear()));
		shortjudgel = new JLabel("�����ȶ���" + ":");
		shortjudge = new JLabel(record.getShortrate());
		judgerl = new JLabel("    ������:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    ʱ��:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));

		generateButton = new JButton("����ͼƬ");
		tendency = new JButton("����ͼ");
		save = new JButton("����");

		this.setOpaque(false);

		head = new JPanel();
		head.setOpaque(false);
		mid = new JPanel();
		mid.setOpaque(false);
		bottom = new JPanel();
		bottom.setOpaque(false);
		white = new JPanel();
		white.setOpaque(false);

	}

	public void init() {
		this.setBounds(0, 0, 400, 400);
		this.setLayout(new GridLayout(4, 1));
		this.add(head);
		this.add(mid);
		this.add(bottom);

		head.add(header);

		mid.setLayout(new GridLayout(3, 4));
		mid.add(bankl);
		mid.add(bank);
		mid.add(yearl);
		mid.add(year);
		mid.add(shortjudgel);
		mid.add(shortjudge);
		mid.add(new JLabel());
		mid.add(new JLabel());
		mid.add(judgerl);
		mid.add(judger);
		mid.add(timel);
		mid.add(time);

		bottom.add(generateButton);
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
							JOptionPane.showMessageDialog(getObject(), "����ɹ�");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(getObject(), "����ʧ��");
							e1.printStackTrace();
						}
					} else {
						JOptionPane
								.showMessageDialog(getObject(), "���Ѿ�������ü�¼��");
					}
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(getObject(), "���ݿ����Ӵ���");
				}

			}
		});
		tendency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// shortJudgeTendency tendency=new
				// shortJudgeTendency(record.getBankname(),String.valueOf(record.getYear()));
				try {

					if (checkImageExist() == false) {
						JOptionPane.showMessageDialog(null, "��������ͼƬ");

					} else {
						showImage = new shortJudgeTendency(
								record.getBankname(), String.valueOf(record
										.getYear()));
						showImage.init();
					}

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		generateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				generateTendencyImage();

				JOptionPane.showMessageDialog(null, "�ɹ�����鿴����ͼ");
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

		return tmpDao.checkExist(record.getBankname(), record.getYear(),
				"short");
	}

	public void generateTendencyImage() {
		ImageEntry result = new ImageEntry();
		ImageEntry range = new ImageEntry();
		ImageEntry grade = new ImageEntry();

		String banknameString = this.record.getBankname();
		String yearString = String.valueOf(this.record.getYear());

		// System.out.println("bankname before"+banknameString);
		result.setBankName(banknameString);
		result.setYear(yearString);
		result.setImageName(banknameString + "����" + "�ȶ���");
		try {
			result.setAbscissa(getYearArray());
			result.setOrdinate(getOrdinateResult());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		range.setBankName(banknameString);
		range.setYear(yearString);
		range.setImageName(banknameString + "����" + "�ȶ�������");
		try {
			// range.setOrdinate("");//������
			range.setAbscissa(getYearArray());
			range.setOrdinate(getOrdinateRange());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		grade.setBankName(banknameString);
		grade.setYear(yearString);
		grade.setImageName(banknameString + "������ȱ���ʵȼ�");

		try {
			grade.setAbscissa(getYearArray());
			grade.setOrdinate(getOrdinateGrade());
		} catch (Exception e) {
			// TODO: handle exception
		}

		ImageHandle handle = new ImageHandle();
		try {

			handle.saveImage(range);
			handle.saveImage(result);
			handle.saveImage(grade);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// handle.saveImage(range);
		// show image

		/*
		 * shortJudgeTendency tendency=new
		 * shortJudgeTendency(banknameString,yearString); try { tendency.init();
		 * } catch (Exception e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 */

	}

	public int[] getYearArray() throws SQLException {
		YearDao yearDao = new YearDaoImpl();
		int[] year = new int[yearDao.getAllYear().size()];

		// System.out.println("result"+yearDao.getAllYear().size());
		for (int i = 0; i < yearDao.getAllYear().size(); i++) {
			year[i] = yearDao.getAllYear().get(i).getYear();
			// System.out.println("year["+i+"]"+year[i]);
		}

		return year;
	}

	// ĳ����, �����ȱ����,
	public double[] getOrdinateResult() throws Exception {

		YearDao yearDao = new YearDaoImpl();

		List<Year> years = yearDao.getAllYear();
		int minYear = years.get(0).getYear();
		for (Year year : years) {
			if (year.getYear() < minYear)
				minYear = year.getYear();
		}

		BankShortDataDao bankDao = new BankShortDataDaoImpl();

		double[] o = new double[years.size()];

		for (int i = 0; i < years.size(); i++) {

			int currentYear = minYear + i;
			// System.out.println(" second compute");
			BankShortData bankShortData = bankDao.getShortBankDataByBY(
					this.record.getBankname(), currentYear);

			shortJudgeEntity entity = getResultRangeEntity(
					bankShortData.getBankname(), currentYear);

			o[i] = Judge.gapShort(bankShortData, entity);
		}
		return o;

	}

	public double[] getOrdinateRange() throws Exception {

		shortJudgeEntity entity = this.getUserInput();
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = entity.getRange()[i];
			// System.out.println(tmp[i]);
		}
		return tmp;

	}

	public double[] getOrdinateGrade() throws Exception {

		shortJudgeEntity entity = this.getUserInput();
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];

		for (int i = 0; i < tmp.length; i++) {
			//
			// ��У��������ţ���
			// System.out.println(entity.toShortJudgeRecord().getShortrate());
			switch (entity.getRangeGrade()[i]) {
			case "��":
				tmp[i] = 0;
				break;
			case "��":
				tmp[i] = 1;
				break;
			case "��":
				tmp[i] = 2;
				break;
			case "����":
				tmp[i] = 3;
				break;
			case "��":
				tmp[i] = 4;
				break;

			default:
				tmp[i] = -1;
				break;
			}
		}

		return tmp;
	}

	public shortJudgeEntity getResultRangeEntity(String bankName, int year)
			throws Exception {

		shortJudgeEntity entity = new shortJudgeEntity();

		entity.setBankName(bankName);
		entity.setYear(year);

		entity.setBankDegree(userInputData.bankDegreeString);

		entity.setMoreThanOneWithdrowRate(userInputData.moreThanOneWithdrowRate);
		entity.setRecycleRate(userInputData.recycleRate);
		entity.setPaymentRate(userInputData.paymentRate);

		entity.setShortJudgeResult();

		return entity;
	}

	public shortJudgeEntity getUserInput() throws Exception {

		shortJudgeEntity entity = new shortJudgeEntity();

		entity.setBankName(record.getBankname());
		entity.setYear(record.getYear());

		entity.setBankDegree(userInputData.bankDegreeString);

		entity.setMoreThanOneWithdrowRate(userInputData.moreThanOneWithdrowRate);
		entity.setRecycleRate(userInputData.recycleRate);
		entity.setPaymentRate(userInputData.paymentRate);

		entity.setShortJudgeResult();

		return entity;
	}

	public boolean checkImageExist() {
		// check whether the image has existed.

		File file = new File("./test");
		String[] fileList = file.list();
		int total = 0;
		for (int i = 0; i < fileList.length; i++) {
			if (fileList[i]
					.equals(record.getBankname() + "������ȱ���ʵȼ�" + ".jpg")
					|| fileList[i].equals(record.getBankname() + "����" + "�ȶ�������"
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "����" + "�ȶ���"
							+ ".jpg")) {

				total++;
			}
		}
		if (total == 3) {
			return true;
		} else {
			return false;
		}

	}

	public void testGap() throws Exception {
		shortJudgeEntity entity = new shortJudgeEntity();
		entity.setBankName(record.getBankname());
		entity.setYear(record.getYear());

		entity.setBankDegree(userInputData.bankDegreeString);

		entity.setMoreThanOneWithdrowRate(userInputData.moreThanOneWithdrowRate);
		entity.setRecycleRate(userInputData.recycleRate);
		entity.setPaymentRate(userInputData.paymentRate);
		entity.setShortJudgeResult();

		BankShortDataDao b = new BankShortDataDaoImpl();

		BankShortData bankShortData = b.getShortBankDataByBY("��������", 2006);

		System.out.println("GAP" + Judge.gapShort(bankShortData, entity));

	}

}
