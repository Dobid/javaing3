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
 
public class modifier_supprimer_eleve extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Modifier un eleve ");
  
  private JTextField eleve_nom =new JTextField("nom");
  private JTextField eleve_prenom =new JTextField("prenom");
 private JTextField info_eleve =new JTextField("  ");
  private JButton but_eleve= new JButton(" valider");
 private  ArrayList<String> val=new ArrayList<String>();
 private  ArrayList<String> val2=new ArrayList<String>();
 
	Database bdd;

  
  

  private JLabel supp = new JLabel("Supprimer eleve",JLabel.CENTER);

  private JButton but_info = new JButton("modifier info");
  
  private JButton but_supp= new JButton("supprimer");

  public modifier_supprimer_eleve() throws ClassNotFoundException{
	  
	
	  
    this.setTitle("Modifier / Supprimer eleve");
    this.setSize(1100, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    JPanel top2 = new JPanel();
    top.setLayout(new GridLayout(2,1));
    top2.setLayout(new GridLayout(6,1));
    
    
    /// boutton de top


    but_info.addActionListener(new BoutonListener());


    
     //// boutton de top2
    but_eleve.addActionListener(new BoutonListener());
    but_supp.addActionListener(new BoutonListener());
	but_supp.setEnabled(false);
    info_eleve.setEnabled(false);
	
    Font police = new Font("Arial", Font.BOLD, 25);
    label.setFont(police);
    label.setForeground(Color.BLUE);
  supp.setFont(police);
  supp.setForeground(Color.BLUE);
    
    eleve_nom.setFont(police);
    eleve_prenom.setFont(police);
    
    top.add(label);
   
 
    
   
    top.add(but_info);
    
    
  top2.add(supp);
  top2.add(eleve_nom);
  top2.add(eleve_prenom);
  top2.add(but_eleve);
  top2.add(info_eleve);
  top2.add(but_supp);
    
  
   top.setBounds(0, 0, 400, 300);
   top2.setBounds(450, 0, 500, 500);
   
    container.add(top);  
    container.add(top2);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_eleve)
	    		{boolean verif=true;
	    			/// verification eleve existe
	    			val.add(eleve_nom.getText());
	    			val.add(eleve_prenom.getText());
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
	    			info_eleve.setText(eleve_nom.getText());
	    			but_supp.setEnabled(true);
	    			}
	    			else {
	    				
	    				info_eleve.setBackground(Color.RED);
	    				new PopUp("L'eleve saisi n'existe pas");
		    			but_supp.setEnabled(false);
	    			}
	    			
	    			val.clear();
	    		}	    	 
	    	 if(source== but_supp)
	    	 {  
	    	 
	    		 info_eleve.getText();
	    		 ///requete sql suppresion eleve avec info eleve
	    		
	    			info_eleve.setBackground(Color.WHITE);
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
	    				val2.add(eleve_nom.getText());
		    			val2.add(eleve_prenom.getText());
	    			System.out.println(val);
	    			try {
						bdd.supEleve(val2);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			 eleve_nom.setText("");
		    		 eleve_prenom.setText("");
		    		 info_eleve.setText("");
	    			
	    			
	    			
	    			
	    			val2.clear();
	    			
	    			but_supp.setEnabled(false);
	    		
	    	 }

	
	if(source ==but_info)
	{
		  System.out.println("TEXT : Executer bite  " );
	try {
		new modifier_supprimer_eleve_info();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	
	}
	
	

	    }
	  }
 
}
