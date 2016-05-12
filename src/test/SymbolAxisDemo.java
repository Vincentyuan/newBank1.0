package test;
import java.awt.Font;

import javax.swing.JFrame;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.*;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.*;
import org.jfree.data.xy.DefaultXYDataset;

public class SymbolAxisDemo {
   public static void main(String[] args) {
      DefaultXYDataset dataset = new DefaultXYDataset();
      
      
   //   double [] x={1991,1992,1993,1994,1995,1996,1997,1998,1999,2000};
      double [] x={1,2,3,4,5,6,7,8,9,10};
      double [] y={1,2,3,4,0,2,4,1,4,1};
     // dataset.addSeries("Values 1",new double[][]{{1, 2, 3}, {2, 4, 1}});      
    //  dataset.addSeries("Values 2",new double[][]{{4, 5, 6}, {0, 3, 2}});      
    //  dataset.addSeries("Values 3",new double[][]{{1.5, 3.5, 5.5}, {0.5, 3.5, 2.5}});      
      dataset.addSeries("value", new double [] []{
    	//	  {1,2,3,4,5,6,7,8,9,10},{1,2,3,4,0,2,4,1,4,1}
    		  x,y
      });
      
    //  ValueAxis xAxis = new NumberAxis("x");
      ValueAxis xAxis=new SymbolAxis("x", new String[]{"1991","1992","1993",
    	  "1994","1995","1996","1997","1998","1999","2000"});
      
      ValueAxis yAxis = new SymbolAxis("Symbol",
    		  new String[]{"One","Two","Three","Four","Five"});
      
      XYItemRenderer renderer = new XYLineAndShapeRenderer();
      
      XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
      
      JFreeChart chart = new JFreeChart("Symbol Axis Demo",
    		  new Font("Tahoma", 0, 18), plot, true);
     
      
      JFrame frame = new JFrame("XY Plot Demo");
      frame.setContentPane(new ChartPanel(chart));
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}