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
 
public class affiche_classe extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("affiche");
  
  private JTextField classe= new JTextField("rentrer une classe");
  private JButton but_classe= new JButton("rechercher classe");
  private JTextField res_classe= new JTextField("normalement sa affiche les infos de la classe");
  

  public affiche_classe(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    top.setLayout(new FlowLayout());
    
    
    /// boutton de top
    but_classe.addActionListener(new BoutonListener());

     
    
    
    top.add(label);
    top.add(classe);
    top.add(but_classe);
    top.add(res_classe);
 
  
   top.setBounds(20, 78, 250, 200);
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	if(source ==but_classe)
	{
		  System.out.println("TEXT : Executer affiche classe " );
		  /// faire requete sql qui retourne un string a la place de bite
	res_classe.setText("bite de classe");
	}
	    	
	

	    }
	  }
 
}