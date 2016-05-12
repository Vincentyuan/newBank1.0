package bank.util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import jxl.format.Orientation;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class ImageHandle {

	// ͼƬ����Ҫ���룬����Ҫ�ĺ���ֵ��Ҫ���롣���⡣
	public ImageHandle() {
		// TODO Auto-generated constructor stub
	}

	public Dataset getDataSet() {

		return null;
	}

	public void saveImage(ImageEntry entry) throws IOException {
		
		//test whether there exist one folder called test.
		File file =new File("./test");
		
		if (file.exists()) {
			//System.out.println("exist");
		}else {
			file.mkdir();
			System.out.println("created");
		}
				
				
		String nameString = entry.getImageName();

		if (nameString.contains("�ۺ�")) {
			saveImageMultiJudge(entry);
		//	System.out.println("�����ۺ�");
		} else {
			if (nameString.contains("�ȼ�")) {
				//�������
				saveImageStringAxis(entry);
				
			//	System.out.println("����ȼ�");
			} else {
				if (nameString.contains("����")) {
					//ÿ���������
					saveImageRangeAxis(entry);
			//		System.out.println("��������");
				} else {
					//������ȱ����
					saveImageResultAxis(entry);
				//	System.out.println("������"+entry.getImageName());
				}
			}
		}

	}

	public void saveImageRangeAxis(ImageEntry entry) throws IOException {

		String nameString = entry.getImageName();
		// JOptionPane.showMessageDialog(null,
		// "start to generate image for "+nameString);
		// ����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ������������
		mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));
		// ����ͼ������
		mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);

		// JOptionPane.showMessageDialog(null,
		// "the theme inintialed for "+nameString);
		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"���", "����", xySeriesCollection, PlotOrientation.VERTICAL, true,
				true, false);
		jfreechart.getXYPlot().getRangeAxis().setInverted(true);
		
		XYPlot plot = (XYPlot) jfreechart.getPlot();

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormat year = NumberFormat.getNumberInstance();
		year.setMaximumFractionDigits(0);
		format.setMaximumFractionDigits(2); // etc.
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				"{0} {2}", year, format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);
		
		
		// renderer.setSeriesItemLabelsVisible(true);
		/*
		 * NumberAxis numberAxis=(NumberAxis) plot.getRangeAxis();
		 * numberAxis.setTickLabelFont(new Font("sans-serif",Font.PLAIN,11));
		 * ValueAxis categoryAxis=plot.getDomainAxis();
		 * categoryAxis.setTickLabelFont(new Font("sans-serif", Font.PLAIN,
		 * 11));
		 * 
		 * 
		 * jfreechart.getXYPlot().getRangeAxis().setInverted(true);
		 */
		File fos_jpg = new File("test/" + nameString + ".jpg");

		// ������ĸ������
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // ͳ��ͼ������
				700, // ��
				400);

	}

	public void saveImageResultAxis(ImageEntry entry) throws IOException {

		String nameString = entry.getImageName();
		

		// ����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ������������
		mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));
		// ����ͼ������
		mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);

		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"���", "��ȫ�߼��ʣ�%��", xySeriesCollection, PlotOrientation.VERTICAL, true,
				true, false);

		

		XYPlot plot = jfreechart.getXYPlot();

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);
		
		ValueAxis vAxis= plot.getRangeAxis();
		vAxis.setAutoTickUnitSelection(true);
		//vAxis.setLabelAngle(180);
	//	vAxis.setInverted(true);
		vAxis.setAutoRange(true);
		vAxis.setVisible(true);
		
		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormat year = NumberFormat.getNumberInstance();
		year.setMaximumFractionDigits(0);
		format.setMaximumFractionDigits(2); // etc.
		
		XYItemLabelGenerator g=new StandardXYItemLabelGenerator();
		
		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				"{0}{2}", year, format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);

		
		
		File fos_jpg = new File("test/" + nameString + ".jpg");

		
	
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // ͳ��ͼ������
				700, // ��
				400);
	}

	public void saveImageStringAxis(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();
	

		// ����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ������������
		mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));
		// ����ͼ������
		mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);

		String[] xStrings = new String[entry.getAbscissa().length];
		double[] xYear = new double[entry.getAbscissa().length];

		double[] y = new double[entry.getOrdinate().length];
		for (int i = 0; i < entry.getAbscissa().length; i++) {
			y[i] = entry.getOrdinate()[i];
			xStrings[i] = String.valueOf(entry.getAbscissa()[i]);
			xYear[i] = i;
		}
		DefaultXYDataset dataset = new DefaultXYDataset();
		// System.out.println("length xyear "+xYear.length +" y "+y.length);
		double[][] stringAxisData = { xYear, y };

		dataset.addSeries("", stringAxisData);

		// ValueAxis xAxis = new NumberAxis("���");

		ValueAxis xAxis = new SymbolAxis("���", xStrings);
		ValueAxis yAxis = new SymbolAxis("�ȼ�", new String[] { "��", "��", "��",
				"����", "��" });

		
	//	JFreeChart jfreechart = new JFreeChart(nameString, new Font("Tahoma",0, 18), plot, true);
		
		JFreeChart jfreechart =ChartFactory.createXYLineChart(nameString,
				"", "", dataset);
		

		//jfreechart.getXYPlot().getRangeAxis().setInverted(true);
		
		XYPlot plot=jfreechart.getXYPlot();
		plot.setDomainAxis(xAxis);
		plot.setRangeAxis(yAxis);
		
		

		// �����ļ������

		File fos_jpg = new File("test/" + nameString + ".jpg");
		
		// ������ĸ������
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // ͳ��ͼ������
				710, // ��
				400);

	}

	
	public void saveImageMultiJudge(ImageEntry entity) throws IOException {

		String nameString = entity.getImageName();

		// String[] axisStrings = { "��", "��", "��", "����", "��" };

		// ����������ʽ
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// ���ñ�������
		mChartTheme.setExtraLargeFont(new Font("����", Font.BOLD, 20));
		// ������������
		mChartTheme.setLargeFont(new Font("����", Font.CENTER_BASELINE, 15));
		// ����ͼ������
		mChartTheme.setRegularFont(new Font("����", Font.CENTER_BASELINE, 15));
		// Ӧ��������ʽ
		ChartFactory.setChartTheme(mChartTheme);

		String[] xStrings = { "��", "��", "��", "����", "��" };
		double[] xInt = new double[1];

		String[] yStrings = { "��", "��", "��", "����", "��" };
		double[] yInt = new double[1];
		double[] size = new double[1];
		size[0] = 0.5;
		for (int i = 0; i < entity.getAbscissa().length; i++) {
			// initial xint and yint and size

			if (entity.getAbscissa()[i] != 0) {
				xInt[0] = Double.valueOf(i);
				//System.out.println(nameString+" short "+i);
			}
			if (entity.getOrdinate()[i] != 0) {
				yInt[0] = i;
				//System.out.println(nameString+" long "+i);
			}

		}

		// system.out.result

		double[][] dataToImage = { xInt, yInt, size };

		DefaultXYZDataset dataset = new DefaultXYZDataset();

		dataset.addSeries("", dataToImage);

		ValueAxis xAxis = new SymbolAxis("��������", xStrings);
		ValueAxis yAxis = new SymbolAxis("��������", yStrings);

		JFreeChart jfreechart = ChartFactory.createBubbleChart(nameString, "",
				"", dataset, PlotOrientation.HORIZONTAL, true, true, false);

		XYItemRenderer renderer = new XYLineAndShapeRenderer();
		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

		// Jfreechart chart=ChartFactory.createBubbleChart(title, xAxisLabel,
		// yAxisLabel, dataset, orientation, legend, tooltips, urls)

		// JFreeChart jfreechart = new JFreeChart(nameString, new
		// Font("Tahoma",0, 18), plot, true);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();
		xyplot.setDomainAxis(xAxis);
		xyplot.setRangeAxis(yAxis);
		xyplot.setForegroundAlpha(0.65F);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.blue);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setLowerMargin(0.2);
		numberaxis.setUpperMargin(0.5);
		NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
		numberaxis1.setLowerMargin(0.8);
		numberaxis1.setUpperMargin(0.9);

		int width = 560; /* Width of the image */
		int height = 370; /* Height of the image */

		File bubbleChart = new File("test/" + entity.getImageName() + ".jpg");

		ChartUtilities.saveChartAsJPEG(bubbleChart, jfreechart, width, height);
	}

	// abandon
	public void saveImageNumberAxis1(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			defaultcategorydataset.addValue(entry.getOrdinate()[i], " ",
					String.valueOf(entry.getAbscissa()[i]));
		}
		JFreeChart jfreechart = ChartFactory.createLineChart(nameString, "���",
				"�ȶ���", defaultcategorydataset, PlotOrientation.VERTICAL, true,
				true, false);

		CategoryPlot categoryplot = (CategoryPlot) jfreechart.getPlot();
		categoryplot.setBackgroundPaint(null);
		categoryplot.setRangeGridlinePaint(Color.white);

		LineAndShapeRenderer renderer = (LineAndShapeRenderer) categoryplot
				.getRenderer();

		jfreechart.getXYPlot().getRangeAxis().setInverted(true);

		renderer.setShapesVisible(true);
		DecimalFormat decimalformat1 = new DecimalFormat("######");
		renderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(
				"{2}", decimalformat1));
		renderer.setItemLabelsVisible(true);
		renderer.setSeriesVisible(true);

		File fos_jpg = new File("test/" + nameString + ".jpg");
		// ������ĸ������
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // ͳ��ͼ������
				700, // ��
				400);
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		ImageEntry entry = new ImageEntry();
		entry.setImageName("sdfasdaf");

		int[] x = { 0, 1, 0, 0, 0 };
		double[] y = { 0, 1, 0, 0, 0 };
		entry.setAbscissa(x);
		entry.setOrdinate(y);
		new ImageHandle().saveImageMultiJudge(entry);

	//	System.out.println("generate finished");

	}

}