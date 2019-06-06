package vue;

import java.sql.SQLException;
import java.util.ArrayList;

import projet_ecole.*;
public class test 
{ 


	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database bdd=new Database();
            ArrayList<String> val=new ArrayList();
            val.add("TD01");
            val.add("ING1");
            val.add("1");
           System.out.print(Reporting.medianeClasse(bdd, val));
            //Reporting.moyenneClasse(bdd, val).get(1);
            //new Fenetre2();
//	new affiche_eleve();
	//	new DynamicFormExample();
		//new Jfreetest("test", null, null, null, null, null, null, false);
		// new affiche_classe();
			}
	
 }