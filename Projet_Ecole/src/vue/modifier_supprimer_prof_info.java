package vue;

import java.awt.BorderLayout;
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

import projet_ecole.Database;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class modifier_supprimer_prof_info extends JFrame {
	Database bdd;
	
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Rechercher un prof ");
  
  private JTextField prof =new JTextField("Rentrer prof");
 private JTextField info_prof =new JTextField("   ");
  private JButton but_prof= new JButton(" Prof");
  
 private  ArrayList<String> val=new ArrayList<String>();
 private  ArrayList<String> val2=new ArrayList<String>();

  
  
  private JLabel modif = new JLabel("Info a modifier");
  private JTextField nom =new JTextField("Nom");
  private JTextField prenom =new JTextField("Prenom");

  private JTextField new_discipline =new JTextField("Nouvelle discipline");
  private JTextField new_classe =new JTextField("Nouvelle classe");
  private JTextField new_niveau =new JTextField("Nouveau niveau");
  
  
  

  private JButton but_modifier= new JButton("Modifer");
  

  public modifier_supprimer_prof_info() throws ClassNotFoundException{
	  try {
		  bdd=new Database();
  } catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	  
    this.setTitle("Modifier Info Prof");
    this.setSize(600, 600);
    this.setResizable(false);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();

    top.setLayout(new GridLayout(10,1));
  
    
    
    /// boutton de top

    but_modifier.addActionListener(new BoutonListener());



    
     //// boutton de top2
    but_prof.addActionListener(new BoutonListener());

    info_prof.setEnabled(false);
    new_discipline.setEnabled(false);
   new_niveau.setEnabled(false);
   new_classe.setEnabled(false);

   
    but_modifier.setEnabled(false);
    
    Font police = new Font("Arial", Font.BOLD, 25);
   modif.setFont(police);
   label.setFont(police);
   nom.setFont(police);
   prenom.setFont(police);
   new_discipline.setFont(police);
   new_classe.setFont(police);
   new_niveau.setFont(police);
   
    label.setForeground(Color.BLUE);
    modif.setForeground(Color.BLUE);
	
    top.add(label);
    top.add(nom);
    top.add(prenom);

    top.add(info_prof);
    top.add(but_prof);
   
    top.add(modif);
 
    
    
  
    top.add(new_discipline);
    top.add(new_classe);
    top.add(new_niveau);
    top.add(but_modifier);
  

    
  
   top.setBounds(0, 0, 500, 500);

    container.add(top);  
 
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_prof)
	    		{
	    			/// verification prof existe
	    	
	    		
	    			
	    			
	    			boolean verif=true;
	    			/// verification prof existe
	    			val.add(nom.getText());
	    			val.add(prenom.getText());
	    			try {
	    				 try {
								bdd=new Database();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					verif=	bdd.isPersExiste(val, 0);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		
	    			
	    			
	    			/// si prof existe
	    			if(verif== false)
	    			{
	    			info_prof.setBackground(Color.GREEN);
	    			info_prof.setText(nom.getText());
	    			but_modifier.setEnabled(true);
	    			but_modifier.setEnabled(true);
	    			 new_discipline.setEnabled(true);
	    			   new_classe.setEnabled(true);
	    			   new_niveau.setEnabled(true);
	    			   nom.setEnabled(false);
	    			   prenom.setEnabled(false);
	    			
	    			}
	    			else {
	    				
	    				info_prof.setBackground(Color.RED);
	    				new PopUp("Le professeur saisi n'existe pas");
		    			but_modifier.setEnabled(false);
	    			}
	    			
	    			val.clear();
	    			
	    			
	    			
	    			 prof.setText("");
	    			
	    			
	    			
	    			/// si prof existe

	    		
	    			   
	    			
	    		}	    	 
	    	 if(source== but_modifier)
	    	 {
	    		 info_prof.getText();
	    		 ///requete sql 
	    		
	    		 
	    		
	    		 val2.add(nom.getText());
	    		 val2.add(prenom.getText());
				  val2.add(new_discipline.getText());
				  val2.add(new_classe.getText());
				  val2.add(new_niveau.getText());
				
		
				  try {
					bdd.modifierProf(val2);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	
				  nom.setEnabled(true);
   			   prenom.setEnabled(true);
	    	val2.clear();
	    	 }
	    	 

	

	    }
	  }
 
}
