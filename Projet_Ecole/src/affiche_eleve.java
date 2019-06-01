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
 
public class affiche_eleve extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("affiche");
  
  private JTextField eleve= new JTextField("rentrer un eleve");
  private JButton but_eleve= new JButton("rechercher eleve");
  private JTextField res_eleve= new JTextField("normalement sa affiche les infos de l'eleve");
  

  public affiche_eleve(){
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

     
    
    
    top.add(label);
    top.add(eleve);
    top.add(but_eleve);
    top.add(res_eleve);
 
  
   top.setBounds(20, 78, 250, 200);
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	if(source ==but_eleve)
	{
		  System.out.println("TEXT : Executer affiche eleve  " );
		  /// faire requete sql qui retourne un string a la place de bite
	res_eleve.setText("bite");
	}
	    	
	

	    }
	  }
 
}