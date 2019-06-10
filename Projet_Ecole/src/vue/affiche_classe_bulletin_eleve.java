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


import projet_ecole.Database;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class affiche_classe_bulletin_eleve extends JFrame {
	private JPanel container = new JPanel();
	private JPanel temp = new JPanel();
	private ArrayList<String> liste_info = new ArrayList<>();
	Database bdd;
	private ArrayList<String> val = new ArrayList<>();
	private JLabel label = new JLabel("affiche");
	private JLabel nom_v = new JLabel("affiche");
	private JLabel prenom_v= new JLabel("affiche");
	private String id="";
	
	private JButton but_bulletin = new JButton("Modifi Bulletin");
	private JButton but_detail_bulletin = new JButton("Modifi  Detaillle Bulletin");
	
	private JTextField modif_app = new JTextField("default");
	private String[] tab_info = new String[12];
	private JButton affiche = new JButton("afficher info");
	private List<JTextField> tulls = new ArrayList<>();
	private List<JButton> bulls = new ArrayList<>();
	private List<JButton> bull_det = new ArrayList<>();

	// JButton supp= new JButton("supp");

	private List<JLabel> labels = new ArrayList<>();

	public affiche_classe_bulletin_eleve(String nom,String prenom) throws SQLException{



		this.setTitle("Affiche Bulletin Eleve");
		this.setSize(750, 600);
		//   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setBackground(Color.white);

		container.setLayout(new GridBagLayout());



nom_v.setText(nom);
prenom_v.setText(prenom);



		temp.setBackground(Color.white);
		temp.setLayout(new GridBagLayout());


		affiche.addActionListener(e-> {
			try {
				addButtons(container);
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
			
		
		
			
	
container.add(affiche);




		this.setContentPane(container);
		this.setVisible(true);  

	}

		class BoutonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();

				 for(int i=0;i<bulls.size();i++)
		    	 {
					 
				if(source== bulls.get(i))
				{	
				//	System.out.println(bulls.get(i).getText());
	    			
			
					
					if(bulls.get(i).getText().equals("modifier Bulletin 1"))
					{
						try {
							
								bdd= new Database();
								liste_info.clear();
								liste_info.add(nom_v.getText());
								liste_info.add(prenom_v.getText());
							
								
									liste_info=	bdd.afficheBulletin(liste_info);
							
						
								for(int j=0;j<liste_info.size();j++)
								{
									String str = liste_info.get(j); 
									tab_info = str.split(",", 4); 

								



								val.add(tab_info[0]);
								val.add(tulls.get(i).getText());

							

							
									bdd.modifierBulletin(val);
								}
							//System.out.println(nb_eleve);

						}  catch (ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							
							
					
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

					}
					if(bulls.get(i).getText().equals("modifier Bulletin 2"))
					{System.out.println("b2");
						try {
						
						bdd= new Database();
						liste_info.clear();
						liste_info.add(nom_v.getText());
						liste_info.add(prenom_v.getText());
					
						
							liste_info=	bdd.afficheBulletin(liste_info);
					
				
						for(int j=1;j<liste_info.size();j++)
						{
							String str = liste_info.get(j); 
							tab_info = str.split(",", 4); 

						



						val.add(tab_info[0]);
						val.add(tulls.get(i).getText());

					

					
							bdd.modifierBulletin(val);
						}
					//System.out.println(nb_eleve);

				}  catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
					
			
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


					
					if(bulls.get(i).getText().equals("modifier Bulletin 3"))
					{
						try {
							
							bdd= new Database();
							liste_info.clear();
							liste_info.add(nom_v.getText());
							liste_info.add(prenom_v.getText());
						
							
								liste_info=	bdd.afficheBulletin(liste_info);
						
					
							for(int j=1;j<liste_info.size();j++)
							{
								String str = liste_info.get(j); 
								tab_info = str.split(",", 4); 

							



							val.add(tab_info[0]);
							val.add(tulls.get(i).getText());

						

						
								bdd.modifierBulletin(val);
							}
						//System.out.println(nb_eleve);

					}  catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						
						
				
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}


					}
					
						
				
		    	 }
				
		    	 }
		    	 }
				 for(int p=0;p<bull_det.size();p++)
				 {
				 
				if(source== bull_det.get(p))
				{
					try {
						bdd= new Database();
						liste_info.clear();
						liste_info.add(nom_v.getText());
						liste_info.add(prenom_v.getText());
					
						
							liste_info=	bdd.afficheBulletin(liste_info);
							
						
								String str = liste_info.get(p); 
								tab_info = str.split(",", 4); 
								
								id=tab_info[0];
									
								
					
							
							
							new affiche_classe_bulletin_eleve_detail (id);
							
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				
			
			

				 
				 }
				
				 }}
		    	 
		}
		
		

	private void addButtons(JPanel panel) throws ClassNotFoundException, SQLException {





			int number=6;

			if ( number>0 ) {


				GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,130,1,-124), 0, 0);
				JLabel labele = new JLabel("default");
				String def="defeult";

				try {
					bdd= new Database();
					liste_info.add(nom_v.getText());
					liste_info.add(prenom_v.getText());
					liste_info=	bdd.afficheBulletin(liste_info);
					
				}
				catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				for(int j=0;j<liste_info.size();j++)
				{
					String str = liste_info.get(j); 
					tab_info = str.split(",", 4); 



					for(int i=0; i<number; i++) {

						if(i==0) {
							labele = new JLabel("nom:"); // une étiquette pour le champ
							def=nom_v.getText();

						}
						if(i==1) {
							labele = new JLabel("trimestre:"); // une étiquette pour le champ
							def=tab_info[1];
						}
						if(i==2) {
							labele = new JLabel("moyenne"); // une étiquette pour le champ
							def=tab_info[2];
						}
						if(i==3) {
							labele = new JLabel("appreciation"); // une étiquette pour le champ
							modif_app= new JTextField(tab_info[3]);
							tulls.add(modif_app);
							 tulls.get(j).addActionListener(new BoutonListener());
						}
						if(i==4)
						{
							but_bulletin=new JButton("modifier Bulletin "+(j+1));
							bulls.add(but_bulletin);
							bulls.get(j).addActionListener(new BoutonListener());
							def="modifier ";
						}
						if(i==5)
						{
							but_detail_bulletin= new JButton("modifier detail bulletin "+(j+1));
							bull_det.add(but_detail_bulletin);
							bull_det.get(j).addActionListener(new BoutonListener());
							def="modifier ";
						}

						gbc.gridx = 0;
						gbc.weightx = 0; 
						gbc.fill = GridBagConstraints.NONE;
						if(i!=4 && i!=5) {
							temp.add(labele, gbc);
						}
						if(i==4) {
							temp.add(but_bulletin,gbc);
						}
						if(i==5) {
							temp.add(but_detail_bulletin,gbc);
						}
						container.add(temp,gbc);



						JLabel label = new JLabel(def);  // le champ
						labels.add(label);
						gbc.gridx = 1;
						gbc.weightx = 1; 
						gbc.fill = GridBagConstraints.HORIZONTAL;
						if(i!=3) { temp.add(label, gbc);}
						if(i==3) { 
							gbc.fill = GridBagConstraints.HORIZONTAL;
							temp.add(modif_app,gbc );

						}
						container.add(temp,gbc);

					}
					// on a modifié l'UI alors qu'elle était déjà affichée : on revalide (pour le layout) et on repeint (pour l'affichage)
					container.revalidate();
					container.repaint();


					//	panel.removeAll();


					//	supp.addActionListener(new BoutonListener());


				}
			} 		




		}

			
		}
	
