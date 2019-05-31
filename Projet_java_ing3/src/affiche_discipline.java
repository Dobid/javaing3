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
 
public class affiche_discipline extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("affiche");
  
  private JTextField discipline= new JTextField("rentrer une discipline");
  private JButton but_discipline= new JButton("rechercher discipline");
  private JTextField res_discipline= new JTextField("normalement sa affiche les infos de la discipline");
  

  public affiche_discipline(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    top.setLayout(new FlowLayout());
    
    
    /// boutton de top
    but_discipline.addActionListener(new BoutonListener());

     
    
    
    top.add(label);
    top.add(discipline);
    top.add(but_discipline);
    top.add(res_discipline);
 
  
   top.setBounds(20, 78, 375, 200);
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	if(source ==but_discipline)
	{
		  System.out.println("TEXT : Executer affiche discipline " );
		  /// faire requete sql qui retourne un string a la place de bite
	res_discipline.setText("bite de discipline");
	}
	    	
	

	    }
	  }
 
}