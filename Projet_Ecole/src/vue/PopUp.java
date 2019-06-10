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


public class PopUp extends JFrame {
	private JPanel erreur=new JPanel();
	private JLabel precision;
	
	public PopUp(String message)
	{
		this.setTitle("ERREUR DE SAISIE");
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		erreur.setLayout(new GridLayout(1,1));
		precision=new JLabel(message);
		   Font police = new Font("Arial", Font.BOLD, 40);
		   precision.setFont(police);
		erreur.add(precision);
		this.setContentPane(erreur);
		this.setVisible(true);
	}
}
