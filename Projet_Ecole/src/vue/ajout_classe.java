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
public class ajout_classe extends JFrame{

	
	Database bdd;
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Ajout",JLabel.CENTER);
	  private JTextField nom= new JTextField("nom");
	  
	  private JTextField id_niveau= new JTextField("niveau");

	  
	  private JButton but_valider= new JButton("valider classe");
	
	  

	  public ajout_classe() throws ClassNotFoundException{
		  
		  try {
			  bdd=new Database();
	  } catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		  
		  
	    this.setTitle("Ajout Classe");
	    this.setSize(600, 600);
	 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    container.setBackground(Color.white);
	    container.setLayout(null);

	    JPanel top = new JPanel();
	    top.setLayout(new GridLayout(5,1));
	    
	    Font police = new Font("Arial", Font.BOLD, 25);
	    label.setFont(police);
	    label.setForeground(Color.BLUE);
	    nom.setFont(police);
	    
	    id_niveau.setFont(police);
	   
	     
	    
	    /// boutton de top
	    but_valider.addActionListener(new BoutonListener());
	  
	     
	    
	    
	    top.add(label);
	    top.add(nom);
	 
	    top.add(id_niveau);
	
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
			  val.add(id_niveau.getText());
			
			
	
			  try {
				if(bdd.ajoutClasse(val)==false)
					new PopUp("Impossible d'ajouter cette classe");
				val.clear();
				nom.setText("nom");
				id_niveau.setText("niveau");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		    	
	

		    }
		  }
	 
}

