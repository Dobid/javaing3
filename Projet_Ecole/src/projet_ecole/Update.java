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
    
    public static void setMoyenne(Connexion bdd, int detail, int bulletin, int trimestre, int note) throws SQLException
    {
        System.out.println(note);
        ArrayList<String> resultat=bdd.remplirChampsRequete("SELECT COUNT(note) FROM evaluation WHERE id_detailbull="+detail);
        int nbr_note=Integer.parseInt(resultat.get(0));
        System.out.println(nbr_note);
        resultat=bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin="+bulletin);
        int moy=Integer.parseInt(resultat.get(0));
        System.out.println(moy);
        moy=moy+note;
        System.out.println(moy);
        moy=moy/nbr_note;
        System.out.println(moy);
        System.out.println(resultat.get(0));
        bdd.executeUpdate("UPDATE bulletin SET moyenne="+moy+" WHERE id_bulletin="+bulletin);
    }
    
    public static void ajouterEval(Connexion bdd, ArrayList<String> valeurs) throws SQLException //id_eleve, trimestre, note, appréciation
    {
        
        ArrayList<String> result=bdd.remplirChampsRequete("SELECT id_inscription FROM inscription WHERE id_eleve="+Integer.parseInt(valeurs.get(0)));
        
        result=bdd.remplirChampsRequete("SELECT id_bulletin FROM bulletin WHERE id_inscription="+Integer.parseInt(result.get(0))+" AND id_trimestre="+Integer.parseInt(valeurs.get(1)));
        int bul=Integer.parseInt(result.get(0));
        result=bdd.remplirChampsRequete("SELECT id_detailbull FROM detailbulletin WHERE id_bulletin="+Integer.parseInt(result.get(0)));
        int detbul=Integer.parseInt(result.get(0));
        bdd.executeUpdate("INSERT INTO evaluation (id_detailbull, note, appreciation)" + "VALUES ("+Integer.parseInt(result.get(0))+", "+Integer.parseInt(valeurs.get(2))+", '"+valeurs.get(3)+"')");
        setMoyenne(bdd, detbul, bul, Integer.parseInt(valeurs.get(1)), Integer.parseInt(valeurs.get(2)));
    }
    
    public static void modifiereval(Connexion bdd, String nouv_eval, int id) throws SQLException
    {
        bdd.executeUpdate("UPDATE bulletin SET evaluation='"+nouv_eval+"' WHERE id_bulletin="+id);
    }
    
   
}
