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



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class modifier_supprimer extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Modifier / Supprimer",JLabel.CENTER);
  
  private JButton but_eleve= new JButton(" eleve");

  private JButton but_prof= new JButton(" prof");

  

  public modifier_supprimer(){
    this.setTitle("Modification");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    top.setLayout(new GridLayout(3,1));
    
    
    /// boutton de top
    but_eleve.addActionListener(new BoutonListener());

    but_prof.addActionListener(new BoutonListener());

     
    Font police = new Font("Arial", Font.BOLD, 25);
    label.setFont(police);
    label.setForeground(Color.BLUE);

    
    top.add(label);
    top.add(but_eleve);
    top.add(but_prof);
   
  
   top.setBounds(0, 0, 500, 500);
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	if(source ==but_eleve)
	{
	
	try {
		new modifier_supprimer_eleve();
	} catch (ClassNotFoundException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	}
	    	
	
	if(source ==but_prof)
	{
		
		new modifier_supprimer_prof();
		
	}
	

	    }
	  }
 
}