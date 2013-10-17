/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itats.kuliah.rpl.view;

/**
 *
 * @author ziez
 */
import java.awt.image.BufferedImage;
import java.text.NumberFormat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;

public class PieChart extends JPanel {

     private static final long serialVersionUID = 1L;

     public PieChart(){}
     public PieChart(int global, int local, int param) {

          // This will create the dataset 
          PieDataset dataset = createDataset(global, local, param);
          // based on the dataset we create the chart
          JFreeChart chart = createChart(dataset, "Variabel Analyzer");
          // we put the chart into a panel
          ChartPanel chartPanel = new ChartPanel(chart);
          // default size
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
          // add it to our application
          add(chartPanel);

     }

     /**
      * Creates a sample dataset
      */
     private PieDataset createDataset(int global, int local, int param) {
          DefaultPieDataset result = new DefaultPieDataset();
          result.setValue("Global", global);
          result.setValue("Local", local);
          result.setValue("Parameter", param);
          return result;

     }

     /**
      * Creates a chart
      */
     private JFreeChart createChart(PieDataset dataset, String title) {

          JFreeChart chart = ChartFactory.createPieChart3D(title, // chart title
                  dataset, // data
                  true, // include legend
                  true,
                  false);

          PiePlot3D plot = (PiePlot3D) chart.getPlot();
          plot.setStartAngle(145);
          plot.setDirection(Rotation.CLOCKWISE);
          plot.setForegroundAlpha(0.5f);
          plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{2} {0}={1}"));
          return chart;

     }
     
     public BufferedImage getChartImage(int global, int local, int param){
          PieDataset dataset = createDataset(global, local, param);
          JFreeChart chart = createChart(dataset, "Variabel Analyzer");
          return chart.createBufferedImage(350, 280);
     }
}
