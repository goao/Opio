package util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardLegend;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.XYLineAndShapeRenderer;
import org.jfree.data.XYDataset;
import org.jfree.data.XYSeries;
import org.jfree.data.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import softonPack.util.DateHandle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class GraphQtdByTime extends ApplicationFrame {

	private Connection conn;
	private AbstractAction exportFigure;

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
		
        exportFigure = new AbstractAction("Export figure") {
            public void actionPerformed(ActionEvent arg0) {
                exportFigure(chart);
            }
        };

        this.setJMenuBar(createMenuBar());


	}
	
    private JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.add(exportFigure);

        result.add(fileMenu);

        return result;
    }

	
    private void exportFigure(JFreeChart chart) {
        // TODO: use a dialog to ask for the filename
        File svgFile = new File("example_file.svg");

        // write it to file
        try {
            exportChartAsSVG(chart,
                getContentPane().getBounds(), svgFile);

            // TODO: notify the user the file has been saved (e.g. status bar)
            System.out.println("Figured saved as " + svgFile.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error saving file:\n" + e.getMessage());
        }
    }
    
    
    void exportChartAsSVG(JFreeChart chart, Rectangle bounds, File svgFile) throws IOException {
        // Get a DOMImplementation and create an XML document
        DOMImplementation domImpl =
            GenericDOMImplementation.getDOMImplementation();
        Document document = domImpl.createDocument(null, "svg", null);

        // Create an instance of the SVG Generator
        SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

        // draw the chart in the SVG generator
        chart.draw(svgGenerator, bounds);

        // Write svg file
        OutputStream outputStream = new FileOutputStream(svgFile);
        Writer out = new OutputStreamWriter(outputStream, "UTF-8");
        svgGenerator.stream(out, true /* use css */);
        outputStream.flush();
        outputStream.close();
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
		 legend.setDisplaySeriesLines(true);


//		// get a reference to the plot for further customisation...
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
//		// plot.setAxisOffset(new Spacer(Spacer.ABSOLUTE, 5.0, 5.0, 5.0, 5.0));
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);
		plot.setRangeGridlinesVisible(true);
		

		 final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
//		 renderer.setSeriesLinesVisible(1, true);
		 renderer.setBaseItemLabelsVisible(true);
		 renderer.setDefaultLinesVisible(true);
		 renderer.setDefaultShapesFilled(true);
		 renderer.setDefaultShapesVisible(true);
		 renderer.setItemLabelsVisible(true);
//		 renderer.setSeriesItemLabelsVisible(1, true);
//		 renderer.setSeriesShapesVisible(1, false);
//		 
//		 renderer.setItemLabelGenerator(new LabelGenerator(50.0));
		 renderer.setItemLabelsVisible(null); 
		 renderer.setSeriesItemLabelsVisible(0, true);
		 
		 
		 

//		 CategoryPlot plot = (CategoryPlot)chart.getPlot(); 
//		 NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
//		 rangeAxis.setRange(0, 5);  
//		 rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
//		 plot.getDomainAxis().setMaximumCategoryLabelLines(5);
//		 XYItemRenderer renderer = plot.getRenderer();
		 
		 renderer.setBaseItemLabelsVisible(true);  
//		 renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		 
//		 ValueAxis axis = plot.getRangeAxis();
//		 axis.setTickLabelsVisible(true);
//		 axis.setLabel("xpto");

		 
		 renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 25)); 
		 
		 
		 
		 
		 
//		 plot.setToolTipGenerator(new StandardCategoryToolTipGenerator());
			
//		 StandardXYToolTipGenerator standardxytooltipgenerator = new StandardXYToolTipGenerator("{1} = {2}", new SimpleDateFormat("yyyy"), new DecimalFormat("0"));
//		 renderer.setToolTipGenerator(standardxytooltipgenerator);

		 
		 
		 
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

		for (int i = 0; i < 6; i++) {
			GraphQtdByTime demo = new GraphQtdByTime("evolucao by tempo", i);
			demo.pack();
			RefineryUtilities.centerFrameOnScreen(demo);
			demo.setVisible(true);
			
		}
		
//		GraphQtdByTime demo = new GraphQtdByTime("evolucao by tempo");
//		demo.pack();
//		RefineryUtilities.centerFrameOnScreen(demo);
//		demo.setVisible(true);


	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
