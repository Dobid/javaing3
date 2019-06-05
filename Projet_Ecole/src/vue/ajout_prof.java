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
public class ajout_prof extends JFrame{

	
	Database bdd;
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("ajout",JLabel.CENTER);
	  private JTextField nom= new JTextField("nom");
	  private JTextField prenom= new JTextField("prenom");
	  private JTextField age= new JTextField("age");
	  private JTextField enseigne= new JTextField("enseignement");
	  private JTextField classe= new JTextField("classe");
	  private JTextField niveau= new JTextField("niveau");
	  
	  
	  private JButton but_valider= new JButton("valider prof");
	
	  

	  public ajout_prof() throws ClassNotFoundException{
		  
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
	    top.setLayout(new GridLayout(8,1));
	    
	    Font police = new Font("Arial", Font.BOLD, 25);
	    label.setFont(police);
	    label.setForeground(Color.BLUE);
	    nom.setFont(police);
	    prenom.setFont(police);
	    age.setFont(police);
	    enseigne.setFont(police);
	    classe.setFont(police);
	    niveau.setFont(police);
	    
	    
	    
	    /// boutton de top
	    but_valider.addActionListener(new BoutonListener());
	  
	     
	    
	    
	    top.add(label);
	    top.add(nom);
	    top.add(prenom);
	    top.add(age);
	    top.add(enseigne);
	    top.add(classe);
	    top.add(niveau);
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
			  val.add(enseigne.getText());
			  val.add(classe.getText());
			  val.add(niveau.getText());
			  try {
				bdd.ajoutProf(val);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//  container.removeAll();
		}
		    	
	

		    }
		  }
	 
}
