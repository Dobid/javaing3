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

		 
                 
                 val2.add("Coudray");
                 val2.add("Fabienne");
                 val2.add("Electronique");
                 val2.add("TD01");
                 val2.add("ING1");
                 
                 bdd.modifierProf(val2);
                 
	 }
}
