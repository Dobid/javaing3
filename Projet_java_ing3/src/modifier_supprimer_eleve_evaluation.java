


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
 
public class modifier_supprimer_eleve_evaluation extends JFrame {
  private JPanel container = new JPanel();
  private JLabel label = new JLabel("modifier supprimer un eleve ");
  
  private JTextField eleve =new JTextField("rentrer eleve");
 private JTextField info_eleve =new JTextField("eleve existe");
  private JButton but_eleve= new JButton(" recherche");
  
  private JLabel titre = new JLabel("modifier supprimer l'evaluation ");
  private JTextField info_eval =new JTextField("rentrer info ici");

  private JButton but_modif_eval= new JButton(" modifier");
  
  
  



  public modifier_supprimer_eleve_evaluation(){
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
    container.setLayout(null);

    JPanel top = new JPanel();
    
    top.setLayout(new FlowLayout());

    info_eleve.setEnabled(false);
    info_eval.setEnabled(false);
    
    /// boutton de top

    but_eleve.addActionListener(new BoutonListener());
    but_modif_eval.addActionListener(new BoutonListener());
    but_modif_eval.setEnabled(false);
	
    top.add(label);
    top.add(eleve);
    top.add(info_eleve);
    top.add(but_eleve);
    top.add(titre);
    top.add(info_eval);
    top.add(but_modif_eval);
    


  
   top.setBounds(20, 78, 200, 200);

   
    container.add(top);  
 
   
    
    
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
	    			info_eval.setEnabled(true);
	    			but_modif_eval.setEnabled(true);
	    			
	    		}	    	 
	    		if(source ==but_modif_eval)
	    		{
	    			info_eleve.getText();
	    			/// requete sql modif bulletin
	    			info_eval.setText("rentrer info");
	    		}	    	 
	    	
	

	    }
	  }

 
}
