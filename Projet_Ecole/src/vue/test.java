package vue;
import java.sql.SQLException;
import java.util.ArrayList;

import projet_ecole.*;
public class test { 


	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database bdd=new Database();
            ArrayList <String> val=new ArrayList<String>();
            val.add("NS2");
            val.add("16");
            val.add("Ne devrait rien afficher");
            bdd.modifierEval(val);
		//new Fenetre2();
			
			
	}
	 
 }