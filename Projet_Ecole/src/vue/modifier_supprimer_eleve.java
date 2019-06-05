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
 
public class modifier_supprimer_eleve extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("modifier supprimer un eleve ");
  
  private JTextField eleve =new JTextField("rentrer eleve");
 private JTextField info_eleve =new JTextField("eleve existe");
  private JButton but_eleve= new JButton(" eleve");
  
  

  
  
  private JLabel modif = new JLabel("info a modifier");
  private JLabel supp = new JLabel("supprimer eleve");
  private JButton but_bulletin= new JButton("bulletin");
  private JButton but_evaluation= new JButton("evaluation");
  private JButton but_info = new JButton("modifier info");
  
  private JButton but_supp= new JButton("supprimer");

  public modifier_supprimer_eleve(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    JPanel top2 = new JPanel();
    top.setLayout(new FlowLayout());
    top2.setLayout(new FlowLayout());
    
    
    /// boutton de top

    but_bulletin.addActionListener(new BoutonListener());
    but_evaluation.addActionListener(new BoutonListener());
    but_info.addActionListener(new BoutonListener());


    
     //// boutton de top2
    but_eleve.addActionListener(new BoutonListener());
    but_supp.addActionListener(new BoutonListener());
	but_supp.setEnabled(false);
    info_eleve.setEnabled(false);
	
    top.add(label);
    top.add(modif);
 
    
    top.add(but_bulletin);
    top.add(but_evaluation);
    top.add(but_info);
    
    
  top2.add(supp);
  top2.add(eleve);
  top2.add(but_eleve);
  top2.add(info_eleve);
  top2.add(but_supp);
    
  
   top.setBounds(20, 78, 160, 200);
   top2.setBounds(300, 78, 90, 200);
   
    container.add(top);  
    container.add(top2);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_eleve)
	    		{
	    			/// verification eleve existe
	    	
	    			info_eleve.setBackground(Color.GREEN);
	    			
	    			
	    			/// si eleve existe
	    			info_eleve.setText(eleve.getText());
	    			but_supp.setEnabled(true);
	    			
	    		}	    	 
	    	 if(source== but_supp)
	    	 {
	    		 info_eleve.getText();
	    		 ///requete sql suppresion eleve avec info eleve
	    		 eleve.setText("");
	    		 info_eleve.setText("");
	    			info_eleve.setBackground(Color.WHITE);
	    			but_supp.setEnabled(false);
	    	 }
	    	 
	if(source ==but_bulletin)
	{
		  System.out.println("TEXT : Executer bulletin  " );
		new modifier_supprimer_eleve_bulletin();
	
	}
	if(source ==but_evaluation)
	{
		  System.out.println("TEXT : Executer cheval  " );
	
	try {
		new modifier_supprimer_eleve_evaluation();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
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
