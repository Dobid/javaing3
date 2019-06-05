package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
public class affiche_eleve extends JFrame {
  private JPanel container = new JPanel();
  private JPanel temp= new JPanel();
 

  
  private JLabel label = new JLabel("affiche");
  
  private JTextField eleve= new JTextField("rentrer un eleve");
  private JButton but_eleve= new JButton("rechercher eleve");
  private JTextField res_eleve= new JTextField("normalement sa affiche les infos de l'eleve");
  
  private JButton affiche= new JButton("ALLERN EN VRAI C BO");
//	JButton supp= new JButton("supp");
  


private List<JLabel> labels = new ArrayList<>();

  public affiche_eleve(){
	  
	  
	  
	  
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
 
    container.setLayout(new GridBagLayout());


    JPanel top = new JPanel();
    JPanel top2 = new JPanel();
   
    
    top.setLayout(new GridLayout(3,1));
    top2.setLayout(new GridLayout(1,1));
    
    affiche.setEnabled(false);
    
 //   GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2,4,2,4), 0, 0);
    
    temp.setBackground(Color.white);
    temp.setLayout(new GridBagLayout());
    
    /// boutton de top
    but_eleve.addActionListener(new BoutonListener());
    affiche.addActionListener(e-> addButtons(container));
     
    
    
   // top.add(label);
    top.add(eleve);
    top.add(but_eleve,BorderLayout.SOUTH);
    top.add(res_eleve);
 
    top2.add(affiche);

   top.setBounds(2, 15, 250, 200);
   top2.setBounds(550, 15, 250, 400);

   
    container.add(top);
    container.add(top2);
    

    
    
    
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
	affiche.setEnabled(true);
	temp.removeAll();

	}
	
	    	
	
	    }
	  }
 
  
  private void addButtons(JPanel panel) {
	  
	  int number=3;
	  Random rand = new Random();
	int randi=  rand.nextInt(2-0+1)+0;
	  
		if ( number>0 ) {
			int count = panel.getComponentCount()/2; // on compte le nombre de composants d�j� ajout�s

			GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,130,1,-124), 0, 0);
			JLabel labele = new JLabel("default");
			String def="defeult";
			for(int i=0; i<number; i++) {
				
if(randi==0) {
			 labele = new JLabel("nom:"); // une �tiquette pour le champ
			 def="nom du gars";
}
if(randi==1) {
	 labele = new JLabel("prenom:"); // une �tiquette pour le champ
	 def="prenom de son pere";
}
if(randi==2) {
	 labele = new JLabel("age:"); // une �tiquette pour le champ
	 def="age de sa grand mere";
}
	

    
				gbc.gridx = 0;
				gbc.weightx = 0; 
				gbc.fill = GridBagConstraints.NONE;
				temp.add(labele, gbc);
				panel.add(temp,gbc);

				
				
				JLabel label = new JLabel(def);  // le champ
				labels.add(label);
				gbc.gridx = 1;
				gbc.weightx = 1; 
				gbc.fill = GridBagConstraints.HORIZONTAL;
				temp.add(label, gbc);
				panel.add(temp,gbc);

			}
			// on a modifi� l'UI alors qu'elle �tait d�j� affich�e : on revalide (pour le layout) et on repeint (pour l'affichage)
			panel.revalidate();
			panel.repaint();
			affiche.setEnabled(false);
	
		//	panel.removeAll();
		
		
		//	supp.addActionListener(new BoutonListener());
			

		}
	
	}
  
  
  
  
  
  
  
}