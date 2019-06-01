package vue;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;

import projet_ecole.Database;





public class Fenetre2 extends JFrame {
private JPanel container = new JPanel();

/// se que je veux dans top
private JTextField jtf_ajout = new JTextField("Ajouter une donn�e");
private JButton but_ajout= new JButton("Ajout");

private JTextField jtf_modif = new JTextField("modifier/supprimer");
private JButton but_modif= new JButton("Modif");

private JTextField jtf_rechercher = new JTextField("Rechercher");
private JButton but_rechercher= new JButton("Rechercher");

private JTextField jtf_Stat = new JTextField("Afficher Stat");
private JButton but_Stat= new JButton("Stat");




private JLabel label = new JLabel("Action possible");

private JLabel toto= new JLabel(jtf_ajout.getText());
private JLabel label2 = new JLabel("Une ComboBox");

private JComboBox combo = new JComboBox();

public Fenetre2(){
	

	
	
  this.setTitle("Animation");
  this.setSize(900, 900);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  
  container.setBackground(Color.white);
  container.setLayout(null);
  JPanel top = new JPanel();
  top.setLayout(new FlowLayout());
  
  
  JPanel topa = new JPanel();
  JPanel topi = new JPanel();
 
  
  Font police = new Font("Arial", Font.BOLD, 14);
  jtf_ajout.setFont(police);
  jtf_ajout.setPreferredSize(new Dimension(150, 30));
  jtf_ajout.setForeground(Color.BLUE);
  
  
  String[] tab = {"Option 1", "5", "Option 3", "Option 4"};
  combo = new JComboBox(tab);
  
  combo.addActionListener(new ItemAction());
  
  combo.setPreferredSize(new Dimension(100, 20));
 combo.setForeground(Color.blue);
 
  
  /// boutton de top
 but_ajout.addActionListener(new BoutonListener());
 but_modif.addActionListener(new BoutonListener());
 but_rechercher.addActionListener(new BoutonListener());
 but_Stat.addActionListener(new BoutonListener());
  

 

  top.add(label);
 // top.add(jtf_ajout);
  top.add(but_ajout);
 // top.add(jtf_modif);
  top.add(but_modif);
//  top.add(jtf_rechercher);
  top.add(but_rechercher);
//  top.add(jtf_Stat);
  top.add(but_Stat);
  
  //top.add(toto);
  
  topi.add(jtf_modif);
  topi.add(jtf_rechercher);

  topa.add(label2);
  topa.add(combo);
  

  top.setBounds(200, 300,100,150);
//  topi.setBounds(40, 600,200,200);
//  topa.setBounds(40, 20,100,100);
  
  container.add(topi);
  container.add(top);
 container.add(topa);
 



  
  //container2.add(topa, BorderLayout.SOUTH);
//this.pack();
  this.setContentPane(container);

  this.setVisible(true);    

  
 
  
}


class BoutonListener implements ActionListener{
    public void actionPerformed(ActionEvent e) {
    	 Object source = e.getSource();
if(source ==but_ajout)
{
	  System.out.println("TEXT :  " + jtf_ajout.getText());
	  toto.setText(jtf_ajout.getText());
	  new  Fenetre();
}
    	
if(source ==but_modif)
{
	  System.out.println("TEXT : Executer fenetre modif  " );
	  new modifier_supprimer();
}
if(source ==but_rechercher)
{
	  System.out.println("TEXT : Executer fenetre rechercher  " );
	new rechercher();
}
if(source ==but_Stat)
{
	  System.out.println("TEXT : Executer fenetre Stat " );
	
}

    }
  }
class ItemAction implements ActionListener{
    public void actionPerformed(ActionEvent e) {
      System.out.println("ActionListener : action sur " + combo.getSelectedItem());

      
    }               
  }
}