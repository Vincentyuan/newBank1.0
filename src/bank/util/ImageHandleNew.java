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
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
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

public class ImageHandleNew {

	// 图片名字要传入，所需要的横纵值需要传入。标题。
	public ImageHandleNew() {
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

		if (nameString.contains("综合")) {
			saveImageMultiJudge(entry);
			// System.out.println("计算综合");
		} else {
			if (nameString.contains("缺口率")) {
				// 流动性缺口率 短期 的图片
				saveImageResultAxis(entry, "流动性缺口率");

				// System.out.println("计算等级");
			} else {
				if (nameString.contains("排名")) {
					// 每年的排名。 长期、短期都有
					saveImageRangeAxis(entry);
					// System.out.println("计算排名");
				} else {
					if (nameString.contains("安全边际")) {
						// 长期
						saveImageResultAxis(entry, "安全边际率（%）");

					} else {
						// 优良差等，等级，长期、短期均有
						saveImageStringAxis(entry);
					}

					// System.out.println("计算结果"+entry.getImageName());
				}
			}
		}

	}

	public void saveImageRangeAxis(ImageEntry entry) throws IOException {

		String nameString = entry.getImageName();
		// JOptionPane.showMessageDialog(null,
		// "start to generate image for "+nameString);

		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		// JOptionPane.showMessageDialog(null,
		// "the theme inintialed for "+nameString);
		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"年份", "排名", xySeriesCollection, PlotOrientation.VERTICAL, true,
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

		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
				400);

	}

	public void saveImageResultAxis(ImageEntry entry, String colName)
			throws IOException {

		String nameString = entry.getImageName();

		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		XYSeries series = new XYSeries("");

		for (int i = 0; i < entry.getOrdinate().length; i++) {
			series.add(entry.getAbscissa()[i], entry.getOrdinate()[i]);
		}

		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(series);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString,
				"年份", colName, xySeriesCollection, PlotOrientation.VERTICAL,
				true, true, false);

		XYPlot plot = jfreechart.getXYPlot();
		// jfreechart.setBackgroundPaint(ChartColor.WHITE);

		plot.setOutlineVisible(false);
		// 设置上下的边界区域
		plot.getRangeAxis().setUpperMargin(0.1);
		plot.getRangeAxis().setUpperBound(1);
		plot.getRangeAxis().setLowerMargin(0.1);
		plot.getRangeAxis().setLowerBound(1);
		// 设置图的背景颜色
		plot.setBackgroundPaint(ChartColor.WHITE);

		plot.setDomainGridlinesVisible(false);
		plot.setRangeGridlinesVisible(false);

		// second axis
		ValueAxis yAxis = new SymbolAxis("", new String[] { "差", "", "", "",
				"", "", "", "优" });
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

		File fos_jpg = new File("test/" + nameString + ".jpg");

		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
				400);
	}

	public void saveImageStringAxis(ImageEntry entry) throws IOException {
		String nameString = entry.getImageName();

		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
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

		// ValueAxis xAxis = new NumberAxis("年份");

		ValueAxis xAxis = new SymbolAxis("年份", xStrings);
		ValueAxis yAxis = new SymbolAxis("等级", new String[] { "差", "中", "良",
				"较优", "优" });

		// JFreeChart jfreechart = new JFreeChart(nameString, new
		// Font("Tahoma",0, 18), plot, true);

		JFreeChart jfreechart = ChartFactory.createXYLineChart(nameString, "",
				"", dataset);

		// jfreechart.getXYPlot().getRangeAxis().setInverted(true);

		XYPlot plot = jfreechart.getXYPlot();
		plot.setDomainAxis(xAxis);
		plot.setRangeAxis(yAxis);

		// 创建文件输出流

		File fos_jpg = new File("test/" + nameString + ".jpg");

		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				710, // 宽
				400);

	}

	public void saveImageMultiJudge(ImageEntry entity) throws IOException {

		String nameString = entity.getImageName();

		// String[] axisStrings = { "差", "中", "良", "较优", "优" };

		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		String[] xStrings = { "差", "中", "良", "较优", "优" };
		double[] xInt = new double[1];

		String[] yStrings = { "差", "中", "良", "较优", "优" };
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

		ValueAxis xAxis = new SymbolAxis("短期评级", xStrings);

		ValueAxis yAxis = new SymbolAxis("长期评级", yStrings);

		JFreeChart jfreechart = ChartFactory.createBubbleChart(nameString, "",
				"", dataset, PlotOrientation.HORIZONTAL, true, true, false);

		XYItemRenderer renderer = new XYLineAndShapeRenderer();
		// XYBubbleRenderer renderer2
		// XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		// second line in bubble chart

		XYSeries dataStrings = new XYSeries("");
		dataStrings.add(0, 4); // {{"差","优"},{"优","差"}}
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

		int width = 460; /* Width of the image */
		int height = 460; /* Height of the image */
		File bubbleChart = new File("test/" + entity.getImageName() + ".jpg");

		ChartUtilities.saveChartAsJPEG(bubbleChart, jfreechart, width, height);
	}

	public void saveImageMultiJudge3(ImageEntry entity) throws IOException {

		String nameString = entity.getImageName();

		// String[] axisStrings = { "差", "中", "良", "较优", "优" };

		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		String[] xStrings = { "差", "中", "良", "较优", "优" };
		double[] xInt = new double[1];

		String[] yStrings = { "差", "中", "良", "较优", "优" };
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

		ValueAxis xAxis = new SymbolAxis("短期评级", xStrings);

		ValueAxis yAxis = new SymbolAxis("长期评级", yStrings);

		JFreeChart jfreechart = ChartFactory.createBubbleChart(nameString, "",
				"", dataset, PlotOrientation.HORIZONTAL, true, true, false);

		XYItemRenderer renderer = new XYLineAndShapeRenderer();
		// XYBubbleRenderer renderer2
		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

		// Jfreechart chart=ChartFactory.createBubbleChart(title, xAxisLabel,
		// yAxisLabel, dataset, orientation, legend, tooltips, urls)

		// JFreeChart jfreechart = new JFreeChart(nameString, new
		// Font("Tahoma",0, 18), plot, true);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		// second line in bubble chart

		XYSeries dataStrings = new XYSeries("");
		dataStrings.add(1, 1);
		dataStrings.add(0, 0);
		// {{"差","优"},{"优","差"}}
		IntervalXYDataset dataSet2 = new XYSeriesCollection(dataStrings);
		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(true,
				true);
		// arguments of new XYLineAndShapeRenderer are to activate or deactivate
		// the display of points or line. Set first argument to true if you want
		// to draw lines between the points for e.g.
		plot.setDataset(1, dataSet2);
		plot.setRenderer(1, renderer1);

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

		int width = 460; /* Width of the image */
		int height = 460; /* Height of the image */
		File bubbleChart = new File("test/" + entity.getImageName() + ".jpg");

		ChartUtilities.saveChartAsJPEG(bubbleChart, jfreechart, width, height);
	}

	// abandon backup
	public void saveImageMultiJudge2(ImageEntry entity) throws IOException {

		String nameString = entity.getImageName();

		// String[] axisStrings = { "差", "中", "良", "较优", "优" };

		// 创建主题样式
		StandardChartTheme mChartTheme = new StandardChartTheme("CN");
		// 设置标题字体
		mChartTheme.setExtraLargeFont(new Font("黑体", Font.BOLD, 20));
		// 设置轴向字体
		mChartTheme.setLargeFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 设置图例字体
		mChartTheme.setRegularFont(new Font("宋体", Font.CENTER_BASELINE, 15));
		// 应用主题样式
		ChartFactory.setChartTheme(mChartTheme);

		String[] xStrings = { "差", "中", "良", "较优", "优" };
		double[] xInt = new double[1];

		String[] yStrings = { "差", "中", "良", "较优", "优" };
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

		ValueAxis xAxis = new SymbolAxis("短期评级", xStrings);

		ValueAxis yAxis = new SymbolAxis("长期评级", yStrings);

		JFreeChart jfreechart = ChartFactory.createBubbleChart(nameString, "",
				"", dataset, PlotOrientation.HORIZONTAL, true, true, false);

		XYItemRenderer renderer = new XYLineAndShapeRenderer();

		XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);

		// Jfreechart chart=ChartFactory.createBubbleChart(title, xAxisLabel,
		// yAxisLabel, dataset, orientation, legend, tooltips, urls)

		// JFreeChart jfreechart = new JFreeChart(nameString, new
		// Font("Tahoma",0, 18), plot, true);

		XYPlot xyplot = (XYPlot) jfreechart.getPlot();

		// second line in bubble chart

		XYSeries dataStrings = new XYSeries("");
		dataStrings.add(0, 5);
		dataStrings.add(5, 0);
		// {{"差","优"},{"优","差"}}
		IntervalXYDataset dataSet2 = new XYSeriesCollection(dataStrings);
		XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer(false,
				true);
		// arguments of new XYLineAndShapeRenderer are to activate or deactivate
		// the display of points or line. Set first argument to true if you want
		// to draw lines between the points for e.g.
		plot.setDataset(1, dataSet2);
		plot.setRenderer(1, renderer1);

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

		int width = 460; /* Width of the image */
		int height = 460; /* Height of the image */
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
		JFreeChart jfreechart = ChartFactory.createLineChart(nameString, "年份",
				"稳定性", defaultcategorydataset, PlotOrientation.VERTICAL, true,
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
		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, jfreechart, // 统计图表对象
				700, // 宽
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
		new ImageHandleNew().saveImageMultiJudge(entry);

		// System.out.println("generate finished");

	}

}
