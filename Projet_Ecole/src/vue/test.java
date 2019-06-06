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
            val.add("1");
            val.add("NS1");
            val.add("Electronique");
            val.add("10");
            val.add("Un peu juste");
           bdd.ajouterEval(val);
            //Reporting.moyenneClasse(bdd, val).get(1);
            //new Fenetre2();
//	new affiche_eleve();
	//	new DynamicFormExample();
		//new Jfreetest("test", null, null, null, null, null, null, false);
		// new affiche_classe();
			}
	
 }