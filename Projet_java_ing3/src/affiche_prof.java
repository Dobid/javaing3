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
 
public class affiche_prof extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("affiche");
  
  private JTextField prof= new JTextField("rentrer une prof");
  private JButton but_prof= new JButton("rechercher prof");
  private JTextField res_prof= new JTextField("normalement sa affiche les infos de la prof");
  

  public affiche_prof(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    top.setLayout(new FlowLayout());
    
    
    /// boutton de top
    but_prof.addActionListener(new BoutonListener());

     
    
    
    top.add(label);
    top.add(prof);
    top.add(but_prof);
    top.add(res_prof);
 
  
   top.setBounds(20, 78, 250, 200);
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	if(source ==but_prof)
	{
		  System.out.println("TEXT : Executer affiche prof " );
		  /// faire requete sql qui retourne un string a la place de bite
	res_prof.setText("bite de prof");
	}
	    	
	

	    }
	  }
 
}