


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

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
  private JLabel label = new JLabel("modifier supprimer un prof ");
  
  private JTextField prof =new JTextField("rentrer prof");
 private JTextField info_prof =new JTextField("info prof");
  private JButton but_prof= new JButton(" prof");
  
  

  
  
  private JLabel modif = new JLabel("info a modifier");
  private JTextField nom =new JTextField("nom");
  private JTextField prenom =new JTextField("prenom");
  private JTextField age =new JTextField("age");
  private JTextField id =new JTextField("id");
  

  private JButton but_modifier= new JButton("modifer");
  

  public modifier_supprimer_prof_info() throws ClassNotFoundException{
	  try {
		  bdd=new Database();
  } catch(SQLException e)
	{
		System.out.println(e.getMessage());
	}
	  
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();

    top.setLayout(new FlowLayout());
  
    
    
    /// boutton de top

    but_modifier.addActionListener(new BoutonListener());



    
     //// boutton de top2
    but_prof.addActionListener(new BoutonListener());

    info_prof.setEnabled(false);
    nom.setEnabled(false);
   prenom.setEnabled(false);
    age.setEnabled(false);
    id.setEnabled(false);
    but_modifier.setEnabled(false);
	
    top.add(label);
    top.add(prof);
    top.add(info_prof);
    top.add(but_prof);
    top.add(modif);
 
    
    
    top.add(nom);
    top.add(prenom);
    top.add(age);
    top.add(id);
    top.add(but_modifier);
  

    
  
   top.setBounds(20, 78, 160, 200);

    container.add(top);  
 
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_prof)
	    		{
	    			/// verification eleve existe
	    	
	    			info_prof.setBackground(Color.GREEN);
	    			
	    			
	    			/// si eleve existe
	    			info_prof.setText(prof.getText());
	    			but_modifier.setEnabled(true);
	    			 nom.setEnabled(true);
	    			   prenom.setEnabled(true);
	    			    age.setEnabled(true);
	    			    id.setEnabled(true);
	    			
	    		}	    	 
	    	 if(source== but_modifier)
	    	 {
	    		 info_prof.getText();
	    		 ///requete sql 
	    		 
	    		 
	    		 ArrayList<String> val=new ArrayList<String>();
	    		  val.add(id.getText());
				  val.add(nom.getText());
				  val.add(prenom.getText());
				  val.add(age.getText());
				
		
				  try {
					bdd.modifierProf(val);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	
	    		 prof.setText("");
	    	
	    	 }
	    	 

	

	    }
	  }
 
}
