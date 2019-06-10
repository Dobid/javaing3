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
 
public class modifier_supprimer_eleve_info extends JFrame {
	Database bdd;
	
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Modifier info  eleve ");
  
  private JTextField eleve =new JTextField("Rentrer eleve");
 private JTextField info_eleve =new JTextField(" ");
  private JButton but_eleve= new JButton(" valider");
  
 private  ArrayList<String> val=new ArrayList<String>();
 private  ArrayList<String> val2=new ArrayList<String>();

  
  
  private JLabel modif = new JLabel("Info a modifier");
  private JTextField nom =new JTextField("Nom");
  private JTextField prenom =new JTextField("Prenom");

  private JTextField new_nom =new JTextField("Nouveau nom");
  private JTextField new_prenom =new JTextField("Nouveau prenom");
  private JTextField new_age =new JTextField("Nouvel age");
  
  
  

  private JButton but_modifier= new JButton("Modifer");
  

  public modifier_supprimer_eleve_info() throws ClassNotFoundException{
	  try {
		  bdd=new Database();
  } catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	  
    this.setTitle("Mofifier Info eleve");
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

    but_eleve.addActionListener(new BoutonListener());

    info_eleve.setEnabled(false);
    new_nom.setEnabled(false);
   new_prenom.setEnabled(false);
   new_age.setEnabled(false);
    but_modifier.setEnabled(false);
	
    Font police = new Font("Arial", Font.BOLD, 25);
    label.setFont(police);
    label.setForeground(Color.BLUE);
    nom.setFont(police);
    prenom.setFont(police);
    modif.setFont(police);
    modif.setForeground(Color.BLUE);
    
    new_nom.setFont(police);
    new_prenom.setFont(police);
    new_age.setFont(police);
    
    top.add(label);
    top.add(nom);
    top.add(prenom);

    top.add(info_eleve);
    top.add(but_eleve);
   
    top.add(modif);
 
    
    
  
    top.add(new_nom);
    top.add(new_prenom);
    top.add(new_age);
    top.add(but_modifier);
  

    
  
   top.setBounds(0, 0, 500, 500);

    container.add(top);  
 
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_eleve)
	    		{
	    			/// verification eleve existe
	    	
	    		
	    			
	    			
	    			boolean verif=true;
	    			/// verification eleve existe
	    			val.add(nom.getText());
	    			val.add(prenom.getText());
	    			try {
	    				 try {
								bdd=new Database();
							} catch (ClassNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					verif=	bdd.isPersExiste(val, 1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		
	    			
	    			
	    			/// si eleve existe
	    			if(verif== false)
	    			{
	    			info_eleve.setBackground(Color.GREEN);
	    			info_eleve.setText(nom.getText());
	    			but_modifier.setEnabled(true);
	    			but_modifier.setEnabled(true);
	    			 new_nom.setEnabled(true);
	    			   new_prenom.setEnabled(true);
	    			   new_age.setEnabled(true);
	    			
	    			}
	    			else {
	    				
	    				info_eleve.setBackground(Color.RED);
	    				new PopUp("L'eleve saisi n'existe pas");
		    			but_modifier.setEnabled(false);
	    			}
	    			
	    			val.clear();
	    			
	    			
	    			
	    			 eleve.setText("");
	    			
	    			
	    			
	    			/// si eleve existe

	    		
	    			   
	    			
	    		}	    	 
	    	 if(source== but_modifier)
	    	 {
	    		 info_eleve.getText();
	    		 ///requete sql 
	    		
	    		 
	    		
	    		 val2.add(nom.getText());
	    		 val2.add(prenom.getText());
				  val2.add(new_nom.getText());
				  val2.add(new_prenom.getText());
				  val2.add(new_age.getText());
				
		
				  try {
					bdd.modifierEleve(val2);
					val2.clear();
					nom.setText("nom");
					prenom.setText("prenom");
					new_nom.setText("nom");
					new_prenom.setText("prenom");
					new_age.setText("age");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	
	  
	    	
	    	 }
	    	 

	

	    }
	  }
 
}
