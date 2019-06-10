package vue;


import java.awt.BorderLayout;
import projet_ecole.Reporting;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jfree.ui.RefineryUtilities;


import projet_ecole.Database;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class stat extends JFrame{

	
	Database bdd;
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Reporting",JLabel.CENTER);
	  private JTextField td=new JTextField("TD");
	  private JTextField niveau=new JTextField("ING");
	  private JTextField trimestre=new JTextField("trimestre");
	  private JButton valider = new JButton ("moyenne classe");
	  private JButton valider2 = new JButton ("moyenne discipline");
	  private JTextField discipline=new JTextField("Rentrer la discipline");
	  
	  private  ArrayList<String> val=new ArrayList<String>();
	  private  double[] result=new double[4];
	  

	  public stat() throws ClassNotFoundException{
		  
		  try {
			  bdd=new Database();
	  } catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		  
		  JPanel top=new JPanel();
		  JPanel top2 =new JPanel();
		  
		  top.setLayout(new GridLayout(4,1));
		  top2.setLayout(new GridLayout(2,1));
		  
	    this.setTitle("Reporting");
	    this.setSize(775, 600);
	    this.setResizable(false);
	 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	
	   container.setLayout(null);
	    valider.addActionListener(new BoutonListener());
	    valider2.addActionListener(new BoutonListener());
	     
	    
	    top.setBounds(0, 100, 200, 200);
	    top2.setBounds(300, 100, 400, 200);
	    Font police = new Font("Arial", Font.BOLD, 25);
	    td.setFont(police);
	    niveau.setFont(police);
	    trimestre.setFont(police);
	    discipline.setFont(police);
	    
top.add(td);
top.add(niveau);
top.add(trimestre);
top.add(valider);

top2.add(discipline);
top2.add(valider2);


container.add(top);
container.add(top2);
this.setContentPane(container);
this.setVisible(true);  

	  }
class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	    	 
	    	 
	    	 if(source == valider) {
	    	 
	    	 
	    	 try {
	    		  bdd=new Database();
	    		  val.add(td.getText());
	    		  val.add(niveau.getText());
	    		  val.add(trimestre.getText());
	    		
	    		 result = Reporting.moyenneClasse(bdd, val);}
	    	 catch(SQLException | ClassNotFoundException e1)
	    	{
	    		System.out.println(e1.getMessage());
	    	}
	    	
	    		    
	    		    Jfreetest_pie demo = new Jfreetest_pie( "Moyenne",result[0],result[1],result[2],result[3] );  
	    		      demo.setSize( 560 , 367 );    
	    		      RefineryUtilities.centerFrameOnScreen( demo );    
	    		      demo.setVisible( true );
	    		      
	    		  val.clear(); 
	    	
	    	 }
	    	 
	    	 if(source == valider2)
	    	 {
	    		 try {
		    		  bdd=new Database();
		    		  val.add(discipline.getText());
		    	
		    		 result = Reporting.moyenneDiscipline(bdd, val);
	    		 }
		    	 catch(SQLException | ClassNotFoundException e1)
		    	{
		    		System.out.println(e1.getMessage());
		    	}
		    	
	    		 
	    		  Jfreetest_pie demo = new Jfreetest_pie( "Moyenne Disicpline ",result[0],result[1],result[2],result[3] );  
    		      demo.setSize( 560 , 367 );    
    		      RefineryUtilities.centerFrameOnScreen( demo );    
    		      demo.setVisible( true );
    		      val.clear();
	    	 }
	    	 
	    		    
	    		  }
	    }
	  }


