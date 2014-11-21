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
import java.applet.Applet;
 import java.util.*;
 import java.awt.*;
 import java.awt.event.*;
import java.net.*;
import java.io.*;
import java.lang.*; // nécessaire pour transformer String en double


public class chart extends Applet implements ActionListener {

String NomFichier; 
String tablo[] = new String[43];
String ligne;
int i, co = 0, l, appui = 0;
String col[], lig[], titre, source;
double tab[][];
Button b1, b2, b3, b4, b5, b6, b7, b8; String ba;
String  texte; 
graphFram f = new graphFram(ba, tab, co, l, col, lig, titre);

 
     public void init() {
	NomFichier = getParameter("NomFichier"); 
	// lecture du fichier
	 URL u;
	 InputStream is;
	 BufferedReader dis;
         GridBagLayout gridbag = new GridBagLayout();
         GridBagConstraints c = new GridBagConstraints();
	 Label titretab;
 	 Label consignes;
	 Label consignes2;
	 Label vide;
	 Label lignaff;

         setLayout(gridbag);

	try
	{
	// Définir l'URL pointant sur le fichier
	u = new URL(getDocumentBase(), NomFichier);
	// Lire le flux d'entrée (InputStream) lié au fichier
	is = u.openStream();
	// En faire un flux de données (BufferedReader)
	dis = new BufferedReader(new InputStreamReader(is));
	// Lire le fichier jusqu'à la fin - compter le nombre de lignes
	ligne = dis.readLine();
	i = 0;
	while (ligne != null & i < 43)
	{
	tablo[i] = ligne;
	ligne = dis.readLine();
	i++;
	}
	l = i - 3; // l nombre de lignes de chiffres = nbre de lignes du tableau - 3 

	// reconnaissance des données lues
	titre = tablo[0];
	source = tablo[l+2];

	// calcul du nombre de colonnes co de chiffres

	StringTokenizer st;

	texte = tablo[1];         
	st = new StringTokenizer(texte,"\t");
	 co = st.countTokens() -1;

	// création des tableaux de chiffres, de titres des colonnes et lignes
	col = new String[co+1];
	lig = new String[l];
	tab = new double[l][co];

	// récupération des titres de colonnes
	texte = tablo[1];
	st = new StringTokenizer(texte,"\t");
	i = 0;
	while(st.hasMoreTokens()){
	col[i]=st.nextToken();
	i ++;
	 }

	// récupération des autres lignes
	for (int j=2; j<l+2; j++){                // pour chaque ligne n°2 à la fin -1
		int index = 0;
		texte = tablo[j];
		index = texte.indexOf("\t");
		if (index > -1) {		// récupération des titres de lignes
		  lig[j-2] = texte.substring(0, index); 
		  texte = texte.substring(index+1); 
			}

		st = new StringTokenizer(texte,"\t");  // récupération des chiffres
		i = 0;
		while(st.hasMoreTokens()){
		 String tex = st.nextToken();
		 // transformation des virgules éventuelles en points 
		 if (tex.indexOf(",") > -1) tex = tex.replace(',','.');
		 // suppression de blancs dans les chiffres
//		  if (tex.indexOf(" ") > -1) {
//		  tex = tex.substring(0, index)+  tex.substring(index+1); }

		Double DS = Double.valueOf(tex);
		tab[j-2][i] = DS.doubleValue();
		i ++;
		 }
	} // fin de récupération des autres lignes - boucle for j
	

	//*******  affichage du tableau ********

         // pour que le composant utilise toute la place
	c.fill = GridBagConstraints.BOTH; 

	// première zone : le titre du tableau
        c.gridwidth = GridBagConstraints.REMAINDER;
	c.gridheight = 1;
	c.weightx = 4;
	titretab = new Label(titre, Label.CENTER);
	if (titre.length() < 50) {
	  titretab.setFont(new Font("Helvetica", Font.PLAIN, 22));}
	   else {titretab.setFont(new Font("Helvetica", Font.PLAIN, 10));}
	titretab.setBackground(Color.white);
	gridbag.setConstraints(titretab, c);
	add(titretab);

	// 2e zone: le tableau, avec un panel où le mettre
	Panel tableau = new Panel();
	c.gridwidth = GridBagConstraints.REMAINDER;
	c.gridheight = 1;
	c.weightx = 20;
	GridBagLayout grille = new GridBagLayout();
	GridBagConstraints cg = new GridBagConstraints();
	cg.insets = new Insets(1,1,1,1); // écart entre les labels du tableau
	gridbag.setConstraints(tableau, c);
	tableau.setBackground(Color.white);

	tableau.setLayout(grille);
	Color coule = new Color(219,226,249); // couleur du fond du tableau

		// composants du tableau
		setFont(new Font("Arial", Font.PLAIN, 12));
		cg.fill = GridBagConstraints.BOTH; 
		cg.weighty = 1;

		// 1ère ligne - co +1 colonnes, première colonne taille 2
		// on n'affiche que 8 caractères par titre de colonne
		Label titres[] = new Label[co+1];
		for (int i = 0; i<co+1; i ++) {
		  String ss;
		  ss = col[i];	  
		  if (i >0 & ss.length() > 7) {ss = ss.substring(0,8);}
		  titres[i] = new Label(ss, Label.CENTER);
		  titres[i].setBackground(coule);
		  if (i == 0 ) {
		    cg.weightx = 2;
			}
		    else {
			 cg.weightx = 1;
			 }
		  if (i == co) {
		    cg.gridwidth = GridBagConstraints.REMAINDER; //end row 
			}
		 grille.setConstraints(titres[i],cg);
		 tableau.add(titres[i]);
		   }

		// autres lignes
		cg.gridwidth = 1;	   	 
		Label ele[][] = new Label[l+1][co+1];
        	
		// transformation du tableau  en lettres pour affichage dans le label
		  String tablettres[][] = new String[l][co];
			for (int j = 0; j<l; j ++){
			 for (int i =0; i<co; i ++){
			  tablettres[j][i] = Double.toString(tab[j][i]);
	  			}
			     }
		// affichage	
		for (int j = 0; j<l; j ++){
			cg.weightx = 2;
			cg.gridwidth = 1;
			ele[j][0] = new Label(lig[j], Label.LEFT);
		  ele[j][0].setBackground(coule);
		  grille.setConstraints(ele[j][0],cg);
		  tableau.add(ele[j][0]);

		  for (int i = 0; i<co; i ++) {
			cg.weightx = 1;
			ele[j][i+1] = new Label(tablettres[j][i], Label.CENTER);
		        ele[j][i+1].setBackground(coule);

		  if (i == co-1) {
		    cg.gridwidth = GridBagConstraints.REMAINDER; //end row 
			}
		  grille.setConstraints(ele[j][i+1],cg);
		  tableau.add(ele[j][i+1]);
		 }
		}		

	add(tableau);


	// 3e zone : la source du tableau
         c.weighty = 0.0;
        c.gridwidth = GridBagConstraints.REMAINDER;
	c.gridheight = 1;
	setFont(new Font("Arial", Font.PLAIN, 8));
	consignes = new Label(source, Label.LEFT);
	consignes.setBackground(Color.white);
	gridbag.setConstraints(consignes, c);
	add(consignes);

	// 4e zone : les consignes
	c.gridwidth = GridBagConstraints.REMAINDER;
	c.gridheight = 1;
	setFont(new Font("Arial", Font.PLAIN, 12));
	consignes2 = new Label("Cliquez sur un des boutons pour faire un graphique. ", Label.CENTER);
	gridbag.setConstraints(consignes2, c);
	consignes2.setBackground(Color.yellow);
	add(consignes2);

	// avants dernières lignes : 8 boutons pour faire un graphique
	c.gridwidth = 1;	   	   //reset to the default
 	c.gridheight = 1;
        c.weightx = 1.0;
	c.weighty = 1.0;
	b1 = new Button("courbes");
	b2 = new Button("bâtons");
	b3 = new Button("nuage de points");
	b4 = new Button("diagramme circulaire");
	b5 = new Button("courbes");
	b6 = new Button("bâtons");
	b7 = new Button("nuage de points");
	b8 = new Button("diagramme circulaire");
	Label l1 = new Label("Colonnes en abscisse :", Label.LEFT);
	l1.setBackground(Color.white);
	gridbag.setConstraints(l1, c);
	add(l1);
         gridbag.setConstraints(b1, c);
         add(b1);
	 b1.addActionListener(this);
         gridbag.setConstraints(b2, c);
         add(b2);
	 b2.addActionListener(this);
         gridbag.setConstraints(b3, c);
         add(b3);
	 b3.addActionListener(this);

     	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
         gridbag.setConstraints(b4, c);
         add(b4);
	 b4.addActionListener(this);

	c.gridwidth = 1;	   	   //reset to the default
	Label l2 = new Label("Lignes en abscisse :", Label.LEFT);
	l2.setBackground(Color.white);
	gridbag.setConstraints(l2, c);
	add(l2);
         gridbag.setConstraints(b5, c);
         add(b5);
	 b5.addActionListener(this);
         gridbag.setConstraints(b6, c);
         add(b6);
	 b6.addActionListener(this);
         gridbag.setConstraints(b7, c);
         add(b7);
	 b7.addActionListener(this);

     	   c.gridwidth = GridBagConstraints.REMAINDER; //end row
         gridbag.setConstraints(b8, c);
         add(b8);
	 b8.addActionListener(this);

	// signature
	c.gridwidth = GridBagConstraints.REMAINDER;
	vide = new Label("Applet téléchargeable sur le site http://i.gautier.free.fr/java/  ", Label.RIGHT);
	vide.setFont(new Font("Arial", Font.PLAIN, 10));
	vide.setBackground(Color.white);
	gridbag.setConstraints(vide, c);
	add(vide);
	
	// taille de la fenêtre (à donner dans la page html)
         setSize(400 + 20 * co, 220 + 10 * l);

// problèmes de lecture du fichier
 } catch (FileNotFoundException fnfe) {
      System.out.println("fichier non trouvé");
    }
    catch (IOException ioe) {
      System.out.println("erreur d'E/S " + ioe.getMessage());
    }

  
     }


