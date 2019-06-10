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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class Fenetre extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("Ajout",JLabel.CENTER);
  
  private JButton but_eleve= new JButton("Ajout eleve");
  private JButton but_classe= new JButton("Ajout classe");
  private JButton but_prof= new JButton("Ajout prof");
  private JButton but_disci= new JButton("Ajout discipline");
  private JButton but_eval= new JButton("Ajout evaluation");
  

  public Fenetre(){
    this.setTitle("Ajout");
    this.setSize(600, 700);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);
    this.setResizable(false);
    JPanel top = new JPanel();
    top.setLayout(new GridLayout(6,1));
    
    Font police = new Font("Arial", Font.BOLD, 25);
    
    label.setFont(police);
    label.setForeground(Color.BLUE);
    /// boutton de top
    but_eleve.addActionListener(new BoutonListener());
    but_classe.addActionListener(new BoutonListener());
    but_prof.addActionListener(new BoutonListener());
    but_disci.addActionListener(new BoutonListener());
    but_eval.addActionListener(new BoutonListener());
     
    
    
    top.add(label);
    top.add(but_eleve);
    top.add(but_prof);
    top.add(but_classe);
    top.add(but_disci);
    top.add(but_eval);
  
   top.setBounds(0, 0, 600, 600);
 
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
			new  ajout_eleve();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	    	
	if(source ==but_classe)
	{
		
		  try {
			new ajout_classe();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	if(source ==but_prof)
	{
		 
		  try {
			new ajout_prof();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	if(source ==but_disci)
	{
		 
		try {
			new ajout_discipline();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	if(source ==but_eval)
	{
		  
		try {
			new ajout_evaluation();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	    }
	  }
 
}