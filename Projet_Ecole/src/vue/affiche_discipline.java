package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
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
 
public class affiche_discipline extends JFrame {
  private JPanel container = new JPanel();
  private JPanel temp= new JPanel();
 

  
  private JLabel label = new JLabel("affiche");
  
  private JTextField discipline= new JTextField("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
  private JButton but_discipline= new JButton("rechercher discipline");
  private JTextField res_discipline= new JTextField("rentrer discipline");
  
  private JButton affiche= new JButton("afficher info");
//	JButton supp= new JButton("supp");
  


private List<JLabel> labels = new ArrayList<>();

  public affiche_discipline(){
	  
	  
	  
	  
    this.setTitle("fentreloliloio");
    this.setSize(600, 600);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
 
    container.setLayout(new GridBagLayout());


    JPanel top = new JPanel();
    JPanel top2 = new JPanel();
   
    
    top.setLayout(new BorderLayout());
    top2.setLayout(new FlowLayout());
    
    affiche.setEnabled(false);
    
 //   GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2,4,2,4), 0, 0);
    
    temp.setBackground(Color.white);
    temp.setLayout(new GridBagLayout());
    
    /// boutton de top
    but_discipline.addActionListener(new BoutonListener());
    affiche.addActionListener(e-> addButtons(container));
     
    
    
   top.add(label,BorderLayout.WEST);
//    top.add(discipline);
    top.add(but_discipline,BorderLayout.SOUTH);
    top.add(res_discipline);
 
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
	if(source ==but_discipline)
	{
		  System.out.println("TEXT : Executer affiche discipline  " );
		  /// faire requete sql qui retourne un string a la place de bite
		  // si true 
	res_discipline.setText("bite");
	res_discipline.setBackground(Color.green);
	affiche.setEnabled(true);
	temp.removeAll();
	//else set red

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
			 def="nom du garssssssssss";
}
if(randi==1) {
	 labele = new JLabel("prenom:"); // une �tiquette pour le champ
	 def="prenom de son pereeeeeeeeeeee";
}
if(randi==2) {
	 labele = new JLabel("age:"); // une �tiquette pour le champ
	 def="age de sa grand mereeeeeeeeeeee";
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
			res_discipline.setText("rentrer discipline");
			res_discipline.setBackground(Color.WHITE);
	
		//	panel.removeAll();
		
		
		//	supp.addActionListener(new BoutonListener());
			

		}
	
	}
  
  
  
  
  
  
  
}