/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;

import java.sql.SQLException;
import projet_ecole.Connexion;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sebas
 */
public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connexion base = new Connexion("ecole", "root", "");
        ArrayList<String> val = new ArrayList<String>();
        ArrayList<String> val2 = new ArrayList<String>();
        // val.add("CM2");
        // val.add("pour");
        // val.add("67");
        /*
         * val.add("2"); val.add("Essai"); val.add("Affichage"); val.add("22");
         * 
         * Update.modifierEleve(base, val); Reporting.consulterBulletin(base, 1);
         * Query.afficherTable(base, "eleve");

        val.add("Robert");
        val.add("Pierre");
        val.add("TD10");
        val.add("2009");

        Update.inscrireEleve(base, val);
        val2.add("1");
        val2.add("1");
        val2.add("4");
        val2.add("De grosses lacunes");
        Update.ajouterEval(base, val2);*/

        val.add("TD09");


        Update.supprClasse(base, val);
    }

}
