package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

import projet_ecole.Database;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import projet_ecole.*;
 public class affiche_classe extends JFrame {
  private JPanel container = new JPanel();
  private JPanel temp= new JPanel();
 
	Database bdd;
  
  private JLabel label = new JLabel("Affiche");
  

  private JButton but_classe= new JButton("rechercher classe");
  private JTextField res_classe= new JTextField("ING");
  private JTextField res_td= new JTextField("TD");
  
  private JButton bull= new JButton("bulletin");
  
  private JButton affiche= new JButton("afficher info");
 private ArrayList<String> classe_rech = new ArrayList<>();
 private ArrayList<String> classe_list = new ArrayList<>();


private List<JLabel> labels = new ArrayList<>();
private List<JButton> bulls = new ArrayList<>();

private int nb_eleve=0;
  public affiche_classe() throws SQLException{
	
	  
	 
	  
    this.setTitle("Affichage Classe");
    this.setSize(800, 800);
 //   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    container.setBackground(Color.white);
	//int nb_elevee=  bdd.numEleve(classe_rech);
    container.setLayout(new GridBagLayout());


    JPanel top = new JPanel();
    JPanel top2 = new JPanel();
   
    
    top.setLayout(new GridLayout(4,1));
    top2.setLayout(new FlowLayout());
    
    affiche.setEnabled(false);
    Font police = new Font("Arial", Font.BOLD, 25);
    label.setFont(police);
    label.setForeground(Color.BLUE);
    
 
    
    temp.setBackground(Color.white);
    temp.setLayout(new GridBagLayout());
    
    /// boutton de top
    but_classe.addActionListener(new BoutonListener());
  
    
    
    affiche.addActionListener(e-> {
		try {
			addButtons(container);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	});
     
    
    
   top.add(label);
//    top.add(classe);
 top.add(res_td);

    top.add(res_classe);
    top.add(but_classe);
 
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
	    	 int nb_elever=1;
	    
	    	
	if(source ==but_classe)
	{ 
		
		  classe_rech.add(res_td.getText());
		  classe_rech.add(res_classe.getText());
		  try {
			  
			  try {
				bdd= new Database();
				bdd.numEleve(classe_rech);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			  
			if(!(bdd.isClasseExist(classe_rech)))
			{bulls.clear();
	classe_list=	bdd.afficherClasse(classe_rech);
	res_classe.setBackground(Color.green);
	affiche.setEnabled(true);
	temp.removeAll();
	

			}
			else {
				res_classe.setBackground(Color.red);
				classe_rech.clear();
				new PopUp("Cette classe n'existe pas");
				
			}

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


	}
	else {
		
		 for(int i=0;i<nb_eleve;i++)
    	 {
    	
    	 if(source== bulls.get(i))
	    	{
    		// System.out.println(bull.getText());
    		// System.out.println("Bulletin wsh3");
	    		if(bulls.get(i).getText().equals("Bulletin "+i) )
	    		{
	    			
	    			
	    			
	    			String str = classe_list.get(i); 
			        String[] id_tab= str.split(",", 3); 
			     
			        try {
			        
						new affiche_classe_bulletin_eleve(id_tab[0],id_tab[1]);
					
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    			
	    		}
	    	}
   
    	 
	    		
	    		
	    	
	    	
    	 }
		
		
		
		
	}
	
	    	
	
	
	
	    }
	  }
 
  
  private void addButtons(JPanel panel) throws ClassNotFoundException {

	 // bull.setBounds(200, 300, 100, 100);

	  int number=3;
	try {
		bdd= new Database();
		 nb_eleve=  bdd.numEleve(classe_rech);
		//System.out.println(nb_eleve);
		number=nb_eleve;
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	 
		if ( number>0 ) {
			
	    } 
		
			//System.out.println(classe_list.get(0));

			GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,130,1,-124), 0, 0);
			JLabel labele = new JLabel("default");
			String def="defeult";
			
			
			for(int i=0; i<number; i++) {
				
				String str = classe_list.get(i); 
		        String[] arrOfStr = str.split(",", 3); 
		  
		       
				
				
				for(int j=0; j<4; j++) {

	if(j==0) {
			 labele = new JLabel("nom:"); // une étiquette pour le champ
		def=arrOfStr[0];
		
			// def="nom du gars"+i;
			 
	}
				if(j==1) {
	 labele = new JLabel("prenom:"); // une étiquette pour le champ
	// def="prenom de son pere";
	 //def=classe_list.get(i);
	 def=arrOfStr[1];
	 
	 }

		if(j==2) {
	 labele = new JLabel("age:"); // une étiquette pour le champ
	 def=arrOfStr[2];
	// def=classe_list.get(i);
	 
		}
		if(j==3)
		{
			
			bull=new JButton("Bulletin "+i);
			bulls.add(bull);
			   bulls.get(i).addActionListener(new BoutonListener());
			
			 def="afficher bulletin";
			
		}

    
				gbc.gridx = 0;
				gbc.weightx = 0; 
				gbc.fill = GridBagConstraints.NONE;
				
				if(j != 3)
				{
				temp.add(labele, gbc);}
				//gbc.fill = GridBagConstraints.VERTICAL;
				//gbc.fill = GridBagConstraints.BELOW_BASELINE;
				if(j==3)
				{
				temp.add(bull,gbc);}
			//	gbc.fill = GridBagConstraints.NONE;
				panel.add(temp,gbc);

				
				
				JLabel label = new JLabel(def);  // le champ
			//	labels.add(label);
			
				//  labelb.add(bull);
				gbc.gridx = 1;
				gbc.weightx = 1; 
				gbc.fill = GridBagConstraints.HORIZONTAL;
			
				temp.add(label, gbc);
			//	temp.add(bull,gbc);
			//	if(j==2) {temp.add(bull,gbc);}
				panel.add(temp,gbc);

			}
			// on a modifié l'UI alors qu'elle était déjà affichée : on revalide (pour le layout) et on repeint (pour l'affichage)
			panel.revalidate();
			panel.repaint();
			affiche.setEnabled(false);
			res_classe.setText("ING");
			res_td.setText("TD");
			res_classe.setBackground(Color.WHITE);
	classe_rech.clear();

		//	panel.removeAll();
		
		
		//	supp.addActionListener(new BoutonListener());
			
			}
		}
	
	}
  
  


  
  
