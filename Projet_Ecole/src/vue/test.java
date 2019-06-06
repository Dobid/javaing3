package vue;

import java.sql.SQLException;
import java.util.ArrayList;

import projet_ecole.*;
public class test 
{ 


	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database bdd=new Database();
            ArrayList<String> val=new ArrayList();
            val.add("Informatique");
           System.out.print("Eleves ayant moins de 5, 10, 15 et plus de 15 dans la matiere "+val.get(0));
            System.out.println(Reporting.moyenneDiscipline(bdd, val));
            //Reporting.moyenneClasse(bdd, val).get(1);
            //new Fenetre2();
//	new affiche_eleve();
	//	new DynamicFormExample();
		//new Jfreetest("test", null, null, null, null, null, null, false);
		// new affiche_classe();
			}
	
 }