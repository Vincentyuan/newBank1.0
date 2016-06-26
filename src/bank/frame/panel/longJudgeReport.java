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
import bank.util.ImageName;

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
		header = new JLabel("���г����ȶ��Ա���");
		bankl = new JLabel("    ����:");
		bank = new JLabel(record.getBankname());
		yearl = new JLabel("    ���:");
		year = new JLabel(String.valueOf(record.getYear()));
		depositl = new JLabel("    �����ȶ��ԣ����ά�ȣ�:");
		deposit = new JLabel(record.getDeposit());
		loanl = new JLabel("    �����ȶ��ԣ�����ά�ȣ�:");
		loan = new JLabel(record.getLoan());
		judgerl = new JLabel("    ������:");
		judger = new JLabel(ClientContext.name);
		timel = new JLabel("    ʱ��:");
		time = new JLabel(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		generateImage = new JButton("����ͼƬ");
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
					JOptionPane.showMessageDialog(getObject(), "���ݿ��ȡ������");
				}
			}
		});
		tendency.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				try {

					if (checkImageExist() == false) {
						JOptionPane.showMessageDialog(null, "��������ͼƬ");

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
					JOptionPane.showMessageDialog(null, "ͼƬ���ɳɹ�");

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

		// ����ͼƬ��һ��6����result range grade.
		
		ImageEntry resultDeposit = new ImageEntry();
		ImageEntry resultLoan = new ImageEntry();
		ImageEntry rangeDeposit = new ImageEntry();
		ImageEntry rangeLoan = new ImageEntry();
		ImageEntry gradeDeposit = new ImageEntry();
		ImageEntry gradeLoan = new ImageEntry();

		String banknameString = record.getBankname();
		String yearString = String.valueOf(record.getYear());
		String [] imageName = ImageName.getLongNameArray(banknameString);




		rangeDeposit.setBankName(banknameString);
		rangeDeposit.setYear(yearString);
//		rangeDeposit.setImageName(banknameString + "���ڴ��" + "�ȶ�������(���ά��)");
		rangeDeposit.setImageName(imageName[0]);
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

		gradeDeposit.setBankName(banknameString);
		gradeDeposit.setYear(yearString);
//		gradeDeposit.setImageName(banknameString + "���ڴ��" + "�ȶ��Եȼ�(���ά��)");
		gradeDeposit.setImageName(imageName[1]);
		try {
			gradeDeposit.setAbscissa(getYearArray());
			gradeDeposit.setOrdinate(getOrdinateGradeDeposit());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		resultDeposit.setBankName(banknameString);
		resultDeposit.setYear(yearString);
		//resultDeposit.setImageName(banknameString + "��ȫ�߼���");
		resultDeposit.setImageName(imageName[2]);
		try {
			resultDeposit.setAbscissa(getYearArray());
			resultDeposit.setOrdinate(getOrdinateResultDeposit());
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		rangeLoan.setBankName(banknameString);
		rangeLoan.setYear(yearString);
//		rangeLoan.setImageName(banknameString + "���ڴ���" + "�ȶ�������(����ά��)");
		rangeLoan.setImageName(imageName[3]);
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

		gradeLoan.setBankName(banknameString);
		gradeLoan.setYear(yearString);
//		gradeLoan.setImageName(banknameString + "���ڴ���" + "�ȶ��Եȼ�(����ά��)");
		gradeLoan.setImageName(imageName[4]);
		try {
			gradeLoan.setAbscissa(getYearArray());
			gradeLoan.setOrdinate(getOrdinateGradeLoan());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		resultLoan.setBankName(banknameString);
		resultLoan.setYear(yearString);
//		resultLoan.setImageName(banknameString + "���ȫ�߼���");
		resultLoan.setImageName(imageName[5]);
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
			// ��У��������ţ���
			// System.out.println(entity.toShortJudgeRecord().getShortrate());

			switch (entity.getRangeGradeDeposit()[i]) {
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

	public double[] getOrdinateGradeLoan() throws Exception {

		longJudgeEntity entity = new longJudgeEntity();
		entity.setBankname(record.getBankname());
		entity.setYear(Integer.valueOf(String.valueOf(record.getYear())));
		YearDao yearDao = new YearDaoImpl();
		double[] tmp = new double[yearDao.getAllYear().size()];

		for (int i = 0; i < tmp.length; i++) {
			//
			// ��У��������ţ���
			// System.out.println(entity.toShortJudgeRecord().getShortrate());
			switch (entity.getRangeGradeLoan()[i]) {
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

			/*if (fileList[i].equals(record.getBankname() + "���ȫ�߼���" + ".jpg")
					|| fileList[i].equals(record.getBankname() + "��ȫ�߼���"
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "���ڴ���ȶ�������(���ά��)" //ok
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "���ڴ����ȶ�������(����ά��)"//ok
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "���ڴ���ȶ��Եȼ�(���ά��)"//ok
							+ ".jpg")
					|| fileList[i].equals(record.getBankname() + "���ڴ����ȶ��Եȼ�(����ά��)"//ok
							+ ".jpg")) {

				total++;
			}*/
			
			if(ImageExist(fileList[i])){
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
	public boolean ImageExist(String targetName){
		String [] namelist = ImageName.getLongNameArray(record.getBankname());
		
		boolean exist = false;
		for (int i = 0; i < namelist.length; i++) {
			if ((namelist[i]+".jpg").equals(targetName)) {
				return true;
			}
			
		}
		return exist;
	}

}
