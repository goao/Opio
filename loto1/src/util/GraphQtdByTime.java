package util;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.XYLineAndShapeRenderer;
import org.jfree.data.XYDataset;
import org.jfree.data.XYSeries;
import org.jfree.data.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.ui.Spacer;

import softonPack.util.DateHandle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GraphQtdByTime extends ApplicationFrame {

	private Connection conn;

	/**
	 * Creates a new demo.
	 * 
	 * @param title
	 *            the frame title.
	 * @param i 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public GraphQtdByTime(final String title, int i) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		super(title);

		final XYDataset dataset = createDataset(i);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}
	
	public GraphQtdByTime(final String title) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		super(title);

		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);
		chartPanel.setMouseZoomable(true, false);

	}

	/**
	 * Creates a sample dataset.
	 * @param i2 
	 * 
	 * @return a sample dataset.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	private XYDataset createDataset(int i2) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	String userName = "root";
		String password = "lacuna";
		String url = "jdbc:mysql://localhost/loto";

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		conn = (Connection) DriverManager
				.getConnection(url, userName, password);

		System.out.println("Database connection established");

		Statement s = (Statement) conn.createStatement();

//		s.executeQuery("select * from sorteios_grouped_quadrante order by CONCURSO LIMIT 150 ");
		final XYSeriesCollection dataset = new XYSeriesCollection();		
		
		
		
		
		for(int i = ((10*i2) + 1); i <=((10*i2) + 10);i++){
			
			s.executeQuery("select sum(1) qtd, DATE_FORMAT(data , '%m/%d %H') data,numero from random_org_monitor where numero = "+i+" group by 2 order by 2;");
			ResultSet rs = s.getResultSet();
			int count = 0;		
			XYSeries series1 = new XYSeries(""+i);
			int last = 0;
			while (rs.next()) {    	
				int qtd = rs.getInt("qtd");
				String data = rs.getString("data");
				Date date = DateHandle.parseDate(data, "MM/dd HH");
				series1.add(date.getTime(), qtd+last);
//				series1.add(qtd+last,date.getTime());

				last = last + qtd;

			}
			dataset.addSeries(series1);

		}
                
        return dataset;
        
    }
	
	
	private XYDataset createDataset() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
    	String userName = "root";
		String password = "lacuna";
		String url = "jdbc:mysql://localhost/loto";

		Class.forName("com.mysql.jdbc.Driver").newInstance();

		conn = (Connection) DriverManager
				.getConnection(url, userName, password);

		System.out.println("Database connection established");

		Statement s = (Statement) conn.createStatement();

//		s.executeQuery("select * from sorteios_grouped_quadrante order by CONCURSO LIMIT 150 ");
		final XYSeriesCollection dataset = new XYSeriesCollection();		

		for(int i =1; i <=60;i++){
			
			s.executeQuery("select sum(1) qtd, DATE_FORMAT(data , '%m/%d %H') data,numero from random_org_monitor where numero = "+i+" group by 2 order by 2;");
			ResultSet rs = s.getResultSet();
			int count = 0;		
			XYSeries series1 = new XYSeries(""+i);
			int last = 0;
			while (rs.next()) {    	
				int qtd = rs.getInt("qtd");
				String data = rs.getString("data");
				Date date = DateHandle.parseDate(data, "MM/dd HH");
				series1.add(date.getTime(), qtd+last);
//				series1.add(qtd+last,date.getTime());

				last = last + qtd;

			}
			dataset.addSeries(series1);

		}
                
        return dataset;
        
    }

	/**
	 * Creates a chart.
	 * 
	 * @param dataset
	 *            the data for the chart.
	 * 
	 * @return a chart.
	 */
	private JFreeChart createChart(final XYDataset dataset) {

		// create the chart...
		final JFreeChart chart = ChartFactory.createXYLineChart(
				"sorteios Grouped", // chart title
				"X", // x axis label
				"Y", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				true // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		 final StandardLegend legend = (StandardLegend) chart.getLegend();
		 legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		 plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		 final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		 renderer.setSeriesLinesVisible(0, false);
//		 renderer.setSeriesShapesVisible(1, false);
		 plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
//		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//		
//		final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
//		domainAxis.setTickUnit(new NumberTickUnit(1));
//		
////		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//		rangeAxis.setTickUnit(new NumberTickUnit(1));
//		plot.setAxisOffset(new Spacer(arg0, arg1, arg2, arg3, arg4));
		// OPTIONAL CUSTOMISATION COMPLETED.

		return chart;

	}

	// ****************************************************************************
	// * JFREECHART DEVELOPER GUIDE *
	// * The JFreeChart Developer Guide, written by David Gilbert, is available
	// *
	// * to purchase from Object Refinery Limited: *
	// * *
	// * http://www.object-refinery.com/jfreechart/guide.html *
	// * *
	// * Sales are used to provide funding for the JFreeChart project - please *
	// * support us so that we can continue developing free software. *
	// ****************************************************************************

	/**
	 * Starting point for the demonstration application.
	 * 
	 * @param args
	 *            ignored.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static void main(final String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

//		for (int i = 0; i < 6; i++) {
//			GraphQtdByTime demo = new GraphQtdByTime("evolucao by tempo", i);
//			demo.pack();
//			RefineryUtilities.centerFrameOnScreen(demo);
//			demo.setVisible(true);
//			
//		}
		
		GraphQtdByTime demo = new GraphQtdByTime("evolucao by tempo");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);


	}

}
