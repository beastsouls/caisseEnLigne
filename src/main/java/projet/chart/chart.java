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

import javax.servlet.http.HttpServletResponse;

import org.hibernate.cfg.annotations.reflection.XMLContext.Default;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.util.Rotation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// nécessaire pour transformer String en double

@Controller
@RequestMapping("/charts")
public class chart {

	@RequestMapping(value="/piechart", method = RequestMethod.GET)
	public void DrawPieChart(HttpServletResponse reponse)
	{
		reponse.setContentType("image/png");
		PieDataset pdSet = createDataSet();
		
		JFreeChart chart = createChart(pdSet,"My Pie Chart");
		
		try{
			ChartUtilities.writeChartAsPNG(reponse.getOutputStream(),chart,750,400);
			reponse.getOutputStream().close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
	}

	private JFreeChart createChart(PieDataset pdSet, String string) {
		JFreeChart chart = ChartFactory.createPieChart3D(string, pdSet,true,true,false);
		PiePlot3D plot = (PiePlot3D) chart.getPlot();
		plot.setStartAngle(290);
		plot.setDirection(Rotation.CLOCKWISE);
		plot.setForegroundAlpha(0.5f);
				
		return chart;
	}

	private PieDataset createDataSet() {
		DefaultPieDataset dpd = new DefaultPieDataset();
		dpd.setValue("Mac", 15);
		dpd.setValue("Linux", 30);
		dpd.setValue("Windows", 50);
		dpd.setValue("Other", 55);
		return dpd;
	}
	
	@RequestMapping(value="/piechart1", method = RequestMethod.GET)
	public String  chart()
	{
		return "chart";
	}
} // fin


