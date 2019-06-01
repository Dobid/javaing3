package vue;
import java.sql.SQLException;
import java.util.ArrayList;

import projet_ecole.*;
public class test { 


	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
		 Database bdd=new Database();
		 ArrayList<String> val = new ArrayList<String>();
		 ArrayList<String> val2 = new ArrayList<String>();
		
//	new modifier_supprimer_eleve();
		//new Fenetre2();
			
		//new modifier_supprimer_prof();
		 val.add("Depardieu");
		 val.add("Gérard");
		 val.add("2");
		 val.add("DS2");
		 val.add("14");
		 val.add("Ensemble correct");

		 bdd.ajouterEval(val);
	 }
}