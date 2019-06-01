

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
public class ajout_evaluation extends JFrame{
	Database bdd;
	
	
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("ajout             ");

	  private JTextField id_eleve= new JTextField("id_eleve");
	  private JTextField trimestre= new JTextField("trimestre");
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
		  
	    this.setTitle("fentreloliloio");
	    this.setSize(600, 600);
	 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    this.setLocationRelativeTo(null);
	    container.setBackground(Color.white);
	    container.setLayout(null);

	    JPanel top = new JPanel();
	    top.setLayout(new FlowLayout());
	    
	    
	    /// boutton de top
	    but_valider.addActionListener(new BoutonListener());
	  
	     
	    
	    
	    top.add(label);
	    top.add(id_eleve);
	    top.add(trimestre);
	    top.add(note);
	    top.add(app);
	    
	    top.add(but_valider);
	  
	  
	   top.setBounds(20, 78, 90, 200);
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
				 
			  val.add(id_eleve.getText());
			  val.add(trimestre.getText());
			  val.add(note.getText());
			  val.add(app.getText());
	
			  try {
				bdd.ajouterEval(val);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		    	
	

		    }
		  }
	 
}

