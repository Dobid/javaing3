/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;
import java.util.*;
import java.sql.SQLException;
import java.lang.*;


/**
 *
 * @author sebas
 */
public class Update {
    
    public static void modifierEleve(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {
        int id=Integer.parseInt(valeurs.get(0), 10);
         int age= Integer.parseInt(valeurs.get(3), 10);
         bdd.executeUpdate("UPDATE eleve SET nom='"+valeurs.get(1)+"', prenom='"+valeurs.get(2)+"', age=" +age+" WHERE id_eleve="+id);
    }
    public void modifier(Connexion bdd, String table, ArrayList <String> valeurs) throws SQLException
    {
        if(table=="eleve")
            modifierEleve(bdd, valeurs);
    }
    
    public static void ajoutEleve(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {
        int age= Integer.parseInt(valeurs.get(2), 10);
        bdd.executeUpdate("INSERT INTO eleve (nom, prenom, age)" + "VALUES ('"+valeurs.get(0)+"', '"+valeurs.get(1)+"', '"+age+ "')");
    }
    
    public static void ajoutNiveau(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {   
        bdd.executeUpdate("INSERT INTO niveau (nom)" + "VALUES ('"+valeurs.get(0)+"')");
    }
    
    public static void ajoutClasse(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {
        int ecole=Integer.parseInt(valeurs.get(1), 10);
        int niveau=Integer.parseInt(valeurs.get(2), 10);
        int annee=Integer.parseInt(valeurs.get(3), 10);
        bdd.executeUpdate("INSERT INTO classe (nom, id_ecole, id_niveau, id_annee)" + "VALUES ('"+valeurs.get(0)+"', '"+ecole+"', '"+niveau+ "', '"+annee+"')");
    }
    
     public static void ajoutProf(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {
        int age= Integer.parseInt(valeurs.get(2), 10);
        bdd.executeUpdate("INSERT INTO professeur (nom, prenom, age)" + "VALUES ('"+valeurs.get(0)+"', '"+valeurs.get(1)+"', '"+age+ "')");
    }
     
    public static void modifierProf(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {
        int id=Integer.parseInt(valeurs.get(0), 10);
         int age= Integer.parseInt(valeurs.get(3), 10);
         bdd.executeUpdate("UPDATE professeur SET nom='"+valeurs.get(1)+"', prenom='"+valeurs.get(2)+"', age=" +age+" WHERE id_professeur="+id);
    }
    
   
}
