package vue;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
 
public class Jfreetest_pie extends ApplicationFrame {
	static double c1;
	static double c2;
	static double c3;
	static double c4;
	
   public Jfreetest_pie( String title, double a,double b, double c, double d ) {
      super( title ); 
      c1=a;
      c2=b;
      c3=c;
      c4=d;
      
      setContentPane(createDemoPanel( c1,c2,c3,c4));
      
     
   }
   
   private static PieDataset createDataset(double c1,double c2,double c3,double c4) {

      DefaultPieDataset dataset = new DefaultPieDataset( );
      dataset.setValue( "0-5" , new Double( c1 ) );  
      dataset.setValue( "5-10" , new Double( c2 ) );   
     dataset.setValue( "10-15" , new Double( c3) );    
      dataset.setValue( "15-20" , new Double( c4) );  
      return dataset;         
   }
   
   private static JFreeChart createChart( PieDataset dataset ) {
      JFreeChart chart = ChartFactory.createPieChart(      
         "Repartition des moyennes",   // chart title 
         dataset,          // data    
         true,             // include legend   
         true, 
         false);

      return chart;
   }
   
   public static JPanel createDemoPanel( double c1,double c2,double c3, double c4) {
      JFreeChart chart = createChart(createDataset(c1,c2,c3,c4 ) );  
      return new ChartPanel( chart ); 
   }

 
}