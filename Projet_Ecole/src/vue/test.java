package vue;

import java.sql.SQLException;
import java.util.*;

import projet_ecole.*;
public class test { 

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    
        Database bdd=new Database();
		
        //new Fenetre2();
        ArrayList<String> val = new ArrayList<>();
        
        val.add("Olivares");
        val.add("David");
        val.add("20");
        val.add("TD01");
        val.add("ING1");
        
        bdd.ajoutEleve(val);
        
    }
	
 }