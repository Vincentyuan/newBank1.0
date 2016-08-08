package bank.util;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import jxl.format.Orientation;

import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.labels.XYItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYBubbleRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.DefaultXYZDataset;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.TextAnchor;

import bank.frame.panel.indexPanel;

public class ImageHandle {

	// ͼƬ����Ҫ���룬����Ҫ�ĺ���ֵ��Ҫ���롣���⡣
	public ImageHandle() {
		// TODO Auto-generated constructor stub
	}

	public Dataset getDataSet() {

		return null;
	}

	public void saveImage(ImageEntry entry) throws IOException {

		// test whether there exist one folder called test.
		File file = new File("./test");

		if (file.exists()) {
			// System.out.println("exist");
		} else {
			file.mkdir();
			System.out.println("created");
		}

		String nameString = entry.getImageName();

		if (nameString.contains("�ۺ�")) {
			saveImageMultiJudge(entry);
			// System.out.println("�����ۺ�");
		} else {
			if (nameString.contains("ȱ����")) {
				// ������ȱ���� ���� ��ͼƬ
				saveImageResultAxis(entry, "������ȱ����");

				// System.out.println("����ȼ�");
			} else {
				if (nameString.contains("����")) {
					// ÿ��������� ���ڡ����ڶ���
					saveImageRangeAxis(entry, "����");
					// System.out.println("��������");
				} else {
					if (nameString.contains("��ȫ�߼�")) {
						// ����
						saveImageResultAxis(entry, "��ȫ�߼��ʣ�%��");

					} else {
						// ������ȣ��ȼ������ڡ����ھ���
						saveImageStringAxis(entry);
					}

					// System.out.println("������"+entry.getImageName());
				}
			}
		}

	}

