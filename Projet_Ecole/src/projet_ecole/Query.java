/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet_ecole;
import java.sql.*;

/**
 *
 * @author sebas
 */
public abstract class Query {
    public static void afficherTable(Connexion bdd, String table) throws SQLException
    {
        System.out.println(bdd.remplirChampsRequete("SELECT * FROM " +table));
    }
}
