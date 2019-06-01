
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
 
public class DynamicFormExample extends JPanel {
 
	/**
         * 
         */
	private static final long serialVersionUID = 1L;
 
	private List<JTextField> textFields = new ArrayList<>(); // une liste pour retrouver ses champs
 
	public DynamicFormExample() {
		super(new BorderLayout());
 
		// cr�ation des composants statiques
		JPanel buttonPanel = new JPanel(new BorderLayout()); // un panel pour le champ de saisie du nombre et le bouton +
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(5,0,10000,10)); // un champ pour saisir le nombre de champs � ajouter
		JButton buttonAdd = new JButton("+");
		buttonPanel.add(spinner, BorderLayout.CENTER);
		buttonPanel.add(buttonAdd, BorderLayout.EAST);
		add(buttonPanel, BorderLayout.NORTH);
		JPanel mainPanel = new JPanel(new GridBagLayout()); // un panel pour ajouter les champs dynamiquement
		JPanel containerPanel = new JPanel(new BorderLayout()); // on a besoin d'un panel interm�diaire pour que les champs soient plaqu�s en haut de la fen�tre (le + simple pour le faire)
		containerPanel.add(mainPanel, BorderLayout.NORTH);
		add(new JScrollPane(containerPanel), BorderLayout.CENTER); // on encapsule le panel des champs dans un scrollpane pour qu'on puisse acc�der aux champs qui sont dans la partie non visible de la fen�tre
 
		buttonAdd.addActionListener(e-> addButtons(mainPanel, (Integer)spinner.getValue()));
 
	}
 
	private void addButtons(JPanel panel, int number) {
		if ( number>0 ) {
			int count = panel.getComponentCount()/2; // on compte le nombre de composants d�j� ajout�s
 
			GridBagConstraints gbc = new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(2,4,2,4), 0, 0);
			for(int i=0; i<number; i++) {
 
				JLabel label = new JLabel(getLabelText(count+i)); // une �tiquette pour le champ
				gbc.gridx = 0;
				gbc.weightx = 0; 
				gbc.fill = GridBagConstraints.NONE;
				panel.add(label, gbc);
 
				JTextField textField = new JTextField();  // le champ
				textFields.add(textField);
				gbc.gridx = 1;
				gbc.weightx = 1; 
				gbc.fill = GridBagConstraints.HORIZONTAL;
				panel.add(textField, gbc);
 
			}
			// on a modifi� l'UI alors qu'elle �tait d�j� affich�e : on revalide (pour le layout) et on repeint (pour l'affichage)
			panel.revalidate();
			panel.repaint();
		}
	}
 
	/**
         * Cette m�thode permet de r�cup�rer toutes les valeurs saisies dans chaque champ (dans l'ordre)
         * @return
         */
	public List<String> getTexts() {
		return Collections.unmodifiableList(textFields.stream().map(f->f.getText()).collect(Collectors.toList()));
	}
 
	/**
         *  m�thode pour d�terminer le nom du champ d'index index
         * @param index
         * @return
         */
	protected String getLabelText(int index) {
		return "Champ " + index;
	}
 
	public static void main(String[] args) {
 
                // une fen�tre pour afficher l'UI
		JFrame frame = new JFrame("Exemple");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
                // on cr�� le composant et on l'ajoute � la fen�tre pour l'afficher
		DynamicFormExample formExample = new DynamicFormExample();
		frame.add(formExample);
 
		// pour d�mo, on affiche les valeurs saisies dans les champs � la fermeture de la fen�tre
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) { 
				int i=1;
				for(String text : formExample.getTexts()) {
					System.out.printf("Texte %d : %s%n",i++, text);
				}
			}
		});
 
                // on affiche la fen�tre
		frame.setSize(400, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
 
 
	}
 
}