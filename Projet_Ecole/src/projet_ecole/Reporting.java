/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;
import java.util.*;
import java.sql.SQLException;

/**
 *
 * @author sebas
 */
public class Reporting {
    
    public static void consulterBulletin(Connexion bdd, int id_eleve) throws SQLException
    {
        ArrayList<String> result=new ArrayList<String>();
        int inscription;
        result=bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_eleve="+id_eleve);
        System.out.println(result.get(0));
        inscription=Integer.parseInt(result.get(0));
        result=bdd.remplirChampsRequete("SELECT appreciation FROM bulletin WHERE id_inscription="+inscription);
        for(int i=0; i<result.size(); i++)
            System.out.println(result.get(i));
        
    }
    
    
    
    
}
