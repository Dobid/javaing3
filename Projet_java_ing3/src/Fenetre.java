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
 
public class Fenetre extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("ajout");
  
  private JButton but_eleve= new JButton("Ajout eleve");
  private JButton but_classe= new JButton("Ajout classe");
  private JButton but_prof= new JButton("Ajout prof");
  private JButton but_disci= new JButton("Ajout discipline");
  

  public Fenetre(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    top.setLayout(new FlowLayout());
    
    
    /// boutton de top
    but_eleve.addActionListener(new BoutonListener());
    but_classe.addActionListener(new BoutonListener());
    but_prof.addActionListener(new BoutonListener());
    but_disci.addActionListener(new BoutonListener());
     
    
    
    top.add(label);
    top.add(but_eleve);
    top.add(but_prof);
    top.add(but_classe);
    top.add(but_disci);
  
   top.setBounds(20, 78, 100, 200);
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	if(source ==but_eleve)
	{
		  System.out.println("TEXT : Executer fenetre eleve  " );
		  new  ajout_eleve();
	}
	    	
	if(source ==but_classe)
	{
		  System.out.println("TEXT : Executer fenetre classe " );
		  new ajout_classe();
	}
	if(source ==but_prof)
	{
		  System.out.println("TEXT : Executer fenetre prof  " );
		  new ajout_prof();
		
	}
	if(source ==but_disci)
	{
		  System.out.println("TEXT : Executer fenetre disci" );
		new ajout_discipline();
	}

	    }
	  }
 
}