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
 
public class modifier_supprimer_discipline extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("modifier supprimer une discipline ");
  
  private JTextField discipline =new JTextField("rentrer discipline");
 private JTextField info_discipline =new JTextField("eleve existe");
  private JButton but_discipline= new JButton(" discipline");
  
  

  
  
  private JLabel modif = new JLabel("info a modifier");
  private JLabel supp = new JLabel("supprimer discipline");
  private JButton but_bulletin= new JButton("bulletin");
  private JButton but_evaluation= new JButton("evaluation");
  private JButton but_supp= new JButton("supprimer");

  public modifier_supprimer_discipline(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);


    JPanel top = new JPanel();

    top.setLayout(new FlowLayout());
    
    
    /// boutton de top

    but_bulletin.addActionListener(new BoutonListener());

    but_evaluation.addActionListener(new BoutonListener());


    
     //// boutton de top
    but_discipline.addActionListener(new BoutonListener());
    but_supp.addActionListener(new BoutonListener());
	but_supp.setEnabled(false);
    info_discipline.setEnabled(false);
	

    
    
  top.add(supp);
  top.add(discipline);
  top.add(but_discipline);
  top.add(info_discipline);
  top.add(but_supp);
    
  
 
   top.setBounds(300, 78, 110, 200);
   
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_discipline)
	    		{
	    			/// verification eleve existe
	    	
	    			info_discipline.setBackground(Color.GREEN);
	    			
	    			
	    			/// si eleve existe
	    			info_discipline.setText(discipline.getText());
	    			but_supp.setEnabled(true);
	    			
	    		}	    	 
	    	 if(source== but_supp)
	    	 {
	    		 info_discipline.getText();
	    		 ///requete sql suppresion eleve avec info eleve
	    		discipline.setText("");
	    		 info_discipline.setText("");
	    			info_discipline.setBackground(Color.WHITE);
	    			but_supp.setEnabled(false);
	    	 }
	    	 
	if(source ==but_bulletin)
	{
		  System.out.println("TEXT : Executer bulletin  " );
		new modifier_supprimer_eleve_bulletin();
	
	}
	if(source ==but_evaluation)
	{
		  System.out.println("TEXT : Executer bulletin  " );
	
	new modifier_supprimer_eleve_evaluation();
	}
	
	

	    }
	  }
 
}
