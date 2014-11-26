package projet.chart;

//
//
//import java.applet.Applet;
//import java.awt.Font;
//import java.awt.Font;
//
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.axis.ValueAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.renderer.category.CategoryItemRenderer;
//import org.jfree.chart.renderer.category.StatisticalBarRenderer;
//import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
//import org.jfree.data.statistics.StatisticalCategoryDataset;
//import org.jfree.ui.ApplicationFrame;
//import org.jfree.ui.RefineryUtilities;
//
//import javax.servlet.http.HttpSession;
//import javax.swing.JFrame;
//
//import org.jfree.chart.ChartPanel;
//import org.jfree.chart.JFreeChart;
//import org.jfree.chart.axis.CategoryAxis;
//import org.jfree.chart.axis.NumberAxis;
//import org.jfree.chart.axis.ValueAxis;
//import org.jfree.chart.plot.CategoryPlot;
//import org.jfree.chart.renderer.category.CategoryItemRenderer;
//import org.jfree.chart.renderer.category.StatisticalBarRenderer;
//import org.jfree.data.statistics.DefaultStatisticalCategoryDataset;
//import org.jfree.data.statistics.StatisticalCategoryDataset;
//import org.jfree.ui.RefineryUtilities;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import projet.client.repository.ClientRepository;
//
//
//
//public class chart extends  Applet {
//	
//	@Autowired
//	private ClientRepository c;
//
//	private ApplicationFrame f = new ApplicationFrame(null);
//	
//	public chart(final String title) {
//		super();
//        final StatisticalCategoryDataset dataset = createDataset();
//
//        final CategoryAxis xAxis = new CategoryAxis("Type");
//        xAxis.setLowerMargin(0.01d); // percentage of space before first bar
//        xAxis.setUpperMargin(0.01d); // percentage of space after last bar
//        xAxis.setCategoryMargin(0.05d); // percentage of space between categories
//        final ValueAxis yAxis = new NumberAxis("Value");
//
//        // define the plot
//        final CategoryItemRenderer renderer = new StatisticalBarRenderer();
//        final CategoryPlot plot = new CategoryPlot(dataset, xAxis, yAxis, renderer);
//
//        final JFreeChart chart =
//        		new JFreeChart("Statistical Bar Chart Demo",  new Font("Helvetica", Font.BOLD, 14),  plot, true);
//       //chart.setBackgroundPaint(Color.white);
//        // add the chart to a panel...
//        final ChartPanel chartPanel = new ChartPanel(chart);
//        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
//        f.setContentPane(chartPanel);
//        
//
//    }
//	
//	private StatisticalCategoryDataset createDataset() {
//
//        final DefaultStatisticalCategoryDataset result = new DefaultStatisticalCategoryDataset();
//
//        result.add(32.5, 17.9, "Series 1", "Type 1");
//        result.add(27.8, 11.4, "Series 1", "Type 2");
//        result.add(29.3, 14.4, "Series 1", "Type 3");
//        result.add(37.9, 10.3, "Series 1", "Type 4");
//
//        result.add(22.9,  7.9, "Series 2", "Type 1");
//        result.add(21.8, 18.4, "Series 2", "Type 2");
//        result.add(19.3, 12.4, "Series 2", "Type 3");
//        result.add(30.3, 20.7, "Series 2", "Type 4");
//
//        result.add(12.5, 10.9, "Series 3", "Type 1");
//        result.add(24.8,  7.4, "Series 3", "Type 2");
//        result.add(19.3, 13.4, "Series 3", "Type 3");
//        result.add(17.1, 10.6, "Series 3", "Type 4");
//
//        return result;
//
//    }
//	
//	
//}

