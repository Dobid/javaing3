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



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ajout_classe extends JFrame{

	
	
	
	 private JPanel container = new JPanel();
	  private JLabel label = new JLabel("ajout       ");
	  private JTextField nom= new JTextField("nom");
	  private JTextField annee= new JTextField("annee");
	  private JTextField id= new JTextField("id");
	  private JTextField ecole= new JTextField("ecole");
	  
	  private JButton but_valider= new JButton("valider classe");
	
	  

	  public ajout_classe(){
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
	    top.add(annee);
	    top.add(id);
	    top.add(ecole);
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
			  System.out.println("TEXT : Executer valider classe  " );
			  //envoyer requete sql
		}
		    	
	

		    }
		  }
	 
}

