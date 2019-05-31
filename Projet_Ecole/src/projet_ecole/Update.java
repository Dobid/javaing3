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

    public static void modifierEleve(Connexion bdd, ArrayList<String> valeurs) throws SQLException {
        int id = Integer.parseInt(valeurs.get(0), 10);
        int age = Integer.parseInt(valeurs.get(3), 10);
        bdd.executeUpdate("UPDATE eleve SET nom='" + valeurs.get(1) + "', prenom='" + valeurs.get(2) + "', age=" + age
                + " WHERE id_eleve=" + id);
    }

    public void modifier(Connexion bdd, String table, ArrayList<String> valeurs) throws SQLException {
        if (table == "eleve")
            modifierEleve(bdd, valeurs);
    }

    public static void ajoutEleve(Connexion bdd, ArrayList<String> valeurs) throws SQLException {
        int age = Integer.parseInt(valeurs.get(2), 10);
        bdd.executeUpdate("INSERT INTO eleve (nom, prenom, age)" + "VALUES ('" + valeurs.get(0) + "', '"
                + valeurs.get(1) + "', '" + age + "')");
    }

    public static void ajoutNiveau(Connexion bdd, ArrayList<String> valeurs) throws SQLException {
        bdd.executeUpdate("INSERT INTO niveau (nom)" + "VALUES ('" + valeurs.get(0) + "')");
    }

    public static void ajoutDiscipline(Connexion bdd, ArrayList<String> valeurs) throws SQLException {
        bdd.executeUpdate("INSERT INTO discipline(nom)" + "VALUES ('" + valeurs.get(0) + "')");
    }

    public static void ajoutClasse(Connexion bdd, ArrayList<String> valeurs) throws SQLException {
        int ecole = Integer.parseInt(valeurs.get(1), 10);
        int niveau = Integer.parseInt(valeurs.get(2), 10);
        int annee = Integer.parseInt(valeurs.get(3), 10);
        bdd.executeUpdate("INSERT INTO classe (nom, id_ecole, id_niveau, id_annee)" + "VALUES ('" + valeurs.get(0)
                + "', '" + ecole + "', '" + niveau + "', '" + annee + "')");
    }

    public static void ajoutProf(Connexion bdd, ArrayList<String> valeurs) throws SQLException { //nom, prenom, age, discipline, classe
        int age = Integer.parseInt(valeurs.get(2), 10);
        bdd.executeUpdate("INSERT INTO professeur (nom, prenom, age)" + "VALUES ('" + valeurs.get(0) + "', '"
                + valeurs.get(1) + "', '" + age + "')");
        ArrayList<String> resultat=new ArrayList<String>();
        resultat=bdd.remplirChampsRequete("SELECT id_discipline FROM discipline WHERE nom='"+valeurs.get(3)+"'");
        int discipline=Integer.parseInt(resultat.get(0));
        resultat=bdd.remplirChampsRequete("SELECT id_classe FROM classe WHERE nom='"+valeurs.get(4)+"'");
        int classe=Integer.parseInt(resultat.get(0));
        resultat=bdd.remplirChampsRequete("SELECT id_professeur FROM professeur WHERE nom='"+valeurs.get(0)+"'");
        int prof=Integer.parseInt(resultat.get(0));
        bdd.executeUpdate("INSERT INTO enseignement (id_classe, id_discipline, id_professeur)" +"VALUES ("+classe+", "+discipline+ ", "+prof+")");
    }

    public static void modifierProf(Connexion bdd, ArrayList<String> valeurs) throws SQLException // id, nom, prenom,
                                                                                                  // age, discipline, classe
    {
        int id = Integer.parseInt(valeurs.get(0), 10);
        int age = Integer.parseInt(valeurs.get(3), 10);
        bdd.executeUpdate("UPDATE professeur SET nom='" + valeurs.get(1) + "', prenom='" + valeurs.get(2) + "', age="
                + age + " WHERE id_professeur=" + id);
    }

    public static void setMoyenne(Connexion bdd, int detail, int bulletin, int trimestre, int note)
            throws SQLException {
        System.out.println(note);
        ArrayList<String> resultat = bdd
                .remplirChampsRequete("SELECT COUNT(note) FROM evaluation WHERE id_detailbull=" + detail);
        int nbr_note = Integer.parseInt(resultat.get(0));
        System.out.println(nbr_note);
        resultat = bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin=" + bulletin);
        int moy = Integer.parseInt(resultat.get(0));
        System.out.println(moy);
        moy = moy + note;
        System.out.println(moy);
        moy = moy / nbr_note;
        System.out.println(moy);
        System.out.println(resultat.get(0));
        bdd.executeUpdate("UPDATE bulletin SET moyenne=" + moy + " WHERE id_bulletin=" + bulletin);
    }

    public static void ajouterEval(Connexion bdd, ArrayList<String> valeurs) throws SQLException // id_eleve, trimestre,
                                                                                                 // note, appréciation
    {

        ArrayList<String> result = bdd.remplirChampsRequete(
                "SELECT id_inscription FROM inscription WHERE id_eleve=" + Integer.parseInt(valeurs.get(0)));

        result = bdd.remplirChampsRequete("SELECT id_bulletin FROM bulletin WHERE id_inscription="
                + Integer.parseInt(result.get(0)) + " AND id_trimestre=" + Integer.parseInt(valeurs.get(1)));
        int bul = Integer.parseInt(result.get(0));
        result = bdd.remplirChampsRequete(
                "SELECT id_detailbull FROM detailbulletin WHERE id_bulletin=" + Integer.parseInt(result.get(0)));
        int detbul = Integer.parseInt(result.get(0));
        bdd.executeUpdate("INSERT INTO evaluation (id_detailbull, note, appreciation)" + "VALUES ("
                + Integer.parseInt(result.get(0)) + ", " + Integer.parseInt(valeurs.get(2)) + ", '" + valeurs.get(3)
                + "')");
        setMoyenne(bdd, detbul, bul, Integer.parseInt(valeurs.get(1)), Integer.parseInt(valeurs.get(2)));
    }

    public static void inscrireEleve(Connexion bdd, ArrayList<String> valeurs) throws SQLException {
        String nom = valeurs.get(0);
        System.out.println(nom);
        String prenom = valeurs.get(1);
        System.out.println(prenom);
        String nomClasse = valeurs.get(2);
        System.out.println(nomClasse);
        String annee = valeurs.get(3);
        System.out.println(annee);

        ArrayList<String> result = new ArrayList<>();
        String sqlQuery = "SELECT id_eleve FROM eleve WHERE nom='" + nom + "'AND prenom='" + prenom + "'";
        result = bdd.remplirChampsRequete(sqlQuery);
        int id_eleve = Integer.parseInt(result.get(0));
        System.out.println(id_eleve);

        sqlQuery = "SELECT id_classe FROM classe WHERE nom='" + nomClasse + "'AND id_annee='" + annee + "'";
        result = bdd.remplirChampsRequete(sqlQuery);
        int id_classe = Integer.parseInt(result.get(0));
        System.out.println(id_classe);

        sqlQuery = "INSERT INTO inscription (id_classe, id_eleve)" + "VALUES ('" + id_classe + "', '" + id_eleve + "')";
        bdd.executeUpdate(sqlQuery);

        sqlQuery = "SELECT id_inscription FROM inscription WHERE id_eleve='" + id_eleve + "'AND id_classe='" + id_classe
                + "'";
        result = bdd.remplirChampsRequete(sqlQuery);
        int id_inscription = Integer.parseInt(result.get(0));

        sqlQuery = "INSERT INTO bulletin (id_trimestre, id_inscription, moyenne)" + "VALUES (1, '" + id_inscription
                + "', 0)";
        bdd.executeUpdate(sqlQuery);
        sqlQuery = "INSERT INTO bulletin (id_trimestre, id_inscription, moyenne)" + "VALUES (2, '" + id_inscription
                + "', 0)";
        bdd.executeUpdate(sqlQuery);
        sqlQuery = "INSERT INTO bulletin (id_trimestre, id_inscription, moyenne)" + "VALUES (3, '" + id_inscription
                + "', 0)";
        bdd.executeUpdate(sqlQuery);

    }

    public static void modifierAppEval(Connexion bdd, String nouv_eval, int id) throws SQLException {
        bdd.executeUpdate("UPDATE evaluation SET appreciation='" + nouv_eval + "' WHERE id_evaluation=" + id);
    }
    
    public static void supEval(Connexion bdd, int id) throws SQLException
    {
        int moy, nouv_moy=0;
        int detail, bulletin;
        String[] notes;
        ArrayList<String> resultat =new ArrayList<String>();
        resultat=bdd.remplirChampsRequete("SELECT id_detailbull FROM evaluation WHERE id_eval="+id);
        detail=Integer.parseInt(resultat.get(0));
        resultat=bdd.remplirChampsRequete("SELECT id_bulletin FROM detailbulletin WHERE id_detailbull="+detail);
        bulletin=Integer.parseInt(resultat.get(0));
        resultat=bdd.remplirChampsRequete("SELECT moyenne FROM bulletin WHERE id_bulletin="+bulletin);
        moy=Integer.parseInt(resultat.get(0));
        resultat=bdd.remplirChampsRequete("SELECT COUNT(note) FROM evaluation WHeRE id_eval="+id);
        int nbr_note=Integer.parseInt(resultat.get(0));
        if(nbr_note==1)
        {
            bdd.executeUpdate("UPDATE bulletin SET moyenne="+0+" WHERE id_bulletin="+bulletin);
            bdd.executeUpdate("DELETE FROM evaluation WHERE id_eval="+id);
        }
        else
        {
            bdd.executeUpdate("DELETE FROM evaluation WHERE id_eval="+id);
            resultat=bdd.remplirChampsRequete("SELECT note FROM evaluation WHERE id_detailbull="+detail);
            notes=new String[resultat.size()];
            for (int i=0; i<resultat.size(); i++)
            {
                notes[i]=resultat.get(i);
                nouv_moy=nouv_moy+Integer.parseInt(notes[i]);
            }
            System.out.println(resultat.size());
            nouv_moy=nouv_moy/resultat.size();
            bdd.executeUpdate("UPDATE bulletin SET moyenne="+nouv_moy+" WHERE id_bulletin="+bulletin);
        }
    }
    
    public static void prof (Connexion bdd)
    {
        
    }

}
