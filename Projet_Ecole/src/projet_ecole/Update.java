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
    
    public static void ajoutDiscipline(Connexion bdd, ArrayList<String> valeurs) throws SQLException
    {
        bdd.executeUpdate("INSERT INTO discipline(nom)"+ "VALUES ('"+valeurs.get(0)+"')");
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
     
    public static void modifierProf(Connexion bdd, ArrayList<String> valeurs) throws SQLException //id, nom, prenom, age
    {
        int id=Integer.parseInt(valeurs.get(0), 10);
         int age= Integer.parseInt(valeurs.get(3), 10);
         bdd.executeUpdate("UPDATE professeur SET nom='"+valeurs.get(1)+"', prenom='"+valeurs.get(2)+"', age=" +age+" WHERE id_professeur="+id);
    }
    
    public static void ajouterEval(Connexion bdd, ArrayList<String> valeurs) throws SQLException //
    {
        
    }
    
    public static void inscrireEleve(Connexion bdd, ArrayList<String> valeurs)throws SQLException
    {
        String nom = valeurs.get(0);
        System.out.println(nom);
        String prenom = valeurs.get(1);
        System.out.println(prenom);
        String nomClasse = valeurs.get(2);
        System.out.println(nomClasse);
        String annee = valeurs.get(3);
        System.out.println(annee);

        ArrayList<String> result = new ArrayList<>();
       // bdd.executeUpdate("INSERT INTO professeur (nom, prenom, age)" + "VALUES ('"+valeurs.get(0)+"', '"+valeurs.get(1)+"', '"+age+ "')");
        String sqlQuery = "SELECT id_eleve FROM eleve WHERE nom='"+nom+"'AND prenom='"+prenom+"'";
        result = bdd.remplirChampsRequete(sqlQuery);
        int id_eleve = Integer.parseInt(result.get(0));
        System.out.println(id_eleve);

        sqlQuery = "SELECT id_classe FROM classe WHERE nom='"+nomClasse+"'AND id_annee='"+annee+"'";
        result = bdd.remplirChampsRequete(sqlQuery);
        int id_classe = Integer.parseInt(result.get(0));
        System.out.println(id_classe);

        sqlQuery = "INSERT INTO inscription (id_classe, id_eleve)" + "VALUES ('"+id_classe+"', '"+id_eleve+"')";
        bdd.executeUpdate(sqlQuery);

    }

}