	// prise en compte des événéments
	public void actionPerformed(ActionEvent event)
	{
		ba ="ba";

	// appui sur les différents boutons
		if(event.getSource() == b1){
			ba = "ba1";
		}
 		if(event.getSource() == b2){
			ba = "ba2";	
			}
		if(event.getSource() == b3){
			ba = "ba3";
		}
		if(event.getSource() == b4){
			ba = "ba4";
		}
		if(event.getSource() == b5){
			ba = "ba5";	
		}
		if(event.getSource() == b6){
			ba = "ba6";	
		}
		if(event.getSource() == b7){
			ba = "ba7";
	
		}
		if(event.getSource() == b8){
			ba = "ba8";
	
		}
		appui++;
		
		if (appui> 1) {
  		f.setVisible(false);
  		appui = 0;
 	    	}
		f.boutonappuye = ba;
		f.tab = tab;
		f.c = co;
		f.l = l;
		f.cs = col;
		f.ls = lig;
		f.titretab = titre;
//		f.setSize(27 * l,420);
		if ( l < 11 && co < 11) { f.setSize(450,420);}
		if (co < l && l >= 11) {
			 f.setSize( 27 * l,420);
		    } 
		 if (co >= l && co >=11) {
		  	f.setSize(27 * co,420);
		  	}
		f.addWindowListener( new WindowAdapter() {
		public void windowClosing(WindowEvent e){
		boolean b = false;
		e.getWindow().setVisible(b);
		appui = 0;
		// avec jdk 1.1  e.getWindow().hide();
		}
		}
		);
		f.show();	
	}
 }
 
