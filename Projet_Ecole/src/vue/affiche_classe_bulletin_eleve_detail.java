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

public class affiche_classe_bulletin_eleve_detail  extends JFrame {
	private JPanel container = new JPanel();
	private JPanel temp = new JPanel();
	private ArrayList<String> liste_info = new ArrayList<>();
	Database bdd;
	private ArrayList<String> val = new ArrayList<>();
	private JLabel label = new JLabel("affiche");
	private JLabel id_v = new JLabel("affiche");
	
	private JButton but_bulletin = new JButton("Modifi Bulletin");
	private JButton but_note= new JButton("Modifi  Detaillle Bulletin");
	
	private JTextField modif_app = new JTextField("default");
	private String[] tab_info = new String[12];
	private JButton affiche = new JButton("afficher info");
	private List<JTextField> tulls = new ArrayList<>();
	private List<JButton> bulls = new ArrayList<>();
	private List<JButton> bull_det = new ArrayList<>();
	private List<JButton> bull_note = new ArrayList<>();

	// JButton supp= new JButton("supp");

	private List<JLabel> labels = new ArrayList<>();

	public affiche_classe_bulletin_eleve_detail (String id) throws SQLException{



		this.setTitle("Affiche Detail Bulletin");
		this.setSize(750, 600);
		//   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		container.setBackground(Color.white);

		container.setLayout(new GridBagLayout());


id_v.setText(id);



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

				for(int i=0; i<bulls.size();i++)
				{
				if(source== bulls.get(i))
				{
					try {
						bdd= new Database();
						
						val.add(id_v.getText());
					val.add(tulls.get(i).getText());
						
						bdd.modifierDetailBulletin(val);
						
					}
					catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				if( source == bull_note.get(i) )
				{
					try {
						new affiche_classe_bulletin_eleve_detail_note(id_v.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				

				
				}
				
			
		    	 }
				
				
				
				
		    	 }
		
		


	private void addButtons(JPanel panel) throws ClassNotFoundException, SQLException {





			int number=5;

			if ( number>0 ) {


				GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(1,130,1,-124), 0, 0);
				JLabel labele = new JLabel("default");
				String def="defeult";

				try {
					bdd= new Database();
					liste_info.add(id_v.getText());
			
					liste_info=	bdd.afficheDetailBulletin(liste_info);
					
                                        System.out.println(liste_info);
                                        
					for(int j=0;j<liste_info.size();j++)
					{
						String str = liste_info.get(j); 
						tab_info = str.split(",", 4); 



						for(int i=0; i<number; i++) {

							if(i==0) {
								labele = new JLabel("discipline:"); // une étiquette pour le champ
								def=tab_info[1];

							}
							
						
							if(i==1) {
								labele = new JLabel("appreciation"); // une étiquette pour le champ
								modif_app= new JTextField(tab_info[2]);
								tulls.add(modif_app);
								 tulls.get(j).addActionListener(new BoutonListener());
							}
							if(i==3)
							{
								but_bulletin=new JButton("modifier  appreciation"+(j+1));
								bulls.add(but_bulletin);
								bulls.get(j).addActionListener(new BoutonListener());
								def="modifier  ";
							}
							if(i==2) {
								labele = new JLabel("note:"); // une étiquette pour le champ
								def=tab_info[3];

							}
							
							
							if(i==4)
							{
								but_note=new JButton("afficher note ");
								bull_note.add(but_note);
								bull_note.get(j).addActionListener(new BoutonListener());
								def="note  ";
							}
							
							gbc.gridx = 0;
							gbc.weightx = 0; 
							gbc.fill = GridBagConstraints.NONE;
							if(i!=3 && i!=4) {
								temp.add(labele, gbc);
							}
							if(i==3) {
								temp.add(but_bulletin,gbc);
							}
							if(i==4) {
								temp.add(but_note,gbc);
							}
							container.add(temp,gbc);



							JLabel label = new JLabel(def);  // le champ
							labels.add(label);
							gbc.gridx = 1;
							gbc.weightx = 1; 
							gbc.fill = GridBagConstraints.HORIZONTAL;
							if(i!=1) { temp.add(label, gbc);}
							if(i==1) { 
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
				catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			
			} 		




		}

			

	}
