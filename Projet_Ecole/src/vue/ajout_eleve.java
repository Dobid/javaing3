
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
public class ajout_eleve extends JFrame{

	
	Database bdd;
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Ajout",JLabel.CENTER);
	  private JTextField nom= new JTextField("nom");
	  private JTextField prenom= new JTextField("prenom");
	  private JTextField age= new JTextField("age");
	  private JTextField niveau= new JTextField("niveau");
	  private JTextField classe= new JTextField("classe");
	  
	  
	  private JButton but_valider= new JButton("valider eleve");
	
	  

	  public ajout_eleve() throws ClassNotFoundException{
		  
		  try {
			  bdd=new Database();
	  } catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
	    this.setTitle("Ajout Eleve");
	    this.setSize(600, 600);
	    this.setResizable(false);
	 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(null);

	    JPanel top = new JPanel();
	    top.setLayout(new GridLayout(7,1));
	    
	    
	    /// boutton de top
	    but_valider.addActionListener(new BoutonListener());
	  
	    Font police = new Font("Arial", Font.BOLD, 25);
	    label.setFont(police);
	    label.setForeground(Color.BLUE);
	    nom.setFont(police);
	    prenom.setFont(police);
	    age.setFont(police);
	    niveau.setFont(police);
	    classe.setFont(police);
	
	    
	    
	    top.add(label);
	    top.add(nom);
	    top.add(prenom);
	    top.add(age);
	    top.add(niveau);
	    top.add(classe);
	    top.add(but_valider);
	  
	  
	   top.setBounds(0, 0, 500, 500);
	    container.add(top);
	    
	    
	    
	    
	    
	    this.setContentPane(container);
	    this.setVisible(true);  
	    
	 

	    
	  }
	  class BoutonListener implements ActionListener{
		    public void actionPerformed(ActionEvent e) {
		    	 Object source = e.getSource();
		if(source ==but_valider)
		{
			
			  ArrayList<String> val=new ArrayList<String>();
			 
			  val.add(nom.getText());
			  val.add(prenom.getText());
			  val.add(age.getText());
			 
			
			  try {
				  val.add(classe.getText());
				  val.add(niveau.getText());
				  if(bdd.ajoutEleve(val)==false)
					  new PopUp("Impossible d'ajouter cet eleve");
				nom.setText("nom");
				prenom.setText("prenom");
				age.setText("age");
				classe.setText("classe");
				niveau.setText("niveau");
				val.clear();
				
		
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			  
		}
		    	
	

		    }
		  }
	 
}

