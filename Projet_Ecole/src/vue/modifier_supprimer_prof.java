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
 
public class modifier_supprimer_prof extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("modifier supprimer un prof ");
  
  private JTextField prof =new JTextField("rentrer prof");
 private JTextField info_prof =new JTextField("prof existe");
  private JButton but_prof= new JButton(" prof");
  
  

  
  
  private JLabel modif = new JLabel("info a modifier");
  private JLabel supp = new JLabel("supprimer prof");
  private JButton but_classe= new JButton("classe");
  private JButton but_discipline= new JButton("discipline");
  private JButton but_supp= new JButton("supprimer");

  public modifier_supprimer_prof(){
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

    but_classe.addActionListener(new BoutonListener());

    but_discipline.addActionListener(new BoutonListener());


    
     //// boutton de top2
    but_prof.addActionListener(new BoutonListener());
    but_supp.addActionListener(new BoutonListener());
	but_supp.setEnabled(false);
    info_prof.setEnabled(false);
	
    top.add(label);
    top.add(modif);
 
    
    top.add(but_classe);
    top.add(but_discipline);
    
    
  top2.add(supp);
  top2.add(prof);
  top2.add(but_prof);
  top2.add(info_prof);
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
	 
	    		if(source ==but_prof)
	    		{
	    			/// verification prof existe
	    	
	    			info_prof.setBackground(Color.GREEN);
	    			
	    			
	    			/// si prof existe
	    			info_prof.setText(prof.getText());
	    			but_supp.setEnabled(true);
	    			
	    		}	    	 
	    	 if(source== but_supp)
	    	 {
	    		 info_prof.getText();
	    		 ///requete sql suppresion prof avec info prof
	    		 prof.setText("");
	    		 info_prof.setText("");
	    			info_prof.setBackground(Color.WHITE);
	    			but_supp.setEnabled(false);
	    	 }
	    	 
	if(source ==but_classe)
	{
		  System.out.println("TEXT : Executer classe  " );
	
	
	}
	if(source ==but_discipline)
	{
		  System.out.println("TEXT : Executer discilienne  " );
	

	}
	
	

	    }
	  }
 
}
