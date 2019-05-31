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



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ajout_prof extends JFrame{

	
	
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("ajout       ");
	  private JTextField nom= new JTextField("nom");
	  private JTextField prenom= new JTextField("prenom");
	  private JTextField id= new JTextField("id");
	  private JTextField enseigne= new JTextField("enseignement");
	  
	  
	  private JButton but_valider= new JButton("valider prof");
	
	  

	  public ajout_prof(){
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
	    top.add(nom);
	    top.add(prenom);
	    top.add(id);
	    top.add(enseigne);
	    
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
			  System.out.println("TEXT : Executer valider prof " );
			  //envoyer requete sql
			//  container.removeAll();
		}
		    	
	

		    }
		  }
	 
}
