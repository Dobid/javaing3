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
 //https://codes-sources.commentcamarche.net/source/51950-creer-des-graphiques-utilisation-de-jfreechart
//https://www.tutorialspoint.com/jfreechart/jfreechart_pie_chart.htm
public class affiche_classe extends JFrame {
  private JPanel container = new JPanel();
  private JPanel temp= new JPanel();
 
	Database database;
  
  private JLabel label = new JLabel("affiche");
  
  private JTextField classe= new JTextField("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
  private JButton but_classe= new JButton("rechercher classe");
  private JTextField res_classe= new JTextField("rentrer classe");
  private JTextField res_td= new JTextField("TD");
  
  private JButton affiche= new JButton("afficher info");

  


private List<JLabel> labels = new ArrayList<>();

  public affiche_classe(){
	  
	  
		 try {
			Database bdd=new Database();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  
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
    but_classe.addActionListener(new BoutonListener());
    affiche.addActionListener(e-> addButtons(container));
     
    
    
   top.add(label,BorderLayout.WEST);
//    top.add(classe);
 top.add(res_td,BorderLayout.NORTH);
    top.add(but_classe,BorderLayout.SOUTH);
    top.add(res_classe);
 
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
	if(source ==but_classe)
	{  ArrayList<String> classe_rech = new ArrayList<>();
		  System.out.println("TEXT : Executer affiche classe  " );
		  /// faire requete sql qui retourne un string a la place de bite
		  classe_rech.add(res_td.getText());
		  classe_rech.add(res_classe.getText());
		  try {
			if(!(database.isClasseExist(classe_rech)))
			{
		classe_rech=	database.afficherClasse(classe_rech);
			}
			  // si true 
res_classe.setText("bite");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	res_classe.setBackground(Color.green);
	affiche.setEnabled(true);
	temp.removeAll();
	//else set red

	}
	
	    	
	
	    }
	  }
 
  
  private void addButtons(JPanel panel) {
	  JButton bull= new JButton("bulletin");
	  bull.setBounds(200, 300, 100, 100);
	  res_classe.getText();
	  int number=3;
	int nb_eleve=Integer.parseInt(classe.getText());
	
	//int randi=  rand.nextInt(2-0+1)+0;
	 
		if ( number>0 ) {
			int count = panel.getComponentCount()/2; // on compte le nombre de composants déjà ajoutés

			GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,130,1,-124), 0, 0);
			JLabel labele = new JLabel("default");
			String def="defeult";
			for(int i=0; i<nb_eleve; i++) {
				

	if(i==0) {
			 labele = new JLabel("nom:"); // une étiquette pour le champ
			 
			 def="nom du gars"+i;
			 
	}
				if(i==1) {
	 labele = new JLabel("prenom:"); // une étiquette pour le champ
	 def="prenom de son pere";}

		if(i==2) {
	 labele = new JLabel("age:"); // une étiquette pour le champ
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
				if(i==2) {temp.add(bull,gbc);}
				panel.add(temp,gbc);

			}
			// on a modifié l'UI alors qu'elle était déjà affichée : on revalide (pour le layout) et on repeint (pour l'affichage)
			panel.revalidate();
			panel.repaint();
			affiche.setEnabled(false);
			res_classe.setText("rentrer classe");
			res_classe.setBackground(Color.WHITE);
	
		//	panel.removeAll();
		
		
		//	supp.addActionListener(new BoutonListener());
			

		}
	
	}
  
  

/*
  String[] col = columns.toArray(new String[0]);
          String[][] finalArr = new String[tabInfosPersonnes.size()][(tabInfosPersonnes.get(0).size())];
          
          for(int i = 0; i < tabInfosPersonnes.size(); i++)
          { 
                String[] array = data.get(i).toArray(new String[0]);
                finalArr[i] = Arrays.copyOf(array,array.length);         
          }
          /// SET TABLE
          DefaultTableModel model;
          model = new DefaultTableModel(finalArr,col);
          JTable table = new JTable(model);
          table.setRowHeight(40);
          table.setFont(new Font("Serif", Font.BOLD, 20));
          JScrollPane myPane = new JScrollPane(table);
          myPane.setPreferredSize(new Dimension(600, 800));
          for(int i = 0; i < tabBoutonsModif.size();i++)
          {
              tabBoutonsModif.get(i).setBounds((int) (tailleEcran.width/(2.4)),(int)tailleEcran.height/16+ +i*40, 120, 30);
              tabBoutonsSupprimer.get(i).setBounds((int) (tailleEcran.width/(1.67)),(int )tailleEcran.height/16+ +i*40, 100, 30);
          }
          inserer.setBounds((int) (tailleEcran.width/(15)),(int)tailleEcran.height/22 - 20, 90,30);
          
          p0.add(inserer);   
          p0.add(myPane);
          inserer.addActionListener(this);
          p0.setBackground(Color.BLUE);
          p1.add(p0);
  
  */
  
  
}