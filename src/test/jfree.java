package test;

import java.awt.Font;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.SymbolAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.DefaultXYDataset;

public class jfree {
   public static void main(String[] args) throws IOException {
      DefaultXYDataset dataset = new DefaultXYDataset();
      dataset.addSeries("Values 1",new double[][]{{1, 2, 10}, {2, 4, 1}});      
      dataset.addSeries("Values 2",new double[][]{{4, 5, 6}, {0, 3, 2}});      
      dataset.addSeries("Values 3",new double[][]{{1.5, 3.5, 5.5}, {0.5, 3.5, 2.5}});      
      ValueAxis xAxis = new NumberAxis("x");
      ValueAxis yAxis = new SymbolAxis("Symbol", new String[]{"One","Two","Three","Four","Five"});
      XYItemRenderer renderer = new XYLineAndShapeRenderer();
      renderer.setBaseItemLabelsVisible(true);
      XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
      plot.getRangeAxis().setInverted(true);
      JFreeChart chart = new JFreeChart("Symbol Axis Demo", new Font("Tahoma", 0, 18), plot, true);
      JFrame frame = new JFrame("XY Plot Demo");
      File fos_jpg = new File("test/呵呵.jpg");

		// 输出到哪个输出流
		ChartUtilities.saveChartAsJPEG(fos_jpg, chart, // 统计图表对象
				710, // 宽
				400);
      frame.setContentPane(new ChartPanel(chart));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
      
   }
   
}