/* **************** Applet réalisée par Isabelle Gautier *********************
 ******* téléchargeable sur le site http://i.gautier.free.fr/java/ ************
 ******* protégée par la licence LLDL-v1, Licence de Libre Diffusion   ********
 *** à consulter sur :  http://pauillac.inria.fr/~lang/licence/v1/lldd.html ***
 ******** Vous pouvez librement utiliser, modifier et diffuser ce document ****
 *****************  mais uniquement à titre gratuit. **************************
 *** C'est mon premier programme en java, si vous l'améliorez ou avez des *****
 ********** critiques constructives à faire, merci de me les envoyer à ********
 *****************     i.gautier@wanadoo.fr      ******************************
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.util.Rotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// nécessaire pour transformer String en double

import projet.client.model.Client;
import projet.client.repository.ClientRepository;

@Controller
@RequestMapping("/charts")
public class chart {

	@Autowired
	private ClientRepository clientRepository;
	private ArrayList<Client> c = new ArrayList<Client>();

	private XYSeries series1 = new XYSeries("Planned");
	private XYSeries series2 = new XYSeries("Delivered");
	private XYSeries series3 = new XYSeries("Third");

	// http://www.massapi.com/class/de/DefaultXYDataset.html

	@RequestMapping(value = "/piechart", method = RequestMethod.GET)
	public void DrawPieChart(HttpServletResponse reponse) {
		reponse.setContentType("image/png");
		PieDataset pdSet = createDataSet();

		JFreeChart chart = createChart(pdSet, "My Pie Chart");

		try {
			ChartUtilities.writeChartAsPNG(reponse.getOutputStream(), chart,
					750, 400);
			reponse.getOutputStream().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private JFreeChart createChart(PieDataset pdSet, String string) {
		JFreeChart chart = ChartFactory.createPieChart3D(string, pdSet, true,
				true, false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);

		return chart;
	}

	private PieDataset createDataSet() {

		c = (ArrayList<Client>) clientRepository.findAll(); //tableau contenant otus les cients
		ArrayList<Client> c2 = new ArrayList<Client>();// tableau qui va contenir les 5 plus gros depensier

		int i;
		int tmp = 0, tmp2 = 0;

		for (int j = 0; j < 5; j++) {
			double somme = 0;
			for ( i = 0; i < c.size(); i++) {
				System.out.println("client a l'index " + i + " est  " + c.get(i).getName() );
				System.out.println("somme a l'index " + i + " est  " + c.get(i).getSommePayer() +"\n" );
				if (somme < c.get(i).getSommePayer()) {
					somme = c.get(i).getSommePayer();
					tmp = i;
				}
				
			}
			c2.add(c.get(tmp));
			c.remove(tmp);
		}
		

		DefaultPieDataset dpd = new DefaultPieDataset();
//		dpd.setValue("Mac", 15);
//		dpd.setValue("Linux", 30);
//		dpd.setValue("Windows", 50);
//		dpd.setValue("Other", 55);
		for(int j = 0 ; j<c2.size() ; j ++)
		{
			System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH3  ");
			dpd.setValue(c2.get(j).getName(), c2.get(j).getSommePayer());
			System.out.println("client " + j + " : " + c2.get(j).getName() + " --> somme : " + c2.get(j).getSommePayer() +  "\n");
			//System.out.println("le somme N° " + j + " dans le graphique vaut " + somme + " et le client est " + c.get(j).getName() +  "\n");
		}
		return dpd;
	}

	/* histo */

	@RequestMapping(value = "/piechart2", method = RequestMethod.GET)
	public void DrawChart(HttpServletResponse reponse) {

		// create a dataset...

		series1.add(1.0, 1.0);
		series1.add(2.0, 4.0);
		series1.add(3.0, -3.0);
		series1.add(4.0, 5.0);
		series1.add(5.0, 5.0);
		series1.add(6.0, 7.0);
		series1.add(7.0, 7.0);
		series1.add(8.0, 8.0);

		series2.add(1.0, 5.0);
		series2.add(2.0, 7.0);
		series2.add(3.0, 6.0);
		series2.add(4.0, 8.0);
		series2.add(5.0, -4.0);
		series2.add(6.0, 4.0);
		series2.add(7.0, 2.0);
		series2.add(8.0, 1.0);

		series3.add(3.0, 4.0);
		series3.add(4.0, 3.0);
		series3.add(5.0, 2.0);
		series3.add(6.0, -3.0);
		series3.add(7.0, 6.0);
		series3.add(8.0, 3.0);
		series3.add(9.0, -4.0);
		series3.add(10.0, 3.0);

		reponse.setContentType("image/png");
		XYDataset pdSet = (XYDataset) createDataSet2();

		JFreeChart chart = createChart2(pdSet, "My histo Chart");

		try {
			ChartUtilities.writeChartAsPNG(reponse.getOutputStream(), chart,
					750, 400);
			reponse.getOutputStream().close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	private JFreeChart createChart2(XYDataset pdSet, String string) {

		JFreeChart chart = ChartFactory.createXYLineChart("TestChart", "X",
				"Y", pdSet, PlotOrientation.VERTICAL, false, false, false);
		XYPlot plot = (XYPlot) chart.getPlot();

		return chart;
	}

	private DefaultXYDataset createDataSet2() {
		DefaultXYDataset dpd = new DefaultXYDataset();
		dpd.addSeries("Planned", series1.toArray());
		dpd.addSeries("Delivered", series2.toArray());
		dpd.addSeries("Third", series3.toArray());
		return dpd;
	}

	@RequestMapping(value = "/piechart1", method = RequestMethod.GET)
	public String chart() {
		return "chart";
	}

} // fin

