package vue;

import java.sql.SQLException;
import java.util.ArrayList;

import projet_ecole.*;
public class test 
{ 


	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database bdd=new Database();
            ArrayList<String> val=new ArrayList();
            ArrayList<String> val2=new ArrayList();
            
            
            val.add("David");
            val.add("Plop");
           System.out.println(bdd.afficherNote(val));
            //Reporting.moyenneClasse(bdd, val).get(1);
            //new Fenetre2();
//	new affiche_eleve();
	//	new DynamicFormExample();
		//new Jfreetest("test", null, null, null, null, null, null, false);
		// new affiche_classe();
			}
	
 }