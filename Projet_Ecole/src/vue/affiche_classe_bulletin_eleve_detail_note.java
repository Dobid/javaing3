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

public class affiche_classe_bulletin_eleve_detail_note extends JFrame {
	private JPanel container = new JPanel();
	private JPanel temp = new JPanel();
	private ArrayList<String> liste_info = new ArrayList<>();
	Database bdd;
	private ArrayList<String> val = new ArrayList<>();
	private JLabel label = new JLabel("affiche");
	private JLabel id_v = new JLabel("affiche");
	
	private JButton but_bulletin = new JButton("Modifi Bulletin");
	
	
	private JTextField nom_note = new JTextField("nom");
	private JTextField new_note = new JTextField("nouvelle note");
	private JTextField new_app = new JTextField("nouvelle appreciation");
	private JButton valider = new JButton("valider info");
	
	private String[] tab_info = new String[12];
	private JButton affiche = new JButton("afficher info");
	

	// JButton supp= new JButton("supp");

	private List<JLabel> labels = new ArrayList<>();

	public affiche_classe_bulletin_eleve_detail_note(String id) throws SQLException{



		this.setTitle("Affiche Note Eleve");
		this.setSize(600, 600);
		//   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setBackground(Color.white);
		container.setLayout(new GridBagLayout());


id_v.setText(id);



		temp.setBackground(Color.white);
		temp.setLayout(new GridBagLayout());

valider.addActionListener(new BoutonListener());
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
			
		
		JPanel top= new JPanel();
		top.setBounds(10,50,200,200);
		top.setLayout(new GridLayout(4,1));
			
	top.add(nom_note);
	top.add(new_note);
	top.add(new_app);
	top.add(valider);
	
	container.add(top);
container.add(affiche);




		this.setContentPane(container);
		this.setVisible(true);  

	}

		class BoutonListener implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				Object source = e.getSource();

			if(source == valider)
			{
				try {
					bdd= new Database();
					System.out.println("ok bouton");
				val.add(nom_note.getText());
				val.add(new_note.getText());
				System.out.println(val);
				
				val.add(new_app.getText());
				
				if(bdd.modifierEval(val)==false)
					new PopUp("Cette evaluation n'existe pas");
					
				}
				catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
				
			
		    	 }
				
				
				
				
		    	 }
		
		

	private void addButtons(JPanel panel) throws ClassNotFoundException, SQLException {





			int number=3;

			if ( number>0 ) {


				GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,130,1,-124), 0, 0);
				JLabel labele = new JLabel("default");
				String def="defeult";

				try {
					bdd= new Database();
					liste_info.add(id_v.getText());
			
					liste_info=	bdd.afficherNotes(liste_info);
					
					for(int j=0;j<liste_info.size();j++)
					{
						String str = liste_info.get(j); 
						tab_info = str.split(",", 3); 



						for(int i=0; i<number; i++) {

							if(i==0) {
								labele = new JLabel("nom:"); // une étiquette pour le champ
								def=tab_info[1];

							}
							
						
							if(i==1) {
								labele = new JLabel("note"); // une étiquette pour le champ
								def=tab_info[0];
								
							}
							if(i==2) {
								labele = new JLabel("appreciation"); // une étiquette pour le champ
								def=tab_info[2];
								
							}
						
						
							
							gbc.gridx = 0;
							gbc.weightx = 0; 
							gbc.fill = GridBagConstraints.NONE;
					
								temp.add(labele, gbc);
							
						
							container.add(temp,gbc);



							JLabel label = new JLabel(def);  // le champ
							labels.add(label);
							gbc.gridx = 1;
							gbc.weightx = 1; 
							gbc.fill = GridBagConstraints.HORIZONTAL;
							temp.add(label, gbc);
							
							container.add(temp,gbc);

						}
						// on a modifié l'UI alors qu'elle était déjà affichée : on revalide (pour le layout) et on repeint (pour l'affichage)
						container.revalidate();
						container.repaint();


						//	panel.removeAll();


						//	supp.addActionListener(new BoutonListener());


					}
					
				}
				catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
			} 		




		}

			

	}
