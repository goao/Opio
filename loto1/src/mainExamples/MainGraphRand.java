package mainExamples;
import java.awt.Color;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.XYLineAndShapeRenderer;
import org.jfree.data.XYDataset;
import org.jfree.data.XYSeries;
import org.jfree.data.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class MainGraphRand extends ApplicationFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 306422452764451919L;
	private Connection conn;

	/**
	 * Creates a new demo.
	 * 
	 * @param title
	 *            the frame title.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public MainGraphRand(final String title) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {

		super(title);

		final XYDataset dataset = createDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		setContentPane(chartPanel);

	}

	/**
	 * Creates a sample dataset.
	 * 
	 * @return a sample dataset.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
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
		
		s.executeQuery("select * from random2_bb where codigo <= 20000 order by codigo, dezena");
		

		ResultSet rs = s.getResultSet();
		int count = 0;
		
		final XYSeries series1 = new XYSeries("First");
		
		while (rs.next()) {    	
			int concurso = rs.getInt("codigo");
			int quadrante = rs.getInt("numero");
//			series1.add(concurso, quadrante);
			series1.add(quadrante, concurso);

		}


   

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);


                
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
				"Random numbers", // chart title
				"X", // x axis label
				"Y", // y axis label
				dataset, // data
				PlotOrientation.VERTICAL, true, // include legend
				true, // tooltips
				false // urls
				);

		// NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
		chart.setBackgroundPaint(Color.white);

		// final StandardLegend legend = (StandardLegend) chart.getLegend();
		// legend.setDisplaySeriesShapes(true);

		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		 final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		 renderer.setSeriesLinesVisible(0, false);
		 renderer.setSeriesShapesVisible(1, false);
		 plot.setRenderer(renderer);

		// change the auto tick unit selection to integer units only...
		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		
		final NumberAxis domainAxis = (NumberAxis) plot.getDomainAxis();
		domainAxis.setTickUnit(new NumberTickUnit(1));
		
//		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		rangeAxis.setTickUnit(new NumberTickUnit(1));
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

		final MainGraphRand demo = new MainGraphRand("Line Chart Demo 6");
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);

	}

}
