

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Jfreetest extends JPanel {

	private static final long serialVersionUID = 1L;

	/** titre : Le titre du graphique affich� en haut */
	private String titre;
	/** ordonnee : le nom de l'axe des ordonn�es */
	private String ordonnee;
	/** abscisses : le nom de l'axe des abscisses */
	private String abscisse;
	/** valeurs : les valeurs � afficher, elles sont tri�es par s�ries et par cat�gories*/
	private List<Float> valeurs;
	/** series : la liste des s�ries */
	private List<String> series;
	/** categories : la liste des categories */
	private List<String> categories;
	/** legende : booleen vrai si on affiche la l�gende */
	private boolean legende;
	/** couleurFond : la couleur du fond */
	private Color couleurFond;
	/** couleurBarres : les couleurs appliqu�es aux barres */
	private Color[] couleursBarres = {Color.cyan.darker(), 
			Color.red, Color.green, Color.cyan, Color.magenta, 
			Color.yellow, Color.pink, Color.darkGray, Color.orange};

	/*
	 
 Constructeur
	 
 @param titre : le titre du graphique
	 
 @param abscisse : le nom de l'axe des abscisses
	 
 @param ordonnee : le nom de l'axe des ordonn�es
	 
 @param valeurs : les valeurs
	 
 @param fond : la couleur de fond
	 
 @param listeSeries : les s�ries
	 
 @param listeCategory : les cat�gories
	 
 @param legende : vrai si on affiche la l�gende
	 
*/

	public Jfreetest(String titre, String abscisse, String ordonnee, List<Float> valeurs, Color fond, List<String> listeSeries, List<String> listeCategory, boolean legende) {
		super(new GridLayout(1,0));
		this.titre=titre;
		this.ordonnee=ordonnee;
		this.abscisse=abscisse;
		this.valeurs=valeurs;
		this.series=listeSeries;
		this.categories=listeCategory;
		this.legende=legende;
		this.couleurFond=fond;
		initialiser();
	}


	private void initialiser(){
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		int k = 0;
		for ( int j=0; j<categories.size(); j++){
			for (int i=0; i<series.size(); i++){
				dataset.addValue(valeurs.get(k), series.get(i), categories.get(j));
				k++;
			}

		}
		JFreeChart chart = ChartFactory.createBarChart(
				titre,   					// chart title
				abscisse,					// domain axis label
				ordonnee,   				// range axis label
				dataset,    				// data
				PlotOrientation.VERTICAL, 	// orientation
				legende,                    // include legend
				true,                     	// tooltips
				false                     	// URL
		);

		// definition de la couleur de fond
		chart.setBackgroundPaint(couleurFond);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();

		//valeur comprise entre 0 et 1 transparence de la zone graphique
		plot.setBackgroundAlpha(0.9f);

		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		// pour la couleur des barres pour chaque serie

		for (int s=0; s<series.size(); s++){
			GradientPaint gp0 = new GradientPaint(0.0f, 0.0f, couleursBarres[s],
					0.0f, 0.0f, new Color(0, 40, 70));
			renderer.setSeriesPaint(s, gp0);

		}		

		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setFillZoomRectangle(true);
		chartPanel.setMouseWheelEnabled(true);
		chartPanel.setPreferredSize(new Dimension(500, 270));

		add(chartPanel);
	}



	public static void main(String[] a){
		List<Float> donnees = new ArrayList<Float>();
		List<String> l1 = new ArrayList<String>();
		List<String> l2 = new ArrayList<String>();
		l2.add("0");
		l1.add("1");
		l1.add("2");
		l1.add("3");
		l1.add("4");
		donnees.add(2f);
		donnees.add(3f);
		donnees.add(4f);
		donnees.add(4f);
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setBounds(10,10,500,500);
		Jfreetest g = new Jfreetest("titre", "x", "y", donnees, Color.white, l2, l1, true);
		f.add(g);
		f.setVisible(true);
	}

}