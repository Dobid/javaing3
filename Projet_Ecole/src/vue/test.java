package vue;
import java.sql.SQLException;
import java.util.ArrayList;

import projet_ecole.*;
public class test { 


	 public static void main(String[] args) throws ClassNotFoundException, SQLException {
            Database bdd=new Database();
            ArrayList <String> val=new ArrayList<String>();
            val.add("NS1");
            val.add("16");
            val.add("");
            bdd.modifierAppEval(val);
		//new Fenetre2();
			
			
			}
	 
 }