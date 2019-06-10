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
public class ajout_evaluation extends JFrame{
	Database bdd;
	
	
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("Ajout",JLabel.CENTER);

	  private JTextField nom= new JTextField("nom");
	  private JTextField prenom= new JTextField("prenom");
	  private JTextField trimestre= new JTextField("trimestre");
	  private JTextField nom_evaluation= new JTextField("nom evaluation");
	  private JTextField discipline= new JTextField("discipline");
	  private JTextField note= new JTextField("note");
	
	  private JTextField app= new JTextField("appreciation");
	  
	  private JButton but_valider= new JButton("valider evaluation");
	
	  

	  public ajout_evaluation() throws ClassNotFoundException{
		  
		  
		  try {
			  bdd=new Database();
	  } catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		  
	    this.setTitle("Ajouter evaluation");
	    this.setSize(600, 600);
	 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    this.setResizable(false);
	    container.setBackground(Color.white);
	    container.setLayout(null);

	    JPanel top = new JPanel();
	    top.setLayout(new GridLayout(9,1));
	    
	    Font police = new Font("Arial", Font.BOLD, 25);
	    label.setFont(police);
	    label.setForeground(Color.BLUE);
	    nom.setFont(police);
	    trimestre.setFont(police);
	    note.setFont(police);
	    app.setFont(police);
	    nom_evaluation.setFont(police);
	    discipline.setFont(police);
	    prenom.setFont(police);
	    /// boutton de top
	    but_valider.addActionListener(new BoutonListener());
	  
	     
	    
	    
	    top.add(label);
	    top.add(nom);
	    top.add(prenom);
	    top.add(trimestre);
	    top.add(nom_evaluation);
	    top.add(discipline);
	    top.add(note);
	    top.add(app);
	    
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
			  val.add(trimestre.getText());
			  val.add(nom_evaluation.getText());
			  val.add(discipline.getText());
			  val.add(note.getText());
			  val.add(app.getText());
	
			  try {
				if(bdd.ajouterEval(val)==false)
					new PopUp("Cette note a deja ete ajoutee");
				val.clear();
				nom.setText("nom");
				prenom.setText("prenom");
				trimestre.setText("trimestre");
				nom_evaluation.setText("nom_evaluation");
				discipline.setText("discipline");
				note.setText("note");
				app.setText("appreciation");
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		    	
	

		    }
		  }
	 
}

