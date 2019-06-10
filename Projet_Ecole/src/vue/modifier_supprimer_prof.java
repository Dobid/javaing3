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
 
public class modifier_supprimer_prof extends JFrame {
  private JPanel container = new JPanel();
 
  
  private JTextField nom_prof =new JTextField("Nom ");
  private JTextField prenom_prof =new JTextField("Prenom ");
 private JTextField info_prof =new JTextField("    ");
  private JButton but_prof= new JButton(" Prof");
  private  ArrayList<String> val=new ArrayList<String>();
  private  ArrayList<String> val2=new ArrayList<String>();
  
  
	Database bdd;

  
  
  private JLabel modif = new JLabel("Info a modifier",JLabel.CENTER);
  private JLabel supp = new JLabel("Supprimer prof",JLabel.CENTER);

  private JButton but_supp= new JButton("Supprimer");
  private JButton but_info= new JButton("info");


  public modifier_supprimer_prof(){
    this.setTitle("Modifier / Supprimer prof");
    this.setSize(1000, 700);
    this.setResizable(false);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    JPanel top2 = new JPanel();
    top.setLayout(new GridLayout(2,1));
    top2.setLayout(new GridLayout(6,1));
    
    
    /// boutton de top


    but_info.addActionListener(new BoutonListener());


    
     //// boutton de top2
    but_prof.addActionListener(new BoutonListener());
    but_supp.addActionListener(new BoutonListener());
	but_supp.setEnabled(false);
    info_prof.setEnabled(false);

    
    Font police = new Font("Arial", Font.BOLD, 25);
    modif.setFont(police);
    supp.setFont(police);
    nom_prof.setFont(police);
    prenom_prof.setFont(police);
    info_prof.setFont(police);

    
    
    modif.setForeground(Color.BLUE);
    supp.setForeground(Color.BLUE);
    
    
    
    top.add(modif);

    top.add(but_info);
    
  top2.add(supp);
  top2.add(nom_prof);
  top2.add(prenom_prof);
  top2.add(but_prof);
  top2.add(info_prof);
  top2.add(but_supp);
    
  
   top.setBounds(0, 0, 300, 300);
   top2.setBounds(350, 0, 500, 500);
   
    container.add(top);  
    container.add(top2);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_prof)
	    		{
	    			boolean verif=true;
	    			/// verification prof existe
	    			val.add(nom_prof.getText());
	    			val.add(prenom_prof.getText());
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
	    			
	    			but_supp.setEnabled(true);
	    			}
	    			else {
	    				
	    				info_prof.setBackground(Color.RED);
		    
		    			but_supp.setEnabled(false);
		    			new PopUp("Le Professeur saisi n'existe pas");
	    			}
	    			
	    			val.clear();
	    		}	    	 
	    	 if(source== but_supp)
	    	 {
	    		 
	    		 ///requete sql suppresion prof avec info prof
	    		 

	    		
	    		
	    			info_prof.setBackground(Color.WHITE);
	    			  try {
	    				  try {
							bdd=new Database();
						} catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	    		  } catch(SQLException e1)
	    			{
	    				System.out.println(e1.getMessage());
	    			}
	    				val2.add(nom_prof.getText());
		    			val2.add(prenom_prof.getText());
	    			
	    			try {
	    				
	    			
						bdd.supProf(val2);}
					 catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		 
	    		 
	    		 
	    		 
	    		 nom_prof.setText("nom");
	    		 prenom_prof.setText("prenom");
	    		 info_prof.setText("");
	    			info_prof.setBackground(Color.WHITE);
	    			but_supp.setEnabled(false);
	    			val2.clear();
	    			
	    	 }

	if(source ==but_info)
	{
		  try {
			new modifier_supprimer_prof_info();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	

	}
	

	    }
	  }
 
}