// classe frame
class graphFram extends Frame {

	double tab[][], max, min ,tabe[][];
	int c, ce, D;
	int l, le;
        String cs[], ls[], cse[], lse[], titretab;
	String boutonappuye;

	public graphFram(String bouton, double tableau[][], int colonnes, int lignes, String col[], String lig[], String titre ){
		tab = tableau;
		c = colonnes;
		l = lignes;
		cs = col;
		ls = lig;
		titretab = titre;
		boutonappuye = bouton;

	}


	public void paint(Graphics g)
	{
	// création d'un tableau tabe de dimension D x D (D = max (c, l))
	if (c > l) { D = c; } else { D = l; }
	tabe = new double[D][D];
	cse = new String[D + 1];
	lse = new String[D];
	cse[0] = cs[0]; // 1ère colonne 1ère ligne = unités
	for (int j = 0; j < D; j ++){
	for (int i = 0; i < D; i ++) {
		tabe[i][j] = 0.0;
		}
	  }

	// inversion des lignes et colonnes si boutons 5, 6, 7, 8
	if (boutonappuye == "ba5" | boutonappuye == "ba6" | boutonappuye == "ba7" | boutonappuye == "ba8"){
	ce = l; le = c;
	for (int j = 0; j < ce; j ++){
		cse[j+1] = ls[j];      // nouvelle colonne = titre de l'ancienne ligne
	for (int i = 0; i < le; i ++) {
		lse[i] = cs[i+1];	 // nouvelle ligne = titre de l'ancienne colonne
		tabe[i][j] = tab[j][i];
		}
	  }
	} // fin d'inversion des lignes et colonnes
	else { // boutons b1 à b4
	ce = c; le = l;
	for (int j = 0; j < c; j ++){
		cse[j+1] = cs[j+1];      // nouvelle colonne = ancienne
	for (int i = 0; i < l; i ++) {
		lse[i] = ls[i];	 // nouvelle ligne = titre de l'ancienne colonne
		tabe[i][j] = tab[i][j];
		}
	  }
	} // fin de la création du nouveau tableau

	// dessins des diagrammes *******************
	if (boutonappuye == "ba4" | boutonappuye == "ba8") { 
		// titre et unités
		if (titretab.length() < 48) {g.drawString(titretab,60,36);}
		  else {g.drawString(titretab.substring(0,48),60,36);}
		g.drawString(cse[0], 5, 50); 
		g.setFont(new Font("Helvetica", Font.BOLD, 14));
		g.drawString("Légende :", 370 , 100); 
		g.setFont(new Font("Helvetica", Font.PLAIN, 12));

		// dessin des diagrammes : 2 par ligne
		for (int i=0; i<ce; i ++){
		// n° ligne où doit se trouver le cercle = n tel que i/2 < n
		int n;
		Double nbc = new Double(i/2);
		n = (nbc.intValue());

		// n° du cercle dans la ligne nl
		int nl;
		nl = i - (n * 2);
		g.setColor(Color.black);
		g.drawOval(20 + (nl * 150), 30 + 20 *( n + 1) + n*120, 120, 120);
		 // on n'affiche que 10 caractères par titre de colonne
		 String ss;
		  ss = cse[i+1];	 
		  if (ss.length() > 9) { ss = ss.substring(0,10);}
		g.drawString(ss, 60 +(nl *150), 165 + 20 *( n + 1) + n*120); 
		int angle0;
		angle0 = 0;
		int angle; 

		// couleur d'écriture
	  	for (int j = 0; j<le; j ++){
		  if (j == 0 |j == 10 |j == 20|j == 30){ g.setColor(Color.black);} 
	   	  if (j == 1 |j == 11 |j == 21|j == 31){ g.setColor(Color.red);} 
		  if (le > 1 ) { 
			if (j == 2|j == 12 |j == 22|j == 32) { g.setColor(Color.blue);}}
		  if (le>2){ if (j == 3|j == 13 |j == 23|j == 33){ g.setColor(Color.cyan);}}
		  if (le>3){if(j == 4|j == 14 |j == 24|j == 34){g.setColor(Color.green);}}
		  if (le>4){if(j == 5|j == 15 |j == 25|j == 35){g.setColor(Color.orange);}}
		  if (le>5){if(j == 6|j == 16 |j == 26|j == 36){ g.setColor(Color.pink);}}
		  if (le>6){if (j == 7|j == 17 |j == 27|j == 37){g.setColor(Color.yellow);}}
		  if (le>7){if (j == 8|j == 18 |j == 28|j == 38){ g.setColor(Color.gray);}}
		  if (le>8){if(j == 9|j == 19 |j == 29|j == 39){g.setColor(Color.magenta);}}
		  // légende
		 g.drawString(lse[j], 370, 120 + 20 * j ); 
		  // angle = valeur / total des valeurs * 360 
		  double total;
		  total = 0.0;
		  for (int k=0; k<le; k ++) {
		  if (tabe[k][i] < 0) { total = total - tabe[k][i];} // cas nombres négatifs
		   else { total = total + tabe[k][i]; }} 
		  if (tabe[j][i] < 0) { total = - tabe[j][i] / total * 360;}
		   else {  total = tabe[j][i] / total * 360; }
		  Double ANGLE = new Double(total);
		  angle = ANGLE.intValue();
		  if (total > (angle + 0.5)) { angle = angle + 1;}
		  if (j == (l -1)) { angle = 360 -angle0;};
		  // dessin des arcs d'ellipse
// fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) :
// trace arc d'ellipse inscrit dans un rectangle de dim width - height
		g.fillArc(20 + (nl * 150), 30 + 20 *( n + 1) + n*120, 120, 120, angle0, angle);
		angle0 = angle0 + angle;	
		} // fin de la boucle j
		} // fin des c cercles (boucle i)
	
	} // fin b4 et 8 (diagrammes circulaires)

	 else { // **************** début des courbes, bâtons et nuages ***************

	// éléments particuliers du tableau pour les graphiques : valeurs min, max
	for (int j = 0; j<le; j ++){
	 for (int i =0; i<ce; i ++){
           if (i ==0 & j ==0) { 
		max = tabe[j][i]; 
		}
             else  if(tabe[j][i]>max) {
		max = tabe[j][i]; 
		} 
	  			}
			     }
	for (int j = 0; j<le; j ++){
	 for (int i =0; i<ce; i ++){
           if (i ==0 & j ==0) { 
		min = tabe[j][i]; 
		}
             else  if(tabe[j][i]< min) {
		min = tabe[j][i];
		} 
	  			}
			     }


		// titre et unités
		if (titretab.length() < 49) {g.drawString(titretab,60,36);}
		  else {g.drawString(titretab.substring(0,49),60,36);}
		g.drawString(cse[0], 5, 50);

		// axes 
		g.drawLine(40, 60, 40, 360); 
		if (ce > 9) {g.drawLine(40, 360, 360+(ce-9)*15, 360);} 
		else {g.drawLine(40, 360, 360, 360);}
		// axe des 0 si le minimum est inférieur à 0 
		int yz = 0; 
		if (min < 0){
		  double d1 =  360 - (- min * (300 / (max - min)));
		  Double D1 = new Double(d1);
		  yz =  D1.intValue();
		  if (ce > 9) {g.drawLine(40, yz, 360+(ce-9)*15, yz);} 
		     else {g.drawLine(40, yz, 360, yz);}
		  g.drawString("0.0", 3 , yz);
			}

		// titres sur l'axe des x : 1er chiffre=x, 2e = y ; 
		// max axe des x = 320 ou (320+(ce-9)*15)
		for (int k=1; k<ce+1; k++){
		int x;
		if (ce > 9) {x = 40 + (k-1) * (320+(ce-9)*15) / (ce-1); } 
		else {	x = 40 + (k-1) * 320 / (ce-1); }
		 // on n'affiche que 8 caractères par titre de colonne
		 String ss;
		  ss = cse[k];	 
		  if (ss.length() > 8){ss = ss.substring(0,8);} else { ss = cse[k]; }
		  g.setFont(new Font("Helvetica", Font.PLAIN, 8)); 
		  g.drawString(ss, x-7 , 372); 
		  g.setFont(new Font("Helvetica", Font.PLAIN, 12));
		  g.drawLine(x, 360, x, 355); 
		    if (min < 0) g.drawLine(x, yz + 2, x, yz-2);
			}
		String str, str1;
		str = Double.toString(max);
		g.drawString(str, 3 , 62);
		str = Double.toString(min);
		g.drawString(str, 3 , 362);
	
		// graduations de l'axe des y 
		double ec, ecart;
		ecart = (max - min) / 10;
		if (ecart < 0) ecart = - ecart;
		ec = 0;
		if (((max-min)/10)<0.1 ) ec = ecart* 100; 
			else {
		if (((max-min)/10)<1 ) ec = ecart*10; 
			else {
		if (((max-min)/10)< 10){ 
			Double Ecart = new Double(ecart); ec = Ecart.intValue();}
			else {
		if (((max-min)/10)< 100){
			Double Ecart = new Double(ecart/10); 
			ec = (Ecart.intValue() * 10);}
			else {
		if (((max-min)/10)< 1000){ 
			Double Ecart = new Double(ecart/100); 
			ec = (Ecart.intValue() * 100); }
			else {
		if (((max-min)/10)< 10000){ 
			Double Ecart = new Double(ecart/1000); 
			ec = (Ecart.intValue() * 1000); }
			else {
		if (((max-min)/10)< 100000){ 
			Double Ecart = new Double(ecart/10000); 
			ec = (Ecart.intValue() * 10000); }
			}}}}}}

		 int depart = 0;
		 int arrive = 0;
		 if (min < 0) {
		    double ecmin = min / ec;
		    if (((max-min)/10)<0.1 ) ecmin = min*100 / ec; 
			else {
		    if (((max-min)/10)<1 ) ecmin = min*10 / ec;  
			}
			Double Ecmin = new Double(ecmin);
			depart = Ecmin.intValue();
		    }

		double ecmax = max /ec;
		if (((max-min)/10)<0.1 ) ecmax = max * 100 / ec;
			else {
		if (((max-min)/10)<1 ) ecmax = max * 10 / ec;
			}
		Double Ecmax = new Double(ecmax);
		arrive = Ecmax.intValue();
	
		for (int k = depart; k < arrive; k ++) {
		 if ((ecart >0.99)&((ec*k*9.9/10)>min)&((ec*k*10.1/10)<max)){
			 double grad = 360 -((ec * k- min) * 300 / (max -min));
			 Double GRAD = new Double(grad);
			 int yg = GRAD.intValue(); 
			 str = Double.toString(ec * k);
			 g.drawString(str, 3 , yg);
			 g.drawLine(40, yg, 45, yg);
			}// fin du if
		if (ecart<0.1){
			 double grad = 360 -((ec/100 * k- min) * 300 / (max -min));
			 Double GRAD = new Double(grad);
			 int yg = GRAD.intValue(); 
			 str = Double.toString(ec*k/100);
			 g.drawString(str, 3 , yg);
			 g.drawLine(40, yg, 45, yg);
			}// fin du if
		if ((ecart>0.09)&(ecart<1)){
			 double grad = 360 -((ec/10 * k- min) * 300 / (max -min));
			 Double GRAD = new Double(grad);
			 int yg = GRAD.intValue(); 
			 str = Double.toString(ec*k/10);
			 g.drawString(str, 3 , yg);
			 g.drawLine(40, yg, 45, yg);
			}// fin du if
		  }// fin de la boucle for k 
		}


	// courbes , origine=40,360 ; max y = 60 ; min y = 360 ; max x = 360 ou 360+(ce-9)*15
	// sur l'axe des y : (max-min) = 300 
	// dessiner 1ère colonne, puis 2e, puis 3e avec le même i (ligne)
	//  y = 360 - (valeur * (300 / (max - min)) )
	// x de 40 à 360 =>  320 sur l'axe des x, espacement = 320 / (ce-1)
	// x = 40 + (j * 320 / (ce-1))
	g.setFont(new Font("Helvetica", Font.BOLD, 14));
	if (ce > 9) g.drawString("Légende :", 370 +(ce-9)*15, 100); 	// légende
	else g.drawString("Légende :", 370 , 100);
	g.setFont(new Font("Helvetica", Font.PLAIN, 12)); 		
		int x1, y1, x0, y0;
		x1 = 0; y1 = 0; x0 = 0; y0 = 0;
	  for (int j = 0; j<le; j ++){
		if (j == 0 | j==10 | j==20 | j==30) { g.setColor(Color.black);}
	   	if (j == 1 | j==11 | j==21 | j==31) { g.setColor(Color.red);}
		if (le > 1 ) { if (j==2 | j==12 | j==22 | j==32) { g.setColor(Color.blue);}}
		if (le > 2 ) { if (j == 3| j==13 | j==23 | j==33){ g.setColor(Color.cyan);}}
		if (le > 3 ) { if (j == 4 | j==14 | j==24|j==34) {g.setColor(Color.green);}}
		if (le > 4 ) { if (j == 5 | j==15 |j==25|j==35) {g.setColor(Color.orange);}}
		if (le > 5 ) { if (j == 6 | j==16 | j==26|j==36){ g.setColor(Color.pink);}}
		if (le > 6 ) { if (j == 7 | j==17 |j==27|j==37){g.setColor(Color.yellow);}}
		if (le > 7 ) { if (j == 8 | j==18 |j==28 | j==38){ g.setColor(Color.red);}}
		if (le > 8 ) { if (j == 9|j==19 |j==29| j==38){g.setColor(Color.magenta);}}
		if (le > 9 ) { if (j == 10| j==20 | j==30|j==40){g.setColor(Color.gray);}}
	     for (int i = 0; i<ce; i ++){
			double d1 =  360 - ((tabe[j][i]- min) * (300 / (max - min)));
			Double D1 = new Double(d1);
			y1 =  D1.intValue();
		if (ce > 9) {x1 = (i * (320+(ce-9)*15) / (ce-1)) + 40;} 
		else {	x1 = (i * 320 / (ce-1)) + 40;}
		// dessin des courbes si le bouton b1 ou b5 a été appuyé
		if ((boutonappuye == "ba1" | boutonappuye == "ba5") & i>0) {
                        double d0 = 360 - ((tabe[j][i-1] - min) * ( 300 / (max-min)));
			Double D0 = new Double(d0);
		if (ce > 9) { x0 = ((i-1) * (320+(ce-9)*15) / (ce-1)) + 40;}
		else {	x0 = ((i-1) * 320 / (ce-1)) + 40;}
			y0 = D0.intValue();
		  g.drawLine(x0, y0, x1, y1);}

		// dessin des bâtons si le bouton b2 ou b6 a été appuyé 
		//  fillRect(int x, int y, int width, int height)
		// nbre de bâtons : (le+1) * ce -1 (un espace entre chaque date) 
		// => width = 320 / (le +1)*ce - 1); height= 360-y; y : celui calculé
		// x = 40 + (j+(i*(le+1))+intValue(j/l))* 320 / ((l +1)*c-1)
		if (boutonappuye == "ba2" | boutonappuye == "ba6") {
		   if (tabe[j][i] == min) {y1 = y1 - 1;}
		   double espace = j / le;
		   Double Esp = new Double(espace);
		   int esp;
		   esp = Esp.intValue();
	if (ce >9){int xx1 = 40 + (j+(i*(le+1)) + esp)* (320+(ce-9)*15)/ ((le +1)*ce-1); 
	g.fillRect(xx1, y1, 320 / ((le +1)*ce-1), 360-y1);}
	else g.fillRect(40+(j+(i*(le+1))+esp)*320/((le+1)*ce-1),y1,320/((le+1)*ce-1),360-y1);}
		// dessin des points du nuage si le bouton b3 ou b7 a été appuyé
		if (boutonappuye == "ba3" | boutonappuye == "ba7") {
		  g.fillRect(x1 -2, y1 - 2, 4, 4);}
		 } // fin de la boucle i
	if (ce > 9) g.drawString(lse[j], 235+ce*15, 120 + 20 * j );	// légende
	else g.drawString(lse[j], 370, 120 + 20 * j ); 
		} // fin de la boucle j

		} // fin de la partie commune b1, b2, b3, b5, b6, b7
		


	} // fin


