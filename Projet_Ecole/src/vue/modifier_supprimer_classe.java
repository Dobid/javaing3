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
 
public class modifier_supprimer_classe extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("modifier supprimer une classe ");
  
  private JTextField classe =new JTextField("rentrer classe");
 private JTextField info_classe =new JTextField("eleve existe");
  private JButton but_classe= new JButton(" classe");
  
  

  
  
  private JLabel modif = new JLabel("info a modifier");
  private JLabel supp = new JLabel("supprimer classe");

  private JButton but_supp= new JButton("supprimer");

  public modifier_supprimer_classe(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);


    JPanel top = new JPanel();

    top.setLayout(new FlowLayout());
    
    



    
     //// boutton de top
    but_classe.addActionListener(new BoutonListener());
    but_supp.addActionListener(new BoutonListener());
	but_supp.setEnabled(false);
    info_classe.setEnabled(false);
	

    
    
  top.add(supp);
  top.add(classe);
  top.add(but_classe);
  top.add(info_classe);
  top.add(but_supp);
    
  
 
   top.setBounds(300, 78, 110, 200);
   
    container.add(top);
    
    
    
    
    
    this.setContentPane(container);
    this.setVisible(true);  
    
 

    
  }
  class BoutonListener implements ActionListener{
	    public void actionPerformed(ActionEvent e) {
	    	 Object source = e.getSource();
	 
	    		if(source ==but_classe)
	    		{
	    			/// verification eleve existe
	    	
	    			info_classe.setBackground(Color.GREEN);
	    			
	    			
	    			/// si eleve existe
	    			info_classe.setText(classe.getText());
	    			but_supp.setEnabled(true);
	    			
	    		}	    	 
	    	 if(source== but_supp)
	    	 {
	    		 info_classe.getText();
	    		 ///requete sql suppresion eleve avec info eleve
	    		classe.setText("");
	    		 info_classe.setText("");
	    			info_classe.setBackground(Color.WHITE);
	    			but_supp.setEnabled(false);
	    	 }
	
	
	

	    }
	  }
 
}