	public void saveImageRangeAxis(ImageEntry entry, String colName)
			throws IOException {

		String nameString = entry.getImageName();

//		setCharset();
		int[] xAxisDataInt = entry.getAbscissa();
		String[] yearStrings = new String[xAxisDataInt.length];
		double[] yearAxisDouble = new double[xAxisDataInt.length];

		for (int i = 0; i < xAxisDataInt.length; i++) {
			yearStrings[i] = String.valueOf(xAxisDataInt[i]);
			// yearAxisDouble[i] = Double.valueOf(xAxisDataInt[i]);
			yearAxisDouble[i] = i;
		}

		DefaultXYDataset dataset = new DefaultXYDataset();
		dataset.addSeries("",
				new double[][] { yearAxisDouble, entry.getOrdinate() });
		ValueAxis xValueAxis = new SymbolAxis("���", yearStrings);
		ValueAxis yValueAxis = new NumberAxis(colName);

		XYItemRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseItemLabelsVisible(true);
		XYPlot plot = new XYPlot(dataset, xValueAxis, yValueAxis, renderer);
		plot.getRangeAxis().setInverted(true);

		setNodeDataVisible(renderer, plot);

		JFreeChart jfreechart = new JFreeChart(nameString, new Font("Tahoma",
				0, 18), plot, true);
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD,12));

		outputImage(jfreechart, nameString, 700, 400);

	}

	public void saveImageResultAxis(ImageEntry entry, String colName)
			throws IOException {

		String nameString = entry.getImageName();
// used for the old version
//		setCharset(); 

		int[] xAxisDataInt = entry.getAbscissa();
		String[] yearStrings = new String[xAxisDataInt.length];
		double[] yearAxisDouble = new double[xAxisDataInt.length];


		for (int i = 0; i < xAxisDataInt.length; i++) {
			yearStrings[i] = String.valueOf(xAxisDataInt[i]);
			// yearAxisDouble[i] = Double.valueOf(xAxisDataInt[i]);
			yearAxisDouble[i] = i;
			
		}

		DefaultXYDataset dataset = new DefaultXYDataset();
		dataset.addSeries("",
				new double[][] { yearAxisDouble, entry.getOrdinate() });
		ValueAxis xValueAxis = new SymbolAxis("���", yearStrings);
		ValueAxis yValueAxis = new NumberAxis(colName);
		
		//yValueAxis.setAutoRangeIncludesZero(false);
		yValueAxis.setAutoRange(true);
		yValueAxis.setAutoTickUnitSelection(true);

		XYItemRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setBaseItemLabelsVisible(true);
		XYPlot plot = new XYPlot(dataset, xValueAxis, yValueAxis, renderer);

		
		setYAxisBounder(plot,entry.getOrdinate());


		// second axis
		ValueAxis yAxis = new SymbolAxis("", new String[] { "��", "", "", "",
				"", "", "", "��" });
		plot.setRangeAxis(1, yAxis);
		yAxis.setRange(0, 7);
		setNodeDataVisible(renderer, plot);

		
		JFreeChart jfreechart = new JFreeChart(nameString, new Font("Tahoma",
				0, 18), plot, true);
		jfreechart.getTitle().setFont(new Font("����", Font.BOLD,12));

		outputImage(jfreechart, nameString, 700, 400);
	}

	public void saveImageStringAxis(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();

		setCharset();

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

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString, "",
				"", dataset);

		XYPlot plot = jfreechart.getXYPlot();
		plot.setDomainAxis(xAxis);
		plot.setRangeAxis(yAxis);

		outputImage(jfreechart, nameString, 710, 400);

	}

	public void saveImageMultiJudge(ImageEntry entity) throws IOException {

		String nameString = entity.getImageName();

		// String[] axisStrings = { "��", "��", "��", "����", "��" };

		setCharset();

		String[] xStrings = { "��", "��", "��", "����", "��" };
		double[] xInt = new double[1];

		String[] yStrings = { "��", "��", "��", "����", "��" };
		double[] yInt = new double[1];
		double[] size = new double[1];
		size[0] = 0.2;
		for (int i = 0; i < entity.getAbscissa().length; i++) {
			// initial xint and yint and size

			if (entity.getAbscissa()[i] != 0) {
				xInt[0] = Double.valueOf(i);
				// System.out.println(nameString+" short "+i);
			}
			if (entity.getOrdinate()[i] != 0) {
				yInt[0] = i;
				// System.out.println(nameString+" long "+i);
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
		// XYBubbleRenderer renderer2
		// XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		// second line in bubble chart

		XYSeries dataStrings = new XYSeries("");
		dataStrings.add(0, 4); // {{"��","��"},{"��","��"}}
		dataStrings.add(4, 0);

		IntervalXYDataset dataSet2 = new XYSeriesCollection(dataStrings);
		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(true,
				true);
		// arguments of new XYLineAndShapeRenderer are to activate or deactivate
		// the display of points or line. Set first argument to true if you want
		// to draw lines between the points for e.g.
		xyplot.setDataset(1, dataSet2);
		xyplot.setRenderer(1, renderer1);

		xyplot.setDomainAxis(xAxis);
		xyplot.setRangeAxis(yAxis);
		xyplot.setForegroundAlpha(0.65F);
		XYItemRenderer xyitemrenderer = xyplot.getRenderer();
		xyitemrenderer.setSeriesPaint(0, Color.blue);
		NumberAxis numberaxis = (NumberAxis) xyplot.getDomainAxis();
		numberaxis.setLowerMargin(0.2);
		numberaxis.setUpperMargin(0.5);
		NumberAxis numberaxis1 = (NumberAxis) xyplot.getRangeAxis();
		// numberaxis1.setRange(0,9);
		numberaxis1.setLowerMargin(0.8);
		numberaxis1.setUpperMargin(0.9);

		int width = 520; /* Width of the image */
		int height = 460; /* Height of the image */

		outputImage(jfreechart, entity.getImageName(), width, height);
	}
	
	public void setYAxisBounder(XYPlot plot, double [] yAxisData){
		double max = yAxisData[0];
		double min = yAxisData[0];
		for (int i = 0; i < yAxisData.length; i++) {
			if (yAxisData[i]>max) {
				max = yAxisData[i];
			}
			if (yAxisData[i]<min) {
				min = yAxisData[i];
			}
		}
		
		//plot.setOutlineVisible(true);
		// �������µı߽�����
		double bounder = 0;
		double remoteNode = 0;
		if (Math.abs(max)>Math.abs(min)) {
			remoteNode = Math.abs(max);
		}else {
			remoteNode = Math.abs(min);
		}
		if (remoteNode>5) {
			bounder = remoteNode *0.1;
		}else {
			bounder = 0.5;
		}
		plot.getRangeAxis().setUpperMargin(1);
		plot.getRangeAxis().setUpperBound(max+bounder);
		plot.getRangeAxis().setLowerMargin(1);
		plot.getRangeAxis().setLowerBound(min-bounder);
		// ����ͼ�ı�����ɫ
		plot.setBackgroundPaint(ChartColor.WHITE);

//		plot.setDomainGridlinesVisible(false);
//		plot.setRangeGridlinesVisible(false);
	}

	//set the old version create image by chartfactory
	public void setCharset() {

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

	}

	public void setNodeDataVisible(XYItemRenderer renderer, XYPlot plot) {
		// ����renderer ��ʾ���ݡ�
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));

		// ���������Ƕ���������ͼ���ݱ�ʾ�Ĺؼ�����
		renderer.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		renderer.setBaseItemLabelFont(new Font("Dialog", 1, 14));
		plot.setRenderer(renderer);
	}

	public void outputImage(JFreeChart chart, String imageName, int length,
			int high) throws IOException {
		File fos_jpg = new File("test/" + imageName + ".jpg");

		ChartUtilities.saveChartAsJPEG(fos_jpg, chart, // ͳ��ͼ�����
				length, // ��
				high);
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

		// System.out.println("generate finished");

	}

	// abandon
	public void saveImageRangeAxis2(ImageEntry entry) throws IOException {

		String nameString = entry.getImageName();

		setCharset();

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

		outputImage(jfreechart, nameString, 700, 400);

	}

	// abandon
	public void saveImageResultAxis2(ImageEntry entry, String colName)
			throws IOException {

		String nameString = entry.getImageName();

		setCharset();

		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"���", colName, xySeriesCollection, PlotOrientation.VERTICAL,
				true, true, false);

		XYPlot plot = jfreechart.getXYPlot();
		// jfreechart.setBackgroundPaint(ChartColor.WHITE);

		plot.setOutlineVisible(false);
		// �������µı߽�����
		plot.getRangeAxis().setUpperMargin(0.1);
		plot.getRangeAxis().setUpperBound(1);
		plot.getRangeAxis().setLowerMargin(0.1);
		plot.getRangeAxis().setLowerBound(1);
		// ����ͼ�ı�����ɫ
		plot.setBackgroundPaint(ChartColor.WHITE);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);

		// second axis
		ValueAxis yAxis = new SymbolAxis("", new String[] { "��", "", "", "",
				"", "", "", "��" });
		plot.setRangeAxis(1, yAxis);

		yAxis.setRange(0, 7);

		XYLineAndShapeRenderer renderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		renderer.setBaseItemLabelsVisible(true);

		NumberAxis vAxis = (NumberAxis) plot.getRangeAxis();
		vAxis.setAutoRangeIncludesZero(false);
		vAxis.setAutoRange(true);
		vAxis.setAutoTickUnitSelection(true);
		// vAxis.setLabelAngle(180);
		// vAxis.setInverted(true);
		vAxis.setVisible(true);

		NumberFormat format = NumberFormat.getNumberInstance();
		NumberFormat year = NumberFormat.getNumberInstance();
		year.setMaximumFractionDigits(0);
		format.setMaximumFractionDigits(2); // etc.

		XYItemLabelGenerator g = new StandardXYItemLabelGenerator();

		XYItemLabelGenerator generator = new StandardXYItemLabelGenerator(
				"{0}{2}", year, format);
		renderer.setBaseItemLabelGenerator(generator);
		renderer.setBaseItemLabelsVisible(true);

		outputImage(jfreechart, nameString, 700, 400);
	}

}
