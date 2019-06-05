package vue;

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
 
public class modifier_supprimer_eleve_info extends JFrame {
	Database bdd;
	
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("modifier supprimer un eleve ");
  
  private JTextField eleve =new JTextField("rentrer eleve");
 private JTextField info_eleve =new JTextField("eleve existe");
  private JButton but_eleve= new JButton(" eleve");
  
  

  
  
  private JLabel modif = new JLabel("info a modifier");
  private JTextField nom =new JTextField("nom");
  private JTextField prenom =new JTextField("prenom");
  private JTextField age =new JTextField("age");
  private JTextField id =new JTextField("id");
  

  private JButton but_modifier= new JButton("modifer");
  

  public modifier_supprimer_eleve_info() throws ClassNotFoundException{
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
    but_eleve.addActionListener(new BoutonListener());

    info_eleve.setEnabled(false);
    nom.setEnabled(false);
   prenom.setEnabled(false);
    age.setEnabled(false);
    id.setEnabled(false);
    but_modifier.setEnabled(false);
	
    top.add(label);
    top.add(eleve);
    top.add(info_eleve);
    top.add(but_eleve);
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
	 
	    		if(source ==but_eleve)
	    		{
	    			/// verification eleve existe
	    	
	    			info_eleve.setBackground(Color.GREEN);
	    			
	    			
	    			/// si eleve existe
	    			info_eleve.setText(eleve.getText());
	    			but_modifier.setEnabled(true);
	    			 nom.setEnabled(true);
	    			   prenom.setEnabled(true);
	    			    age.setEnabled(true);
	    			    id.setEnabled(true);
	    			
	    		}	    	 
	    	 if(source== but_modifier)
	    	 {
	    		 info_eleve.getText();
	    		 ///requete sql 
	    		 
	    		 
	    		 ArrayList<String> val=new ArrayList<String>();
	    		  val.add(id.getText());
				  val.add(nom.getText());
				  val.add(prenom.getText());
				  val.add(age.getText());
				
		
				  try {
					bdd.modifierEleve(val);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    	
	    		 eleve.setText("");
	    	
	    	 }
	    	 

	

	    }
	  }
 
}
